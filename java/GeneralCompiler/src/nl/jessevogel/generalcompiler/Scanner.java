package nl.jessevogel.generalcompiler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Scanner {

    private FileReader fileReader;
    private String file;
    private int currentLine;
    private int currentPosition;
    private Character currentCharacter;

    public Scanner(String source) {
        // Initialize
        try {
            file = source;
            fileReader = new FileReader(new File(source));
            currentLine = 0;
            currentPosition = 0;
            currentCharacter = null;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Character next() {
        try {
            // Update current line and position
            if(currentCharacter != null) {
                if(newLine(currentCharacter)) {
                    currentLine ++;
                    currentPosition = 0;
                }
                else {
                    currentPosition ++;
                }
            }

            // Read character
            int i = fileReader.read();
            if(i == -1) return null;

            // Return Character
            return (currentCharacter = new Character(i, file, currentLine, currentPosition));
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            fileReader.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String getFile() {
        return file;
    }

    public int getCurrentLine() {
        return currentLine;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    private boolean newLine(Character character) {
        // TODO: what counts as newline?
        if(character.character == '\n') return true;
//        if(character.character == '\r') return true;
        return false;
    }

}
