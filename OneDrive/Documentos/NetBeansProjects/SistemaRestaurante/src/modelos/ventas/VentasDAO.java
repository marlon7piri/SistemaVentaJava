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
    
    public List<Ventas> VerVentas() {
        List<Ventas> ventas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ventas";
            con = Conexion.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Ventas venta = new Ventas();
                venta.setId(rs.getInt("id"));
                venta.setCliente(rs.getInt("cliente"));
                venta.setVendedor(rs.getString("vendedor"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setTotal(rs.getDouble("total"));
                ventas.add(venta);
            }
            
        } catch (Exception e) {
            System.out.println("Error obteniendo las ventas");
        }
        return ventas;
    }
    
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
    
    public int getIdVenta() {
        int id = 0;
        
        try {
            String sql = "SELECT MAX(id) FROM ventas";
            
            con = Conexion.getConnection();
            pst = con.prepareStatement(sql);
            
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
                return id;
            }
            con.close();
            pst.close();
        } catch (Exception e) {
            System.out.println("Error obteniendo el id de venta" + e);
        }
        return 8;
    }
    
    public boolean ActualizarStock(int cantidad, int codigo) {
        
        try {
            String sql = "UPDATE productos SET cantidad = ?  WHERE codigo = ? ";
            
            con = Conexion.getConnection();
            pst = con.prepareStatement(sql);
            
            pst.setInt(1, cantidad);
            pst.setInt(2, codigo);
            
            pst.executeUpdate();
            
            con.close();
            pst.close();
            return true;
            
        } catch (Exception e) {
            System.out.println("Error actualizando el stock" + e);
        }
        return false;
    }
}
