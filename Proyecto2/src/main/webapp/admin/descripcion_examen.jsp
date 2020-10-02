<%-- 
    Document   : descripcion_examen
    Created on : 1/10/2020, 12:13:00
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
        <%String mensaje = (String) session.getAttribute("descripcion");%>
        <div style ="border: 1px solid black" class = "container mt-4 col-lg-4">
            <%= mensaje%>            
        </div>
        <div class = "container mt-4 col-lg-4">
            <form action ="ServletAdmin" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a los examenes" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
