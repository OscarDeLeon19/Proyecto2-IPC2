package usuarios;

import java.sql.Date;
import java.util.ArrayList;

public class Medico {

    private String codigo;
    private String nombre;
    private int numero_colegiado;
    private String dpi;
    private int telefono;
    private String correo;
    private String hora_entrada;
    private String hora_salida;
    private Date fecha_inicio;
    private String contraseña;
    private ArrayList<Especialidad> especialidades = new ArrayList<>();

    public Medico(String codigo, String nombre, int numero_colegiado, String dpi, int telefono, String correo, String hora_entrada, String hora_salida, Date fecha_inicio, String contraseña) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.numero_colegiado = numero_colegiado;
        this.dpi = dpi;
        this.telefono = telefono;
        this.correo = correo;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.fecha_inicio = fecha_inicio;
        this.contraseña = contraseña;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero_colegiado() {
        return numero_colegiado;
    }

    public void setNumero_colegiado(int numero_colegiado) {
        this.numero_colegiado = numero_colegiado;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ArrayList<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(ArrayList<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public String AñadirEspecialidad(String titulo) {
        Especialidad especialidad = new Especialidad(codigo, titulo);
        String mensaje = null;
        if (especialidades.size() > 0) {
            boolean especialidad_repetida = false;
            for (int i = 0; i < especialidades.size(); i++) {
                if (titulo.equals(especialidades.get(i).getTitulo())) {
                    especialidad_repetida = true;
                }
            }
            if (especialidad_repetida == true) {
                mensaje = "Este medico ya tiene la especialidad";
            } else {
                especialidades.add(especialidad);
                mensaje = "Especialidad Añadida";
            }
        } else {
            especialidades.add(especialidad);
            mensaje = "Especialidad Añadida";
        }
        return mensaje;
    }

}
