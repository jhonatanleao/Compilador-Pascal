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
    private String funcao_procedure;
    public SintaticalAnalyzer(List<Lexeme> lexemes){
        this.lexemes = lexemes;
        this.index = 0;
        this.tipo = "INTEGER REAL CHAR STRING";
        this.operador_mais_menos = "MAIS MENOS";
        this.tipo_numerico = "REAL INTEIRO";
        this.operador_numerico = "IGUALDADE DIFERENTE MENOR MENOR_IGUAL MAIOR MAIOR_IGUAL";
        this.operador_divisao_multiplicacao = "DIVISAO MULTIPLICACAO";
        this.operador_logico = "OPERADOR_LOGICO_OU OPERADOR_LOGICO_E";
        this.funcao_procedure = "PROCEDUREFUNCTION";
    }

    private void readLexeme(){
        if (index < lexemes.size()){
            index++;
        }
    }
    
    private String getLexemeValue(int index){
        return lexemes.get(index).getValue();
    }

    private Token getLexemeToken(int index){
        return lexemes.get(index).getToken();
    }


    private void programa(){      
        if(getLexemeValue(index).equals("PROGRAM")){
            readLexeme();
            if(getLexemeToken(index).equals(Token.IDENTIFICADOR)){
                 readLexeme();
                if(getLexemeToken(index).equals(Token.PONTO_VIRGULA)){
                    readLexeme();
                    declaracoes();
                    if(getLexemeValue(index).equals("BEGIN")){
                        instrucoes();
                        if(getLexemeValue(index).equals("END.")){
                            System.out.println("FIM");        
                        } else{
                            System.out.println("Erro END.");
                        } 
                    }else{
                        System.out.println("Erro BEGIN");
                    }   
                }else{
                    System.out.println("Erro PONTO_VIRGULA");
                }
            }else{
                System.out.println("Erro IDENTIFICADOR");
            }
        }else{
            System.out.println("Erro PROGRAM");
        }
    }
    
    private void bloco(){
        readLexeme();
        if(getLexemeValue(index).equals("BEGIN")){
            instrucoes();
            readLexeme();
            if(getLexemeValue(index).equals("END")){
                readLexeme();
                if(!getLexemeToken(index).equals(Token.PONTO_VIRGULA)){
                    System.out.println("Erro PONTO_VIRGULA");
                }
            }
        }
    }
  
    private void declaracoes(){
        declaracaoVariavel();
        declaracaoConstante();
        declaracaoProcedimento();

    }

    private void declaracaoConstante(){
        if(getLexemeValue(index).equals("CONST")){
            readLexeme(); 
            declConstList(); 
        }
    }

    private void declConstList(){
        if(getLexemeToken(index).equals(Token.IDENTIFICADOR)){
            readLexeme();
            if(getLexemeToken(index).equals(Token.DOIS_PONTOS)){
                readLexeme();
                if(tipo.contains(getLexemeValue(index))){
                    readLexeme();
                    if(getLexemeToken(index).equals(Token.IGUALDADE)){
                        readLexeme();
                        valor();
                        if(getLexemeToken(index).equals(Token.PONTO_VIRGULA)){
                            readLexeme();
                            declConstList();
                        }
                    } 
                } 
            }else if(getLexemeToken(index).equals(Token.IGUALDADE)){
                readLexeme();
                valor();
                if(getLexemeToken(index).equals(Token.PONTO_VIRGULA)){
                    readLexeme();
                    declConstList();
                }
            }
        }
    }
    
    private void declaracaoVariavel(){
        if (getLexemeValue(index).equals("VAR")) {
            readLexeme();
            declVarList();
        }
    }
    
    private void declVarList(){
        delcVar();
        declConstList();
    }
    
    private void delcVar(){
        readLexeme();
        if (variavel()){
            conjuntoIds();
            readLexeme();
            if (getLexemeToken(index).equals(Token.DOIS_PONTOS)) {
                readLexeme();
                if (tipo.contains(getLexemeToken(index).toString())) {
                    readLexeme();
                    if (!getLexemeToken(index).equals(Token.PONTO_VIRGULA)) {
                        System.out.println("Erro");
                    }
                }
            }
        } 
    }

    private void conjuntoIds(){
        readLexeme();
        if (getLexemeToken(index).equals(Token.VIRGULA)){
            readLexeme();
            if (variavel()) {
                conjuntoIds();
            } 
        }
    }
    
    private void valor(){
        if(getLexemeToken(index).equals("LITERAL")){
            readLexeme();
        }else{
            unario(); 
        }
    }
    
    private void declaracaoProcedimento(){
        declProc();
        declaracaoProcedimento();
    }

    private void declProc(){
        boolean isProcedure = false;
        readLexeme();
        if (funcao_procedure.contains(getLexemeValue(index))){
            readLexeme();
            if (getLexemeValue(index).equals("PROCEDURE")){
                isProcedure = true;
            }
            if(getLexemeToken(index).equals(Token.IDENTIFICADOR)){
                readLexeme();
                if (getLexemeToken(index).equals(Token.PARENTESE_ESQUERDO)){
                    parametros();
                    if(getLexemeToken(index).equals(Token.PARENTESE_DIREITO)){
                        readLexeme();
                        if(isProcedure){
                            if(getLexemeToken(index).equals(Token.PONTO_VIRGULA)){
                               declaracaoVariavel();
                               bloco();
                            } 
                        }else{
                            if (getLexemeToken(index).equals(Token.DOIS_PONTOS)){
                                readLexeme();
                                if (tipo.contains(getLexemeValue(index))){
                                    if (getLexemeToken(index).equals(Token.PONTO_VIRGULA)) {
                                        declaracaoVariavel();
                                        bloco();
                                    }
                                }
                            }      
                        }    
                    }
                }
            }
        }
    }

    private void parametros(){
        declVarList();
    }

    private void instrucoes(){
        inst();
        instrucoes();
    }

    private void inst(){
        readLexeme();
        if(getLexemeToken(index).equals(Token.IDENTIFICADOR)){
            readLexeme();
            if(getLexemeToken(index).equals(Token.OPERADOR_ATRIBUICAO)){
                expr();
                readLexeme();
                if(!getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                    System.out.println("Erro, ponto e virgula não encontrado");                           
            }

            if(getLexemeToken(index).equals(Token.COLCHETE_ESQUERDO)){
                expr();
                if(getLexemeToken(index).equals(Token.COLCHETE_DIREITO)){
                    readLexeme();
                    if(getLexemeToken(index).equals(Token.OPERADOR_ATRIBUICAO)){
                        expr();
                        readLexeme();
                        if(!getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                            System.out.println("Erro, ponto e virgula não encontrado");                                     
                    } else {
                    System.out.println("Erro, é espera um operador de atribuição");                    
                    }
                } else {
                    System.out.println("Erro, colchete direito não encontrado");                    
                }
            }

            if(getLexemeToken(index).equals(Token.PARENTESE_ESQUERDO)){
                parametros2();
                readLexeme();
                if(getLexemeToken(index).equals(Token.PARENTESE_DIREITO)){
                    readLexeme();
                    if(!getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                        System.out.println("Erro, ponto e virgula não encontrado");                    
                } else {
                    System.out.println("Erro, parentese direito não encontrado");                    
                }
            }
        
        } else if(getLexemeValue(index).equals("IF")) {
            expr();
            readLexeme();
            if(getLexemeValue(index).equals("THEN")){
                inst();
                readLexeme();
                if(getLexemeValue(index).equals("ELSE"))
                    inst();
                else
                    index--;                
            } else {
                System.out.println("Erro, é esperado um then");
            }
            
        } else if(getLexemeValue(index).equals("WHILE")){
            expr();
            readLexeme();
            if(getLexemeValue(index).equals("DO")){
                inst();
            }else {
                System.out.println("Erro, é esperado um DO no laço de repetição WHILE");
            }

        } else if(getLexemeValue(index).equals("REPEAT")){
            inst();
            readLexeme();
            if(getLexemeValue(index).equals("UNTIL")){
                expr();
                readLexeme();
                if(!getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                    System.out.println("Erro, ponto e virgula não encontrado");
            } else {
                System.out.println("Erro, é esperado um UNTIL no laço de repetição REPEAT");
            }

        } else if(getLexemeValue(index).equals("BREAK")){
            readLexeme();
            if(!getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                System.out.println("Erro, ponto e virgula não encontrado");

        } else if(getLexemeValue(index).equals("CONTINUE")){
            readLexeme();
            if(!getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                System.out.println("Erro, ponto e virgula não encontrado");

        } else {
            bloco();
        }
    }

    private void parametros2(){       
        readLexeme();
        if (getLexemeToken(index).equals(Token.VIRGULA)){
            readLexeme();
            expr();
            parametros2();

        } else {
            index--;
            expr();
            parametros2();
        }
    }

    private void expr(){
        exprComparacao();
        expr2();
    }
   
    private void expr2(){
        readLexeme();
        if(operador_logico.contains(getLexemeValue(index))){
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
        if(operador_numerico.contains(getLexemeToken(index).toString())){
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
        if(operador_mais_menos.contains(getLexemeToken(index).toString())){
            termo();
            exprOp2();
        }
    }

    private void termo2(){
        readLexeme();
        if(operador_divisao_multiplicacao.contains(getLexemeToken(index).toString())){
            unario();
            termo2();
        }
    }

    private void unario(){
        readLexeme();
        if(operador_mais_menos.contains(getLexemeToken(index).toString())){
            fator();
        }else{
            fator();
        }
    }

    private void fator(){
        if(getLexemeToken(index).equals(Token.PARENTESE_ESQUERDO)){
            expr();
            if(getLexemeToken(index).equals(Token.PARENTESE_DIREITO)){
                System.out.println("Erro");
            }
        }

        if(!tipo.contains(getLexemeToken(index).toString()) && !getLexemeToken(index).equals(Token.INVALID_CARACTERE)){
           variavel();
        }else{
            System.out.println("Erro");
        }
    }
    
    private boolean variavel(){
        if (getLexemeToken(index).equals(Token.IDENTIFICADOR))
            return true;
        else
            return false;
    }

    private boolean num(){
        if (tipo_numerico.contains(getLexemeToken(index).toString()))
            return true;
        else
            return false;
    }

    private boolean id(){
        if (getLexemeToken(index).equals(Token.IDENTIFICADOR))
            return true;
        else 
            return false;
    }

    private boolean literal(){
        if (getLexemeToken(index).equals(Token.STRING))
            return true;
        else 
            return false;
    }
}
