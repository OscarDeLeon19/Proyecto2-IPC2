package usuarios;

public class Dia_de_trabajo {

    private int ID;
    private String codigo_laboratorista;
    private String Dia;

    public Dia_de_trabajo(String codigo_laboratorista, String Dia) {
        this.codigo_laboratorista = codigo_laboratorista;
        this.Dia = Dia;
    }

    public String getCodigo_laboratorista() {
        return codigo_laboratorista;
    }

    public void setCodigo_laboratorista(String codigo_laboratorista) {
        this.codigo_laboratorista = codigo_laboratorista;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String Dia) {
        this.Dia = Dia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    
}
