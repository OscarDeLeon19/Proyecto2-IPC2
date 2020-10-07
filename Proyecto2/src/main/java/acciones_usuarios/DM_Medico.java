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
    /**
     * Valida si un medico se encuentra en la base de datos
     * @param codigo El codigo del medico
     * @param contraseña La contraseña del medico
     * @return El medico en la base de datos
     */
    public Medico Validar(String codigo, String contraseña) {
        Medico medico = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Medico WHERE Codigo = ? AND Contraseña = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setString(1, codigo);
            PrSt.setString(2, ObtenerEncriptacion(contraseña));
            rs = PrSt.executeQuery();
            while (rs.next()) {
                medico = new Medico(rs.getString("Codigo"), rs.getString("Nombre"), rs.getInt("Numero_De_Colegiado"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getString("Correo_Electronico"), rs.getString("Hora_Entrada"), rs.getString("Hora_Salida"), rs.getDate("Fecha_Inicio"), rs.getString("Contraseña"));
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            medico = null;
        }
        return medico;
    }
    /**
     * Obtiene a todos los medicos en la base de datos
     * @return La la lista de medicos
     */
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
    /**
     * Obtiene un medico de la base de datos en base a su codigo
     * @param codigo El codigo del medico
     * @return El medico de la base de datos
     */
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
    /**
     * Obtiene medicos en base al nombre
     * @param Nombre Nombre del medico
     * @return La lista de medicos
     */
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
    /**
     * Obtiene medicos de la base de datos en base a sus especialidades
     * @param titulo Titulo de la especialidad
     * @return La lista de medicos
     */
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
    /**
     * Obtiene mediocos en base al horario de trabajo
     * @param hora La hora en que se quiere la cita
     * @return La lista de medicos
     */
    public ArrayList<Medico> VerMedicoPorHorario(String hora) {
        ArrayList<Medico> lista_medicos = new ArrayList<>();
        try {
            int Hora = Integer.parseInt(hora);
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Medico c JOIN Especialidad e ON c.Codigo = e.Codigo_Medico";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                String horaI = rs.getString("Hora_Entrada");
                String horaS = rs.getString("Hora_Salida");
                int hora1 = ObtenerHora(horaI);
                int hora2 = ObtenerHora(horaS);
                if (Hora >= hora1 && Hora <= hora2) {
                    Medico medico = new Medico(rs.getString("Codigo"), rs.getString("Nombre"), rs.getInt("Numero_De_Colegiado"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getString("Correo_Electronico"), rs.getString("Hora_Entrada"), rs.getString("Hora_Salida"), rs.getDate("Fecha_Inicio"), rs.getString("Contraseña"));
                    medico.setTitulo(rs.getString("Titulo"));
                    lista_medicos.add(medico);
                }
            }
            PrSt.close();
            rs.close();
        } catch (Exception e) {
            lista_medicos.clear();
        }
        return lista_medicos;
    }
    /**
     * Obtienes a los medicos que tienen una fecha de inicio mayor a la fecha ingresada
     * @param fecha_medico La fecha ingresada para el intervalo
     * @return  La lista de medicos
     */
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
    /**
     * Obtiene todos los medicos de la empresa 
     * @return La lista de medicos
     */
    public ArrayList<Medico> VerTodosLosMedicos() {
        ArrayList<Medico> lista_medicos = new ArrayList<>();
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Medico";
            PrSt = conexion.prepareStatement(Query);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                Medico medico = new Medico(rs.getString("Codigo"), rs.getString("Nombre"), rs.getInt("Numero_De_Colegiado"), rs.getString("DPI"), rs.getInt("Telefono"), rs.getString("Correo_Electronico"), rs.getString("Hora_Entrada"), rs.getString("Hora_Salida"), rs.getDate("Fecha_Inicio"), rs.getString("Contraseña"));
                lista_medicos.add(medico);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista_medicos.clear();
        }
        return lista_medicos;
    }
    /**
     * Obtiene todas las especialidades de los medicos en la base de datos
     * @return La lista de especialidades
     */
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
                especialidad.setCodigo(rs.getInt("ID"));
                lista_especialidades.add(especialidad);
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            lista_especialidades.clear();
        }
        return lista_especialidades;
    }
    /**
     * Obtiene una especialidad en base a su ID
     * @param codigo El Id de la especialidad
     * @return La especialidad requerida
     */
    public Especialidad VerEspecialidadPorCodigo(int codigo) {
        Especialidad especialidad = null;
        try {
            PreparedStatement PrSt;
            ResultSet rs = null;
            String Query = "SELECT * FROM Especialidad WHERE ID = ?";
            PrSt = conexion.prepareStatement(Query);
            PrSt.setInt(1, codigo);
            rs = PrSt.executeQuery();
            while (rs.next()) {
                especialidad = new Especialidad(rs.getString("Codigo_Medico"), rs.getString("Titulo"));
                especialidad.setCodigo(rs.getInt("ID"));
            }
            PrSt.close();
            rs.close();
        } catch (SQLException e) {
            especialidad = null;
        }
        return especialidad;
    }
    /**
     * Agrega un medico a la base de datos
     * @param medico el medico que se agregara
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
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
    /**
     * Agrega una especialidad de un medico a la base de datos
     * @param especialidad La especialidad que se agregara
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
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
    /**
     * Modifica los datos del medico en la base de datos
     * @param medico El medico que se modificara
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
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
    /**
     * Elimina un medico de la base de datos
     * @param codigo El codigo del medico que se eliminara
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
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
    /**
     * Elimina una especialidad de la base de datos
     * @param codigo_medico El codigo del medico 
     * @param titulo el titulo de la especialidad
     * @return Un mensaje que indica si la operacion fue exitosa o no
     */
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
    
    /**
     * Comprueba si un medico ya tiene una especialidad a la base de datos
     * @param codigo_medico El codigo del medico
     * @param titulo El titulo de la especialidad
     * @return Si el medico tiene la especialidad o no
     */
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
    /**
     * Comprueba si un medico trabaja en el horario requerido para una cita
     * @param codigo_medico el codigo del medico
     * @param hora La hora en que se quiere realizar la cita
     * @return Si el medico trabaja en ese horario o no
     */
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
    /**
     * Convierte la hora en la base de datos a Entero
     * @param hora La hora en la base de datos
     * @return La hora en numero entero
     */
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
    /**
     * Convierte un numero entero a hora
     * @param hora La hora que convertiremos
     * @return La hora convertida
     */
    public String ConvertirHora(int hora) {
        if (hora < 24 && hora >= 0) {
            String Hora = String.valueOf(hora);
            Hora = Hora + ":00";
            return Hora;
        } else {
            return null;
        }
    }
}
