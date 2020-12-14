/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

public class Pais {

    private int idPais;
    private String nombrePais;

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
    
    /**
     * Constructor con parametros del pais
     * @param nombrePais nombre del pais
     */
    public Pais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
    
    /**
     * Constructor vacio
     */
    public Pais() {
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Pais{" + "idPais=" + idPais + "nombrePais=" + nombrePais + '}';
    }

}
