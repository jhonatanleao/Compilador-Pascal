package Model;

public class Lexeme {

    private Token token;
    private String value;
    private int line;

    public Lexeme(Token token, String value, int line){
        this.token = token;
        this.value = value;
        this.line = line;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "token: " 
                + getToken()
                + "; value: " 
                + getValue()
                + "; line: "
                + getLine();
    }
}
