/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

public class Genero {

    //Falta hacer la lista de genero en el lado logico
    
    private int idGenero;
    private String nombreGenero;
    private String descripcion;


    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }
    
    /**
     * Constructor con parametros de Genero
     * @param nombreGenero nombre de Genero
     * @param descripcion descripcion de Genero
     */
    public Genero(String nombreGenero, String descripcion) {
        this.nombreGenero = nombreGenero;
        this.descripcion = descripcion;
    }
    
    /**
     * Constructor vacio
     */
    public Genero() {
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Genero{" + "idGenero=" + idGenero + "nombreGenero=" + nombreGenero + ", descripcion=" + descripcion + '}';
    }

}
