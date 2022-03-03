package Model;

import java.util.List;

import javax.print.DocFlavor.STRING;

public class SintaticalAnalyzer {
    
    private List<Lexeme> lexemes;
    private String tipo;
    private Integer index;
    private String operador_mais_menos;
    private String tipo_numerico;
    private String operador_numerico;
    private String operador_divisao_multiplicacao;
    private String operador_logico;

    public SintaticalAnalyzer(List<Lexeme> lexemes){
        this.lexemes = lexemes;
        this.index = 0;
        this.tipo = "INTEGER REAL CHAR STRING";
        this.operador_mais_menos = "MAIS MENOS";
        this.tipo_numerico = "REAL INTEIRO";
        this.operador_numerico = "IGUALDADE DIFERENTE MENOR MENOR_IGUAL MAIOR MAIOR_IGUAL";
        this.operador_divisao_multiplicacao = "DIVISAO MULTIPLICACAO";
        this.operador_logico = "OPERADOR_LOGICO_OU OPERADOR_LOGICO_E";
    }

    private void readLexeme(){
        if (index < lexemes.size()){
            index++;
        }
    }

    private void programa(){     
        
        boolean state = false;
        if(lexemes.get(index).getValue().equals("PROGRAM")){
            readLexeme();
            if(lexemes.get(index).getToken().equals(Token.IDENTIFICADOR)){
                readLexeme();
                if((lexemes.get(index).getToken().equals(Token.PONTO_VIRGULA))){
                    readLexeme();
                    declaracoes();
                    if((lexemes.get(index).getValue().equals("BEGIN"))){
                        instrucoes();
                        if((lexemes.get(index).getValue().equals("END."))){
                            System.out.println("FIM");        
                        }else{
                            System.out.println("Erro: Está faltando identificador PONTO_VIRGULA");
                        }
                    }else{
                        System.out.println("Erro: IDENTIFICADOR iválido.");
                    }
                }else{
                    System.out.println("Erro: Está faltando identificador PROGRAM.");
                }
            }else{
                System.out.println("Erro");
            }
        }else{
                System.out.println("Erro");
        }
    }
    private void declaracoes(){
        declaracaoVariavel();
       // declaracaoConstante();
       // declaracaoProcedimento();

    }

    private void declaracaoConstante(){
       // if(lexemes.get(index).getValue().equals("CONST")){
       //     readLexeme();
       //
       //     declConstList(); 
       // }
    }

    private void declConstList(){
       //  if(lexemes.get(index).getToken().equals("IDENTIFICADOR")){
       //     readLexeme();
       //     if(lexemes.get(index).getValue().equals("DOIS_PONTOS")){
       //         readLexeme();
       //         if(tipo.contains(lexemes.get(index).getValue())){
       //             readLexeme();
       //             if(lexemes.get(index).getValue().equals("IGUALDADE")){
       //                 readLexeme();
       //                 valor();
       //                 
       //             } 
       //         } 
       //     }
       // }
    }
    
    private void declaracaoVariavel(){
        readLexeme();
        if (!lexemes.get(index).getToken().equals(Token.INVALID_CARACTERE)) {
            declVarList();
        }
    }
    
    private void declVarList(){
        delcVar();
        declConstList();
    }
    
    private void delcVar(){
        readLexeme();
        variavel();
        conjuntoIds();
        if (lexemes.get(index).getToken().equals(Token.DOIS_PONTOS)) {
            readLexeme();
            if (tipo.contains(lexemes.get(index).getToken().toString())) {
                readLexeme();
                if (!lexemes.get(index).getToken().equals(Token.PONTO_VIRGULA)) {
                    System.out.println("Erro");
                }
            }
        }
    }

    private void conjuntoIds(){

    }
    
    private void valor(){
       // if(!lexemes.get(index).getToken().equals("STRING") && !lexemes.get(index).getToken().equals("INVALID_CARACTERE"))){
       //    unario();
       // }
    }
    
    private void declaracaoProcedimento(){
        
    }

    private void declProc(){

    }

    private void parametros(){
        declVarList();
    }

    private void instrucoes(){

    }

    private void inst(){
        
    }

    private void parametros2(){
        readLexeme();
    }

    private void expr(){
        exprComparacao();
        expr2();
    }
   
    private void expr2(){
        readLexeme();
        if(operador_logico.contains(lexemes.get(index).getToken().toString())){
            exprComparacao();
            expr2();
        }
    }

    private void exprComparacao(){
        exprOp();
        exprComparacao2();
    }
    
    private void exprComparacao2(){
        readLexeme();
        if(operador_numerico.contains(lexemes.get(index).getToken().toString())){
            exprOp();
            exprComparacao2();
        }
    }
    
    private void exprOp(){
       termo();
       exprOp2();
    }

    private void termo(){
       unario();
       termo2();
    }

    private void exprOp2(){
        readLexeme();
        if(operador_mais_menos.contains(lexemes.get(index).getToken().toString())){
            termo();
            exprOp2();
        }
    }

    private void termo2(){
        readLexeme();
        if(operador_divisao_multiplicacao.contains(lexemes.get(index).getToken().toString())){
            unario();
            termo2();
        }
    }

    private void unario(){
        readLexeme();
        if(operador_mais_menos.contains(lexemes.get(index).getToken().toString())){
            fator();
        }else{
            fator();
        }
    }

    private void fator(){
        if(lexemes.get(index).getToken().equals(Token.PARENTESE_ESQUERDO)){
            expr();
            if(lexemes.get(index).getToken().equals(Token.PARENTESE_DIREITO)){
                System.out.println("Erro");
            }
        }

        if(!tipo.contains(lexemes.get(index).getToken().toString()) && !lexemes.get(index).getToken().equals(Token.INVALID_CARACTERE)){
           variavel();
        }else{
            System.out.println("Erro");
        }
    }

    private void variavel(){
        if (lexemes.get(index).getToken().equals(Token.IDENTIFICADOR))
            exprOp();    
    }

    private boolean num(){
        if (tipo_numerico.contains(lexemes.get(index).getToken().toString()))
            return true;
        else
            return false;
    }

    private boolean id(){
        if (lexemes.get(index).getToken().equals(Token.IDENTIFICADOR))
            return true;
        else 
            return false;
    }

    private boolean literal(){
        if (lexemes.get(index).getToken().equals(Token.STRING))
            return true;
        else 
            return false;
    }
}
