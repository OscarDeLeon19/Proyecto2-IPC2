/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import acciones_funciones.DM_Cita;
import acciones_funciones.DM_Examen;
import acciones_funciones.DM_Resultado;
import acciones_funciones.DM_TipoExamen;
import acciones_usuarios.DM_Medico;
import acciones_usuarios.DM_Paciente;
import funciones.Cita;
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
import usuarios.Medico;
import usuarios.Paciente;

/**
 *
 * @author oscar19
 */
public class ServletPaciente extends HttpServlet {

    DM_Paciente dmpac = new DM_Paciente();
    DM_Medico dmmed = new DM_Medico();
    DM_Cita dmcit = new DM_Cita();
    DM_TipoExamen dmtip = new DM_TipoExamen();
    DM_Examen dmexa = new DM_Examen();
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
            out.println("<title>Servlet ServletPaciente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletPaciente at " + request.getContextPath() + "</h1>");
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
        String sesion_pac = dmpac.ObtenerCodigoSesion();
        Paciente paciente_sesion = dmpac.VerPacientePorCodigo(sesion_pac);
        String acceder = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("registrar")) {
            acceder = "paciente/registrar.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("AddConsulta")) {
            Paciente paciente = dmpac.VerPacientePorCodigo(request.getParameter("id"));
            request.setAttribute("paciente", paciente);
            acceder = "paciente/agendar_consulta.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("VerHistorial")) {
            request.setAttribute("paciente", paciente_sesion);
            acceder = "paciente/historial.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("VerCitasEnCurso")) {
            request.setAttribute("paciente", paciente_sesion);
            acceder = "paciente/citas.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("AgendarExamen")) {
            request.setAttribute("paciente", paciente_sesion);
            acceder = "paciente/examen.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Reporte1")) {
            ArrayList<Resultado> resultados = dmres.ReporteVerUltimos5Resultados(sesion_pac);
            request.setAttribute("lista", resultados);
            acceder = "paciente/reporte1.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Reporte2")) {
            acceder = "paciente/reporte2.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Reporte3")) {
            ArrayList<Cita> citas = dmcit.ReporteVerUltimas5Citas(sesion_pac);
            request.setAttribute("lista", citas);
            acceder = "paciente/reporte3.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Reporte4")) {
            acceder = "paciente/reporte4.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        }
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
        String sesion_pac = dmpac.ObtenerCodigoSesion();
        Paciente paciente_sesion = dmpac.VerPacientePorCodigo(sesion_pac);
        String acceder = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Ingresar")) {
            String mensaje = null;
            String codigo = request.getParameter("txcodigo");
            String contraseña = request.getParameter("pass");
            Paciente paciente = dmpac.Validar(codigo, contraseña);
            if (paciente != null) {
                dmpac.AbrirSesion(codigo);
                request.setAttribute("paciente", paciente);
                acceder = "paciente/interfaz.jsp";
                RequestDispatcher pagina = request.getRequestDispatcher(acceder);
                pagina.forward(request, response);
            } else {
                mensaje = "Codigo o contraseña incorrectas";
                request.getSession().setAttribute("mensaje", mensaje);
                acceder = "paciente/login.jsp";
                RequestDispatcher pagina = request.getRequestDispatcher(acceder);
                pagina.forward(request, response);
            }
        } else if (accion.equalsIgnoreCase("Registrar")) {
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
                String contraseña = request.getParameter("contra");
                Paciente paciente = new Paciente(codigo, nombre, sexo, fecha_de_nacimiento, dpi, telefono, peso, tipo_sangre, correo, contraseña);
                String agregar_paciente = dmpac.AgregarPaciente(paciente);
                request.getSession().setAttribute("mensaje", agregar_paciente);
                acceder = "paciente/registrar.jsp";
                RequestDispatcher pagina = request.getRequestDispatcher(acceder);
                pagina.forward(request, response);
            } catch (Exception e) {
                request.getSession().setAttribute("mensaje", e.toString());
                acceder = "paciente/registrar.jsp";
                RequestDispatcher pagina = request.getRequestDispatcher(acceder);
                pagina.forward(request, response);
            }
        } else if (accion.equalsIgnoreCase("Salir")) {
            acceder = "paciente/login.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Busqueda Por Nombre")) {
            ArrayList<Medico> lista = dmmed.VerMedicoPorNombre(request.getParameter("cajatexto"));
            request.setAttribute("medicos", lista);
            acceder = "paciente/agendar_consulta.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Busqueda Por Especialidad")) {
            ArrayList<Medico> lista = dmmed.VerMedicoPorTitulo(request.getParameter("cajatexto"));
            request.setAttribute("medicos", lista);
            acceder = "paciente/agendar_consulta.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Busqueda Por Fecha")) {
            ArrayList<Medico> lista = dmmed.VerMedicoPorFecha(request.getParameter("cajatexto"));
            request.setAttribute("medicos", lista);
            acceder = "paciente/agendar_consulta.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Agendar Consulta")) {
            String alerta = "Fallo para agendar consulta";
            try {
                String codigo = request.getParameter("codigo");
                String codigo_paciente = request.getParameter("codigo_paciente");
                String codigo_medico = request.getParameter("codigo_medico");
                String especialidad = request.getParameter("tipo_consulta");
                Date fecha = Date.valueOf(request.getParameter("fecha"));
                int hora = Integer.parseInt(request.getParameter("hora"));

                boolean comprobar_especialidad = dmmed.ComprobarEspecialidad(codigo_medico, especialidad);
                if (comprobar_especialidad == true) {
                    boolean comprobar_horario = dmmed.ComprobarHorario(codigo_medico, hora);
                    if (comprobar_horario == true) {
                        boolean comprobar_cita = dmcit.ComprobarCita(codigo_medico, fecha, hora);
                        if (comprobar_cita == false) {
                            String hora_cita = dmcit.ObtenerHora(hora);
                            Cita cita = new Cita(codigo, codigo_paciente, codigo_medico, especialidad, fecha, hora_cita);
                            alerta = dmcit.AgregarCita(cita);
                        } else {
                            alerta = "El medico ya tiene una cita para esa hora";
                        }
                    } else {
                        alerta = "El medico no trabaja en ese horario";
                    }
                } else {
                    alerta = "El medico no tiene esa especialidad";
                }

            } catch (Exception e) {
                alerta = e.toString();
            }
            request.getSession().setAttribute("alerta", alerta);
            acceder = "paciente/agendar_consulta.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Regresar a la interfaz")) {
            request.setAttribute("paciente", paciente_sesion);
            acceder = "paciente/interfaz.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Agendar Examen")) {
            String alerta = "Fallo para agendar examen";
            try {
                String codigo = request.getParameter("codigo");
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
            request.getSession().setAttribute("alerta", alerta);
            acceder = "paciente/examen.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Obtener Examenes")) {
            String tipo_examen = request.getParameter("rep2tipo");
            String fecha1 = request.getParameter("rep2fecha1");
            String fecha2 = request.getParameter("rep2fecha2");
            ArrayList<Resultado> resultados = dmres.ReporteVerExamenesDeUnTipo(sesion_pac, tipo_examen, fecha1, fecha2);
            request.setAttribute("lista", resultados);
            acceder = "paciente/reporte2.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Obtener citas")) {
            String medico = request.getParameter("rep4nombre");
            String fecha1 = request.getParameter("rep4fecha1");
            String fecha2 = request.getParameter("rep4fecha2");
            ArrayList<Cita> citas = dmcit.ReporteVerCitasPorMedicoYFechas(sesion_pac, medico, fecha1, fecha2);
            request.setAttribute("lista", citas);
            acceder = "paciente/reporte4.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        } else if (accion.equalsIgnoreCase("Salir a la pagina principal")) {
            acceder = "index.jsp";
            RequestDispatcher pagina = request.getRequestDispatcher(acceder);
            pagina.forward(request, response);
        }
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
