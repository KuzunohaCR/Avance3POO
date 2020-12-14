/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

public class Tienda {

    private Cancion canciones;

    public Cancion getCanciones() {
        return canciones;
    }

    public void setCanciones(Cancion canciones) {
        this.canciones = canciones;
    }
    
    /**
     * Constructor vacio
     */
    public Tienda() {
    }

    /**
     * Constructor con parametros de la tienda
     * @param canciones canciones en la tienda
     */
    public Tienda(Cancion canciones) {
        this.canciones = canciones;
    }
    
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Tienda{" + "Canciones=" + canciones + '}';
    }

  

}
