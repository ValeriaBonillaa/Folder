/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Usuarios;
import modelo.UsuariosDAO;
import vista.frmUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ashle
 */
public class ControllerUsuarios implements ActionListener{
     frmUsuarios vistaUsuarios = new frmUsuarios();
    Usuarios v = new Usuarios();
    UsuariosDAO dao = new UsuariosDAO();

    public ControllerUsuarios(frmUsuarios frm) { //Paso 3
        this.vistaUsuarios= frm;
        this.vistaUsuarios.btnGuardar.addActionListener(this);
        this.vistaUsuarios.btnBuscar.addActionListener(this);
        this.vistaUsuarios.btnEditar.addActionListener(this);
        this.vistaUsuarios.btnLimpiar.addActionListener(this);
        this.vistaUsuarios.btnEliminar.addActionListener(this);
        this.vistaUsuarios.btnRefrescar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vistaUsuarios.btnGuardar) {

            if (vistaUsuarios.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe ingresar un nombre");
            } else if (vistaUsuarios.txtApellido1.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe ingresar el primer apellido");
            } else if (vistaUsuarios.txtApellido2.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe ingresar el segundo apellido");
            } else if (vistaUsuarios.txtEmail.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe ingresar un Email");
            } else if (vistaUsuarios.txtNombreU.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe ingresar un Nombre de Usuario");
            } else if (vistaUsuarios.txtPassword.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe ingresar un password");
                
            } else {
                agregarUsuario();
            }
        }
        if (e.getSource() == vistaUsuarios.btnBuscar) {
            filtrarTablaPorNombre(vistaUsuarios.tblDatos, vistaUsuarios.txtBuscar.getText());
        }
        if (e.getSource() == vistaUsuarios.btnRefrescar) {
            filtrarTablaPorNombre(vistaUsuarios.tblDatos, "");
        }
        if (e.getSource() == vistaUsuarios.btnEditar) {

            if (vistaUsuarios.txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe de seleccionar un registro en la tabla");
            } else if (vistaUsuarios.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe de seleccionar un nombre ");
            } else if (vistaUsuarios.txtApellido1.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe seleccionar el primer apellido");
            } else if (vistaUsuarios.txtApellido2.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe seleccionar el segundo apellido");
            } else if (vistaUsuarios.txtEmail.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe seleccionar el email");
            } else if (vistaUsuarios.txtNombreU.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe seleccionar un nombre de Usuario");
            } else if (vistaUsuarios.txtPassword.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuarios, "Debe seleccionar un password");
            } else {
                actualizarUsuarios();
            }
        }
        if (e.getSource() == vistaUsuarios.btnEliminar) {
            eliminarUsuarios();
        }
        if (e.getSource() == vistaUsuarios.btnLimpiar) {
            limpiarCampos();
        }
    }

    private void agregarUsuario() {
        String nombre = vistaUsuarios.txtNombre.getText();
        String apellido1 = vistaUsuarios.txtApellido1.getText();
        String apellido2 = vistaUsuarios.txtApellido2.getText();
        String email = vistaUsuarios.txtEmail.getText();
        String nombreUsuario= vistaUsuarios.txtNombreU.getText();
        String password = vistaUsuarios.txtPassword.getText();
        String tipoUsuario = vistaUsuarios.cbTipoU.getSelectedItem().toString ();
        Boolean estado = vistaUsuarios.cbEstado.isSelected();

        v.setNombre(nombre);
        v.setApellido1(apellido1);
        v.setApellido2(apellido2);
        v.setEmail(email);
        v.setNombreUsuario(nombreUsuario);
        v.setPassword(password);
        v.setTipoUsuario(tipoUsuario);
        v.setEstado (estado);
        

        int r = dao.agregarUsuario(v);

        if (r == 1) {
            JOptionPane.showMessageDialog(vistaUsuarios, "USUARIO AGREGADO CORRECTAMENTE");
            filtrarTablaPorNombre(vistaUsuarios.tblDatos, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaUsuarios, "USUARIO NO AGREGADO CORRECTAMENTE");

        }
    }

    public void actualizarUsuarios() {
        
        int id = Integer.parseInt(vistaUsuarios.txtId.getText());
        String nombre = vistaUsuarios.txtNombre.getText();
        String apellido1 = vistaUsuarios.txtApellido1.getText();
        String apellido2 = vistaUsuarios.txtApellido2.getText();
        String email = vistaUsuarios.txtEmail.getText();
        String nombreUsuario= vistaUsuarios.txtNombreU.getText();
        String password = vistaUsuarios.txtPassword.getText();
        String tipoUsuario = vistaUsuarios.cbTipoU.getSelectedItem().toString ();
        Boolean estado = vistaUsuarios.cbEstado.isSelected();
     
        v.setIdUsuario(id);
        v.setNombre(nombre);
        v.setApellido1(apellido1);
        v.setApellido2(apellido2);
        v.setEmail(email);
        v.setNombreUsuario(nombreUsuario);
        v.setPassword(password);
        v.setTipoUsuario(tipoUsuario);
        v.setEstado (estado);

        int r = dao.actualizarUsuario(v);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaUsuarios, "USUARIO ACTUALIZADO CON EXITO");
            filtrarTablaPorNombre(vistaUsuarios.tblDatos, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaUsuarios, "USUARIO NO ACTUALIZADO");
        }
    }

    public void eliminarUsuarios() {
        int fila = vistaUsuarios.tblDatos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaUsuarios, "Debe seleccionar un registro en la tabla");
        } else {
            int id = Integer.parseInt(vistaUsuarios.tblDatos.getValueAt(fila, 0).toString());
            int r = dao.eliminarUsuario(id);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaUsuarios, "El Usuario se eliminó con exito");
                filtrarTablaPorNombre(vistaUsuarios.tblDatos, "");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vistaUsuarios, "El Usuario NO se eliminó");
            }
        }
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);
    }

    public void limpiarCampos() {

        vistaUsuarios.txtId.setText("");
        vistaUsuarios.txtBuscar.setText("");
        vistaUsuarios.txtNombre.setText("");
        vistaUsuarios.txtApellido1.setText("");
        vistaUsuarios.txtApellido2.setText("");
        vistaUsuarios.txtEmail.setText("");
        vistaUsuarios.txtNombreU.setText("");
        vistaUsuarios.txtPassword.setText("");
        vistaUsuarios.cbEstado.setText("");
        vistaUsuarios.cbTipoU.setSelectedIndex(0);
    }

    public void inicio() {
        filtrarTablaPorNombre(vistaUsuarios.tblDatos, "");
        limpiarCampos();
    }
}
