
package Actores;

import java.util.ArrayList;


/**
 *
 * @author omara
 */

/*
Esta clase crea una lista de acuerdo a el numero de elementos q le digas
las inicializa para q si en algun formulario hay una caja de texto vacia 
no genere un error por el el programa ya genero los datos 

*/
public class ObtenerTextos {
    //Iniciando la lista
    public ArrayList<Object> datos= new ArrayList<>();
    public void add(String dato){
        if(dato.length()!=0){
           datos.add(dato); 
        }else{
            datos.add("");
        }
        
    }
    

}
