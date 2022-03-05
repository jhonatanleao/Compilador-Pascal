package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import Model.ColorLineTable;
import Model.Erro;
import Model.Lexeme;
import Model.LexicalAnalyzer;
import Model.SintaticalAnalyzer;
import View.LittlePascalView;

public class LexicalController {

    LittlePascalView view;
    LexicalAnalyzer lexicalAnalyzer;
    DefaultTableModel tblLexemes;
    ColorLineTable colorLine;
    SintaticalAnalyzer sintaticalAnalyzer;

    public LexicalController() {
        this.lexicalAnalyzer = new LexicalAnalyzer();
        this.view = new LittlePascalView();
        this.tblLexemes = new DefaultTableModel();
        this.colorLine = new ColorLineTable();
        this.sintaticalAnalyzer = new SintaticalAnalyzer(lexicalAnalyzer.getLexemes());
        this.view.getBtnCompile().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analyze();
                sintaticalAnalyzer.programa();
                view.getTxaOutput().setText(update());
                
            }

        });

        this.view.getBtnClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lexicalAnalyzer.clearLists();
                view.getTxaOutput().setText("");
                view.getTxaInput().setText("");
                tblLexemes.removeRow(1);
                clearTable();
            }
        });
    }

    private Map<Integer, String> removeComments(String content) {
        Map<Integer, String> code = new LinkedHashMap<>();
        String[] lines = content.split("\n");
        boolean lock = true;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (!line.strip().startsWith("//") && lock && !line.strip().startsWith("(*")
                    && !line.strip().startsWith("{") && !line.isBlank())
                code.put(i + 1, line);
            if (line.strip().startsWith("{") || line.strip().startsWith("(*"))
                lock = false;
            if (line.strip().endsWith("}") || line.strip().endsWith("*)"))
                lock = true;
        }
        return code;
    }

    private void analyze() {
        String code = this.view.getTxaInput().getText();
        this.lexicalAnalyzer.codeAnalizer(removeComments(code));
    }

    private String update() {

        tblLexemes = (DefaultTableModel) view.getLexameTable().getModel();
        String output = "";
        if (this.lexicalAnalyzer.getErrorList().size() > 0) {
            for (Erro e : this.lexicalAnalyzer.getErrorList()) {
                output += "".concat(e.getType()).concat("\n");
            }
        }
        for (Lexeme l : this.lexicalAnalyzer.getLexemes()) {

            Object[] lineTable = { l.getValue(), l.getToken(), l.getLine(), l.getColumn() };

            tblLexemes.addRow(lineTable);
        }
        view.getLexameTable().setDefaultRenderer(Object.class, new ColorLineTable());
        return output;

    }

    private void clearTable() {
        while (tblLexemes.getRowCount() > 0) {
            tblLexemes.removeRow(0);
        }
    }

    public void init() {
        this.view.setVisible(true);
        this.view.setLocationRelativeTo(null);
        this.view.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
