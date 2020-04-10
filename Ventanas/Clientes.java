package Ventanas;

import Actores.ObtenerTextos;
import Actores.TextPrompt;

import Principal.Conectar;
import static Principal.Conectar.Conec;

import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author omara
 */
public class Clientes extends javax.swing.JFrame implements Conectar {

    ArrayList<JTextField> cajas = new ArrayList<>();
    ArrayList<String> place = new ArrayList();
    int pocionActual = 0;
    String id;

    public Clientes() {
        initComponents();
        setExtendedState(this.MAXIMIZED_BOTH);
        setLocationRelativeTo(this);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/papeleria.png")).getImage());
        Thread Formato = new Thread(new FormatoTabla());
        Thread hint = new Thread(new Hint());
        Formato.start();
        hint.start();
    }

    //Hilos para q la carga sea mas rapida
    //el hilo se encargara de dar diseño a la tabla 
    class FormatoTabla implements Runnable {

        @Override
        public void run() {
            diseñoTabla(Tabla);

            tabla("select * from clientes;", Tabla);
        }

    }

    //Hilo dos se encarga de cargar los place holder en las cajjas de texto
    class Hint implements Runnable {

        @Override
        public void run() {
            PlaceHorlder();
            TextPrompt prueba = new TextPrompt("Escribe el  nombre del cliente ", txtBuscar);
        }

    }

    public void PlaceHorlder() {
        llenarTextos();
        llenarcajas();
        for (int i = 0; i < cajas.size(); i++) {

            TextPrompt prueba = new TextPrompt(place.get(i), cajas.get(i));

        }
        place.clear();
        cajas.clear();

    }

    public void llenarTextos() {

        place.add("Nombre de la persona");
        place.add("Telefono celular");
        place.add("Correo electronico");
    }

    public void diseñoTabla(JTable Tabla) {
        Tabla.getTableHeader().setFont(new Font("Segoe", Font.BOLD, 12));
        Tabla.getTableHeader().setOpaque(false);
        Tabla.getTableHeader().setBackground(new Color(255, 255, 255));
        Tabla.getTableHeader().setForeground(new Color(0, 0, 0));

    }

    public void llenarcajas() {
        cajas.add(txtNombreCliente);
        cajas.add(txtTelefono);
        cajas.add(txtCorrreo);

    }

    public void Actualizar() {

        llenarcajas();
        //Primero meto tadas las cajas en una lista para con un for obtener sus datos y meterlos 
        //a la lista de productos
        ObtenerTextos tex = new ObtenerTextos();
        for (int i = 0; i < cajas.size(); i++) {
            tex.add(cajas.get(i).getText());
        }

        String sql = "update clientes set nombre='" + tex.datos.get(0)
                + "', telefono='" + tex.datos.get(1)
                + "', correo='" + tex.datos.get(2) + "' where id_cliente=" + id + ";";
      
         Conec.update(sql,"No se pudieron actilizar los datos");

        for (int i = 0; i < cajas.size(); i++) {
            cajas.get(i).setText("");
        }
        tex.datos.clear();
        cajas.clear();
    }

    public void ObtenerProductos() {

        llenarcajas();
        //Primero meto tadas las cajas en una lista para con un for obtener sus datos y meterlos 
        //a la lista de productos
        ObtenerTextos tex = new ObtenerTextos();
        for (int i = 0; i < cajas.size(); i++) {
            tex.add(cajas.get(i).getText());
        }

        for (int i = 0; i < cajas.size(); i++) {
            cajas.get(i).setText("");
        }
        cajas.clear();
        Conec.insert("insert into clientes (nombre,telefono,correo) values (?,?,?);", tex.datos, "No se pudieron agregar los clientes ");
        tex.datos.clear();

    }

    public void tabla(String sql, JTable Tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("#");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Correo");

        Tabla.setModel(modelo);
        setVisible(true);

        ArrayList<String> datos = Conec.Select(sql, 4);

        int j = 0;
        String fila[] = new String[4];
        for (int i = 0; i < datos.size();) {

            while (j < 4) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        PanelRegistro = new javax.swing.JPanel();
        Texto1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txtCorrreo = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        BtnRegistro = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();

        jPopupMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jPopupMenu1.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N

        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jMenuItem1.setText("Actualizar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem2.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jMenuItem2.setText("Eliminar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(238, 112, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/papeleria (1).png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, -4, -1, 140));

        jLabel2.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Papelería L y N");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 225, 31));

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Administración de los clientes ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, -1, -1));

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jInternalFrame1.setBackground(new java.awt.Color(255, 255, 255));
        jInternalFrame1.setBorder(null);
        jInternalFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jInternalFrame1.setResizable(true);
        jInternalFrame1.setFocusCycleRoot(false);
        jInternalFrame1.setFrameIcon(null);
        jInternalFrame1.setVisible(true);

        PanelRegistro.setBackground(new java.awt.Color(255, 255, 255));
        PanelRegistro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Texto1.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        Texto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Texto1.setText("Registro de Clientes");
        PanelRegistro.add(Texto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 550, 60));

        jLabel12.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel12.setText("Nombre del Cliente");
        PanelRegistro.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 310, 50));

        txtNombreCliente.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtNombreCliente.setBorder(null);
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });
        PanelRegistro.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 320, 50));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PanelRegistro.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 320, 30));

        jLabel13.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel13.setText("Telefono");
        PanelRegistro.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 300, 50));

        txtTelefono.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTelefono.setBorder(null);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        PanelRegistro.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 320, 60));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PanelRegistro.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 320, 30));

        jLabel14.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel14.setText("Correo");
        PanelRegistro.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, -1, 50));

        txtCorrreo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCorrreo.setBorder(null);
        txtCorrreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorrreoKeyTyped(evt);
            }
        });
        PanelRegistro.add(txtCorrreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 320, 50));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PanelRegistro.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, 320, 30));

        BtnRegistro.setBackground(new java.awt.Color(255, 102, 0));
        BtnRegistro.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BtnRegistro.setForeground(new java.awt.Color(255, 255, 255));
        BtnRegistro.setText("Registrar");
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
        PanelRegistro.add(BtnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 630, 290, 50));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/llamada50.png"))); // NOI18N
        PanelRegistro.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, 60));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nombre50.png"))); // NOI18N
        PanelRegistro.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, 60));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/gmail.png"))); // NOI18N
        PanelRegistro.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, 60));

        jLabel22.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Buscar");
        PanelRegistro.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 80, 40));

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        PanelRegistro.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, 430, 40));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PanelRegistro.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 60, 430, 30));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        PanelRegistro.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 10, 60, 60));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        Tabla.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Correo", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla.setComponentPopupMenu(jPopupMenu1);
        Tabla.setFocusable(false);
        Tabla.setIntercellSpacing(new java.awt.Dimension(0, 0));
        Tabla.setRowHeight(35);
        Tabla.setSelectionBackground(new java.awt.Color(255, 153, 0));
        Tabla.setShowVerticalLines(false);
        Tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Tabla);

        PanelRegistro.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 1130, 600));

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 1756, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int fila = Tabla.getSelectedRow();
        if (fila >= 0) {
            id = Tabla.getValueAt(fila, 0) + "";
            pocionActual = 1;

            llenarcajas();
            for (int i = 0; i < 3; i++) {

                cajas.get(i).setText(Tabla.getValueAt(fila, i + 1) + "");
            }
            cajas.clear();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        int fila = Tabla.getSelectedRow();
        if (fila >= 0) {
            Conec.delete("delete from clientes where id_cliente='" + Tabla.getValueAt(fila, 0) + "';", "No se pudo eliminar el producto");
            tabla("select * from clientes;", Tabla);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        tabla("select * from clientes where  nombre like '%"+txtBuscar.getText()+"%';", Tabla);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void BtnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistroActionPerformed
        if (pocionActual == 0) {
            if (txtNombreCliente.getText().length() != 0) {

                ObtenerProductos();
                tabla("select * from clientes;", Tabla);
            } else {
                JOptionPane.showMessageDialog(this, "Debes ingresar el nombre del cliente");
            }
        }
        if (pocionActual == 1) {
            Actualizar();
            tabla("select * from clientes;", Tabla);
        }
    }//GEN-LAST:event_BtnRegistroActionPerformed

    private void BtnRegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRegistroMouseExited
        // TODO add your handling code here:
        BtnRegistro.setBackground(new java.awt.Color(255, 102, 0));
    }//GEN-LAST:event_BtnRegistroMouseExited

    private void BtnRegistroMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRegistroMouseMoved
        // TODO add your handling code here:
        //[255,122,47]
        BtnRegistro.setBackground(new java.awt.Color(255, 122, 47));
    }//GEN-LAST:event_BtnRegistroMouseMoved

    private void txtCorrreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorrreoKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCorrreoKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txtNombreClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            if (pocionActual == 1) {

                String sql = "select * from clientes where nombre='" + txtNombreCliente.getText() + "';";
                ArrayList<String> lista = Conec.Select(sql, 4);
                if (lista.size() != 0) {
                    llenarcajas();

                    for (int i = 1; i < lista.size(); i++) {
                        if (lista.get(i).length() != 0) {
                            cajas.get(i).setText(lista.get(i));
                        }
                    }
                    cajas.clear();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encuentra ningun cliente con ese nombre");
                }
            }
        }
    }//GEN-LAST:event_txtNombreClienteKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRegistro;
    private javax.swing.JPanel PanelRegistro;
    private javax.swing.JTable Tabla;
    private javax.swing.JLabel Texto1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCorrreo;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
