package nl.jessevogel.generalcompiler;

public class Node {

    NodeType type;
    String value = null;
    Node[] children;
    int tokenLength;

    Node(NodeType type, int amountOfChildren) {
        this.type = type;
        children = new Node[amountOfChildren];
    }

    Node(NodeType type, Token token) {
        this.type = type;
        value = token.value;
        tokenLength = 1;
    }



    void print() {
        printPrefix("");
    }

    private void printPrefix(String prefix) {
        if(value != null) {
            System.out.println(prefix + "[" + type.name + "] " + value);
        }
        else {
            System.out.println(prefix + "[" + type.name + "]");
            for(int i = 0; i < children.length; ++i)
                children[i].printPrefix(prefix + "  ");
        }
    }
}
