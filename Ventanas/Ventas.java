package Ventanas;

import Principal.Conectar;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author omara
 */
public class Ventas extends javax.swing.JFrame implements Conectar {

    /**
     * Creates new form Ventas
     */
    ArrayList<String> codigo = new ArrayList<>();
    
    public Ventas() {
        initComponents();
        setLocationRelativeTo(null);

        /* codigo.add("1707");
        codigo.add("7501035113794");
        codigo.add("7506129400866");
       // Alertas l= new Alertas(codigo);
        //l.enviarMensaje();*/
        tabla("select * from productos;", Tabla1, new String[]{"Codigo barras",
            "Nombre producto", "Precio unitario","Precio Mayoreo", "Unidades"});
    }

    public void tabla(String sql, JTable Tabla, String[] columnas) {
        DefaultTableModel modelo = new DefaultTableModel();
        for (int i = 0; i < columnas.length; i++) {
            modelo.addColumn(columnas[i]);
        }

        Tabla.setModel(modelo);
        setVisible(true);

        ArrayList<String> datos = Conec.Select(sql, columnas.length);

        int j = 0;
        String fila[] = new String[columnas.length];
        for (int i = 0; i < datos.size();) {

            while (j < columnas.length) {
                fila[j] = datos.get(i);

                i++;
                j++;
            }
            modelo.addRow(fila);
            j = 0;

        }
        Tabla.setModel(modelo);
        datos.clear();

    }

    public Object getTexto(JTextField caja) {
        return caja.getText();
    }

    public void agregarCarrito() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtUnidades = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        txtCodigoB = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        BtnRegistro = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtCodigoBarra2 = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        JCPrecio = new javax.swing.JCheckBox();
        BtnAgregar = new javax.swing.JButton();
        marco = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUnidades.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtUnidades.setBorder(null);
        txtUnidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnidadesActionPerformed(evt);
            }
        });
        txtUnidades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnidadesKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnidadesKeyTyped(evt);
            }
        });
        jPanel1.add(txtUnidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 230, -1));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 230, 20));

        jLabel13.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Carrito de compras");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, 1070, -1));

        txtCodigoB.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCodigoB.setBorder(null);
        txtCodigoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoBActionPerformed(evt);
            }
        });
        txtCodigoB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoBKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodigoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 230, -1));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 230, 20));

        BtnRegistro.setBackground(new java.awt.Color(255, 102, 0));
        BtnRegistro.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BtnRegistro.setForeground(new java.awt.Color(255, 255, 255));
        BtnRegistro.setText("Realizar compra");
        BtnRegistro.setBorder(null);
        BtnRegistro.setBorderPainted(false);
        BtnRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnRegistro.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BtnRegistroMouseMoved(evt);
            }
        });
        BtnRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnRegistroMouseExited(evt);
            }
        });
        BtnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistroActionPerformed(evt);
            }
        });
        jPanel1.add(BtnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 660, 230, 40));

        jLabel14.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel14.setText("Código de Barras ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel15.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Agregar productos ");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 230, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        Tabla.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código Barras", "Nombre Producto", "Unidades", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla.setFocusable(false);
        Tabla.setIntercellSpacing(new java.awt.Dimension(0, 0));
        Tabla.setRowHeight(35);
        Tabla.setSelectionBackground(new java.awt.Color(255, 153, 0));
        Tabla.setShowVerticalLines(false);
        Tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Tabla);
        if (Tabla.getColumnModel().getColumnCount() > 0) {
            Tabla.getColumnModel().getColumn(0).setResizable(false);
            Tabla.getColumnModel().getColumn(1).setResizable(false);
            Tabla.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, 1080, 210));

        jLabel22.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Buscar");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 140, -1));

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 440, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 30, 130, -1));

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jCheckBox1.setText("Agregar Cliente");
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 180, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 440, 20));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        Tabla1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código Barras", "Nombre Producto", "Precio Unitario", "Precio Mayoreo", "Unidades"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla1.setFocusable(false);
        Tabla1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        Tabla1.setRowHeight(35);
        Tabla1.setSelectionBackground(new java.awt.Color(255, 153, 0));
        Tabla1.setShowVerticalLines(false);
        Tabla1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(Tabla1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 1090, 300));

        jLabel16.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel16.setText("Cantidad");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel17.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel17.setText("Importe: ");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        txtCodigoBarra2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCodigoBarra2.setBorder(null);
        txtCodigoBarra2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoBarra2ActionPerformed(evt);
            }
        });
        txtCodigoBarra2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoBarra2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBarra2KeyTyped(evt);
            }
        });
        jPanel1.add(txtCodigoBarra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 230, -1));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 230, 20));

        jLabel18.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel18.setText("Efectivo");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        JCPrecio.setBackground(new java.awt.Color(255, 255, 255));
        JCPrecio.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        JCPrecio.setText("Precio mayoreo");
        jPanel1.add(JCPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 180, -1));

        BtnAgregar.setBackground(new java.awt.Color(255, 102, 0));
        BtnAgregar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BtnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAgregar.setText("Agregar");
        BtnAgregar.setBorder(null);
        BtnAgregar.setBorderPainted(false);
        BtnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAgregar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BtnAgregarMouseMoved(evt);
            }
        });
        BtnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnAgregarMouseExited(evt);
            }
        });
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(BtnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 230, 40));

        marco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(marco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 350));

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 280, 340));

        jPanel2.setBackground(new java.awt.Color(238, 112, 82));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ventas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1217, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUnidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnidadesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnidadesActionPerformed

    private void txtUnidadesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadesKeyPressed

    }//GEN-LAST:event_txtUnidadesKeyPressed

    private void txtUnidadesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnidadesKeyTyped

    private void txtCodigoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBActionPerformed

    private void txtCodigoBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBKeyPressed

    private void txtCodigoBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBKeyTyped

    private void BtnRegistroMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRegistroMouseMoved
        // TODO add your handling code here:
        //[255,122,47]
        BtnRegistro.setBackground(new java.awt.Color(255, 122, 47));
    }//GEN-LAST:event_BtnRegistroMouseMoved

    private void BtnRegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRegistroMouseExited
        // TODO add your handling code here:
        BtnRegistro.setBackground(new java.awt.Color(255, 102, 0));
    }//GEN-LAST:event_BtnRegistroMouseExited

    private void BtnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistroActionPerformed

    }//GEN-LAST:event_BtnRegistroActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        tabla("select * from productos where codigoBarras='" + txtBuscar.getText() + "'  union select * from productos where NombreP like '%" + txtBuscar.getText() + "%';", Tabla1, new String[]{"Codigo barras",
            "Nombre producto", "Precio unitario","Precio Mayoreo", "Unidades"});
        // tabla("select * from productos where codigoBarras='" + txtBuscar.getText() + "'  union select * from productos where NombreP like '%" + txtBuscar.getText() + "%';", Tabla);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtCodigoBarra2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoBarra2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBarra2ActionPerformed

    private void txtCodigoBarra2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarra2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBarra2KeyPressed

    private void txtCodigoBarra2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarra2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBarra2KeyTyped

    private void BtnAgregarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAgregarMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAgregarMouseMoved

    private void BtnAgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAgregarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAgregarMouseExited

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        // TODO add your handling code here:
        // Aqui agremos los poductos a la tabla 
        if ((getTexto(txtCodigoB) + "").length() != 0 && (getTexto(txtUnidades) + "").length() != 0) {
            //Primero debemos optener el codigo de barras para buscar el precio
            
             ArrayList<String>datos;
             
            if(JCPrecio.isSelected()){
            datos=Conec.Select("select codigoBarras,NombreP,PrecioMayoreo from productos where codigoBarras='"+getTexto(txtCodigoB)+"';", 3);   
            }else{
               datos=Conec.Select("select codigoBarras,NombreP,PrecioUnitario from productos where codigoBarras='"+getTexto(txtCodigoB)+"';", 3);     
            }
            //convirtiendo la lista en un arreglo 
            String[]d=new String[datos.size()];
            for(int i=0; i<datos.size(); i++){
                d[i]=datos.get(i);
            }
            //metiendo los datos a la fila 
            for(int i=0; i<datos.size(); i++){
                System.out.println(d[i]);
            }
               
             
        } else {
            JOptionPane.showMessageDialog(null, "Debes  escribir todos los datos");
        }
    }//GEN-LAST:event_BtnAgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnRegistro;
    private javax.swing.JCheckBox JCPrecio;
    private javax.swing.JTable Tabla;
    private javax.swing.JTable Tabla1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel marco;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigoB;
    private javax.swing.JTextField txtCodigoBarra2;
    private javax.swing.JTextField txtUnidades;
    // End of variables declaration//GEN-END:variables
}
