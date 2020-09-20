package acciones_funciones;

import funciones.Cita;
import funciones.Resultado;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import principal.Datos_Conexion;

public class DM_Cita extends Datos_Conexion {

    public DM_Cita() {
    }

    public String AgregarCita(Cita cita) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Cita (Codigo, Codigo_Paciente, Codigo_Medico, Fecha, Hora)VALUES (?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, cita.getCodigo());
            PrSt.setString(2, cita.getCodigo_paciente());
            PrSt.setString(3, cita.getCodigo_medico());
            PrSt.setDate(4, cita.getFecha());
            PrSt.setString(5, cita.getHora());
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
