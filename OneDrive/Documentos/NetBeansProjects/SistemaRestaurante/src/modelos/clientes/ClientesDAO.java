package modelos.clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Conexion;


public class ClientesDAO {

    static PreparedStatement pst;

    public List<Clientes> VerClientes() {
        // Hacemos un JOIN entre productos y proveedores para obtener el nombre del proveedor
        String sql = "SELECT * FROM clientes";
        List<Clientes> clientes = new ArrayList<>();

        try {

            Connection con = Conexion.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setRuc(rs.getInt("ruc"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setRazon_social(rs.getString("razon_social"));

                clientes.add(cliente);

            }

        } catch (SQLException error) {
            System.out.println("Error al obtener productos: " + error.getMessage());

        }
        return clientes;
    }

    public PreparedStatement CrearCliente() {

        try {
            String sql = "INSERT INTO clientes(nombre,ruc,telefono,direccion,razon_social) VALUES (?,?,?,?,?)";
            Connection con = Conexion.getConnection();
            pst = con.prepareStatement(sql);

        } catch (SQLException e) {
            System.out.print("Error creando el producto" + e);
        }
        return pst;
    }
}
