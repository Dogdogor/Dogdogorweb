<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Registrar Cliente</title></head>
<body>
<h1>Registrar Cliente</h1>
<form action="ClienteServlet" method="post">
    Nombre: <input type="text" name="nombre" required><br>
    Teléfono: <input type="text" name="telefono" required><br>
    Correo: <input type="email" name="correo" required><br>
    <input type="submit" value="Registrar">
</form>
<br>
<a href="ClienteServlet">Ver Listado de Clientes</a>
</body>
</html>