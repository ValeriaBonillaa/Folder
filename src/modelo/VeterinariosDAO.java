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
 * @author ashle
 */
public class VeterinariosDAO {
    
    PreparedStatement ps;
    Connection con;
    ResultSet rs;
    Conexion conectar = new Conexion();

    public int agregarVeterinario(Veterinarios user) {

        int r = 0;
        String sql = "INSERT INTO Veterinario (nombre, apellido1, apellido2, cedula, codProfesional, email, telefono, direccion, activo) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            con = conectar.getConecction(); //1: Abrir conexion con la BD
            ps = con.prepareStatement(sql); //2: Preparar el query de sql 
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido1());
            ps.setString(3, user.getApellido2());
            ps.setString(4, user.getCedula());
            ps.setString(5, user.getCodProfesional());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getTelefono());
            ps.setString(8, user.getDireccion());
            ps.setBoolean(9, user.getEstado());                      //3: Completar el query
            r = ps.executeUpdate(); //4: Ejecutar query 0= Fallo 1= success
        } catch (SQLException e) {
        }
        return r;
    }

    public int actualizarVeterinario(Veterinarios user) {

        int r = 0;
        String sql = "UPDATE veterinario SET nombre=?, apellido1=?, apellido2=?, cedula=?, codProfesional=?, email=?, telefono=?, direccion=?, activo=? WHERE IdVeterinario=?"; //value1..value2
        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido1());
            ps.setString(3, user.getApellido2());
            ps.setString(4, user.getCedula());
            ps.setString(5, user.getCodProfesional());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getTelefono());
            ps.setString(8, user.getDireccion());
            ps.setBoolean(9, user.getEstado());
            ps.setInt(10, user.getIdVeterinario());
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public int eliminarVeterinario(int id) {

        int r = 0;
        String sql = "DELETE FROM veterinario WHERE IdVeterinario=" + id;
        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        String[] titulos = {"IDVETERINARIO", "NOMBRE", "APELLIDO1", "APELLIDO2", "CEDULA", "CODIGOPROFESIONAL", "EMAIL", "TELEFONO", "DIRECCION", "ESTADO"};
        String[] registros = new String[10];
        String sql = "SELECT * FROM Veterinario WHERE nombre LIKE '%" + filtro + "%'"; //"Her"
        DefaultTableModel modelo;
        modelo = new DefaultTableModel(null, titulos);

        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                registros[0] = rs.getString("IdVeterinario");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("apellido1");
                registros[3] = rs.getString("apellido2");
                registros[4] = rs.getString("cedula");
                registros[5] = rs.getString("codProfesional");
                registros[6] = rs.getString("email");
                registros[7] = rs.getString("telefono");
                registros[8] = rs.getString("direccion");
                registros[9] = rs.getString("activo");
                modelo.addRow(registros);
            }
            table.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error al buscar los datos " + e.getMessage());
        }
    }
}


