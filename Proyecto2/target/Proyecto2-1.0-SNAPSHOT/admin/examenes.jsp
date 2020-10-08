<%-- 
    Document   : examenes
    Created on : 30/09/2020, 17:30:42
    Author     : oscar19
--%>

<%@page import="funciones.Tipo_Examen"%>
<%@page import="java.util.ArrayList"%>
<%@page import="acciones_funciones.DM_TipoExamen"%>
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
            DM_TipoExamen dmtip = new DM_TipoExamen();
            ArrayList<Tipo_Examen> examenes = dmtip.VerExamenes();
        %>
        <div class = "d-flex"><!-- Formulario par ingresar o modificar un examen -->
            <div style ="border: 1px solid black" class = "container mt-4 col-lg-4">
                <h3>Tipo de Examen</h3>
                <form action ="ServletAdmin" method = "POST" class="form-group">
                    <div class="form-group">
                        <label>Codigo del examen</label>
                        <input type = "text" name = "codigo"  class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type = "text" name = "nombre" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Orden</label>
                        <select class="form-control" name = "orden" class="form-control">
                            <option>true</option>
                            <option>false</option>
                        </select>    
                    </div> 
                    <div class="form-group">
                        <label>Descripcion</label>
                        <textarea  name = "descripcion"  rows = "10" class="form-control"></textarea>
                    </div> 
                    <div class="form-group">
                        <label>Costo</label>
                        <input type = "text" name = "costo" class="form-control">
                    </div> 
                    <div class="form-group">
                        <label>Tipo de informe</label>
                        <select class="form-control" name = "tipo" class="form-control">
                            <option>JPG</option>
                            <option>PDF</option>
                        </select>
                    </div> 
                    <div class="form-group">
                        <input type="submit" name ="accion" value="Agregar tipo de examen" class="btn btn-primary">
                        <input type="submit" name ="accion" value="Modificar Costo" class="btn btn-primary">
                        <small class="form-text text-muted">Para modificar un costo introduce el codigo el examen y el costo nuevo. Los otros datos no son necesarios</small>
                    </div>
                </form>
            </div>
            <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra los tipos de examenes del hospital -->
                <h3>Tipos de Examen en la empresa</h3>
                <table class = "table table-hover">
                    <thead>
                        <tr>
                            <th>Codigo del examen</th>
                            <th>Nombre</th>
                            <th>Orden</th> 
                            <th>Descripcion</th> 
                            <th>Costo</th> 
                            <th>Tipo de informe </th> 
                        </tr>
                    </thead>
                    <tbody>
                        <% if (examenes != null) {
                                for (int i = 0; i < examenes.size(); i++) {
                                    Tipo_Examen t = examenes.get(i);

                        %>
                        <tr>
                            <td><%= t.getCodigo()%></td>
                            <td><%= t.getNombre()%></td>
                            <td><%= t.isOrden()%></td> 
                            <td><a href="ServletAdmin?accion=ver_descripcion&descripcion=<%= t.getDescripcion()%>"> Ver Descripcion </a></td>                            
                            <td><%= t.getCosto()%></td>
                            <td><%= t.getTipo_informe()%></td>
                            <td> 
                                <a href="ServletAdmin?accion=eliminar_examen&code=<%= t.getCodigo()%>"> Eliminar </a>
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
            String mensaje = (String) session.getAttribute("error_examen");
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
