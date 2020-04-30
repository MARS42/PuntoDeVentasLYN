package Ventanas;

import Actores.CodigoBarras;
import Actores.GenerarFecha;
import Actores.Producto;
import Actores.User;
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
    // ArrayList<String> codigo = new ArrayList<>();
    ArrayList<Producto> pro = new ArrayList<>();
    double importe = 0;
    int fila;
    ArrayList<Double> precios = new ArrayList<>();

    public Ventas() {
        initComponents();
        setLocationRelativeTo(null);

        /* codigo.add("1707");
        codigo.add("7501035113794");
        codigo.add("7506129400866");
       // Alertas l= new Alertas(codigo);
        //l.enviarMensaje();*/
        tabla("select * from productos;", Tabla1, new String[]{"Codigo barras",
            "Nombre producto", "Precio unitario", "Precio Mayoreo", "Unidades"});
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

    public void agregarCarrito(String[] columnas) {
        DefaultTableModel modelo = new DefaultTableModel();
        for (int i = 0; i < columnas.length; i++) {
            modelo.addColumn(columnas[i]);
        }

        for (int i = 0; i < pro.size(); i++) {
            modelo.addRow(new Object[]{pro.get(i).codigoBarras, pro.get(i).NombreP, pro.get(i).unidades, pro.get(i).importe});
        }
        Tabla.setModel(modelo);
    }

    public void calcularNuevoImporte() {
        importe = 0;
        for (int i = 0; i < pro.size(); i++) {
            importe += pro.get(i).importe;
        }
        JLImporte.setText("Importe " + importe);
    }

    public void comprar() {
        ArrayList<Object> da = new ArrayList<>();
        da.add(User.usuario);
        da.add(importe);
        da.add(new GenerarFecha().getFecha());

        Conec.insert("insert into ventas (usuario,importe,fecha) values (?,?,?);", da, "No se puedo registrar la compra");
        da.clear();
        RegistarCarrito();
        precios.clear();
        pro.clear();
    }

    public void RegistarCarrito() {
        //Tenemos q crear una lista con solo los campos de vamos a utilzar
        ArrayList<Object> objetos = new ArrayList<>();
        int id=Conec.ultimo("select  max(id_venta) from ventas;");
        for(int i=0; i<pro.size(); i++){
            objetos.add(pro.get(i).codigoBarras);
            objetos.add(pro.get(i).unidades);
            //consulta para saber cual fue el utlimo registo de la base de datos 
            objetos.add(id);
            System.out.println(objetos.get(0));
            System.out.println(objetos.get(1));
            System.out.println(objetos.get(2));
             //Conec.insert("insert into carrito (id_producto,unidades,id_venta) values (?,?,?);", objetos, "Nose puede registar");
             objetos.clear();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        Menu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        Dialogo = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        DialogoEditar = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        TxtUnidades2 = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        BotonModificar = new javax.swing.JButton();
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
        JcCliente = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        JLImporte = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        JCPrecio = new javax.swing.JCheckBox();
        BtnAgregar = new javax.swing.JButton();
        marco = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jPopupMenu1.setBackground(new java.awt.Color(255, 255, 255));

        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jMenuItem1.setText("Agregar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        Menu2.setBackground(new java.awt.Color(255, 255, 255));

        jMenuItem2.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem2.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jMenuItem2.setText("Eliminar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Menu2.add(jMenuItem2);

        jMenuItem3.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jMenuItem3.setText("Editar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        Menu2.add(jMenuItem3);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel3.setText("¿Enserio Quieres Eliminar el producto?");

        jButton1.setBackground(new java.awt.Color(255, 51, 0));
        jButton1.setFont(new java.awt.Font("Corbel", 0, 26)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("NO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 102, 0));
        jButton2.setFont(new java.awt.Font("Corbel", 0, 26)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("SI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel3)))
                .addContainerGap(93, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(72, 72, 72)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(329, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(167, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(89, 89, 89)))
        );

        javax.swing.GroupLayout DialogoLayout = new javax.swing.GroupLayout(Dialogo.getContentPane());
        Dialogo.getContentPane().setLayout(DialogoLayout);
        DialogoLayout.setHorizontalGroup(
            DialogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DialogoLayout.setVerticalGroup(
            DialogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel17.setText("Unidades");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        TxtUnidades2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtUnidades2.setBorder(null);
        TxtUnidades2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtUnidades2ActionPerformed(evt);
            }
        });
        TxtUnidades2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtUnidades2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtUnidades2KeyTyped(evt);
            }
        });
        jPanel4.add(TxtUnidades2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 230, -1));

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 230, 20));

        BotonModificar.setBackground(new java.awt.Color(255, 102, 0));
        BotonModificar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BotonModificar.setForeground(new java.awt.Color(255, 255, 255));
        BotonModificar.setText("Modificar");
        BotonModificar.setBorder(null);
        BotonModificar.setBorderPainted(false);
        BotonModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonModificar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BotonModificarMouseMoved(evt);
            }
        });
        BotonModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonModificarMouseExited(evt);
            }
        });
        BotonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonModificarActionPerformed(evt);
            }
        });
        jPanel4.add(BotonModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 230, 40));

        javax.swing.GroupLayout DialogoEditarLayout = new javax.swing.GroupLayout(DialogoEditar.getContentPane());
        DialogoEditar.getContentPane().setLayout(DialogoEditarLayout);
        DialogoEditarLayout.setHorizontalGroup(
            DialogoEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
        );
        DialogoEditarLayout.setVerticalGroup(
            DialogoEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
        );

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

            },
            new String [] {

            }
        ));
        Tabla.setComponentPopupMenu(Menu2);
        Tabla.setFocusable(false);
        Tabla.setIntercellSpacing(new java.awt.Dimension(0, 0));
        Tabla.setRowHeight(35);
        Tabla.setSelectionBackground(new java.awt.Color(255, 153, 0));
        Tabla.setShowVerticalLines(false);
        Tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Tabla);

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

        JcCliente.setBackground(new java.awt.Color(255, 255, 255));
        JcCliente.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        JcCliente.setText("Agregar Cliente");
        jPanel1.add(JcCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 180, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 440, 20));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        Tabla1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Tabla1.setComponentPopupMenu(jPopupMenu1);
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

        JLImporte.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        JLImporte.setText("Importe: ");
        jPanel1.add(JLImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 240, -1));

        txtEfectivo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtEfectivo.setBorder(null);
        txtEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEfectivoActionPerformed(evt);
            }
        });
        txtEfectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEfectivoKeyTyped(evt);
            }
        });
        jPanel1.add(txtEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 230, -1));

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
        if (JcCliente.isSelected()) {

        } else {//Varificnado q el prewcio este puesto
            if (txtEfectivo.getText().length() != 0) {
                //Varificando q el efectivo sea mayor a la cantidad ingresada
                double efectivo = 0;
                try {
                    efectivo = Double.parseDouble(txtEfectivo.getText());
                    if (importe > efectivo) {
                        JOptionPane.showMessageDialog(this, "El monto a pagar es mayor");
                    } else {
                        comprar();
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes de ingresar el efectivo");
            }

        }
    }//GEN-LAST:event_BtnRegistroActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        tabla("select * from productos where codigoBarras='" + txtBuscar.getText() + "'  union select * from productos where NombreP like '%" + txtBuscar.getText() + "%';", Tabla1, new String[]{"Codigo barras",
            "Nombre producto", "Precio unitario", "Precio Mayoreo", "Unidades"});
        // tabla("select * from productos where codigoBarras='" + txtBuscar.getText() + "'  union select * from productos where NombreP like '%" + txtBuscar.getText() + "%';", Tabla);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEfectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEfectivoActionPerformed

    private void txtEfectivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEfectivoKeyPressed

    private void txtEfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoKeyTyped
        // TODO add your handling code here:
        char mander = evt.getKeyChar();
        String ex = mander + "";
        if (ex.equalsIgnoreCase(".")) {
            if (!txtEfectivo.getText().contains(".")) {
                txtEfectivo.setText(txtEfectivo.getText() + ".");
            }

        }
        if (!Character.isDigit(mander)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtEfectivoKeyTyped

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

            ArrayList<String> datos;

            if (JCPrecio.isSelected()) {
                datos = Conec.Select("select NombreP,PrecioMayoreo from productos where codigoBarras='" + getTexto(txtCodigoB) + "';", 2);
            } else {
                datos = Conec.Select("select NombreP,PrecioUnitario from productos where codigoBarras='" + getTexto(txtCodigoB) + "';", 2);
            }
            //Agreando el precio en una lista aparte
            precios.add(Double.parseDouble(datos.get(1)));
            //Agreando el producto en otra lista para operar con el
            pro.add(new Producto(getTexto(txtCodigoB) + "", datos.get(0),//codigo barras
                    Double.parseDouble(txtUnidades.getText()),//Nombre
                    (Double.parseDouble(datos.get(1)) * Double.parseDouble(getTexto(txtUnidades) + ""))));//precio por unidad
            agregarCarrito(new String[]{"Codigo barras", "Nombre producto", "Unidades", "Importe"});
            //Calculando importe
            importe += Double.parseDouble(datos.get(1)) * Double.parseDouble(getTexto(txtUnidades) + "");
            JLImporte.setText("Importe: " + importe);
            //Borrando
            txtCodigoB.setText("");
            txtUnidades.setText("");
            datos.clear();

        } else {
            JOptionPane.showMessageDialog(null, "Debes  escribir todos los datos");
        }
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int fila = Tabla1.getSelectedRow();
        if (fila >= 0) {
            System.out.println("Enviando");
            txtCodigoB.setText(Tabla1.getValueAt(fila, 0) + "");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        pro.remove(fila);
        precios.remove(fila);
        calcularNuevoImporte();
        agregarCarrito(new String[]{"Codigo barras", "Nombre producto", "Unidades", "Importe"});
        Dialogo.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Dialogo.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        fila = Tabla.getSelectedRow();
        Dialogo.setSize(532, 268);
        Dialogo.setLocationRelativeTo(this);
        Dialogo.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void TxtUnidades2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtUnidades2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtUnidades2ActionPerformed

    private void TxtUnidades2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtUnidades2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtUnidades2KeyPressed

    private void TxtUnidades2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtUnidades2KeyTyped
        // TODO add your handling code here:
        char mander = evt.getKeyChar();
        String ex = mander + "";
        if (ex.equalsIgnoreCase(".")) {
            if (!TxtUnidades2.getText().contains(".")) {
                TxtUnidades2.setText(TxtUnidades2.getText() + ".");
            }

        }
        if (!Character.isDigit(mander)) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtUnidades2KeyTyped

    private void BotonModificarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonModificarMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonModificarMouseMoved

    private void BotonModificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonModificarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonModificarMouseExited

    private void BotonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonModificarActionPerformed
        // TODO add your handling code here:
        if (TxtUnidades2.getText().length() != 0) {
            pro.get(fila).unidades = Double.parseDouble(TxtUnidades2.getText());
            pro.get(fila).importe = pro.get(fila).unidades * precios.get(fila);
            agregarCarrito(new String[]{"Código barras", "Nombre producto", "Unidades", "Importe"});
            calcularNuevoImporte();
            DialogoEditar.setVisible(false);
            TxtUnidades2.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Debes de llenar los campos antes de actualizarlos");
        }
    }//GEN-LAST:event_BotonModificarActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        fila = Tabla.getSelectedRow();
        TxtUnidades2.setText(Tabla.getValueAt(fila, 2) + "");
        DialogoEditar.setSize(313, 355);
        DialogoEditar.setLocationRelativeTo(this);
        DialogoEditar.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonModificar;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnRegistro;
    private javax.swing.JDialog Dialogo;
    private javax.swing.JDialog DialogoEditar;
    private javax.swing.JCheckBox JCPrecio;
    private javax.swing.JLabel JLImporte;
    private javax.swing.JCheckBox JcCliente;
    private javax.swing.JPopupMenu Menu2;
    private javax.swing.JTable Tabla;
    private javax.swing.JTable Tabla1;
    private javax.swing.JTextField TxtUnidades2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel marco;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigoB;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtUnidades;
    // End of variables declaration//GEN-END:variables
}
