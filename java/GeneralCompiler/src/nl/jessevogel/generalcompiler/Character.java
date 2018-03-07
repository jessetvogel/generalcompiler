package nl.jessevogel.generalcompiler;

public class Character {

    public char character;
    public String file;
    public int line;
    public int position;

    public Character(int character, String file, int line, int position) {
        this.character = (char) character;
        this.file = file;
        this.line = line;
        this.position = position;
    }

    public void print() {
        System.out.println("File " + file + " on " + (line + 1) + ":" + (position + 1) + "\t" + character);
    }
}
