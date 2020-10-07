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
    /**
     * Cambia el estado de la cita a Realizada en la base de datos
     * @param cita La cita a modificar 
     * @return Si la modificacion fue exitosa o no
     */
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
    /**
     * Obtiene algunos parametros del informe despues de realizar una cita
     * @param codigo El codigo de la cita realizada
     * @return El informe
     */
    public Informe ObtenerInforme(String codigo) {
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
    /**
     * Muestra todas las citas en las que ha estado un paciente
     * @param codigo_paciente El codigo del paciente
     * @return La lista de citas
     */
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
    /**
     * Obtiene todas las citas que aun no se han realizado de un paciente
     * @param codigo_paciente El codigo del paciente
     * @return La lista de citas
     */
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
    /**
     * Obtiene las ultimas 5 citas realizadas por un paciente
     * @param codigo_paciente El codigo del paciente
     * @return La lista de citas
     */
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
    /**
     * Muetra todas las citas realizadas con un medico en especifico en un intervalo de tiempo
     * @param codigo_paciente El codigo del paciente
     * @param nombre_medico El nombre del medico
     * @param f1 La primera fecha
     * @param f2 La segunda fecha
     * @return  La lista de citas
     */
    public ArrayList<Cita> ReporteVerCitasPorMedicoYFechas(String codigo_paciente, String nombre_medico, String f1, String f2) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            nombre_medico = "%" + nombre_medico + "%";
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
    /**
     * Muestra todas las citas realizadas por un medico en un intervalo de tiempo
     * @param codigo_medico El codigo del medico
     * @param f1 La primera fecha
     * @param f2 La segunda fecha
     * @return La lista de citas
     */
    public ArrayList<Cita> ReporteCitasEnIntervaloDeTiempo(String codigo_medico, String f1, String f2) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            Date fecha1 = Date.valueOf(f1);
            Date fecha2 = Date.valueOf(f2);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Cita WHERE Codigo_Medico = ? AND Estado = 'Realizada' AND Fecha BETWEEN ? AND ? ORDER BY Fecha DESC";
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
        } catch (Exception e) {
            lista.clear();
        }
        return lista;
    }
    /**
     * Obtiene las citas de un medico para la fecha ingresada
     * @param codigo_medica El codigo del medico
     * @param f La fecha del dia
     * @return La lista de citas
     */
    public ArrayList<Cita> ReportesCitasDiaActual(String codigo_medica, String f) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            Date fecha = Date.valueOf(f);
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
    /**
     * Muestra Las citas con un paciente que aun no se han realizado
     * @param codigo_paciente El codigo del pacietne
     * @param codigo_medico El codigo del medico
     * @return 
     */
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
    /**
     * Obtiene una hora de un numero entero
     * @param hora El numero entero de la hora
     * @return La hora transformada
     */
    public String ObtenerHora(int hora) {
        if (hora < 24 && hora >= 0) {
            String Hora = String.valueOf(hora);
            Hora = Hora + ":00";
            return Hora;
        } else {
            return null;
        }
    }
    /**
     * Comprueba que el medico no tenga una cita en curso en una fecha y en una hora
     * @param codigo_medico El codigo del medico
     * @param fecha La fecha del dia
     * @param hora La hora de la cita
     * @return Si el medico tiene cita o no
     */
    public Boolean ComprobarCita(String codigo_medico, Date fecha, int hora) {
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
    /**
     * Obtiene las ganancias generada por un medico en citas, en un intervalo de tiempo
     * @param f1 La primera fecha
     * @param f2 La segunda fecha
     * @return  La lista de citas
     */
    public ArrayList<Cita> ReporteVerGananciasMedico(String f1, String f2) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            Date fecha1 = Date.valueOf(f1);
            Date fecha2 = Date.valueOf(f2);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT SUM(o.Costo) AS Ganancia, c.Codigo_Medico FROM Cita c JOIN Consulta o ON c.Especialidad = o.Tipo WHERE c.Fecha BETWEEN ? AND ? GROUP BY Codigo_Medico ORDER BY SUM(o.Costo) DESC";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setDate(1, fecha1);
            PrSt.setDate(2, fecha2);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getInt("Ganancia"), "xxxx", rs.getString("Codigo_Medico"), "xxxx", Date.valueOf("2020-05-10"), "xxxx");
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista.clear();
        }
        return lista;
    }
    /**
     * Obtiene un conteo de los medicos con menos citas realizadas en el hospital
     * @param f1 La primera fecha
     * @param f2 La segunda fecha
     * @return La lista de citas
     */
    public ArrayList<Cita> ReporteMedicosCitasMenores(String f1, String f2) {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            Date fecha1 = Date.valueOf(f1);
            Date fecha2 = Date.valueOf(f2);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT COUNT(Codigo_Medico) AS Citas, Codigo_Medico FROM Cita WHERE Fecha BETWEEN ? AND ? GROUP BY Codigo_Medico ORDER BY COUNT(Codigo_Medico) ASC LIMIT 5";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setDate(1, fecha1);
            PrSt.setDate(2, fecha2);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getInt("Citas"), "xxxx", rs.getString("Codigo_Medico"), "xxxx", Date.valueOf("2020-05-10"), "xxxx");
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista.clear();
        }
        return lista;
    }
    /**
     * Obtiene todas las ganancias generadas por un paciente en citas
     * @return La lista de citas
     */
    public ArrayList<Cita> ReporteVerGananciasPaciente() {
        ArrayList<Cita> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT SUM(o.Costo) AS Ganancia, c.Codigo_Paciente FROM Cita c JOIN Consulta o ON c.Especialidad = o.Tipo GROUP BY Codigo_Paciente ORDER BY SUM(o.Costo) DESC";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(rs.getInt("Ganancia"), rs.getString("Codigo_Paciente"), "xxxx", "xxxx", Date.valueOf("2020-05-10"), "xxxx");
                lista.add(cita);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista.clear();
        }
        return lista;
    }
}
