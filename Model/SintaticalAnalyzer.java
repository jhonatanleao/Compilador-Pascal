package Model;

import java.util.List;

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

    private String identificadores_instrucoes;

    public SintaticalAnalyzer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
        this.index = 0;
        this.tipo = "INTEGERREALCHARSTRING";
        this.operador_mais_menos = "MAIS MENOS";
        this.tipo_numerico = "REAL INTEIRO";
        this.operador_numerico = "IGUALDADE DIFERENTE MENOR MENOR_IGUAL MAIOR MAIOR_IGUAL";
        this.operador_divisao_multiplicacao = "DIVISAO MULTIPLICACAO";
        this.operador_logico = "AND OR";
        this.funcao_procedure = "PROCEDURE FUNCTION";
        this.identificadores_instrucoes = "IF THEN ELSE WHILE REPEAT UNTIL BREAK CONTINUE";
    }

    private void readLexeme() {
        if (index < lexemes.size()) {
            index++;
        }
    }

    private String getLexemeValue(int index) {
        return lexemes.get(index).getValue();
    }

    private Token getLexemeToken(int index) {
        return lexemes.get(index).getToken();
    }

    public void programa() {

        if (getLexemeValue(index).equals("PROGRAM")) {
            readLexeme();
            if (getLexemeToken(index).equals(Token.IDENTIFICADOR)) {
                readLexeme();
                if (getLexemeToken(index).equals(Token.PONTO_VIRGULA)) {
                    readLexeme();
                    declaracoes();
                    if (getLexemeValue(index).equals("BEGIN")) {

                        readLexeme();

                        instrucoes();
                        if (getLexemeValue(index).equals("END.")) {
                            System.out.println("FIM");
                        } else {
                            System.out.println("Erro END.");
                        }
                    } else {
                        System.out.println("Erro BEGIN");
                    }
                } else {
                    System.out.println("Erro PONTO_VIRGULA");
                }
            } else {
                System.out.println("Erro IDENTIFICADOR");
            }
        } else {
            System.out.println("Erro PROGRAM");
        }
    }

    private void bloco() {

        if (getLexemeValue(index).equals("BEGIN")) {
            instrucoes();
            readLexeme();
            if (getLexemeValue(index).equals("END")) {
                readLexeme();
                if (getLexemeToken(index).equals(Token.PONTO_VIRGULA)) {
                    readLexeme();

                }
            }
        }
    }

    private void declaracoes() {
        declaracaoVariavel();
        declaracaoConstante();
        declaracaoProcedimento();

    }

    private void declaracaoConstante() {
        if (getLexemeValue(index).equals("CONST")) {
            readLexeme();
            declConstList();
        }
    }

    private void declConstList() {
        if (getLexemeToken(index).equals(Token.IDENTIFICADOR)) {
            readLexeme();
            if (getLexemeToken(index).equals(Token.DOIS_PONTOS)) {
                readLexeme();
                if (tipo.contains(getLexemeValue(index))) {
                    readLexeme();
                    if (getLexemeToken(index).equals(Token.IGUALDADE)) {
                        readLexeme();
                        valor();
                        if (getLexemeToken(index).equals(Token.PONTO_VIRGULA)) {
                            readLexeme();
                            declConstList();
                        }
                    }
                }
            } else if (getLexemeToken(index).equals(Token.IGUALDADE)) {
                readLexeme();
                valor();
                if (getLexemeToken(index).equals(Token.PONTO_VIRGULA)) {
                    readLexeme();
                    declConstList();
                }
            }
        }
    }

    private void declaracaoVariavel() {
        if (getLexemeValue(index).equals("VAR")) {
            readLexeme();
            declVarList();
        }
    }

    private void declVarList() {
        delcVar();
        if (variavel())
            declVarList();
    }

    private void delcVar() {

        if (variavel()) {
            conjuntoIds();
            readLexeme();
            if (getLexemeToken(index).equals(Token.DOIS_PONTOS)) {
                readLexeme();
                if (tipo.contains(getLexemeValue(index))) {
                    readLexeme();
                    if (getLexemeToken(index).equals(Token.PONTO_VIRGULA)) {
                        readLexeme();
                    }
                }
            }
        }
    }

    private void conjuntoIds() {

        if (getLexemeToken(index).equals(Token.VIRGULA)) {
            readLexeme();
            if (variavel()) {
                readLexeme();
                conjuntoIds();
            }
        }
    }

    private void valor() {

        if (getLexemeToken(index).equals(Token.STRING)) {
            readLexeme();
        } else {
            unario();
            readLexeme();

        }
    }

    private void declaracaoProcedimento() {
        declProc();
        if (funcao_procedure.contains(getLexemeValue(index)))
            declaracaoProcedimento();
    }

    private void declProc() {

        boolean isProcedure = false;

        if (funcao_procedure.contains(getLexemeValue(index))) {
            readLexeme();
            if (getLexemeValue(index).equals("PROCEDURE")) {
                isProcedure = true;

            }
            if (getLexemeToken(index).equals(Token.IDENTIFICADOR)) {
                readLexeme();
                if (getLexemeToken(index).equals(Token.PARENTESE_ESQUERDO)) {

                    readLexeme();
                    parametros();
                    if (getLexemeToken(index).equals(Token.PARENTESE_DIREITO)) {
                        readLexeme();
                        if (isProcedure) {
                            if (getLexemeToken(index).equals(Token.PONTO_VIRGULA)) {
                                readLexeme();

                                declaracaoVariavel();
                                bloco();
                            }
                        } else {
                            if (getLexemeToken(index).equals(Token.DOIS_PONTOS)) {
                                readLexeme();
                                if (tipo.contains(getLexemeValue(index))) {
                                    readLexeme();

                                    if (getLexemeToken(index).equals(Token.PONTO_VIRGULA)) {
                                        readLexeme();
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

    private void parametros() {
        declVarList();
    }

    private void instrucoes() {

        inst();
        if (getLexemeToken(index).equals(Token.IDENTIFICADOR)
                || identificadores_instrucoes.contains(getLexemeValue(index)))
            instrucoes();

    }
 // daqui pra baixo nao precisa
    private void inst() {

        if (getLexemeToken(index).equals(Token.IDENTIFICADOR)) {
            readLexeme();
            if (getLexemeToken(index).equals(Token.OPERADOR_ATRIBUICAO)) {
                readLexeme();
                expr();
                readLexeme();
                if (getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                    readLexeme();

            } else if (getLexemeToken(index).equals(Token.COLCHETE_ESQUERDO)) {
                readLexeme();
                expr();
                readLexeme();
                if (getLexemeToken(index).equals(Token.COLCHETE_DIREITO)) {
                    readLexeme();
                    if (getLexemeToken(index).equals(Token.OPERADOR_ATRIBUICAO)) {
                        readLexeme();
                        expr();
                        readLexeme();
                        if (getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                            readLexeme();
                    } else {
                        System.out.println("Erro, é espera um operador de atribuição");
                    }
                } else {
                    System.out.println("Erro, colchete direito não encontrado");
                }
            } else if (getLexemeToken(index).equals(Token.PARENTESE_ESQUERDO)) {
                readLexeme();
                parametros2();
                readLexeme();
                if (getLexemeToken(index).equals(Token.PARENTESE_DIREITO)) {
                    readLexeme();
                    if (getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                        readLexeme();
                } else {
                    System.out.println("Erro, parentese direito não encontrado");
                }
            }

        } else if (getLexemeValue(index).equals("IF")) {
            System.out.println("IF - " + getLexemeValue(index));
            readLexeme();
            expr();
            readLexeme();
            System.out.println(getLexemeValue(index));
            if (getLexemeValue(index).equals("THEN")) {
                inst();
                if (getLexemeValue(index).equals("ELSE"))
                    inst();
            } else {
                System.out.println("Erro, é esperado um then");
            }

        } else if (getLexemeValue(index).equals("WHILE")) {
            readLexeme();
            expr();
            readLexeme();
            if (getLexemeValue(index).equals("DO")) {
                inst();
            } else {
                System.out.println("Erro, é esperado um DO no laço de repetição WHILE");
            }

        } else if (getLexemeValue(index).equals("REPEAT")) {
            readLexeme();
            inst();
            if (getLexemeValue(index).equals("UNTIL")) {
                readLexeme();
                expr();
                readLexeme();
                if (getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                    readLexeme();
            } else {
                System.out.println("Erro, é esperado um UNTIL no laço de repetição REPEAT");
            }

        } else if (getLexemeValue(index).equals("BREAK")) {
            readLexeme();
            if (getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                readLexeme();

        } else if (getLexemeValue(index).equals("CONTINUE")) {
            readLexeme();
            if (getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                readLexeme();

        } else if (getLexemeValue(index).equals("BEGIN")) {
            bloco();
        } else {

        }
    }

    private void parametros2() {
        if (getLexemeToken(index).equals(Token.VIRGULA)) {
            readLexeme();
            expr();
            readLexeme();
            parametros2();

        } else {
            expr();
            readLexeme();
            parametros2();
        }

    }

    private void expr() {
        exprComparacao();
        readLexeme();
        expr2();
    }

    private void expr2() {

        if (operador_logico.contains(getLexemeValue(index))) {
            System.out.println("expr2 - " + getLexemeValue(index));

            exprComparacao();
            readLexeme();
            expr2();
        }
    }

    private void exprComparacao() {
        exprOp();
        System.out.println("exprComparacao - " + getLexemeValue(index));
        readLexeme();

        exprComparacao2();
    }

    private void exprComparacao2() {

        System.out.println("exprComparacao2 - " + getLexemeValue(index));
        if (operador_numerico.contains(getLexemeToken(index).toString())) {
            readLexeme();

            exprOp();
            readLexeme();
            exprComparacao2();
        }
    }

    private void exprOp() {
        System.out.println("exprOp - " + getLexemeValue(index));
        termo();
        readLexeme();
        exprOp2();
    }

    private void termo() {
        unario();
        readLexeme();
        termo2();
    }

    private void exprOp2() {
        if (operador_mais_menos.contains(getLexemeToken(index).toString())) {
            readLexeme();

            termo();
            readLexeme();
            System.out.println("exprOp2 - " + getLexemeValue(index));
            exprOp2();
        }
    }

    private void termo2() {

        if (operador_divisao_multiplicacao.contains(getLexemeToken(index).toString())) {
            readLexeme();

            unario();
            readLexeme();
            termo2();
        }
    }

    private void unario() {

        if (operador_mais_menos.contains(getLexemeToken(index).toString())) {
            readLexeme();

            fator();
        } else {
            fator();
        }
    }

    private void fator() {

        if (variavel() || num() || literal()) {
        } else {
            // erro
        }

        // if(getLexemeToken(index).equals(Token.PARENTESE_ESQUERDO)){
        // expr();
        // if(getLexemeToken(index).equals(Token.PARENTESE_DIREITO)){
        // System.out.println("Erro");
        // }
        // }
        // if(!tipo.contains(getLexemeToken(index).toString()) &&
        // !getLexemeToken(index).equals(Token.INVALID_CARACTERE)){
        // variavel();
        // }else{
        // System.out.println("Erro");
        // }

    }

    private boolean variavel() {
        if (getLexemeToken(index).equals(Token.IDENTIFICADOR))
            return true;
        else
            return false;
    }

    private boolean num() {

        if (tipo_numerico.contains(getLexemeToken(index).toString()))
            return true;
        else
            return false;
    }

    private boolean id() {
        if (getLexemeToken(index).equals(Token.IDENTIFICADOR))
            return true;
        else
            return false;
    }

    private boolean literal() {
        if (getLexemeToken(index).equals(Token.STRING))
            return true;
        else
            return false;
    }
}
