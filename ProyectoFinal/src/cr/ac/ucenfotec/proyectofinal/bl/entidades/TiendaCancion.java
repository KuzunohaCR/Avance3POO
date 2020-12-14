/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

public class TiendaCancion {
    
    private int idTiendaCancion;
    private Cancion canciones;
    private int precio;

    public int getIdTiendaCancion() {
        return idTiendaCancion;
    }

    public void setIdTiendaCancion(int idTiendaCancion) {
        this.idTiendaCancion = idTiendaCancion;
    }

    public Cancion getCanciones() {
        return canciones;
    }

    public void setCanciones(Cancion canciones) {
        this.canciones = canciones;
    }

    public int getPrecio() {
        return precio;
    }


    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    /**
     * Constructor vacio
     */
    public TiendaCancion() {
    }
    
    /**
     * Constructor con parametros de la Cancion en la tienda
     * @param canciones cancion en la tienda
     * @param precio  precio de la cancion en la tienda
     */
    public TiendaCancion(Cancion canciones, int precio) {
        this.canciones = canciones;
        this.precio = precio;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "TiendaCancion{" + "idTiendaCancion=" + idTiendaCancion + "canciones=" + canciones + ", precio=" + precio + '}';
    }

}
