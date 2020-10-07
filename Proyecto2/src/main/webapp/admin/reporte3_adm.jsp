<%-- 
    Document   : reporte3_adm
    Created on : 1/10/2020, 18:50:56
    Author     : oscar19
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="funciones.Cita"%>
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
            ArrayList<Cita> citas = (ArrayList<Cita>) request.getAttribute("lista");
        %>
        <div class = "container mt-4 col-lg-8"><!-- Formulario para ingresar las fechas del intervalo -->
            <form action ="ServletAdmin" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Fecha No.1</label>
                    <input type = "text" name = "rep3fecha1" class="form-control">
                </div>
                <div class="form-group">
                    <label>Fecha No.2</label>
                    <input type = "text" name = "rep3fecha2" class="form-control">
                </div>
                <div class="form-group">
                    <input type="submit" name ="accion" value="Obtener Conteo" class="btn btn-primary">
                </div>
            </form>
        </div>
        <div class = "container mt-4 col-lg-8"><!-- Tabla para obtener el conteo de citas -->
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Citas</th>
                        <th>Codigo del Medico</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (citas != null) {
                            for (int i = 0; i < citas.size(); i++) {
                                Cita inf = citas.get(i);
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
