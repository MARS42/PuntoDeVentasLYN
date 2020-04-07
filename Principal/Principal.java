package Principal;

import Animaciones.Controlador;
import BaseDatos.Encriptar;
import Ventanas.CrearCuenta;
import Ventanas.MenuProductos;
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
      
        
    }
    
}
