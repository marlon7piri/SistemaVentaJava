
package modelos.detalles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelos.Conexion;


public class DetallesDAO {
    Connection con ;
    ResultSet rs;
    PreparedStatement pst;
  
    
    public boolean RegistrarDetalles(Detalles detalleV){
        
        try {
            
            String sq="INSERT INTO detalles (codigo_prod,precio,cantidad,id_venta) VALUES(?,?,?,?)";
           con =  Conexion.getConnection();
           pst = con.prepareStatement(sq);
           
           pst.setInt(1, detalleV.getCodigo_prod());
           pst.setDouble(2,detalleV.getPrecio());
           pst.setInt(3,detalleV.getCantidad());
           pst.setInt(4,detalleV.getId());
           
           int filas = pst.executeUpdate();
           
           con.close();
           pst.close();
           return filas > 0;
           
            
        } catch (Exception e) {
            System.out.println("Error creando detalles de venta" + e);
        }
        return false;
    }
    
}
