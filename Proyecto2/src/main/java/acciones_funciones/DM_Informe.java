package acciones_funciones;

import funciones.Informe;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import principal.Datos_Conexion;

public class DM_Informe extends Datos_Conexion {

    public DM_Informe() {
    }

    public String AgregarInforme(Informe informe) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Informe (Codigo, Codigo_Paciente, Codigo_Medico, Descripcion, Fecha, Hora)VALUES (?,?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, informe.getCodigo());
            PrSt.setString(2, informe.getCodigo_paciente());
            PrSt.setString(3, informe.getCodigo_medico());
            PrSt.setString(4, informe.getDescripcion());
            PrSt.setDate(5, informe.getFecha());
            PrSt.setString(6, informe.getHora());
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
