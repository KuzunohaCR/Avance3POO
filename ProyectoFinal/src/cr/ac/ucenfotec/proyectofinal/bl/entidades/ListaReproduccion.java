/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

import java.util.ArrayList;
import java.util.Date;

public class ListaReproduccion {

    private int idListaReproduccion;
    private NoAdmin noAdmin;
    private ArrayList<Cancion> canciones;
    private String nombreLista;
    private int calificacion;
    private Date fechaCreacion;

    public NoAdmin getNoAdmin() {
        return noAdmin;
    }

    public void setNoAdmin(NoAdmin idNoAdmin) {
        this.noAdmin = idNoAdmin;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }

    public int getIdListaReproduccion() {
        return idListaReproduccion;
    }

    public void setIdListaReproduccion(int idListaReproduccion) {
        this.idListaReproduccion = idListaReproduccion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    /**
     * Constructor vacio
     */
    public ListaReproduccion() {
    }

    /**
     * Constructor con parametros de la lista de reproduccion
     *
     * @param canciones cancion de la lista de reproduccion
     * @param nombreLista nombre de la lista de reproduccion
     * @param calificacion calificacion de la lista de reproduccion
     * @param fechaCreacion fecha de creacion de la lista de reproduccion
     */
    public ListaReproduccion(NoAdmin idNoAdmin, ArrayList<Cancion> canciones, String nombreLista, int calificacion, Date fechaCreacion) {
        this.noAdmin = idNoAdmin;
        this.canciones = canciones;
        this.nombreLista = nombreLista;
        this.calificacion = calificacion;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ListaReproduccion{" + "canciones=" + canciones + ", nombreLista=" + nombreLista + ", calificacion=" + calificacion + ", fechaCreacion=" + fechaCreacion + '}';
    }

}
