<%-- 
    Document   : modificar_medico
    Created on : 29/09/2020, 16:22:33
    Author     : oscar19
--%>

<%@page import="usuarios.Medico"%>
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
            Medico medico = (Medico) request.getAttribute("medico");
        %>
        <div style ="border: 1px solid black" class = "container mt-4 col-lg-4"><!-- Formulario para modificar un medico -->
            <h3>Modificar Medico</h3>
            <form action ="ServletAdmin" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Codigo del Medico</label>
                    <input type = "hidden" name = "codigo" value="<%= medico.getCodigo()%>" class="form-control">
                </div>
                <div class="form-group">
                    <label>Nombre</label>
                    <input type = "text" name = "nombre" value="<%= medico.getNombre()%>" class="form-control">
                </div>
                <div class="form-group">
                    <label>Numero de Colegiado</label>
                    <input type = "text" name = "colegiado"  value="<%= medico.getNumero_colegiado()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>DPI</label>
                    <input type = "text" name = "dpi"  value="<%= medico.getDpi()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>Telefono</label>
                    <input type = "text" name = "telefono"  value="<%= medico.getTelefono()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>Correo Electronico</label>
                    <input type = "text" name = "correo"  value="<%= medico.getCorreo()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>Hora de entrada</label>
                    <input type = "text" name = "hora1"  value="<%= medico.getHora_entrada()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>Hora de salida</label>
                    <input type = "text" name = "hora2" value="<%= medico.getHora_salida()%>"  class="form-control">
                </div> 
                <div class="form-group">
                    <label>Fecha en que inicio a trabajar</label>
                    <input type = "text" name = "fecha"  value="<%= medico.getFecha_inicio()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <input type="submit" name ="accion" value="Modificar Medico" class="btn btn-primary">
                </div>
            </form>
        </div>
        <%            
        String mensaje = (String) session.getAttribute("error");
        if (mensaje != null) {
        %>     
        <div class="alert alert-primary">
            <%= mensaje%>
        </div>
        <%
            }
        %>
    </body>
</html>
