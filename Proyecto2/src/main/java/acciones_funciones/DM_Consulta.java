package acciones_funciones;

import funciones.Consulta;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import principal.Datos_Conexion;
import usuarios.Administrador;

public class DM_Consulta extends Datos_Conexion {

    public DM_Consulta() {
    }
    /**
     * Agrega una consulta a la base de datos
     * @param consulta La consulta que vamos a agregar
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
    public String AgregarConsulta(Consulta consulta) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Consulta (Tipo, Costo)VALUES (?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, consulta.getTipo());
            PrSt.setDouble(2, consulta.getCosto());
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Informacion ingresada";
            } else {
                mensaje = "Fallo al ingresar";
            }
            PrSt.close();
        } catch (SQLException e) {
            mensaje = e.toString();
            System.out.println(e.toString());
        }
        return mensaje;
    }
    /**
     * Obtiene todas las consultas de la base de datos
     * @return La lista de consultas
     */
    public ArrayList<Consulta> VerConsultas() {
        ArrayList<Consulta> lista = new ArrayList();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Consulta";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Consulta consulta = new Consulta(rs.getString("Tipo"), rs.getDouble("Costo"));
                lista.add(consulta);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista.clear();
        }
        return lista;
    }
    /**
     * Modifica el costo de una consulta en la base de datos
     * @param consulta La consulta que se modificara
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
    public String ModificarConsulta(Consulta consulta) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "UPDATE Consulta SET Costo = ? WHERE Tipo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setDouble(1, consulta.getCosto());
            PrSt.setString(2, consulta.getTipo());
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Informacion modificada";
            } else {
                mensaje = "Fallo al modificada";
            }
            PrSt.close();
        } catch (Exception e) {
            mensaje = e.toString();
            System.out.println(e.toString());
        }
        return mensaje;
    }
     /**
      * Elimina una consulta de la base de datos
      * @param tipo El tipo de consulta que se eliminara
      * @return Un mensaje que indica si la operacion fue exitosa o no
      */
     public String EliminarConsulta(String tipo) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "DELETE FROM Consulta  WHERE Tipo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, tipo);
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Informacion Eliminada";
            } else {
                mensaje = "Fallo al eliminar";
            }
            PrSt.close();
        } catch (Exception e) {
            mensaje = e.toString();
            System.out.println(e.toString());
        }
        return mensaje;
    }
 
}
