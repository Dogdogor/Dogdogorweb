<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.mycompany.dogdogor.Cliente" %>
<html>
<head><title>Listado de Clientes</title></head>
<body>
<h1>Clientes Registrados</h1>
<table border="1">
<tr>
    <th>Nombre</th><th>Teléfono</th><th>Correo</th><th>Acciones</th>
</tr>
<%
    List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
    if (listaClientes != null) {
        for (Cliente c : listaClientes) {
%>
<tr>
    <td><%= c.getNombre() %></td>
    <td><%= c.getTelefono() %></td>
    <td><%= c.getCorreo() %></td>
    <td>
        <a href="ClienteServlet?accion=editar&correo=<%=c.getCorreo()%>">Editar</a> |
        <a href="ClienteServlet?accion=eliminar&correo=<%=c.getCorreo()%>">Eliminar</a>
    </td>
</tr>
<%
        }
    } else {
%>
<tr><td colspan="4">No hay clientes registrados</td></tr>
<% } %>
</table>
<br>
<a href="registro.jsp">Registrar Nuevo Cliente</a>
</body>
</html>