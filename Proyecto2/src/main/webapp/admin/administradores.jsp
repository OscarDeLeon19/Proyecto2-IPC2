<%-- 
    Document   : administradores
    Created on : 30/09/2020, 15:01:18
    Author     : oscar19
--%>

<%@page import="usuarios.Administrador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="acciones_usuarios.DM_Administrador"%>
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
            DM_Administrador dmadm = new DM_Administrador();
            ArrayList<Administrador> labs = dmadm.VerAdministradores();
        %>
        <div class = "d-flex"><!-- Formulario para agregar un administrador -->
            <div style ="border: 1px solid black" class = "container mt-4 col-lg-4">
                <h3>Administrador</h3>
                <form action ="ServletAdmin" method = "POST" class="form-group">
                    <div class="form-group">
                        <label>Codigo del Administrador</label>
                        <input type = "text" name = "codigo"  class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type = "text" name = "nombre" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>DPI</label>
                        <input type = "text" name = "dpi"  class="form-control">
                    </div> 
                    <div class="form-group">
                        <label>Contraseña</label>
                        <input type = "text" name = "cr"  class="form-control">
                    </div> 
                    <div class="form-group">
                        <input type="submit" name ="accion" value="Agregar Administrador" class="btn btn-primary">
                        <input type="submit" name ="accion" value="Modificar Administrador" class="btn btn-primary">
                        <small class="form-text text-muted">Para modificar introduce el codigo del administrador y los datos que quieras modificar en el Nombre y DPI</small>
                    </div>
                </form>
            </div>
            <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra los administradores de la empresa -->
                <h3>Administradores en la empresa</h3>
                <table class = "table table-hover">
                    <thead>
                        <tr>
                            <th>Codigo de Administrador</th>
                            <th>Nombre</th>
                            <th>DPI</th>
                            <th>Contraseña</th>  
                        </tr>
                    </thead>
                    <tbody>

                        <% if (labs != null) {
                                for (int i = 0; i < labs.size(); i++) {
                                    Administrador m = labs.get(i);

                        %>
                        <tr>
                            <td><%= m.getCodigo()%></td>
                            <td><%= m.getNombre()%></td>
                            <td><%= m.getDpi()%></td>
                            <td><%= m.getContraseña()%></td> 
                            <td> 
                                <a href="ServletAdmin?accion=eliminar_adm&code=<%= m.getCodigo()%>"> Eliminar </a>
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
            String mensaje = (String) session.getAttribute("error_agregar_adm");
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
