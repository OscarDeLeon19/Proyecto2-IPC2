/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import acciones_funciones.DM_Cita;
import acciones_funciones.DM_Consulta;
import acciones_funciones.DM_Examen;
import acciones_funciones.DM_Informe;
import acciones_funciones.DM_Resultado;
import acciones_funciones.DM_TipoExamen;
import acciones_usuarios.DM_Administrador;
import acciones_usuarios.DM_Laboratorista;
import acciones_usuarios.DM_Medico;
import acciones_usuarios.DM_Paciente;
import funciones.Cita;
import funciones.Consulta;
import funciones.Informe;
import funciones.Tipo_Examen;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import usuarios.Administrador;
import usuarios.Dia_de_trabajo;
import usuarios.Especialidad;
import usuarios.Laboratorista;
import usuarios.Medico;
import usuarios.Paciente;

/**
 *
 * @author oscar19
 */
public class ServletAdmin extends HttpServlet {

    DM_Cita dmcit = new DM_Cita();
    DM_Informe dminf = new DM_Informe();
    DM_Examen dmexa = new DM_Examen();
    DM_TipoExamen dmtip = new DM_TipoExamen();
    DM_Paciente dmpac = new DM_Paciente();
    DM_Laboratorista dmlab = new DM_Laboratorista();
    DM_Resultado dmres = new DM_Resultado();
    DM_Administrador dmadm = new DM_Administrador();
    DM_Medico dmmed = new DM_Medico();
    DM_Consulta dmcon = new DM_Consulta();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletAdmin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sesion_admin = dmadm.ObtenerCodigoSesion();
        Administrador admin_sesion = dmadm.VerAdministradorPorCodigo(sesion_admin);
        String acceder = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("medicos")) {
            request.getSession().setAttribute("error_agregar", null);
            acceder = "admin/medicos.jsp";
        } else if (accion.equalsIgnoreCase("laboratoristas")) {
            request.getSession().setAttribute("error_agregar_lab", null);
            acceder = "admin/laboratoristas.jsp";
        } else if (accion.equalsIgnoreCase("administradores")) {
            request.getSession().setAttribute("error_agregar_adm", null);
            acceder = "admin/administradores.jsp";
        } else if (accion.equalsIgnoreCase("pacientes")) {
            acceder = "admin/pacientes.jsp";
        } else if (accion.equalsIgnoreCase("consultas")) {
            request.getSession().setAttribute("error_consulta", null);
            acceder = "admin/consultas.jsp";
        } else if (accion.equalsIgnoreCase("examenes")) {
            request.getSession().setAttribute("error_examen", null);
            acceder = "admin/examenes.jsp";
        } else if (accion.equalsIgnoreCase("editar_med")) {
            String codigo = request.getParameter("code");
            Medico medico = dmmed.VerMedicoPorCodigo(codigo);
            request.setAttribute("medico", medico);
            acceder = "admin/modificar_medico.jsp";
        } else if (accion.equalsIgnoreCase("eliminar_med")) {
            String codigo = request.getParameter("code");
            dmmed.EliminarMedico(codigo);
            acceder = "admin/medicos.jsp";
        } else if (accion.equalsIgnoreCase("eliminar_especialidad")) {
            int codigo = Integer.parseInt(request.getParameter("code"));
            Especialidad especialidad = dmmed.VerEspecialidadPorCodigo(codigo);
            dmmed.EliminarEspecialidad(especialidad.getCodigo_medico(), especialidad.getTitulo());
            acceder = "admin/medicos.jsp";
        } else if (accion.equalsIgnoreCase("editar_lab")) {
            String codigo = request.getParameter("code");
            Laboratorista laboratorista = dmlab.VerLaboratoristasPorCodigo(codigo);
            request.setAttribute("laboratorista", laboratorista);
            acceder = "admin/modificar_lab.jsp";
        } else if (accion.equalsIgnoreCase("eliminar_lab")) {
            String codigo = request.getParameter("code");
            dmlab.EliminarLaboratorista(codigo);
            acceder = "admin/laboratoristas.jsp";
        } else if (accion.equalsIgnoreCase("eliminar_dia")) {
            int codigo = Integer.parseInt(request.getParameter("code"));
            Dia_de_trabajo dia = dmlab.VerDiasPorCodigo(codigo);
            dmlab.EliminarDia(dia.getCodigo_laboratorista(), dia.getDia());
            acceder = "admin/laboratoristas.jsp";
        } else if (accion.equalsIgnoreCase("eliminar_adm")) {
            String codigo = request.getParameter("code");
            dmadm.EliminarAdministrador(codigo);
            acceder = "admin/administradores.jsp";
        } else if (accion.equalsIgnoreCase("editar_pac")) {
            String codigo = request.getParameter("code");
            Paciente paciente = dmpac.VerPacientePorCodigo(codigo);
            request.setAttribute("paciente", paciente);
            acceder = "admin/modificar_pac.jsp";
        } else if (accion.equalsIgnoreCase("eliminar_pac")) {
            String codigo = request.getParameter("code");
            dmpac.EliminarPaciente(codigo);
            acceder = "admin/pacientes.jsp";
        } else if (accion.equalsIgnoreCase("eliminar_consulta")) {
            String tipo = request.getParameter("code");
            dmcon.EliminarConsulta(tipo);
            acceder = "admin/consultas.jsp";
        } else if (accion.equalsIgnoreCase("ver_descripcion")) {
            String descripcion = request.getParameter("descripcion");
            request.getSession().setAttribute("descripcion", descripcion);
            acceder = "admin/descripcion_examen.jsp";
        } else if (accion.equalsIgnoreCase("eliminar_examen")) {
            Tipo_Examen tipo_examen = dmtip.VerExamenePorCodigo(request.getParameter("code"));
            dmtip.EliminarExamen(tipo_examen);
            acceder = "admin/examenes.jsp";
        } else if (accion.equalsIgnoreCase("Regresar a los examenes")) {
            request.getSession().setAttribute("error_examen", null);
            acceder = "admin/examenes.jsp";
        } else if (accion.equalsIgnoreCase("reporte1_adm")) {
            acceder = "admin/reporte1_adm.jsp";
        } else if (accion.equalsIgnoreCase("reporte2_adm")) {
            acceder = "admin/reporte2_adm.jsp";
        } else if (accion.equalsIgnoreCase("reporte3_adm")) {
            acceder = "admin/reporte3_adm.jsp";
        } else if (accion.equalsIgnoreCase("reporte4_adm")) {
            acceder = "admin/reporte4_adm.jsp";
        } else if (accion.equalsIgnoreCase("reporte5_adm")) {
            acceder = "admin/reporte5_adm.jsp";
        } else if (accion.equalsIgnoreCase("reporte6_adm")) {
            acceder = "admin/reporte6_adm.jsp";
        }
        RequestDispatcher pagina = request.getRequestDispatcher(acceder);
        pagina.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sesion_admin = dmadm.ObtenerCodigoSesion();
        Administrador admin_sesion = dmadm.VerAdministradorPorCodigo(sesion_admin);
        String acceder = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Ingresar")) {
            String codigo = request.getParameter("txcodigo");
            String contraseña = request.getParameter("pass");
            Administrador admin = dmadm.Validar(codigo, contraseña);
            if (admin != null) {
                dmlab.AbrirSesion(codigo);
                request.setAttribute("administrador", admin);
                acceder = "admin/interfaz_adm.jsp";
            } else {
                String mensaje = "Codigo o contraseña incorrectas";
                request.getSession().setAttribute("mensaje", mensaje);
                acceder = "admin/login_adm.jsp";
            }
        } else if (accion.equalsIgnoreCase("Regresar a la interfaz")) {
            request.setAttribute("administrador", admin_sesion);
            acceder = "admin/interfaz_adm.jsp";
        } else if (accion.equalsIgnoreCase("Modificar Medico")) {
            try {
                String codigo = request.getParameter("codigo");
                String nombre = request.getParameter("nombre");
                int numero_colegiado = Integer.parseInt(request.getParameter("colegiado"));
                String dpi = request.getParameter("dpi");
                int telefono = Integer.parseInt(request.getParameter("telefono"));
                String correo = request.getParameter("correo");
                String hora_entrada = request.getParameter("hora1");
                String hora_salida = request.getParameter("hora2");
                Date fecha_inicio = Date.valueOf(request.getParameter("fecha"));
                String contraseña = "XXXXX";
                if ("".equals(codigo)) {
                    codigo = null;
                }
                if ("".equals(nombre)) {
                    nombre = null;
                }
                if ("".equals(dpi)) {
                    dpi = null;
                }
                if ("".equals(correo)) {
                    correo = null;
                }
                if ("".equals(hora_entrada)) {
                    hora_entrada = null;
                }
                if ("".equals(hora_salida)) {
                    hora_salida = null;
                }
                Medico medico = new Medico(codigo, nombre, numero_colegiado, dpi, telefono, correo, hora_entrada, hora_salida, fecha_inicio, contraseña);
                dmmed.ModificarMedico(medico);
                acceder = "admin/medicos.jsp";
            } catch (Exception e) {
                String error = "Error en los datos al modificar";
                request.getSession().setAttribute("error", error);
                acceder = "admin/modificar_medicos.jsp";
            }
        } else if (accion.equalsIgnoreCase("Agregar Medico")) {
            String error = "";
            try {
                String codigo = request.getParameter("codigo");
                String nombre = request.getParameter("nombre");
                String colegiado = request.getParameter("colegiado");
                String dpi = request.getParameter("dpi");
                String cell = request.getParameter("telefono");
                String correo = request.getParameter("correo");
                String hora_entrada = request.getParameter("hora1");
                String hora_salida = request.getParameter("hora2");
                String fecha = request.getParameter("fecha");
                String contraseña = request.getParameter("cr");
                if ("".equals(codigo)) {
                    codigo = null;
                }
                if ("".equals(nombre)) {
                    nombre = null;
                }
                if ("".equals(dpi)) {
                    dpi = null;
                }
                if ("".equals(correo)) {
                    correo = null;
                }
                if ("".equals(hora_entrada)) {
                    hora_entrada = null;
                }
                if ("".equals(hora_salida)) {
                    hora_salida = null;
                }
                int numero_colegiado = Integer.parseInt(colegiado);
                int telefono = Integer.parseInt(cell);
                Date fecha_inicio = Date.valueOf(fecha);
                Medico medico = new Medico(codigo, nombre, numero_colegiado, dpi, telefono, correo, hora_entrada, hora_salida, fecha_inicio, contraseña);
                dmmed.AgregarMedico(medico);
                error = "Datos Agregados";
                request.getSession().setAttribute("error_agregar", error);
            } catch (Exception e) {
                error = e.toString();

            }
            request.getSession().setAttribute("error_agregar", error);
            acceder = "admin/medicos.jsp";
        } else if (accion.equalsIgnoreCase("Agregar Especialidad")) {
            String error = "";
            String codigo = request.getParameter("codigo_med");
            String titulo = request.getParameter("titulo");
            boolean comprobacion = dmmed.ComprobarEspecialidad(codigo, titulo);
            if (comprobacion == false) {
                Especialidad especialidad = new Especialidad(codigo, titulo);
                error = dmmed.AgregarEspecialidad(especialidad);
            } else {
                error = "El medico ya tiene esta especialidad";
            }
            request.getSession().setAttribute("error_agregar", error);
            acceder = "admin/medicos.jsp";
        } else if (accion.equalsIgnoreCase("Modificar Laboratorista")) {
            try {
                String codigo = request.getParameter("codigo");
                String nombre = request.getParameter("nombre");
                String numero_registro = request.getParameter("registro");
                String dpi = request.getParameter("dpi");
                int telefono = Integer.parseInt(request.getParameter("telefono"));
                String tipo_examen = request.getParameter("tipo");
                String correo = request.getParameter("correo");
                Date fecha_inicio = Date.valueOf(request.getParameter("fecha"));
                String contraseña = "XXXXX";
                if ("".equals(codigo)) {
                    codigo = null;
                }
                if ("".equals(nombre)) {
                    nombre = null;
                }
                if ("".equals(numero_registro)) {
                    numero_registro = null;
                }
                if ("".equals(dpi)) {
                    dpi = null;
                }
                if ("".equals(tipo_examen)) {
                    tipo_examen = null;
                }
                if ("".equals(correo)) {
                    correo = null;
                }
                Laboratorista laboratorista = new Laboratorista(codigo, nombre, numero_registro, dpi, telefono, tipo_examen, correo, fecha_inicio, contraseña);
                dmlab.ModificarLaboratorista(laboratorista);
                acceder = "admin/laboratoristas.jsp";
            } catch (Exception e) {
                String error = "Error en los datos al modificar";
                request.getSession().setAttribute("error", error);
                acceder = "admin/modificar_lab.jsp";
            }
        } else if (accion.equalsIgnoreCase("Agregar Laboratorista")) {
            try {
                String codigo = request.getParameter("codigo");
                String nombre = request.getParameter("nombre");
                String numero_registro = request.getParameter("registro");
                String dpi = request.getParameter("dpi");
                int telefono = Integer.parseInt(request.getParameter("telefono"));
                String tipo_examen = request.getParameter("tipo");
                String correo = request.getParameter("correo");
                Date fecha_inicio = Date.valueOf(request.getParameter("fecha"));
                String contraseña = request.getParameter("cr");
                if ("".equals(codigo)) {
                    codigo = null;
                }
                if ("".equals(nombre)) {
                    nombre = null;
                }
                if ("".equals(numero_registro)) {
                    numero_registro = null;
                }
                if ("".equals(dpi)) {
                    dpi = null;
                }
                if ("".equals(tipo_examen)) {
                    tipo_examen = null;
                }
                if ("".equals(correo)) {
                    correo = null;
                }
                Laboratorista laboratorista = new Laboratorista(codigo, nombre, numero_registro, dpi, telefono, tipo_examen, correo, fecha_inicio, contraseña);
                String error = "";
                error = dmlab.AñadirLaboratorista(laboratorista);
                request.getSession().setAttribute("error_agregar_lab", error);
                acceder = "admin/laboratoristas.jsp";
            } catch (Exception e) {
                String error = e.toString();
                request.getSession().setAttribute("error_agregar_lab", error);
                acceder = "admin/laboratoristas.jsp";
            }

        } else if (accion.equalsIgnoreCase("Agregar Dia")) {
            String error = "";
            String codigo_lab = request.getParameter("codigo_lab");
            String dia = request.getParameter("dia");
            boolean comprobacion = dmlab.ComprobarDia(codigo_lab, dia);
            if (comprobacion == false) {
                Dia_de_trabajo dia_trab = new Dia_de_trabajo(codigo_lab, dia);
                error = dmlab.AgregarDiaDeTrabajo(dia_trab);
            } else {
                error = "El laboratorista ya trabaja ese dia";
            }
            request.getSession().setAttribute("error_agregar_lab", error);
            acceder = "admin/laboratoristas.jsp";
        } else if (accion.equalsIgnoreCase("Agregar Administrador")) {
            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            String dpi = request.getParameter("dpi");
            String contraseña = request.getParameter("cr");
            if ("".equals(codigo)) {
                codigo = null;
            }
            if ("".equals(nombre)) {
                nombre = null;
            }
            if ("".equals(dpi)) {
                dpi = null;
            }
            if ("".equals(contraseña)) {
                contraseña = null;
            }
            Administrador admin = new Administrador(codigo, nombre, dpi, contraseña);
            String error = "";
            error = dmadm.AgregarAdministrador(admin);
            request.getSession().setAttribute("error_agregar_adm", error);
            acceder = "admin/administradores.jsp";
        } else if (accion.equalsIgnoreCase("Modificar Administrador")) {
            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            String dpi = request.getParameter("dpi");
            String contraseña = request.getParameter("cr");
            Administrador admin = new Administrador(codigo, nombre, dpi, contraseña);
            String error = "";
            error = dmadm.ModificarAdministrador(admin);
            request.getSession().setAttribute("error_agregar_adm", error);
            acceder = "admin/administradores.jsp";
        } else if (accion.equalsIgnoreCase("Modificar Paciente")) {
            try {
                String codigo = request.getParameter("codigo");
                String nombre = request.getParameter("nombre");
                String sexo = request.getParameter("sexo");
                Date fecha_de_nacimiento = Date.valueOf(request.getParameter("birth"));
                String dpi = request.getParameter("dpi");
                int telefono = Integer.parseInt(request.getParameter("telefono"));
                double peso = Double.parseDouble(request.getParameter("peso"));
                String tipo_sangre = request.getParameter("sangre");
                String correo = request.getParameter("correo");
                String contraseña = "XXXX";
                if ("".equals(codigo)) {
                    codigo = null;
                }
                if ("".equals(nombre)) {
                    nombre = null;
                }
                if ("".equals(sexo)) {
                    sexo = null;
                }
                if ("".equals(dpi)) {
                    dpi = null;
                }
                if ("".equals(tipo_sangre)) {
                    tipo_sangre = null;
                }
                if ("".equals(correo)) {
                    correo = null;
                }
                Paciente paciente = new Paciente(codigo, nombre, sexo, fecha_de_nacimiento, dpi, telefono, peso, tipo_sangre, correo, contraseña);
                dmpac.ModificarPaciente(paciente);
                acceder = "admin/pacientes.jsp";
            } catch (Exception e) {
                String error = "Error en los datos al modificar";
                request.getSession().setAttribute("error_pacientes", error);
                acceder = "admin/modificar_pac.jsp";
            }
        } else if (accion.equalsIgnoreCase("Agregar Consulta")) {
            String error = "Error en los datos al agregar";
            try {
                String tipo = request.getParameter("tipo");
                double costo = Double.parseDouble(request.getParameter("costo"));
                if ("".equals(tipo)){
                    tipo = null;
                }
                Consulta consulta = new Consulta(tipo, costo);
                error = dmcon.AgregarConsulta(consulta);
            } catch (Exception e) {
                error = e.toString();
            }
            request.getSession().setAttribute("error_consulta", error);
            acceder = "admin/consultas.jsp";
        } else if (accion.equalsIgnoreCase("Modificar Consulta")) {
            String error = "Error en los datos al modificar";
            try {
                String tipo = request.getParameter("tipo");
                double costo = Double.parseDouble(request.getParameter("costo"));
                Consulta consulta = new Consulta(tipo, costo);
                error = dmcon.ModificarConsulta(consulta);
            } catch (Exception e) {
                error = e.toString();
            }
            request.getSession().setAttribute("error_consulta", error);
            acceder = "admin/consultas.jsp";
        } else if (accion.equalsIgnoreCase("Agregar tipo de examen")) {
            String error = "Error en los datos al modificar";
            try {
                String codigo = request.getParameter("codigo");
                String nombre = request.getParameter("nombre");
                boolean orden = Boolean.getBoolean(request.getParameter("orden"));
                String descripcion = request.getParameter("descripcion");
                double costo = Double.parseDouble(request.getParameter("costo"));
                String tipo_informe = request.getParameter("tipo");
                if ("".equals(codigo)){
                    codigo = null;
                }
                if ("".equals(nombre)){
                    nombre = null;
                }
                if ("".equals(descripcion)){
                    descripcion = null;
                }
                Tipo_Examen tipo_examen = new Tipo_Examen(codigo, nombre, orden, descripcion, costo, tipo_informe);
                error = dmtip.AgregarExamen(tipo_examen);
            } catch (Exception e) {
                error = e.toString();
            }
            request.getSession().setAttribute("error_examen", error);
            acceder = "admin/examenes.jsp";
        } else if (accion.equalsIgnoreCase("Modificar Costo")) {
            String error = "Error en los datos al modificar";
            try {
                String codigo = request.getParameter("codigo");
                double costo = Double.parseDouble(request.getParameter("costo"));
                Tipo_Examen tipo_examen = dmtip.VerExamenePorCodigo(codigo);
                tipo_examen.setCosto(costo);
                error = dmtip.ModificarExamen(tipo_examen);
            } catch (Exception e) {
                error = e.toString();
            }
            request.getSession().setAttribute("error_examen", error);
            acceder = "admin/examenes.jsp";
        } else if (accion.equalsIgnoreCase("Obtener Medicos")) {
            String fecha1 = request.getParameter("rep1fecha1");
            String fecha2 = request.getParameter("rep1fecha2");
            ArrayList<Informe> lista = dminf.ReporteADMMedicosConMasInformes(fecha1, fecha2);
            request.setAttribute("lista", lista);
            acceder = "admin/reporte1_adm.jsp";
        } else if (accion.equalsIgnoreCase("Obtener Ganancias")) {
            String fecha1 = request.getParameter("rep2fecha1");
            String fecha2 = request.getParameter("rep2fecha2");
            String[] fechas = new String[2];
            fechas[0] = fecha1;
            fechas[1] = fecha2;
            request.setAttribute("fechas", fechas);
            acceder = "admin/reporte2_adm.jsp";
        } else if (accion.equalsIgnoreCase("Obtener Conteo")) {
            String fecha1 = request.getParameter("rep3fecha1");
            String fecha2 = request.getParameter("rep3fecha2");
            ArrayList<Cita> lista = dmcit.ReporteMedicosCitasMenores(fecha1, fecha2);
            request.setAttribute("lista", lista);
            acceder = "admin/reporte3_adm.jsp";
        } else if (accion.equalsIgnoreCase("Regresar a los examenes")) {
            acceder = "admin/examenes.jsp";
        }
        RequestDispatcher pagina = request.getRequestDispatcher(acceder);
        pagina.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
