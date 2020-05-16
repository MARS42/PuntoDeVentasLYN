/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actores;

import java.util.LinkedList;

/**
 *
 * @author omara
 */
public class Calendario {
  public LinkedList<Meses>mes = new LinkedList<>();
    public Calendario() {
        mes.add(new Meses(1,31));
     if(new GenerarFecha().getYear()%4==0){
         mes.add(new Meses(2, 29));
     }else{
       mes.add(new Meses(2, 28));  
     }
     mes.add(new Meses(3, 31));
     mes.add(new Meses(4, 30));
     mes.add(new Meses(5, 31));
     mes.add(new Meses(6, 30));
     mes.add(new Meses(7, 31));
     mes.add(new Meses(8, 31));
     mes.add(new Meses(9, 30));
     mes.add(new Meses(10, 31));
     mes.add(new Meses(11, 31));
     mes.add(new Meses(12, 31));
     
    }
    
}
