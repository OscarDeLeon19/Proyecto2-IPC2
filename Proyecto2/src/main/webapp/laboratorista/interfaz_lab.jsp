<%-- 
    Document   : interfaz_lab
    Created on : 28/09/2020, 13:56:38
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
        <% Laboratorista lab = (Laboratorista) request.getAttribute("laboratorista");%>
        <div><!-- Enlaces para las acciones del laboratorista -->
            <h1>Usuario: <%= lab.getNombre()%></h1>
            <div>
                <a href="ServletLaboratorista?accion=RealizarExamen"> Realizar Examen </a>
            </div>
        </div>
            <div><!-- Enlaces para ver los reportes del laboratorista -->
            <h1>Reportes</h1>
            <div>
                <a href="ServletLaboratorista?accion=Reporte1"> Examenes para el dia </a>
            </div>
            <div>
                <a href="ServletLaboratorista?accion=Reporte2"> Examenes realizados en el dia. </a>
            </div>
            <div>
                <a> Utilizacion de cada dia de trabajo </a>
            </div>
            <div>
                <a href="ServletLaboratorista?accion=Reporte3"> 10 fechas con mas trabajo realizado.</a>
            </div>
        </div>
            <div><!-- Enlace para salir a la pagina principal -->
            <form action ="Inicial" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Salir a la pagina principal" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
