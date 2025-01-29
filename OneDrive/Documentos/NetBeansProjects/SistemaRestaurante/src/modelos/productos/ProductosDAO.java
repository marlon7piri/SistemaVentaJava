package modelos.productos;

import interfaces.SistemaPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Conexion;

public class ProductosDAO {

    static PreparedStatement pst;
/*
    
     private int id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private int precio;
    private String codigo;
    private int proveedor_id;
    private String proveedor_nombre;
    */
    public List<Productos> VerProductos() {
        // Hacemos un JOIN entre productos y proveedores para obtener el nombre del proveedor
    String sql2 = "SELECT p.id, p.nombre, p.descripcion, p.cantidad, p.precio, p.codigo, p.proveedor_id, prov.nombre AS proveedor_nombre " +
                 "FROM productos p " +
                 "INNER JOIN proveedores prov ON p.proveedor_id = prov.id";
        String sql = "SELECT p.id,p.nombre,p.descripcion,p.cantidad,p.precio,p.codigo,p.proveedor_id,pr.nombre AS proveedor_nombre FROM productos p INNER JOIN proveedores pr ON p.proveedor_id = pr.id";
        List<Productos> productos = new ArrayList<>();

        try {

            Connection con = Conexion.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Productos producto = new Productos();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getInt("precio"));
                producto.SetCodigo(rs.getString("codigo"));
                producto.setProveedor_id(rs.getInt("proveedor_id"));
                
                producto.setProveedor_nombre(rs.getString("proveedor_nombre"));

                productos.add(producto);

            }

        } catch (SQLException error) {
            System.out.println("Error al obtener productos: " + error.getMessage());

        }
        return productos;
    }

    public PreparedStatement CrearProducto() {

        try {
            String sql = "INSERT INTO productos(nombre,cantidad ,precio,descripcion,codigo,proveedor_id ) VALUES (?,?,?,?,?,?)";
            Connection con = Conexion.getConnection();
            pst = con.prepareStatement(sql);

        } catch (SQLException e) {
            System.out.print("Error creando el producto" + e);
        }
        return pst;
    }

}
