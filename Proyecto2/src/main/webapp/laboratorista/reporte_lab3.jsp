<%-- 
    Document   : reporte_lab3
    Created on : 28/09/2020, 18:43:40
    Author     : oscar19
--%>

<%@page import="funciones.Resultado"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Interfaz Laboratorista</title>
    </head>
    <body>
        <div class = "container mt-4 col-lg-8">
            <%
                ArrayList<Resultado> resultados = (ArrayList<Resultado>) request.getAttribute("lista");
            %>
            <h1>10 fechas con mas trabajo realizado</h1>
            <div style ="border: 1px solid black"><!-- Tabla que muestra las fechas con mas trabajo realizado -->
                <table class = "table table-hover">
                    <thead>
                        <tr>
                            <th>Examenes</th>
                            <th>Fecha</th>
                        </tr>
                    </thead>
                    <tbody>

                        <% if (resultados != null) {
                                for (int i = 0; i < resultados.size(); i++) {
                                    Resultado resultado = resultados.get(i);

                        %>
                        <tr>
                            <td><%= resultado.getCodigo()%></td>
                            <td><%= resultado.getFecha()%></td> 
                        </tr>
                        <%                                    }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <div class = "container mt-4 col-lg-8"><!-- Boton para regresar a la interfaz -->
            <form action ="ServletLaboratorista" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>  
    </body>
</html>
