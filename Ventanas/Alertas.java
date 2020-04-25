
package Ventanas;

import Actores.EnviarCorreos;
import Principal.Conectar;
import java.util.ArrayList;
import java.util.Calendar;


public class Alertas implements Conectar{
    ArrayList<String>codigoBarras;
    ArrayList<String>Productos= new ArrayList<>();
    public Alertas(ArrayList<String> codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    public void enviarMensaje(){
     for(int i=0; i<codigoBarras.size(); i++){
         if(Float.parseFloat(Conec.Select("select Unidades from productos where codigoBarras='"
               +codigoBarras.get(i)+"';", 1).get(0)+"")<10){
             Productos.add(codigoBarras.get(i));
         }
       
     }
     codigoBarras.clear();
     if(Productos.size()!=0){
         String msg="";
         Calendar fecha = Calendar.getInstance();
         int hora = fecha.get(Calendar.HOUR_OF_DAY);
         //Personalizando el saludo
         if(hora>=7 && hora<=12){
             msg="Buenos dias\n";
         }
         else if(hora>=13 && hora <=18){
             msg="Buenas tardes\n";
         }else if(hora>18){
             msg="Buenas noches, ";
         }
        //Comunicand
       
        msg+="este es el informe de los productos con un inventario menor a 10 unidades.";
        msg+="\n";
       //Lista de los productos
       for(int i=0; i<Productos.size(); i++){
          ArrayList<String> p=Conec.Select("select * from productos where codigoBarras='"+Productos.get(i)+"';",5 );
          for(int j=0; j<5; j++){
              msg+=""+p.get(j)+"\t";
          }
          msg+="\n";
       }
         Thread h= new Thread(new hilo(msg));
         h.start();
     }
    }
   public class hilo implements Runnable{
  String msg;

        public hilo(String msg) {
            this.msg = msg;
        }
  
        @Override
        public void run() {
            
           new EnviarCorreos().enviarCorreoJava(msg, "liz_110698@outlook.es","papelerialyn2020@gmail.com" 
               , ":::Lapiz:1001:::", "Alertas");
        }
       
   }
}
