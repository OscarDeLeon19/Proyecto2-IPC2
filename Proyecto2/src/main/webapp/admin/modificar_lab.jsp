<%-- 
    Document   : modificar_lab
    Created on : 30/09/2020, 13:09:21
    Author     : oscar19
--%>

<%@page import="usuarios.Laboratorista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Interfaz Laboratorista</title>
    </head>
    <body>
        <%Laboratorista lab = (Laboratorista) request.getAttribute("laboratorista");%>
        <div style ="border: 1px solid black" class = "container mt-4 col-lg-4">
            <h3>Modificar Laboratorista</h3>
            <form action ="ServletAdmin" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Codigo del Laboratorista</label>
                    <input type = "hidden" name = "codigo" value="<%= lab.getCodigo()%>" class="form-control">
                </div>
                <div class="form-group">
                    <label>Nombre</label>
                    <input type = "text" name = "nombre" value="<%= lab.getNombre()%>" class="form-control">
                </div>
                <div class="form-group">
                    <label>Numero de Registro</label>
                    <input type = "text" name = "registro" value="<%= lab.getNumero_registro()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>DPI</label>
                    <input type = "text" name = "dpi" value="<%= lab.getDpi()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>Telefono</label>
                    <input type = "text" name = "telefono" value="<%= lab.getTelefono()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>Tipo de Examen</label>
                    <input type = "text" name = "tipo" value="<%= lab.getTipo_examen()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <label>Correo Electronico</label>
                    <input type = "text" name = "correo" value="<%= lab.getCorreo()%>" class="form-control">
                </div>  
                <div class="form-group">
                    <label>Fecha en que inicio a trabajar</label>
                    <input type = "text" name = "fecha" value="<%= lab.getFecha_inicio()%>" class="form-control">
                </div> 
                <div class="form-group">
                    <input type="submit" name ="accion" value="Modificar Laboratorista" class="btn btn-primary">
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
