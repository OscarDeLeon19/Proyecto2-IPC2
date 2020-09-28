package acciones_funciones;

import funciones.Cita;
import funciones.Informe;
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
            String Query = "INSERT INTO Cita (Codigo_Paciente, Codigo_Medico, Especialidad, Fecha, Hora)VALUES (?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, cita.getCodigo_paciente());
            PrSt.setString(2, cita.getCodigo_medico());
            PrSt.setString(3, cita.getEspecialidad());
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
                Cita nueva_cita = new Cita(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
                cita = nueva_cita;
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            cita = null;
        }
        return cita;
    }

    public Boolean RealizarCita(Cita cita) {
        boolean mensaje = false;
        try {
            PreparedStatement PrSt;
            String Query = "UPDATE Cita set Estado = 'Realizada' WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setInt(1, cita.getCodigo());
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = true;
            } 
            PrSt.close();
        } catch (SQLException e) {
            mensaje = false;
            System.out.println(e.toString());
        }
        return mensaje;
    }

    public Informe ObtenerInforme(String codigo){
        Informe informe = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Cita WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                informe = new Informe(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), null, rs.getDate("Fecha"), rs.getString("Hora"));                
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            informe = null;
        }
        return informe;
    }
    
    public ArrayList<Cita> VerHistorialCitasPaciente(String codigo_paciente) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Cita WHERE Codigo_Paciente = ? AND Estado = 'Realizada' ORDER BY Fecha DESC";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
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
            String Query = "SELECT * FROM Cita WHERE Codigo_Paciente = ? AND Estado IS NULL ORDER BY Fecha ASC";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
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
                Cita cita = new Cita(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }

    public ArrayList<Cita> ReporteVerCitasPorMedicoYFechas(String codigo_paciente, String nombre_medico, String f1, String f2) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            nombre_medico = "%"+nombre_medico+"%";
            Date fecha1 = Date.valueOf(f1);
            Date fecha2 = Date.valueOf(f2);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT c.Codigo, c.Codigo_Paciente, m.Nombre AS Medico, c.Especialidad, c.Fecha, c.Hora FROM Cita c JOIN Medico m ON c.Codigo_Medico = m.Codigo WHERE c.Codigo_Paciente = ? AND m.Nombre LIKE ? AND c.Fecha BETWEEN ? AND ? AND Estado = 'Realizada' ORDER BY c.Fecha DESC";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            PrSt.setString(2, nombre_medico);
            PrSt.setDate(3, fecha1);
            PrSt.setDate(4, fecha2);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
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
                Cita cita = new Cita(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
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
                Cita cita = new Cita(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }
    
    public ArrayList<Cita> VerCitaActual(String codigo_paciente, String codigo_medico) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Cita WHERE Codigo_Paciente = ? AND Codigo_Medico = ? AND Estado IS NULL";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_paciente);
            PrSt.setString(2, codigo_medico);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getInt("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getString("Especialidad"), rs.getDate("Fecha"), rs.getString("Hora"));
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }
    
    public String ObtenerHora(int hora){
        String Hora = String.valueOf(hora);
        Hora = Hora + ":00";
        return Hora;
    }
    
    public Boolean ComprobarCita(String codigo_medico, Date fecha, int hora){
        boolean comprobacion = false;
        try {
            String texhora = ObtenerHora(hora);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Cita WHERE Codigo_Medico = ? AND Fecha = ? AND Hora = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_medico);
            PrSt.setDate(2, fecha);
            PrSt.setString(3, texhora);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                comprobacion = true;
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
             comprobacion = false;
        }
        return comprobacion;
    }
}
