package nl.jessevogel.generalcompiler;

import java.util.List;
import java.util.ArrayList;

public class Parser {

    private Grammar grammar;
    private List<Token> currentTokens;

    public Parser(Grammar grammar) {
        this.grammar = grammar;
    }

    public Node parse(NodeType type, String file) {
        currentTokens = new ArrayList<>();
        Lexer lexer = new Lexer(grammar, file);
        Token token;
        while ((token = lexer.next()) != null) {
            // Ignore all tokens that start with a dash '_'
            if (token.type.name.charAt(0) == '_') continue;

            currentTokens.add(token);
        }

        for(Token t : currentTokens)  t.print(); // TODO: remove this

        return findNode(type, 0);
    }

    public Node findNode(NodeType type, int index) {
        // If looking for a token, check if it is there, and if so return it
        Token token = currentTokens.get(index);
        if(token.type.name.equals(type.name))
            return new Node(type, token);

        // Try to find a fitting rule
        for (Rule rule : grammar.rules) {
            if (rule.type != type) continue;

            int n = rule.childrenTypes.length;
            Node node = new Node(type, n);
            int newIndex = index;
            boolean match = true;
            for (int i = 0; i < n; ++i) {
                Node child = findNode(rule.childrenTypes[i], newIndex);
                if(child == null) {
                    match = false;
                    break;
                }
                node.children[i] = child;
                node.tokenLength += child.tokenLength;
                newIndex += child.tokenLength;
            }

            if(match)
                return node;
        }

        return null;
    }
}
