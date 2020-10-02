
package usuarios;


public class Especialidad{
    
    private int codigo;
    private String codigo_medico;
    private String titulo;

    public Especialidad(String codigo_medico, String titulo) {
        this.codigo_medico = codigo_medico;
        this.titulo = titulo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigo_medico() {
        return codigo_medico;
    }

    public void setCodigo_medico(String codigo_medico) {
        this.codigo_medico = codigo_medico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
}
