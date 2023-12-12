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
public class UsuariosDAO {
    
    PreparedStatement ps;
    Connection con;
    ResultSet rs;
    Conexion conectar = new Conexion();

    public int agregarUsuario(Usuarios user) {

        int r = 0;
        String sql = "INSERT INTO Usuario (nombre, apellido1, apellido2, email, nombreUsuario, password, tipoUsuario, activo) VALUES (?,?,?,?,?,?,?,?)";
        try {
            con = conectar.getConecction(); //1: Abrir conexion con la BD
            ps = con.prepareStatement(sql); //2: Preparar el query de sql 
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido1());
            ps.setString(3, user.getApellido2());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getNombreUsuario());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getTipoUsuario());
            ps.setBoolean(8, user.getEstado());                      //3: Completar el query
            r = ps.executeUpdate(); //4: Ejecutar query 0= Fallo 1= success
        } catch (SQLException e) {
        }
        return r;
    }

    public int actualizarUsuario(Usuarios user) {

        int r = 0;
        String sql = "UPDATE usuario SET nombre=?, apellido1=?, apellido2=?, email=?, nombreUsuario=?, password=?, tipoUsuario=?, activo=? WHERE IdUsuario=?"; //value1..value2
        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido1());
            ps.setString(3, user.getApellido2());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getNombreUsuario());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getTipoUsuario());
            ps.setBoolean(8, user.getEstado());
            ps.setInt(9, user.getIdUsuario());
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public int eliminarUsuario(int id) {

        int r = 0;
        String sql = "DELETE FROM usuario WHERE IdUsuario=" + id;
        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        String[] titulos = {"IDUSUARIO", "NOMBRE", "APELLIDO1", "APELLIDO2", "EMAIL", "NOMBREUSUARIO", "PASSWORD", "TIPOUSUARIO", "ESTADO"};
        String[] registros = new String[9];
        String sql = "SELECT * FROM usuario WHERE nombre LIKE '%" + filtro + "%'"; //"Her"
        DefaultTableModel modelo;
        modelo = new DefaultTableModel(null, titulos);

        try {
            con = conectar.getConecction();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                registros[0] = rs.getString("IdUsuario");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("apellido1");
                registros[3] = rs.getString("apellido2");
                registros[4] = rs.getString("email");
                registros[5] = rs.getString("nombreUsuario");
                registros[6] = rs.getString("password");
                registros[7] = rs.getString("tipoUsuario");
                registros[8] = rs.getString("activo");
                modelo.addRow(registros);
            }
            table.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error al buscar los datos " + e.getMessage());
        }
    }
}
