package nl.jessevogel.generalcompiler;

class Character {

    char character;
    String file;
    int line;
    int position;

    Character(int character, String file, int line, int position) {
        this.character = (char) character;
        this.file = file;
        this.line = line;
        this.position = position;
    }

    public String getPositionString() {
        return "file " + file + " on " + (line + 1) + ":" + (position + 1);
    }

    void print() {
        System.out.println("File " + file + " on " + (line + 1) + ":" + (position + 1) + "\t" + character);
    }
}
