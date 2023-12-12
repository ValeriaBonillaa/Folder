/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package modelo;

/**
 *
 * @author ashle
 */
public class Veterinarios {

   public int idVeterinario;
    public String nombre;
    public String apellido1;
    public String apellido2;
    public String cedula;
    public String codProfesional;
    public String email;
    public String telefono;
    public String direccion;
    public Boolean estado;

    public Veterinarios() {
    }

    public Veterinarios(int idVeterinario, String nombre, String apellido1, String apellido2, String cedula, String codProfesional, String email, String telefono, String direccion, Boolean estado) {
        this.idVeterinario = idVeterinario;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.cedula = cedula;
        this.codProfesional = codProfesional;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCodProfesional() {
        return codProfesional;
    }

    public void setCodProfesional(String codProfesional) {
        this.codProfesional = codProfesional;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
