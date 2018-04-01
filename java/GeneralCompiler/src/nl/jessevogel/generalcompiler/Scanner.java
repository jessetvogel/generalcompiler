package nl.jessevogel.generalcompiler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Scanner {

    private FileReader fileReader;
    private String file;
    private int currentIndex;
    private List<Integer> lineIndices;

    Scanner(String source) {
        try {
            file = source;
            fileReader = new FileReader(new File(source));
            currentIndex = 0;
            lineIndices = new ArrayList<>();
            lineIndices.add(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int next() {
        try {
            // Read character
            int i = fileReader.read();
            if (i == -1) return -1;

            // Increment index and check for newlines
            currentIndex ++;
            if(newLine(i))
                lineIndices.add(currentIndex);

            // Return character
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    Position getPosition(int index) {
        Position position = new Position();
        position.file = file;
        int n = lineIndices.size();
        int line;
        for(line = 0;line < n; ++ line) {
            int lineIndex = lineIndices.get(line);
            if(index < lineIndex) {
                -- line;
                break;
            }
        }

        position.line = line;
        position.position = index - lineIndices.get(line);
        return position;
    }

    private boolean newLine(int character) {
        // TODO: what counts as newline?
        if (character == '\n') return true;
//        if(character.character == '\r') return true;
        return false;
    }

    class Position {
        String file;
        int line;
        int position;
    }
}
