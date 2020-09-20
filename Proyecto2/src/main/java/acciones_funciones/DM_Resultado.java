
package acciones_funciones;

import funciones.Resultado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public ArrayList<Resultado> VerHistorialPacientes(String codigo_paciente) {
        ArrayList<Resultado> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Resultado WHERE Codigo_Paciente = ? AND Estado IS NULL ORDER BY Fecha";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Resultado resultado = new Resultado(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Examen"), rs.getString("Codigo_Laboratorista"), rs.getString("Orden"), rs.getString("Informe"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(resultado);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }
}
