package acciones_funciones;

import funciones.Cita;
import funciones.Resultado;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import principal.Datos_Conexion;

public class DM_Cita extends Datos_Conexion {

    public DM_Cita() {
    }

    /**
     * Agrega una cita a la base de datos
     *
     * @param cita La cita que vamos a agregar
     * @return El mensaje que indica si fue satisfactoria o no
     */
    public String AgregarCita(Cita cita) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Cita (Codigo, Codigo_Paciente, Codigo_Medico, Especialidad, Fecha, Hora)VALUES (?,?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, cita.getCodigo());
            PrSt.setString(2, cita.getCodigo_paciente());
            PrSt.setString(3, cita.getCodigo_medico());
            PrSt.setString(4, cita.getEspecialidad());
            PrSt.setDate(5, cita.getFecha());
            PrSt.setString(6, cita.getHora());
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

    /**
     * Devuelve una cita seguin su codigo
     *
     * @param codigo codigo de la cita
     * @return La cita
     */
    public Cita VerCitaPorCodigo(String codigo) {
        Cita cita = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Cita WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita nueva_cita = new Cita(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
                cita = nueva_cita;
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            cita = null;
        }
        return cita;
    }

    public String RealizarCita(Cita cita) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "UPDATE Cita set Estado = 'Realizada' WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, cita.getCodigo());
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Cita realizada";
            } else {
                mensaje = "Fallo al realizar";
            }
            PrSt.close();
        } catch (SQLException e) {
            mensaje = e.toString();
            System.out.println(e.toString());
        }
        return mensaje;
    }

    public ArrayList<Cita> VerHistorialCitasPaciente(String codigo_paciente) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Cita WHERE Codigo_Paciente = ? AND Estado = 'Realizada' ORDER BY Fecha";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }

    public ArrayList<Cita> VerCitasEnCurso(String codigo_paciente) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Cita WHERE Codigo_Paciente = ? AND Estado IS NULL ORDER BY Fecha";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }

    public ArrayList<Cita> ReporteVerUltimas5Citas(String codigo_paciente) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Cita WHERE Codigo_Paciente = ? AND Estado = 'Realizada' ORDER BY Fecha DESC LIMIT 5";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }

    public ArrayList<Cita> ReporteVerCitasPorMedicoYFechas(String codigo_paciente, String nombre_medico, Date fecha1, Date fecha2) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT c.Codigo, c.Codigo_Paciente, m.Nombre AS Medico, c.Especialidad, c.Fecha, c.Hora FROM Cita c JOIN Medico m ON c.Codigo_Medico = m.Codigo WHERE c.Codigo_Paciente = ? AND m.Nombre LIKE ? AND c.Fecha BETWEEN ? AND ? AND Estado = 'Realizada'";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            PrSt.setString(2, nombre_medico);
            PrSt.setDate(3, fecha1);
            PrSt.setDate(4, fecha2);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }
    
    public ArrayList<Cita> ReporteCitasEnIntervaloDeTiempo(String codigo_medico, Date fecha1, Date fecha2) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Cita WHERE Codigo_Medico = ? AND Estado = 'Realizada' AND Fecha BETWEEN ? AND ? ORDER BY Fecha";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_medico);
            PrSt.setDate(2, fecha1);
            PrSt.setDate(3, fecha2);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }
    
    public ArrayList<Cita> ReportesCitasDiaActual(String codigo_medica, Date fecha) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Cita WHERE Codigo_Medico = ? AND Fecha = ? AND Estado IS NULL";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_medica);
            PrSt.setDate(2, fecha);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }
}
