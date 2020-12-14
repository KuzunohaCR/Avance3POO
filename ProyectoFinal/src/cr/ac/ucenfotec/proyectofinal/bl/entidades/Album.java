/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

public class Album {
    //Falta la imagen

    private String nombreAlbum;
    private String fechaLanzamiento;
    //private Artista artistaAlbum;
    private Cancion albumCancion;
    private String imagen;

    public Cancion getAlbumCancion() {
        return albumCancion;
    }

    /*public Artista getArtistaAlbum() {
        return artistaAlbum;
    }*/

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setAlbumCancion(Cancion albumCancion) {
        this.albumCancion = albumCancion;
    }

    /*public void setArtistaAlbum(Artista artistaAlbum) {
        this.artistaAlbum = artistaAlbum;
    }*/

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Constructor del Album con parametros
     * @param nombreAlbum nombre del Album
     * @param fechaLanzamiento fecha de lanzamiento del Album
     * @param albumCancion cancion del Album
     * @param imagen imagen del Album
     */
    public Album(String nombreAlbum, String fechaLanzamiento/*, Artista artistaAlbum*/, Cancion albumCancion, String imagen) {
        this.nombreAlbum = nombreAlbum;
        this.fechaLanzamiento = fechaLanzamiento;
       // this.artistaAlbum = artistaAlbum;
        this.albumCancion = albumCancion;
        this.imagen = imagen;
    }
    
    /**
     * Constructor vacio
     */
    public Album() {
    }
    
    /**
     * to String
     * @return 
     */
    @Override
    public String toString() {
        return "Album{" + "nombreAlbum=" + nombreAlbum + ", fechaLanzamiento=" + fechaLanzamiento /*+ ", artistaAlbum=" + artistaAlbum */+ ", albumCancion=" + albumCancion + ", imagen=" + imagen + '}';
    }

}
