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



/**
 *
 * @author admin
 */
public class ReportesDAO {

    PreparedStatement ps;
    Connection con;
    ResultSet rs;
    Conexion conectar = new Conexion();

    public void filtrarTablaPorFecha(JTable table, String filtro) {

        String[] titulos = {"ID CITA", "ASUNTO", "FECHA", "HORA", "FECHA CREACIÓN", "ID DUEÑO", "ID VETERINARIO", "ID USUARIO", "CEDULA DUEÑO"};
        String[] registros = new String[9];
        String sql = "SELECT * FROM cita WHERE fecha LIKE '%" + filtro + "%'";
        DefaultTableModel modelo;
        modelo = new DefaultTableModel(null, titulos);

        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                registros[0] = rs.getString("IdCita");
                registros[1] = rs.getString("asunto");
                registros[2] = rs.getString("fecha");
                registros[3] = rs.getString("hora");
                registros[4] = rs.getString("fechaCreacion");
                registros[5] = rs.getString("IdDueno");
                registros[6] = rs.getString("IdVeterinario");
                registros[7] = rs.getString("IdUsuario");
                registros[8] = rs.getString("CedulaDueno");
                modelo.addRow(registros);
            }
            table.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error al buscar los datos " + e.getMessage());
        }
    }

    public void filtrarTablaPorCedula(JTable table, String filtro) {

        String[] titulos = {"ID CITA", "ASUNTO", "FECHA", "HORA", "FECHA CREACIÓN", "ID DUEÑO", "ID VETERINARIO", "ID USUARIO", "CEDULA DUEÑO"};
        String[] registros = new String[9];
        String sql = "SELECT * FROM cita WHERE CedulaDueno LIKE '%" + filtro + "%'";
        DefaultTableModel modelo;
        modelo = new DefaultTableModel(null, titulos);

        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                registros[0] = rs.getString("IdCita");
                registros[1] = rs.getString("asunto");
                registros[2] = rs.getString("fecha");
                registros[3] = rs.getString("hora");
                registros[4] = rs.getString("fechaCreacion");
                registros[5] = rs.getString("IdDueno");
                registros[6] = rs.getString("IdVeterinario");
                registros[7] = rs.getString("IdUsuario");
                registros[8] = rs.getString("CedulaDueno");
                modelo.addRow(registros);
            }
            table.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error al buscar los datos " + e.getMessage());
        }
    }
}
