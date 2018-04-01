package nl.jessevogel.generalcompiler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TokenType {

    String name;
    private Pattern pattern;

    TokenType(String name, String pattern) {
        this.name = name;
        try {
            this.pattern = Pattern.compile("^(" + pattern + ")");
        }
        catch(Exception e) {
            System.err.print("Invalid regex: " + pattern);
            this.pattern = null;
        }
    }

    int match(StringBuilder buffer) {
        if(pattern == null) return 0;

        Matcher matcher = pattern.matcher(buffer);
        if (!matcher.find())
            return 0;

        return matcher.group().length();
    }
}
