<%-- 
    Document   : reporte1
    Created on : 27/09/2020, 15:31:16
    Author     : oscar19
--%>

<%@page import="funciones.Resultado"%>
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
            ArrayList<Resultado> resultados = (ArrayList<Resultado>) request.getAttribute("lista");
        %>
        <div>
            <h1>Ultimos 5 examenes Realizados</h1>
            <div style ="border: 1px solid black">
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
                            <td><%= resultado.getOrden()%></td>
                            <td><%= resultado.getInforme()%></td> 
                            <td><%= resultado.getFecha()%></td> 
                            <td><%= resultado.getHora()%></td> 
                        </tr>
                        <%                                    }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <div>
            <form action ="ServletPaciente" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
