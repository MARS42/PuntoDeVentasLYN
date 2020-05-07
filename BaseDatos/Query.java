/*
Esta clase  ejecuta los queries de mysql para devolvernos
arreglos o listas con los datos de las tablas 
 */
package BaseDatos;

import Actores.Producto;
import Principal.Conectar;
import Ventanas.MensajeError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Query implements Conectar{
   public void insert(String query,ArrayList<Object>campos ,String mensaje){

        Connection cn=c.getConnection();
        try 
        {
            PreparedStatement psd=cn.prepareStatement(query);
            for(int i=0; i<campos.size(); i++){
                psd.setObject((i+1),campos.get(i));
            }
            int n=   psd.executeUpdate();
            if(n>0){
                
            }
           
        } catch (Exception e) {
            System.out.println(mensaje +" "+e.getMessage());
           MensajeError men = new MensajeError();
                    men.Mensaje.setText(mensaje);
                    men.setVisible(true);     
        }
    }  

    public void update(String query,String mensaje){
        try { 
            Connection cn=c.getConnection();
            PreparedStatement pst= cn.prepareStatement(query);
            if(pst.executeUpdate()>0){
            
            }   
        } catch (SQLException ex) {
            System.out.println(mensaje+" "+ex.getMessage());
                     MensajeError men = new MensajeError();
                    men.Mensaje.setText(mensaje+"\n"+ex.getMessage());
                    men.setVisible(true);
                    
        }
    }
   
    public void delete(String query,String mensaje){
        Connection cn=c.getConnection();
        try {
            PreparedStatement psd=cn.prepareStatement(query);
            if(psd.executeUpdate()>0){
                
            }
        } catch (Exception e) {
            MensajeError men = new MensajeError();
                    men.Mensaje.setText(mensaje);
                    men.setVisible(true);  
        }
    }
    public ArrayList Select(String query,int columnas){
        ArrayList<String> datos= new ArrayList();
        try{
            Connection cn=c.getConnection();
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                for(int i=1; i<=columnas; i++){
                    datos.add(rs.getString(i)); 
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            MensajeError men = new MensajeError();
                    men.Mensaje.setText(e.getMessage());
                    men.setVisible(true);
                    
        }
        return datos;    
    }
      
     public ArrayList SelectProductos(String query,int columnas){
        ArrayList<Producto> datos= new ArrayList();
        try{
            Connection cn=c.getConnection();
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                
                    datos.add(new Producto(rs.getString("NombreP"),Double.parseDouble(rs.getString("Unidades")))); 
                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            MensajeError men = new MensajeError();
                    men.Mensaje.setText(e.getMessage());
                    men.setVisible(true);
                    
        }
        return datos;    
    }
      public ArrayList SelectProductos(String query){
        ArrayList<Producto> datos= new ArrayList();
        try{
            Connection cn=c.getConnection();
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                //odigoBarras,NombreP,Unidades
                    datos.add(new Producto(rs.getString("codigoBarras"),rs.getString("NombreP"),Integer.parseInt(rs.getString("Unidades")))); 
                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            MensajeError men = new MensajeError();
                    men.Mensaje.setText(e.getMessage());
                    men.setVisible(true);
                    
        }
        return datos;    
    }
       public void create_table(String query,String mensaje){
        try { 
            Connection cn=c.getConnection();
            PreparedStatement pst= cn.prepareStatement(query);
            if(pst.executeUpdate()>0){
                System.out.println("Tabla creada");
            }   
        } catch (SQLException ex) {
            System.out.println("Error "+ex.getSQLState());
             MensajeError men = new MensajeError();
                    men.Mensaje.setText(mensaje+"\n"+ex.getMessage());
                    men.setVisible(true);
                    
        }
    }
  public ArrayList tabla(String query){
        ArrayList<String> datos= new ArrayList();
        try{
            Connection cn=c.getConnection();
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                
                    datos.add(rs.getString(1)); 
                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            MensajeError men = new MensajeError();
                    men.Mensaje.setText(e.getMessage());
                    men.setVisible(true);
                    
        }
        return datos;    
    } 
}
