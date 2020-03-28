/*
Ventana de Animacion de Inicio del sistema 
 */
package Ventanas;

import Principal.Conectar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class SplashScrean extends javax.swing.JFrame implements Conectar{
      private Timer time;
    int contador;
    private final int SEGUNDOS=5;
 
    public SplashScrean() {
        initComponents();
        setLocationRelativeTo(this);
        mostrarb();
    }
    //Medoto para mandar al login despues de que termine la carga 
    public  void login(){
         new Login().setVisible(true);
         this.dispose();
    }
    //Comienza el conteo
public void comenzar(){time.start();

 }
     public void mostrarb(){
         //Este metodo muestra la barra en el login
       progreso.setVisible(true);
                     contador=-1;
                       progreso.setValue(0);
                       progreso.setStringPainted(true);
                       time=  new Timer(SEGUNDOS,new SplashScrean.timerListener());
                       comenzar();
  }
class timerListener implements ActionListener{


    public void actionPerformed(ActionEvent ae) {
            contador++;
           progreso.setValue(contador);
           if(progreso.getValue()==100){
               time.stop();
               
         //Mostrar Login
         login();
           }
           
            }
            
        }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progreso = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        progreso.setBackground(new java.awt.Color(255, 153, 0));
        progreso.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        progreso.setForeground(new java.awt.Color(255, 204, 102));
        progreso.setBorderPainted(false);
        progreso.setOpaque(true);
        getContentPane().add(progreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 390, 110));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cargando el sistema ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 360, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lapiz.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 855, 519));

        pack();
    }// </editor-fold>//GEN-END:initComponents

 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar progreso;
    // End of variables declaration//GEN-END:variables
}
