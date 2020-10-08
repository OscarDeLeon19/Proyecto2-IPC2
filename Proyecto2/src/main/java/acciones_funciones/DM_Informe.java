package acciones_funciones;

import funciones.Informe;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import principal.Datos_Conexion;

public class DM_Informe extends Datos_Conexion {

    public DM_Informe() {
    }
    /**
     * Agrega un informe a la base de datos
     * @param informe El informe que se agregara
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
    public String AgregarInforme(Informe informe) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Informe (Codigo_Paciente, Codigo_Medico, Descripcion, Fecha, Hora)VALUES (?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, informe.getCodigo_paciente());
            PrSt.setString(2, informe.getCodigo_medico());
            PrSt.setString(3, informe.getDescripcion());
            PrSt.setDate(4, informe.getFecha());
            PrSt.setString(5, informe.getHora());
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
     * Obtiene el conteo de pacientes con mas informes realizados segun el medico, en un intervalo de tiempo
     * @param codigo_medico El codigo del medico
     * @param f1 La primera fecha
     * @param f2 La segunda fecha
     * @return La lista de Informes
     */
    public ArrayList<Informe> ReportePacienteConMasInformes(String codigo_medico, String f1, String f2) {
        ArrayList<Informe> lista = new ArrayList<>();
        try {
            Date fecha1 = Date.valueOf(f1);
            Date fecha2 = Date.valueOf(f2);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT COUNT(Codigo_Paciente) AS Informes, Codigo_Paciente FROM Informe WHERE Codigo_Medico = ? AND Fecha BETWEEN ? AND ? GROUP BY Codigo_Paciente ORDER BY COUNT(Codigo_Paciente) DESC";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_medico);
            PrSt.setDate(2, fecha1);
            PrSt.setDate(3, fecha2);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Informe informe = new Informe(rs.getInt("Informes"), rs.getString("Codigo_Paciente"), "XXXXX", "XXXXX", fecha2, "XXXXX");
                lista.add(informe);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista.clear();
        }
        return lista;
    }
    /**
     * Obtiene un conteo de los medicos con mas informes realizados en el hospital, en un intervalo de tiempo.
     * @param f1 La primera fecha
     * @param f2 La segunda fecha
     * @return La lista de informes
     */
    public ArrayList<Informe> ReporteADMMedicosConMasInformes(String f1, String f2) {
        ArrayList<Informe> lista = new ArrayList<>();
        try {
            Date fecha1 = Date.valueOf(f1);
            Date fecha2 = Date.valueOf(f2);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT COUNT(Codigo_Medico) AS Informes, Codigo_Medico FROM Informe WHERE Fecha BETWEEN ? AND ? GROUP BY Codigo_Medico ORDER BY COUNT(Codigo_Medico) DESC LIMIT 10";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setDate(1, fecha1);
            PrSt.setDate(2, fecha2);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Informe informe = new Informe(rs.getInt("Informes"), "xxxx", rs.getString("Codigo_Medico"), "xxxx", Date.valueOf("2020-05-10"), "XXXX");
                lista.add(informe);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista.clear();
        }
        return lista;
    }
}
