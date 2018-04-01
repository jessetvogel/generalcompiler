package nl.jessevogel.generalcompiler;

class Lexer {

    private Grammar grammar;
    private Scanner scanner;
    private int currentIndex;
    private StringBuilder stringBuffer;

    Lexer(Grammar grammar, String source) {
        this.grammar = grammar;
        scanner = new Scanner(source);
        currentIndex = 0;
        stringBuffer = new StringBuilder();
    }

    Token next() {
        // If current buffer is empty, read new buffer
        if (stringBuffer.length() == 0) {
            int character;
            while ((character = scanner.next()) != -1) {
                stringBuffer.append((char) character);
                if (character == '\n') break; // Stop buffer after newline
            }

            // If current buffer is still empty, there is no next buffer, i.e. we are at the end of the file
            if (stringBuffer.length() == 0)
                return null;
        }

        // Find best token match
        TokenType bestMatchType = null;
        int bestMatchLength = 0;
        for (TokenType type : grammar.tokenTypes) {
            int matchLength = type.match(stringBuffer);
            if (matchLength > bestMatchLength) {
                bestMatchType = type;
                bestMatchLength = matchLength;
            }
        }

        // If no match, give error
        if (bestMatchType == null) {
            Scanner.Position position = scanner.getPosition(currentIndex);
            System.err.println("Error in " + position.file + " at position " + position.line + ":" + position.position + ": Unknown token");
            return null;
        }

        // Return token
        Token token = new Token(bestMatchType, stringBuffer.substring(0, bestMatchLength), currentIndex);
        stringBuffer.delete(0, bestMatchLength);
        currentIndex += bestMatchLength;
        return token;
    }
}
