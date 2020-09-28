package acciones_funciones;

import funciones.Tipo_Examen;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import principal.Datos_Conexion;

public class DM_TipoExamen extends Datos_Conexion {

    public DM_TipoExamen() {
    }

    public String AgregarExamen(Tipo_Examen examen) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Tipo_Examen (Codigo, Nombre, Orden, Descripcion, Costo, Tipo_Informe)VALUES (?,?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, examen.getCodigo());
            PrSt.setString(2, examen.getNombre());
            PrSt.setBoolean(3, examen.isOrden());
            PrSt.setString(4, examen.getDescripcion());
            PrSt.setDouble(5, examen.getCosto());
            PrSt.setString(6, examen.getTipo_informe());
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

    public ArrayList<Tipo_Examen> VerExamenes() {
        ArrayList<Tipo_Examen> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Tipo_Examen";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Tipo_Examen examen = new Tipo_Examen(rs.getString("Codigo"), rs.getString("Nombre"), rs.getBoolean("Orden"), rs.getString("Descripcion"), rs.getDouble("Costo"), rs.getString("Tipo_Informe"));
                lista.add(examen);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }

    public Tipo_Examen VerExamenePorCodigo(String codigo) {
        Tipo_Examen examen = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Tipo_Examen WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Tipo_Examen nuevo_examen = new Tipo_Examen(rs.getString("Codigo"), rs.getString("Nombre"), rs.getBoolean("Orden"), rs.getString("Descripcion"), rs.getDouble("Costo"), rs.getString("Tipo_Informe"));
                examen = nuevo_examen;
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            examen = null;
        }
        return examen;
    }

    public Boolean ComprobarOrden(String codigo) {
        boolean comprobacion = false;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Tipo_Examen WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                comprobacion = rs.getBoolean("Orden");
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            comprobacion = false;
        }
        return comprobacion;
    }
    
    public String ObtenerTipo(String codigo) {
        String tipo = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Tipo_Examen WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                tipo = rs.getString("Tipo_Informe");
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            tipo = null;
        }
        return tipo;
    }
}
