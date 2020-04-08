
package Ventanas;

import Animaciones.Controlador;
import Animaciones.GaussianBlur;
import AppPackage.AnimationClass;
import BaseDatos.Encriptar;
import BaseDatos.Query;
import Principal.Conectar;
import java.awt.AWTException;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author omara
 */
public class Login extends javax.swing.JFrame implements Conectar, WindowListener{

    public static Login ins;
    boolean desbloquear=false;
    Query sql = SplashScrean.sql;
    ArrayList<String> datos;
    ArrayList<JPanel> panelsBotones = new ArrayList<>();
    public Salida salida;
    
    public Login() {
        ins = this;
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/papeleria.png")).getImage());
        Init();
    }
    
    private void Init()
    {
        this.setLocationRelativeTo(null);   
        //sql = new Query();
        datos = this.sql.Select("select usarName,password from usuarios where usarName='"+getUsuario()+"';", 2);
        addWindowListener(this);
        setOpacity(0);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH );
        Controlador.main.AccionesVentana(this, this::Abrir, 4);
        panelsBotones.add(BotonesMenu);
        //panelsBotones.add(JPClientes);
        salida = new Salida(this);
        salida.setVisible(true);
        defaultSize.setSize(getWidth(), getHeight());
        //salida.Ocultar();
    }
    
    private String getUsuario(){ return txtUsuario.getText(); }
    private String getPass(){ return new Encriptar(txtPassword.getText().toString()).Encrip(); }
    public void Login(){
        try
        {
            if (!maxi)setPreferredSize(defaultSize);
            BotonesMenu.setLayout(null);
            datos = sql.Select("select usarName,password from usuarios where usarName='"+getUsuario()+"';", 2);
            if(datos.get(0).equals(getUsuario())&& datos.get(1).equals(getPass())){
                desbloquear=true;
                Controlador.main.AnimacionJPIngreso(JPIngreso, JPMenu);
            }else{
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrecta");
            }   
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public Void Minimizar()
    {
        setOpacity(0);
        this.setState(Login.ICONIFIED);
        return null;
    }
    public Void Maximizar()
    {
        setOpacity(1);
        this.setState(NORMAL);
        return null;
    }
    public Void Cerrar() { System.exit(0); return null; }
    public Void Abrir(){ setOpacity(1); return null; }
    
    public void CerrarDef()
    {
        Controlador.main.AccionesVentana(this, this::Cerrar, 3);
        //unDarken();
    }
    
    public Void Opacidad(float lerp, boolean min)
    {
        if(min)
            setOpacity(1 - lerp);
        else
            setOpacity(lerp);
        return null;
    }
    boolean dark = false;
    GaussianBlur gb;
    public void Darken()
    {
        setEnabled(false);
        dark = true;
        repaint();
    }
    public void unDarken()
    {
        setEnabled(true);
        dark = false;
        gb = null;
        repaint();
    }
    
    BufferedImage ss() 
    {
        try {
            Robot r = new Robot();
            //Rectangle rec = new Rectangle(getX() + 2, getY()-2, getWidth()-2, getHeight()-2);
            //r.createScreenCapture(getBounds());
            BufferedImage bi = r.createScreenCapture(getBounds());
            return bi;
        } catch (AWTException ex) {
        }
        //BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        return null;
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if(dark)
        {
            if(gb == null){
            gb = new GaussianBlur(ss(), 12);
            //g.setColor(new Color(0, 0, 0, 140));
            //g.fillRect(0, 0, getWidth(), getHeight());
            gb.imagenBlur(getGraphics(), this);}
        }
    }
    
    public void ActivarPanel(int index)
    {
        for(int i = 0; i < panelsBotones.size(); i++)
        {
            JPanel p = panelsBotones.get(index);
            if(index == i)
                p.setVisible(true);
            else
                p.setVisible(false);
        }
    }
    
//    @Override
//    public void paint(Graphics gr)
//    {
//        super.paint(gr);
//        Graphics2D g2d = (Graphics2D) gr;
//        g2d.setRenderingHint(
//            RenderingHints.KEY_ANTIALIASING,
//            RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setComposite(AlphaComposite.getInstance(
//            AlphaComposite.SRC_OVER, lerp));
//    }
//    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPWindowTools = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        BotonesMenu = new javax.swing.JPanel();
        JPIngreso = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        JLInternet = new javax.swing.JLabel();
        JPMenu = new javax.swing.JPanel();
        NombreLocal = new javax.swing.JLabel();
        subJPMenu = new javax.swing.JPanel();
        pClientes = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pProductos = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pCiber = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pCuenta = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pVentas = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1080, 580));

        JPWindowTools.setBackground(new java.awt.Color(255, 255, 255));
        JPWindowTools.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        JPWindowTools.setForeground(new java.awt.Color(255, 255, 255));
        JPWindowTools.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        JPWindowTools.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                JPWindowToolsMouseDragged(evt);
            }
        });
        JPWindowTools.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPWindowToolsMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPWindowToolsMousePressed(evt);
            }
        });
        JPWindowTools.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        JPWindowTools.add(jLabel20);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8-maximize-button-32.png"))); // NOI18N
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        JPWindowTools.add(jLabel18);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Multiply_32px.png"))); // NOI18N
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        JPWindowTools.add(jLabel19);

        JPIngreso.setBackground(new java.awt.Color(255, 255, 255));
        JPIngreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        JPIngreso.setForeground(new java.awt.Color(255, 255, 255));
        JPIngreso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(238, 112, 82));
        jLabel3.setFont(new java.awt.Font("Candara", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(238, 112, 82));
        jLabel3.setText("Contraseña");
        JPIngreso.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        jLabel4.setBackground(new java.awt.Color(238, 112, 82));
        jLabel4.setFont(new java.awt.Font("Candara", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(238, 112, 82));
        jLabel4.setText("Usuario:");
        JPIngreso.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/UserName120.png"))); // NOI18N
        JPIngreso.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, 140));

        txtUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txtUsuario.setBorder(null);
        txtUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUsuarioMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtUsuarioMouseExited(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });
        JPIngreso.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 40));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        JPIngreso.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 210, 10));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        JPIngreso.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 210, 10));

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(187, 187, 187));
        txtPassword.setBorder(null);
        txtPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPasswordMouseClicked(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        JPIngreso.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 200, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Key_32px.png"))); // NOI18N
        JPIngreso.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_customer_32px_1.png"))); // NOI18N
        JPIngreso.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Menu_32px_1.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        JPIngreso.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_OFF.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_ON.png"))); // NOI18N
        jButton1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_ON.png"))); // NOI18N
        jButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_ON.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        JPIngreso.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 425, 120, 40));

        JLInternet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/paciente.png"))); // NOI18N
        JLInternet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLInternetMouseClicked(evt);
            }
        });
        JPIngreso.add(JLInternet, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 60, -1, 40));

        JPMenu.setBackground(new java.awt.Color(255, 255, 255));
        JPMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        JPMenu.setForeground(new java.awt.Color(255, 255, 255));

        NombreLocal.setBackground(new java.awt.Color(62, 120, 207));
        NombreLocal.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        NombreLocal.setForeground(new java.awt.Color(238, 112, 82));
        NombreLocal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NombreLocal.setText("Papeleria L Y N");
        NombreLocal.setPreferredSize(new java.awt.Dimension(800, 30));
        JPMenu.add(NombreLocal);

        subJPMenu.setBackground(new java.awt.Color(255, 255, 255));
        subJPMenu.setForeground(new java.awt.Color(255, 255, 255));
        subJPMenu.setOpaque(false);
        subJPMenu.setPreferredSize(new java.awt.Dimension(800, 450));
        subJPMenu.setLayout(new java.awt.GridLayout(2, 3));

        pClientes.setBackground(new java.awt.Color(255, 255, 255));
        pClientes.setForeground(new java.awt.Color(255, 255, 255));
        pClientes.setOpaque(false);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/comprador.png"))); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 150));
        jLabel8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel8MouseMoved(evt);
            }
        });
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
        });
        pClientes.add(jLabel8);

        jLabel9.setBackground(new java.awt.Color(238, 112, 82));
        jLabel9.setFont(new java.awt.Font("Candara", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(238, 101, 71));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Clientes");
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 30));
        pClientes.add(jLabel9);

        subJPMenu.add(pClientes);

        pProductos.setBackground(new java.awt.Color(255, 255, 255));
        pProductos.setForeground(new java.awt.Color(255, 255, 255));
        pProductos.setOpaque(false);
        pProductos.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/papeleria (1).png"))); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(150, 150));
        jLabel11.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel11MouseMoved(evt);
            }
        });
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });
        pProductos.add(jLabel11);

        jLabel10.setBackground(new java.awt.Color(238, 112, 82));
        jLabel10.setFont(new java.awt.Font("Candara", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(238, 101, 71));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Productos");
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 30));
        pProductos.add(jLabel10);

        subJPMenu.add(pProductos);

        pCiber.setBackground(new java.awt.Color(255, 255, 255));
        pCiber.setForeground(new java.awt.Color(255, 255, 255));
        pCiber.setOpaque(false);
        pCiber.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/computadora.png"))); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(150, 150));
        jLabel17.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel17MouseMoved(evt);
            }
        });
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });
        pCiber.add(jLabel17);

        jLabel16.setBackground(new java.awt.Color(238, 112, 82));
        jLabel16.setFont(new java.awt.Font("Candara", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(238, 101, 71));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Ciber");
        jLabel16.setPreferredSize(new java.awt.Dimension(150, 30));
        pCiber.add(jLabel16);

        subJPMenu.add(pCiber);

        pCuenta.setBackground(new java.awt.Color(255, 255, 255));
        pCuenta.setForeground(new java.awt.Color(255, 255, 255));
        pCuenta.setOpaque(false);
        pCuenta.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/seminario-web.png"))); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 150));
        jLabel12.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel12MouseMoved(evt);
            }
        });
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });
        pCuenta.add(jLabel12);

        jLabel13.setBackground(new java.awt.Color(238, 112, 82));
        jLabel13.setFont(new java.awt.Font("Candara", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(238, 101, 71));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Cuenta");
        jLabel13.setPreferredSize(new java.awt.Dimension(150, 30));
        pCuenta.add(jLabel13);

        subJPMenu.add(pCuenta);

        pVentas.setBackground(new java.awt.Color(255, 255, 255));
        pVentas.setForeground(new java.awt.Color(255, 255, 255));
        pVentas.setOpaque(false);
        pVentas.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/carrito-de-compras.png"))); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(150, 150));
        jLabel14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel14MouseMoved(evt);
            }
        });
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
            }
        });
        pVentas.add(jLabel14);

        jLabel15.setBackground(new java.awt.Color(238, 112, 82));
        jLabel15.setFont(new java.awt.Font("Candara", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(238, 101, 71));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Ventas");
        jLabel15.setPreferredSize(new java.awt.Dimension(150, 30));
        pVentas.add(jLabel15);

        subJPMenu.add(pVentas);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(100, 100));
        subJPMenu.add(jPanel8);

        JPMenu.add(subJPMenu);

        javax.swing.GroupLayout BotonesMenuLayout = new javax.swing.GroupLayout(BotonesMenu);
        BotonesMenu.setLayout(BotonesMenuLayout);
        BotonesMenuLayout.setHorizontalGroup(
            BotonesMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BotonesMenuLayout.createSequentialGroup()
                .addComponent(JPIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JPMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE))
        );
        BotonesMenuLayout.setVerticalGroup(
            BotonesMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
            .addComponent(JPMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPWindowTools, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BotonesMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JPWindowTools, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotonesMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    Point lastPos;
    Dimension defaultSize = new Dimension(), d = new Dimension();
    boolean maxi = false;
    
//    public void Resize()
//    {
//        //System.out.println(BotonesMenu.getLayout().getClass().getName());
//        if(BotonesMenu.getLayout() != null)
//            return;
//        BotonesMenu.setLayout(new GroupLayout(BotonesMenu));
//        GroupLayout layout = (GroupLayout)getLayout();
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(JPWindowTools, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//            .addComponent(BotonesMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addComponent(JPWindowTools, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(BotonesMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//        System.out.println("asdsa");
//    }
    
    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        maxi = !maxi;
        int maxx = Toolkit.getDefaultToolkit().getScreenSize().width;
        int maxy = Toolkit.getDefaultToolkit().getScreenSize().height;
        if(maxi)
        {
            lastPos = getLocation();
            d.setSize(maxx, maxy);
            setPreferredSize(d);
            setLocation(0, 0);
        }
        else
        {
            setPreferredSize(defaultSize);
            setLocation(lastPos);
        }
        pack();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
//        int dialog=JOptionPane.YES_NO_OPTION;
//        int result=JOptionPane.showConfirmDialog(null,"¿Desea salir del Login?","Salir",dialog);
//        if(result==0){
//            //System.exit(0);
//            Controlador.main.AccionesVentana(this, this::Cerrar, 3);
//        }
        salida.Mostrar(this);
    }//GEN-LAST:event_jLabel19MouseClicked

    private int tx, ty;
    private void JPWindowToolsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPWindowToolsMousePressed
        tx = evt.getX();
        ty = evt.getY();
        Controlador.main.ResetMinMax();
    }//GEN-LAST:event_JPWindowToolsMousePressed

    private void JPWindowToolsMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPWindowToolsMouseDragged
        if(maxi)
            return;
        setLocation(evt.getXOnScreen() - tx,  evt.getYOnScreen() - ty);
    }//GEN-LAST:event_JPWindowToolsMouseDragged

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        Controlador.main.AccionesVentana(this, this::Minimizar, 1);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        // TODO add your handling code here:
        repaint();
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
    }//GEN-LAST:event_jLabel14MouseExited

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        if(desbloquear){
            //abrir una nueva ventana
        }else{
            JOptionPane.showMessageDialog(this, "Primero debe de entrar con usuario valido");
        }
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel14MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseMoved
        // TODO add your handling code here:
        repaint();
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,153)));
    }//GEN-LAST:event_jLabel14MouseMoved

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        // TODO add your handling code here:
        repaint();
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
    }//GEN-LAST:event_jLabel12MouseExited

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:

        if(desbloquear){
            //abrir una nueva ventana
        }else{
            JOptionPane.showMessageDialog(this, "Primero debe de entrar con usuario valido");
        }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel12MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseMoved
        // TODO add your handling code here:
        repaint();
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,153)));
    }//GEN-LAST:event_jLabel12MouseMoved

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
        // TODO add your handling code here:
        repaint();
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
    }//GEN-LAST:event_jLabel17MouseExited

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:

        if(desbloquear){
            //abrir una nueva ventana
        }else{
            JOptionPane.showMessageDialog(this, "Primero debe de entrar con usuario valido");
        }
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel17MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseMoved
        // TODO add your handling code here:
        repaint();
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,153)));
    }//GEN-LAST:event_jLabel17MouseMoved

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        // TODO add your handling code here:
        repaint();
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        if(desbloquear){
            //abrir una nueva ventana
           new MenuProductos().setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Primero debe de entrar con usuario valido");
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel11MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseMoved
        // TODO add your handling code here:
        repaint();
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,153)));
    }//GEN-LAST:event_jLabel11MouseMoved

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        // TODO add your handling code here:
        repaint();
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,255,255)));
    }//GEN-LAST:event_jLabel8MouseExited

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        //Producto  esto es como un boton  pero es un label
        if(desbloquear){
            //abrir una nueva ventana
            ActivarPanel(0);
        }else{
            JOptionPane.showMessageDialog(this, "Primero debe de entrar con usuario valido");
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel8MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseMoved
        // TODO add your handling code here:
        repaint();
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153,153,153)));
    }//GEN-LAST:event_jLabel8MouseMoved

    private void JLInternetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLInternetMouseClicked
        // TODO add your handling code here:
        // Fade.JFrameFadeIn(1f, 0f, 0.1f, 30, new CrearCuenta());
       new CrearCuenta().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_JLInternetMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Login();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        AnimationClass internet= new AnimationClass();
        internet.jLabelXRight(-40, 10, 10, 5, JLInternet);

        //<---
        AnimationClass internett= new AnimationClass();
        internett.jLabelXLeft(10, -40, 10, 5, JLInternet);

    }//GEN-LAST:event_jLabel6MouseClicked

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            Login();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void txtPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPasswordMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtPasswordMouseClicked

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10 && txtPassword.getText().length()>0){
            Login();

        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_txtUsuarioMouseExited

    private void txtUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtUsuarioMouseClicked

    private void JPWindowToolsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPWindowToolsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_JPWindowToolsMouseClicked

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Login().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel BotonesMenu;
    private javax.swing.JLabel JLInternet;
    private javax.swing.JPanel JPIngreso;
    private javax.swing.JPanel JPMenu;
    private javax.swing.JPanel JPWindowTools;
    private javax.swing.JLabel NombreLocal;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel pCiber;
    private javax.swing.JPanel pClientes;
    private javax.swing.JPanel pCuenta;
    private javax.swing.JPanel pProductos;
    private javax.swing.JPanel pVentas;
    private javax.swing.JPanel subJPMenu;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
        Controlador.main.AccionesVentana(this, this::Minimizar, 1);
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        Controlador.main.AccionesVentana(this, this::Maximizar, 2);
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
