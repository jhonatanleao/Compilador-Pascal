package Model;

import java.util.ArrayList;
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
    private List<Erro> erros;
    private String str;
    private Integer idLine;
    private Integer idWord;
    private Integer id;
    private String error;

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
        this.identificadores_instrucoes = "IF ELSE WHILE REPEAT UNTIL BREAK CONTINUE";
        this.erros = new ArrayList<>();

        this.str = " ";
        this.idLine = 0;
        this.idWord = 0;
        this.id = 0;
        this.error = " ";

    }

    public List<Erro> getErros() {
        return this.erros;
    }

    private void readLexeme() {
        if (index < lexemes.size()) {
            index++;
        }
    }

    public void clearErros() {

        erros = new ArrayList<>();

    }

    private String getLexemeValue(int index) {
        return lexemes.get(index).getValue();
    }

    private Token getLexemeToken(int index) {
        return lexemes.get(index).getToken();
    }

    public void programa() {
        System.out.println(getLexemeValue(index));
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
                            System.out.println("alô");
                            id = getErros().size() + 1;
                            str = lexemes.get(index).getValue();
                            idLine = lexemes.get(index).getLine();
                            idWord = lexemes.get(index).getColumn();
                            error = "END nao encontrado na %1$d, coluna %2$d";
                            System.out.println("alô1");
                            getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                            System.out.println("Erro END.");
                        }
                    } else {
                        id = getErros().size() + 1;
                        str = lexemes.get(index).getValue();
                        idLine = lexemes.get(index).getLine();
                        idWord = lexemes.get(index).getColumn();
                        error = "BEGIN nao encontrado na %1$d, coluna %2$d";
                        getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                        System.out.println("Erro BEGIN");
                    }
                } else {
                    id = getErros().size() + 1;
                    str = lexemes.get(index).getValue();
                    idLine = lexemes.get(index - 1).getLine();
                    idWord = lexemes.get(index).getColumn() + 1 + lexemes.get(index).getValue().length();
                    error = " ; nao encontrado na linha %1$d, coluna %2$d";
                    getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                    System.out.println("Erro PONTO_VIRGULA");
                }
            } else {
                id = getErros().size() + 1;
                str = lexemes.get(index).getValue();
                idLine = lexemes.get(index).getLine();
                idWord = lexemes.get(index).getColumn();
                error = "Identificador nao encontrado na %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                System.out.println("Erro IDENTIFICADOR");
            }
        } else {
            id = getErros().size() + 1;
            str = lexemes.get(index).getValue();
            idLine = lexemes.get(index).getLine();
            idWord = lexemes.get(index).getColumn();
            error = "PROGRAM nao encontrado na %1$d, coluna %2$d";
            getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

            System.out.println("Erro PROGRAM");
        }
    }

    private void bloco() {
        if (getLexemeValue(index).equals("BEGIN")) {
            readLexeme();
            instrucoes();

            if (getLexemeValue(index).equals("END")) {
                readLexeme();
                if (getLexemeToken(index).equals(Token.PONTO_VIRGULA)) {
                    readLexeme();
                } else {
                    id = getErros().size() + 1;
                    str = lexemes.get(index).getValue();
                    idLine = lexemes.get(index).getLine();
                    idWord = lexemes.get(index).getColumn();
                    error = "Falta ';'  na linha %1$d, coluna %2$d";
                    getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                    System.out.println("Falta PONTO_VIRGULA");
                }
            } else {
                id = getErros().size() + 1;
                str = lexemes.get(index).getValue();
                idLine = lexemes.get(index).getLine();
                idWord = lexemes.get(index).getColumn();
                error = "END nao encontrado na %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                System.out.println("Falta END");
            }
        } else {
            id = getErros().size() + 1;
            str = lexemes.get(index).getValue();
            idLine = lexemes.get(index).getLine();
            idWord = lexemes.get(index).getColumn();
            error = "BEGIN nao encontrado na %1$d, coluna %2$d";
            getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

            System.out.println("Falta BEGIN");
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
                        } else {
                            id = getErros().size() + 1;
                            str = lexemes.get(index).getValue();
                            idLine = lexemes.get(index).getLine();
                            idWord = lexemes.get(index).getColumn();
                            error = "Falta ';'na linha %1$d, coluna %2$d";
                            getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                            System.out.println("Falta PONTO_VIRGULA");
                        }
                    } else {
                        id = getErros().size() + 1;
                        str = lexemes.get(index).getValue();
                        idLine = lexemes.get(index).getLine();
                        idWord = lexemes.get(index).getColumn();
                        error = "Falta '='na linha %1$d, coluna %2$d";
                        getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                        System.out.println("Falta IGUALDADE");
                    }
                } else {
                    id = getErros().size() + 1;
                    str = lexemes.get(index).getValue();
                    idLine = lexemes.get(index).getLine();
                    idWord = lexemes.get(index).getColumn();
                    error = "Falta TIPO na linha %1$d, coluna %2$d";
                    getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                    System.out.println("Falta TIPO");
                }
            } else if (getLexemeToken(index).equals(Token.IGUALDADE)) {
                readLexeme();
                valor();
                if (getLexemeToken(index).equals(Token.PONTO_VIRGULA)) {
                    readLexeme();
                    declConstList();
                } else {

                    id = getErros().size() + 1;
                    str = lexemes.get(index).getValue();
                    idLine = lexemes.get(index).getLine();
                    idWord = lexemes.get(index).getColumn();
                    error = "Falta ';'na linha %1$d, coluna %2$d";
                    getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                    System.out.println("Falta PONTO_VIRGULA");
                }
            } else {
                id = getErros().size() + 1;
                str = lexemes.get(index).getValue();
                idLine = lexemes.get(index).getLine();
                idWord = lexemes.get(index).getColumn();
                error = "Falta '='na linha %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                System.out.println("Falta IGUALDADE");
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
            readLexeme();
            conjuntoIds();
            if (getLexemeToken(index).equals(Token.DOIS_PONTOS)) {
                readLexeme();
                if (tipo.contains(getLexemeValue(index))) {
                    readLexeme();
                    if (getLexemeToken(index).equals(Token.PONTO_VIRGULA)) {
                        readLexeme();
                    } else {
                        id = getErros().size() + 1;
                        str = lexemes.get(index).getValue();
                        idLine = lexemes.get(index).getLine();
                        idWord = lexemes.get(index).getColumn();
                        error = "Falta ';'na linha %1$d, coluna %2$d";
                        getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                        System.out.println("Falta PONTO_VIRGULA");
                    }
                } else {
                    id = getErros().size() + 1;
                    str = lexemes.get(index).getValue();
                    idLine = lexemes.get(index).getLine();
                    idWord = lexemes.get(index).getColumn();
                    error = "Falta TIPO na linha %1$d, coluna %2$d";
                    getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                    System.out.println("Falta TIPO");
                }
            } else {
                id = getErros().size() + 1;
                str = lexemes.get(index).getValue();
                idLine = lexemes.get(index).getLine();
                idWord = lexemes.get(index).getColumn();
                error = "Falta ':'na linha %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                System.out.println("Falta DOIS_PONTOS");
            }
        }
    }

    private void conjuntoIds() {
        if (getLexemeToken(index).equals(Token.VIRGULA)) {
            readLexeme();
            if (variavel()) {
                readLexeme();
                conjuntoIds();
            } else {
                id = getErros().size() + 1;
                str = lexemes.get(index).getValue();
                idLine = lexemes.get(index).getLine();
                idWord = lexemes.get(index).getColumn();
                error = "Falta VARIAVEL na linha %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                System.out.println("Falta VARIAVEL");
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

            if (getLexemeValue(index).equals("PROCEDURE")) {
                isProcedure = true;
            }
            readLexeme();
            System.out.println(getLexemeValue(index));
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
                            } else {
                                id = getErros().size() + 1;
                                str = lexemes.get(index).getValue();
                                idLine = lexemes.get(index).getLine();
                                idWord = lexemes.get(index).getColumn();
                                error = "Falta ';'na linha %1$d, coluna %2$d";
                                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                                System.out.println("Falta PONTO_VIRGULA");
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
                                    } else {
                                        id = getErros().size() + 1;
                                        str = lexemes.get(index).getValue();
                                        idLine = lexemes.get(index).getLine();
                                        idWord = lexemes.get(index).getColumn();
                                        error = "Falta ';'na linha %1$d, coluna %2$d";
                                        getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine,
                                                idWord));

                                        System.out.println("Falta PONTO_VIRGULA");
                                    }
                                } else {
                                    id = getErros().size() + 1;
                                    str = lexemes.get(index).getValue();
                                    idLine = lexemes.get(index).getLine();
                                    idWord = lexemes.get(index).getColumn();
                                    error = "Falta IDENTIFICADOR na linha %1$d, coluna %2$d";
                                    getErros().add(
                                            new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                                    System.out.println("Falta IDENTIFICADOR");
                                }
                            } else {
                                id = getErros().size() + 1;
                                str = lexemes.get(index).getValue();
                                idLine = lexemes.get(index).getLine();
                                idWord = lexemes.get(index).getColumn();
                                error = "Falta ':'na linha %1$d, coluna %2$d";
                                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                                System.out.println("Falta DOIS_PONTOS");
                            }
                        }
                    } else {
                        id = getErros().size() + 1;
                        str = lexemes.get(index).getValue();
                        idLine = lexemes.get(index).getLine();
                        idWord = lexemes.get(index).getColumn();
                        error = "Falta ')'na linha %1$d, coluna %2$d";
                        getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                        System.out.println("Falta PARENTESE_DIREITO");
                    }
                } else {
                    id = getErros().size() + 1;
                    str = lexemes.get(index).getValue();
                    idLine = lexemes.get(index).getLine();
                    idWord = lexemes.get(index).getColumn();
                    error = "Falta '('na linha %1$d, coluna %2$d";
                    getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));
                    System.out.println("Falta PARENTESE_ESQUERDO");
                }
            } else {
                id = getErros().size() + 1;
                str = lexemes.get(index).getValue();
                idLine = lexemes.get(index).getLine();
                idWord = lexemes.get(index).getColumn();
                error = "Falta IDENTIFICADOR na linha %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));
                System.out.println("Falta IDENTIFICADOR");
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

    private void inst() {

        if (getLexemeToken(index).equals(Token.IDENTIFICADOR)) {
            readLexeme();
            if (getLexemeToken(index).equals(Token.OPERADOR_ATRIBUICAO)) {
                readLexeme();
                expr();
                readLexeme();
                if (getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                    readLexeme();
                else {
                    id = getErros().size() + 1;
                    str = lexemes.get(index).getValue();
                    idLine = lexemes.get(index).getLine();
                    idWord = lexemes.get(index).getColumn();
                    error = "Falta ';' na linha %1$d, coluna %2$d";
                    getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                    System.out.println("Falta PONTO_VIRGULA");
                }

            } else if (getLexemeToken(index).equals(Token.COLCHETE_ESQUERDO)) {
                readLexeme();
                expr();
                if (getLexemeToken(index).equals(Token.COLCHETE_DIREITO)) {
                    readLexeme();
                    if (getLexemeToken(index).equals(Token.OPERADOR_ATRIBUICAO)) {
                        readLexeme();
                        expr();
                        if (getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                            readLexeme();
                        else {
                            id = getErros().size() + 1;
                            str = lexemes.get(index).getValue();
                            idLine = lexemes.get(index).getLine();
                            idWord = lexemes.get(index).getColumn();
                            error = "Falta ';' na linha %1$d, coluna %2$d";
                            getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                            System.out.println("Falta PONTO_VIRGULA");
                        }
                    } else {
                        id = getErros().size() + 1;
                        str = lexemes.get(index).getValue();
                        idLine = lexemes.get(index).getLine();
                        idWord = lexemes.get(index).getColumn();
                        error = "É esperado um operador de atribuição na linha %1$d, coluna %2$d";
                        getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                        System.out.println("Erro, é espera um operador de atribuição");
                    }
                } else {
                    id = getErros().size() + 1;
                    str = lexemes.get(index).getValue();
                    idLine = lexemes.get(index).getLine();
                    idWord = lexemes.get(index).getColumn();
                    error = " '}' não encontrado na linha %1$d, coluna %2$d";
                    getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

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
                    else {
                        str = lexemes.get(index).getValue();
                        idLine = lexemes.get(index).getLine();
                        idWord = lexemes.get(index).getColumn();
                        id = getErros().size() + 1;
                        error = " ';' não encontrado na linha %1$d, coluna %2$d";
                        getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));
                        System.out.println("Falta PONTO_VIRGULA");
                    }
                } else {
                    id = getErros().size() + 1;
                    str = lexemes.get(index).getValue();
                    idLine = lexemes.get(index).getLine();
                    idWord = lexemes.get(index).getColumn();
                    error = " ')' não encontrado na linha %1$d, coluna %2$d";
                    getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                    System.out.println("Erro, parentese direito não encontrado");
                }
            } else {
                id = getErros().size() + 1;
                str = lexemes.get(index).getValue();
                idLine = lexemes.get(index).getLine();
                idWord = lexemes.get(index).getColumn();
                error = " Erro na linha %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));
                // Pode ser erro de muita coisa
                System.out.println("Erro");
            }

        } else if (getLexemeValue(index).equals("IF")) {
            readLexeme();
            expr();
            if (getLexemeValue(index).equals("THEN")) {
                readLexeme();
                inst();
                if (getLexemeValue(index).equals("ELSE"))
                    readLexeme();
                inst();
            } else {
                id = getErros().size() + 1;
                str = lexemes.get(index).getValue();
                idLine = lexemes.get(index).getLine();
                idWord = lexemes.get(index).getColumn();
                error = " Erro, é esperado um then na linha %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                System.out.println("Erro, é esperado um then");
            }

        } else if (getLexemeValue(index).equals("WHILE")) {
            readLexeme();
            expr();
            if (getLexemeValue(index).equals("DO")) {
                readLexeme();
                inst();
            } else {
                id = getErros().size() + 1;
                str = lexemes.get(index).getValue();
                idLine = lexemes.get(index).getLine();
                idWord = lexemes.get(index).getColumn();
                error = " Erro, é esperado um DO no laço de repetição WHILE na linha %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                System.out.println("Erro, é esperado um DO no laço de repetição WHILE");
            }

        } else if (getLexemeValue(index).equals("REPEAT")) {
            readLexeme();
            inst();
            if (getLexemeValue(index).equals("UNTIL")) {
                readLexeme();
                expr();
                if (getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                    readLexeme();
                else {
                    id = getErros().size() + 1;
                    str = lexemes.get(index).getValue();
                    idLine = lexemes.get(index).getLine();
                    idWord = lexemes.get(index).getColumn();
                    error = " Falta ';' na linha %1$d, coluna %2$d";
                    getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                    System.out.println("Falta PONTO_VIRGULA");
                }
            } else {
                id = getErros().size() + 1;
                str = lexemes.get(index).getValue();
                idLine = lexemes.get(index).getLine();
                idWord = lexemes.get(index).getColumn();
                error = " Erro, é esperado um UNTIL no laço de repetição REPEAT na linha %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                System.out.println("Erro, é esperado um UNTIL no laço de repetição REPEAT");
            }

        } else if (getLexemeValue(index).equals("BREAK")) {
            readLexeme();
            if (getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                readLexeme();
            else {
                id = getErros().size() + 1;
                str = lexemes.get(index).getValue();
                idLine = lexemes.get(index).getLine();
                idWord = lexemes.get(index).getColumn();
                error = " Falta ';' na linha %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                System.out.println("Falta PONTO_VIRGULA");
            }

        } else if (getLexemeValue(index).equals("CONTINUE")) {
            readLexeme();
            if (getLexemeToken(index).equals(Token.PONTO_VIRGULA))
                readLexeme();
            else {
                id = getErros().size() + 1;
                str = lexemes.get(index).getValue();
                idLine = lexemes.get(index).getLine();
                idWord = lexemes.get(index).getColumn();
                error = " Falta ';' na linha %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                System.out.println("Falta PONTO_VIRGULA");
            }

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
        expr2();
    }

    private void expr2() {
        if (operador_logico.contains(getLexemeValue(index))) {
            exprComparacao();
            expr2();
        }
    }

    private void exprComparacao() {
        exprOp();
        exprComparacao2();
    }

    private void exprComparacao2() {
        if (operador_numerico.contains(getLexemeToken(index).toString())) {
            readLexeme();
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
        readLexeme();
        termo2();
    }

    private void exprOp2() {
        if (operador_mais_menos.contains(getLexemeToken(index).toString())) {
            readLexeme();
            termo();
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
        } else if (getLexemeToken(index).equals(Token.PARENTESE_ESQUERDO)) {
            readLexeme();
            expr();
            if (getLexemeToken(index).equals(Token.PARENTESE_DIREITO)) {

            } else {
                id = getErros().size() + 1;
                error = " Falta ';' na linha %1$d, coluna %2$d";
                getErros().add(new Erro(id, str, String.format(error, idLine, idWord), idLine, idWord));

                System.out.println("Falta PARENTESE_DIREITO");
            }
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

    private boolean literal() {
        if (getLexemeToken(index).equals(Token.STRING))
            return true;
        else
            return false;
    }
}
