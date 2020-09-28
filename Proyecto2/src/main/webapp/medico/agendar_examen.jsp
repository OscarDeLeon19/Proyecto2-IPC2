<%-- 
    Document   : examen
    Created on : 27/09/2020, 11:20:03
    Author     : oscar19
--%>

<%@page import="acciones_funciones.DM_TipoExamen"%>
<%@page import="funciones.Tipo_Examen"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">        
        <title>Interfaz Medico</title>
    </head>
    <body>  
        <%
            DM_TipoExamen dmtip = new DM_TipoExamen();
        %>
        <div class = "d-flex">
            <div style ="border: 1px solid black" class = "container mt-4 col-lg-4">
                <h3>Agendar Examen Medico</h3>
                <form action ="ServletMedico" method = "POST" class="form-group">
                    <div class="form-group">
                        <label>Codigo del paciente</label>
                        <input type = "text" name = "codigo_paciente"  class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Codigo del medico</label>
                        <input type = "text" name = "codigo_medico" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Codigo del examen</label>
                        <input type = "text" name = "codigo_examen" class="form-control">
                    </div>
                    <div class="form-group">
                        <label> Orden</label>
                        <input type = "file" name = "orden" class="form-control">
                        <small class="form-text text-muted">Tiene determinar si es necesaria la orden y el formato para subirla. (PDF o JPG)</small>
                    </div>
                    <div>
                        <label>Fecha</label>
                        <input type = "text" name = "fecha" class="form-control">
                    </div>
                    <div class="form-group">
                        <input type="submit" name ="accion" value="Agendar Examen" class="btn btn-primary">
                    </div>
                </form>
            </div>
            <% ArrayList<Tipo_Examen> examenes = dmtip.VerExamenes();         %>
            <div style ="border: 1px solid black" class = "container mt-4 col-lg-8">
                <h3>Examenes disponibles en el hospital</h3>
                <table class = "table table-hover">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Orden</th>
                            <th>Costo</th>
                            <th>Formato de la orden</th>
                        </tr>
                    </thead>
                    <tbody>

                        <% if (examenes != null) {
                                for (int i = 0; i < examenes.size(); i++) {
                                    Tipo_Examen tipo = examenes.get(i);

                        %>
                        <tr>
                            <td><%= tipo.getCodigo()%></td> 
                            <td><%= tipo.getNombre()%></td> 
                            <% if (tipo.isOrden() == true) {
                            %>
                            <td> Necesaria</td> 
                            <%                        } else {
                            %>
                            <td> No Necesaria</td> 
                            <%
                                }
                            %>
                            <td><%= tipo.getCosto()%></td> 
                            <td><%= tipo.getTipo_informe()%></td> 
                        </tr>
                        <%                                    }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <%  String mensaje = (String) session.getAttribute("alerta_e");
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
