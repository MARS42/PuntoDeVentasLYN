package Principal;

import Animaciones.Controlador;
import BaseDatos.Encriptar;
import Ventanas.SplashScrean;
import com.sun.awt.AWTUtilities;

/**
 *
 * @author omara
 */
public class Principal {

 
    public static void main(String[] args) {
        new Controlador();
        SplashScrean ventana= new SplashScrean();
        AWTUtilities.setWindowOpaque(ventana,false);
        ventana.setVisible(true);
        System.out.println(new Encriptar("12345").Encrip());
    }
    
}
