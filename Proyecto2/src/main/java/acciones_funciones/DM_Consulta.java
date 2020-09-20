
package acciones_funciones;

import funciones.Cita;
import funciones.Consulta;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import principal.Datos_Conexion;


public class DM_Consulta extends Datos_Conexion{

    public DM_Consulta() {
    }
    
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
}
