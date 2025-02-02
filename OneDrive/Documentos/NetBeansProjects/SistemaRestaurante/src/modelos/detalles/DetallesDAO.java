package modelos.detalles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelos.Conexion;

public class DetallesDAO {

    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    public boolean RegistrarDetalles(Detalles detalleV) {

        try {

            String sq = "INSERT INTO detalles (codigo_prod,precio,cantidad,id_venta) VALUES(?,?,?,?)";
            con = Conexion.getConnection();
            pst = con.prepareStatement(sq);

            pst.setInt(1, detalleV.getCodigo_prod());
            pst.setDouble(2, detalleV.getPrecio());
            pst.setInt(3, detalleV.getCantidad());
            pst.setInt(4, detalleV.getId());

            int filas = pst.executeUpdate();

            con.close();
            pst.close();
            return filas > 0;

        } catch (Exception e) {
            System.out.println("Error creando detalles de venta" + e);
        }
        return false;
    }

    public List<Detalles> VerDetalles(int id) {

        List<Detalles> detalles = new ArrayList<>();
        try {
            String sql = "SELECT d.id,p.nombre,p.descripcion, d.codigo_prod, d.precio, d.cantidad "
                    + "FROM detalles d  INNER JOIN ventas v "
                    + "ON d.id_venta = v.id "
                    + " INNER JOIN productos p ON p.codigo = d.codigo_prod "
                    + "WHERE v.id =?";

            con = Conexion.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            rs = pst.executeQuery();

            while (rs.next()) {

                Detalles detalle = new Detalles();
                detalle.setId(rs.getInt("id"));
                detalle.setCodigo_prod(rs.getInt("codigo_prod"));
                detalle.setProducto(rs.getString("nombre"));
                detalle.setDescripcion(rs.getString("descripcion"));

                detalle.setPrecio(rs.getDouble("precio"));
                detalle.setCantidad(rs.getInt("cantidad"));

                detalles.add(detalle);

            }
        } catch (Exception e) {
            System.out.println("Error obteniendo detalles de venta" + e);
        }
        return detalles;
    }
}
