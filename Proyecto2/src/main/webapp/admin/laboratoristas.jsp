<%-- 
    Document   : laboratoristas
    Created on : 30/09/2020, 11:51:04
    Author     : oscar19
--%>

<%@page import="usuarios.Dia_de_trabajo"%>
<%@page import="usuarios.Laboratorista"%>
<%@page import="java.util.ArrayList"%>
<%@page import="acciones_usuarios.DM_Laboratorista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Interfaz Administracion</title>
    </head>
    <body>
        <%
            DM_Laboratorista dmlab = new DM_Laboratorista();
            ArrayList<Laboratorista> labs = dmlab.VerLaboratoristas();
            ArrayList<Dia_de_trabajo> dias = dmlab.VerDias();
        %>
        <div class = "d-flex"><!-- Formulario para agregar un laboratorista -->
            <div style ="border: 1px solid black" class = "container mt-4 col-lg-4">
                <h3>Laboratorista</h3>
                <form action ="ServletAdmin" method = "POST" class="form-group">
                    <div class="form-group">
                        <label>Codigo del Laboratorista</label>
                        <input type = "text" name = "codigo"  class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type = "text" name = "nombre" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Numero de Registro</label>
                        <input type = "text" name = "registro"  class="form-control">
                    </div> 
                    <div class="form-group">
                        <label>DPI</label>
                        <input type = "text" name = "dpi"  class="form-control">
                    </div> 
                    <div class="form-group">
                        <label>Telefono</label>
                        <input type = "text" name = "telefono"  class="form-control">
                    </div> 
                    <div class="form-group">
                        <label>Tipo de Examen</label>
                        <input type = "text" name = "tipo"  class="form-control">
                    </div> 
                    <div class="form-group">
                        <label>Correo Electronico</label>
                        <input type = "text" name = "correo"  class="form-control">
                    </div>  
                    <div class="form-group">
                        <label>Fecha en que inicio a trabajar</label>
                        <input type = "text" name = "fecha"  class="form-control">
                    </div> 
                    <div class="form-group">
                        <label>Contraseña</label>
                        <input type = "text" name = "cr"  class="form-control">
                    </div> 
                    <div class="form-group">
                        <input type="submit" name ="accion" value="Agregar Laboratorista" class="btn btn-primary">
                    </div>
                </form>
            </div>
            <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra a los laboratoristas de la empresa -->
                <h3>Laboratoristas en la empresa</h3>
                <table class = "table table-hover">
                    <thead>
                        <tr>
                            <th>Codigo de Laboratorista</th>
                            <th>Nombre</th>
                            <th>Numero de Registro</th>
                            <th>DPI</th>
                            <th>Telefono</th>
                            <th>Examen que realiza</th>
                            <th>Correo Electronico</th>
                            <th>Fecha en que inicio a trabajar</th> 
                            <th>Contraseña</th>  
                        </tr>
                    </thead>
                    <tbody>
                        <% if (labs != null) {
                                for (int i = 0; i < labs.size(); i++) {
                                    Laboratorista m = labs.get(i);

                        %>
                        <tr>
                            <td><%= m.getCodigo()%></td>
                            <td><%= m.getNombre()%></td>
                            <td><%= m.getNumero_registro()%></td>
                            <td><%= m.getDpi()%></td>
                            <td><%= m.getTelefono()%></td>
                            <td><%= m.getTipo_examen()%></td>
                            <td><%= m.getCorreo()%></td>
                            <td><%= m.getFecha_inicio()%></td> 
                            <td><%= m.getContraseña()%></td> 
                            <td> 
                                <a href="ServletAdmin?accion=editar_lab&code=<%= m.getCodigo()%>"> Editar </a>
                                <a href="ServletAdmin?accion=eliminar_lab&code=<%= m.getCodigo()%>"> Eliminar </a>
                            </td>
                        </tr>
                        <%                                    }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <div class = "d-flex"><!-- Formulario para ingresar un dia de trabajo de laboratorista -->
            <div style ="border: 1px solid black" class = "container mt-4 col-lg-4">
                <h3>Dias de trabajo</h3>
                <form action ="ServletAdmin" method = "POST" class="form-group">
                    <div class="form-group">
                        <label>Codigo del Laboratorista</label>
                        <input type = "text" name = "codigo_lab"  class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Dia de trabajo</label>
                        <select class="form-control" name = "dia" class="form-control">
                            <option>Lunes</option>
                            <option>Martes</option>
                            <option>Miercoles</option> 
                            <option>Jueves</option> 
                            <option>Viernes</option> 
                            <option>Sabado</option> 
                            <option>Domingo</option> 
                        </select>
                    </div> 
                    <div class="form-group">
                        <input type="submit" name ="accion" value="Agregar Dia" class="btn btn-primary">
                    </div>
                </form>
            </div>
            <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra los dias de trabajo de todos los laboratoristas -->
                <h3>Dias de trabajos de los laboratoristas</h3>
                <table class = "table table-hover">
                    <thead>
                        <tr>
                            <th>Codigo de Laboratorista</th>
                            <th>Dia de trabajoo</th>
                        </tr>
                    </thead>
                    <tbody>

                        <% if (dias != null) {
                                for (int i = 0; i < dias.size(); i++) {
                                    Dia_de_trabajo m = dias.get(i);

                        %>
                        <tr>
                            <td><%= m.getCodigo_laboratorista()%></td>
                            <td><%= m.getDia()%></td>
                            <td> 
                                <a href="ServletAdmin?accion=eliminar_dia&code=<%= m.getID()%>"> Eliminar </a>
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
            String mensaje = (String) session.getAttribute("error_agregar_lab");
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
