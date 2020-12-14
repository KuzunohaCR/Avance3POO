/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

import java.util.Date;

public class Cancion {
    
    private int idCancion;
    private String nombreCancion;
    private Genero generos;
    private Compositor cancCompositor;
    private Date fechaLanzamiento;

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public Compositor getCancCompositor() {
        return cancCompositor;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public Genero getGeneros() {
        return generos;
    }

    public void setCancCompositor(Compositor cancCompositor) {
        this.cancCompositor = cancCompositor;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public void setGeneros(Genero generos) {
        this.generos = generos;
    }
    
    /**
     * Constructor con parametros de Cancion
     * @param nombreCancion nombre de la cancion
     * @param generos genero de la cancion
     * @param cancCompositor compositor de la cancion
     * @param fechaLanzamiento fecha de lanzamiento de la cancion
     */
    public Cancion(String nombreCancion,Genero generos, Compositor cancCompositor, Date fechaLanzamiento) {
        this.nombreCancion = nombreCancion;
        this.generos = generos;
        this.cancCompositor = cancCompositor;
        this.fechaLanzamiento = fechaLanzamiento;
    }
    
    /**
     * Constructor vacio
     */
    public Cancion() {
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Cancion{" + "idCancion=" + idCancion + "nombreCancion=" + nombreCancion + "generos=" + generos + ", cancCompositor=" + cancCompositor + ", fechaLanzamiento=" + fechaLanzamiento + '}';
    }

}
