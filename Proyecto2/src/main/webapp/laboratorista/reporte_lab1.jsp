<%-- 
    Document   : reporte_lab1
    Created on : 28/09/2020, 17:53:28
    Author     : oscar19
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="funciones.Examen"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <div class = "container mt-4 col-lg-8">
            <form action ="ServletLaboratorista" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Ingresa la fecha del dia de hoy </label>
                    <input type = "text" name = "fecha" class="form-control">
                </div>
                <div class="form-group">
                    <input type = "submit" name = "accion" value = "Buscar Examenes" class="btn btn-primary">
                </div>
            </form>
        </div>
        <div class = "container mt-4 col-lg-8"> 
            <% ArrayList<Examen> examenes = (ArrayList<Examen>) request.getAttribute("lista"); %>
            <h2> Examenes listos para realizar</h2>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Codigo del Paciente</th>
                        <th>Codigo del Medico</th>
                        <th>Nombre de el examen</th>
                        <th>Fecha </th>
                        <th>Orden</th>
                    </tr>
                </thead>
                <tbody>

                    <% if (examenes != null) {
                            for (int i = 0; i < examenes.size(); i++) {
                                Examen examen = examenes.get(i);

                    %>
                    <tr>
                        <td><%= examen.getCodigo()%></td> 
                        <td><%= examen.getCodigo_paciente()%></td> 
                        <td><%= examen.getCodigo_medico()%></td> 
                        <td><%= examen.getTipo_examen()%></td> 
                        <td><%= examen.getFecha()%></td> 
                        <td> <% if (examen.getOrden() != null) {%>
                            <a href="<%= examen.getOrden()%> "> <%= examen.getOrden()%> </a>
                            <%}%></td> 
                    </tr>
                    <%                                    }
                        }

                    %>
                </tbody>
            </table>
        </div>
        <div>
            <form action ="ServletLaboratorista" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>  
    </body>
</html>
