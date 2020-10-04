<%-- 
    Document   : interfaz
    Created on : 27/09/2020, 19:24:32
    Author     : oscar19
--%>

<%@page import="usuarios.Medico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Interfaz Medico</title>
    </head>
    <body>
        <%
            Medico medico = (Medico) request.getAttribute("medico");
        %>
        <div>
            <h1>Usuario: <%= medico.getNombre()%></h1>
            <div>
                <a href = "ServletMedico?accion=RealizarCita"> Realizar una cita </a>
            </div>
            <div>
                <a href = "ServletMedico?accion=AgendarExamen"> Agendar Examen para un paciente </a>
            </div>
        </div>
        <div>
            <h1> Reportes </h1>
            <div>
                <a href = "ServletMedico?accion=Reporte1"> Historial Medico de Pacientes </a>
            </div>
            <div>
                <a href = "ServletMedico?accion=Reporte2"> Citas medicas en un intervalo de tiempo </a>
            </div>
            <div>
                <a href = "ServletMedico?accion=Reporte3"> Citas para el día actual </a>
            </div>
            <div>
                <a href = "ServletMedico?accion=Reporte4"> Pacientes con mas informes médicos en un intervalo de tiempo. </a>
            </div>
        </div>
        <div>
            <form action ="Inicial" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Salir a la pagina principal" class="btn btn-primary">
            </form>
        </div>
    </div>
</body>
</html>
