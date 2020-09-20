
package acciones_funciones;

import funciones.Examen;
import funciones.Resultado;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import principal.Datos_Conexion;


public class DM_Resultado extends Datos_Conexion{

    public DM_Resultado() {
    }
    
    public String AgregarResultado(Resultado resultado) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Resultado (Codigo, Codigo_Paciente, Codigo_Examen, Codigo_Laboratorista, Orden, Informe, Fecha, Hora )VALUES (?,?,?,?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, resultado.getCodigo());
            PrSt.setString(2, resultado.getCodigo_paciente());
            PrSt.setString(3, resultado.getCodigo_examen());
            PrSt.setString(4, resultado.getCodigo_laboratorista());
            PrSt.setString(5, resultado.getOrden());
            PrSt.setString(6, resultado.getInforme());
            PrSt.setDate(7, resultado.getFecha());
            PrSt.setString(8, resultado.getHora());
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
