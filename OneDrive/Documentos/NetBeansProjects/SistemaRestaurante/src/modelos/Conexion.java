package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/sistemaventabd";
    private static final String USER = "root";
    private static final String PASSWORD = "4123";

    // Método estático para obtener la conexión
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}