<%-- 
    Document   : interfaz_admin
    Created on : 29/09/2020, 11:36:07
    Author     : oscar19
--%>

<%@page import="usuarios.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Interfaz Administrador</title>
    </head>
    <body>
        <% Administrador admin = (Administrador) request.getAttribute("administrador");%>
        <div>
            <h1>Usuario: <%= admin.getNombre()%></h1>
            <div>
                <a href="ServletAdmin?accion=medicos"> Medicos del Hospital </a>
            </div>
            <div>
                <a href="ServletAdmin?accion=laboratoristas"> Laboratoristas del Hospital </a>
            </div>
            <div>
                <a href="ServletAdmin?accion=administradores"> Administradores del Hospital </a>
            </div>
            <div>
                <a href="ServletAdmin?accion=pacientes"> Pacientes del Hospital </a>
            </div>
            <div>
                <a href="ServletAdmin?accion=consultas"> Tipos de Consulta del Hospital </a>
            </div>
            <div>
                <a href="ServletAdmin?accion=examenes"> Tipos de Examen del Hospital </a>
            </div>
        </div>
        <div>
            <h1> Reportes </h1>
            <div>
                <a href="ServletAdmin?accion=reporte1_adm"> Los 10 medicos que han realizado mas informes en un intervalo de tiempo </a>
            </div>
            <div>
                <a href="ServletAdmin?accion=reporte2_adm"> Ingresos obtenidos por medico en intervalo de tiempo </a>
            </div>
            <div>
                <a href="ServletAdmin?accion=reporte3_adm"> 5 Medicos con la menor cantidad de consultas en un intervalo de tiempo </a>
            </div>
            <div>
                <a href="ServletAdmin?accion=reporte4_adm"> Examenes de laboratorio mas demandados </a>
            </div>
            <div>
                <a href="ServletAdmin?accion=reporte5_adm"> Los medicos con mayor cantidad de examenes requeridos </a>
            </div>
            <div>
                <a href="ServletAdmin?accion=reporte6_adm"> Ingresos generados por paciente </a>
            </div>
        </div>   
        <div>
            <form action ="Inicial" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Salir a la pagina principal" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
