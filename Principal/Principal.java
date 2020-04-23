package Principal;

import Animaciones.Controlador;
import BaseDatos.tablas;
import Ventanas.GestorVentanas;

/**
 *
 * @author omara
 */
public class Principal {

    //public static Salida salida;
    public static GestorVentanas gestorVentanas;
 
    public static void main(String[] args) 
    {   
       new Principal().iniciar();
        //salida = new Salida();
        //salida.setVisible(true);
    }
    public void iniciar(){
         
       Thread t1= new Thread(new BaseDatos());
        Thread t2= new Thread(new gestor());
       t1.start();
       t2.start();
    }
    public class gestor implements Runnable{

        @Override
        public void run() {
            System.out.println("Hilo Ventana");
           new Controlador();
        gestorVentanas = new GestorVentanas(); 
        }
        
    }
    public class BaseDatos implements Runnable{
        
        @Override
        public void run() {
            System.out.println("Hilo Base");
          new tablas().verificar();
        }
        
    }
}
