package com.mycompany.dogdogorweb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Servlet encargado de procesar el registro de clientes en el sistema DogDogOr
@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {

   // Método que se ejecuta cuando el usuario envía el formulario
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       // Capturar datos enviados desde el formulario
String nombre = request.getParameter("nombre");
String telefono = request.getParameter("telefono");
String correo = request.getParameter("correo");

        // Configuración de conexión a la base de datos MySQL
        String url = "jdbc:mysql://localhost:3306/dogdogor?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String pass = "123456";

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer conexión con auto-commit activado
            Connection conexion = DriverManager.getConnection(url, user, pass);
            conexion.setAutoCommit(true);

           // Preparar sentencia SQL para insertar datos del cliente
            String sql = "INSERT INTO cliente(nombre, telefono, correo) VALUES(?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, correo);

            // Ejecutar la inserción en la base de datos
            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                System.out.println("INSERT OK"); // log en consola de Tomcat
            } else {
                System.out.println("NO INSERT");
            }

            // Cerrar recursos
            ps.close();
            conexion.close();

        } catch (Exception e) {
            // Mostrar error en navegador y consola
            e.printStackTrace();
            response.getWriter().println("Error al guardar registro: " + e.getMessage());
            return; // detener ejecución para no redirigir
        }

        // Redirigir a la página de resultado con el nombre del cliente
        response.sendRedirect("resultado.jsp?nombre=" + nombre);
    }
}