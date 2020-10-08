<%-- 
    Document   : reporte5_adm
    Created on : 1/10/2020, 22:10:00
    Author     : oscar19
--%>

<%@page import="funciones.Examen"%>
<%@page import="java.util.ArrayList"%>
<%@page import="acciones_funciones.DM_Examen"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Interfaz Administracion</title>
    </head>
    <body>
        <%
            DM_Examen dmexa = new DM_Examen();
            ArrayList<Examen> examenes = dmexa.ReporteVerExamenesRequeridosPorMedicos();
        %>
        <div><!-- Tabla que muestra los examenes mas demandados del medico  -->
            <h3> Medicos con mas examenes demandados </h3>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Cantidad de examenes</th>
                        <th>Codigo del Medico</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (examenes != null) {
                            for (int i = 0; i < examenes.size(); i++) {
                                Examen inf = examenes.get(i);
                    %>
                    <tr>
                        <td><%= inf.getCodigo()%></td>
                        <td><%= inf.getCodigo_medico()%></td>
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
