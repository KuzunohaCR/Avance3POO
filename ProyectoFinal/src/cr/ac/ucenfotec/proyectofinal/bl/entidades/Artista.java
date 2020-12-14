/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

public class Artista extends Persona {

    private int idArtista;
    private String nombreArtistico;
    private String fechaNacimiento;
    private String fechaDefuncion;
    private Genero artistaGenero;
    private int edad;
    private String descripcion;
    //private AlbumArtista artista;

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    /*public AlbumArtista getArtista() {
        return artista;
    }*/
    public Genero getArtistaGenero() {
        return artistaGenero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getEdad() {
        return edad;
    }

    public String getFechaDefuncion() {
        return fechaDefuncion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    /*public void setArtista(AlbumArtista artista) {
        this.artista = artista;
    }*/
    public void setArtistaGenero(Genero artistaGenero) {
        this.artistaGenero = artistaGenero;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setFechaDefuncion(String fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public Artista() {
    }
    
    /**
     * Constructor con parametros del Artista
     * @param nombreArtistico nombre artistico del artista
     * @param fechaNacimiento fecha de nacimiento del artista
     * @param fechaDefuncion fecha defuncion del artista
     * @param artistaGenero genero del artista
     * @param edad edad del artista
     * @param descripcion descripcion del artista
     * @param nombre nombre del artista
     * @param apellido apellido del artista
     * @param paises pais del artista
     */
    public Artista(String nombreArtistico, String fechaNacimiento, String fechaDefuncion, Genero artistaGenero, int edad, String descripcion/*, AlbumArtista artista*/, String nombre, String apellido, Pais paises) {
        super(nombre, apellido, paises);
        this.nombreArtistico = nombreArtistico;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaDefuncion = fechaDefuncion;
        this.artistaGenero = artistaGenero;
        this.edad = edad;
        this.descripcion = descripcion;
        //this.artista = artista;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Artista{" + "idArtista=" + idArtista + "nombreArtistico=" + nombreArtistico + ", fechaNacimiento=" + fechaNacimiento + ", fechaDefuncion=" + fechaDefuncion + ", artistaGenero=" + artistaGenero + ", edad=" + edad + ", descripcion=" + descripcion /*+ ", artista=" + artista*/ + '}' + super.toString();
    }

}
