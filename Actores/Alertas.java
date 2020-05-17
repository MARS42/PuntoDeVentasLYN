/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actores;

import static Principal.Conectar.Conec;
import Ventanas.MenuProductos;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author omara
 */
public class Alertas {
    public void GenerarAlertas(){
        //Tenemos q optener los productos con stock bajos 
    Date horaDespertar = new Date(System.currentTimeMillis());
        
        Calendar c = Calendar.getInstance();
        c.setTime(horaDespertar);
        System.out.println(c.get(Calendar.DAY_OF_WEEK));
        // Si la hora es posterior a las 8am se programa la alarma para el dia siguiente
       /*if (c.get(Calendar.HOUR_OF_DAY) >= 31) {
            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
        }*/
        
        c.set(Calendar.HOUR_OF_DAY, 17);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        
        horaDespertar = c.getTime();
        System.out.println(horaDespertar);
        System.out.println(c.get(Calendar.DAY_OF_WEEK));
        // El despertador suena cada 24h (una vez al dia)
        int tiempoRepeticion = 86400000; 
        
        // Programamos el despertador para que "suene" a las 8am todos los dias 
        Timer temporizador = new Timer();
        temporizador.schedule(new Temporizador(), horaDespertar, tiempoRepeticion);
        
    }
    public class Temporizador extends TimerTask{

    @Override
    public void run() {
        Calendar diaActual = Calendar.getInstance();
        
        switch (diaActual.get(Calendar.DAY_OF_WEEK)) {
            case 1: 
              
            case 2: // Durante los dias de diario suena el despertador
            case 3:
            case 4:
            case 5:
            case 6:               
            case 7:
        {
            try {
                new GenerarReportes().ProductosBajos("reportes.pdf");
                Thread t= new Thread(new hiloCorreo());
            t.start();
            } catch (IOException ex) {
                Logger.getLogger(Alertas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
                break;                
        }
    }

}
    public class hiloCorreo implements Runnable{

        @Override
        public void run() {
            new EnviarCorreos().EnviarCorreo("Reporte de los productos. ",
                ""+Conec.Select("select Correo from usuarios where  usarName='"+User.usuario+"';", 1).get(0), "papelerialyn2020@gmail.com", ":::Lapiz:1001:::", "Reporte productos", "reportes.pdf",true);
        }
    
}
}
