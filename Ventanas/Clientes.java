package Ventanas;


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

    public Clientes() {
        initComponents();
        setExtendedState(this.MAXIMIZED_BOTH);
        setLocationRelativeTo(this);
    
        diseñoTabla(Tabla);
        
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/papeleria.png")).getImage());
        tabla("select * from clientes;", Tabla);
        PlaceHorlder();
        TextPrompt prueba = new TextPrompt("Escribe el nombre del cliente", txtBuscar);

    }

    void MostrarVentanaC() {
        //Mostrando la ventana correspondienrte de acuerdo  la barra 
        if (pocionActual == 0) {
            Texto1.setText("Registro de productos");
            BtnRegistro.setText("Registrar");
        } else if (pocionActual == 1) {
            Texto1.setText("Actualizar productos");
            BtnRegistro.setText("Actualizar");
        }

    }

    public void PlaceHorlder() {
        for (int i = 0; i < cajas.size(); i++) {

            TextPrompt prueba = new TextPrompt(place.get(i), cajas.get(i));

        }
        place.clear();
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

    public Object obtenerValor(JTextField caja) {
        return caja.getText();
    }
    //Generarion de Codigos de barras para los productos q no tienen codigo de barras

    /* 
 Para generar los codigos se toma en cuenta lo siguinte 
 el numero de letras del nombre del producto
 los numeros de la fecha actual y el numero de registros q tiene la tabla actualmente 
 mas un numero randon entre el 0-20
 ejemplo Escuadra Baco
 num de letras 12
 fecha actual 29/03/2020/11/1
 numero random 20
 numero de registros actuales es de 10
 entonces el codigo de barras quedaria asi 
 12290320202011110
     */
    public Object GenerarBarras(String nombre) {
        int numLetras = nombre.length();
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        int numero = (int) (Math.random() * 20);
        int registros = Conec.Select("Select * from productos;", 5).size();

        return numLetras + "" + año + "" + mes + "" + dia + "" + hora + "" + minuto + "" + segundo + "" + numero + "" + registros;

    }

    public void Actualizar() {
        llenarTextos();
        llenarcajas();
        //Primero meto tadas las cajas en una lista para con un for obtener sus datos y meterlos 
        //a la lista de productos

        ArrayList<Object> products = new ArrayList<>();
        //Tenemos q llenar la lsta con daros vasios por si el usuario no los introduce se pongas estos datos
        products.add("");
        products.add("");
        products.add(0);
        products.add(0);
        products.add(0);
        for (int i = 0; i < cajas.size(); i++) {
            if (obtenerValor(cajas.get(i)).equals("")) {
                if (i == 0) {
                    products.set(0, GenerarBarras(txtTelefono.getText()));
                }
            } else {
                products.set(i, cajas.get(i).getText());
            }
        }
        for (int i = 0; i < products.size(); i++) {
            cajas.get(i).setText("");
        }
        cajas.clear();

        Conec.update("update productos set NombreP='" + products.get(1)
                + "', PrecioUnitario =" + products.get(2)
                + ", PrecioMayoreo=" + products.get(3) + ", Unidades=" + products.get(4)
                + " where codigoBarras='" + products.get(0) + "';",
                "No se pudo actualizar los produtos");

        products.clear();

    }

    public void ObtenerProductos() {
        llenarTextos();
        llenarcajas();
        //Primero meto tadas las cajas en una lista para con un for obtener sus datos y meterlos 
        //a la lista de productos

        ArrayList<Object> products = new ArrayList<>();
        //Tenemos q llenar la lsta con daros vasios por si el usuario no los introduce se pongas estos datos
        products.add("");
        products.add("");
        products.add(0);
        products.add(0);
        products.add(0);
        for (int i = 0; i < cajas.size(); i++) {
            if (obtenerValor(cajas.get(i)).equals("")) {
                if (i == 0) {
                    products.set(0, GenerarBarras(txtTelefono.getText()));
                }
            } else {
                products.set(i, cajas.get(i).getText());
            }
        }
        for (int i = 0; i < products.size(); i++) {
            cajas.get(i).setText("");
        }
        cajas.clear();
        Conec.insert("insert into productos values (?,?,?,?,?);", products, "No se pudieron agregar los productos ");

        products.clear();

    }

    public void tabla(String sql, JTable Tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Correo");
       

        Tabla.setModel(modelo);
        setVisible(true);

        ArrayList<String> datos = Conec.Select(sql, 3);

        int j = 0;
        String fila[] = new String[3];
        for (int i = 0; i < datos.size();) {

            while (j < 3) {
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
        Label = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
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

        Label.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        Label.setForeground(new java.awt.Color(255, 255, 255));
        Label.setText("Registro clientes");
        Label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Label.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                LabelMouseMoved(evt);
            }
        });
        Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LabelMouseExited(evt);
            }
        });
        jPanel2.add(Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 180, 40));

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Actualizar clientes");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel5MouseMoved(evt);
            }
        });
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 50, 200, 40));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/carnet50.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, -1, 100));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar50.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, -1, 100));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/basura.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 20, -1, 100));

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Eliminar Clientes");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel4MouseMoved(evt);
            }
        });
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 50, 190, 40));

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
        PanelRegistro.add(Texto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 920, 60));

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
        PanelRegistro.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 300, 50));

        txtTelefono.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTelefono.setBorder(null);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        PanelRegistro.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 320, 60));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PanelRegistro.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 320, 30));

        jLabel14.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel14.setText("Correo");
        PanelRegistro.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, 50));

        txtCorrreo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCorrreo.setBorder(null);
        txtCorrreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorrreoKeyTyped(evt);
            }
        });
        PanelRegistro.add(txtCorrreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 320, 50));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PanelRegistro.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 320, 30));

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
        PanelRegistro.add(BtnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, 400, 50));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/llamada50.png"))); // NOI18N
        PanelRegistro.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, -1, 60));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nombre50.png"))); // NOI18N
        PanelRegistro.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, 60));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/gmail.png"))); // NOI18N
        PanelRegistro.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, 60));

        jLabel22.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Buscar");
        PanelRegistro.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 30, 80, 40));

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        PanelRegistro.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 430, 40));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PanelRegistro.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 70, 430, 30));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        PanelRegistro.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 20, 60, 60));

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

        PanelRegistro.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 100, 700, 600));

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

    private void LabelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelMouseMoved
        // TODO add your handling code here:
      
    }//GEN-LAST:event_LabelMouseMoved

    private void LabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelMouseExited
      
    }//GEN-LAST:event_LabelMouseExited

    private void LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelMouseClicked
   
            pocionActual = 0;
            
          //  Borrar.setVisible(false);
            PanelRegistro.setVisible(true);
            MostrarVentanaC();
       
    }//GEN-LAST:event_LabelMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int fila = Tabla.getSelectedRow();
        if (fila >= 0) {
            pocionActual = 1;
            MostrarVentanaC();
            llenarcajas();
            for (int i = 0; i < 5; i++) {

                cajas.get(i).setText(Tabla.getValueAt(fila, i) + "");
            }
            cajas.clear();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        int fila = Tabla.getSelectedRow();
        if (fila >= 0) {
            Conec.delete("delete from productos where codigoBarras='" + Tabla.getValueAt(fila, 0) + "';", "No se pudo eliminar el producto");
            tabla("select * from productos;", Tabla);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
      
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        
        pocionActual = 1;
           //Borrar.setVisible(false);
            PanelRegistro.setVisible(true);
        MostrarVentanaC();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseMoved
       
    }//GEN-LAST:event_jLabel5MouseMoved

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        tabla("select * from productos where codigoBarras='" + txtBuscar.getText() + "'  union select * from productos where NombreP like '%" + txtBuscar.getText() + "%';", Tabla);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void BtnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistroActionPerformed
      
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
        char mander = evt.getKeyChar();
        String ex = mander + "";
        if (ex.equalsIgnoreCase(".")) {
            if (!txtCorrreo.getText().contains(".")) {
                txtCorrreo.setText(txtCorrreo.getText() + ".");
            }

        }
        if (!Character.isDigit(mander)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCorrreoKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txtNombreClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            if (pocionActual == 1) {

                String sql = "select * from productos where codigoBarras='" + txtNombreCliente.getText() + "';";
                ArrayList<String> lista = Conec.Select(sql, 5);
                if (lista.size() != 0) {
                    llenarcajas();

                    for (int i = 1; i < lista.size(); i++) {
                        if (lista.get(i).length() != 0) {
                            cajas.get(i).setText(lista.get(i));
                        }
                    }
                    cajas.clear();
                }else{
                    JOptionPane.showMessageDialog(this, "No se encuentra ningun producto con ese codigo de barrras");
                }
            }
        }
    }//GEN-LAST:event_txtNombreClienteKeyPressed

    private void jLabel4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseMoved

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRegistro;
    private javax.swing.JLabel Label;
    private javax.swing.JPanel PanelRegistro;
    private javax.swing.JTable Tabla;
    private javax.swing.JLabel Texto1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
