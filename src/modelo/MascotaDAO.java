/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.Mascota;

/**
 *
 * @author Natalia
 */
public class MascotaDAO {

    PreparedStatement ps;
    Connection ccn;
    ResultSet rs;
    Conexion conectar = new Conexion();

    public int agregarMascota(Mascota user) {

        int r = 0;
        String sql = "INSERT INTO Mascota (nombre, genero, tipo, raza, activo, IdDueno) VALUES (?,?,?,?,?,?)";

        try {
            ccn = conectar.getConecction();
            ps = ccn.prepareStatement(sql);
            
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getGenero());
            ps.setString(3, user.getTipo());
            ps.setString(4, user.getRaza());
            ps.setBoolean(5, user.getActivo());
            ps.setInt(6, user.getIdDueno());
            
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public int actualizarMascota(Mascota user) {

        int r = 0;
        String sql = "UPDATE Mascota SET nombre=?, genero=?, tipo=?, raza=?, activo=?, IdDueno=? WHERE IdMascota=?";
        try {
            ccn = conectar.getConecction();
            ps = ccn.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getGenero());
            ps.setString(3, user.getTipo());
            ps.setString(4, user.getRaza());
            ps.setBoolean(5, user.getActivo());
            ps.setInt(6, user.getIdDueno());
            ps.setInt(7, user.getIdMascota());
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public int eliminarMascota(int id) {

        int r = 0;
        String sql = "DELETE FROM Mascota WHERE IdMascota=" + id;
        try {
            ccn = conectar.getConecction();
            ps = ccn.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {

        String[] titulos = {"ID", "NOMBRE", "GENERO", "TIPO", "RAZA", "ACTIVO", "ID DUEÑO"};
        String[] registros = new String[7];
        String sql = "SELECT * FROM Mascota WHERE Nombre LIKE '%" + filtro + "%'";
        DefaultTableModel modelo;
        modelo = new DefaultTableModel(null, titulos);

        try {
            ccn = conectar.getConecction();
            ps = ccn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                registros[0] = rs.getString("IdMascota");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("genero");
                registros[3] = rs.getString("tipo");
                registros[4] = rs.getString("raza");
                registros[5] = rs.getString("activo");
                registros[6] = rs.getString("IdDueno");
                modelo.addRow(registros);
            }
            table.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error al buscar los datos " + e.getMessage());
        }
    }
}
