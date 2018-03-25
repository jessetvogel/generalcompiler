package nl.jessevogel.generalcompiler;

public class Token {

    private TokenType type;
    private String value;
    private Character start;

    public Token(TokenType type, String value, Character start) {
        this.type = type;
        this.value = value;
        this.start = start;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void print() {
        System.out.println("File " + start.file + " on " + (start.line + 1) + ":" + (start.position + 1) + " '" + value + "' (" + type.identifier + ")");
    }
}
