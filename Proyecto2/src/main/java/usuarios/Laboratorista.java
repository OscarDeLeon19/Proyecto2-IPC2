package usuarios;

import java.sql.Date;
import java.util.ArrayList;

public class Laboratorista {

    private String codigo;
    private String nombre;
    private int numero_registro;
    private int dpi;
    private int telefono;
    private String tipo_examen;
    private String correo;
    private String hora_salida;
    private Date fecha_inicio;
    private String contraseña;
    private ArrayList<String> dias_de_trabajo = new ArrayList<String>();

    public Laboratorista(String codigo, String nombre, int numero_registro, int dpi, int telefono, String tipo_examen, String correo, String hora_salida, Date fecha_inicio, String contraseña) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.numero_registro = numero_registro;
        this.dpi = dpi;
        this.telefono = telefono;
        this.tipo_examen = tipo_examen;
        this.correo = correo;
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

    public int getNumero_registro() {
        return numero_registro;
    }

    public void setNumero_registro(int numero_registro) {
        this.numero_registro = numero_registro;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getTipo_examen() {
        return tipo_examen;
    }

    public void setTipo_examen(String tipo_examen) {
        this.tipo_examen = tipo_examen;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public ArrayList<String> getDias_de_trabajo() {
        return dias_de_trabajo;
    }

    public void setDias_de_trabajo(ArrayList<String> dias_de_trabajo) {
        this.dias_de_trabajo = dias_de_trabajo;
    }

    public void AñadirDiaDeTrabajo(String Dia) {
        dias_de_trabajo.add(Dia);
    }

}
