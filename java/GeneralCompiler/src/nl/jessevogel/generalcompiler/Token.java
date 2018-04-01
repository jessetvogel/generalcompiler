package nl.jessevogel.generalcompiler;

class Token {

    final TokenType type;
    final String value;
    final int index;

    Token(TokenType type, String value, int index) {
        this.type = type;
        this.value = value;
        this.index = index;
    }

    void print() {
        System.out.println("Token of type '" + type.name + "' at index " + index + ": '" + value + "'");
    }
}
