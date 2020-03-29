package Animaciones;

import Ventanas.Login;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;
import javax.swing.Timer;

/**
 *
 * @author Robert
 */
public class Animacion implements ActionListener
{
    private final Timer timer;  
    private float lerp = 0f;;
    public float getLerp(){ return lerp; }
    private final Transform origin_transform;                               //Estado original del componente
    private final Transform from_transform;                                 //Estado inicial del componente
    private final Transform to_transform;                                   //A donde se moverá y escalará el componente
    private Transform current_transform;                                    //Estado de la transición 
    private final Component objetivo;
    private final Callable<Void> action;                                    //Método a llamar al terminar animación
    private Callable<Void> updateAction;                                    //Método que se llamará a cada actualización 
    public void setUpdateAction(Callable<Void> updateAction){ this.updateAction = updateAction; }
    
    /***
     * Inicia una animación que dará como resultado un desplazamiento o escalonamiento
     * @param o Componente al que se le aplicará la animación
     * @param newpx Valor objetivo de posición en eje X
     * @param newpy Valor objetivo de posición en eje Y
     * @param newsx Valor objetivo de escala en eje X
     * @param newsy Valor objetivo de escala en eje Y
     * @param action Método que se llamará al final de la animación
     */
    public Animacion(Component o, int newpx, int newpy, int newsx, int newsy, Callable<Void> action)
    {
        this.objetivo = o;
        this.action = action;
        origin_transform = new Transform(o.getX(), o.getY(), o.getWidth(), o.getHeight());
        from_transform = new Transform(origin_transform.px, origin_transform.py, origin_transform.sx, origin_transform.sy);
        to_transform = new Transform(newpx, newpy, newsx, newsy);
        current_transform = new Transform(origin_transform.px, origin_transform.py, origin_transform.sx, origin_transform.sy);
        timer = new Timer(Controlador.main.ratioAnimacion, this);
        timer.setCoalesce(true);
    }
    
    public void Iniciar(){
        from_transform.px = origin_transform.px;
        from_transform.py = origin_transform.py;
        from_transform.sx = origin_transform.sx;
        from_transform.sy = origin_transform.sy;
        lerp = 0f;
        timer.start();
    }
    public void Reinciar(){
        from_transform.px = origin_transform.px;
        from_transform.py = origin_transform.py;
        from_transform.sx = origin_transform.sx;
        from_transform.sy = origin_transform.sy;
        lerp = 0f;
        timer.restart(); 
    }
    public void Detener(){ timer.stop(); }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Animar();
    }
    
    private void Animar()
    {
        try {
            if(lerp <= 1f)
            {
                current_transform = Controlador.main.LerpTransform(from_transform, to_transform, lerp, current_transform);
                objetivo.setLocation(current_transform.px, current_transform.py);
                objetivo.setSize(current_transform.sx, current_transform.sy);
                lerp += Controlador.main.velocidadAnim;
                Login.ins.pack();
                updateAction.call();
            }
            else
            {
                action.call();
                timer.stop();
            }
        }
        catch (Exception ex) { }
    }
}
