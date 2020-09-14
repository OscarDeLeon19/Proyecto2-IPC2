package usuarios;

public class Administrador {

    private String codigo;
    private String nombre;
    private int dpi;
    private String contraseña;

    public Administrador(String codigo, String nombre, int dpi, String contraseña) {
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

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
