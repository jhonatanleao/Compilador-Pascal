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
<<<<<<< HEAD

    public SintaticalAnalyzer(List<Lexeme> lexemes) {
=======
    private String funcao_procedure;
    public SintaticalAnalyzer(List<Lexeme> lexemes){
>>>>>>> master
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

    private void readLexeme() {
        if (index < lexemes.size()) {
            index++;
        }
    }
    
    private String getLexemeValue(int index){
        return lexemes.get(index).getValue();
    }

<<<<<<< HEAD
    private void programa() {
        // Lexeme lexeme = new Lexeme()
        // boolean state = false;
        // if(lexemes.get(index).getValue().equals('PROGRAM')){
        // readLexeme();
        // if(lexemes.get(index).getToken().equals('IDENTIFICADOR')){
        // readLexeme();
        // if((lexemes.get(index).getToken().equals('PONTO_VIRGULA')){
        // readLexeme();
        // declaracoes();
        // if((lexemes.get(index).getValue().equals('BEGIN')){
        // instrucoes();
        // if((lexemes.get(index).getValue().equals('END.')){
        // System.out.println('FIM');
        // }
        // }else{
        // System.out.println('Erro: Está faltando identificador PONTO_VIRGULA');
        // }
        // }else{
        // System.out.println('Erro: IDENTIFICADOR iválido.');
        // }
        // }else{
        // System.out.println('Erro: Está faltando identificador PROGRAM.');
        // }
    }

    private void declaracoes() {
        declaracaoVariavel();
        // declaracaoConstante();
        // declaracaoProcedimento();

    }

    private void declaracaoConstante() {
        // if(lexemes.get(index).getValue().equals('CONST')){
        // readLexeme();
        //
        // declConstList();
        // }
    }

    private void declConstList() {
        // if(lexemes.get(index).getToken().equals('IDENTIFICADOR')){
        // readLexeme();
        // if(lexemes.get(index).getValue().equals('DOIS_PONTOS')){
        // readLexeme();
        // if(tipo.contains(lexemes.get(index).getValue())){
        // readLexeme();
        // if(lexemes.get(index).getValue().equals('IGUALDADE')){
        // readLexeme();
        // valor();
        //
        // }
        // }
        // }
        // }
    }

    private void declaracaoVariavel() {
        readLexeme();
        if (!lexemes.get(index).getToken().equals(Token.INVALID_CARACTERE)) {
=======
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
>>>>>>> master
            declVarList();
        }
    }

    private void declVarList() {
        delcVar();
        declConstList();
    }

    private void delcVar() {
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

<<<<<<< HEAD
    private void conjuntoIds() {
        readLexeme();
        variavel();

    }

    private void valor() {
        // if(!lexemes.get(index).getToken().equals('STRING') &&
        // !lexemes.get(index).getToken().equals('INVALID_CARACTERE'))){
        // unario();
        // }
    }

    private void declaracaoProcedimento() {
        declProc();
        

    }

    private void declProc() {
        readLexeme();
        if (lexemes.get(index).getToken().equals(Token.PALAVRA_CHAVE)) {


        }

=======
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
        if(getLexemeToken(index).equals("LITERAL"){
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
        boolean isProcedute = false;
        readLexeme();
        if (funcao_procedure.contains(getLexemeValue(index))){
            readLexeme();
            if (getLexemeValue(index).equals("PROCEDURE")){
                isProcedute = true;
            }
            if(getLexemeToken(index).equals(Token.IDENTIFICADOR)){
                readLexeme();
                if (getLexemeToken(index).equals(Token.PARENTESE_ESQUERDO)){
                    parametros();
                    if(getLexemeToken(index).equals(Token.PARENTESE_DIREITO)){
                        readLexeme();
                        if(isProcedute){
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
>>>>>>> master
    }

    private void parametros() {
        declVarList();
    }

<<<<<<< HEAD
    private void instrucoes() {

=======
    private void instrucoes(){
        inst();
        instrucoes();
>>>>>>> master
    }

    private void inst() {

        
    }

    private void parametros2() {
        readLexeme();
    }

    private void expr() {
        exprComparacao();
        expr2();
    }

    private void expr2() {
        readLexeme();
<<<<<<< HEAD
        if (operador_logico.contains(lexemes.get(index).getToken().toString())) {
=======
        if(operador_logico.contains(getLexemeValue(index))){
>>>>>>> master
            exprComparacao();
            expr2();
        }
    }

    private void exprComparacao() {
        exprOp();
        exprComparacao2();
    }

    private void exprComparacao2() {
        readLexeme();
<<<<<<< HEAD
        if (operador_numerico.contains(lexemes.get(index).getToken().toString())) {
=======
        if(operador_numerico.contains(getLexemeToken(index).toString())){
>>>>>>> master
            exprOp();
            exprComparacao2();
        }
    }

    private void exprOp() {
        termo();
        exprOp2();
    }

    private void termo() {
        unario();
        termo2();
    }

    private void exprOp2() {
        readLexeme();
<<<<<<< HEAD
        if (operador_mais_menos.contains(lexemes.get(index).getToken().toString())) {
=======
        if(operador_mais_menos.contains(getLexemeToken(index).toString())){
>>>>>>> master
            termo();
            exprOp2();
        }
    }

    private void termo2() {
        readLexeme();
<<<<<<< HEAD
        if (operador_divisao_multiplicacao.contains(lexemes.get(index).getToken().toString())) {
=======
        if(operador_divisao_multiplicacao.contains(getLexemeToken(index).toString())){
>>>>>>> master
            unario();
            termo2();
        }
    }

    private void unario() {
        readLexeme();
<<<<<<< HEAD
        if (operador_mais_menos.contains(lexemes.get(index).getToken().toString())) {
=======
        if(operador_mais_menos.contains(getLexemeToken(index).toString())){
>>>>>>> master
            fator();
        } else {
            fator();
        }
    }

<<<<<<< HEAD
    private void fator() {
        if (lexemes.get(index).getToken().equals(Token.PARENTESE_ESQUERDO)) {
            expr();
            if (lexemes.get(index).getToken().equals(Token.PARENTESE_DIREITO)) {
=======
    private void fator(){
        if(getLexemeToken(index).equals(Token.PARENTESE_ESQUERDO)){
            expr();
            if(getLexemeToken(index).equals(Token.PARENTESE_DIREITO)){
>>>>>>> master
                System.out.println("Erro");
            }
        }

<<<<<<< HEAD
        if (!tipo.contains(lexemes.get(index).getToken().toString())
                && !lexemes.get(index).getToken().equals(Token.INVALID_CARACTERE)) {
            variavel();
        } else {
            System.out.println("Erro");
        }
    }

    private void variavel() {
        if (lexemes.get(index).getToken().equals(Token.IDENTIFICADOR))
            exprOp();
    }

    private boolean num() {
        if (tipo_numerico.contains(lexemes.get(index).getToken().toString()))
=======
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
>>>>>>> master
            return true;
        else
            return false;
    }

<<<<<<< HEAD
    private boolean id() {
        if (lexemes.get(index).getToken().equals(Token.IDENTIFICADOR))
=======
    private boolean id(){
        if (getLexemeToken(index).equals(Token.IDENTIFICADOR))
>>>>>>> master
            return true;
        else
            return false;
    }

<<<<<<< HEAD
    private boolean literal() {
        if (lexemes.get(index).getToken().equals(Token.STRING))
=======
    private boolean literal(){
        if (getLexemeToken(index).equals(Token.STRING))
>>>>>>> master
            return true;
        else
            return false;
    }
}
