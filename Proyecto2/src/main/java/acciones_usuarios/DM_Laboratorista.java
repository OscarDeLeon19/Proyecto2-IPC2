package acciones_usuarios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import principal.Datos_Conexion;
import usuarios.Dia_de_trabajo;
import usuarios.Laboratorista;

public class DM_Laboratorista extends Datos_Conexion {

    public DM_Laboratorista() {
    }
    /**
     * Valida si existe el laboratorista en la base de datos
     * @param codigo El codigo del laboratorista
     * @param contraseña La constraseña del laboratorista
     * @return El laboratorista validado
     */
    public Laboratorista Validar(String codigo, String contraseña) {
        Laboratorista laboratorista = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Laboratorista WHERE Codigo = ? AND Contraseña = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            PrSt.setString(2, ObtenerEncriptacion(contraseña));
            rs = PrSt.executeQuery();
            while (rs.next()) {
                laboratorista = new Laboratorista(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("Numero_de_Registro"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getString("Tipo_De_Examen"), rs.getString("Correo_Electronico"), rs.getDate("Fecha_Inicio"), rs.getString("Contraseña"));
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            laboratorista = null;
        }
        return laboratorista;
    }
    /**
     * Muestra todos los laboratoristas de la base de datos
     * @return La lista de laboratoristas
     */
    public ArrayList<Laboratorista> VerLaboratoristas() {
        ArrayList<Laboratorista> lista_laboratorista = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Laboratorista ORDER BY Codigo";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Laboratorista lab = new Laboratorista(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("Numero_de_Registro"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getString("Tipo_De_Examen"), rs.getString("Correo_Electronico"), rs.getDate("Fecha_Inicio"), rs.getString("Contraseña"));
                lista_laboratorista.add(lab);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista_laboratorista.clear();
        }
        return lista_laboratorista;
    }
    /**
     * Muestra un laboratorista en base a su codigo
     * @param codigo El codigo del laboratorista
     * @return El laboratorista obtenido
     */
    public Laboratorista VerLaboratoristasPorCodigo(String codigo) {
        Laboratorista laboratorista = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Laboratorista WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Laboratorista lab = new Laboratorista(rs.getString("Codigo"), rs.getString("Nombre"), rs.getString("Numero_de_Registro"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getString("Tipo_De_Examen"), rs.getString("Correo_Electronico"), rs.getDate("Fecha_Inicio"), rs.getString("Contraseña"));
                laboratorista = lab;
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            laboratorista = null;
        }
        return laboratorista;
    }
    /**
     * Muestra todos los dias de trabajo de cada laboratorista
     * @return La lista de dias de trabajo
     */
    public ArrayList<Dia_de_trabajo> VerDias() {
        ArrayList<Dia_de_trabajo> lista_dias = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Dia_De_Trabajo ORDER BY Codigo_Laboratorista";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Dia_de_trabajo dia = new Dia_de_trabajo(rs.getString("Codigo_Laboratorista"), rs.getString("Dia"));
                dia.setID(rs.getInt("ID"));
                lista_dias.add(dia);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista_dias.clear();
        }
        return lista_dias;
    }
    /**
     * Obtiene un dia de trabajo en base al ID
     * @param ID El ID del dia de trabajo
     * @return El dia de trabajo
     */
    public Dia_de_trabajo VerDiasPorCodigo(int ID) {
        Dia_de_trabajo dia = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Dia_De_Trabajo WHERE ID = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setInt(1, ID);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                dia = new Dia_de_trabajo(rs.getString("Codigo_Laboratorista"), rs.getString("Dia"));
                dia.setID(rs.getInt("ID"));
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            dia = null;
        }
        return dia;
    }
    /**
     * Añade un laboratorista a la base de datos
     * @param laboratorista El laboratorista que vamos a agregar
     * @return El mensaje que indica si fue efectivo el ingreso del laboratorista o no
     */
    public String AñadirLaboratorista(Laboratorista laboratorista) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Laboratorista (Codigo, Nombre, Numero_de_Registro, DPI, Telefono, Tipo_De_Examen, Correo_Electronico, Fecha_Inicio, Contraseña)VALUES (?,?,?,?,?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, laboratorista.getCodigo());
            PrSt.setString(2, laboratorista.getNombre());
            PrSt.setString(3, laboratorista.getNumero_registro());
            PrSt.setString(4, laboratorista.getDpi());
            PrSt.setInt(5, laboratorista.getTelefono());
            PrSt.setString(6, laboratorista.getTipo_examen());
            PrSt.setString(7, laboratorista.getCorreo());
            PrSt.setDate(8, laboratorista.getFecha_inicio());
            PrSt.setString(9, ObtenerEncriptacion(laboratorista.getContraseña()));
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                ArrayList<Dia_de_trabajo> lista_dias = laboratorista.getDias_de_trabajo();
                mensaje = "Informacion Ingresada";
                if (lista_dias.size() > 0) {
                    for (int i = 0; i < lista_dias.size(); i++) {
                        String comprobar = AgregarDiaDeTrabajo(lista_dias.get(i));
                        if (comprobar != null) {
                            mensaje = comprobar;
                        }
                    }
                }
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
     * Agrega un dia de trabajo de un laboratorista a la base de datos
     * @param dia El dia de trabajo
     * @return Si la operacion fue exitosa o no
     */
    public String AgregarDiaDeTrabajo(Dia_de_trabajo dia) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Dia_De_Trabajo (Codigo_Laboratorista, Dia) VALUES (?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, dia.getCodigo_laboratorista());
            PrSt.setString(2, dia.getDia());
            PrSt.executeUpdate();
            PrSt.close();
        } catch (Exception e) {
            mensaje = e.toString();
        }
        return mensaje;
    }
    /**
     * Modifica un laboratorista en la base de datos
     * @param laboratorista El laboratorista que vamos a modificar
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
    public String ModificarLaboratorista(Laboratorista laboratorista) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "UPDATE Laboratorista SET Nombre = ?, Numero_de_Registro = ?, DPI = ?, Telefono = ?, Tipo_De_Examen = ?, Correo_Electronico = ?, Fecha_Inicio = ? WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, laboratorista.getNombre());
            PrSt.setString(2, laboratorista.getNumero_registro());
            PrSt.setString(3, laboratorista.getDpi());
            PrSt.setInt(4, laboratorista.getTelefono());
            PrSt.setString(5, laboratorista.getTipo_examen());
            PrSt.setString(6, laboratorista.getCorreo());
            PrSt.setDate(7, laboratorista.getFecha_inicio());
            PrSt.setString(8, laboratorista.getCodigo());
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Informacion modificada";
            } else {
                mensaje = "Fallo al modificar";
            }
            PrSt.close();
        } catch (SQLException e) {
            mensaje = e.toString();
        }
        return mensaje;
    }
    /**
     * Elimina un laboratorista de la base de datos
     * @param codigo El codigo del laboratorista que se eliminara
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
    public String EliminarLaboratorista(String codigo) {
        String mensaje;
        try {
            PreparedStatement PrSt;
            String Query = "DELETE FROM Laboratorista WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Informacion eliminada";
            } else {
                mensaje = "No existe ese codigo";
            }
            PrSt.close();
        } catch (Exception e) {
            mensaje = e.toString();
        }
        return mensaje;
    }
    /**
     * Elimina un dia de trabajo de laboratorista de la base de datos
     * @param codigo_laboratorista el codigo del laboratorista
     * @param dia El dia que se va a eliminar
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
    public String EliminarDia(String codigo_laboratorista, String dia) {
        String mensaje;
        try {
            PreparedStatement PrSt;
            String Query = "DELETE FROM Dia_De_Trabajo WHERE Codigo_Laboratorista = ? AND Dia = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_laboratorista);
            PrSt.setString(2, dia);
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Informacion eliminada";
            } else {
                mensaje = "No existe ese codigo o dia";
            }
            PrSt.close();
        } catch (Exception e) {
            mensaje = e.toString();
        }
        return mensaje;
    }
    /**
     * Comprueba si un dia ya esta en la base de datos
     * @param codigo_laboratorista El codigo del laboratorista
     * @param dia El dia que se va a comprobar
     * @return Si el dia esta en la base de datos o no
     */
    public Boolean ComprobarDia(String codigo_laboratorista, String dia) {
        boolean comprobacion = false;
        try {
            PreparedStatement PrSt;
            ResultSet rs;
            String Query = "SELECT * FROM Dia_De_Trabajo WHERE Codigo_Laboratorista = ? AND Dia = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_laboratorista);
            PrSt.setString(2, dia);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                comprobacion = true;
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            comprobacion = false;
        }
        return comprobacion;
    }
}
