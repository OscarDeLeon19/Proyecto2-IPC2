
package acciones_funciones;

import funciones.Examen;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import principal.Datos_Conexion;


public class DM_Examen extends Datos_Conexion {

    public DM_Examen() {
    }
    
    public String AgregarExamen(Examen examen) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Examen (Codigo, Nombre, Orden, Descripcion, Costo, Tipo_Informe)VALUES (?,?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, examen.getCodigo());
            PrSt.setString(2, examen.getNombre());
            PrSt.setBoolean(3, examen.isOrden());
            PrSt.setString(4, examen.getDescripcion());
            PrSt.setDouble(5, examen.getCosto());
            PrSt.setString(6, examen.getTipo_informe());
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
