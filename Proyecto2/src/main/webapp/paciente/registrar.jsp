<%-- 
    Document   : registrar
    Created on : 24/09/2020, 01:36:37
    Author     : oscar19
--%>

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
            <h3>Datos del Paciente</h3>
            <form action ="ServletPaciente" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Codigo</label>
                    <input type = "text" name = "codigo" class="form-control">
                </div>
                <div class="form-group">
                    <label>Nombre</label>
                    <input type = "text" name = "nombre" class="form-control">
                </div>
                <div class="form-group">
                    <label>Sexo</label>
                    <select class="form-control" name = "sexo" class="form-control">
                        <option>Masculino</option>
                        <option>Femenino</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Fecha De Nacimiento</label>
                    <input type = "text" name = "birth" class="form-control">
                </div>
                <div class="form-group">
                    <label>DPI</label>
                    <input type = "text" name = "dpi" class="form-control">
                </div>
                <div class="form-group">
                    <label>Telefono</label>
                    <input type = "text" name = "telefono" class="form-control">
                </div>
                <div>
                    <label>Peso (kg)</label>
                    <input type = "text" name = "peso" class="form-control">
                </div>
                <div class="form-group">
                    <label>Tipo de Sangre</label>
                    <input type = "text" name = "sangre" class="form-control">
                </div>
                <div class="form-group">
                    <label>Correo Electronico</label>
                    <input type = "text" name = "correo" class="form-control">
                </div>
                <div class="form-group">
                    <label>Contraseña</label>
                    <input type = "text" name = "contra" class="form-control">
                </div>
                <div class="form-group">
                    <input type="submit" name ="accion" value="Registrar" class="btn btn-primary">
                </div>
            </form>
        </div>
        <%
            String mensaje = (String) session.getAttribute("mensaje_r");
            if (mensaje != null) {
        %>
        <div class="alert alert-primary">
            <%= mensaje%>
        </div>
        <%
            }
        %>
        <div>
            <form action ="ServletPaciente" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Salir" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
