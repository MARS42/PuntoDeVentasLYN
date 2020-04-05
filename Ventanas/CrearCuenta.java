package Ventanas;

import Actores.ObtenerTextos;
import Actores.TextPrompt;
import Principal.Conectar;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author omara
 */
public class CrearCuenta extends javax.swing.JFrame implements Conectar {
    //Variables para saber si el usuario esta disponible 
     boolean UserDisponible;
   
    
    int k;
    ArrayList<Object> cajasTexto = new ArrayList();
    ArrayList<String> place = new ArrayList();

    public CrearCuenta(int k) {
        initComponents();
        this.k = k;
        setLocationRelativeTo(this);
        llenarCajas();
        llenarTextos();

        Thread t2 = new Thread(new placeHolder());

        t2.start();
        setExtendedState(this.MAXIMIZED_BOTH);
        Panel2.setSize(830, 2000);
        setVisible(true);

    }

   

    public void llenarCajas() {
        cajasTexto.add(txtNombre);
        cajasTexto.add(txtUsuario);

        cajasTexto.add(txtCorreo);
        cajasTexto.add(txtTelefono);

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
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        JLDisponibilidadUsuario = new javax.swing.JLabel();
        JLDisponibilidadUsuario1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

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
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 360, 410, 50));

        jComboBox1.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Administrador" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 460, 410, 50));

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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 750, 400, 50));

        jLabel4.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel4.setText("Crea una cuenta");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 80, 210, 50));

        jLabel5.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jLabel5.setText("O bien");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 80, 60, 50));

        txtCorreo.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 530, 410, 50));

        txtTelefono.setFont(new java.awt.Font("Corbel", 0, 20)); // NOI18N
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 600, 410, 50));

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

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bloquear.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 360, 50, 50));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/equipo.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 460, -1, 60));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/gmail.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 530, -1, 50));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ui.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 600, -1, 50));

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
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 690, 390, 50));

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
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 360, 40, 50));

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

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Primero preguntamos si todos los requisistos indispensables se cumplen
        
        if(UserDisponible && txtNombre.getText().length()>0 && txtPass.getText().length()>0){
              ObtenerTextos tex = new ObtenerTextos();
        tex.add("");//usuario
        tex.add("");//Nombre

        tex.add("");//contraseña
        tex.add(2);//rol
        tex.add("");//Correo
        tex.add("");//Telfono
        
        
        }else{
            //Aqui cambia este mensaje por uno como el que hiciste en el login xd
            JOptionPane.showMessageDialog(this, "Revisa los datos que deben ser obligatorios");
        }
      

    }//GEN-LAST:event_jButton1ActionPerformed

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
                UserDisponible=false;

                JLDisponibilidadUsuario1.setText("");
            } else {
                JLDisponibilidadUsuario.setText("");

                JLDisponibilidadUsuario1.setText("Usuario  disponible");
                UserDisponible=true;
            }
        }

    }//GEN-LAST:event_txtUsuarioKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLDisponibilidadUsuario;
    private javax.swing.JLabel JLDisponibilidadUsuario1;
    private javax.swing.JPanel Panel2;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
