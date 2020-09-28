<%-- 
    Document   : index
    Created on : 23/09/2020, 19:50:58
    Author     : oscar19
--%>

<%@page import="java.io.File"%>
<%@page import="principal.Carga"%>
<%@page import="principal.Datos_Conexion"%>
<%@page import="usuarios.Administrador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="acciones_usuarios.DM_Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Inicial</title>
    </head>
    <body>
        <%
            Datos_Conexion dc = new Datos_Conexion();
            dc.EliminarSesion();
        %>
        <div>
            <h1> Hospital Proyecto 2 </h1>
            <a href="Inicial?accion=paciente">Ingreso Paciente</a>
        </div>
        <div>
            <a href="Inicial?accion=medico">Ingreso Medico</a>
        </div>
        <div>   
            <%
                DM_Administrador dmadmin = new DM_Administrador();
                ArrayList<Administrador> lista = dmadmin.VerAdministradores();
                if (lista.size() == 0) {
            %>
            <form action = "Inicial" method="POST" >
                <div class="form-group">
                    <label >No hay datos en la base de datos. Ingresa la ruta de tu archivo XML </label>
                    <input type="text" class="form-control-file" name = "archivoxml">
                </div>
                <input type="submit" class="btn btn-primary" name = "accion" value="CargarArchivo">
            </form>
        </div>
        <%
            }
        %>
        <%
            String mensaje = (String) session.getAttribute("mensaje");
            if (mensaje != null) {
        %>
        <div class="alert alert-primary">
            <%= mensaje%>
        </div>
        <%
            }
        %>
    </body>
</html>
