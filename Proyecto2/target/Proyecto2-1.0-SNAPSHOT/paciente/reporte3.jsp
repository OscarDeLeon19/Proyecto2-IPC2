<%-- 
    Document   : reporte3
    Created on : 27/09/2020, 16:27:15
    Author     : oscar19
--%>

<%@page import="funciones.Cita"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
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
            ArrayList<Cita> citas = (ArrayList<Cita>) request.getAttribute("lista");
        %>
        <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra las citas del paciente --> 
            <h2> Ultimas 5 citas realizadas</h2>
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
        <div><!-- Boton para regresar a la interfaz -->
            <form action ="ServletPaciente" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>