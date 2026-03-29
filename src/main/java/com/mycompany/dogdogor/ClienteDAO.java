package com.mycompany.dogdogor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection conexion;

    public ClienteDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertar(Cliente c) throws SQLException {
        String sql = "INSERT INTO cliente(nombre, telefono, correo) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getCorreo());
            ps.executeUpdate();
        }
    }

    public List<Cliente> listar() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT nombre, telefono, correo FROM cliente";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setNombre(rs.getString("nombre"));
                c.setTelefono(rs.getString("telefono"));
                c.setCorreo(rs.getString("correo"));
                lista.add(c);
            }
        }
        return lista;
    }

    public Cliente obtenerPorCorreo(String correo) throws SQLException {
        Cliente c = null;
        String sql = "SELECT nombre, telefono, correo FROM cliente WHERE correo=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente();
                    c.setNombre(rs.getString("nombre"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setCorreo(rs.getString("correo"));
                }
            }
        }
        return c;
    }

    public void actualizar(Cliente c) throws SQLException {
        String sql = "UPDATE cliente SET nombre=?, telefono=? WHERE correo=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getCorreo());
            ps.executeUpdate();
        }
    }

    public void eliminar(String correo) throws SQLException {
        String sql = "DELETE FROM cliente WHERE correo=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.executeUpdate();
        }
    }
}