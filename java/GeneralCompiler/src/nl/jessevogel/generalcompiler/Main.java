package nl.jessevogel.generalcompiler;

public class Main {

    public static void main(String[] args) {
        // Load grammar
        Grammar grammar = new Grammar();
        grammar.loadTokenTypes("/Users/jessetvogel/Desktop/language.tokens");
        grammar.loadGrammer("/Users/jessetvogel/Desktop/language.grammar");
        grammar.print();

        // Create parser
        Parser parser = new Parser(grammar);

        // Parse file
        Node node = parser.parse(grammar.getOrIntroduceType("if_statement"), "/Users/jessetvogel/Desktop/source.txt");
        if(node != null) {
            node.print();
        }
        else {
            System.out.println("Unable to parse!");
        }
    }
}
