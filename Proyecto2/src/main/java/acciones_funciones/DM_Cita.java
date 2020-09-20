package acciones_funciones;

import funciones.Cita;
import funciones.Resultado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import principal.Datos_Conexion;

public class DM_Cita extends Datos_Conexion {

    public DM_Cita() {
    }

    public String AgregarCita(Cita cita) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Cita (Codigo, Codigo_Paciente, Codigo_Medico, Fecha, Hora)VALUES (?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, cita.getCodigo());
            PrSt.setString(2, cita.getCodigo_paciente());
            PrSt.setString(3, cita.getCodigo_medico());
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
                Cita nueva_cita = new Cita(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getDate("Fecha"), rs.getString("Hora"));
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
                Cita cita = new Cita(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getDate("Fecha"), rs.getString("Hora"));
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
                Cita cita = new Cita(rs.getString("Codigo"), rs.getString("Codigo_Paciente"), rs.getString("Codigo_Medico"), rs.getDate("Fecha"), rs.getString("Hora"));
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
