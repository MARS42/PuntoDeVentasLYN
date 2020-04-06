package Animaciones;

import Ventanas.Login;
import java.awt.Component;
import java.util.concurrent.Callable;
import javax.swing.GroupLayout;

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
    public Animacion minimizar, maximizar, cerrar, abrir, grande, pequena;
    public Animacion panelIngreso;
    public Animacion panelMenu;
    public Animacion abrirSalida, cerrarSalida;
    
    public void ResetMinMax(){ minimizar = null; maximizar = null; }
    public void AccionesVentana(Component f, Callable<Void> action, int opcion)
    {
        switch(opcion)
        {
            case 1://Minimizar
                if(minimizar == null)
                {
                    minimizar = new Animacion(f, f.getX(), f.getY() + f.getHeight() / 2, f.getWidth(), f.getHeight(), action, 3);
                    minimizar.setUpdateAction(() -> Login.ins.Opacidad(minimizar.getLerp(), true));
                    minimizar.Iniciar();
                }
                else
                    minimizar.Reinciar();
                break;
            case 2://Maximizar
                if(maximizar == null)
                {
                    maximizar = new Animacion(f, f.getX(), f.getY() - f.getHeight() / 2, f.getWidth(), f.getHeight(), action, 3);
                    maximizar.setUpdateAction(() -> Login.ins.Opacidad(maximizar.getLerp(), false));
                    maximizar.Iniciar();
                }
                else
                    maximizar.Reinciar();
                break;
            case 3://Cerrar
                if(cerrar == null)
                {
                    cerrar = new Animacion(f, f.getX(), f.getY(), f.getWidth(), f.getHeight(), action, 4);
                    cerrar.setUpdateAction(() -> Login.ins.Opacidad(cerrar.getLerp(), true));
                    cerrar.Iniciar();
                }
                else
                    cerrar.Reinciar();
                break;
            case 4://Abrir
                if(abrir == null)
                {
                    abrir = new Animacion(f, f.getX(), f.getY(), f.getWidth(), f.getHeight(), action, 3);
                    abrir.setUpdateAction(() -> Login.ins.Opacidad(abrir.getLerp(), false));
                    abrir.Iniciar();
                }
                else
                    abrir.Reinciar();
                break;
            case 5://Cerrar Salida
                if(cerrarSalida == null)
                {
                    cerrarSalida = new Animacion(f, f.getX()+200, f.getY()-150, f.getWidth(), f.getHeight(), action, 3);
                    cerrarSalida.setUpdateAction(() -> Login.ins.salida.Opacidad(cerrarSalida.getLerp(), true));
                    cerrarSalida.Iniciar();
                }
                else
                    cerrarSalida.Reinciar();
                break;
            case 6://Abrir Salida
                if(abrirSalida == null)
                {
                    abrirSalida = new Animacion(f, f.getX(), f.getY(), f.getWidth(), f.getHeight(), action, 3);
                    abrirSalida.setUpdateAction(() -> Login.ins.salida.Opacidad(abrirSalida.getLerp(), false));
                    abrirSalida.Iniciar();
                }
                else
                    abrirSalida.Reinciar();
                break;
        }
    }
    
    public void AnimacionJPIngreso(Component c, Component c2)
    {
        if(panelIngreso == null)
        {
            panelIngreso = new Animacion(c, -c.getWidth(), c.getY(), c.getWidth(), c.getHeight(), null, 3);
            panelMenu = new Animacion(c2, c2.getX() - c.getWidth(), c2.getY(), c2.getWidth() + c.getWidth(), c2.getHeight(), null, 3);
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
        return x * x * x;
    }
    float animacion_cubicaMod(float x)
    {
        return x * x *(3f - (2f * x));
    }
    float animacion_cuadrada(float x)
    {
        return x * x;
    }
    float animacion_sqrt(float x)
    {
        return (float)Math.sqrt(x);
    }
    
    //INTERPOLACIÓN
    float Lerp(float a, float b, float x)
    {
        return a + (x * (b - a));
    }
    //INTERPOLACIÓN DE TRANSFORM
    Transform LerpTransform(Transform a, Transform b, float t, Transform to, int animType)
    {
        if(to == null)
            return new Transform((int)Lerp(a.getX(), b.getX(), t), (int)Lerp(a.getY(), b.getY(), t), 
                    (int)Lerp(a.getXs(), b.getXs(), t), (int)Lerp(a.getYs(), b.getYs(), t));
        else
        {
            switch(animType)
            {
                case 1:
                    t = animacion_cuadrada(t);
                    break;
                case 2:
                    t = animacion_cubica(t);
                    break;
                case 3:
                    t = animacion_cubicaMod(t);
                    break;
                case 4:
                    t = animacion_sqrt(t);
                    break;
            }
            to.setPos(((int)Lerp(a.getX(), b.getX(), t)), (int)Lerp(a.getY(), b.getY(), t));
            to.setSc(((int)Lerp(a.getXs(), b.getXs(), t)), (int)Lerp(a.getYs(), b.getYs(), t));
            return to;
        }
    }
}
