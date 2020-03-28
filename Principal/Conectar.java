/*
Utilizamos la interfaz  para generar aqui  la coneccion y solo importar el objeto desde todas las clases
hijas
 */
package Principal;

import BaseDatos.Sql;


public interface Conectar {
    Sql c= new Sql();
    
}
