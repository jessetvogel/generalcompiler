package nl.jessevogel.generalcompiler;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    private final List<TokenType> tokenTypes = new ArrayList<>();
    private final List<String> messages = new ArrayList<>();

    void addTokenType(TokenType type) {
        tokenTypes.add(type);
    }

    boolean loadTokenTypes(String file) {
        TokenTypeLoader loader = new TokenTypeLoader(this);
        return loader.load(file);
    }

    List<TokenType> getTokenTypes() {
        return tokenTypes;
    }

    public void interpret(String source) {
        Lexer lexer = new Lexer(this, source);
        Token token;
        while ((token = lexer.next()) != null) {
            token.print();
        }
    }

    void addMessage(String message) {
        messages.add(message);
    }

    public void printMessages() {
        for (String message : messages)
            System.out.println(message);
    }
}
