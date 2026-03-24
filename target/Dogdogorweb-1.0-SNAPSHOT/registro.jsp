<%@ page contentType="text/html;charset=UTF-8" %>

<h2>Registro de Cliente - DogDogor</h2>

<form action="ClienteServlet" method="post">
    Nombre: <input type="text" name="nombre" required><br><br>
    Teléfono: <input type="text" name="telefono" required><br><br>
    Correo: <input type="text" name="correo" required><br><br>
    <input type="submit" value="Registrar">
</form>