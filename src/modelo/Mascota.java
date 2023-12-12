/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author 50688
 */
public class Mascota {
    
    private int idMascota;
    private String nombre;
    private String genero;
    private String tipo;
    private String raza;
    private boolean activo;
    private int idDueno;
    
     public Mascota() {
    }

    public Mascota(int idMascota, String nombre, String genero, String tipo, String raza, boolean activo ,int idDueno) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.genero = genero;
        this.tipo = tipo;
        this.raza = raza;
        this.activo = activo;
        this.idDueno = idDueno;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getTipo() {
        return tipo;
    }

    public String getRaza() {
        return raza;
    }

    public boolean getActivo() {
        return activo;
    }

    public int getIdDueno() {
        return idDueno;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

   public void setIdDueno(int idDueno) {
       this.idDueno = idDueno;
   }

}