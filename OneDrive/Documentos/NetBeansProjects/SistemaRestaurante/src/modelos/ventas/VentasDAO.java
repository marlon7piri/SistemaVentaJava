package modelos.ventas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Conexion;

public class VentasDAO {

    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    public boolean CrearVenta(Ventas venta) {

        try {

            String sql = "INSERT INTO ventas (cliente,vendedor,total)"
                    + "VALUES (?,?,?)";

            con = Conexion.getConnection();
            pst = con.prepareStatement(sql);

            pst.setInt(1, venta.getCliente());
            pst.setString(2, venta.getVendedor());
            pst.setDouble(3, venta.getTotal());

           

            int filasNuevas = pst.executeUpdate();
            
             // Cerrar recursos
            pst.close();
            con.close();
            
            return filasNuevas > 0;

        } catch (SQLException e) {
            System.out.println("Error del servidor" + e);
            return false;
        }
    }

}
