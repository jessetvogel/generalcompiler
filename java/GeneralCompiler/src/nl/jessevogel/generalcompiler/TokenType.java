package nl.jessevogel.generalcompiler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TokenType {

    String identifier;
    Pattern pattern;

    TokenType(String identifier, String pattern) {
        this.identifier = identifier;
        this.pattern = Pattern.compile("^(" + pattern + ")");
    }

    int match(StringBuilder buffer) {
        Matcher matcher = pattern.matcher(buffer);
        if (!matcher.find())
            return 0;

        return matcher.group().length();
    }
}
