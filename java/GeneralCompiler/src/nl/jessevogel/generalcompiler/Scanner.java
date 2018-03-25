package nl.jessevogel.generalcompiler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Scanner {

    private Interpreter interpreter;
    private FileReader fileReader;
    private String file;
    private int currentLine;
    private int currentPosition;
    private Character currentCharacter;

    Scanner(Interpreter interpreter, String source) {
        try {
            this.interpreter = interpreter;
            file = source;
            fileReader = new FileReader(new File(source));
            currentLine = 0;
            currentPosition = 0;
            currentCharacter = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Character next() {
        try {
            // Update current line and position
            if (currentCharacter != null) {
                if (newLine(currentCharacter)) {
                    currentLine++;
                    currentPosition = 0;
                } else {
                    currentPosition++;
                }
            }

            // Read character
            int i = fileReader.read();
            if (i == -1) return null;

            // Return Character
            return (currentCharacter = new Character(i, file, currentLine, currentPosition));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    void close() {
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getFile() {
        return file;
    }

    int getCurrentLine() {
        return currentLine;
    }

    int getCurrentPosition() {
        return currentPosition;
    }

    private boolean newLine(Character character) {
        // TODO: what counts as newline?
        if (character.character == '\n') return true;
//        if(character.character == '\r') return true;
        return false;
    }
}
