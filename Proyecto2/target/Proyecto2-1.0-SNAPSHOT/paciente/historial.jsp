<%-- 
    Document   : historial
    Created on : 26/09/2020, 17:11:45
    Author     : oscar19
--%>

<%@page import="acciones_funciones.DM_Resultado"%>
<%@page import="funciones.Resultado"%>
<%@page import="funciones.Cita"%>
<%@page import="java.util.ArrayList"%>
<%@page import="acciones_funciones.DM_Cita"%>
<%@page import="usuarios.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">                
        <title>Interfaz Cliente</title>
    </head>
    <body>
        <%
            DM_Cita dmcit = new DM_Cita();
            DM_Resultado dmres = new DM_Resultado();
            Paciente paciente = (Paciente) request.getAttribute("paciente");
            ArrayList<Cita> citas = dmcit.VerHistorialCitasPaciente(paciente.getCodigo());
            ArrayList<Resultado> resultados = dmres.VerHistorialPacientes(paciente.getCodigo());

        %>
        <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra todas las citas realizadas en el hospital -->
            <h2> Historial de citas realizadas en el hospital</h2>
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
        <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra todos los examenes realizados en el hospital --> 
            <h2> Historial de examens realizadas en el hospital</h2>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Codigo del Paciente</th>
                        <th>Examen</th>
                        <th>Codigo del Medico</th>
                        <th>Codigo del laboratorista </th>
                        <th>Orden</th>
                        <th>Informe</th>
                        <th>Fecha</th>
                        <th>Hora</th>
                    </tr>
                </thead>
                <tbody>

                    <% if (resultados != null) {
                            for (int i = 0; i < resultados.size(); i++) {
                                Resultado resultado = resultados.get(i);

                    %>
                    <tr>
                        <td><%= resultado.getCodigo()%></td>
                        <td><%= resultado.getCodigo_paciente()%></td>
                        <td><%= resultado.getCodigo_examen()%></td>
                        <td><%= resultado.getCodigo_medico()%></td>
                        <td><%= resultado.getCodigo_laboratorista()%></td> 
                        <% if (resultado.getOrden() != null) {%>
                        <td><a href="<%= resultado.getOrden()%>"><%= resultado.getOrden()%></a></td>
                            <%} else {
                            %><td> NULL </td><%
                                }%>
                        <% if (resultado.getInforme() != null) {%>
                        <td><a href="<%= resultado.getInforme()%>"><%= resultado.getInforme()%></a></td>
                            <%} else {
                            %><td> NULL </td><%
                                    }%>
                        <td><%= resultado.getFecha()%></td> 
                        <td><%= resultado.getHora()%></td> 
                    </tr>
                    <%                                    }
                        }

                    %>
                </tbody>
            </table>
        </div>
        <div>
            <form action ="ServletPaciente" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
