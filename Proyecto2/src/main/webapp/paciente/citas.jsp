<%-- 
    Document   : citas
    Created on : 26/09/2020, 21:50:05
    Author     : oscar19
--%>

<%@page import="funciones.Examen"%>
<%@page import="usuarios.Paciente"%>
<%@page import="acciones_funciones.DM_Examen"%>
<%@page import="funciones.Cita"%>
<%@page import="java.util.ArrayList"%>
<%@page import="acciones_funciones.DM_Cita"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Interfaz Paciente</title>
    </head>
    <body>
        <%
            DM_Cita dmcit = new DM_Cita();
            DM_Examen dmexa = new DM_Examen();
            Paciente paciente = (Paciente) request.getAttribute("paciente");
            ArrayList<Cita> citas = dmcit.VerCitasEnCurso(paciente.getCodigo());
            ArrayList<Examen> examenes = dmexa.VerExamenesSinRealizarPaciente(paciente.getCodigo());
        %>
        <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra las citas pendientes --> 
            <h2> Citas por realizar</h2>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Codigo del Paciente</th>
                        <th>Codigo del Medico</th>
                        <th>Especialidad</th>
                        <th>Fecha </th>
                        <th>Hora</th>
                    </tr>
                </thead>
                <tbody>

                    <% if (citas != null) {
                            for (int i = 0; i < citas.size(); i++) {
                                Cita cita = citas.get(i);

                    %>
                    <tr>
                        <td><%= cita.getCodigo()%></td>
                        <td><%= cita.getCodigo_paciente()%></td>
                        <td><%= cita.getCodigo_medico()%></td>
                        <td><%= cita.getEspecialidad()%></td>
                        <td><%= cita.getFecha()%></td>
                        <td><%= cita.getHora()%></td>
                    </tr>
                    <%                                    }
                        }

                    %>
                </tbody>
            </table>
        </div>
        <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra los examenes pendientes --> 
            <h2> Examenes que deben realizar</h2>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Codigo del Paciente</th>
                        <th>Codigo del Medico</th>
                        <th>Nombre de el examen</th>
                        <th>Fecha </th>
                        <th>Orden</th>
                    </tr>
                </thead>
                <tbody>

                    <% if (examenes != null) {
                            for (int i = 0; i < examenes.size(); i++) {
                                Examen examen = examenes.get(i);

                    %>
                    <tr>
                        <td><%= examen.getCodigo()%></td> 
                        <td><%= examen.getCodigo_paciente()%></td> 
                        <td><%= examen.getCodigo_medico()%></td> 
                        <td><%= examen.getTipo_examen()%></td> 
                        <td><%= examen.getFecha()%></td> 
                        <td> <% if (examen.getOrden() != null) {%>
                            <a href="<%= examen.getOrden()%> "> <%= examen.getOrden()%> </a>
                            <%}%></td> 
                    </tr>
                    <%                                    }
                        }

                    %>
                </tbody>
            </table>
        </div>
        <div><!-- Boton para regresar a la interfaz -->
            <form action ="ServletPaciente" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
