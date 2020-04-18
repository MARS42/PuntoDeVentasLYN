/*
Ventana de Animacion de Inicio del sistema 
 */
package Ventanas;

import BaseDatos.Query;
import Principal.Conectar;

import java.util.concurrent.Callable;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public class SplashScrean extends javax.swing.JFrame implements Conectar{
      private Timer time;
    int contador;
    private final int SEGUNDOS=5;
    Callable<Void> his = () -> hideSplash();
    static Query sql = new Query();
 
    public SplashScrean() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/papeleria.png")).getImage());
        setLocationRelativeTo(this);
        //Animaciones.Animacion a = new Animaciones.Animacion(this, getX(), getY(), getWidth(), getHeight(), null, 3);
        //a.setUpdateAction(() -> Opacidad(a.getLerp(), false));
        //a.Iniciar();
        setOpacity(0);
        mostrarb();
        a.setTo(getX(), getY()*2, getWidth(), getHeight());
        a.setEndAction(his);
        a.setUpdateAction(() -> Opacidad(a.getLerp(), true));
    }
    Animaciones.Animacion a = new Animaciones.Animacion(this, 0, 100, 100, 100, 
                 this::hideSplash, 3);
    //Medoto para mandar al login despues de que termine la carga 
    public  void login(){
        
        //new Login().setVisible(true);
        new OnlyLogin().setVisible(true);
         //this.dispose();
        a.Reinciar();
    }
    
    Void hideSplash()
    {
        setOpacity(0);
        dispose();
        return null;
    }
    
    public Void Opacidad(float lerp, boolean min)
    {
        if(min)
            setOpacity(1 - lerp);
        else
            setOpacity(lerp);
        return null;
    }
        
    //Comienza el conteo
    public void comenzar(){time.start();}
    public void mostrarb(){
         //Este metodo muestra la barra en el login
        //progreso.setVisible(true);
        contador=-1;
        progreso.setValue(0);
        progreso.setStringPainted(true);
        time=  new Timer(SEGUNDOS,(e) -> 
        {
            contador+= 5;
            contador = contador > 100 ? 100 : contador;
            setOpacity(contador / 100f);
            progreso.setValue(contador);
            if(progreso.getValue()==100){
                time.stop();      
                //Mostrar Login
                login();
            }
        });
        comenzar();
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
