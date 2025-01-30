package modelos.productos.proveedores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Conexion;

public class ProveedorDAO {

    public List<Proveedores> VerProveedor() {
        List<Proveedores> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";

        try {

            Connection con = Conexion.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Proveedores proveedor = new Proveedores();

                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setRuc(rs.getInt("ruc"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setRazon_social(rs.getString("razon_social"));

                proveedores.add(proveedor);

            }

        } catch (SQLException e) {
            System.out.print("Error obteniendo los proveedores" + e);
        }

        return proveedores;
    }

    public boolean ActualizarProveedor(int id, Proveedores proveedor) {
        try {
            String sql = "UPDATE proveedores SET nombre= ?,ruc= ?,telefono= ?,direccion= ?,razon_social= ? WHERE id = ?";
            Connection con = Conexion.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, proveedor.getNombre());
            pst.setInt(2, proveedor.getRuc());
            pst.setString(3, proveedor.getTelefono());
            pst.setString(4, proveedor.getDireccion());
            pst.setString(5, proveedor.getRazon_social());
            pst.setInt(6, id);

            pst.setInt(7, id);

            int filasActualizadas = pst.executeUpdate();

            con.close();

            return filasActualizadas > 0;
        } catch (SQLException e) {
            System.out.println("Error del servidor" + e.toString());
            return false;
        }
    }

}
