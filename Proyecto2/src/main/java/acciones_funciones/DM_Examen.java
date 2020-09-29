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
            String Query = "INSERT INTO Examen (Codigo_Paciente, Codigo_Medico, Codigo_Examen, Fecha, Orden )VALUES (?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, examen.getCodigo_paciente());
            PrSt.setString(2, examen.getCodigo_medico());
            PrSt.setString(3, examen.getTipo_examen());
            PrSt.setDate(4, examen.getFecha());
            PrSt.setString(5, examen.getOrden());
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

    public Examen ObtenerExamen(int codigo) {
        Examen examen = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs;
            String Query = "SELECT * FROM Examen WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setInt(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Examen nuevo = new Examen(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Codigo_Examen"), rs.getDate("Fecha"));
                nuevo.setOrden(rs.getString("Orden"));
                examen = nuevo;
            }
            PrSt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return examen;
    }

    public ArrayList<Examen> VerExamenesSegunLaboratorista(String tipo_examen) {
        ArrayList<Examen> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT e.Codigo, e.Codigo_Paciente, e.Codigo_Medico, t.Nombre, e.Fecha, e.Orden FROM Examen e JOIN Tipo_Examen t ON e.Codigo_Examen = t.Codigo WHERE t.Nombre = ? AND Estado IS NULL";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, tipo_examen);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Examen examen = new Examen(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Nombre"), rs.getDate("Fecha"));
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

    public Resultado RealizarExamen(Examen examen) {
        Resultado resultado = new Resultado();
        try {
            PreparedStatement PrSt;
            String Query = "UPDATE Examen SET Estado = 'Realizado' WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setInt(1, examen.getCodigo());
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                resultado.setCodigo_paciente(examen.getCodigo_paciente());
                resultado.setCodigo_examen(examen.getTipo_examen());
                resultado.setCodigo_medico(examen.getCodigo_medico());
                resultado.setOrden(examen.getOrden());
            } else {

            }
            PrSt.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resultado;
    }

    public ArrayList<Examen> ReporteLabExamenesParaElDia(String tipo_examen, String f) {
        ArrayList<Examen> lista = new ArrayList<>();
        try {
            Date fecha = Date.valueOf(f);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT e.Codigo, e.Codigo_Paciente, e.Codigo_Medico, t.Nombre, e.Fecha FROM Examen e JOIN Tipo_Examen t ON e.Codigo_Examen = t.Codigo WHERE t.Nombre = ? AND e.Fecha = ? AND Estado IS NULL";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, tipo_examen);
            PrSt.setDate(2, fecha);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Examen examen = new Examen(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Nombre"), rs.getDate("Fecha"));
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
                Examen examen = new Examen(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Nombre"), rs.getDate("Fecha"));
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

    public ArrayList<Examen> ReporteVerGananciasMedico(String f1, String f2) {
        ArrayList<Examen> lista = new ArrayList<>();
        try {
            Date fecha1 = Date.valueOf(f1);
            Date fecha2 = Date.valueOf(f2);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT SUM(t.Costo) AS Ganancia, e.Codigo_Medico FROM Examen e JOIN Tipo_Examen t ON e.Codigo_Examen = t.Codigo WHERE e.Fecha BETWEEN ? AND ? GROUP BY Codigo_Medico ORDER BY SUM(t.Costo) DESC";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setDate(1, fecha1);
            PrSt.setDate(2, fecha2);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Examen examen = new Examen(rs.getInt("Ganancia"), "xxxx", rs.getString("Codigo_Medico"), "xxxx", Date.valueOf("2020-05-10"));
                lista.add(examen);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista.clear();
        }
        return lista;
    }

    public ArrayList<Examen> ReporteVerExamenesDemandados() {
        ArrayList<Examen> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT COUNT(Codigo_Examen) AS Examenes, Codigo_Examen FROM Examen GROUP BY Codigo_Examen ORDER BY COUNT(Codigo_Examen) DESC";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Examen examen = new Examen(rs.getInt("Examenes"), "xxxx", "xxxx", rs.getString("Codigo_Examen"), Date.valueOf("2020-05-10"));
                lista.add(examen);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista.clear();
        }
        return lista;
    }

    public ArrayList<Examen> ReporteVerExamenesRequeridosPorMedicos() {
        ArrayList<Examen> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT COUNT(Codigo_Medico) AS Examenes, Codigo_Medico FROM Examen GROUP BY Codigo_Medico ORDER BY COUNT(Codigo_Medico) DESC";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Examen examen = new Examen(rs.getInt("Examenes"), "xxxx", rs.getString("Codigo_Medico"), "xxxx", Date.valueOf("2020-05-10"));
                lista.add(examen);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista.clear();
        }
        return lista;
    }

    public ArrayList<Examen> ReporteVerGananciasPaciente() {
        ArrayList<Examen> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT SUM(t.Costo) AS Ganancia, e.Codigo_Paciente FROM Examen e JOIN Tipo_Examen t ON e.Codigo_Examen = t.Codigo GROUP BY Codigo_Paciente ORDER BY SUM(t.Costo) DESC";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Examen examen = new Examen(rs.getInt("Ganancia"), rs.getString("Codigo_Paciente"), "xxxx", "xxxx", Date.valueOf("2020-05-10"));
                lista.add(examen);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista.clear();
        }
        return lista;
    }
}
