<%-- 
    Document   : reporte6_adm
    Created on : 1/10/2020, 22:19:11
    Author     : oscar19
--%>

<%@page import="acciones_funciones.DM_Cita"%>
<%@page import="acciones_funciones.DM_Examen"%>
<%@page import="funciones.Cita"%>
<%@page import="funciones.Examen"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Interfaz Administrador</title>
    </head>
    <body>
        <%
            DM_Cita dmcit = new DM_Cita();
            DM_Examen dmexa = new DM_Examen();
            ArrayList<Cita> citas = dmcit.ReporteVerGananciasPaciente();
            ArrayList<Examen> examenes = dmexa.ReporteVerGananciasPaciente();

        %>
        <h2> Ganancias generadas por pacientes </h2>
        <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra las ganancias generadas por medico en citas --> 
            <h3> Citas </h3>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Ganancias</th>
                        <th>Codigo del Paciente</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (citas != null) {
                            for (int i = 0; i < citas.size(); i++) {
                                Cita inf = citas.get(i);
                    %>
                    <tr>
                        <td>Q<%= inf.getCodigo()%></td>
                        <td><%= inf.getCodigo_paciente()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
            <h3> Examen </h3>
            <table class = "table table-hover"><!-- Tabla que muestra las ganancias generadas por paciente en la base de datos -->
                <thead>
                    <tr>
                        <th>Ganancias</th>
                        <th>Codigo del paciente</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (examenes != null) {
                            for (int i = 0; i < examenes.size(); i++) {
                                Examen inf = examenes.get(i);
                    %>
                    <tr>
                        <td>Q<%= inf.getCodigo()%></td>
                        <td><%= inf.getCodigo_paciente()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div><!-- Boton para regresar a la interfaz -->
            <form action ="ServletAdmin" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
