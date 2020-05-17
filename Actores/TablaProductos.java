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
            String sql = " select count(*) from carrito inner join ventas"
                    + " on ventas.id_venta=carrito.id_venta where ventas.fecha between '"
                    + fecha1 + "' and '" + fecha2 + "' and id_producto='" + codigos.get(i) + "';";

            p[i] = new Producto(codigos.get(i), Integer.parseInt(Conec.Select(sql, 1).get(0) + ""));

        }
        try {
            metodo_quick(0, p.length - 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void metodo_quick(int primero, int ultimo) {
        int i, j, pivote;Producto aux; i = primero;j = ultimo; int po = (int) (primero + ultimo) / 2;
        pivote = p[po].ventas;
        do {
            while (p[i].ventas < pivote) {
                i++;
            }
            while (p[j].ventas > pivote) {
                j--;
            }
            //Intercambio
            if (i <= j) {
                aux = p[i];
                p[i] = p[j];
                p[j] = aux;
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) {
            metodo_quick(primero, j);
        }
        if (i < ultimo) {
            metodo_quick(i, ultimo);
        }
    }
}
