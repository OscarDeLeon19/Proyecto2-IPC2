<%-- 
    Document   : login_adm
    Created on : 29/09/2020, 11:16:23
    Author     : oscar19
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Interfaz Administrador</title>
    </head>
    <body>
        <div>
            <h1>Login Laboratorista</h1>
            <form action ="ServletAdmin" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Codigo</label>
                    <input type = "text" name = "txcodigo" value="ADMIN1" class="form-control">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type = "password" name = "pass" value="dfDrVH~9a#-7)^w^P9pB" class="form-control">
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
    </body>
</html>