<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.dogdogor.Cliente" %>
<%
    Cliente c = (Cliente) request.getAttribute("cliente");
%>
<html>
<head><title>Editar Cliente</title></head>
<body>
<h1>Editar Cliente</h1>
<form action="ClienteServlet" method="post">
    <input type="hidden" name="accion" value="actualizar">
    Nombre: <input type="text" name="nombre" value="<%=c.getNombre()%>" required><br>
    Teléfono: <input type="text" name="telefono" value="<%=c.getTelefono()%>" required><br>
    Correo: <input type="email" name="correo" value="<%=c.getCorreo()%>" readonly><br>
    <input type="submit" value="Actualizar">
</form>
<br>
<a href="ClienteServlet">Volver al Listado de Clientes</a>
</body>
</html>