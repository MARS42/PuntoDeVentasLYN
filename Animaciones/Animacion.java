package Animaciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Robert
 */
public class Animacion implements ActionListener
{
    private Timer animador;
    private int ratio = 50;
    
    public Animacion(int ratio)
    {
        animador = new Timer(ratio, this);
        animador.start();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //TODO
    }
    
}
