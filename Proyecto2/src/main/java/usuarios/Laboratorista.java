package usuarios;

import java.sql.Date;
import java.util.ArrayList;

public class Laboratorista {

    private String codigo;
    private String nombre;
    private int numero_registro;
    private String dpi;
    private int telefono;
    private String tipo_examen;
    private String correo;
    private Date fecha_inicio;
    private String contraseña;
    private ArrayList<Dia_de_trabajo> dias_de_trabajo = new ArrayList<>();

    public Laboratorista(String codigo, String nombre, int numero_registro, String dpi, int telefono, String tipo_examen, String correo, Date fecha_inicio, String contraseña) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.numero_registro = numero_registro;
        this.dpi = dpi;
        this.telefono = telefono;
        this.tipo_examen = tipo_examen;
        this.correo = correo;
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

    public ArrayList<Dia_de_trabajo> getDias_de_trabajo() {
        return dias_de_trabajo;
    }

    public void setDias_de_trabajo(ArrayList<Dia_de_trabajo> dias_de_trabajo) {
        this.dias_de_trabajo = dias_de_trabajo;
    }

    public void AñadirDiaDeTrabajo(String Dia) {
        Dia_de_trabajo dia = new Dia_de_trabajo(codigo, Dia);
        dias_de_trabajo.add(dia);
    }

}
