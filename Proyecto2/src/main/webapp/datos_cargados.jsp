<%-- 
    Document   : datos_cargados
    Created on : 8/10/2020, 10:28:45
    Author     : oscar19
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos cargados</title>
    </head>
    <body>
        <%  ArrayList<String> mensajes = (ArrayList<String>) request.getAttribute("mensajes");  %>
        <div>
            <%
                for (int i = 0; i < mensajes.size(); i++) {
                    out.println(mensajes.get(i));
            %>
            <br>
            <%
                }
            %>
        </div>
        <div>
            <form action ="Inicial" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la pagina principal" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
