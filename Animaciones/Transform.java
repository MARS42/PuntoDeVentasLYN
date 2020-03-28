package Animaciones;

//Clase que contiene las transformaciones de un Componente
/**
 *
 * @author Robert
 */
public class Transform 
{
    //Posición
    int px, py;     //Posición en XY
    
    //Escala
    int sx, sy;     //Escala en XY
    
    /**
     * Inicia un Transform que contendrá las transformaciones de un componente
     * @param px Posición en eje X
     * @param py Posición en eje Y
     * @param sx Escala en eje X
     * @param sy Escala en eje Y
     */
    public Transform(int px, int py, int sx, int sy)
    {
        this.px = px;
        this.py = py;
        
        this.sx = sx;
        this.sy = sy;
    }
}
