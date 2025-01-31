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
    Connection con;
    ResultSet rs;

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
            con.close();

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
            con.close();

        } catch (SQLException e) {
            System.out.print("Error creando el producto" + e);
        }
        return pst;
    }

    public boolean EliminarCliente(int id) {

        try {
            String sql = "DELETE FROM clientes WHERE id = ?";
            Connection con = Conexion.getConnection();
            pst = con.prepareStatement(sql);

            pst.setInt(1, id);
            pst.execute();
            con.close();

            return true;
        } catch (SQLException e) {
            System.out.println("Error eliminando cliente" + e);
            return false;
        }
    }

    public boolean ActualizarCliente(int id, Clientes cliente) {

        try {
            String sql = "UPDATE clientes SET nombre= ?,ruc= ?,telefono= ?,direccion= ?,razon_social= ? WHERE id = ?";
            Connection con = Conexion.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, cliente.getNombre());
            pst.setInt(2, cliente.getRuc());
            pst.setString(3, cliente.getTelefono());
            pst.setString(4, cliente.getDireccion());
            pst.setString(5, cliente.getRazon_social());
            pst.setInt(6, id);

            int filasActualizadas = pst.executeUpdate();

            con.close();

            return filasActualizadas > 0;
        } catch (SQLException e) {
            System.out.println("Error del servidor" + e.toString());
            return false;
        }
    }

    public Clientes BuscarCliente(int ruc) {
        Clientes cliente = new Clientes();
        try {
            String sql = "SELECT * FROM clientes WHERE ruc =?";
            con = Conexion.getConnection();
            pst = con.prepareStatement(sql);

            pst.setInt(1, ruc);

            rs = pst.executeQuery();

            while (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                cliente.setRuc(rs.getInt("ruc"));
                cliente.setId(rs.getInt("id"));

            }

        } catch (Exception e) {
            System.out.println("Error del servidor" + e);
        }
        return cliente;

    }
}
