<%-- 
    Document   : pacientes
    Created on : 30/09/2020, 16:14:17
    Author     : oscar19
--%>

<%@page import="usuarios.Paciente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="acciones_usuarios.DM_Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <title>Interfaz Administrador</title>
    </head>
    <body>
        <%
            DM_Paciente dmpac = new DM_Paciente();
            ArrayList<Paciente> pacientes = dmpac.VerPacientes();
        %>
        <div><!-- Tabla que muestra los pacientes de la empresa -->
            <h3>Pacientes en la empresa</h3>
            <table class = "table table-hover">
                <thead>
                    <tr>
                        <th>Codigo de Paciente</th>
                        <th>Nombre</th>
                        <th>Sexo</th>
                        <th>Fecha de Nacimiento</th>
                        <th>DPI</th>
                        <th>Telefono</th>
                        <th>Peso</th>
                        <th>Tipo de Sangre</th>
                        <th>Correo Electronico</th>
                        <th>Contraseña</th>  
                    </tr>
                </thead>
                <tbody>

                    <% if (pacientes != null) {
                            for (int i = 0; i < pacientes.size(); i++) {
                                Paciente p = pacientes.get(i);

                    %>
                    <tr>
                        <td><%= p.getCodigo()%></td> 
                        <td><%= p.getNombre()%></td> 
                        <td><%= p.getSexo()%></td> 
                        <td><%= p.getFecha_de_nacimiento()%></td> 
                        <td><%= p.getDpi()%></td> 
                        <td><%= p.getTelefono()%></td> 
                        <td><%= p.getPeso()%></td> 
                        <td><%= p.getTipo_sangre()%></td> 
                        <td><%= p.getCorreo()%></td> 
                        <td><%= p.getContraseña()%></td> 
                        <td> 
                            <a href="ServletAdmin?accion=editar_pac&code=<%= p.getCodigo()%>"> Editar </a>
                            <a href="ServletAdmin?accion=eliminar_pac&code=<%= p.getCodigo()%>"> Eliminar </a>
                        </td>
                    </tr>
                    <%                                    }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
