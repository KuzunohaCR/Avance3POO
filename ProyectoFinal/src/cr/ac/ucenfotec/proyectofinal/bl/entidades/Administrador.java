/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

public class Administrador extends Usuario {

    public Administrador() {
    }
    
    /**
     * Constructor con parametros del Administrador
     * @param edad edad del administrador
     * @param correo correo del administrador
     * @param nombreUsuario nombre de usuario del administrador
     * @param contrasenna contrasenna del administrador
     * @param nombre nombre del administrador
     * @param apellido apellido del administrador
     * @param paises pais del administrador
     */
    public Administrador( int edad, String correo, String nombreUsuario, String contrasenna, String nombre, String apellido, Pais paises) {
        super(edad, correo, nombreUsuario, contrasenna, nombre, apellido, paises);

    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Administrador{" + super.toString();
    }

}
