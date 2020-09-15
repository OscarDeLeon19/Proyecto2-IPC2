
package usuarios;


public class Especialidad{
    
    private String codigo_medico;
    private String titulo;

    public Especialidad(String codigo_medico, String titulo) {
        this.codigo_medico = codigo_medico;
        this.titulo = titulo;
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
