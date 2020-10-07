<%-- 
    Document   : consultas
    Created on : 30/09/2020, 16:57:03
    Author     : oscar19
--%>

<%@page import="funciones.Consulta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="acciones_funciones.DM_Consulta"%>
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
            DM_Consulta dmcon = new DM_Consulta();
            ArrayList<Consulta> consultas = dmcon.VerConsultas();
        %>
        <div class = "d-flex"><!-- Formulario para agregar o modificar una consulta -->
            <div style ="border: 1px solid black" class = "container mt-4 col-lg-4">
                <h3>Consulta</h3>
                <form action ="ServletAdmin" method = "POST" class="form-group">
                    <div class="form-group">
                        <label>Tipo de Consulta</label>
                        <input type = "text" name = "tipo"  class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Costo</label>
                        <input type = "text" name = "costo" class="form-control">
                    </div>
                    <div class="form-group">
                        <input type="submit" name ="accion" value="Agregar Consulta" class="btn btn-primary">
                        <input type="submit" name ="accion" value="Modificar Consulta" class="btn btn-primary">
                    </div>
                </form>
            </div>
            <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra las consultas de la empresa -->
                <h3>Tipos de Consultas en la empresa</h3>
                <table class = "table table-hover">
                    <thead>
                        <tr>
                            <th>Tipo de Consulta</th>
                            <th>Costo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (consultas != null) {
                                for (int i = 0; i < consultas.size(); i++) {
                                    Consulta m = consultas.get(i);

                        %>
                        <tr>
                            <td><%= m.getTipo()%></td>
                            <td><%= m.getCosto()%></td>
                            <td> 
                                <a href="ServletAdmin?accion=eliminar_consulta&code=<%= m.getTipo()%>"> Eliminar </a>
                            </td>
                        </tr>
                        <%                                    }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <%
            String mensaje = (String) session.getAttribute("error_consulta");
            if (mensaje != null) {
        %>     
        <div class="alert alert-primary">
            <%= mensaje%>
        </div>
        <%
            }
        %>
        <div><!-- Boton para regresar a la interfaz -->
            <form action ="ServletAdmin" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
