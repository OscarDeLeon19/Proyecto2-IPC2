package acciones_usuarios;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import principal.Datos_Conexion;
import usuarios.Especialidad;
import usuarios.Medico;

public class DM_Medico extends Datos_Conexion {

    public DM_Medico() {
    }

    public ArrayList<Medico> VerMedicos() {
        ArrayList<Medico> lista_medicos = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Medico c JOIN Especialidad e ON c.Codigo = e.Codigo_Medico";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Medico medico = new Medico(rs.getString("Codigo"), rs.getString("Nombre"), rs.getInt("Numero_De_Colegiado"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getString("Correo_Electronico"), rs.getString("Hora_Entrada"), rs.getString("Hora_Salida"), rs.getDate("Fecha_Inicio"), rs.getString("Contraseña"));
                medico.setTitulo(rs.getString("Titulo"));
                lista_medicos.add(medico);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista_medicos.clear();
        }
        return lista_medicos;
    }

    public Medico VerMedicoPorCodigo(String codigo) {
        Medico medico = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Medico WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Medico nuevo = new Medico(rs.getString("Codigo"), rs.getString("Nombre"), rs.getInt("Numero_De_Colegiado"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getString("Correo_Electronico"), rs.getString("Hora_Entrada"), rs.getString("Hora_Salida"), rs.getDate("Fecha_Inicio"), rs.getString("Contraseña"));
                medico = nuevo;
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            medico = null;
        }
        return medico;
    }

    public ArrayList<Medico> VerMedicoPorNombre(String Nombre) {
        ArrayList<Medico> lista_medicos = new ArrayList<>();
        Nombre = "%" + Nombre + "%";
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Medico c JOIN Especialidad e ON c.Codigo = e.Codigo_Medico WHERE Nombre LIKE ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, Nombre);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Medico medico = new Medico(rs.getString("Codigo"), rs.getString("Nombre"), rs.getInt("Numero_De_Colegiado"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getString("Correo_Electronico"), rs.getString("Hora_Entrada"), rs.getString("Hora_Salida"), rs.getDate("Fecha_Inicio"), rs.getString("Contraseña"));
                medico.setTitulo(rs.getString("Titulo"));
                lista_medicos.add(medico);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista_medicos.clear();
        }
        return lista_medicos;
    }

    public ArrayList<Medico> VerMedicoPorTitulo(String titulo) {
        ArrayList<Medico> lista_medicos = new ArrayList<>();
        titulo = "%" + titulo + "%";
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Medico c JOIN Especialidad e ON c.Codigo = e.Codigo_Medico WHERE Titulo LIKE ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, titulo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Medico medico = new Medico(rs.getString("Codigo"), rs.getString("Nombre"), rs.getInt("Numero_De_Colegiado"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getString("Correo_Electronico"), rs.getString("Hora_Entrada"), rs.getString("Hora_Salida"), rs.getDate("Fecha_Inicio"), rs.getString("Contraseña"));
                medico.setTitulo(rs.getString("Titulo"));
                lista_medicos.add(medico);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista_medicos.clear();
        }
        return lista_medicos;
    }

    public ArrayList<Medico> VerMedicoPorHorario(String inicio, String salida) {
        ArrayList<Medico> lista_medicos = new ArrayList<>();
        inicio = "%" + inicio + "%";
        salida = "%" + salida + "%";
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Medico c JOIN Especialidad e ON c.Codigo = e.Codigo_Medico WHERE Hora_Entrada LIKE ? AND Hora_Salida LIKE ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, inicio);
            PrSt.setString(2, salida);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Medico medico = new Medico(rs.getString("Codigo"), rs.getString("Nombre"), rs.getInt("Numero_De_Colegiado"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getString("Correo_Electronico"), rs.getString("Hora_Entrada"), rs.getString("Hora_Salida"), rs.getDate("Fecha_Inicio"), rs.getString("Contraseña"));
                medico.setTitulo(rs.getString("Titulo"));
                lista_medicos.add(medico);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista_medicos.clear();
        }
        return lista_medicos;
    }

    public ArrayList<Medico> VerMedicoPorFecha(String fecha_medico) {
        ArrayList<Medico> lista_medicos = new ArrayList<>();
        try {
            Date fecha = Date.valueOf(fecha_medico);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Medico c JOIN Especialidad e ON c.Codigo = e.Codigo_Medico WHERE Fecha_Inicio >= ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setDate(1, fecha);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Medico medico = new Medico(rs.getString("Codigo"), rs.getString("Nombre"), rs.getInt("Numero_De_Colegiado"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getString("Correo_Electronico"), rs.getString("Hora_Entrada"), rs.getString("Hora_Salida"), rs.getDate("Fecha_Inicio"), rs.getString("Contraseña"));
                medico.setTitulo(rs.getString("Titulo"));
                lista_medicos.add(medico);
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista_medicos.clear();
        }
        return lista_medicos;
    }

    public ArrayList<Especialidad> VerEspecialidades() {
        ArrayList<Especialidad> lista_especialidades = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Especialidad ORDER BY Codigo_Medico";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Especialidad especialidad = new Especialidad(rs.getString("Codigo_Medico"), rs.getString("Titulo"));
                lista_especialidades.add(especialidad);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista_especialidades.clear();
        }
        return lista_especialidades;
    }

    public ArrayList<Especialidad> VerEspecialidadesPorCodigo(String codigo) {
        ArrayList<Especialidad> lista_especialidades = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Especialidad WHERE Codigo_Medico = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Especialidad especialidad = new Especialidad(rs.getString("Codigo_Medico"), rs.getString("Titulo"));
                lista_especialidades.add(especialidad);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista_especialidades.clear();
        }
        return lista_especialidades;
    }

    public String AgregarMedico(Medico medico) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Medico (Codigo, Nombre, Numero_De_Colegiado, DPI, Telefono, Correo_Electronico, Hora_Entrada, Hora_Salida, Fecha_Inicio, Contraseña)VALUES (?,?,?,?,?,?,?,?,?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, medico.getCodigo());
            PrSt.setString(2, medico.getNombre());
            PrSt.setInt(3, medico.getNumero_colegiado());
            PrSt.setString(4, medico.getDpi());
            PrSt.setInt(5, medico.getTelefono());
            PrSt.setString(6, medico.getCorreo());
            PrSt.setString(7, medico.getHora_entrada());
            PrSt.setString(8, medico.getHora_salida());
            PrSt.setDate(9, medico.getFecha_inicio());
            PrSt.setString(10, ObtenerEncriptacion(medico.getContraseña()));
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                ArrayList<Especialidad> lista_especialidades = medico.getEspecialidades();
                mensaje = "Informacion Ingresada";
                if (lista_especialidades.size() > 0) {
                    for (int i = 0; i < lista_especialidades.size(); i++) {
                        String comprobar = AgregarEspecialidad(lista_especialidades.get(i));
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

    public String AgregarEspecialidad(Especialidad especialidad) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "INSERT INTO Especialidad (Codigo_Medico, Titulo) VALUES (?,?)";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, especialidad.getCodigo_medico());
            PrSt.setString(2, especialidad.getTitulo());
            PrSt.executeUpdate();
            PrSt.close();
        } catch (Exception e) {
            mensaje = e.toString();
        }
        return mensaje;
    }

    public String ModificarMedico(Medico medico) {
        String mensaje = null;
        try {
            PreparedStatement PrSt;
            String Query = "UPDATE Medico SET Nombre = ?, Numero_De_Colegiado = ?, DPI = ?, Telefono = ?, Correo_Electronico = ?, Hora_Entrada = ?, Hora_Salida = ?, Fecha_Inicio = ? WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, medico.getNombre());
            PrSt.setInt(2, medico.getNumero_colegiado());
            PrSt.setString(3, medico.getDpi());
            PrSt.setInt(4, medico.getTelefono());
            PrSt.setString(5, medico.getCorreo());
            PrSt.setString(6, medico.getHora_entrada());
            PrSt.setString(7, medico.getHora_salida());
            PrSt.setDate(8, medico.getFecha_inicio());
            PrSt.setString(9, medico.getCodigo());
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

    public String EliminarMedico(String codigo) {
        String mensaje;
        try {
            PreparedStatement PrSt;
            String Query = "DELETE FROM Medico WHERE Codigo = ?";
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

    public String EliminarEspecialidad(String codigo_medico, String titulo) {
        String mensaje;
        try {
            PreparedStatement PrSt;
            String Query = "DELETE FROM Especialidad WHERE Codigo_Medico = ? AND Titulo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_medico);
            PrSt.setString(2, titulo);
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Informacion eliminada";
            } else {
                mensaje = "No existe ese codigo o titulo";
            }
            PrSt.close();
        } catch (Exception e) {
            mensaje = e.toString();
        }
        return mensaje;
    }

    public String EliminarEspecialidades(String codigo_medico) {
        String mensaje;
        try {
            PreparedStatement PrSt;
            String Query = "DELETE FROM Especialidad WHERE Codigo_Medico = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_medico);
            int ejecucion = PrSt.executeUpdate();
            if (ejecucion > 0) {
                mensaje = "Informacion eliminada";
            } else {
                mensaje = "No existe ese codigo o titulo";
            }
            PrSt.close();
        } catch (Exception e) {
            mensaje = e.toString();
        }
        return mensaje;
    }

    public Boolean ComprobarEspecialidad(String codigo_medico, String titulo) {
        Boolean comprobacion = false;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Especialidad WHERE Codigo_Medico = ? AND Titulo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_medico);
            PrSt.setString(2, titulo);
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

    public Boolean ComprobarHorario(String codigo_medico, int hora) {
        Boolean comprobacion = false;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Medico WHERE Codigo = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo_medico);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                String hora_inicio = rs.getString("Hora_Entrada");
                String hora_salida = rs.getString("Hora_Salida");
                int hora1 = ObtenerHora(hora_inicio);
                int hora2 = ObtenerHora(hora_salida);
                if (hora >= hora1 && hora <= hora2) {
                    comprobacion = true;
                }
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            comprobacion = false;
        }
        return comprobacion;
    }

    public int ObtenerHora(String hora) {
        int signo = 0;
        for (int i = 0; i < hora.length(); i++) {
            int j = i + 1;
            if (":".equals(hora.substring(i, j))) {
                signo = i;
            }
        }
        int numero = Integer.parseInt(hora.substring(0, signo));
        return numero;
    }
}
