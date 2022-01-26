package Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class LexicalAnalyzer {
    private Map<String, Token> keywords;
    private List<String> errorList;

    public LexicalAnalyzer() {
        this.errorList = new ArrayList<>();
        this.keywords = new LinkedHashMap<>();
        keywords.put("PROGRAM", Token.PALAVRA_CHAVE);
        keywords.put("BEGIN", Token.PALAVRA_CHAVE);
        keywords.put("END", Token.PALAVRA_CHAVE);
        keywords.put("CONST", Token.PALAVRA_CHAVE);
        keywords.put("VAR", Token.PALAVRA_CHAVE);
        keywords.put("INTEGER", Token.PALAVRA_CHAVE);
        keywords.put("REAL", Token.PALAVRA_CHAVE);
        keywords.put("CHAR", Token.PALAVRA_CHAVE);
        keywords.put("STRING", Token.PALAVRA_CHAVE);
        keywords.put("LITERAL", Token.PALAVRA_CHAVE);
        keywords.put("PROCEDURE", Token.PALAVRA_CHAVE);
        keywords.put("FUNCTION", Token.PALAVRA_CHAVE);
        keywords.put("IF", Token.PALAVRA_CHAVE);
        keywords.put("THEN", Token.PALAVRA_CHAVE);
        keywords.put("ELSE", Token.PALAVRA_CHAVE);
        keywords.put("WHILE", Token.PALAVRA_CHAVE);
        keywords.put("DO", Token.PALAVRA_CHAVE);
        keywords.put("REPEAT", Token.PALAVRA_CHAVE);
        keywords.put("UNTIL", Token.PALAVRA_CHAVE);
        keywords.put("BREAK", Token.PALAVRA_CHAVE);
        keywords.put("CONTINUE", Token.PALAVRA_CHAVE);
        keywords.put("OR", Token.PALAVRA_CHAVE);
        keywords.put("AND", Token.PALAVRA_CHAVE);
        keywords.put("<>", Token.DIFERENTE);
        keywords.put("<", Token.MENOR);
        keywords.put("<=", Token.MENOR_IGUAL);
        keywords.put(">", Token.MAIOR);
        keywords.put(">=", Token.MAIOR_IGUAL);
        keywords.put("==", Token.IGUALDADE);
        keywords.put(":=", Token.OPERADOR_ATRIBUICAO);
        keywords.put("NUM", Token.PALAVRA_CHAVE);
        keywords.put("+", Token.MAIS);
        keywords.put("-", Token.MENOS);
        keywords.put("*", Token.MULTIPLICACAO);
        keywords.put("/", Token.DIVISAO);
        keywords.put("|", Token.PIPE);
        keywords.put(",", Token.VIRGULA);
        keywords.put(":", Token.DOIS_PONTOS);
        keywords.put(";", Token.PONTO_VIRGULA);
        keywords.put("//", Token.DUAS_BARRAS);
        keywords.put("{", Token.ABRE_CHAVE);
        keywords.put("}", Token.FECHA_CHAVE);
        keywords.put("(*", Token.PARENTESE_ASTERISCO);
        keywords.put("*)", Token.ASTERISCO_PARENTESE);
        keywords.put("END.", Token.PALAVRA_CHAVE);
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public List<Lexeme> codeAnalizer(Map<Integer, String> code) {
        List<Lexeme> lexemes = new ArrayList<>();
        
        code.forEach((idLine, line) -> {
            //System.out.println(line);
            Map<String, Token> lineMap = lineReader(line.strip(), idLine);
            lineMap.forEach(
                (value, token) -> {
                    lexemes.add(new Lexeme(token, value, idLine));
                });
        });
        
        return lexemes;
    }

    private Map<String, Token> lineReader(String line, Integer idLine) {
        Map<String, Token> lineTokens = new LinkedHashMap<>();
        LexicalAutomaton la = new LexicalAutomaton();
        boolean containSemicolon = false;
        boolean moreThanFifteen = false;
        for (String str : line.split(" ")) {
            if(str.contains(";")){
                String teste[] = str.split(";");
                str = String.join("", teste[0]);
                containSemicolon = true;
            } 

            if (keywords.containsKey(str.toUpperCase())) {
                lineTokens.put(str, keywords.get(str.toUpperCase()));
            } else {
                Token token = la.evaluate(str);
                
                if(str.length() > 15)
                    moreThanFifteen = true;

                if (token == Token.INVALID_CARACTERE || token == Token.BEGIN_INVALID ||  moreThanFifteen){
                    wordAnalizer(str, idLine, line.indexOf(str), token);
                }
                lineTokens.put(str, token);
            }     
            if(containSemicolon){
                lineTokens.put(";", keywords.get(";"));
                containSemicolon = false;
            }
        }
        return lineTokens;
    }

    public void wordAnalizer(String str, Integer idLine, Integer idWord, Token token){
        String error = "";
        if (str.length() > 15){
            error = "String with more than 15 characters in line %1$d, column %2$d";
            getErrorList().add(String.format(error, idLine, idWord));
        }
       
        String specialCaracteres = "$ % @ # ! ? / ° º ` ç Ç";
        if(token == Token.INVALID_CARACTERE){
            for (String s : specialCaracteres.split(" ")) {
                if (str.indexOf(s) != -1){
                    error = "String contains invalid characters in line %1$d, column %2$d";
                    getErrorList().add(String.format(error, idLine, idWord));
                    break;
                }
            }
        }

        if(token == Token.BEGIN_INVALID){
            error = "Invalid begin for declaration in line %1$d, column %2$d";
            getErrorList().add(String.format(error, idLine, idWord));
        }

    }

    public String codeParser(List<Lexeme> lexemes){
        String codeLine = "";
        int line = 0;
        for (Lexeme lexeme : lexemes) {
            if (line != lexeme.getLine()){
                codeLine += "\n";
                line = lexeme.getLine();
            }
            if (lexeme.getToken().name().equals("IDENTIFICADOR")){
                codeLine += "".
                            concat("<").
                            concat(lexeme.getToken().name()).
                            concat(",").
                            concat(String.valueOf(lexeme.getLine())).
                            concat(",").
                            concat(lexeme.getValue()).
                            concat(">");
            }else{
                codeLine += "".
                            concat("<").
                            concat(lexeme.getValue()).
                            concat(">");
            }
        }   
        return codeLine;
    }
}