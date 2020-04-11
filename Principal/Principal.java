package Principal;

import Animaciones.Controlador;
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
        new Controlador();
        gestorVentanas = new GestorVentanas();
      
        //salida = new Salida();
        //salida.setVisible(true);
    }
    
}
