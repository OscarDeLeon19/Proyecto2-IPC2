<%-- 
    Document   : reporte4
    Created on : 28/09/2020, 01:04:24
    Author     : oscar19
--%>

<%@page import="funciones.Informe"%>
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
            Object l = request.getAttribute("lista");
            ArrayList<String> informe = null;
            if (l != null) {
                informe = (ArrayList<String>) l;
            }
        %>
        <div class = "container mt-4 col-lg-8"><!-- Formulario para ingresar las fechas del dia -->
            <form action ="ServletMedico" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Fecha No.1</label>
                    <input type = "text" name = "rep4fecha1" class="form-control">
                </div>
                <div class="form-group">
                    <label>Fecha No.2</label>
                    <input type = "text" name = "rep4fecha2" class="form-control">
                </div>
                <div class="form-group">
                    <input type="submit" name ="accion" value="Obtener Conteo" class="btn btn-primary">
                </div>
            </form>
        </div>
        <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra los pacientes con mas informes --> 
            <h2> Paciente con mas informes </h2>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Informes</th>
                        <th>Codigo del Paciente</th>
                    </tr>
                </thead>
                <tbody>

                    <% if (informe != null) {

                    %>
                    <tr>
                        <td><%= informe.get(0)%></td>
                        <td><%= informe.get(1)%></td>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td>Hola</td>
                        <td>H</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div><!-- Boton para regresar a la interfaz -->
            <form action ="ServletMedico" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
