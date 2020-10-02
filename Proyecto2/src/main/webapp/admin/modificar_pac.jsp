<%-- 
    Document   : modificar_pac
    Created on : 30/09/2020, 16:22:15
    Author     : oscar19
--%>

<%@page import="usuarios.Paciente"%>
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
            Paciente paciente = (Paciente) request.getAttribute("paciente");
        %>
        <div style ="border: 1px solid black" class = "container mt-4 col-lg-4">
            <h3>Administrador</h3>
            <form action ="ServletAdmin" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Codigo del Paciente</label>
                    <input type = "hidden" name = "codigo" value="<%= paciente.getCodigo()%>" class="form-control">
                </div>
                <div class="form-group">
                    <label>Nombre</label>
                    <input type = "text" name = "nombre" value="<%= paciente.getNombre()%>" class="form-control">
                </div>
                <div class="form-group">
                    <label>Sexo</label>
                    <input type = "text" name = "sexo" value="<%= paciente.getSexo()%>" class="form-control">
                </div>
                <div class="form-group">
                    <label>Fecha de Nacimiento </label>
                    <input type = "text" name = "birth" value="<%= paciente.getFecha_de_nacimiento()%>" class="form-control">
                </div>
                <div class="form-group">
                    <label>DPI</label>
                    <input type = "text" name = "dpi" value="<%= paciente.getDpi()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>Telefono</label>
                    <input type = "text" name = "telefono" value="<%= paciente.getTelefono()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>Peso</label>
                    <input type = "text" name = "peso" value="<%= paciente.getPeso()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>Tipo de Sangre</label>
                    <input type = "text" name = "sangre" value="<%= paciente.getTipo_sangre()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>Correo Electronico</label>
                    <input type = "text" name = "correo" value="<%= paciente.getCorreo()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <input type="submit" name ="accion" value="Modificar Paciente" class="btn btn-primary">
                </div>
            </form>
        </div>
        <%
            String mensaje = (String) session.getAttribute("error_pacientes");
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
