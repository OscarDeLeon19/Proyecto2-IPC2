<%-- 
    Document   : reporte1_adm
    Created on : 1/10/2020, 16:11:40
    Author     : oscar19
--%>

<%@page import="funciones.Informe"%>
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
            ArrayList<Informe> informe = (ArrayList<Informe>) request.getAttribute("lista");
        %>
        <div class = "container mt-4 col-lg-8"><!-- Formulario para ingresar las fechas del intervalo -->
            <form action ="ServletAdmin" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Fecha No.1</label>
                    <input type = "text" name = "rep1fecha1" class="form-control">
                </div>
                <div class="form-group">
                    <label>Fecha No.2</label>
                    <input type = "text" name = "rep1fecha2" class="form-control">
                </div>
                <div class="form-group">
                    <input type="submit" name ="accion" value="Obtener Medicos" class="btn btn-primary">
                </div>
            </form>
        </div>
        <div class = "container mt-4 col-lg-8"><!-- Tabla para mostrar el conteo de informe --> 
            <h2> Medicos con mas informes </h2>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Informes</th>
                        <th>Codigo del Medico</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (informe != null) {
                            for (int i = 0; i < informe.size(); i++) {
                                Informe inf = informe.get(i);
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
