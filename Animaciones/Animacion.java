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
    private final Timer timer;  
    private float lerp = 0f;;
    private final Transform origin_transform;                       //Estado original del componente
    private Transform from_transform;                               //Estado inicial del componente
    private Transform to_transform;                                 //A donde se moverá y escalará el componente
    private Transform current_transform;//Estado de la transición 
    private Component objetivo;
    
    /***
     * Inicia una animación que dará como resultado un desplazamiento o escalonamiento
     * @param o Componente al que se le aplicará la animación
     * @param newpx Valor objetivo de posición en eje X
     * @param newpy Valor objetivo de posición en eje Y
     * @param newsx Valor objetivo de escala en eje X
     * @param newsy Valor objetivo de escala en eje Y
     */
    public Animacion(Component o, int newpx, int newpy, int newsx, int newsy)
    {
        this.objetivo = o;
        origin_transform = new Transform(o.getLocation().x, o.getLocation().y, o.getWidth(), o.getHeight());
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
        if(lerp <= 1f)
        {
            current_transform = Controlador.main.LerpTransform(from_transform, to_transform, lerp, current_transform);
            objetivo.setLocation(current_transform.px, current_transform.py);
            objetivo.setSize(current_transform.sx, current_transform.sy);
            lerp += Controlador.main.velocidadAnim;
        }
        else
            timer.stop();
    }
}
