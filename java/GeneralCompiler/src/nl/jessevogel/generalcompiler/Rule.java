package nl.jessevogel.generalcompiler;

class Rule {

    NodeType type;
    NodeType[] childrenTypes;

    Rule(NodeType type, int amountOfTerms) {
        this.type = type;
        childrenTypes = new NodeType[amountOfTerms];
    }
}
