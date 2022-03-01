package atividade1;

import java.io.IOException;

public class postfix{
    public static void main(String[] args) throws IOException{
        Parser parse = new Parser();
        parse.expr();
        System.out.println("\n");
    }
}