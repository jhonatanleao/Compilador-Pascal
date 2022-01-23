package Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Lexeme;
import Model.LexicalAnalyzer;


public class LexicalController {
    public void analyze() throws IOException{
        Path fileName = Path.of("teste.txt");
        String content = Files.readString(fileName);
        String[] lines = content.split("\n");
        Map<Integer, String> code = new HashMap<>();

        for(int i = 0; i < lines.length; i++){
            code.put(i+1, lines[i]);
        }

        List<Lexeme> lexemes = new LexicalAnalyzer().codeAnalizer(code);
    
    }
}
