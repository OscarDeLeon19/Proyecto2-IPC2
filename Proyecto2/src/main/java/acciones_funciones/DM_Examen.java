package acciones_funciones;

import funciones.Examen;
import funciones.Resultado;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public Examen ObtenerExamen(String codigo) {
        Examen examen = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs;
            String Query = "SELECT * FROM Examen WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Examen nuevo = new Examen(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Codigo_Examen"), rs.getDate("Fecha"));
                examen = nuevo;
            }
            PrSt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return examen;
    }

    public Resultado RealizarExamen(Examen examen) {
        Resultado resultado = new Resultado();
        try {
            PreparedStatement PrSt;
            String Query = "UPDATE Examen SET Estado = 'Realizado' WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, examen.getCodigo());
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                resultado.setCodigo_paciente(examen.getCodigo_paciente());
                resultado.setCodigo_examen(examen.getTipo_examen());
                resultado.setCodigo_medico(examen.getCodigo_medico());
                resultado.setFecha(examen.getFecha());
            } else {

            }
            PrSt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return resultado;
    }

    public ArrayList<Examen> ReporteLabExamenesParaElDia(String tipo_examen, Date fecha) {
        ArrayList<Examen> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT e.Codigo, e.Codigo_Paciente, e.Codigo_Medico, t.Nombre, e.Fecha FROM Examen e JOIN Tipo_Examen t ON e.Codigo_Examen = t.Codigo WHERE t.Nombre = ? AND e.Fecha = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, tipo_examen);
            PrSt.setDate(2, fecha);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Examen examen = new Examen(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Nombre"), rs.getDate("Fecha"));
                lista.add(examen);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
            System.out.println(e.toString());
        }
        return lista;
    }
    
    public ArrayList<Examen> VerExamenesSinRealizarPaciente(String codigo_paciente) {
        ArrayList<Examen> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT e.Codigo, e.Codigo_Paciente, e.Codigo_Medico, t.Nombre, e.Fecha, e.Orden FROM Examen e JOIN Tipo_Examen t ON e.Codigo_Examen = t.Codigo  WHERE Codigo_Paciente = ? AND Estado IS NULL ORDER BY Fecha ASC";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Examen examen = new Examen(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Nombre"), rs.getDate("Fecha"));
                examen.setOrden(rs.getString("Orden"));
                lista.add(examen);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
            System.out.println(e.toString());
        }
        return lista;
    }
}
