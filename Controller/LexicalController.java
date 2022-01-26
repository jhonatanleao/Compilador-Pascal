package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import Model.Erro;
import Model.LexicalAnalyzer;
import View.PrincipalLittlePascal;

public class LexicalController {

    PrincipalLittlePascal view;
    LexicalAnalyzer lexicalAnalyzer;

    public LexicalController(){
        this.lexicalAnalyzer = new LexicalAnalyzer();
        this.view = new PrincipalLittlePascal();
        
        this.view.getBtnCompile().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                analyze();
                view.getTxaOutput().setText(update());
            }

        });

        this.view.getBtnClear().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                lexicalAnalyzer.clearLists();
                view.getTxaOutput().setText("");
                view.getTxaInput().setText("");
            }
        });
    }

    private Map<Integer, String> removeComments(String content){
        Map<Integer, String> code = new LinkedHashMap<>();
        String[] lines = content.split("\n");  
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

    private void analyze(){
        String code = this.view.getTxaInput().getText();
        this.lexicalAnalyzer.codeAnalizer(removeComments(code));
    }

    private String update(){
        String output = "";
        if (this.lexicalAnalyzer.getErrorList().size() > 0){
            for (Erro e : this.lexicalAnalyzer.getErrorList()) {
                output += "".concat(e.getType()).concat("\n");
                System.out.println(e.getType());
            }
        }
        
        return output;
    }

    public void init(){
        this.view.setVisible(true);
        this.view.setLocationRelativeTo(null);
    }
}
