package acciones_usuarios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import principal.Datos_Conexion;
import usuarios.Paciente;

public class DM_Paciente extends Datos_Conexion {

    public DM_Paciente() {

    }
    /**
     * Valida si un paciente se encuentra en la base de datos
     * @param codigo El codigo del paciente
     * @param contraseña La contraseña del paciente
     * @return El paciente de la base de datos
     */
    public Paciente Validar(String codigo, String contraseña){
        Paciente paciente = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Paciente WHERE Codigo = ? AND Contraseña = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            PrSt.setString(2, ObtenerEncriptacion(contraseña));
            rs = PrSt.executeQuery();
            while (rs.next()) {
               Paciente nuevo = new Paciente(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("Sexo"), rs.getDate("Fecha_De_Nacimiento"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getDouble("Peso"), rs.getString("Tipo_De_Sangre"), rs.getString("Correo_Electronico"), rs.getString("Contraseña"));
               paciente = nuevo;
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            paciente = null;
        }
        return paciente;
    }
    /**
     * Obtiene todos los pacientes que se encuentran en la base de datos
     * @return La lista de pacientes
     */
    public ArrayList<Paciente> VerPacientes() {
        ArrayList<Paciente> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Paciente";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Paciente paciente = new Paciente(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("Sexo"), rs.getDate("Fecha_De_Nacimiento"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getDouble("Peso"), rs.getString("Tipo_De_Sangre"), rs.getString("Correo_Electronico"), rs.getString("Contraseña"));
                lista.add(paciente);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }
    /**
     * Obtiene un paciente en base a su codigo
     * @param codigo El codigo del paciente
     * @return El paciente obtenido
     */
    public Paciente VerPacientePorCodigo(String codigo) {
        Paciente paciente = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Paciente WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Paciente nuevo = new Paciente(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("Sexo"), rs.getDate("Fecha_De_Nacimiento"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getDouble("Peso"), rs.getString("Tipo_De_Sangre"), rs.getString("Correo_Electronico"), rs.getString("Contraseña"));
                paciente = nuevo;
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            paciente = null;
        }
        return paciente;
    }
    /**
     * Agrega un paciente a la base de datos
     * @param paciente El paciente que agregaremos
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
    public String AgregarPaciente(Paciente paciente) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Paciente (Codigo, Nombre, Sexo, Fecha_De_Nacimiento, DPI, Telefono, Peso, Tipo_De_Sangre, Correo_Electronico, Contraseña)VALUES (?,?,?,?,?,?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, paciente.getCodigo());
            PrSt.setString(2, paciente.getNombre());
            PrSt.setString(3, paciente.getSexo());
            PrSt.setDate(4, paciente.getFecha_de_nacimiento());
            PrSt.setString(5, paciente.getDpi());
            PrSt.setInt(6, paciente.getTelefono());
            PrSt.setDouble(7, paciente.getPeso());
            PrSt.setString(8, paciente.getTipo_sangre());
            PrSt.setString(9, paciente.getCorreo());
            PrSt.setString(10, ObtenerEncriptacion(paciente.getContraseña()));
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
     * Modifica un paciente en la base de datos
     * @param paciente El paciene que se va a modificar
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
    public String ModificarPaciente(Paciente paciente) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "UPDATE Paciente SET Nombre = ?, Sexo = ?, Fecha_De_Nacimiento = ? , DPI = ?, Telefono = ?, Peso = ?, Tipo_De_Sangre = ?, Correo_Electronico = ? WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, paciente.getNombre());
            PrSt.setString(2, paciente.getSexo());
            PrSt.setDate(3, paciente.getFecha_de_nacimiento());
            PrSt.setString(4, paciente.getDpi());
            PrSt.setInt(5, paciente.getTelefono());
            PrSt.setDouble(6, paciente.getPeso());
            PrSt.setString(7, paciente.getTipo_sangre());
            PrSt.setString(8, paciente.getCorreo());
            PrSt.setString(9, paciente.getCodigo());
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Informacion modificada";
            } else {
                mensaje = "Fallo al modificar";
            }
            PrSt.close();
        } catch (Exception e) {
            mensaje = e.toString();
        }
        return mensaje;
    }
    /**
     * Elimina un paciente de la base de datos
     * @param codigo El codigo del paciente
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
    public String EliminarPaciente(String codigo) {
        String mensaje;
        try {
            PreparedStatement PrSt;
            String Query = "DELETE FROM Paciente WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Informacion eliminada";
            } else {
                mensaje = "No existe ese codigo";
            }
        } catch (Exception e) {
            mensaje = e.toString();
        }
        return mensaje;
    }

}
