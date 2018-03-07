package nl.jessevogel.generalcompiler;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner("/Users/Jesse/Projects/generalcompiler/examples/test.txt");

        Character c;

        while((c = scanner.next()) != null) {
            c.print();
        }

    }
}
