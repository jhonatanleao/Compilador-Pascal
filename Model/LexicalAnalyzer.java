package Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class LexicalAnalyzer {
    private Map<String, Token> keywords;
    private List<Erro> errorList;
    private List<Lexeme> lexemes;
    private List<String> caracteres;

    public LexicalAnalyzer() {
        this.errorList = new ArrayList<>();
        this.keywords = new LinkedHashMap<>();
        this.lexemes = new ArrayList<>();
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
        keywords.put("=", Token.IGUALDADE);
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
        keywords.put("[", Token.COLCHETE_ESQUERDO);
        keywords.put("]", Token.COLCHETE_DIREITO);
        keywords.put("(*", Token.PARENTESE_ASTERISCO);
        keywords.put("*)", Token.ASTERISCO_PARENTESE);
        keywords.put("(", Token.PARENTESE_ESQUERDO);
        keywords.put(")", Token.PARENTESE_DIREITO);
        keywords.put("END.", Token.PALAVRA_CHAVE);
        caracteres = new ArrayList<>();
        caracteres.add("<>");
        caracteres.add("<=");
        caracteres.add(">=");
        caracteres.add(":=");
        caracteres.add("<");
        caracteres.add("=");
        caracteres.add(">");
        caracteres.add("+");
        caracteres.add("-");
        caracteres.add("*");
        caracteres.add("/");
        caracteres.add("|");
        caracteres.add(",");
        caracteres.add(":");
        caracteres.add(";");
        caracteres.add("[");
        caracteres.add("]");
        caracteres.add("(");
        caracteres.add(")");
    }

    public List<Erro> getErrorList() {
        return errorList;
    }

    public List<Lexeme> getLexemes(){
        return lexemes;
    }

    public void clearLists(){
        lexemes = new ArrayList<>();
        errorList = new ArrayList<>();
    }

    public void codeAnalizer(Map<Integer, String> code) {
        int cont;
        for (Map.Entry<Integer, String> codeEntry : code.entrySet()) {
            Map<String,Token> lineMap = lineReader(codeEntry.getValue(), codeEntry.getKey());
            cont = 0;
            for (Map.Entry<String, Token> lineEntry : lineMap.entrySet()){               
                if(lineEntry.getValue() != null){                   
                    lexemes.add(new Lexeme(lineEntry.getValue(), lineEntry.getKey(), codeEntry.getKey(),  cont));
                    cont += lineEntry.getKey().length() + 1;
                }
            }
        }
    }

    private Map<String, Token> lineReader(String line, Integer idLine) {
        Map<String, Token> lineTokens = new LinkedHashMap<>();
        LexicalAutomaton la = new LexicalAutomaton();
        boolean moreThanFifteen = false; 
        String pattern = "";

        for (String s : caracteres){
            if(line.contains(s)){                
                switch (s) {
                    case "(":
                        pattern = "\\(";
                        break;
                    case ")":
                        pattern = "\\)";
                        break;
                    case "[":
                        pattern = "\\[";
                        break;
                    case "]":
                        pattern = "\\]";
                        break;
                    case "+":
                        pattern = "\\+";
                        break;
                    default:
                        pattern = s;
                        break;
                }
                line = line.replaceAll(pattern, " " + s + " ");
            } 
        }
        if (line.contains(":  ="))
            line = line.replaceAll(":  =", ":=");
        else if (line.contains(">  ="))
            line = line.replaceAll(">  =", ">=");
        else if (line.contains("<  ="))
            line = line.replaceAll("<  =", "<=");
        else if (line.contains("<  >"))
            line = line.replaceAll("<  >", "<>");
            
        for (String str : line.split(" ")) { 
            if (keywords.containsKey(str.toUpperCase())) {
                lineTokens.put(str, keywords.get(str.toUpperCase()));
            } else {
                Token token = la.evaluate(str);
                
                if(str.length() > 15)
                    moreThanFifteen = true;

                if (token == Token.INVALID_CARACTERE || token == Token.BEGIN_INVALID ||  moreThanFifteen){
                    erroAnalizer(str, idLine, line.indexOf(str), token, moreThanFifteen);
                }
                lineTokens.put(str, token);
            }      
        }
        return lineTokens;
    }

    public void erroAnalizer(String str, Integer idLine, Integer idWord, Token token, boolean moreThanFifteen){
        String error = "";
        Integer id = getErrorList().size() + 1;
        if (moreThanFifteen){
            error = "String com mais de 15 caracteres na %1$d, coluna %2$d";
            getErrorList().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));
        }
       
        if(token == Token.INVALID_CARACTERE){
            error = "Identificador cont??m caracteres inv??lidos na linha %1$d, coluna %2$d";
            getErrorList().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));
        }

        if(token == Token.BEGIN_INVALID){
            error = "Identificador come??a de forma inv??lida %1$d, coluna %2$d";
            getErrorList().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));
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