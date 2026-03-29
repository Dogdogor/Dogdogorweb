package com.mycompany.dogdogorweb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;

import com.mycompany.dogdogor.Cliente;
import com.mycompany.dogdogor.ClienteDAO;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {

    private String url = "jdbc:mysql://localhost:3306/dogdogor?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String user = "root";
    private String pass = "123456";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, user, pass);
            ClienteDAO dao = new ClienteDAO(conexion);
            String accion = request.getParameter("accion");

            if (accion == null) {
                List<Cliente> lista = dao.listar();
                request.setAttribute("listaClientes", lista);
                RequestDispatcher rd = request.getRequestDispatcher("listar.jsp");
                rd.forward(request, response);
                conexion.close();
                return;
            }

            if ("eliminar".equals(accion)) {
                String correo = request.getParameter("correo");
                dao.eliminar(correo);
                response.sendRedirect("ClienteServlet");
                conexion.close();
                return;
            }

            if ("editar".equals(accion)) {
                String correo = request.getParameter("correo");
                Cliente cliente = dao.obtenerPorCorreo(correo);
                request.setAttribute("cliente", cliente);
                RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
                rd.forward(request, response);
                conexion.close();
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, user, pass);
            ClienteDAO dao = new ClienteDAO(conexion);

            if (accion == null) {
                Cliente c = new Cliente();
                c.setNombre(nombre);
                c.setTelefono(telefono);
                c.setCorreo(correo);
                dao.insertar(c);
                request.setAttribute("nombre", nombre);
                RequestDispatcher rd = request.getRequestDispatcher("resultado.jsp");
                rd.forward(request, response);
                conexion.close();
                return;
            }

            if ("actualizar".equals(accion)) {
                Cliente c = new Cliente();
                c.setNombre(nombre);
                c.setTelefono(telefono);
                c.setCorreo(correo);
                dao.actualizar(c);
                response.sendRedirect("ClienteServlet");
                conexion.close();
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error en el proceso: " + e.getMessage());
        }
    }
}