/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Actores.User;
import Animaciones.*;
import AppPackage.AnimationClass;
import BaseDatos.Encriptar;
import Principal.Conectar;
import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Robert
 */
public class OnlyLogin extends javax.swing.JFrame implements Conectar {

    ArrayList<String> datos;
    ArrayList<Object> datosUsuario;

    Salida salida;

    Animacion abrir, cerrar, minimizar;
    int clicks=0; char prueba;
    public OnlyLogin(ArrayList<Object> datosUsuario) {
        initComponents();
        Init();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/papeleria.png")).getImage());
        this.datosUsuario = datosUsuario;
        JOptionPane.showMessageDialog(null, "Necesitas autorizacion para poder registrarte \n"
                + "pidele a algun admin que te de permiso para poder continuar\n creando tu cuenta");
        salida = new Salida(this, "¿Seguro?", () -> {
            unDarken();
            return null;
        }, () -> {
            Darken();
            return null;
        }, () -> cerrarDef());
        setLocation(0, 0);
        abrir = new Animacion(this, getX(), getY(), getWidth(), getHeight(), null, 3);
        prueba = txtPassword.getEchoChar();
    }

    public OnlyLogin() {
        initComponents();
        Init();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/papeleria.png")).getImage());

        setOpacity(0);
        setLocationRelativeTo(null);
        abrir = new Animacion(this, getX(), getY(), getWidth(), getHeight(), () -> Opacidad(1), 4);
        abrir.setUpdateAction(() -> Opacidad(abrir.getLerp(), false));
        cerrar = new Animacion(this, getX(), 0, getWidth(), getHeight(), () -> {
            System.exit(0);
            return null;
        }, 4);
        minimizar = new Animacion(this, getX(), 0, getWidth(), getHeight(), () -> Opacidad(0), 4);
        cerrar.setUpdateAction(() -> Opacidad(cerrar.getLerp(), true));
        minimizar.setUpdateAction(() -> Opacidad(cerrar.getLerp(), true));
        setLocation(getX(), 0);
        abrir.Iniciar();

        salida = new Salida(this, "¿Seguro?", () -> {
            unDarken();
            return null;
        }, () -> {
            Darken();
            return null;
        },
                () -> {
                    unDarken();
                    cerrarDef();
                    return null;
                });
    }

    private void Init() {
        //setPreferredSize(new Dimension(300, 400));
        setLocationRelativeTo(null);
    }

    boolean dark = false;
    GaussianBlur gb;

    public void Darken() {
        //setEnabled(false);
        dark = true;
        repaint();
    }

    public void unDarken() {
        //setEnabled(true);
        dark = false;
        gb = null;
        repaint();
    }

    BufferedImage ss() {
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
    public void paint(Graphics g) {
        super.paint(g);
        if (dark) {
            if (gb == null) {
                gb = new GaussianBlur(ss(), 12);
                //g.setColor(new Color(0, 0, 0, 140));
                //g.fillRect(0, 0, getWidth(), getHeight());
                gb.imagenBlur(getGraphics(), this);
            }
        }
    }

    public Void Opacidad(float lerp, boolean min) {
        if (min) {
            setOpacity(1 - lerp);
        } else {
            setOpacity(lerp);
        }
        return null;
    }

    public Void Opacidad(float val) {
        setOpacity(val);
        if (val == 0) {
            dispose();
        }
        return null;
    }

    Void cerrarDef() {
        cerrar.Iniciar();
        //System.exit(0);
        return null;
    }

    private String getUsuario() {
        return txtUsuario.getText();
    }

    private String getPass() {
        return new Encriptar(txtPassword.getText().toString()).Encrip();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jButtonEnter = new javax.swing.JButton();
        JLInternet = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(300, 540));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(300, 540));
        setResizable(false);
        setSize(new java.awt.Dimension(300, 540));
        getContentPane().setLayout(new java.awt.CardLayout());

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
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
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

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Multiply_32px.png"))); // NOI18N
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        JPIngreso.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        getContentPane().add(JPIngreso, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioMouseClicked

    private void txtUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioMouseExited

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10 && txtPassword.getText().length() > 0) {
            //Login();

        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPasswordMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordMouseClicked

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            try {
                datos = Conec.Select("select usarName,password from usuarios where usarName='" + getUsuario() + "';", 2);
                if (datos.get(0).equals(getUsuario()) && datos.get(1).equals(getPass())) {
                    //Registrar
                    User.usuario=datos.get(0);
                    jButtonEnter.setEnabled(false);
                    System.out.println("si es usuario ");
                    Principal.Principal.gestorVentanas.MostrarMenu();
                    minimizar.Reinciar();
                    //Conec.insert("insert into usuarios values (?,?,?,?,?,?);", datosUsuario, "No se pudo agregar el Usuario");
                } else {
                    MensajeError men = new MensajeError();
                    men.Mensaje.setText("Revisa el usuario o contraseña");
                    men.setVisible(true);
                }
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        AnimationClass internet = new AnimationClass();
        internet.jLabelXRight(-40, 10, 10, 5, JLInternet);

        //<---
        AnimationClass internett = new AnimationClass();
        internett.jLabelXLeft(10, -40, 10, 5, JLInternet);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jButtonEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnterActionPerformed
        // TODO add your handling code here:
        //Login();
        try {
            datos = Conec.Select("select usarName,password from usuarios where usarName='" + getUsuario() + "';", 2);
            if (datos.get(0).equals(getUsuario()) && datos.get(1).equals(getPass())) {
                //Registrar
                User.usuario=datos.get(0);
                jButtonEnter.setEnabled(false);
                System.out.println("si es usuario ");
                Principal.Principal.gestorVentanas.MostrarMenu();
                minimizar.Reinciar();
                System.out.println("");
                //Conec.insert("insert into usuarios values (?,?,?,?,?,?);", datosUsuario, "No se pudo agregar el Usuario");
            } else {
                MensajeError men = new MensajeError();
                men.Mensaje.setText("Revisa el usuario o contraseña");
                men.setVisible(true);
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButtonEnterActionPerformed


    private void JLInternetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLInternetMouseClicked
        // TODO add your handling code here:
        new CrearCuenta().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JLInternetMouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
        //        int dialog=JOptionPane.YES_NO_OPTION;
        //        int result=JOptionPane.showConfirmDialog(null,"¿Desea salir del Login?","Salir",dialog);
        //        if(result==0){
        //            //System.exit(0);
        //            Controlador.main.AccionesVentana(this, this::Cerrar, 3);
        //        }
        //Principal.Principal.gestorVentanas.MostrarSalida(this, "¿Seguro?");
        salida.Mostrar();
        //System.exit(0);
    }//GEN-LAST:event_jLabel19MouseClicked

    private void JPIngresoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPIngresoMouseDragged
        // TODO add your handling code here:
        setLocation(evt.getXOnScreen() - tx, evt.getYOnScreen() - ty);
    }//GEN-LAST:event_JPIngresoMouseDragged

    private int tx, ty;
    private void JPIngresoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPIngresoMouseClicked
        // TODO add your handling code here:
        tx = evt.getX();
        ty = evt.getY();
    }//GEN-LAST:event_JPIngresoMouseClicked

    private void JPIngresoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPIngresoMousePressed
        // TODO add your handling code here:
        tx = evt.getX();
        ty = evt.getY();
    }//GEN-LAST:event_JPIngresoMousePressed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        clicks++;
        if (clicks % 2 != 0) {
            //Mostrar Contrañse
          

            txtPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar(prueba);

           setIconImage(new ImageIcon(getClass().getResource("/Imagenes/papeleria.png")).getImage());
        }
    }//GEN-LAST:event_jLabel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLInternet;
    private javax.swing.JPanel JPIngreso;
    private javax.swing.JButton jButtonEnter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
