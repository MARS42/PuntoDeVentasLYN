package Ventanas;

import Actores.Calendario;
import Actores.GenerarFecha;

import Actores.Producto;
import Actores.TextPrompt;
import Actores.Ticket;
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
    boolean ticket = false;
    double cambio = 0;
    String items = "";

    public Ventas() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(this.MAXIMIZED_BOTH);
        /* codigo.add("1707");
        codigo.add("7501035113794");
        codigo.add("7506129400866");
       // Alertas l= new Alertas(codigo);
        //l.enviarMensaje();*/
      //  TextPrompt prueba = new TextPrompt("Escribe la fecha que deseas buscar ej. 2020-05-16", txtfecha);
        tabla("select * from productos;", Tabla1, new String[]{"Código barras",
            "Nombre producto", "Precio unitario", "Precio Mayoreo", "Unidades"});
    }

    public void reiniciar() {
        Reporte.setVisible(false);
        if (pro.size() != 0) {
            pro.clear();

        }
        importe = 0;
        fila = 0;
        if (precios.size() != 0) {
            precios.clear();
        }
        cambio = 0;
        items = "";
        JLImporte.setText("");
        txtEfectivo.setText("");
        txtDescuento.setText("");
        JCPrecio.setSelected(false);
        JcCliente.setSelected(false);
        DefaultTableModel modelo = new DefaultTableModel();
        Tabla.setModel(modelo);
        tabla("select * from productos;", Tabla1, new String[]{"Código barras",
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
        actualizar();
        RegistarCarrito();

    }

    public void comprar2() {
        ArrayList<Object> da = new ArrayList<>();
        da.add(User.usuario);
        da.add(importe);
        da.add(new GenerarFecha().getFecha());
        da.add(id_Clinte);
        Conec.insert("insert into ventas (usuario,importe,fecha,id_cliente) values (?,?,?,?);", da, "No se puedo registrar la compra");
        da.clear();
        actualizar();
        RegistarCarrito();

    }
    int id;

    public void RegistarCarrito() {
        //Tenemos q crear una lista con solo los campos de vamos a utilzar

        ArrayList<Object> objetos = new ArrayList<>();
        ArrayList<Object> venta = Conec.Select("select id_venta from ventas where fecha='" + new GenerarFecha().getFecha() + "';", 1);
        id = Integer.parseInt(venta.get(venta.size() - 1) + "");
        venta.clear();
        for (int i = 0; i < pro.size(); i++) {
            objetos.add(pro.get(i).codigoBarras);
            objetos.add(pro.get(i).unidades);
            //consulta para saber cual fue el utlimo registo de la base de datos 
            objetos.add(id);
            items += pro.get(i).NombreP + "(" + pro.get(i).unidades + ") " + pro.get(i).importe + "\n";

            Conec.insert("insert into carrito (id_producto,unidades,id_venta) values (?,?,?);", objetos, "Nose puede registar");
            objetos.clear();

        }

        Reporte.setSize(569, 460);
        Reporte.setLocationRelativeTo(null);
        Cambio.setText(cambio + "");
        Reporte.setVisible(true);
    }

    public void ImprimirReporte() {
        if (ticket) {

        }
    }

    public void actualizar() {
        for (int i = 0; i < pro.size(); i++) {
            //En esta parte necesitamos dos cosas para actulizar el stoke 
            // el stok actual y el numero de unidades compradas

            double stokactual = Double.parseDouble(Conec.Select("select "
                    + " Unidades from productos where  codigoBarras='" + pro.get(i).codigoBarras + "';", 1).get(0) + "");
            System.out.println(stokactual);
            Conec.update("update productos set Unidades="
                    + (stokactual - pro.get(i).unidades) + " where codigoBarras='"
                    + pro.get(i).codigoBarras + "';", "No se pude actulizar");
        }
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

    public void tabla2(String sql, JTable Tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("# venta");
        modelo.addColumn("Empleado");
        modelo.addColumn("Importe");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cliente");
        Tabla.setModel(modelo);
        setVisible(true);

        ArrayList<String> datos = Conec.Select(sql, 5);
        //Antes de imprimir los datos en la tabla hay q modificar el cliente 

        int j = 0;
        String fila[] = new String[5];
        for (int i = 0; i < datos.size();) {

            while (j < 5) {
                if (j == 4) {
                    if (datos.get(i) == null) {
                        datos.set(i, "");
                    } else if (datos.get(i) != null) {
                        //Aqui cambiamos el id del cliente por su nombre 
                        datos.set(i, "" + Conec.Select("select nombre from clientes where id_cliente=" + datos.get(i) + ";", 1).get(0));
                    }
                }
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
        Reporte = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Cambio = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        cancelar = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        cliente = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla2 = new javax.swing.JTable();
        seleccionar = new javax.swing.JPopupMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        agregarProductos = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCodigoB = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        txtUnidades = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        JCPrecio = new javax.swing.JCheckBox();
        BtnAgregar = new javax.swing.JButton();
        buscarPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        importePanel = new javax.swing.JPanel();
        JLImporte = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        JcCliente = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        BtnRegistro = new javax.swing.JButton();
        carritoPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();

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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Gracias por su compra");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 12, 550, -1));

        jLabel5.setFont(new java.awt.Font("Corbel", 0, 30)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Su cambio");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 55, 540, -1));

        Cambio.setFont(new java.awt.Font("Arial", 1, 45)); // NOI18N
        Cambio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(Cambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 98, 550, 70));

        jButton3.setBackground(new java.awt.Color(255, 102, 0));
        jButton3.setFont(new java.awt.Font("Corbel", 0, 25)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Imprimir  ticket ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 251, 61));

        jButton5.setBackground(new java.awt.Color(51, 153, 0));
        jButton5.setFont(new java.awt.Font("Corbel", 0, 25)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Salir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 251, 61));

        javax.swing.GroupLayout ReporteLayout = new javax.swing.GroupLayout(Reporte.getContentPane());
        Reporte.getContentPane().setLayout(ReporteLayout);
        ReporteLayout.setHorizontalGroup(
            ReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ReporteLayout.setVerticalGroup(
            ReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        jLabel6.setText("¿Realmente quieres cancelar la compra?");

        jButton6.setBackground(new java.awt.Color(255, 51, 0));
        jButton6.setFont(new java.awt.Font("Corbel", 1, 25)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Si");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(204, 204, 0));
        jButton7.setFont(new java.awt.Font("Corbel", 1, 25)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("No");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout cancelarLayout = new javax.swing.GroupLayout(cancelar.getContentPane());
        cancelar.getContentPane().setLayout(cancelarLayout);
        cancelarLayout.setHorizontalGroup(
            cancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cancelarLayout.setVerticalGroup(
            cancelarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Corbel", 1, 25)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Selecciona al cliente");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 979, 25));

        txtBuscar1.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtBuscar1.setBorder(null);
        txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscar1KeyTyped(evt);
            }
        });
        jPanel7.add(txtBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 530, -1));

        jLabel24.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Buscar");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel7.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 530, 30));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, -1, -1));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        Tabla2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Tabla2.setModel(new javax.swing.table.DefaultTableModel(
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
        Tabla2.setComponentPopupMenu(seleccionar);
        Tabla2.setFocusable(false);
        Tabla2.setIntercellSpacing(new java.awt.Dimension(0, 0));
        Tabla2.setRowHeight(35);
        Tabla2.setSelectionBackground(new java.awt.Color(255, 153, 0));
        Tabla2.setShowVerticalLines(false);
        Tabla2.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(Tabla2);

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 153, 980, 330));

        javax.swing.GroupLayout clienteLayout = new javax.swing.GroupLayout(cliente.getContentPane());
        cliente.getContentPane().setLayout(clienteLayout);
        clienteLayout.setHorizontalGroup(
            clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        clienteLayout.setVerticalGroup(
            clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        seleccionar.setBackground(new java.awt.Color(255, 255, 255));

        jMenuItem4.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem4.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jMenuItem4.setText("Seleccionar ");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        seleccionar.add(jMenuItem4);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(238, 112, 82));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ventas");

        jLabel8.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Administrar ventas");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(194, 194, 194)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        agregarProductos.setBackground(new java.awt.Color(255, 255, 255));
        agregarProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        agregarProductos.setPreferredSize(new java.awt.Dimension(312, 270));

        jLabel20.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Agregar productos ");
        jLabel20.setPreferredSize(new java.awt.Dimension(264, 25));
        agregarProductos.add(jLabel20);

        jLabel14.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel14.setText("Código de Barras ");
        jLabel14.setPreferredSize(new java.awt.Dimension(250, 25));
        agregarProductos.add(jLabel14);

        txtCodigoB.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCodigoB.setBorder(null);
        txtCodigoB.setPreferredSize(new java.awt.Dimension(250, 25));
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
        agregarProductos.add(txtCodigoB);

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jSeparator7.setMinimumSize(new java.awt.Dimension(300, 10));
        jSeparator7.setPreferredSize(new java.awt.Dimension(250, 2));
        agregarProductos.add(jSeparator7);

        jLabel16.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel16.setText("Cantidad");
        jLabel16.setPreferredSize(new java.awt.Dimension(250, 25));
        agregarProductos.add(jLabel16);

        txtUnidades.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtUnidades.setBorder(null);
        txtUnidades.setPreferredSize(new java.awt.Dimension(250, 25));
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
        agregarProductos.add(txtUnidades);

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jSeparator6.setPreferredSize(new java.awt.Dimension(250, 2));
        agregarProductos.add(jSeparator6);

        JCPrecio.setBackground(new java.awt.Color(255, 255, 255));
        JCPrecio.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        JCPrecio.setText("Precio mayoreo");
        JCPrecio.setPreferredSize(new java.awt.Dimension(239, 33));
        agregarProductos.add(JCPrecio);

        BtnAgregar.setBackground(new java.awt.Color(255, 102, 0));
        BtnAgregar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAgregar.setText("Agregar");
        BtnAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnAgregar.setBorderPainted(false);
        BtnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAgregar.setPreferredSize(new java.awt.Dimension(150, 30));
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
        agregarProductos.add(BtnAgregar);

        buscarPanel.setBackground(new java.awt.Color(255, 255, 255));
        buscarPanel.setPreferredSize(new java.awt.Dimension(600, 270));

        jLabel22.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Buscar");

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.setPreferredSize(new java.awt.Dimension(350, 40));
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N

        jSeparator1.setPreferredSize(new java.awt.Dimension(350, 2));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(550, 200));

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

        javax.swing.GroupLayout buscarPanelLayout = new javax.swing.GroupLayout(buscarPanel);
        buscarPanel.setLayout(buscarPanelLayout);
        buscarPanelLayout.setHorizontalGroup(
            buscarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarPanelLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel22)
                .addGap(5, 5, 5)
                .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jLabel23)
                .addGap(67, 67, 67))
            .addGroup(buscarPanelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(125, 125, 125))
            .addGroup(buscarPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
        buscarPanelLayout.setVerticalGroup(
            buscarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(buscarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buscarPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel22))
                    .addGroup(buscarPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel23))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        importePanel.setBackground(new java.awt.Color(255, 255, 255));
        importePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        importePanel.setPreferredSize(new java.awt.Dimension(312, 270));

        JLImporte.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        JLImporte.setText("Importe: ");
        JLImporte.setPreferredSize(new java.awt.Dimension(264, 24));
        importePanel.add(JLImporte);

        jLabel19.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel19.setText("Efectivo");
        jLabel19.setPreferredSize(new java.awt.Dimension(250, 25));
        importePanel.add(jLabel19);

        txtEfectivo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtEfectivo.setBorder(null);
        txtEfectivo.setPreferredSize(new java.awt.Dimension(250, 25));
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
        importePanel.add(txtEfectivo);

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jSeparator8.setOpaque(true);
        jSeparator8.setPreferredSize(new java.awt.Dimension(250, 2));
        importePanel.add(jSeparator8);

        JcCliente.setBackground(new java.awt.Color(255, 255, 255));
        JcCliente.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        JcCliente.setText("Agregar Cliente");
        JcCliente.setPreferredSize(new java.awt.Dimension(250, 33));
        importePanel.add(JcCliente);

        jLabel18.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel18.setText("Descuento");
        jLabel18.setPreferredSize(new java.awt.Dimension(250, 25));
        importePanel.add(jLabel18);

        txtDescuento.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        txtDescuento.setBorder(null);
        txtDescuento.setPreferredSize(new java.awt.Dimension(250, 25));
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyTyped(evt);
            }
        });
        importePanel.add(txtDescuento);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setPreferredSize(new java.awt.Dimension(250, 2));
        importePanel.add(jSeparator2);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Pulsa aquí para quitar el descuento");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setMinimumSize(new java.awt.Dimension(250, 14));
        jLabel7.setPreferredSize(new java.awt.Dimension(250, 14));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        importePanel.add(jLabel7);

        BtnRegistro.setBackground(new java.awt.Color(255, 102, 0));
        BtnRegistro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnRegistro.setForeground(new java.awt.Color(255, 255, 255));
        BtnRegistro.setText("Realizar compra");
        BtnRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BtnRegistro.setBorderPainted(false);
        BtnRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnRegistro.setPreferredSize(new java.awt.Dimension(150, 30));
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
        importePanel.add(BtnRegistro);

        carritoPanel.setBackground(new java.awt.Color(255, 255, 255));
        carritoPanel.setMinimumSize(new java.awt.Dimension(160, 60));

        jLabel13.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Carrito de compras");
        jLabel13.setPreferredSize(new java.awt.Dimension(550, 25));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(550, 180));

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

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setBackground(new java.awt.Color(255, 0, 0));
        jButton4.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Cancelar compra");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton4);

        javax.swing.GroupLayout carritoPanelLayout = new javax.swing.GroupLayout(carritoPanel);
        carritoPanel.setLayout(carritoPanelLayout);
        carritoPanelLayout.setHorizontalGroup(
            carritoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, carritoPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(carritoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        carritoPanelLayout.setVerticalGroup(
            carritoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carritoPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(agregarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(buscarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(importePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(carritoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agregarProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buscarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(importePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(carritoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
        if (pro.size() != 0) {

            if (JcCliente.isSelected()) {
                if (txtEfectivo.getText().length() != 0) {
                    //Varificando q el efectivo sea mayor a la cantidad ingresada
                    double efectivo = 0;
                    try {
                        if (txtEfectivo.getText().length() != 0) {

                            efectivo = Double.parseDouble(txtEfectivo.getText());
                            if (importe > efectivo) {
                                JOptionPane.showMessageDialog(this, "El monto a pagar es mayor");
                            } else {

                                ticket = true;
                                //Abriendo la ventana para agregar al cliente
                                tabla("select * from clientes;", Tabla2);
                                cliente.setSize(1010, 497);
                                cliente.setLocationRelativeTo(null);

                                cliente.setVisible(true);
                                cambio = Math.abs(importe - efectivo);

                            }
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debes de ingresar el efectivo");
                }
            } else {//Varificnado q el prewcio este puesto
                if (txtEfectivo.getText().length() != 0) {
                    //Varificando q el efectivo sea mayor a la cantidad ingresada
                    double efectivo = 0;
                    try {
                        efectivo = Double.parseDouble(txtEfectivo.getText());
                        if (importe > efectivo) {
                            JOptionPane.showMessageDialog(this, "El monto a pagar es mayor");
                        } else {
                            cambio = Math.abs(importe - efectivo);
                            comprar();

                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debes de ingresar el efectivo");
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "No se agregado ningun producto aun");
        }
    }//GEN-LAST:event_BtnRegistroActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        tabla("select * from productos where codigoBarras='" + txtBuscar.getText() + "'  union select * from productos where NombreP like '%" + txtBuscar.getText() + "%';", Tabla1, new String[]{"Código barras",
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
            double unidadesdisponibles = Double.parseDouble(Conec.Select("select Unidades from productos where codigoBarras='" + getTexto(txtCodigoB) + "';", 1).get(0) + "");
            if (unidadesdisponibles >= Double.parseDouble(txtUnidades.getText())) {
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
                JOptionPane.showMessageDialog(this, "El numero de unidades de este producto es menor al solicitado");
            }

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

    private void txtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyTyped
        // TODO add your handling code here:
        char mander = evt.getKeyChar();
        String ex = mander + "";
        if (ex.equalsIgnoreCase(".")) {
            if (!txtDescuento.getText().contains(".")) {
                txtDescuento.setText(txtDescuento.getText() + ".");
            }

        }
        if (!Character.isDigit(mander)) {
            evt.consume();
        }

    }//GEN-LAST:event_txtDescuentoKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        // TODO add your handling code here:
        Ticket t = new Ticket("LYN", new GenerarFecha().getFecha(), id + "", User.usuario, new GenerarFecha().gethora(), items, importe + "", txtEfectivo.getText(), cambio + "");
        t.generarTicket();
        reiniciar();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        reiniciar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtDescuentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyPressed
        // TODO add your handling code here:
        //Si precionas enter aplicar el descueto
        if (evt.getKeyCode() == 10) {
            double descuento = Double.parseDouble(txtDescuento.getText()) / 100;
            importe = importe - (importe * descuento);

            JLImporte.setText("Importe: " + importe);
        }

    }//GEN-LAST:event_txtDescuentoKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        reiniciar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        reiniciar();
        cancelar.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        cancelar.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        calcularNuevoImporte();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void txtBuscar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyTyped
        // TODO add your handling code here:
        tabla("select * from clientes where  nombre like '%" + txtBuscar.getText() + "%';", Tabla, new String[]{
            "Nombre", "Correo", "Telefono"
        });
    }//GEN-LAST:event_txtBuscar1KeyTyped
    String id_Clinte;
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        int fila = Tabla2.getSelectedRow();
        if (fila > 0) {
            id_Clinte = Tabla2.getValueAt(fila, 0) + "";
            cliente.setVisible(false);

            comprar2();
        } else {
            JOptionPane.showMessageDialog(null, "No seleccionaste algun cliente");
        }

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        if(Conec.Select("select id_rol from  usuarios where usarName='"+User.usuario+"';", 1).get(0).equals("1")){
             new ReporteVentas().setVisible(true);
             
        }else{
           JOptionPane.showMessageDialog(null, "Lo sentimos no eres administrador no puedes ingresar a esta opción");
        }

    }//GEN-LAST:event_jLabel8MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonModificar;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnRegistro;
    private javax.swing.JLabel Cambio;
    private javax.swing.JDialog Dialogo;
    private javax.swing.JDialog DialogoEditar;
    private javax.swing.JCheckBox JCPrecio;
    private javax.swing.JLabel JLImporte;
    private javax.swing.JCheckBox JcCliente;
    private javax.swing.JPopupMenu Menu2;
    private javax.swing.JDialog Reporte;
    private javax.swing.JTable Tabla;
    private javax.swing.JTable Tabla1;
    private javax.swing.JTable Tabla2;
    private javax.swing.JTextField TxtUnidades2;
    private javax.swing.JPanel agregarProductos;
    private javax.swing.JPanel buscarPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JDialog cancelar;
    private javax.swing.JPanel carritoPanel;
    private javax.swing.JDialog cliente;
    private javax.swing.JPanel importePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPopupMenu seleccionar;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtCodigoB;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtUnidades;
    // End of variables declaration//GEN-END:variables
}
