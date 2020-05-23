/*
Esta clase administra la base de dtos 
si hace falta una tabla la crea o borra 
inserta registros etc
 */
package BaseDatos;

import Principal.Conectar;

import java.util.ArrayList;

/**
 *
 * @author omara
 */
public class tablas implements Conectar {

    public void verificar() {
        
        Conec.create_table("CREATE TABLE IF NOT EXISTS `clientes` (\n"
                + " `id_cliente` int(11) NOT NULL AUTO_INCREMENT,\n"
                + " `nombre` varchar(100) DEFAULT NULL,\n"
                + " `telefono` varchar(20) DEFAULT NULL,\n"
                + " `correo` varchar(80) DEFAULT NULL,\n"
                + " PRIMARY KEY (`id_cliente`)\n"
                + ");", "No se puede crear esa tabla ");

        Conec.create_table("CREATE TABLE IF NOT EXISTS `productos` (\n"
                + "  `codigoBarras` varchar(50) NOT NULL,\n"
                + "  `NombreP` varchar(100) DEFAULT NULL,\n"
                + "  `PrecioUnitario` double DEFAULT NULL,\n"
                + "  `PrecioMayoreo` double DEFAULT NULL,\n"
                + "  `Unidades` double DEFAULT NULL,\n"
                + "  PRIMARY KEY (`codigoBarras`)\n"
                + ");", "No se puede crear esa tabla ");

        Conec.create_table("CREATE TABLE IF NOT EXISTS `roleas` (\n"
                + "  `id_rol` int(11) NOT NULL AUTO_INCREMENT,\n"
                + "  `descripcion` varchar(30) DEFAULT NULL,\n"
                + "  PRIMARY KEY (`id_rol`)\n"
                + ");", "");
        if (Conec.Select("select id_rol from roleas;", 1).size() == 0) {
            ArrayList<Object> datos = new ArrayList<>();
            datos.add("1");
            datos.add("Administrador");

            Conec.insert("insert into roleas values(?,?);", datos, "");
            datos.clear();
            datos.add("2");
            datos.add("Empleado");

            Conec.insert("insert into roleas values(?,?);", datos, "");
            datos.clear();
        }
        Conec.create_table("CREATE TABLE IF NOT EXISTS `usuarios` (\n"
                + "  `usarName` varchar(50) NOT NULL,\n"
                + "  `nombreU` varchar(30) DEFAULT NULL,\n"
                + "  `password` varchar(700) DEFAULT NULL,\n"
                + "  `id_rol` int(11) DEFAULT NULL,\n"
                + "  `Correo` varchar(200) NOT NULL,\n"
                + "  `telefono` varchar(30) NOT NULL,\n"
                + "  PRIMARY KEY (`usarName`),\n"
                + "  KEY `id_rol` (`id_rol`),\n"
                + "  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `roleas` (`id_rol`)\n"
                + ");", "");
        Conec.create_table("create table IF NOT EXISTS ventas (id_venta int primary key auto_increment, "
                + "usuario varchar(50), importe double,fecha date, id_cliente int, "
                + "foreign key (usuario) references usuarios(usarName) ,"
                + " foreign key(id_cliente) references clientes (id_cliente));", "No se puede crear tabla ");
        Conec.create_table("create table IF NOT EXISTS carrito (id_carrito int PRIMARY key AUTO_INCREMENT,"
                + "id_producto varchar(50),unidades double, id_venta int , "
                + "FOREIGN key (id_producto) REFERENCES productos (codigoBarras), FOREIGN key "
                + "(id_venta) REFERENCES ventas (id_venta))", "No se puede crear la tabla ");

    }

}
