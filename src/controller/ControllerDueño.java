/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Dueño;
import modelo.DueñoDAO;
import vista.frmDueño;

/**
 *
 * @author lgfon
 */
public class ControllerDueño implements ActionListener {

    frmDueño vistaDueno = new frmDueño();
    Dueño d = new Dueño();
    DueñoDAO dao = new DueñoDAO();

    public ControllerDueño(frmDueño frm) {

        this.vistaDueno = frm;
        this.vistaDueno.btnGuardar.addActionListener(this);
        this.vistaDueno.btnEditar.addActionListener(this);
        this.vistaDueno.btnEliminar.addActionListener(this);
        this.vistaDueno.btnLimpiar.addActionListener(this);
        this.vistaDueno.btnBuscar.addActionListener(this);
        this.vistaDueno.btnRefrescar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaDueno.btnGuardar) {

            if (vistaDueno.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un nombre");
            } else if (vistaDueno.txtApellido1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un primer apellido");
            } else if (vistaDueno.txtApellido2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un segundo apellido");
            } else if (vistaDueno.txtCedula.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un número cédula");
            } else if (vistaDueno.txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un email");
            } else if (vistaDueno.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un telefono");
            } else if (vistaDueno.txtDireccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar una dirección");
            } else {
                agregarDueno();
            }
        }
        if (e.getSource() == vistaDueno.btnBuscar) {
            filtrarTablaPorNombre(vistaDueno.tblDatos, vistaDueno.txtBuscar.getText());
        }

        if (e.getSource() == vistaDueno.btnEditar) {

            if (vistaDueno.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un nombre");
            } else if (vistaDueno.txtApellido1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un primer apellido");
            } else if (vistaDueno.txtApellido2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un segundo apellido");
            } else if (vistaDueno.txtCedula.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un número cédula");
            } else if (vistaDueno.txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un email");
            } else if (vistaDueno.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar un telefono");
            } else if (vistaDueno.txtDireccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueno, "Debe ingresar una dirección");
            } else {
                actualizarDueno();
            }
        }
        if (e.getSource() == vistaDueno.btnEliminar) {
            eliminarDueno();
        }
        if (e.getSource() == vistaDueno.btnLimpiar) {
            limpiarCampos();
        }
        if (e.getSource() == vistaDueno.btnRefrescar) {
            filtrarTablaPorNombre(vistaDueno.tblDatos, "");
        }
    }

    public void agregarDueno() {

        String nombre = vistaDueno.txtNombre.getText();
        String apellido1 = vistaDueno.txtApellido1.getText();
        String apellido2 = vistaDueno.txtApellido2.getText();
        String cedula = vistaDueno.txtCedula.getText();
        String genero = vistaDueno.cbGenero.getSelectedItem().toString();
        String email = vistaDueno.txtEmail.getText();
        String telefono = vistaDueno.txtTelefono.getText();
        String direccion = vistaDueno.txtDireccion.getText();
        d.setNombre(nombre);
        d.setApellido1(apellido1);
        d.setApellido2(apellido2);
        d.setCedula(cedula);
        d.setGenero(genero);
        d.setEmail(email);
        d.setTelefono(telefono);
        d.setDireccion(direccion);
        int r = dao.agregarDueno(d);

        if (r == 1) {
            JOptionPane.showMessageDialog(vistaDueno, "DUEÑO AGREGADO CORRECTAMENTE");
            filtrarTablaPorNombre(vistaDueno.tblDatos, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaDueno, "DUEÑO NO AGREGADO CORRECTAMENTE");
        }
    }

    public void actualizarDueno() {

        int idDueno = Integer.parseInt(vistaDueno.txtIdDueño.getText());
        String nombre = vistaDueno.txtNombre.getText();
        String apellido1 = vistaDueno.txtApellido1.getText();
        String apellido2 = vistaDueno.txtApellido2.getText();
        String cedula = vistaDueno.txtCedula.getText();
        String genero = vistaDueno.cbGenero.getSelectedItem().toString();
        String email = vistaDueno.txtEmail.getText();
        String telefono = vistaDueno.txtTelefono.getText();
        String direccion = vistaDueno.txtDireccion.getText();
        d.setIdDueno(idDueno);
        d.setNombre(nombre);
        d.setApellido1(apellido1);
        d.setApellido2(apellido2);
        d.setCedula(cedula);
        d.setGenero(genero);
        d.setEmail(email);
        d.setTelefono(telefono);
        d.setDireccion(direccion);

        int r = dao.actualizarDueno(d);

        if (r == 1) {
            JOptionPane.showMessageDialog(vistaDueno, "Dueño actualizado con exito");
            filtrarTablaPorNombre(vistaDueno.tblDatos, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaDueno, "Dueño NO actualizado");
        }
    }

    public void eliminarDueno() {

        int fila = vistaDueno.tblDatos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaDueno, "Debe seleccionar un registro en la tabla");
        } else {
            int id = Integer.parseInt(vistaDueno.tblDatos.getValueAt(fila, 0).toString());

            int r = dao.eliminarDueno(id);

            if (r == 1) {
                JOptionPane.showMessageDialog(vistaDueno, "El dueño se elimino con exito");
                filtrarTablaPorNombre(vistaDueno.tblDatos, "");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vistaDueno, "El dueño NO se elimino");
            }
        }
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);
    }

    public void limpiarCampos() {

        vistaDueno.txtIdDueño.setText("");
        vistaDueno.txtNombre.setText("");
        vistaDueno.txtApellido1.setText("");
        vistaDueno.txtApellido2.setText("");
        vistaDueno.txtCedula.setText("");
        vistaDueno.cbGenero.setSelectedIndex(0);
        vistaDueno.txtEmail.setText("");
        vistaDueno.txtTelefono.setText("");
        vistaDueno.txtDireccion.setText("");
    }

    public void inicio() {
        filtrarTablaPorNombre(vistaDueno.tblDatos, "");
        limpiarCampos();
    }

}
