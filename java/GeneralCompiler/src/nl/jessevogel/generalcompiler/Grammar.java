package nl.jessevogel.generalcompiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Grammar {

    List<TokenType> tokenTypes;
    HashMap<String, NodeType> nodeTypes;
    List<Rule> rules;

    Grammar() {
        tokenTypes = new ArrayList<>();
        nodeTypes = new HashMap<>();
        rules = new ArrayList<>();
    }

    boolean loadTokenTypes(String file) {
        TokenTypeLoader loader = new TokenTypeLoader(this);
        return loader.load(file);
    }

    boolean loadGrammer(String file) {
        GrammarLoader loader = new GrammarLoader(this);
        return loader.load(file);
    }

    public TokenType getTokenType(String typeName) {
        for(TokenType type : tokenTypes) {
            if(type.name.equals(typeName))
                return type;
        }
        return null;
    }

    public NodeType getOrIntroduceType(String typeName) {
        NodeType type = nodeTypes.get(typeName);
        if(type != null) return type;

        TokenType tokenType = getTokenType(typeName);
        if(tokenType != null)
            type = new NodeType(tokenType);
        else
            type = new NodeType(typeName);

        nodeTypes.put(typeName, type);
        return type;
    }



    void print() {
        for(Rule rule : rules) {
            System.out.print(rule.type.name + " =");
            for(int i = 0;i < rule.childrenTypes.length; ++i)
                System.out.print(" " + rule.childrenTypes[i].name);
            System.out.println();
        }
    }
}
