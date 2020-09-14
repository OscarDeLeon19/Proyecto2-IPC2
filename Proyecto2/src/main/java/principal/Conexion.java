
package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Conexion {
    
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static boolean conexion_exitosa;

    /**
     * Constructor de la clase conexion
     *
     * @param URL Direccion de la base de datos
     * @param USERNAME Usuario de la base de datos
     * @param PASSWORD Contraseña de el usuario
     */
    public Conexion(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    /**
     * Regresa un valor para comprobar que la conexion fue exitosa
     *
     * @return El valor de la conexion
     */
    public boolean isConexion_Exitosa() {
        return conexion_exitosa;
    }
    
    /**
     * Metodo que conecta la aplicacion con la base de datos
     * @return La conexion entre la aplicacion y la base de datos
     */
    public static Connection getConnection() {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
            conexion_exitosa = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            conexion_exitosa = true;
        }
        return con;
    }
}
