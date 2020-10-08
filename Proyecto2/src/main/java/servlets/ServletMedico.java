/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import acciones_funciones.DM_Cita;
import acciones_funciones.DM_Examen;
import acciones_funciones.DM_Informe;
import acciones_funciones.DM_TipoExamen;
import acciones_usuarios.DM_Medico;
import acciones_usuarios.DM_Paciente;
import funciones.Cita;
import funciones.Examen;
import funciones.Informe;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import usuarios.Medico;
import usuarios.Paciente;

/**
 *
 * @author oscar19
 */
public class ServletMedico extends HttpServlet {

    DM_Medico dmmed = new DM_Medico();
    DM_Cita dmcit = new DM_Cita();
    DM_Informe dminf = new DM_Informe();
    DM_Examen dmexa = new DM_Examen();
    DM_TipoExamen dmtip = new DM_TipoExamen();
    DM_Paciente dmpac = new DM_Paciente();

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
            out.println("<title>Servlet ServletMedico</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletMedico at " + request.getContextPath() + "</h1>");
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
        String sesion_med = dmmed.ObtenerCodigoSesion();
        Medico medico_sesion = dmmed.VerMedicoPorCodigo(sesion_med);
        String acceder = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("RealizarCita")) {
            request.getSession().setAttribute("mensaje_cita", null);
            acceder = "medico/realizar_cita.jsp";
        } else if (accion.equalsIgnoreCase("AgendarExamen")) {
            request.getSession().setAttribute("alerta_e", null);
            acceder = "medico/agendar_examen.jsp";
        } else if (accion.equalsIgnoreCase("Reporte1")) {
            acceder = "medico/reporte1.jsp";
        } else if (accion.equalsIgnoreCase("Reporte2")) {
            acceder = "medico/reporte2.jsp";
        } else if (accion.equalsIgnoreCase("Reporte3")) {
            acceder = "medico/reporte3.jsp";
        } else if (accion.equalsIgnoreCase("Reporte4")) {
            acceder = "medico/reporte4.jsp";
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
        String sesion_med = dmmed.ObtenerCodigoSesion();
        Medico medico_sesion = dmmed.VerMedicoPorCodigo(sesion_med);
        String acceder = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Ingresar")) {
            String codigo = request.getParameter("txcodigo");
            String contraseña = request.getParameter("pass");
            Medico medico = dmmed.Validar(codigo, contraseña);
            if (medico != null) {
                dmmed.AbrirSesion(codigo);
                request.setAttribute("medico", medico);
                acceder = "medico/interfaz.jsp";
            } else {
                String mensaje = "Codigo o contraseña incorrectas";
                request.getSession().setAttribute("mensaje", mensaje);
                acceder = "medico/login.jsp";
            }
        } else if (accion.equalsIgnoreCase("Regresar a la interfaz")) {
            request.setAttribute("medico", medico_sesion);
            acceder = "medico/interfaz.jsp";
        } else if (accion.equalsIgnoreCase("Buscar Citas")) {
            String codigo_paciente = request.getParameter("codigo_pac");
            ArrayList<Cita> lista = dmcit.VerCitaActual(codigo_paciente, sesion_med);
            request.setAttribute("lista", lista);
            acceder = "medico/realizar_cita.jsp";
        } else if (accion.equalsIgnoreCase("Realizar Cita")) {
            String mensaje = "Fallo al realizar cita";
            try {
                String codigo = request.getParameter("codigo");
                String inf = request.getParameter("informe");
                Cita cita = dmcit.VerCitaPorCodigo(codigo);
                if (cita != null) {
                    Informe informe = dmcit.ObtenerInforme(codigo);
                    if (informe != null) {
                        boolean comprobar = dmcit.RealizarCita(cita);
                        if (comprobar == true) {
                            informe.setDescripcion(inf);
                            mensaje = dminf.AgregarInforme(informe);
                        } else {
                            mensaje = "Fallo al actualizar cita";
                        }
                    } else {
                        mensaje = "Fallo al obtener informe";
                    }
                } else {
                    mensaje = "La cita no existe";
                }
            } catch (Exception e) {
                mensaje = e.toString();
            }
            request.getSession().setAttribute("mensaje_cita", mensaje);
            acceder = "medico/realizar_cita.jsp";
        } else if (accion.equalsIgnoreCase("Agendar Examen")) {
            String alerta = "Fallo para agendar examen";
            try {
                int codigo = 0;
                String codigo_paciente = request.getParameter("codigo_paciente");
                String codigo_medico = request.getParameter("codigo_medico");
                String tipo_examen = request.getParameter("codigo_examen");
                Date fecha = Date.valueOf(request.getParameter("fecha"));
                String Orden = request.getParameter("orden");

                Examen examen = new Examen(codigo, codigo_paciente, codigo_medico, tipo_examen, fecha);
                boolean comprobar_orden = dmtip.ComprobarOrden(tipo_examen);
                if (comprobar_orden == true) {
                    if (Orden != null) {
                        String tipo = dmtip.ObtenerTipo(tipo_examen);
                        if ("PDF".equals(tipo)) {
                            if (Orden.endsWith(".pdf")) {
                                Orden = "archivos/" + Orden;
                                examen.setOrden(Orden);
                                alerta = dmexa.AgendarExamen(examen);
                            } else {
                                alerta = "Orden incorrecta";
                            }
                        } else if ("IMG".equals(tipo)) {
                            if (Orden.endsWith(".jpg") || Orden.endsWith(".png") || Orden.endsWith(".jepg")) {
                                Orden = "archivos/" + Orden;
                                examen.setOrden(Orden);
                                alerta = dmexa.AgendarExamen(examen);
                            } else {
                                alerta = "Orden incorrecta";
                            }
                        } else {
                            alerta = "Fallo en el codigo del Tipo de examen";
                        }
                    } else {
                        alerta = "Es necesaria la orden y no esta agregada";
                    }
                } else {
                    alerta = dmexa.AgendarExamen(examen);
                }
            } catch (Exception e) {
                alerta = e.toString();
            }
            request.getSession().setAttribute("alerta_e", alerta);
            acceder = "medico/agendar_examen.jsp";
        } else if (accion.equalsIgnoreCase("Ver Historial")) {
            Paciente paciente = dmpac.VerPacientePorCodigo(request.getParameter("codigo_paciente"));
            request.setAttribute("paciente", paciente);
            acceder = "medico/reporte1.jsp";
        } else if (accion.equalsIgnoreCase("Obtener Citas")) {
            String fecha1 = request.getParameter("rep2fecha1");
            String fecha2 = request.getParameter("rep2fecha2");
            ArrayList<Cita> lista = dmcit.ReporteCitasEnIntervaloDeTiempo(sesion_med, fecha1, fecha2);
            request.setAttribute("lista", lista);
            acceder = "medico/reporte2.jsp";
        } else if (accion.equalsIgnoreCase("Ver Citas")) {
            String fecha = request.getParameter("rep3fecha");
            ArrayList<Cita> lista = dmcit.ReportesCitasDiaActual(sesion_med, fecha);
            request.setAttribute("lista", lista);
            acceder = "medico/reporte3.jsp";
        } else if (accion.equalsIgnoreCase("Obtener Conteo")) {
            String fecha1 = request.getParameter("rep4fecha1");
            String fecha2 = request.getParameter("rep4fecha2");
            ArrayList<Informe> lista = dminf.ReportePacienteConMasInformes(sesion_med, fecha1, fecha2);
            request.setAttribute("lista", lista);
            acceder = "medico/reporte4.jsp";
        } else if (accion.equalsIgnoreCase("Salir a la pagina principal")){
            acceder = "index.jsp";
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
