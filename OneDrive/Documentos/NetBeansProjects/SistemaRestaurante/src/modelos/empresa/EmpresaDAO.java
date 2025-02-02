package modelos.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelos.Conexion;

public class EmpresaDAO {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Empresa empresa = new Empresa();

    public boolean CrearEmpresa(Empresa empresa) {
        try {
            String sql = "INSERT INTO empresa(nombre,ruc,direccion,razon_social,telefono) VALUES (?,?,?,?,?)";
            con = Conexion.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, empresa.getNombre());
            pst.setInt(2, empresa.getRuc());
            pst.setString(3, empresa.getDireccion());
            pst.setString(4, empresa.getRazon_social());
            pst.setString(5, empresa.getTelefono());

            int fila = pst.executeUpdate();

            con.close();
            pst.close();

            return fila > 0;

        } catch (Exception e) {
            System.out.println("Error creando empresa" + e);
        }
        return false;
    }

    public boolean ActualizarEmpresa(int id, Empresa empresa) {

        try {
            String sql = "UPDATE empresa SET nombre=?,ruc=?,direccion=?,razon_social=?,telefono=? VALUES (?,?,?,?,?) WHERE id=?";
            con = Conexion.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, empresa.getNombre());
            pst.setInt(2, empresa.getRuc());
            pst.setString(3, empresa.getDireccion());
            pst.setString(4, empresa.getRazon_social());
            pst.setString(5, empresa.getTelefono());
            pst.setInt(6, id);

            int filas = pst.executeUpdate();
            con.close();
            pst.close();
            return filas > 0;

        } catch (Exception e) {
            System.out.println("Error actualizando empresa" + e);
        }

        return false;
    }

    public Empresa VerEmpresa(int id) {
        try {

            String sql = "SELECT * from empresa WHERE id =?";
            con = Conexion.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                empresa.setNombre(rs.getString("nombre"));

                empresa.setRuc(Integer.parseInt(String.valueOf(rs.getInt("ruc"))));
                empresa.setDireccion(rs.getString("direccion"));
                empresa.setRazon_social(rs.getString("razon_social"));
                empresa.setTelefono(rs.getString("telefono"));

            }
            con.close();
            pst.close();
        } catch (Exception e) {
        }
        return empresa;

    }

}
