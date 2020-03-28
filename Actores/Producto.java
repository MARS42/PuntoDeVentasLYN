/*
Clase producto para Guardar los datos de los productos en una lista enlazada o ArrayList
*/ 
package Actores;

/**
 *
 * @author omara
 */
public class Producto {
   int codigoBarras;
   String NombreP;
   double PrecioUnitario,PrecioMayoreo;
   double unidades;
   Producto siguinte;

    public Producto(int codigoBarras, String NombreP, double PrecioUnitario, double PrecioMayoreo, double unidades, Producto siguinte) {
        this.codigoBarras = codigoBarras;
        this.NombreP = NombreP;
        this.PrecioUnitario = PrecioUnitario;
        this.PrecioMayoreo = PrecioMayoreo;
        this.unidades = unidades;
        this.siguinte = siguinte;
    }

    public Producto(int codigoBarras, String NombreP, double PrecioUnitario, double PrecioMayoreo, double unidades) {
        this.codigoBarras = codigoBarras;
        this.NombreP = NombreP;
        this.PrecioUnitario = PrecioUnitario;
        this.PrecioMayoreo = PrecioMayoreo;
        this.unidades = unidades;
    }
   
   
}
