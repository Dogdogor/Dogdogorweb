<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Registro Exitoso</title></head>
<body>
<h1>Cliente Registrado Correctamente</h1>
<p>Cliente: <b><%= request.getAttribute("nombre") %></b> registrado con éxito.</p>
<a href="ClienteServlet">Volver al Listado de Clientes</a>
</body>
</html>