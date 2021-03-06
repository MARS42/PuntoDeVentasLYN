/*
Clase producto para Guardar los datos de los productos en una lista enlazada o ArrayList
*/ 
package Actores;

/**
 *
 * @author omara
 */
public class Producto {
   public String  codigoBarras;
   public String NombreP;
   public double PrecioUnitario,PrecioMayoreo;
   public double unidades;
   public double importe;
   public int ventas;

    public Producto(String codigoBarras, int ventas) {
        this.codigoBarras = codigoBarras;
        this.ventas = ventas;
    }
   
   Producto siguinte;

    public Producto(String codigoBarras, String NombreP, double unidades, double importe) {
        this.codigoBarras = codigoBarras;
        this.NombreP = NombreP;
        this.unidades = unidades;
        this.importe = importe;
    }
    
    public Producto(String  codigoBarras, String NombreP, double unidades) {
        this.codigoBarras = codigoBarras;
        this.NombreP = NombreP;
        this.unidades = unidades;
    }

    public Producto(String NombreP, double unidades) {
        this.NombreP = NombreP;
        this.unidades = unidades;
    }
    
    public void mostrarDatos(){
        System.out.println("CB "+codigoBarras+ " Nombre "+NombreP+" Unidades "+unidades);
    }
   

    public String getNombreP() {
        return NombreP;
    }

    public double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public double getPrecioMayoreo() {
        return PrecioMayoreo;
    }

    public double getUnidades() {
        return unidades;
    }

    public Producto getSiguinte() {
        return siguinte;
    }
   
   
}
