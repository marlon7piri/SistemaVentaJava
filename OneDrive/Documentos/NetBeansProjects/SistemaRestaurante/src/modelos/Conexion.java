package modelos;

import java.sql.*;

public class Conexion {

    public  Connection getConnection() {
        
        try {
            String BD = "jdbc:mysql://localhost/sistemaventabd";
            Connection con = DriverManager.getConnection(BD, "root", "");
            return con;

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
