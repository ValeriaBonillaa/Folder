/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Mascota;
import modelo.MascotaDAO;
import vista.frmMascota;

/**
 *
 * @author 50688
 */
public class ControllerMascota implements ActionListener{
    
    frmMascota vistaMascota = new frmMascota();
    Mascota m = new Mascota();
    MascotaDAO dao = new MascotaDAO();

public ControllerMascota(frmMascota frm) {
    
        this.vistaMascota = frm;
        this.vistaMascota.btnGuardar.addActionListener(this);
        this.vistaMascota.btnEditar.addActionListener(this);
        this.vistaMascota.btnEliminar.addActionListener(this);;
        this.vistaMascota.btnLimpiar.addActionListener(this);
        this.vistaMascota.btnBuscar.addActionListener(this);
        this.vistaMascota.btnBuscarDueno.addActionListener(this);
        this.vistaMascota.btnRefrescar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vistaMascota.btnGuardar) {
            if (vistaMascota.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe ingresar un nombre");
            } else {
                agregarMascota();
            }
        }
        if (e.getSource() == vistaMascota.btnBuscar) {
            filtrarTablaPorNombre(vistaMascota.tblMascota, vistaMascota.txtBuscar.getText());               
        }
     
        if (e.getSource() == vistaMascota.btnEditar) {
            
            if (vistaMascota.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe ingresar un nombre");
            } else if (vistaMascota.txtRaza.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe ingresar la Raza");
            } else {
                actualizarMascota();
            }   
        }
        if (e.getSource() == vistaMascota.btnEliminar) {
            eliminarMascota();
        }
        if (e.getSource() == vistaMascota.btnLimpiar) {
            limpiarCampos();
        }
        if (e.getSource() == vistaMascota.btnRefrescar) {
            filtrarTablaPorNombre(vistaMascota.tblMascota, "");
        }
    }
    public void agregarMascota() {

        String nombre = vistaMascota.txtNombre.getText();
        String genero = vistaMascota.cbGenero.getSelectedItem().toString();
        String tipo = vistaMascota.txtTipo.getText();
        String raza = vistaMascota.txtRaza.getText();
        Boolean activo = vistaMascota.cbActivo.isSelected();
        Integer IdDueno = Integer.parseInt(vistaMascota.txtIdDueno.getText());
        m.setNombre(nombre);
        m.setGenero(genero);
        m.setTipo(tipo);
        m.setRaza(raza);
        m.setActivo(activo);
        m.setIdDueno(IdDueno);
        int r = dao.agregarMascota(m);

        if (r == 1) {
            JOptionPane.showMessageDialog(vistaMascota, "MASCOTA AGREGADO CORRECTAMENTE");
            filtrarTablaPorNombre(vistaMascota.tblMascota, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaMascota, "MASCOTA NO AGREGADO CORRECTAMENTE");
        }
    }
    
    public void actualizarMascota() {

        int idMascota = Integer.parseInt(vistaMascota.txtIdMascota.getText());
        String nombre = vistaMascota.txtNombre.getText();
        String genero = vistaMascota.cbGenero.getSelectedItem().toString();
        String tipo = vistaMascota.txtTipo.getText();
        String raza = vistaMascota.txtRaza.getText();
        Boolean activo = vistaMascota.cbActivo.isSelected();
        int idDueno = Integer.parseInt(vistaMascota.txtIdDueno.getText());
        m.setIdMascota(idMascota);
        m.setNombre(nombre);
        m.setGenero(genero);
        m.setTipo(tipo);
        m.setRaza(raza);
        m.setActivo(activo);
        m.setIdDueno(idDueno);
        
        int r = dao.actualizarMascota(m);

        if (r == 1) {
            JOptionPane.showMessageDialog(vistaMascota, "Mascota actualizada con exito");
            filtrarTablaPorNombre(vistaMascota.tblMascota, "");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vistaMascota, "Mascota NO actualizada");
        }
    }
    
     public void eliminarMascota() {

        int fila = vistaMascota.tblMascota.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaMascota, "Debe seleccionar un registro en la tabla");
        } else {
            int id = Integer.parseInt(vistaMascota.tblMascota.getValueAt(fila, 0).toString());

            int r = dao.eliminarMascota(id);

            if (r == 1) {
                JOptionPane.showMessageDialog(vistaMascota, "La mascota se elimino con exito");
                filtrarTablaPorNombre(vistaMascota.tblMascota, "");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vistaMascota, "La mascota NO se elimino");
            }
        }
    }
     
    public void filtrarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);
    }

    public void limpiarCampos() {

        vistaMascota.txtIdMascota.setText("");
        vistaMascota.txtNombre.setText("");
        vistaMascota.txtTipo.setText("");
        vistaMascota.cbGenero.setSelectedIndex(0);
        vistaMascota.txtRaza.setText("");
        vistaMascota.cbActivo.setText("");
    }
    
    public void inicio() {
        filtrarTablaPorNombre(vistaMascota.tblMascota, "");
        limpiarCampos();
    }
}
