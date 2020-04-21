package Ventanas;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ColorearFilas extends DefaultTableCellRenderer {

    private final int columna_patron;

    public ColorearFilas(int Colpatron) {
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        Font font = new Font("Courier", Font.BOLD, 16);
       int valor= Integer.parseInt(table.getValueAt(row, columna_patron).toString());
       if(valor<10){
           setBackground(Color.RED);
            setForeground(Color.BLACK);
                setFont(font);
       }
       else if(valor>=10){
              setBackground(Color.WHITE);
           setForeground(Color.BLACK);
                setFont(font);
       }
     
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        return this;
    }

}
