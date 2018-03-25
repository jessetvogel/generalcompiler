package nl.jessevogel.generalcompiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TokenTypeLoader {

    private static final Pattern patternSkip = Pattern.compile("^\\s*(#.*)?$");
    private static final Pattern patternDefinition = Pattern.compile("^([A-Za-z_]+) (.*)$");

    private Interpreter interpreter;

    TokenTypeLoader(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    boolean load(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Matcher matcher;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;

                // Ignore comments or empty lines
                matcher = patternSkip.matcher(line);
                if (matcher.find()) continue;

                // Parse definitions
                matcher = patternDefinition.matcher(line);
                if (matcher.find()) {
                    interpreter.addTokenType(new TokenType(matcher.group(1), matcher.group(2)));
                    continue;
                }

                // Otherwise, give warning
                interpreter.addMessage("Warning: invalid token type definition on line " + lineNumber + " of " + file);
            }

            br.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
