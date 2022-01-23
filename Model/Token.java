package Model;

public class Token {

    private String lexeme;
    private String category;
    private String token;
    private int line;

    public Token(String lexeme, String token, String category){
        this.lexeme = lexeme;
        this.category = category;
        this.token = token;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
