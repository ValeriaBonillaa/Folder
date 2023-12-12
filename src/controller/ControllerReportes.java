/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.ReportesDAO;
import vista.frmReportesCedula;
import vista.frmReportesFecha;

/**
 *
 * @author admin
 */
public class ControllerReportes implements ActionListener {

    frmReportesFecha vistaFecha = new frmReportesFecha();
    ReportesDAO dao = new ReportesDAO();
    frmReportesCedula vistaCedula = new frmReportesCedula();

    public ControllerReportes(frmReportesFecha frm) {
        this.vistaFecha = frm;
        this.vistaFecha.btnBuscar.addActionListener(this);
        this.vistaFecha.btnRefrescar.addActionListener(this);
    }

    public ControllerReportes(frmReportesCedula frm) {
        this.vistaCedula = frm;
        this.vistaCedula.btnCedula1.addActionListener(this);
        this.vistaCedula.btnRefrescar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaFecha.btnBuscar) {
            SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = sDate.format(vistaFecha.dateFecha.getDate());
            filtrarTablaPorFecha(vistaFecha.tableReportesFecha, fecha);
        }
        if (e.getSource() == vistaCedula.btnCedula1) {
            String cedula = JOptionPane.showInputDialog(vistaCedula, "Ingrese la cedula del cliente:");
            filtrarTablaPorCedula(vistaCedula.tableReportesCedula1, cedula);
        }
        if (e.getSource() == vistaFecha.btnRefrescar) {
            filtrarTablaPorFecha(vistaFecha.tableReportesFecha, "");
        }
        if (e.getSource() == vistaCedula.btnRefrescar) {
            filtrarTablaPorCedula(vistaCedula.tableReportesCedula1, "");
        }
    }

    public void filtrarTablaPorFecha(JTable table, String filtro) {
        dao.filtrarTablaPorFecha(table, filtro);
    }

    public void inicio() {
        filtrarTablaPorFecha(vistaFecha.tableReportesFecha, "");
        filtrarTablaPorCedula(vistaCedula.tableReportesCedula1, "");

    }
    private void filtrarTablaPorCedula(JTable table, String filtro) {
        dao.filtrarTablaPorCedula(table, filtro);
    }

}
