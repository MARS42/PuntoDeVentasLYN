/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actores;

import Principal.Conectar;
import java.util.ArrayList;

/**
 *
 * @author omara
 */
public class TablaProductos implements Conectar {
Producto p[];
    public void productos() {

        int mes = new GenerarFecha().getMes();
        Calendario ca = new Calendario();
        //Fecha de inicio mes 
        String fecha1 = new GenerarFecha().getYear() + "-" + mes + "-01";
        //Fecha final del mes 
        String fecha2 = new GenerarFecha().getYear() + "-" + mes + "-" + ca.mes.get(mes - 1).dias;
        ArrayList<String> codigos = Conec.Select("select codigoBarras from productos;", 1);
        //Genrando la consulta de mysql para optener solos los priductos del mes 

        p = new Producto[codigos.size()];

        for (int i = 0; i < codigos.size(); i++) {
            String sql = " select count(*) from carrito inner join ventas on ventas.id_venta=carrito.id_venta where ventas.fecha between '" + fecha1 + "' and '" + fecha2 + "' and id_producto='" + codigos.get(i) + "';";
            
            p[i]= new Producto(codigos.get(i),Integer.parseInt(Conec.Select(sql, 1).get(0)+""));
            
        }

    }
}
