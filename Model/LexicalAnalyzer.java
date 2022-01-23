package Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class LexicalAnalyzer {
    private Map<String, Token> keywords;

    public LexicalAnalyzer() {
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
        
    }

    public List<Lexeme> codeAnalizer(Map<Integer, String> code) {
        List<Lexeme> lexemes = new ArrayList<>();
        String parsedCode = "";
        code.forEach((idLine, line) -> {
            Map<String, Token> lineMap = lineReader(line.strip());
            lineMap.forEach(
                (value, token) -> {
                    lexemes.add(new Lexeme(token, value, idLine));
                    parsedCode = parsedCode.join("\n", parsedCode, codeParser(token, value, idLine));
                });
        });
        System.out.println(parsedCode);
        return lexemes;
    }

    private Map<String, Token> lineReader(String line) {
        Map<String, Token> lineTokens = new LinkedHashMap<>();
        LexicalAutomaton la = new LexicalAutomaton();

        for (String str : line.split(" ")) {
            if (keywords.containsKey(str.toUpperCase())) {
                lineTokens.put(str, keywords.get(str.toUpperCase()));
            } else {
                lineTokens.put(str, la.evaluate(str));
            }
        }
        return lineTokens;
    }

    public String codeParser(Token token, String value, Integer line){
        String codeLine = "";
        if (token.name().equals("IDENTIFICADOR")){
            codeLine = codeLine 
                        + String.join("", 
                                    "<", token.name(), ",", 
                                    String.valueOf(line), ",",
                                    value, 
                                    ">");
        }else{
            codeLine = codeLine 
                        + String.join("", "<", value, ">");
        }
                
        
        return codeLine;
    }
}
