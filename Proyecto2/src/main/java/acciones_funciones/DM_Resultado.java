
package acciones_funciones;

import funciones.Resultado;
import java.sql.Date;
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
            String Query = "INSERT INTO Resultado (Codigo_Paciente, Codigo_Medico, Codigo_Examen, Codigo_Laboratorista, Orden, Informe, Fecha, Hora )VALUES (?,?,?,?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, resultado.getCodigo_paciente());
            PrSt.setString(2, resultado.getCodigo_medico());
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
            String Query = "SELECT r.Codigo, r.Codigo_Paciente, t.Nombre AS Examen, r.Codigo_Medico, r.Codigo_Laboratorista, r.Orden, r.Informe, r.Fecha, r.Hora FROM Resultado r JOIN Tipo_Examen t ON r.Codigo_Examen = t.Codigo WHERE Codigo_Paciente = ? ORDER BY Fecha DESC";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Resultado resultado = new Resultado(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Examen"), rs.getString("Codigo_Laboratorista"), rs.getString("Orden"), rs.getString("Informe"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(resultado);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }
    
    public ArrayList<Resultado> ReporteVerUltimos5Resultados(String codigo_paciente) {
        ArrayList<Resultado> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Resultado WHERE Codigo_Paciente = ? ORDER BY Fecha DESC LIMIT 5";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Resultado resultado = new Resultado(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"),rs.getString("Codigo_Examen"), rs.getString("Codigo_Laboratorista"), rs.getString("Orden"), rs.getString("Informe"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(resultado);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }
    
    public ArrayList<Resultado> ReporteVerExamenesDeUnTipo(String codigo_paciente, String nombre_examen, String f1, String f2) {
        nombre_examen = "%" + nombre_examen + "%";
        ArrayList<Resultado> lista = new ArrayList<>();
        try {
            Date fecha1 = Date.valueOf(f1);
            Date fecha2 = Date.valueOf(f2);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT r.Codigo, r.Codigo_Paciente, r.Codigo_Medico, t.Nombre AS Examen, r.Codigo_Laboratorista, r.Orden, r.Informe, r.Fecha, r.Hora FROM Resultado r JOIN Tipo_Examen t ON r.Codigo_Examen = t.Codigo WHERE r.Codigo_Paciente = ? AND t.Nombre LIKE ? AND r.Fecha BETWEEN ? AND ? ORDER BY r.Fecha DESC";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            PrSt.setString(2, nombre_examen);
            PrSt.setDate(3, fecha1);
            PrSt.setDate(4, fecha2);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Resultado resultado = new Resultado(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Examen"), rs.getString("Codigo_Laboratorista"), rs.getString("Orden"), rs.getString("Informe"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(resultado);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista.clear();
            System.out.println(e.toString());
        }
        return lista;
    }
    
    public ArrayList<Resultado> ReporteLABExamenesRealizadosDia(String codigo_lab, String f) {
        ArrayList<Resultado> lista = new ArrayList<>();
        try {
            Date fecha = Date.valueOf(f);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Resultado WHERE Codigo_Laboratorista = ? AND Fecha = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_lab);
            PrSt.setDate(2, fecha);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Resultado resultado = new Resultado(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Codigo_Examen"), rs.getString("Codigo_Laboratorista"), rs.getString("Orden"), rs.getString("Informe"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(resultado);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista.clear();
        }
        return lista;
    }
    
    public ArrayList<Resultado> ReporteLABFechasConMasTrabajo(String codigo_lab) {
        ArrayList<Resultado> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT COUNT(Fecha) AS Examenes, Fecha FROM Resultado WHERE Codigo_Laboratorista = ? GROUP BY Fecha ORDER BY COUNT(Fecha) DESC LIMIT 10";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_lab);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Resultado resultado = new Resultado(Integer.parseInt(rs.getString("Examenes")), "xxxx", "xxxx", "xxxx", "xxxx", "xxxx", "xxxx", rs.getDate("Fecha"), "xxxx");
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
