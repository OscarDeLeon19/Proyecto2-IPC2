
package usuarios;

import java.sql.Date;

public class Paciente {
    
    private String codigo;
    private String nombre;
    private String sexo;
    private Date fecha_de_nacimiento;
    private int dpi;
    private int telefono;
    private double peso;
    private String tipo_sangre;
    private String correo;
    private String contraseña;

    public Paciente(String codigo, String nombre, String sexo, Date fecha_de_nacimiento, int dpi, int telefono, double peso, String tipo_sangre, String correo, String contraseña) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.dpi = dpi;
        this.telefono = telefono;
        this.peso = peso;
        this.tipo_sangre = tipo_sangre;
        this.correo = correo;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(Date fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getTipo_sangre() {
        return tipo_sangre;
    }

    public void setTipo_sangre(String tipo_sangre) {
        this.tipo_sangre = tipo_sangre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
