
package funciones;

import java.sql.Date;


public class Resultado {
    
    private String codigo;
    private String codigo_paciente;
    private String codigo_medico;
    private String codigo_examen;
    private String codigo_laboratorista;
    private String orden;
    private String informe;
    private Date fecha;
    private String hora;

    public Resultado(String codigo, String codigo_paciente, String codigo_medico, String codigo_examen, String codigo_laboratorista, String orden, String informe, Date fecha, String hora) {
        this.codigo = codigo;
        this.codigo_paciente = codigo_paciente;
        this.codigo_medico = codigo_medico;
        this.codigo_examen = codigo_examen;
        this.codigo_laboratorista = codigo_laboratorista;
        this.orden = orden;
        this.informe = informe;
        this.fecha = fecha;
        this.hora = hora;
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

    public String getCodigo_examen() {
        return codigo_examen;
    }

    public void setCodigo_examen(String codigo_examen) {
        this.codigo_examen = codigo_examen;
    }

    public String getCodigo_laboratorista() {
        return codigo_laboratorista;
    }

    public void setCodigo_laboratorista(String codigo_laboratorista) {
        this.codigo_laboratorista = codigo_laboratorista;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
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

    public String getCodigo_medico() {
        return codigo_medico;
    }

    public void setCodigo_medico(String codigo_medico) {
        this.codigo_medico = codigo_medico;
    }

    
    
}
