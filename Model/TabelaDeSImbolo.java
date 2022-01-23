package trabalho_01.Model;
import java.util.ArrayList;
import trabalho_01.Model.Token;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

class SymbolTable {

    private ArrayList<Token> symbolList;

    public SymbolTable() throws Exception {
        symbolList = new ArrayList<Token>();
        insertKeywordsFromFile("keywords");
    }

    public void free(ArrayList<Token> symbolList) {
        symbolList.clear();
    }

    public int lookup(String lexeme) {
        int id = -1;

        for (Token t : this.symbolList) {
            if (t.getLexeme().equals(lexeme)) {
                id = symbolList.indexOf(t);
                break;
            }
        }
        if (id == -1) {
            return -1; // Undeclared name
        } else if (id <= 46 && id >= 0) {
            return -2; // palavra reservada
        } else {
            return id;
        }

    }

    public void insert(String lexeme, String token, String category) throws Exception {
        int id = lookup(lexeme);
        if (id == -1) {
            this.symbolList.add(new Token(lexeme, token, category));
        } else if (id == -2) {
            throw new Exception("Reserved Keyword");
        } else {
            throw new Exception("Multiple defined names");
        }
    }

    public void setCategory(String lexeme, String category) throws Exception {
        int id = lookup(lexeme);
        if (id == -1) {
            throw new Exception("Undeclared name");
        } else if (id == -2) {
            throw new Exception("Can not modify keyword");
        } else {
            Token token = this.symbolList.get(id);
            this.symbolList.set(id, token);
        }
    }

    public String getAttribute(String lexeme) throws Exception {
        int id = lookup(lexeme);
        if (id == -1) {
            throw new Exception("Can not get undeclared name");
        } else {
            Token token = this.symbolList.get(id);
            return token.getCategory();
        }
    }

    public void insertKeywordsFromFile(String filePath) throws Exception {
        File fileObj = new File(filePath);
        Scanner reader = new Scanner(fileObj);
        String data = "";
        String[] parts;
        while (reader.hasNextLine()) {
            data = reader.nextLine();
            parts = data.split(" ");
            insert(parts[0], parts[1], parts[2]);
        }
    }
}