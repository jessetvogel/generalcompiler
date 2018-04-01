package nl.jessevogel.generalcompiler;

public class NodeType {

    public String name;
    public boolean isTokenType;

    public NodeType(String name) {
        this.name = name;
        isTokenType = false;
    }

    public NodeType(TokenType tokenType) {
        name = tokenType.name;
        isTokenType = true;
    }

}
