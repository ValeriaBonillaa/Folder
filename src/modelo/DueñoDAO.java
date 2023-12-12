/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lgfon
 */
public class DueñoDAO {

    PreparedStatement ps;
    Connection con;
    ResultSet rs;
    Conexion conectar = new Conexion();

    public int agregarDueno(Dueño user) {

        int r = 0;
        String sql = "INSERT INTO Dueno (nombre, apellido1, apellido2, cedulaDueno, genero, email, telefono, direccion) VALUES (?,?,?,?,?,?,?,?)";

        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido1());
            ps.setString(3, user.getApellido2());
            ps.setString(4, user.getCedula());
            ps.setString(5, user.getGenero());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getTelefono());
            ps.setString(8, user.getDireccion());
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public Dueño buscarDuenoPorID(int ID) {
        String sql = "SELECT * FROM Dueno WHERE IdDueno = " + ID;
        Dueño Resultado = new Dueño();
        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Resultado.setIdDueno(ID);
                Resultado.setCedula(rs.getString("cedulaDueno"));
                Resultado.setNombre(rs.getString("nombre"));
                Resultado.setApellido1(rs.getString("apellido1"));
                Resultado.setApellido2(rs.getString("apellido2"));
                Resultado.setGenero(rs.getString("genero"));
                Resultado.setEmail(rs.getString("email"));
                Resultado.setTelefono(rs.getString("telefono"));
                Resultado.setDireccion(rs.getString("direccion"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar los datos " + e.getMessage());
            Resultado.setIdDueno(-1);
        }
        return Resultado;
    }

    public int actualizarDueno(Dueño user) {

        int r = 0;
        String sql = "UPDATE Dueno SET nombre=?, apellido1=?, apellido2=?, cedulaDueno=?, genero=?, email=?, telefono=?, direccion=? WHERE IdDueno=?";
        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido1());
            ps.setString(3, user.getApellido2());
            ps.setString(4, user.getCedula());
            ps.setString(5, user.getGenero());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getTelefono());
            ps.setString(8, user.getDireccion());
            ps.setInt(9, user.getIdDueno());
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public int eliminarDueno(int id) {

        int r = 0;
        String sql = "DELETE FROM Dueno WHERE IdDueno=" + id;
        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {

        String[] titulos = {"ID", "NOMBRE", "APELLIDO1", "APELLIDO2", "CÉDULA", "GÉNERO", "EMAIL", "TELÉFONO", "DIRECCIÓN"};
        String[] registros = new String[9];
        String sql = "SELECT * FROM Dueno WHERE Nombre LIKE '%" + filtro + "%'";
        DefaultTableModel modelo;
        modelo = new DefaultTableModel(null, titulos);

        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                registros[0] = rs.getString("IdDueno");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("apellido1");
                registros[3] = rs.getString("apellido2");
                registros[4] = rs.getString("cedulaDueno");
                registros[5] = rs.getString("genero");
                registros[6] = rs.getString("email");
                registros[7] = rs.getString("telefono");
                registros[8] = rs.getString("direccion");
                modelo.addRow(registros);
            }
            table.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error al buscar los datos " + e.getMessage());
        }
    }
}
