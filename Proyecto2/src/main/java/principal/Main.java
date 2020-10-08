package principal;

import acciones_funciones.DM_Cita;
import acciones_funciones.DM_Examen;
import acciones_funciones.DM_Informe;
import acciones_funciones.DM_Resultado;
import acciones_usuarios.DM_Medico;
import acciones_usuarios.DM_Paciente;
import funciones.Cita;
import funciones.Examen;
import funciones.Informe;
import funciones.Resultado;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import usuarios.Paciente;

public class Main {

    public static void main(String[] args) {
        DM_Resultado dmres = new DM_Resultado();
        DM_Cita dmcit = new DM_Cita();
        DM_Informe dminf = new DM_Informe();
        DM_Examen d = new DM_Examen();
//        Carga c = new Carga();
//        String m = c.CargarDatos();
//        System.out.println(m);
//        System.out.println("");
//        ArrayList p = c.getMesajes();
//        for (int i = 0; i < p.size(); i++) {
//            System.out.println(p.get(i));
//        }

//        Informe i1 = new Informe("10001", "555", "MED-123", "Informe", Date.valueOf("2020-09-01"), "7:00");
//        Informe i2 = new Informe("10002", "977693", "MED-123", "Informe", Date.valueOf("2020-09-10"), "7:00");
//        Informe i3 = new Informe("10003", "555", "MED-123", "Informe", Date.valueOf("2020-09-09"), "7:00");
//        Informe i4 = new Informe("10004", "977693", "MED-123", "Informe", Date.valueOf("2020-09-09"), "7:00");
//        Informe i5 = new Informe("10005", "555", "MED-123", "Informe", Date.valueOf("2020-09-15"), "7:00");
//        Informe i6 = new Informe("10006", "977693", "MED-123", "Informe", Date.valueOf("2020-09-26"), "7:00");
//        Informe i7 = new Informe("10007", "555", "MED-123", "Informe", Date.valueOf("2020-09-30"), "7:00");
//        Informe i8 = new Informe("10008", "118258", "MED-123", "Informe", Date.valueOf("2020-09-14"), "7:00");
//        Informe i9 = new Informe("10009", "555", "MED-123", "Informe", Date.valueOf("2020-09-05"), "7:00");
//        Informe i10 = new Informe("10010", "118258", "MED-123", "Informe", Date.valueOf("2020-09-02"), "7:00");
//        Informe i11 = new Informe("10011", "555", "MED-127", "Informe", Date.valueOf("2020-09-02"), "7:00");
//        Informe i12 = new Informe("10012", "118258", "MED-127", "Informe", Date.valueOf("2020-09-05"), "7:00");
//        Informe i13 = new Informe("10013", "555", "MED-127", "Informe", Date.valueOf("2020-09-10"), "7:00");
//        Informe i14 = new Informe("10014", "177840", "MED-127", "Informe", Date.valueOf("2020-09-25"), "7:00");
//        Informe i15 = new Informe("10015", "177840", "MED-127", "Informe", Date.valueOf("2020-09-30"), "7:00");
//        dminf.AgregarInforme(i1);
//        dminf.AgregarInforme(i2);
//        dminf.AgregarInforme(i3);
//        dminf.AgregarInforme(i4);
//        dminf.AgregarInforme(i5);
//        dminf.AgregarInforme(i6);
//        dminf.AgregarInforme(i7);
//        dminf.AgregarInforme(i8);
//        dminf.AgregarInforme(i9);
//        dminf.AgregarInforme(i10);
//        dminf.AgregarInforme(i11);
//        dminf.AgregarInforme(i12);
//        dminf.AgregarInforme(i13);
//        dminf.AgregarInforme(i14);
//        dminf.AgregarInforme(i15);
//        System.out.println(i1);
//        Examen e1 = new Examen("1", "555", "MED-127", "555", Date.valueOf("2020-09-10"));
//        e1.setOrden("orden.pdf");
//        Examen e2 = new Examen("2", "555", "MED-127", "555", Date.valueOf("2020-09-15"));
//        Examen e3 = new Examen("3", "555", "MED-123", "234", Date.valueOf("2020-09-11"));
//        Examen e4 = new Examen("4", "555", "MED-852", "555", Date.valueOf("2020-09-01"));
//        Examen e5 = new Examen("5", "555", "MED-123", "234", Date.valueOf("2020-09-05"));
//        Examen e6 = new Examen("6", "555", "MED-852", "555", Date.valueOf("2020-09-06"));
//        Examen e7 = new Examen("7", "118258", "MED-852", "234", Date.valueOf("2020-09-11"));
//        Examen e8 = new Examen("8", "565436", "MED-123", "555", Date.valueOf("2020-09-21"));
//        Examen e9 = new Examen("9", "977693", "MED-852", "555", Date.valueOf("2020-09-25"));
//        Examen e10 = new Examen("10", "118258", "MED-852", "234", Date.valueOf("2020-09-17"));
//        Examen e11 = new Examen("11", "977693", "MED-127", "555", Date.valueOf("2020-09-19"));
//        String s1 = d.AgendarExamen(e1);
//        String s2 = d.AgendarExamen(e2);
//        String s3 = d.AgendarExamen(e3);
//        String s4 = d.AgendarExamen(e4);
//        String s5 = d.AgendarExamen(e5);
//        String s6 = d.AgendarExamen(e6);
//        String s7 = d.AgendarExamen(e7);
//        String s8 = d.AgendarExamen(e8);
//        String s9 = d.AgendarExamen(e9);
//        String s10 = d.AgendarExamen(e10);
//        String s11 = d.AgendarExamen(e11);
//        System.out.println("");

        Cita c1 = new Cita(9, "555", "MED-123", "Ginecologia", Date.valueOf("2020-09-10"), "7:00");
        Cita c2 = new Cita(10, "555", "MED-123", "Ginecologia", Date.valueOf("2020-09-11"), "7:00");
        Cita c3 = new Cita(11, "555", "MED-123", "Pediatria", Date.valueOf("2020-09-12"), "7:00");
        Cita c4 = new Cita(12, "555", "MED-123", "Ginecologia", Date.valueOf("2020-09-17"), "7:00");
        Cita c5 = new Cita(13, "555", "MED-123", "Ginecologia", Date.valueOf("2020-09-01"), "7:00");
        Cita c6 = new Cita(14, "555", "MED-123", "Ginecologia", Date.valueOf("2020-09-05"), "7:00");
        Cita c7 = new Cita(15, "555", "MED-123", "Ginecologia", Date.valueOf("2020-09-03"), "7:00");
        Cita c8 = new Cita(16, "555", "MED-123", "Ginecologia", Date.valueOf("2020-09-14"), "7:00");
        Cita c9 = new Cita(17, "555", "MED-123", "Ginecologia", Date.valueOf("2020-09-30"), "7:00");
        Cita c10 = new Cita(18, "555", "MED-123", "Ginecologia", Date.valueOf("2020-09-22"), "7:00");
        Cita c11 = new Cita(19, "555", "MED-123", "Ginecologia", Date.valueOf("2020-09-19"), "7:00");
        Cita c12 = new Cita(20, "555", "MED-123", "Ginecologia", Date.valueOf("2020-09-07"), "7:00");
//        dmcit.AgregarCita(c1);
//        dmcit.AgregarCita(c2);
//        dmcit.AgregarCita(c3);
//        dmcit.AgregarCita(c4);
//        dmcit.AgregarCita(c5);
//        dmcit.AgregarCita(c6);
//        dmcit.AgregarCita(c7);
//        dmcit.AgregarCita(c8);
//        dmcit.AgregarCita(c9);
//        dmcit.AgregarCita(c10);
//        dmcit.AgregarCita(c11);
//        dmcit.AgregarCita(c12);
        dmcit.RealizarCita(c1);
        dmcit.RealizarCita(c2);
        dmcit.RealizarCita(c3);
        dmcit.RealizarCita(c4);
        dmcit.RealizarCita(c5);
        dmcit.RealizarCita(c6);
        dmcit.RealizarCita(c7);
        dmcit.RealizarCita(c8);
        dmcit.RealizarCita(c9);
        dmcit.RealizarCita(c10);
        dmcit.RealizarCita(c11);
        dmcit.RealizarCita(c12);
//        Resultado r1 = new Resultado("1101", "555", "MED-123", "234", "LAB-247", "orden.jpg", "Informe", Date.valueOf("2020-10-01"), "7:00");
//        Resultado r2 = new Resultado("1102", "555", "MED-123", "234", "LAB-247", "orden.jpg", "Informe", Date.valueOf("2020-10-01"), "7:00");
//        Resultado r3 = new Resultado("1103", "555", "MED-123", "234", "LAB-247", "orden.jpg", "Informe", Date.valueOf("2020-10-01"), "7:00");
//        Resultado r4 = new Resultado("1104", "555", "MED-123", "234", "LAB-247", "orden.jpg", "Informe", Date.valueOf("2020-10-02"), "7:00");
//        Resultado r5 = new Resultado("1105", "555", "MED-123", "234", "LAB-247", "orden.jpg", "Informe", Date.valueOf("2020-10-03"), "7:00");
//        Resultado r6 = new Resultado("1106", "555", "MED-123", "234", "LAB-247", "orden.jpg", "Informe", Date.valueOf("2020-10-03"), "7:00");
//        Resultado r7 = new Resultado("1107", "555", "MED-123", "234", "LAB-247", "orden.jpg", "Informe", Date.valueOf("2020-10-04"), "7:00");
//        dmres.AgregarResultado(r1);
//        dmres.AgregarResultado(r2);
//        dmres.AgregarResultado(r3);
//        dmres.AgregarResultado(r4);
//        dmres.AgregarResultado(r5);
//        dmres.AgregarResultado(r6);
//        dmres.AgregarResultado(r7);
//
//
//ArrayList<Cita> citas = dmcit.ReporteVerCitasPorMedicoYFechas("555", "ale", "2020-09-01", "2020-11-01");
//        for (int i = 0; i < citas.size(); i++) {
//            Cita cita = citas.get(i);
//            
//            System.out.println(cita.getCodigo_medico());
//            System.out.println(cita.getCodigo_paciente());
//        }
    }
}
