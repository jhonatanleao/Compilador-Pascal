package Model;

import java.util.List;

public class Parser {
    // private List<Lexeme> lexemes;
    // private int currentId;

    // public Parser(List<Lexeme> lexemes) {
    //     this.lexemes = lexemes;
    //     this.currentId = 0;
    // }

    // private void getNext() {
    //     currentId++;
    // }

    // private Lexeme getLexeme() {
    //     return lexemes.get(currentId);
    // }

    // private String sintaxErrorAtLine() {
    //     return "Sintax error at line".concat(String.valueOf(getLexeme().getLine()));
    // }

    // private Integer programa() {
    //     if (getLexeme().getValue().equals("PROGRAM")) {
    //         getNext();
    //         if (getLexeme().getToken().name().equals("IDENTIFICADOR")) {
    //             getNext();
    //             if (getLexeme().getValue().equals("PONTO_VIRGULA")) {
    //                 getNext();
    //                 declaracoes();
    //                 getNext();
    //                 if (getLexeme().getValue().equals("BEGIN")) {
    //                     instrucoes();
    //                     getNext();
    //                     if (getLexeme().getValue().equals("END.")) {
    //                         return 0;
    //                     } else {
    //                         throw new Exception(sintaxErrorAtLine());
    //                     }
    //                 } else {
    //                     throw new Exception(sintaxErrorAtLine());
    //                 }
    //             } else {
    //                 throw new Exception(sintaxErrorAtLine());
    //             }
    //         } else {
    //             throw new Exception(sintaxErrorAtLine());
    //         }
    //     } else {
    //         throw new Exception(sintaxErrorAtLine());
    //     }
    // }

    // private void declaracoes(){
    //     declaracaoConstante();
    // }

    // private void declaracaoConstante(){
    //     if (getLexeme().getValue().equals("CONST")){
    //         delConstList();
    //     }
    // }

    // private
}
