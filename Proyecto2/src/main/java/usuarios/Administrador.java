package usuarios;

public class Administrador {

    private String codigo;
    private String nombre;
    private String dpi;
    private String contraseña;

    public Administrador(String codigo, String nombre, String dpi, String contraseña) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dpi = dpi;
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

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
