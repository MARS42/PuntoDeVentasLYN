/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Ventanas.SplashScrean;
import com.sun.awt.AWTUtilities;

/**
 *
 * @author omara
 */
public class Principal {

 
    public static void main(String[] args) {
        SplashScrean ventana= new SplashScrean();
        AWTUtilities.setWindowOpaque(ventana,false);
        ventana.setVisible(true);
    }
    
}
