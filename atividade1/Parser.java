package atividade1;

import java.io.*;

public class Parser{
    static int lookahead;

    public Parser() throws IOException {
        System.out.print("enter a expression: \t");
        lookahead = System.in.read();
    }

    void expr() throws IOException {
        term();

        while(true){
            if(lookahead == '+'){
                match('+');
                term();
                System.out.write('+');
            }else if(lookahead == '-'){
                match('-');
                term();
                System.out.write('-');
            }else if(lookahead == '*'){
                match('*');
                term();
                System.out.write('*');
            }else if(lookahead == '/'){
                match('/');
                term();
                System.out.write('/');
            }
            else return;
        }
    }

    void term() throws IOException{
        if(Character.isDigit((char) lookahead)){
            System.out.write((char) lookahead);
            match(lookahead);
        }
        else throw new Error("syntax error");
    }

    void match(int t) throws IOException{
        if(lookahead == t) lookahead = System.in.read();
        else throw new Error("syntax error");
    }

}              