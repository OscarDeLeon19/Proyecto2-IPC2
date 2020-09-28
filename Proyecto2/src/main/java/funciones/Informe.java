
package funciones;

import java.sql.Date;


public class Informe {
    
    private int codigo;
    private String codigo_paciente;
    private String codigo_medico;
    private String descripcion;
    private Date fecha;
    private String hora;

    public Informe(int codigo, String codigo_paciente, String codigo_medico, String descripcion, Date fecha, String hora) {
        this.codigo = codigo;
        this.codigo_paciente = codigo_paciente;
        this.codigo_medico = codigo_medico;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigo_paciente() {
        return codigo_paciente;
    }

    public void setCodigo_paciente(String codigo_paciente) {
        this.codigo_paciente = codigo_paciente;
    }

    public String getCodigo_medico() {
        return codigo_medico;
    }

    public void setCodigo_medico(String codigo_medico) {
        this.codigo_medico = codigo_medico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    
}
