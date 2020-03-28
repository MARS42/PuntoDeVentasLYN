/*
Esta clase  ejecuta los queries de mysql para devolvernos
arreglos o listas con los datos de las tablas 
 */
package BaseDatos;

import Principal.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Query implements Conectar{
   public void   insert(String query,ArrayList<Object>campos ,String mensaje){
       
     Connection cn=c.getConnection();
     try {
     PreparedStatement psd=cn.prepareStatement(query);
        for(int i=0; i<campos.size(); i++){
        psd.setObject((i+1),campos.get(i));
      }
       int n=   psd.executeUpdate();
       if(n>0){
           JOptionPane.showMessageDialog(null, mensaje);
           
       }
       c.apagar();
     } catch (Exception e) {
        JOptionPane.showMessageDialog(null,"Nose puede guardar los datos "+e.getMessage());     
        }
   
    }  

 public void update(String query,String mensaje){
 try {
      
Connection cn=c.getConnection();
PreparedStatement pst= cn.prepareStatement(query);
if(pst.executeUpdate()>0){
    JOptionPane.showMessageDialog(null, mensaje);
}   
} catch (SQLException ex) {
 JOptionPane.showMessageDialog(null, "Error "+ex.getMessage());
        }
   }
  public void delete(String query,String mensaje){
    Connection cn=c.getConnection();
     try {
PreparedStatement psd=cn.prepareStatement(query);
  if(psd.executeUpdate()>0){
      JOptionPane.showMessageDialog(null, mensaje);
  }
    
} catch (Exception e) {
JOptionPane.showMessageDialog(null,e.getMessage());
        
            
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
        }
   return datos;
            
    }  
  
}
