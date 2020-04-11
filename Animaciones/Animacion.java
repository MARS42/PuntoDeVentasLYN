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
    private final Transform from_transform;                                 //Estado inicial del componente
    private final Transform to_transform;                                   //A donde se moverá y escalará el componente
    public void setTo(int x, int y, int sx, int sy){ to_transform.setPos(x, y); to_transform.setSc(sx, sy);}
    private Transform current_transform;                                    //Estado de la transición 
    private final Component objetivo;
    private Callable<Void> action;                                    //Método a llamar al terminar animación
    public void setEndAction(Callable<Void> action){ this.action = action; }
    private Callable<Void> updateAction;                                    //Método que se llamará a cada actualización 
    public void setUpdateAction(Callable<Void> updateAction){ this.updateAction = updateAction; }
    /***
     * Define que curva usará para definir la suavidad de la animación\n
     * 1. Cuadrada\n
     * 2. Cúbica\n
     * 3. Cúbica suave\n
     * 4. Raíz Cuadrada
     */
    private final int curve;
    public int getCurveType(){ return curve; }
    
    /***
     * Inicia una animación que dará como resultado un desplazamiento o escalonamiento
     * @param o Componente al que se le aplicará la animación
     * @param newpx Valor objetivo de posición en eje X
     * @param newpy Valor objetivo de posición en eje Y
     * @param newsx Valor objetivo de escala en eje X
     * @param newsy Valor objetivo de escala en eje Y
     * @param action Método que se llamará al final de la animación
     * @param curve Define que curva usará para definir la suavidad de la animación
     */
    public Animacion(Component o, int newpx, int newpy, int newsx, int newsy, Callable<Void> action, int curve)
    {
        this.objetivo = o;
        this.action = action;
        this.curve = curve;
        from_transform = new Transform(o.getX(), o.getY(), o.getWidth(), o.getHeight());
        to_transform = new Transform(newpx, newpy, newsx, newsy);
        current_transform = new Transform(from_transform.getX(), from_transform.getY(),
                from_transform.getXs(), from_transform.getYs());
        timer = new Timer(Controlador.main.ratioAnimacion, this);
        timer.setCoalesce(true);
    }
    
    public void Iniciar(){
        from_transform.setPos(objetivo.getX(), objetivo.getY());
        from_transform.setSc(objetivo.getWidth(), objetivo.getHeight());
        lerp = 0f;
        timer.start();
    }
    public void Reinciar(){
        from_transform.setPos(objetivo.getX(), objetivo.getY());
        from_transform.setSc(objetivo.getWidth(), objetivo.getHeight());
        lerp = 0f;
        timer.restart(); 
    }
    public void Detener(){ timer.stop(); }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Animar();
    }
    Dimension d = new Dimension();
    private void Animar()
    {
        if(lerp <= 1f)
        {
            current_transform = Controlador.main.LerpTransform(from_transform, to_transform, lerp, current_transform, curve);
            objetivo.setLocation(current_transform.getX(), current_transform.getY());
            d.setSize(current_transform.getXs(), current_transform.getYs());
            objetivo.setPreferredSize(d);
            try{Login.ins.pack();}catch(Exception e){}
            try{updateAction.call();}catch(Exception e){}
            //objetivo.setSize(current_transform.getXs(), current_transform.getYs());
            lerp += Controlador.main.velocidadAnim;
        }
        else
        {
            try{action.call();}catch(Exception e){}
            timer.stop();
        }
    }
}
