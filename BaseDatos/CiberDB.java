package BaseDatos;

// Author: Robert

import Ventanas.Ciber;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.Timer;

public class CiberDB
{
    public class Maquina
    {
        private String nombre, marca;
        private Contador myCont;
        public Maquina(String nombre, String marca)
        {
            this.nombre = nombre;
            this.marca = marca;
        }
        
        public String getName(){  return nombre; }
        public String getBrand(){  return marca; }
        public void IniciarConte(int i, Calendar inicio, Calendar fin, boolean libre)
        {
            myCont = new Contador(i, inicio, fin, libre);
        }
        
        public class Contador implements ActionListener
        {
            private int i;
            private int hrs, mins, segs;
            private String t;
            private Calendar transcurso, fin;
            private boolean libre;
            public Contador(int i, Calendar inicio, Calendar fin, boolean libre)
            {
                this.i = i;
                transcurso = inicio;
                this.fin = fin;
                this.libre = libre;
                t = ((hrs + "").length() == 1 ? "0" + hrs + ":" : hrs + ":") +
                    ((mins + "").length() == 1 ? "0" + mins + ":" : mins + ":") +
                    ((segs + "").length() == 1 ? "0" + segs : segs + "");
                Ciber.main.actualizarTiempoTabla(i, t);
                cont.start();
            }
            private final Timer cont = new Timer(1000, this);
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                addSeg();
                transcurso.add(Calendar.SECOND, 1);
                if(transcurso.compareTo(fin) == 1)
                {
                    cont.stop();
                    return;
                }
                t = ((hrs + "").length() == 1 ? "0" + hrs + ":" : hrs + ":") +
                    ((mins + "").length() == 1 ? "0" + mins + ":" : mins + ":") +
                    ((segs + "").length() == 1 ? "0" + segs : segs + "");
                Ciber.main.actualizarTiempoTabla(i, t);
            }
            
            private void addSeg()
            {
                segs++;

                if(segs == 60)
                {
                    mins++;
                    segs = 0;
                    if(mins == 60)
                    {
                        hrs++;
                        mins = 0;
                    }
                }
                if(libre)
                    return;
            }
        }
    }
    
    private ArrayList<Maquina> maquinas = new ArrayList<>();
    
    public void registrarMaquina(String nombre, String marca)
    {
        maquinas.add(new Maquina(nombre, marca));
    }
    
    public Maquina ultimoRegistro()
    {
        return maquinas.get(maquinas.size() - 1);
    }
    
    public void IniciarConteo(String name, int i, Calendar inicio, Calendar fin, boolean libre)
    {
        Maquina m = maquinas.stream().filter(x -> name.equals(x.getName())).findAny().orElse(null);
        m.IniciarConte(i, inicio, fin, libre);
    }
}
