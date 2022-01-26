import java.io.IOException;

import Controller.LexicalController;
import View.PrincipalLittlePascal;

public class Main {
    public static void main(String[] args) throws IOException {
        LexicalController controller = new LexicalController();
        controller.analyze();
        PrincipalLittlePascal view = new PrincipalLittlePascal();
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }
}
