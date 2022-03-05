package Model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorLineTable extends DefaultTableCellRenderer {
  public ColorLineTable() {
  };

  @Override
  public Component getTableCellRendererComponent(JTable table, Object object, boolean isSelected, boolean hasFocus,
      int row, int column) {
    JLabel label = (JLabel) super.getTableCellRendererComponent(table, object, isSelected, hasFocus, row, column);

    Color background = Color.WHITE;
    Object objeto = table.getValueAt(row, 1);

    try {
      String token = String.valueOf(objeto).toString();
      if (token.contains("INVALID")) {
        background = Color.RED;
      }
      if (!token.contains("INVALID")) {

        background = Color.BLACK;
      }
    } catch (Exception e) {
    }

    label.setForeground(background);
    return label;
  }
}
