/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import acciones_funciones.DM_Cita;
import acciones_funciones.DM_Examen;
import acciones_funciones.DM_Informe;
import acciones_funciones.DM_Resultado;
import acciones_funciones.DM_TipoExamen;
import acciones_usuarios.DM_Laboratorista;
import acciones_usuarios.DM_Medico;
import acciones_usuarios.DM_Paciente;
import funciones.Examen;
import funciones.Resultado;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import usuarios.Laboratorista;

/**
 *
 * @author oscar19
 */
public class ServletLaboratorista extends HttpServlet {

    DM_Cita dmcit = new DM_Cita();
    DM_Informe dminf = new DM_Informe();
    DM_Examen dmexa = new DM_Examen();
    DM_TipoExamen dmtip = new DM_TipoExamen();
    DM_Paciente dmpac = new DM_Paciente();
    DM_Laboratorista dmlab = new DM_Laboratorista();
    DM_Resultado dmres = new DM_Resultado();

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
            out.println("<title>Servlet ServletLaboratorista</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletLaboratorista at " + request.getContextPath() + "</h1>");
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
        String sesion_lab = dmlab.ObtenerCodigoSesion();
        Laboratorista lab_sesion = dmlab.VerLaboratoristasPorCodigo(sesion_lab);
        String acceder = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("RealizarExamen")) {
            ArrayList<Examen> lista = dmexa.VerExamenesSegunLaboratorista(lab_sesion.getTipo_examen());
            request.setAttribute("lista", lista);
            acceder = "laboratorista/realizar_examen.jsp";
            request.getSession().setAttribute("msj_exa", null);
        } else if (accion.equalsIgnoreCase("Reporte1")) {
            acceder = "laboratorista/reporte_lab1.jsp";
        } else if (accion.equalsIgnoreCase("Reporte2")) {
            acceder = "laboratorista/reporte_lab2.jsp";
        } else if (accion.equalsIgnoreCase("Reporte3")) {
            ArrayList<Resultado> lista = dmres.ReporteLABFechasConMasTrabajo(sesion_lab);
            request.setAttribute("lista", lista);
            acceder = "laboratorista/reporte_lab3.jsp";
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

        String sesion_lab = dmlab.ObtenerCodigoSesion();
        Laboratorista lab_sesion = dmlab.VerLaboratoristasPorCodigo(sesion_lab);
        String acceder = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Ingresar")) {
            String codigo = request.getParameter("txcodigo");
            String contraseña = request.getParameter("pass");
            Laboratorista lab = dmlab.Validar(codigo, contraseña);
            if (lab != null) {
                dmlab.AbrirSesion(codigo);
                request.setAttribute("laboratorista", lab);
                acceder = "laboratorista/interfaz_lab.jsp";
            } else {
                String mensaje = "Codigo o contraseña incorrectas";
                request.getSession().setAttribute("mensaje", mensaje);
                acceder = "laboratorista/login_lab.jsp";
            }
        } else if (accion.equalsIgnoreCase("Regresar a la interfaz")) {
            request.setAttribute("laboratorista", lab_sesion);
            acceder = "laboratorista/interfaz_lab.jsp";
        } else if (accion.equalsIgnoreCase("Realizar Examen")) {
            String msj_exa = "Fallo al agregar resultado";
            try {

                int codigo_examen = Integer.parseInt(request.getParameter("codigo"));
                String informe = request.getParameter("informe");
                Date fecha = Date.valueOf(request.getParameter("fecha"));
                int hora = Integer.parseInt(request.getParameter("hora"));
                if (informe != null) {
                    if (informe.endsWith(".pdf") || informe.endsWith(".jpg") || informe.endsWith(".png") || informe.endsWith(".jpeg")) {
                        Examen examen = dmexa.ObtenerExamen(codigo_examen);
                        if (examen != null) {
                            Resultado resultado = dmexa.RealizarExamen(examen);
                            String Hora = dmcit.ObtenerHora(hora);
                            resultado.setCodigo_laboratorista(sesion_lab);
                            informe = "archivos/" + informe;
                            resultado.setInforme(informe);
                            resultado.setFecha(fecha);
                            resultado.setHora(Hora);
                            msj_exa = dmres.AgregarResultado(resultado);
                        } else {
                            msj_exa = "Codigo de examen incorrecto";
                        }
                    } else {
                        msj_exa = "Archivo Incorrecto";
                    }
                } else {
                    msj_exa = "No hay informe subirdo";
                }

            } catch (Exception e) {
                msj_exa = "ERROR: " + e.toString();
            }
            request.getSession().setAttribute("msj_exa", msj_exa);
            acceder = "laboratorista/realizar_examen.jsp";
        } else if (accion.equalsIgnoreCase("Buscar Examenes")) {
            String fecha = request.getParameter("fecha");
            ArrayList<Examen> lista = dmexa.ReporteLabExamenesParaElDia(lab_sesion.getTipo_examen(), fecha);
            request.setAttribute("lista", lista);
            acceder = "laboratorista/reporte_lab1.jsp";
        } else if (accion.equalsIgnoreCase("Ver Resultados")) {
            String fecha = request.getParameter("fecha");
            ArrayList<Resultado> lista = dmres.ReporteLABExamenesRealizadosDia(sesion_lab, fecha);
            request.setAttribute("lista", lista);
            acceder = "laboratorista/reporte_lab2.jsp";
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
