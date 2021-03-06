<%-- 
    Document   : interfaz
    Created on : 24/09/2020, 00:11:22
    Author     : oscar19
--%>

<%@page import="usuarios.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interfaz Paciente</title>
    </head>
    <body>
        <div>
            <% Paciente paciente = (Paciente) request.getAttribute("paciente");%>
            <div><!-- Enlaces hacia funciones del paciente -->
                <h1> Usuario <%= paciente.getNombre()%></h1>
                <div>
                    <a href="ServletPaciente?accion=AddConsulta&id=<%= paciente.getCodigo()%>"> Agendar Consulta </a>            
                </div>
                <div>
                    <a href="ServletPaciente?accion=VerHistorial&id=<%= paciente.getCodigo()%>"> Ver Historial Medico</a>
                </div>
                <div>
                    <a href="ServletPaciente?accion=VerCitasEnCurso&id=<%= paciente.getCodigo()%>"> Citas medicas y Examenes por realizar</a>
                </div>
                <div>
                    <a href="ServletPaciente?accion=AgendarExamen&id=<%= paciente.getCodigo()%>"> Agendar Examen Medico</a>
                </div>
            </div>
            <div><!-- Enlaces de los reportes que puede obtener en paciente -->
                <h3>Reportes</h3> 
                <div>
                    <a href="ServletPaciente?accion=Reporte1&id=<%= paciente.getCodigo()%>"> Ultimos 5 examenes realizados </a>            
                </div>
                <div>
                    <a href="ServletPaciente?accion=Reporte2"> Exámenes realizados de un tipo especifico en intervalo de tiempo </a>                
                </div>
                <div>
                    <a href="ServletPaciente?accion=Reporte3&id=<%= paciente.getCodigo()%>"> Ultimas 5 consultas realizadas </a>            
                </div>
                <div>
                    <a href="ServletPaciente?accion=Reporte4&id=<%= paciente.getCodigo()%>"> Citas realizadas con un medico en especifico, en un intervalo de tiempo </a>            
                </div>
            </div>
            <div><!-- Boton para regresar al index -->
                <form action ="ServletPaciente" method = "POST" class="form-group">
                    <input type="submit" name ="accion" value="Salir a la pagina principal" class="btn btn-primary">
                </form>
            </div>
        </div>
    </body>
</html>
