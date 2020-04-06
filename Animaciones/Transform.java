package Animaciones;

//Clase que contiene las transformaciones de un Componente
/**
 *
 * @author Robert
 */
public class Transform 
{
    //Posición
    private int px, py;     //Posición en XY
    public void setPos(int x, int y){ px = x; py = y; }
    public int getX(){ return px; }
    public int getY(){ return py; }
    
    //Escala
    private int sx, sy;     //Escala en XY
    public void setSc(int x, int y){ sx = x; sy = y; }
    public int getXs(){ return sx; }
    public int getYs(){ return sy; }
    
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
