<%-- 
    Document   : realizar_cita
    Created on : 27/09/2020, 19:48:55
    Author     : oscar19
--%>

<%@page import="funciones.Cita"%>
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
        <div class = "container mt-4 col-lg-8">
            <h3> Ver citas del paciente pendientes </h3>
            <form action ="ServletMedico" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Codigo del paciente</label>
                    <input type = "text" name = "codigo_pac" class="form-control">
                </div>
                <div class="form-group">
                    <input type = "submit" name = "accion" value = "Buscar Citas" class="btn btn-primary">
                </div>
            </form>    
        </div>
        <div class = "container mt-4 col-lg-8">
            <% ArrayList<Cita> citas = (ArrayList<Cita>) request.getAttribute("lista");            %>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Codigo del Paciente</th>
                        <th>Codigo del Medico</th>
                        <th>Especialidad</th>
                        <th>Fecha </th>
                        <th>Hora</th>
                    </tr>
                </thead>
                <tbody>

                    <% if (citas != null) {
                            for (int i = 0; i < citas.size(); i++) {
                                Cita cita = citas.get(i);

                    %>
                    <tr>
                        <td><%= cita.getCodigo()%></td>
                        <td><%= cita.getCodigo_paciente()%></td>
                        <td><%= cita.getCodigo_medico()%></td>
                        <td><%= cita.getEspecialidad()%></td>
                        <td><%= cita.getFecha()%></td>
                        <td><%= cita.getHora()%></td>
                    </tr>
                    <%                                    }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div class = "container mt-4 col-lg-8">
            <h3> Realizar el informe </h3>
            <form action ="ServletMedico" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Ingresa el codigo de la cita</label>
                    <input type = "text" name = "codigo" class="form-control">
                </div>
                <div class="form-group">
                    <label>Escribe el informe</label>
                    <textarea  name = "informe"  rows = "10" class="form-control"></textarea>
                </div>
                <div class="form-group">
                    <input type = "submit" name = "accion" value = "Realizar Cita" class="btn btn-primary">
                </div>
            </form>
        </div>
        <%  String mensaje = (String) session.getAttribute("mensaje");
            if (mensaje != null) {
        %>     
        <div class="alert alert-primary">
            <%= mensaje%>
        </div>
        <%
            }
        %>
        <div>
            <form action ="ServletMedico" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
