package Model;

import java.util.List;

public class SintaticalAnalyzer {
    
    private List<Lexeme> lexemes;
    private String tipo;
    private Integer index;

    public SintaticalAnalyzer(List<Lexeme> lexemes){
        this.lexemes = lexemes;
        this.index = 0;
        this.tipo = 'INTEGERREALCHARSTRING';
    }

    private void readLexeme(){
        if (index < lexemes.size()){
            index++;
        }
    }

    private void programa(){     
       // Lexeme lexeme = new Lexeme()
       // boolean state = false;
       // if(lexemes.get(index).getValue().equals('PROGRAM')){
       //     readLexeme();
       //     if(lexemes.get(index).getToken().equals('IDENTIFICADOR')){
       //         readLexeme();
       //         if((lexemes.get(index).getToken().equals('PONTO_VIRGULA')){
       //             readLexeme();
       //             declaracoes();
       //             if((lexemes.get(index).getValue().equals('BEGIN')){
       //                 instrucoes();
       //                 if((lexemes.get(index).getValue().equals('END.')){
       //                     System.out.println('FIM');        
       //                 } 
       //         }else{
       //             System.out.println('Erro: Está faltando identificador PONTO_VIRGULA');
       //         }
       //     }else{
       //         System.out.println('Erro: IDENTIFICADOR iválido.');
       //     }
       // }else{
       //     System.out.println('Erro: Está faltando identificador PROGRAM.');
       // }
    }

    private void declaracoes(){
       // declaracaoVariavel();
       // declaracaoConstante();
       // declaracaoProcedimento();

    }

    private void declaracaoConstante(){
       // if(lexemes.get(index).getValue().equals('CONST')){
       //     readLexeme();
       //     declConstList(); 
       // }
    }

    private void declConstList(){
       //  if(lexemes.get(index).getToken().equals('IDENTIFICADOR')){
       //     readLexeme();
       //     if(lexemes.get(index).getValue().equals('DOIS_PONTOS')){
       //         readLexeme();
       //         if(tipo.contains(lexemes.get(index).getValue())){
       //             readLexeme();
       //             if(lexemes.get(index).getValue().equals('IGUALDADE')){
       //                 readLexeme();
       //                 valor();
       //                 
       //             } 
       //         } 
       //     }
       // }
    }
    
    private void declaracaoVariavel(){

    }
    
    private void declVarList(){

    }
    
    private void delcVar(){

    }

    private void conjuntoIds(){

    }
    
    private void valor(){
       // if(!lexemes.get(index).getToken().equals('STRING') && !lexemes.get(index).getToken().equals('INVALID_CARACTERE'))){
       //    unario();
       // }
    }
    
    private void declaracaoProcedimento(){
        
    }

    private void declProc(){

    }

    private void parametros(){

    }

    private void instrucoes(){

    }

    private void inst(){

    }

    private void parametros2(){

    }

    private void expr(){

    }
   
    private void expr2(){

    }

    private void exprComparacao(){

    }
    
    private void exprComparacao2(){

    }
    
    private void exprOp(){
        
    }

    private void termo(){
        
    }

    private void exprOp2(){

    }

    private void termo2(){

    }

    private void unario(){
       // readLexeme();
       // if('MAISMENOS'.lexemes.get(index).getToken()){
       //     fator();
       // }else{
            fator();
        }
    }

    private void fator(){
       // if(!tipo.contains(lexemes.get(index).getValue()) && !lexemes.get(index).getToken().equals('INVALID_CARACTERE')))){
       //    variavel(); 
       // }
    }

    private void variavel(){
        
    }


