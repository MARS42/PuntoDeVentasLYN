package Animaciones;

import Ventanas.Login;
import java.awt.Component;
import java.util.concurrent.Callable;

/**
 *
 * @author Robert
 */
public class Controlador 
{
    public static Controlador main;
    public Controlador() { main = this; }
    
    public float velocidadAnim = 0.05f;   
    public int ratioAnimacion = 8;
    
    //Animaciones
    public Animacion minimizar, maximizar, cerrar;
    public Animacion panelIngreso;
    public Animacion panelMenu;
    
    public void AccionesVentana(Component f, Callable<Void> action, int opcion)
    {
        switch(opcion)
        {
            case 1://Minimizar
                if(minimizar == null)
                {
                    minimizar = new Animacion(f, f.getX(), f.getY() + f.getHeight() / 2, f.getWidth(), f.getHeight(), action);
                    minimizar.setUpdateAction(() -> Login.ins.Opacidad(minimizar.getLerp(), true));
                    minimizar.Iniciar();
                }
                else
                    minimizar.Reinciar();
                break;
            case 2://Maximizar
                if(maximizar == null)
                {
                    maximizar = new Animacion(f, f.getX(), f.getY() - f.getHeight() / 2, f.getWidth(), f.getHeight(), action);
                    maximizar.setUpdateAction(() -> Login.ins.Opacidad(maximizar.getLerp(), false));
                    maximizar.Iniciar();
                }
                else
                    maximizar.Reinciar();
                break;
            case 3://Cerrar
                if(cerrar == null)
                {
                    cerrar = new Animacion(f, f.getX(), f.getY(), f.getWidth(), f.getHeight(), action);
                    cerrar.setUpdateAction(() -> Login.ins.Opacidad(cerrar.getLerp(), true));
                    cerrar.Iniciar();
                }
                else
                    cerrar.Reinciar();
                break;
        }
    }
    
    public void AnimacionJPIngreso(Component c, Component c2)
    {
        if(panelIngreso == null)
        {
            panelIngreso = new Animacion(c, -c.getWidth(), c.getY(), c.getWidth(), c.getHeight(), null);
            panelMenu = new Animacion(c2, c2.getX() - c.getWidth(), c2.getY(), c2.getWidth() + c.getWidth(), c2.getHeight(), null);
            
            panelIngreso.Iniciar();
            panelMenu.Iniciar();
        }
        else
        {
            panelIngreso.Reinciar();
            panelMenu.Reinciar();
        }
    }
    
    //TIPOS ANIMACIÓN
    float animacion_cubica(float x)
    {
        return x * x *(3f - (2f * x));
    }
    float animacion_cuadrada(float x)
    {
        return x * x;
    }
    
    //INTERPOLACIÓN
    float Lerp(float a, float b, float x)
    {
        return a + (x * (b - a));
    }
    //INTERPOLACIÓN DE TRANSFORM
    Transform LerpTransform(Transform a, Transform b, float t, Transform to)
    {
        if(to == null)
            return new Transform((int)Lerp(a.px, b.px, t), (int)Lerp(a.py, b.py, t), 
                    (int)Lerp(a.sx, b.sx, t), (int)Lerp(a.sy, b.sy, t));
        else
        {
            t = animacion_cubica(t);
            to.px = (int)Lerp(a.px, b.px, t);
            to.py = (int)Lerp(a.py, b.py, t);
            to.sx = (int)Lerp(a.sx, b.sx, t);
            to.sy = (int)Lerp(a.sy, b.sy, t);
            return to;
        }
    }
}
