<%-- 
    Document   : medicos
    Created on : 29/09/2020, 15:35:57
    Author     : oscar19
--%>

<%@page import="acciones_usuarios.DM_Medico"%>
<%@page import="usuarios.Especialidad"%>
<%@page import="usuarios.Medico"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            DM_Medico dmmed = new DM_Medico();
            ArrayList<Medico> medicos = dmmed.VerTodosLosMedicos();
            ArrayList<Especialidad> especialidades = dmmed.VerEspecialidades();
            Medico medico = null;
            Object med = request.getParameter("medico");
            if (med != null) {
                medico = (Medico) med;
            }
        %>
        <div class = "d-flex"><!-- Fomulario para ingresar los medicos a la base de datos -->
            <div style ="border: 1px solid black" class = "container mt-4 col-lg-4">
                <h3>Medico</h3>
                <form action ="ServletAdmin" method = "POST" class="form-group">
                    <div class="form-group">
                        <label>Codigo del Medico</label>
                        <input type = "text" name = "codigo"  class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type = "text" name = "nombre" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Numero de Colegiado</label>
                        <input type = "text" name = "colegiado"  class="form-control">
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
                        <label>Correo Electronico</label>
                        <input type = "text" name = "correo"  class="form-control">
                    </div> 
                    <div class="form-group">
                        <label>Hora de entrada</label>
                        <input type = "text" name = "hora1"  class="form-control">
                    </div> 
                    <div class="form-group">
                        <label>Hora de salida</label>
                        <input type = "text" name = "hora2"  class="form-control">
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
                        <input type="submit" name ="accion" value="Agregar Medico" class="btn btn-primary">
                    </div>
                </form>
            </div>
            <div class = "container mt-4 col-lg-8"><!-- Tabla para mostrar los medicos de la empresa -->
                <h3>Medicos en la empresa</h3>
                <table class = "table table-hover">
                    <thead>
                        <tr>
                            <th>Codigo de Medico</th>
                            <th>Nombre</th>
                            <th>Numero de Colegiado</th>
                            <th>DPI</th>
                            <th>Telefono</th>
                            <th>Correo Electronico</th>
                            <th>Hora_Entrada</th>
                            <th>Hora_Salida</th>
                            <th>Fecha en que inicio a trabajar</th> 
                            <th>Contraseña</th>  
                        </tr>
                    </thead>
                    <tbody>
                        <% if (medicos != null) {
                                for (int i = 0; i < medicos.size(); i++) {
                                    Medico m = medicos.get(i);

                        %>
                        <tr>
                            <td><%= m.getCodigo()%></td>
                            <td><%= m.getNombre()%></td>
                            <td><%= m.getNumero_colegiado()%></td>
                            <td><%= m.getDpi()%></td>
                            <td><%= m.getTelefono()%></td>
                            <td><%= m.getCorreo()%></td>
                            <td><%= m.getHora_entrada()%></td>
                            <td><%= m.getHora_salida()%></td>
                            <td><%= m.getFecha_inicio()%></td> 
                            <td><%= m.getContraseña()%></td> 
                            <td> 
                                <a href="ServletAdmin?accion=editar_med&code=<%= m.getCodigo()%>"> Editar </a>
                                <a href="ServletAdmin?accion=eliminar_med&code=<%= m.getCodigo()%>"> Eliminar </a>
                            </td>
                        </tr>
                        <%                                    }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>            
        <div class = "d-flex"><!-- Formulario para ingresar una especialidad a un medico -->
            <div style ="border: 1px solid black" class = "container mt-4 col-lg-4">
                <h3>Especialidades</h3>
                <form action ="ServletAdmin" method = "POST" class="form-group">
                    <div class="form-group">
                        <label>Codigo del Medico</label>
                        <input type = "text" name = "codigo_med"  class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Titulo</label>
                        <input type = "text" name = "titulo" class="form-control">
                    </div>                    
                    <div class="form-group">
                        <input type="submit" name ="accion" value="Agregar Especialidad" class="btn btn-primary">
                    </div>
                </form>
            </div>
            <div class = "container mt-4 col-lg-8"><!-- Tabla que muestra las especialidades de los medicos -->
                <h3>Medicos en la empresa</h3>
                <table class = "table table-hover">
                    <thead>
                        <tr>
                            <th>Codigo de Medico</th>
                            <th>Titulo</th>
                        </tr>
                    </thead>
                    <tbody>

                        <% if (especialidades != null) {
                                for (int i = 0; i < especialidades.size(); i++) {
                                    Especialidad e = especialidades.get(i);

                        %>
                        <tr>
                            <td><%= e.getCodigo_medico()%></td>
                            <td><%= e.getTitulo()%></td>
                            <td> 
                                <a href="ServletAdmin?accion=eliminar_especialidad&code=<%= e.getCodigo()%>"> Eliminar </a>
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
            String mensaje = (String) session.getAttribute("error_agregar");
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
