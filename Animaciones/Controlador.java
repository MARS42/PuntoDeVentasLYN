package Animaciones;

import java.awt.Component;

/**
 *
 * @author Robert
 */
public class Controlador 
{
    public static Controlador main;
    public Controlador() { main = this; }
    
    public enum TiposAnimacion
    {
        DeslizarIzquierda,
        DeslizarDerecha,
        DeslizarArriba,
        DeslizarAbajo
    }
    
    public float velocidadAnim = 0.05f;   
    public int ratioAnimacion = 8;
    
    //Animaciones
    public Animacion panelIngreso;
    
    public void AnimacionJPIngreso(Component component)
    {
        if(panelIngreso == null)
        {
            panelIngreso = new Animacion(component, TiposAnimacion.DeslizarIzquierda);
            panelIngreso.Iniciar();
        }
        else
            panelIngreso.Reinciar();
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
}
