<%-- 
    Document   : login_lab
    Created on : 28/09/2020, 13:14:23
    Author     : oscar19
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Interfaz Laboratorista</title>
    </head>
    <body>
        <div><!-- Formulario para ingresar los datos del laboratorista -->
            <h1>Login Laboratorista</h1>
            <form action ="ServletLaboratorista" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Codigo</label>
                    <input type = "text" name = "txcodigo"  class="form-control">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type = "password" name = "pass"  class="form-control">
                </div>
                <div class="form-group">
                    <input type="submit" name ="accion" value="Ingresar" class="form-control">
                </div>
            </form>
        </div>
        <%
            String msg = (String) session.getAttribute("mensaje");
            if (msg != null) {
        %>
        <div class="alert alert-primary">
            <%= msg%>
        </div>
        <%
            }
        %>
        <div>
            <form action ="Inicial" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la pagina principal" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
