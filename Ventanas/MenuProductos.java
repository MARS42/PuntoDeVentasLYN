
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
public class MenuProductos extends javax.swing.JFrame implements Conectar {

    ArrayList<JTextField> cajas = new ArrayList<>();
    ArrayList<String> place = new ArrayList();

    public MenuProductos() {
        initComponents();
        setExtendedState(this.MAXIMIZED_BOTH);
        setLocationRelativeTo(this);
        llenarTextos();
        llenarcajas();
        diseñoTabla(Tabla);
         setIconImage(new ImageIcon(getClass().getResource("/Imagenes/papeleria.png")).getImage()); 
        tabla("select * from productos;", Tabla);
        PlaceHorlder();
         TextPrompt prueba = new TextPrompt("Escribe el código o nombre del producto", txtBuscar);

    }

    public void PlaceHorlder() {
        for (int i = 0; i < cajas.size(); i++) {

            TextPrompt prueba = new TextPrompt(place.get(i), cajas.get(i));

        }
        place.clear();
    }

    public void llenarTextos() {
        place.add("Ingrese código B");
        place.add("Ej. Cartulina");
        place.add("EJ. 10");
        place.add("EJ. 9.50");
        place.add("EJ. 100");
    }

    public void diseñoTabla(JTable Tabla) {
        Tabla.getTableHeader().setFont(new Font("Segoe", Font.BOLD, 12));
        Tabla.getTableHeader().setOpaque(false);
        Tabla.getTableHeader().setBackground(new Color(255, 255, 255));
        Tabla.getTableHeader().setForeground(new Color(0, 0, 0));
       
    }

    public void llenarcajas() {
        cajas.add(txtCodigoBarra);
        cajas.add(txtNombreProducto);
        cajas.add(txtPrecioUnitarii);
        cajas.add(TxtPrecioMayoreo);
        cajas.add(txtUnidades);
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

    public void ObtenerProductos() {
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
                    products.set(0, GenerarBarras(txtNombreProducto.getText()));
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
        modelo.addColumn("Código Barras");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Precio Mayoreo");
        modelo.addColumn("Cantidad");

        Tabla.setModel(modelo);
        setVisible(true);

        ArrayList<String> datos = Conec.Select(sql, 5);

        int j = 0;
        String fila[] = new String[5];
        for (int i = 0; i < datos.size();) {

            while (j < 5) {
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        PanelRegistro = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCodigoBarra = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txtPrecioUnitarii = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        TxtPrecioMayoreo = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        txtUnidades = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        BtnRegistro = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();

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

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Inventario");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 40, 100, 40));

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Registro Productos");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel4MouseMoved(evt);
            }
        });
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 44, 180, 40));

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Actualizar productos");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, 200, 40));

        jLabel6.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Eliminar Productos");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 40, 200, 40));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inventario.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1520, 10, -1, 100));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pro.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, 100));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, -1, 100));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/basura.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 10, -1, 100));

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

        jLabel11.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Registro de productos");
        PanelRegistro.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 920, 60));

        jLabel12.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel12.setText("Código de Barras ");
        PanelRegistro.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 310, 50));

        txtCodigoBarra.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCodigoBarra.setBorder(null);
        PanelRegistro.add(txtCodigoBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 320, 50));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PanelRegistro.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 320, 30));

        jLabel13.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel13.setText("Nombre del producto ");
        PanelRegistro.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 300, 50));

        txtNombreProducto.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtNombreProducto.setBorder(null);
        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyTyped(evt);
            }
        });
        PanelRegistro.add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 320, 60));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PanelRegistro.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 320, 30));

        jLabel14.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel14.setText("Precio unitario");
        PanelRegistro.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, 50));

        txtPrecioUnitarii.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtPrecioUnitarii.setBorder(null);
        txtPrecioUnitarii.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioUnitariiKeyTyped(evt);
            }
        });
        PanelRegistro.add(txtPrecioUnitarii, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 320, 50));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PanelRegistro.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 320, 30));

        jLabel15.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel15.setText("Precio mayoreo");
        PanelRegistro.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, -1, 50));

        TxtPrecioMayoreo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtPrecioMayoreo.setBorder(null);
        TxtPrecioMayoreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtPrecioMayoreoKeyTyped(evt);
            }
        });
        PanelRegistro.add(TxtPrecioMayoreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, 320, 50));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PanelRegistro.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, 320, 30));

        jLabel16.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel16.setText("Unidades");
        PanelRegistro.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 460, 80, 50));

        txtUnidades.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtUnidades.setBorder(null);
        txtUnidades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnidadesKeyTyped(evt);
            }
        });
        PanelRegistro.add(txtUnidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, 320, 50));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PanelRegistro.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 550, 320, 30));

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
        PanelRegistro.add(BtnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 630, 400, 50));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pegamento.png"))); // NOI18N
        PanelRegistro.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, -1, 60));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rebaja.png"))); // NOI18N
        PanelRegistro.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 360, -1, 60));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/codigo-de-barras.png"))); // NOI18N
        PanelRegistro.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, 60));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/proveedor.png"))); // NOI18N
        PanelRegistro.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, -1, 60));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/precio.png"))); // NOI18N
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
            .addComponent(PanelRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jLabel4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseMoved
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jLabel4MouseMoved

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        // TODO add your handling code here:[238,112,82]
        
    }//GEN-LAST:event_jLabel4MouseExited

    private void txtNombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtNombreProductoKeyTyped

    private void txtPrecioUnitariiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioUnitariiKeyTyped
        // TODO add your handling code here:
        char mander = evt.getKeyChar();
        String ex = mander + "";
        if (ex.equalsIgnoreCase(".")) {
            if (!txtPrecioUnitarii.getText().contains(".")) {
                txtPrecioUnitarii.setText(txtPrecioUnitarii.getText() + ".");
            }

        }
        if (!Character.isDigit(mander)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioUnitariiKeyTyped

    private void TxtPrecioMayoreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPrecioMayoreoKeyTyped
        // TODO add your handling code here:
        char mander = evt.getKeyChar();
        String ex = mander + "";
        if (ex.equalsIgnoreCase(".")) {
            if (!TxtPrecioMayoreo.getText().contains(".")) {
                TxtPrecioMayoreo.setText(TxtPrecioMayoreo.getText() + ".");
            }

        }
        if (!Character.isDigit(mander)) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtPrecioMayoreoKeyTyped

    private void txtUnidadesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadesKeyTyped
        // TODO add your handling code here:
        char mander = evt.getKeyChar();
        String ex = mander + "";
        if (ex.equalsIgnoreCase(".")) {
            if (!txtUnidades.getText().contains(".")) {
                txtUnidades.setText(txtUnidades.getText() + ".");
            }

        }
        if (!Character.isDigit(mander)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtUnidadesKeyTyped

    private void BtnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistroActionPerformed
        // TODO add your handling code here:
        try {
            /* Preguntamos si los  datos como nombre son nulos esos daros no pueden ser nulos ya que todo
            producto tiene un nombre pero puede faltar su codigo de barras
             */
            if (txtNombreProducto.getText().length() == 0 || txtPrecioUnitarii.getText().length() == 0) {

                JOptionPane.showMessageDialog(this, "Debes ingresar el nombre del producto o el precio unitario");
            } else {
                ObtenerProductos();
                tabla("Select * from productos", Tabla);

            }

        } catch (Error e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_BtnRegistroActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        tabla("select * from productos where codigoBarras='" + txtBuscar.getText() + "'  union select * from productos where NombreP like '%" + txtBuscar.getText() + "%';", Tabla);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void BtnRegistroMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRegistroMouseMoved
        // TODO add your handling code here:
        //[255,122,47]
          BtnRegistro.setBackground(new java.awt.Color(255,122,47));
    }//GEN-LAST:event_BtnRegistroMouseMoved

    private void BtnRegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnRegistroMouseExited
        // TODO add your handling code here:
        BtnRegistro.setBackground(new java.awt.Color(255, 102, 0));
    }//GEN-LAST:event_BtnRegistroMouseExited



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRegistro;
    private javax.swing.JPanel PanelRegistro;
    private javax.swing.JTable Tabla;
    private javax.swing.JTextField TxtPrecioMayoreo;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigoBarra;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecioUnitarii;
    private javax.swing.JTextField txtUnidades;
    // End of variables declaration//GEN-END:variables
}
