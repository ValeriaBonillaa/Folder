/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Veterinarios;
import modelo.VeterinariosDAO;
import vista.frmVeterinarios;

/**
 *
 * @author ashle
 */
public class ControllerVeterinarios implements ActionListener{

    frmVeterinarios vistaVeterinarios = new frmVeterinarios();
    Veterinarios v = new  Veterinarios();
    VeterinariosDAO dao = new VeterinariosDAO();

        public ControllerVeterinarios(frmVeterinarios frm) { //Paso 3
        this.vistaVeterinarios= frm;
        this.vistaVeterinarios.btnGuardar.addActionListener(this);
        this.vistaVeterinarios.btnBuscar.addActionListener(this);
        this.vistaVeterinarios.btnEditar.addActionListener(this);
        this.vistaVeterinarios.btnLimpiar.addActionListener(this);
        this.vistaVeterinarios.btnEliminar.addActionListener(this);
        this.vistaVeterinarios.btnRefrescar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vistaVeterinarios.btnGuardar) {

            if (vistaVeterinarios.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe ingresar un nombre");
            } else if (vistaVeterinarios.txtApellido1.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe ingresar el primer apellido");
            } else if (vistaVeterinarios.txtApellido2.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe ingresar el segundo apellido");
            } else if (vistaVeterinarios.txtCedula.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe ingresar un numero de cédula");
            } else if (vistaVeterinarios.txtCodigo.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe ingresar un código profesional");
            } else if (vistaVeterinarios.txtEmail.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe ingresar un Email");
            } else if (vistaVeterinarios.txtTelefono.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe ingresar un número de teléfono");
            } else if (vistaVeterinarios.txtDireccion.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe ingresar el segundo apellido");
            } else {
                agregarVeterinario();
            }
        }
        if (e.getSource() == vistaVeterinarios.btnBuscar) {
            filtrarTablaPorNombre(vistaVeterinarios.tblDatos, vistaVeterinarios.txtBuscar.getText());
        }
        if (e.getSource() == vistaVeterinarios.btnRefrescar) {
            filtrarTablaPorNombre(vistaVeterinarios.tblDatos, "");
        }
        if (e.getSource() == vistaVeterinarios.btnEditar) {

            if (vistaVeterinarios.txtId.getText().equals("")) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe de seleccionar un registro en la tabla");
            } else if (vistaVeterinarios.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe de seleccionar un nombre ");
            } else if (vistaVeterinarios.txtApellido1.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe seleccionar el primer apellido");
            } else if (vistaVeterinarios.txtApellido2.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe seleccionar el segundo apellido");
            } else if (vistaVeterinarios.txtCedula.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe seleccionar una cedula");
            } else if (vistaVeterinarios.txtCodigo.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe seleccionar un código profesional");
            } else if (vistaVeterinarios.txtEmail.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe seleccionar el email");
            } else if (vistaVeterinarios.txtTelefono.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe seleccionar un numero de telefono");
            } else if (vistaVeterinarios.txtDireccion.toString().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "Debe seleccionar una direccion");
            } else {
                actualizarUsuarios();
            }
        }
        if (e.getSource() == vistaVeterinarios.btnEliminar) {
            eliminarVeterinarios();
        }
        if (e.getSource() == vistaVeterinarios.btnLimpiar) {
            limpiarCampos();
        }
    }

    private void agregarVeterinario() {
        
        String nombre = vistaVeterinarios.txtNombre.getText();
        String apellido1 = vistaVeterinarios.txtApellido1.getText();
        String apellido2 = vistaVeterinarios.txtApellido2.getText();
        String cedula = vistaVeterinarios.txtCedula.getText();
        String codigoPorfesional = vistaVeterinarios.txtCodigo.getText();
        String email= vistaVeterinarios.txtEmail.getText();
        String telefono = vistaVeterinarios.txtTelefono.getText();
        String direccion = vistaVeterinarios.txtDireccion.getText();
        Boolean estado = vistaVeterinarios.cbEstado.isSelected();
        v.setNombre(nombre);
        v.setApellido1(apellido1);
        v.setApellido2(apellido2);
        v.setCedula(cedula);
        v.setCodProfesional(codigoPorfesional);
        v.setEmail(email);
        v.setTelefono(telefono);
        v.setDireccion(direccion);
        v.setEstado (estado);
        int r = dao.agregarVeterinario(v);

        if (r == 1) {
            JOptionPane.showMessageDialog(vistaVeterinarios, "VETERINARIO AGREGADO CORRECTAMENTE");
            filtrarTablaPorNombre(vistaVeterinarios.tblDatos, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaVeterinarios, "VETERINARIO NO AGREGADO CORRECTAMENTE");

        }
    }

    public void actualizarUsuarios() {
        
        int id = Integer.parseInt(vistaVeterinarios.txtId.getText());
        String nombre = vistaVeterinarios.txtNombre.getText();
        String apellido1 = vistaVeterinarios.txtApellido1.getText();
        String apellido2 = vistaVeterinarios.txtApellido2.getText();
        String cedula = vistaVeterinarios.txtCedula.getText();
        String codigoPorfesional = vistaVeterinarios.txtCodigo.getText();
        String email= vistaVeterinarios.txtEmail.getText();
        String telefono = vistaVeterinarios.txtTelefono.getText();
        String direccion = vistaVeterinarios.txtDireccion.getText();
        Boolean estado = vistaVeterinarios.cbEstado.isSelected();

        v.setIdVeterinario(id);
        v.setNombre(nombre);
        v.setApellido1(apellido1);
        v.setApellido2(apellido2);
        v.setCedula(cedula);
        v.setCodProfesional(codigoPorfesional);
        v.setEmail(email);
        v.setTelefono(telefono);
        v.setDireccion(direccion);
        v.setEstado (estado);

        int r = dao.actualizarVeterinario(v);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaVeterinarios, "VETERINARIO ACTUALIZADO CON EXITO");
            filtrarTablaPorNombre(vistaVeterinarios.tblDatos, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaVeterinarios, "VETERINARIO NO ACTUALIZADO");
        }
    }

    public void eliminarVeterinarios() {
        int fila = vistaVeterinarios.tblDatos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaVeterinarios, "Debe seleccionar un registro en la tabla");
        } else {
            int id = Integer.parseInt(vistaVeterinarios.tblDatos.getValueAt(fila, 0).toString());
            int r = dao.eliminarVeterinario(id);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaVeterinarios, "El veterinario se eliminó con exito");
                filtrarTablaPorNombre(vistaVeterinarios.tblDatos, "");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vistaVeterinarios, "El veterinario NO se eliminó");
            }
        }
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);
    }

    public void limpiarCampos() {

        vistaVeterinarios.txtId.setText("");
        vistaVeterinarios.txtBuscar.setText("");
        vistaVeterinarios.txtNombre.setText("");
        vistaVeterinarios.txtApellido1.setText("");
        vistaVeterinarios.txtApellido2.setText("");
        vistaVeterinarios.txtCedula.setText("");
        vistaVeterinarios.txtCodigo.setText("");
        vistaVeterinarios.txtEmail.setText("");
        vistaVeterinarios.txtTelefono.setText("");
        vistaVeterinarios.txtDireccion.setText("");
        vistaVeterinarios.cbEstado.setText ("");
        
    }
    public void inicio() {
        filtrarTablaPorNombre(vistaVeterinarios.tblDatos, "");
        limpiarCampos();
    }
}
