package acciones_funciones;

import funciones.Examen;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import principal.Datos_Conexion;

public class DM_Examen extends Datos_Conexion {

    public DM_Examen() {
    }

    public String AgendarExamen(Examen examen) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Examen (Codigo, Codigo_Paciente, Codigo_Medico, Codigo_Examen, Fecha, Orden )VALUES (?,?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, examen.getCodigo());
            PrSt.setString(2, examen.getCodigo_paciente());
            PrSt.setString(3, examen.getCodigo_medico());
            PrSt.setString(4, examen.getTipo_examen());
            PrSt.setDate(5, examen.getFecha());
            PrSt.setString(6, examen.getOrden());
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
