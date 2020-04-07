package Animaciones;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 *
 * @author Robert
 */
public class GaussianBlur 
{
    private BufferedImage imagen = null;
    private BufferedImage imagenA;
    private int radio = 10;
    private boolean fasterBlur = true;
    
    public GaussianBlur(BufferedImage imagenA, int radio)
    {
        this.imagenA = imagenA;
        this.radio = radio;
    }
    
    public Dimension getDimension()
    {
        return new Dimension(imagenA.getWidth(), imagenA.getHeight());
    }
    
    public void imagenBlur(Graphics g, Component c)
    {
        if(imagen == null)
        {
            imagen = new BufferedImage(imagenA.getWidth() + 2 * radio, imagenA.getHeight() + 2 * radio,
                    BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D g2 = imagen.createGraphics();
            g2.drawImage(imagenA, radio, radio, null);
            g2.dispose();
//            
            //long start = System.nanoTime();
            
            if(fasterBlur)
            {
                imagen = cambiarAncho(imagen, imagen.getWidth() / 2);
                imagen = getGaussianBlurFilter(radio / 2, true).filter(imagen, null);
                imagen = getGaussianBlurFilter(radio / 2, false).filter(imagen, null);
                imagen = cambiarAncho(imagen, imagen.getWidth() * 2);
            }
            else
            {
                imagen = getGaussianBlurFilter(radio, true).filter(imagen, null);
                imagen = getGaussianBlurFilter(radio, false).filter(imagen, null);
            }
            
            //long delay = System.nanoTime();
            //System.out.println("time = " + (delay / 1000.0f / 1000.0f) + "ms for radius = " + radio);
            
        }
        
        int x = (c.getWidth() - imagen.getWidth()) / 2;
        int y = (c.getHeight() - imagen.getHeight()) / 2;
        g.drawImage(imagen, x, y, null);
    }
    
    public static BufferedImage cambiarAncho(BufferedImage imagen, int w)
    {
        float ratio = (float) imagen.getWidth() / (float) imagen.getHeight();
        int height = (int) (w / ratio);
        
        BufferedImage temp = new BufferedImage(w, height,
                imagen.getType());
        Graphics2D g2 = temp.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(imagen, 0, 0, temp.getWidth(), temp.getHeight(), null);
        g2.dispose();

        return temp;
    }
    
    public static ConvolveOp getGaussianBlurFilter(int radio, boolean horizontal) {
        if (radio < 1) {
            throw new IllegalArgumentException("Radius must be >= 1");
        }
        
        int tam = radio * 2 + 1;
        float[] data = new float[tam];
        
        float sigma = radio / 3.0f;
        float twoSigmaSquare = 2.0f * sigma * sigma;
        float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
        float total = 0.0f;
        
        for (int i = -radio; i <= radio; i++) {
            float distance = i * i;
            int index = i + radio;
            data[index] = (float) Math.exp(-distance / twoSigmaSquare) / sigmaRoot;
            total += data[index];
        }
        
        for (int i = 0; i < data.length; i++) {
            data[i] /= total;
        }        
        
        Kernel kernel = null;
        if (horizontal) {
            kernel = new Kernel(tam, 1, data);
        } else {
            kernel = new Kernel(1, tam, data);
        }
        return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
    }
}
