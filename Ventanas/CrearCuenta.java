package Ventanas;

import Actores.ObtenerTextos;
import Actores.TextPrompt;
import Actores.User;
import AppPackage.AnimationClass;
import BaseDatos.Encriptar;

import Principal.Conectar;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import javax.swing.JTextField;

/**
 *
 * @author omara
 */
public class CrearCuenta extends javax.swing.JFrame implements Conectar {

    //Variables para saber si el usuario esta disponible 
    boolean UserDisponible;
    int clicks = 0;
    char prueba;
    ObtenerTextos tex;
    ArrayList<Object> cajasTexto = new ArrayList();
    ArrayList<String> place = new ArrayList();
    
    public CrearCuenta() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/papeleria.png")).getImage());
        setLocationRelativeTo(this);
        
        prueba = txtPass.getEchoChar();
        llenarCajas();
        llenarTextos();
        
        Thread t2 = new Thread(new placeHolder());
        
        t2.start();
        setResizable(false);
        Panel2.setMinimumSize(new Dimension(Panel2.getWidth(), this.MAXIMIZED_VERT));
        setVisible(true);
        
    }
    
    public void llenarCajas() {
        cajasTexto.add(txtNombre);
        cajasTexto.add(txtUsuario);
        
        cajasTexto.add(txtCorreo);
        cajasTexto.add(txtTelefono);
        
    }

    private String getPass() {
        return new Encriptar(txtPass.getText().toString()).Encrip();
    }

    private String getPass(JPasswordField txtPass) {
        return new Encriptar(txtPass.getText().toString()).Encrip();
    }

    public void llenarTextos() {
        place.add("Nombre");
        place.add("Usuario");
        place.add("Correo");
        place.add("Telefono");
        
    }
    
    public class placeHolder implements Runnable {
        
        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                
                TextPrompt prueba = new TextPrompt(place.get(i), ((JTextField) cajasTexto.get(i)));
                
            }
            cajasTexto.clear();
            place.clear();
            
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JDialog();
        JPIngreso = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtUsuario1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtPassword = new javax.swing.JPasswordField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButtonEnter = new javax.swing.JButton();
        JLInternet = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Panel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        VerPasword = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        JLDisponibilidadUsuario = new javax.swing.JLabel();
        JLDisponibilidadUsuario1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        JPIngreso.setBackground(new java.awt.Color(255, 255, 255));
        JPIngreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        JPIngreso.setForeground(new java.awt.Color(255, 255, 255));
        JPIngreso.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        JPIngreso.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                JPIngresoMouseDragged(evt);
            }
        });
        JPIngreso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPIngresoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPIngresoMousePressed(evt);
            }
        });
        JPIngreso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(238, 112, 82));
        jLabel8.setFont(new java.awt.Font("Candara", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(238, 112, 82));
        jLabel8.setText("Contraseña");
        JPIngreso.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        jLabel18.setBackground(new java.awt.Color(238, 112, 82));
        jLabel18.setFont(new java.awt.Font("Candara", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(238, 112, 82));
        jLabel18.setText("Usuario:");
        JPIngreso.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/UserName120.png"))); // NOI18N
        JPIngreso.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, 140));

        txtUsuario1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsuario1.setForeground(new java.awt.Color(153, 153, 153));
        txtUsuario1.setBorder(null);
        txtUsuario1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUsuario1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtUsuario1MouseExited(evt);
            }
        });
        txtUsuario1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuario1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuario1KeyTyped(evt);
            }
        });
        JPIngreso.add(txtUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 200, 40));

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

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Key_32px.png"))); // NOI18N
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        JPIngreso.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, 40));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_customer_32px_1.png"))); // NOI18N
        JPIngreso.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 40));

        jButtonEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_OFF.png"))); // NOI18N
        jButtonEnter.setBorder(null);
        jButtonEnter.setBorderPainted(false);
        jButtonEnter.setContentAreaFilled(false);
        jButtonEnter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonEnter.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_ON.png"))); // NOI18N
        jButtonEnter.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_ON.png"))); // NOI18N
        jButtonEnter.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_ON.png"))); // NOI18N
        jButtonEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnterActionPerformed(evt);
            }
        });
        JPIngreso.add(jButtonEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 140, 40));

        JLInternet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/paciente.png"))); // NOI18N
        JLInternet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLInternetMouseClicked(evt);
            }
        });
        JPIngreso.add(JLInternet, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 60, -1, 40));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login.getContentPane());
        login.getContentPane().setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 305, Short.MAX_VALUE)
            .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(loginLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(JPIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
            .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(loginLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(JPIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel2.setText("Contraseña");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 310, 200, 50));

        txtNombre.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 150, 410, 50));

        txtUsuario.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 220, 410, 50));

        txtPass.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 360, 410, 50));

        jComboBox1.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Administrador" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 460, 410, 50));

        jLabel3.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setText(" inicia sesión");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 80, 120, 50));

        jButton1.setBackground(new java.awt.Color(238, 112, 82));
        jButton1.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Crear Cuenta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 750, 400, 50));

        jLabel4.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel4.setText("Crea una cuenta");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 80, 210, 50));

        jLabel5.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel5.setText("O bien");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 80, 60, 50));

        txtCorreo.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 530, 410, 50));

        txtTelefono.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 600, 410, 50));

        jLabel6.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel6.setText("Cargo");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 410, 200, 50));

        Panel2.setBackground(new java.awt.Color(238, 112, 82));
        Panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Corbel", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Papeleria L Y N");
        Panel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 830, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boligrafo.png"))); // NOI18N
        Panel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 330, 390));

        jPanel1.add(Panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 840));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Name.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 150, -1, 50));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/UserName.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 220, -1, 50));

        VerPasword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bloquear.png"))); // NOI18N
        VerPasword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VerPasword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VerPaswordMouseClicked(evt);
            }
        });
        jPanel1.add(VerPasword, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 360, 50, 50));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/equipo.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 460, -1, 60));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/gmail.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 530, -1, 50));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ui.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 600, -1, 50));

        JLDisponibilidadUsuario.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        JLDisponibilidadUsuario.setForeground(new java.awt.Color(255, 0, 0));
        JLDisponibilidadUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(JLDisponibilidadUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 280, 400, 40));

        JLDisponibilidadUsuario1.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        JLDisponibilidadUsuario1.setForeground(new java.awt.Color(51, 153, 0));
        JLDisponibilidadUsuario1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(JLDisponibilidadUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 280, 400, 40));

        jLabel14.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(238, 112, 82));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("* Datos obligatorios");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 690, 390, 50));

        jLabel15.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(238, 112, 82));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("*");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 150, 40, 50));

        jLabel16.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(238, 112, 82));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("*");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 220, 40, 50));

        jLabel17.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(238, 112, 82));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("*");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 360, 40, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1589, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Primero preguntamos si todos los requisistos indispensables se cumplen

        if (UserDisponible && txtNombre.getText().length() > 0 && txtPass.getText().length() > 0) {
            tex = new ObtenerTextos();
            
            cajasTexto.add(txtUsuario);
            cajasTexto.add(txtNombre);
            
            cajasTexto.add(txtPass);
            cajasTexto.add(jComboBox1);
            cajasTexto.add(txtCorreo);
            cajasTexto.add(txtTelefono);
            /* 
                run:
class javax.swing.JTextField
class javax.swing.JTextField
class javax.swing.JPasswordField
class javax.swing.JComboBox
class javax.swing.JTextField
class javax.swing.JTextField
             */
            for (int i = 0; i < cajasTexto.size(); i++) {
                if (String.valueOf(cajasTexto.get(i).getClass()).equals("class javax.swing.JTextField")) {
                    tex.add(((JTextField) cajasTexto.get(i)).getText());
                }
                if (String.valueOf(cajasTexto.get(i).getClass()).equals("class javax.swing.JPasswordField")) {
                    tex.add(getPass());
                }
                if (String.valueOf(cajasTexto.get(i).getClass()).equals("class javax.swing.JComboBox")) {
                    int valor = ((JComboBox) cajasTexto.get(i)).getSelectedIndex();
                    if (valor == 0) {
                        tex.add("2");
                    }
                    if (valor == 1) {
                        tex.add("1");
                    }
                }
                
            }
            if (Conec.Select("select usarName from usuarios ;", 1).size() == 0) {
                System.out.println("No hay usuarios");
                
                Conec.insert("insert into usuarios values (?,?,?,?,?,?);", tex.datos, "NO SE PUEDE REGISTAR AL USUARIO");
                OnlyLogin autorizar = new OnlyLogin();
                autorizar.setVisible(true);
            } else {
                //Madamos los datos
                
                login.setSize(305, 550);
                login.setLocationRelativeTo(this);
                JOptionPane.showMessageDialog(this, "Para poder registrarte necesitas \nautorización de un administrador ");
                login.setVisible(true);

                //RsAni
                //cajasTexto.clear();
                //tex.datos.clear(); 
            }
            
        } else {
            //Aqui cambia este mensaje por uno como el que hiciste en el login xd
            JOptionPane.showMessageDialog(this, "Revisa los datos que deben ser obligatorios");
        }

    }//GEN-LAST:event_jButton1ActionPerformed
public void entar(){
    try {
                ArrayList<String> datos = Conec.Select("select usarName,password,id_rol from usuarios where usarName='" + txtUsuario1.getText() + "';", 3);
                if (datos.get(0).equals(txtUsuario1.getText()) && datos.get(1).equals(getPass(txtPassword)) && datos.get(2).equals("1")) {
                    //Registrar
                    Conec.insert("insert into usuarios values (?,?,?,?,?,?);", tex.datos, "No se pudo agregar el Usuario");
                    User.usuario=tex.datos.get(1)+"";
                    Principal.Principal.gestorVentanas.MostrarMenu();
                    this.setVisible(false);
                    login.setVisible(false);
                } else {
                    MensajeError men = new MensajeError();
                    men.Mensaje.setText("Revisa el usuario o contraseña");
                    men.setVisible(true);
                }
            } catch (Exception e) {
                
            }
            
}
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        // TODO add your handling code here:
        if (txtUsuario.getText().length() == 0) {
            JLDisponibilidadUsuario.setText("");
            JLDisponibilidadUsuario1.setText("");
        } else {
            if (Conec.Select("select usarName from usuarios  where usarName='" + txtUsuario.getText() + "';", 1).size() > 0) {
                JLDisponibilidadUsuario.setText("Usuario no disponible");
                UserDisponible = false;
                
                JLDisponibilidadUsuario1.setText("");
            } else {
                JLDisponibilidadUsuario.setText("");
                
                JLDisponibilidadUsuario1.setText("Usuario  disponible");
                UserDisponible = true;
            }
        }
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void VerPaswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VerPaswordMouseClicked
        // TODO add your handling code here:
        clicks++;
        if (clicks % 2 != 0) {
            //Mostrar Contrañse
            VerPasword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bloquear1.png")));
            
            txtPass.setEchoChar((char) 0);
        } else {
            txtPass.setEchoChar(prueba);
            
            VerPasword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bloquear.png")));
        }
    }//GEN-LAST:event_VerPaswordMouseClicked

    private void txtUsuario1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuario1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuario1MouseClicked

    private void txtUsuario1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuario1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuario1MouseExited

    private void txtUsuario1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuario1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10 && txtPassword.getText().length() > 0) {
            //Login();

        }
    }//GEN-LAST:event_txtUsuario1KeyPressed

    private void txtUsuario1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuario1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuario1KeyTyped

    private void txtPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPasswordMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordMouseClicked

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            entar();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        clicks++;
        if (clicks % 2 != 0) {
            //Mostrar Contrañse

            txtPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar(prueba);
            
            setIconImage(new ImageIcon(getClass().getResource("/Imagenes/papeleria.png")).getImage());
        }
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jButtonEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnterActionPerformed
        // TODO add your handling code here:
        //Login();
       entar();
    }//GEN-LAST:event_jButtonEnterActionPerformed

    private void JLInternetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLInternetMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JLInternetMouseClicked

    private void JPIngresoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPIngresoMouseDragged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JPIngresoMouseDragged

    private void JPIngresoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPIngresoMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JPIngresoMouseClicked

    private void JPIngresoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPIngresoMousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JPIngresoMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLDisponibilidadUsuario;
    private javax.swing.JLabel JLDisponibilidadUsuario1;
    private javax.swing.JLabel JLInternet;
    private javax.swing.JPanel JPIngreso;
    private javax.swing.JPanel Panel2;
    private javax.swing.JLabel VerPasword;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonEnter;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JDialog login;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtUsuario1;
    // End of variables declaration//GEN-END:variables
}
