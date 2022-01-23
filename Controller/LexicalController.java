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

    public Map removeComments() throws IOException {
        Path fileName = Path.of("teste.txt");
        String content = Files.readString(fileName);
        String[] lines = content.split("\n");      
        Map<Integer, String> code = new HashMap<>();
        boolean lock = true;

        for(int i = 0; i < lines.length; i++){
            String line = lines[i];
            if(!line.strip().startsWith("//") && lock && !line.strip().startsWith("(*") && !line.strip().startsWith("{") && !line.isBlank())
                code.put(i+1, line);
            if(line.strip().startsWith("{") || line.strip().startsWith("(*"))
                lock = false;
            if(line.strip().endsWith("}") || line.strip().endsWith("*)"))
                lock = true;                                                                    
        }
        return code;
    }

    public void analyze() throws IOException{

          
        Map<Integer, String> code = new HashMap<>();
        code = removeComments();
        LexicalAnalyzer lexicalAnalyzer = new  LexicalAnalyzer();
        List<Lexeme> lexemes = lexicalAnalyzer.codeAnalizer(code);
        System.out.println(lexicalAnalyzer.codeParser(lexemes));
    
    }
}
