package principal;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Datos_Conexion {

    private final String URL = "jdbc:mysql://localhost:3306/Proyecto2";
    private final String USSERNAME = "root";
    private final String PASSWORD = "mariobros99";

    Conexion clase_conexion = new Conexion(URL, USSERNAME, PASSWORD);
    protected Connection conexion = clase_conexion.getConnection();

    public Datos_Conexion() {
    }

    public String ObtenerEncriptacion(String contraseña) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] encriptar_bytes = md.digest(contraseña.getBytes());
            BigInteger numero = new BigInteger(1, encriptar_bytes);
            String encriptar_string = numero.toString(16);
            while (encriptar_string.length() < 32) {
                encriptar_string = "0" + encriptar_string;
            }
            return encriptar_string;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public void EliminarSesion() {
        try {
            PreparedStatement PrSt;
            String Query = "DELETE FROM Sesion";
            PrSt = conexion.prepareStatement(Query);
            PrSt.executeUpdate();
            PrSt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public String ObtenerCodigoSesion() {
        String codigo = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Sesion";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                codigo = rs.getString("Codigo");
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {

        }
        return codigo;
    }

    public void AbrirSesion(String codigo) {

        try {
            PreparedStatement PrSt;
            String Query = " INSERT INTO Sesion(Codigo) VALUES(?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            PrSt.executeUpdate();
            PrSt.close();
        } catch (SQLException e) {

        }
    }
}
