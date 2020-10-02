<%-- 
    Document   : reporte_adm2
    Created on : 1/10/2020, 18:13:19
    Author     : oscar19
--%>

<%@page import="funciones.Examen"%>
<%@page import="funciones.Cita"%>
<%@page import="java.util.ArrayList"%>
<%@page import="acciones_funciones.DM_Examen"%>
<%@page import="acciones_funciones.DM_Cita"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Interfaz Paciente</title>
    </head>
    <body>
        <div class = "container mt-4 col-lg-8">
            <form action ="ServletAdmin" method = "POST" class="form-group">
                <div class="form-group">
                    <label>Fecha No.1</label>
                    <input type = "text" name = "rep2fecha1" class="form-control">
                </div>
                <div class="form-group">
                    <label>Fecha No.2</label>
                    <input type = "text" name = "rep2fecha2" class="form-control">
                </div>
                <div class="form-group">
                    <input type="submit" name ="accion" value="Obtener Ganancias" class="btn btn-primary">
                </div>
            </form>
        </div>
        <%
            Object f = request.getAttribute("fechas");
            String[] fechas = null;
            ArrayList<Cita> citas = null;
            ArrayList<Examen> examenes = null;
            if (f != null) {
                fechas = (String[]) f;
                DM_Cita dmcit = new DM_Cita();
                DM_Examen dmexa = new DM_Examen();
                citas = dmcit.ReporteVerGananciasMedico(fechas[0], fechas[1]);
                examenes = dmexa.ReporteVerGananciasMedico(fechas[0], fechas[1]);
            }

        %>
        <h2> Ganancias generadas por medicos </h2>
        <div class = "container mt-4 col-lg-8"> 
            <h3> Citas </h3>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Ganancias</th>
                        <th>Codigo del Medico</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (citas != null) {
                            for (int i = 0; i < citas.size(); i++) {
                                Cita inf = citas.get(i);
                    %>
                    <tr>
                        <td>Q<%= inf.getCodigo()%></td>
                        <td><%= inf.getCodigo_medico()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
            <h3> Examen </h3>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Ganancias</th>
                        <th>Codigo del Medico</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (examenes != null) {
                            for (int i = 0; i < examenes.size(); i++) {
                                Examen inf = examenes.get(i);
                    %>
                    <tr>
                        <td>Q<%= inf.getCodigo()%></td>
                        <td><%= inf.getCodigo_medico()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div>
            <form action ="ServletAdmin" method = "POST" class="form-group">
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
