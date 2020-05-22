package Ventanas;

import BaseDatos.CiberDB;
import com.sun.awt.AWTUtilities;
import java.awt.Component;

/**
 *
 * @author Robert
 */
public class GestorVentanas
{
    private SplashScrean splashScreen;
    private OnlyLogin login;
    private Login menu;
    //public Salida salida;
    private Ciber ciber;
    private CiberDB ciberdb;
    
    public GestorVentanas()
    {
        MostrarSplash();
    }
    
    public void MostrarSplash()
    {
        splashScreen= new SplashScrean();
        AWTUtilities.setWindowOpaque(splashScreen,false);
        splashScreen.setVisible(true);
        
//        salida = new Salida();
//        salida.setVisible(true);
    }
    public void MostrarMenu()
    {
        menu = new Login();
        menu.setVisible(true);
    }
    
    public void MostrarCiber(boolean mostrar)
    {
        if(ciber == null)
        {
            ciberdb = new CiberDB();
            ciber = new Ciber(ciberdb);
        }
        ciber.setVisible(mostrar);
    }
    
    private void CerrarVentanas()
    {
        menu.CerrarDef();
    }
}
