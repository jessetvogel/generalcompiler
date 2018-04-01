package nl.jessevogel.generalcompiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GrammarLoader {

    private static final Pattern patternSkip = Pattern.compile("^\\s*(#.*)?$");
    private static final Pattern patternDefinition = Pattern.compile("^([A-Za-z_]\\w*)\\s*::=\\s*(.*)\\s*$");

    private Grammar grammar;

    GrammarLoader(Grammar grammar) {
        this.grammar = grammar;
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
                    // Find definition type
                    String typeName = matcher.group(1);
                    NodeType type = grammar.getOrIntroduceType(typeName);

                    // Find children types
                    String[] childrenTypeNames = matcher.group(2).trim().split("\\s+");
                    Rule rule = new Rule(type, childrenTypeNames.length);
                    for (int i = 0; i < childrenTypeNames.length; ++i)
                        rule.childrenTypes[i] = grammar.getOrIntroduceType(childrenTypeNames[i]);

                    // Add rule to list
                    grammar.rules.add(rule);
                    continue;
                }

                // Otherwise, give warning
                System.err.println("Warning: invalid rule on line " + lineNumber + " of " + file);
            }

            br.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
