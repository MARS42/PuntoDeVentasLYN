
package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Admin {
    private static Connection conect = null;
    private static final String driver="com.mysql.jdbc.Driver"; 
    //Variables para la conexion a mysql 
    String servidor="localhost";
    String baseDatos="mysql";
    String usuario="root";
    String password="";
   public Admin(){
          conect =null;
        try {
            Class.forName(driver);
            // String n="jdbc:mysql://"+host+"/"+bd+","+user+","+password;
            conect = DriverManager.getConnection("jdbc:mysql://"+servidor+"/"+baseDatos,usuario,password);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error"+e.getMessage());
        }
   } 
   public Connection getConnection(){ return conect; }
}
