/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actores;

import java.util.Calendar;

/**
 *
 * @author omara
 */
public class GenerarFecha {

    public String getFecha() {
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        return año + "-" + mes + "-" + dia;
    }
    public int getMes(){
        Calendar fecha = Calendar.getInstance();
       return fecha.get(Calendar.MONTH) + 1;
    }
   public int getYear(){
       Calendar fecha = Calendar.getInstance();
       return fecha.get(Calendar.YEAR);
   }
    public String gethora() {
        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos;
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        return hora+":"+minutos+":"+segundos;
    }
}
