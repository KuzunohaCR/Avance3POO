/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

import java.util.ArrayList;

public class NoAdmin extends Usuario {

    private int idNoAdmin;
    private int identificacion;
    private ArrayList<Cancion> noAdminCancion;
    private ListaReproduccion noAdminLista;
    private Album noAdminAlbum;

   
    public int getIdNoAdmin() {
        return idNoAdmin;
    }

    public void setIdNoAdmin(int idNoAdmin) {
        this.idNoAdmin = idNoAdmin;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public Album getNoAdminAlbum() {
        return noAdminAlbum;
    }

    public ArrayList<Cancion> getNoAdminCancion() {
        return noAdminCancion;
    }

    public ListaReproduccion getNoAdminLista() {
        return noAdminLista;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public void setNoAdminAlbum(Album noAdminAlbum) {
        this.noAdminAlbum = noAdminAlbum;
    }

    public void setNoAdminCancion(ArrayList<Cancion> noAdminCancion) {
        this.noAdminCancion = noAdminCancion;
    }

    public void setNoAdminLista(ListaReproduccion noAdminLista) {
        this.noAdminLista = noAdminLista;
    }

    /**
     * Constructor vacio
     */
    public NoAdmin() {
    }
    
    /**
     * Constructor con paremetros del usuario no administrador
     * @param identificacion identificacion del usuario no administrador
     * @param edad edad del usuario no administrador
     * @param correo correo del usuario no administrador
     * @param nombreUsuario nombre de usuario del usuario no administrador
     * @param contrasenna contrasenna del usuario no administrador
     * @param nombre nombre del usuario no administrador
     * @param apellido apellido del usuario no administrador
     * @param paises pais del usuario no administrador
     */
    public NoAdmin(int identificacion, int edad, String correo, String nombreUsuario, String contrasenna, String nombre, String apellido, Pais paises) {
        super(edad, correo, nombreUsuario, contrasenna, nombre, apellido, paises);
        this.identificacion = identificacion;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "NoAdmin{" + "idNoAdmin=" + idNoAdmin + "identificacion=" + identificacion + ", noAdminCancion=" + noAdminCancion + ", noAdminLista=" + noAdminLista + ", noAdminAlbum=" + noAdminAlbum + '}' + super.toString();
    }

}
