<%-- 
    Document   : reporte4_adm
    Created on : 1/10/2020, 19:09:16
    Author     : oscar19
--%>

<%@page import="acciones_funciones.DM_Examen"%>
<%@page import="funciones.Examen"%>
<%@page import="java.util.ArrayList"%>
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
            ArrayList<Examen> examenes = dmexa.ReporteVerExamenesDemandados();
        %>
        <div>
            <h3> Examenes mas Demandados </h3>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Cantidad de examenes</th>
                        <th>Tipo de Examen</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (examenes != null) {
                            for (int i = 0; i < examenes.size(); i++) {
                                Examen inf = examenes.get(i);
                    %>
                    <tr>
                        <td><%= inf.getCodigo()%></td>
                        <td><%= inf.getTipo_examen()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div>
            <form action ="ServletAdmin" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
