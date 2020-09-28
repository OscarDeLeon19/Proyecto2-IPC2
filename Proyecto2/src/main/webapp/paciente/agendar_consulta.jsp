<%-- 
    Document   : agendar_consulta
    Created on : 25/09/2020, 21:04:54
    Author     : oscar19
--%>

<%@page import="usuarios.Medico"%>
<%@page import="acciones_funciones.DM_Consulta"%>
<%@page import="funciones.Consulta"%>
<%@page import="java.util.ArrayList"%>
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
        <%
            DM_Consulta dmcon = new DM_Consulta();
            ArrayList<Consulta> lista = dmcon.VerConsultas();
        %>
        <div class = "d-flex">
            <div style ="border: 1px solid black" class = "container mt-4 col-lg-2">
                <h3>Agendar Cita</h3>
                <form action ="ServletPaciente" method = "POST" class="form-group">
                    <div class="form-group">
                        <label>Codigo del paciente</label>
                        <input type = "text" name = "codigo_paciente"  class="form-control">
                        <small class="form-text text-muted">El codigo con el que ingreso</small>
                    </div>
                    <div class="form-group">
                        <label>Codigo del medico</label>
                        <input type = "text" name = "codigo_medico" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Tipo de Consulta</label>
                        <select class="form-control" name = "tipo_consulta" class="form-control">
                            <% for (int i = 0; i < lista.size(); i++) {
                                    Consulta consulta = lista.get(i);
                            %>
                            <option><%= consulta.getTipo()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div>
                        <label>Fecha</label>
                        <input type = "text" name = "fecha" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Hora </label>
                        <input type = "text" name = "hora" class="form-control">
                        <small  class="form-text text-muted">Solo el numero, en formato 24 horas</small>
                    </div>
                    <div class="form-group">
                        <input type="submit" name ="accion" value="Agendar Consulta" class="btn btn-primary">
                    </div>
                </form>
            </div>
            <div style ="border: 1px solid black" class = "container mt-4 col-lg-12">
                <div>
                    <form action ="ServletPaciente" method = "POST" class="form-group">
                        <div class="form-group">
                            <input type="submit" name ="accion" value="Busqueda Por Nombre" class="btn btn-primary">
                            <input type="submit" name ="accion" value="Busqueda Por Especialidad" class="btn btn-primary">
                            <input type="submit" name ="accion" value="Busqueda Por Fecha" class="btn btn-primary">
                            <input type="submit" name ="accion" value="Busqueda Por Horario" class="btn btn-primary">
                            <input type = "text" name = "cajatexto" class="form-control">
                        </div>
                    </form>    
                </div>
                <% ArrayList<Medico> lista_medicos = (ArrayList<Medico>) request.getAttribute("medicos");            %>
                <table class = "table table-hover">
                    <thead>
                        <tr>
                            <th>Codigo de Medico</th>
                            <th>Nombre</th>
                            <th>Numero de Colegiado</th>
                            <th>DPI</th>
                            <th>Correo Electronico</th>
                            <th>Hora_Entrada</th>
                            <th>Hora_Salida</th>
                            <th>Fecha en que inicio a trabajar</th>
                            <th>Especialidad</th>
                        </tr>
                    </thead>
                    <tbody>

                        <% if (lista_medicos != null) {
                                for (int i = 0; i < lista_medicos.size(); i++) {
                                    Medico medico = lista_medicos.get(i);

                        %>
                        <tr>
                            <td><%= medico.getCodigo()%></td>
                            <td><%= medico.getNombre()%></td>
                            <td><%= medico.getNumero_colegiado()%></td>
                            <td><%= medico.getDpi()%></td>
                            <td><%= medico.getCorreo()%></td>
                            <td><%= medico.getHora_entrada()%></td>
                            <td><%= medico.getHora_salida()%></td>
                            <td><%= medico.getFecha_inicio()%></td>
                            <td><%= medico.getTitulo()%></td>
                        </tr>
                        <%                                    }
                            }

                        %>
                    </tbody>
                </table>
            </div>            
        </div>
        <%            String mensaje = (String) session.getAttribute("alerta");
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
                <input type="submit" name ="accion" value="Regresar a la interfaz" class="btn btn-primary">
            </form>
        </div>
    </body>
</html>
