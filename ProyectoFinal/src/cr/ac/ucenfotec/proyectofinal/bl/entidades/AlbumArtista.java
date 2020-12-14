/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

import java.util.List;

public class AlbumArtista {
    
    private int idAlbumArtista;
    private String nombreAlbumArtista;
    private List<Cancion> cancionAlbumArtista;

    public List<Cancion> getCancionAlbumArtista() {
        return cancionAlbumArtista;
    }

    public void setCancionAlbumArtista(List<Cancion> cancionAlbumArtista) {
        this.cancionAlbumArtista = cancionAlbumArtista;
    }

    public int getIdAlbumArtista() {
        return idAlbumArtista;
    }

    public void setIdAlbumArtista(int idAlbumArtista) {
        this.idAlbumArtista = idAlbumArtista;
    }

    public String getNombreAlbumArtista() {
        return nombreAlbumArtista;
    }

    public void setNombreAlbumArtista(String nombreAlbumArtista) {
        this.nombreAlbumArtista = nombreAlbumArtista;
    }
    
    /**
     * Constructor Album del artista
     * @param nombreAlbumArtista nombre del album del artista
     */
    public AlbumArtista(String nombreAlbumArtista/*, Cancion cancionAlbumArtista*/) {
        this.nombreAlbumArtista = nombreAlbumArtista;
      //  this.cancionAlbumArtista = cancionAlbumArtista;
    }
    
    /**
     * Constructor vacio
     */
    public AlbumArtista() {
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "AlbumArtista{" + "idAlbumArtista=" + idAlbumArtista + "nombreAlbumArtista=" + nombreAlbumArtista + /*", cancionAlbumArtista=" + cancionAlbumArtista +*/ '}';
    }

}
