package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginDAO {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Conexion cn = new Conexion();

    public login log(String correo, String password) {
        login mensajero = new login();
        String sql = "SELECT * FROM usuarios where correo = ? AND password = ?";
        

        try {

            con = cn.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, correo);
            pst.setString(2, password);

            rs = pst.executeQuery();

            if (rs.next()) {
                mensajero.setId(rs.getInt("id"));
                mensajero.setNombre(rs.getString("nombre"));
                mensajero.setCorreo(rs.getString("correo"));
                mensajero.setPassword(rs.getString("password"));

            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");

            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return mensajero;

    }

}
