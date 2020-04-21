package Ventanas;


import Actores.Producto;
import Actores.TextPrompt;

import Principal.Conectar;
import static Principal.Conectar.Conec;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.temporal.Temporal;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author omara
 */
public class MenuProductos extends javax.swing.JFrame implements Conectar {

    ArrayList<JTextField> cajas = new ArrayList<>();
    ArrayList<String> place = new ArrayList();
    int pocionActual = 0;

    Confirmacion confirmacion = new Confirmacion(this);
    MensajeError msjerror = new MensajeError();

    public MenuProductos() {
        initComponents();
        setExtendedState(this.MAXIMIZED_BOTH);
        setLocationRelativeTo(this);
        PanelBorrar.setVisible(false);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/papeleria.png")).getImage());
        Thread Formato = new Thread(new FormatoTabla());
        Thread hint = new Thread(new Hint());
        Formato.start();
        hint.start();
        //Cambiar 
        
    }

    //clase para detertar cuando un producto ya no esta disponible 
    //Hilos para q la carga sea mas rapida
    //el hilo se encargara de dar diseño a la tabla 
    class FormatoTabla implements Runnable {

        @Override
        public void run() {
            diseñoTabla(Tabla);
            diseñoTabla(Tabla2);
            tabla("select * from productos;", Tabla);
        }

    }

    //Hilo dos se encarga de cargar los place holder en las cajjas de texto
    class Hint implements Runnable {

        @Override
        public void run() {
            PlaceHorlder();
            TextPrompt prueba = new TextPrompt("Escribe el código o nombre del producto", txtBuscar);
        }

    }

    void MostrarVentanaC() {
        //Mostrando la ventana correspondienrte de acuerdo  la barra 
        if (pocionActual == 0) {
            //Texto1.setText("Registro de productos");
            BtnRegistro.setText("Registrar");
        } else if (pocionActual == 1) {
            //Texto1.setText("Actualizar productos");
            BtnRegistro.setText("Actualizar");
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

    public Void ActualizarV() {
        Log.setText(Log.getText() + "\n[" + hora() + "] " + txtNombreProducto.getText() + " actualizado con éxito");
        Actualizar();
        tabla("Select * from productos", Tabla);
        pocionActual = 0;

        PanelBorrar.setVisible(false);
        PanelRegistro.setVisible(true);
        MostrarVentanaC();
        return null;
    }

    String hora() {
        return java.time.LocalTime.now().toString();
    }

    public Void Borrar() {
        int fila = Tabla.getSelectedRow();
        Log.setText(Log.getText() + "\n[" + hora() + "] SE ELIMINÓ " + Tabla.getValueAt(fila, 1).toString());
        if (fila >= 0) {
            Conec.delete("delete from productos where codigoBarras='" + Tabla.getValueAt(fila, 0) + "';", "No se pudo eliminar el producto");
            tabla("select * from productos;", Tabla);
        }
        pocionActual = 0;

        PanelBorrar.setVisible(false);
        PanelRegistro.setVisible(true);
        MostrarVentanaC();
        return null;
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
   private String  getName(JTable tabla, int i , int j){
       //Metodo para obtener un string de la tabla
       return tabla.getValueAt(i,j)+"";
   }
   private int getUnidades(JTable tabla , int i ,int j){
       return Integer.parseInt(tabla.getValueAt(i, j)+"");
   }
    void pintarColumnaTabla(JTable tabla){
        ColorearFilas color = new ColorearFilas(4);
        tabla.getColumnModel().getColumn(4).setCellRenderer(color);
    }
   //Metodo para pintar de colores  la fila de los productos q tengan menos de 10
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Inventario = new javax.swing.JLabel();
        Label = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        InventarioLogo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        PanelRegistro = new javax.swing.JPanel();
        Campos = new javax.swing.JPanel();
        pane_codigoBarras = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtCodigoBarra = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        pane_precioProducto = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        pane_precioUnitario = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtPrecioUnitarii = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        pane_PrecioMayoreo = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        TxtPrecioMayoreo = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        pane_Unidades = new javax.swing.JPanel();
        txtUnidades = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        BtnRegistro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        pane_Buscar = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Log = new javax.swing.JTextArea();
        PanelBorrar = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtBuscar1 = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla2 = new javax.swing.JTable();

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

        jPopupMenu2.setBackground(new java.awt.Color(255, 255, 255));
        jPopupMenu2.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N

        jMenuItem3.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem3.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jMenuItem3.setText("Ver de forma grafica");
        jPopupMenu2.add(jMenuItem3);

        jMenuItem4.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem4.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jMenuItem4.setText("Ver alertas");
        jPopupMenu2.add(jMenuItem4);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1150, 650));
        setSize(new java.awt.Dimension(1200, 700));

        jPanel2.setBackground(new java.awt.Color(238, 112, 82));
        jPanel2.setMinimumSize(new java.awt.Dimension(1100, 136));
        jPanel2.setPreferredSize(new java.awt.Dimension(1100, 136));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/papeleria (1).png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Papelería L y N");

        Inventario.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        Inventario.setForeground(new java.awt.Color(255, 255, 255));
        Inventario.setText("Inventario");
        Inventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Inventario.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                InventarioMouseMoved(evt);
            }
        });
        Inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventarioMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InventarioMouseExited(evt);
            }
        });

        Label.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        Label.setForeground(new java.awt.Color(255, 255, 255));
        Label.setText("Registro Productos");
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

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Actualizar productos");
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

        InventarioLogo.setForeground(new java.awt.Color(255, 255, 255));
        InventarioLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inventario.png"))); // NOI18N
        InventarioLogo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pro.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(52, 52, 52)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(83, 83, 83)
                .addComponent(InventarioLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Inventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(InventarioLogo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Inventario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(1100, 497));
        jLayeredPane1.setLayout(new java.awt.CardLayout());

        PanelRegistro.setBackground(new java.awt.Color(255, 255, 255));
        PanelRegistro.setMinimumSize(new java.awt.Dimension(1100, 497));

        Campos.setBackground(new java.awt.Color(255, 255, 255));
        Campos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pane_codigoBarras.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel12.setText("Código de Barras ");

        txtCodigoBarra.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCodigoBarra.setBorder(null);
        txtCodigoBarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoBarraActionPerformed(evt);
            }
        });
        txtCodigoBarra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoBarraKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBarraKeyTyped(evt);
            }
        });

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/codigo-de-barras.png"))); // NOI18N

        javax.swing.GroupLayout pane_codigoBarrasLayout = new javax.swing.GroupLayout(pane_codigoBarras);
        pane_codigoBarras.setLayout(pane_codigoBarrasLayout);
        pane_codigoBarrasLayout.setHorizontalGroup(
            pane_codigoBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_codigoBarrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(pane_codigoBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigoBarra, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pane_codigoBarrasLayout.createSequentialGroup()
                        .addGroup(pane_codigoBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pane_codigoBarrasLayout.setVerticalGroup(
            pane_codigoBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pane_codigoBarrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_codigoBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(pane_codigoBarrasLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        Campos.add(pane_codigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 80));

        pane_precioProducto.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Nombre del producto ");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pegamento.png"))); // NOI18N

        txtNombreProducto.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtNombreProducto.setBorder(null);
        txtNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProductoActionPerformed(evt);
            }
        });
        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyTyped(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout pane_precioProductoLayout = new javax.swing.GroupLayout(pane_precioProducto);
        pane_precioProducto.setLayout(pane_precioProductoLayout);
        pane_precioProductoLayout.setHorizontalGroup(
            pane_precioProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_precioProductoLayout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(pane_precioProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pane_precioProductoLayout.setVerticalGroup(
            pane_precioProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_precioProductoLayout.createSequentialGroup()
                .addGroup(pane_precioProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pane_precioProductoLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pane_precioProductoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel17)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Campos.add(pane_precioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 395, 80));

        pane_precioUnitario.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel14.setText("Precio unitario");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/precio.png"))); // NOI18N

        txtPrecioUnitarii.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtPrecioUnitarii.setBorder(null);
        txtPrecioUnitarii.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioUnitariiKeyTyped(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout pane_precioUnitarioLayout = new javax.swing.GroupLayout(pane_precioUnitario);
        pane_precioUnitario.setLayout(pane_precioUnitarioLayout);
        pane_precioUnitarioLayout.setHorizontalGroup(
            pane_precioUnitarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pane_precioUnitarioLayout.createSequentialGroup()
                .addGroup(pane_precioUnitarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pane_precioUnitarioLayout.createSequentialGroup()
                        .addGap(0, 75, Short.MAX_VALUE)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pane_precioUnitarioLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addGroup(pane_precioUnitarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecioUnitarii)
                            .addGroup(pane_precioUnitarioLayout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pane_precioUnitarioLayout.setVerticalGroup(
            pane_precioUnitarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_precioUnitarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_precioUnitarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pane_precioUnitarioLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecioUnitarii))
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Campos.add(pane_precioUnitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 395, 80));

        pane_PrecioMayoreo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel15.setText("Precio mayoreo");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rebaja.png"))); // NOI18N

        TxtPrecioMayoreo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtPrecioMayoreo.setBorder(null);
        TxtPrecioMayoreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtPrecioMayoreoKeyTyped(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout pane_PrecioMayoreoLayout = new javax.swing.GroupLayout(pane_PrecioMayoreo);
        pane_PrecioMayoreo.setLayout(pane_PrecioMayoreoLayout);
        pane_PrecioMayoreoLayout.setHorizontalGroup(
            pane_PrecioMayoreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_PrecioMayoreoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addGroup(pane_PrecioMayoreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pane_PrecioMayoreoLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator4))
                    .addGroup(pane_PrecioMayoreoLayout.createSequentialGroup()
                        .addGroup(pane_PrecioMayoreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtPrecioMayoreo, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pane_PrecioMayoreoLayout.setVerticalGroup(
            pane_PrecioMayoreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_PrecioMayoreoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_PrecioMayoreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pane_PrecioMayoreoLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(7, 7, 7)
                        .addComponent(TxtPrecioMayoreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Campos.add(pane_PrecioMayoreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 390, 90));

        pane_Unidades.setBackground(new java.awt.Color(255, 255, 255));

        txtUnidades.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtUnidades.setBorder(null);
        txtUnidades.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUnidadesKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel16.setText("Unidades");

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/proveedor.png"))); // NOI18N

        javax.swing.GroupLayout pane_UnidadesLayout = new javax.swing.GroupLayout(pane_Unidades);
        pane_Unidades.setLayout(pane_UnidadesLayout);
        pane_UnidadesLayout.setHorizontalGroup(
            pane_UnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pane_UnidadesLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel20)
                .addGroup(pane_UnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pane_UnidadesLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(pane_UnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUnidades, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator5))
                        .addContainerGap())
                    .addGroup(pane_UnidadesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(230, Short.MAX_VALUE))))
        );
        pane_UnidadesLayout.setVerticalGroup(
            pane_UnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_UnidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_UnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pane_UnidadesLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        Campos.add(pane_Unidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 390, 80));

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
        Campos.add(BtnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 210, 40));

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
        Tabla.setComponentPopupMenu(jPopupMenu1);
        Tabla.setFocusable(false);
        Tabla.setIntercellSpacing(new java.awt.Dimension(0, 0));
        Tabla.setRowHeight(35);
        Tabla.setSelectionBackground(new java.awt.Color(255, 153, 0));
        Tabla.setShowVerticalLines(false);
        Tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Tabla);

        pane_Buscar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Buscar");

        txtBuscar.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtBuscar.setBorder(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N

        javax.swing.GroupLayout pane_BuscarLayout = new javax.swing.GroupLayout(pane_Buscar);
        pane_Buscar.setLayout(pane_BuscarLayout);
        pane_BuscarLayout.setHorizontalGroup(
            pane_BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_BuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pane_BuscarLayout.setVerticalGroup(
            pane_BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_BuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
            .addGroup(pane_BuscarLayout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        Log.setEditable(false);
        Log.setColumns(20);
        Log.setRows(10);
        Log.setText("\n");
        Log.setDragEnabled(true);
        jScrollPane3.setViewportView(Log);

        javax.swing.GroupLayout PanelRegistroLayout = new javax.swing.GroupLayout(PanelRegistro);
        PanelRegistro.setLayout(PanelRegistroLayout);
        PanelRegistroLayout.setHorizontalGroup(
            PanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Campos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelRegistroLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(pane_Buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelRegistroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))))
                .addContainerGap())
        );
        PanelRegistroLayout.setVerticalGroup(
            PanelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelRegistroLayout.createSequentialGroup()
                .addComponent(Campos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(43, 43, 43))
            .addGroup(PanelRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pane_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane1.add(PanelRegistro, "card2");

        PanelBorrar.setBackground(new java.awt.Color(255, 255, 255));
        PanelBorrar.setMinimumSize(new java.awt.Dimension(1100, 497));
        PanelBorrar.setPreferredSize(new java.awt.Dimension(1100, 497));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Corbel", 1, 20)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Buscar");

        txtBuscar1.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        txtBuscar1.setBorder(null);
        txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscar1KeyTyped(evt);
            }
        });

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(392, 392, 392))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        Tabla2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        Tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tabla2.setComponentPopupMenu(jPopupMenu2);
        Tabla2.setRowHeight(25);
        Tabla2.setSelectionBackground(new java.awt.Color(255, 153, 0));
        Tabla2.setShowVerticalLines(false);
        jScrollPane2.setViewportView(Tabla2);

        javax.swing.GroupLayout PanelBorrarLayout = new javax.swing.GroupLayout(PanelBorrar);
        PanelBorrar.setLayout(PanelBorrarLayout);
        PanelBorrarLayout.setHorizontalGroup(
            PanelBorrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelBorrarLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2)
                .addGap(40, 40, 40))
        );
        PanelBorrarLayout.setVerticalGroup(
            PanelBorrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBorrarLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.add(PanelBorrar, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LabelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelMouseMoved
        // TODO add your handling code here:
        Label.setFont(new java.awt.Font("Corbel", 1, 22));
        Label.setText("Registro productos");
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pro1.png")));
//   jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
    }//GEN-LAST:event_LabelMouseMoved

    private void LabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelMouseExited
        // TODO add your handling code here:[238,112,82]
        Label.setFont(new java.awt.Font("Corbel", 1, 20));
        Label.setText("Registro productos");
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pro.png")));
        //   jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238,112,82)));
    }//GEN-LAST:event_LabelMouseExited

    private void LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelMouseClicked

        pocionActual = 0;

        PanelBorrar.setVisible(false);
        PanelRegistro.setVisible(true);
        MostrarVentanaC();

    }//GEN-LAST:event_LabelMouseClicked

    private void InventarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventarioMouseExited
        // TODO add your handling code here:
        Inventario.setFont(new java.awt.Font("Corbel", 1, 20));
        Inventario.setText("Inventario");
        InventarioLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inventario.png")));
    }//GEN-LAST:event_InventarioMouseExited

    private void InventarioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventarioMouseMoved
        // TODO add your handling code here:
        Inventario.setFont(new java.awt.Font("Corbel", 1, 22));
        Inventario.setText("Inventario");
        InventarioLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inventario1.png")));
    }//GEN-LAST:event_InventarioMouseMoved

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
        //System.out.println("menuitem2");
//        int fila = Tabla.getSelectedRow();
//        if (fila >= 0) {
//            Conec.delete("delete from productos where codigoBarras='" + Tabla.getValueAt(fila, 0) + "';", "No se pudo eliminar el producto");
//            tabla("select * from productos;", Tabla);
//        }
        confirmacion.Mostrar("¿Eliminar " + Tabla.getValueAt(Tabla.getSelectedRow(), 1).toString() + "?", this::Borrar);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        // TODO add your handling code here:
        jLabel5.setFont(new java.awt.Font("Corbel", 1, 20));
        jLabel5.setText("Actualizar productos");
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar.png")));
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:

        pocionActual = 1;
        PanelBorrar.setVisible(false);
        PanelRegistro.setVisible(true);
        MostrarVentanaC();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseMoved
        // TODO add your handling code here:
        jLabel5.setFont(new java.awt.Font("Corbel", 1, 22));
        jLabel5.setText("Actualizar productos");
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/actualizar1.png")));
    }//GEN-LAST:event_jLabel5MouseMoved

    private void InventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventarioMouseClicked
        // TODO add your handling code here:
        PanelBorrar.setVisible(true);
        tabla("select * from productos;", Tabla2);
        PanelRegistro.setVisible(false);
        pintarColumnaTabla(Tabla2);
    }//GEN-LAST:event_InventarioMouseClicked

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        tabla("select * from productos where codigoBarras='" + txtBuscar.getText() + "'  union select * from productos where NombreP like '%" + txtBuscar.getText() + "%';", Tabla);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void BtnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistroActionPerformed
        // TODO add your handling code here:
        if (pocionActual == 0) {

            try {
                /* Preguntamos si los  datos como nombre son nulos esos daros no pueden ser nulos ya que todo
                producto tiene un nombre pero puede faltar su codigo de barras
                 */
                if (txtNombreProducto.getText().length() == 0 || txtPrecioUnitarii.getText().length() == 0) {
                    //MensajeError men = new MensajeError();
                    msjerror.Mostrar("Debes ingresar el nombre del\n producto o el precio unitario");
                    //men.Mensaje.setText("Debes ingresar el nombre del\n producto o el precio unitario");
                    //men.setVisible(true);

                } else {
                    Log.setText(Log.getText() + "\n[" + hora() + "] " + txtNombreProducto.getText() + " registrado con éxito");
                    ObtenerProductos();
                    tabla("Select * from productos", Tabla);
                }

            } catch (Error e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                Log.setText(Log.getText() + "****ERROR****");
            }
        }
        if (pocionActual == 1) {
            if (Tabla.getSelectionModel().isSelectionEmpty()) {
                return;
            }
            //Aqui es donde ponemos lo de actualizar los prodcutos
            confirmacion.Mostrar("¿Actualizar " + Tabla.getValueAt(Tabla.getSelectedRow(), 1).toString() + "?", this::ActualizarV);
            //Actualizar();

            //tabla("Select * from productos", Tabla);
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

    private void txtNombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProductoKeyTyped

    private void txtCodigoBarraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarraKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCodigoBarraKeyTyped

    private void txtCodigoBarraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarraKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            if (pocionActual == 1) {

                String sql = "select * from productos where codigoBarras='" + txtCodigoBarra.getText() + "';";
                ArrayList<String> lista = Conec.Select(sql, 5);
                if (lista.size() != 0) {
                    llenarcajas();

                    for (int i = 1; i < lista.size(); i++) {
                        if (lista.get(i).length() != 0) {
                            cajas.get(i).setText(lista.get(i));
                        }
                    }
                    cajas.clear();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encuentra ningun producto con ese codigo de barrras");
                }
            }
        }
    }//GEN-LAST:event_txtCodigoBarraKeyPressed

    private void txtBuscar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyTyped
        // TODO add your handling code here:
        tabla("select * from productos where codigoBarras='" + txtBuscar.getText() + "'  union select * from productos where NombreP like '%" + txtBuscar1.getText() + "%';", Tabla2);
    }//GEN-LAST:event_txtBuscar1KeyTyped

    private void txtCodigoBarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoBarraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBarraActionPerformed

    private void txtNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProductoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRegistro;
    private javax.swing.JPanel Campos;
    private javax.swing.JLabel Inventario;
    private javax.swing.JLabel InventarioLogo;
    private javax.swing.JLabel Label;
    private javax.swing.JTextArea Log;
    private javax.swing.JPanel PanelBorrar;
    private javax.swing.JPanel PanelRegistro;
    private javax.swing.JTable Tabla;
    private javax.swing.JTable Tabla2;
    private javax.swing.JTextField TxtPrecioMayoreo;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel pane_Buscar;
    private javax.swing.JPanel pane_PrecioMayoreo;
    private javax.swing.JPanel pane_Unidades;
    private javax.swing.JPanel pane_codigoBarras;
    private javax.swing.JPanel pane_precioProducto;
    private javax.swing.JPanel pane_precioUnitario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtCodigoBarra;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecioUnitarii;
    private javax.swing.JTextField txtUnidades;
    // End of variables declaration//GEN-END:variables
}
