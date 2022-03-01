package Model;

public class Erro {
    private Integer id;
    private String type;
    private Integer line;
    private Integer column;
    private String lexeme;

    public Erro(Integer id, String lexeme, String type, Integer line, Integer column){
        this.id = id;
        this.lexeme = lexeme;
        this.type = type;
        this.line = line;
        this.column = column;
    }

    public Integer getId() {
        return id;
    }
    
    public String getLexeme() {
        return lexeme;
    }

    public String getType() {
        return type;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }

}
