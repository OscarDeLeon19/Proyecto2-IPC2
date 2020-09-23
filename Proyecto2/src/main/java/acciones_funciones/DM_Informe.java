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

    public String AgregarInforme(Informe informe) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Informe (Codigo, Codigo_Paciente, Codigo_Medico, Descripcion, Fecha, Hora)VALUES (?,?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, informe.getCodigo());
            PrSt.setString(2, informe.getCodigo_paciente());
            PrSt.setString(3, informe.getCodigo_medico());
            PrSt.setString(4, informe.getDescripcion());
            PrSt.setDate(5, informe.getFecha());
            PrSt.setString(6, informe.getHora());
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
    
    public ArrayList<String> ReportePacienteConMasInformes(String codigo_medico, Date fecha1, Date fecha2) {
        ArrayList<String> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT COUNT(Codigo_Paciente) AS Informes, Codigo_Paciente FROM Informe WHERE Codigo_Medico = ? AND Fecha BETWEEN ? AND ? GROUP BY Codigo_Paciente ORDER BY COUNT(Codigo_Paciente) DESC LIMIT 1";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_medico);
            PrSt.setDate(2, fecha1);
            PrSt.setDate(3, fecha2);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                String informes = rs.getString("Informes");
                String codigo = rs.getString("Codigo_Paciente");
                lista.add(informes);
                lista.add(codigo);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }
    
}
