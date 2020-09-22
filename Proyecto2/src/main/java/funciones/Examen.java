
package funciones;

import java.sql.Date;

public class Examen {
    
    private String codigo;
    private String codigo_paciente;
    private String codigo_medico;
    private String tipo_examen;
    private Date fecha;
    private String Orden;
    private String Estado;

    public Examen(String codigo, String codigo_paciente, String codigo_medico, String tipo_examen, Date fecha) {
        this.codigo = codigo;
        this.codigo_paciente = codigo_paciente;
        this.codigo_medico = codigo_medico;
        this.tipo_examen = tipo_examen;
        this.fecha = fecha;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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

    public String getTipo_examen() {
        return tipo_examen;
    }

    public void setTipo_examen(String tipo_examen) {
        this.tipo_examen = tipo_examen;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getOrden() {
        return Orden;
    }

    public void setOrden(String Orden) {
        this.Orden = Orden;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
       
}
