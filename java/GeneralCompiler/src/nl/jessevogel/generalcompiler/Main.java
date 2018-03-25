package nl.jessevogel.generalcompiler;

public class Main {

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();

        interpreter.loadTokenTypes("/Users/jessetvogel/Projects/generalcompiler/examples/tokentypes.txt");
        interpreter.interpret("/Users/jessetvogel/Projects/generalcompiler/examples/source.txt");

        interpreter.printMessages();
    }
}
