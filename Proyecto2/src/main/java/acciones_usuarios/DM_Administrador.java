
package acciones_usuarios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import principal.Datos_Conexion;
import usuarios.Administrador;


public class DM_Administrador extends Datos_Conexion{

    public DM_Administrador() {
    }
    
    public Administrador Validar(String codigo, String contraseña) {
        Administrador administrador = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Administrador WHERE Codigo = ? AND Contraseña = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            PrSt.setString(2, ObtenerEncriptacion(contraseña));
            rs = PrSt.executeQuery();
            while (rs.next()) {
                administrador = new Administrador(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("DPI"), rs.getString("Contraseña"));
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            administrador = null;
        }
        return administrador;
    }
    
    public ArrayList<Administrador> VerAdministradores() {
        ArrayList<Administrador> lista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Administrador";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Administrador admin = new Administrador(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("DPI"), rs.getString("Contraseña"));
                lista.add(admin);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista.clear();
        }
        return lista;
    }
    
    public Administrador VerAdministradorPorCodigo(String codigo) {
        Administrador admin = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Administrador WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Administrador nuevo_admin = new Administrador(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("DPI"), rs.getString("Contraseña"));
                admin = nuevo_admin;
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            admin = null;
        }
        return admin;
    }
    
    public String AgregarAdministrador(Administrador administrador) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Administrador (Codigo, Nombre, DPI, Contraseña)VALUES (?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, administrador.getCodigo());
            PrSt.setString(2, administrador.getNombre());
            PrSt.setString(3, administrador.getDpi());
            PrSt.setString(4, ObtenerEncriptacion(administrador.getContraseña()));
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
    
    public String ModificarAdministrador(Administrador administrador) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "UPDATE Administrador SET Nombre = ?, DPI = ? WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);            
            PrSt.setString(1, administrador.getNombre());
            PrSt.setString(2, administrador.getDpi());
            PrSt.setString(3, administrador.getCodigo());
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Informacion Modificada";
            } else {
                mensaje = "Fallo al modificar";
            }
            PrSt.close();
        } catch (SQLException e) {
            mensaje = e.toString();
        }
        return mensaje;
    }
    
    public String EliminarAdministrador(String codigo) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "DELETE FROM Administrador WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);            
            PrSt.setString(1, codigo);
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Informacion Eliminada";
            } else {
                mensaje = "Fallo al eliminar";
            }
            PrSt.close();
        } catch (SQLException e) {
            mensaje = e.toString();
        }
        return mensaje;
    }
}
