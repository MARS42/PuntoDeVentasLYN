package Animaciones;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Robert
 */
public class Animacion implements ActionListener
{
    private final Timer animador;  
    private float lerp = 0f;
    private float start = 0f, startt = 0f;
    private float end = 0f, endt = 0f;
    private final Controlador.TiposAnimacion tanim;   
    private Component objetivo;
    
    public Animacion(Component objetivo, Controlador.TiposAnimacion tanim)
    {
        this.objetivo = objetivo;
        this.tanim = tanim;
        switch(tanim)
        {
            case DeslizarIzquierda:
                this.start = objetivo.getLocation().x;
                this.end = -objetivo.getWidth();
                break;
            case DeslizarDerecha:
                this.start = objetivo.getLocation().x;
                this.end = objetivo.getWidth();
                break;
            case DeslizarAbajo:
                this.start = objetivo.getLocation().y;
                this.end = objetivo.getHeight();
                break;
            case DeslizarArriba:
                this.start = objetivo.getLocation().y;
                this.end = -objetivo.getHeight();
            break;
        }
        startt = start;
        endt = end;
        animador = new Timer(Controlador.main.ratioAnimacion, this);
        animador.setCoalesce(true);
    }
    
    public void Iniciar(){
        start = startt;
        end = endt;
        lerp = 0f;
        animador.start();
    }
    public void Reinciar(){
        start = startt;
        end = endt;
        lerp = 0f;
        animador.restart(); 
    }
    public void Detener(){ animador.stop(); }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(lerp <= 1f)
        {
            switch(tanim)
            {
                case DeslizarIzquierda:
                case DeslizarDerecha:
                    objetivo.setLocation((int)Controlador.main.Lerp(start, end, 
                            Controlador.main.animacion_cubica(lerp)), objetivo.getLocation().y);
                    break;
                case DeslizarAbajo:
                case DeslizarArriba:
                    objetivo.setLocation(objetivo.getLocation().x, (int)Controlador.main.Lerp(start, end, 
                            Controlador.main.animacion_cubica(lerp)));
                    break;
            }
            lerp += Controlador.main.velocidadAnim;
        }
        else
            animador.stop();
    }
}
