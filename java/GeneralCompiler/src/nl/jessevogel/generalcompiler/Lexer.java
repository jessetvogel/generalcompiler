package nl.jessevogel.generalcompiler;

import java.util.LinkedList;

class Lexer {

    private Interpreter interpreter;
    private Scanner scanner;
    private LinkedList<Character> characterBuffer;
    private StringBuilder stringBuffer;

    Lexer(Interpreter interpreter, String source) {
        this.interpreter = interpreter;
        scanner = new Scanner(interpreter, source);
        characterBuffer = new LinkedList<>();
        stringBuffer = new StringBuilder();
    }

    Token next() {
        // If current buffer is empty, read new buffer
        if (stringBuffer.length() == 0) {
            Character character;
            while ((character = scanner.next()) != null) {
                characterBuffer.add(character);
                stringBuffer.append(character.character);
                if (character.character == '\n') break; // Stop buffer after newline
            }

            // If current buffer is still empty, there is no next buffer, i.e. we are at the end of the file
            if (stringBuffer.length() == 0)
                return null;
        }

        TokenType bestMatchType = null;
        int bestMatchLength = 0;

        for (TokenType type : interpreter.getTokenTypes()) {
            int matchLength = type.match(stringBuffer);
            if (matchLength > bestMatchLength) {
                bestMatchType = type;
                bestMatchLength = matchLength;
            }
        }

        if (bestMatchType == null) {
            interpreter.addMessage("Error in " + characterBuffer.get(0).getPositionString() + ": Unknown token");
            return null;
        }

        Token token = new Token(bestMatchType, stringBuffer.substring(0, bestMatchLength), characterBuffer.get(0));
        characterBuffer.subList(0, bestMatchLength).clear();
        stringBuffer.delete(0, bestMatchLength);
        return token;
    }
}
