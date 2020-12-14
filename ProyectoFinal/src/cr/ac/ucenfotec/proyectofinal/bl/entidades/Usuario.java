/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

public class Usuario extends Persona {

    protected int edad;
    /*private image avatar;
    Recordar agregar avatar al set y get y al constructor/toString
     */
    protected String correo;
    protected String nombreUsuario;
    protected String contrasenna;

    public String getContrasenna() {
        return contrasenna;
    }

    public String getCorreo() {
        return correo;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    /**
     * Constructor vacio
     */
    public Usuario() {
    }
    
    /**
     * Constructor con parametros del padre Usuario
     * @param edad edad del usuario
     * @param correo correo del usuario 
     * @param nombreUsuario Nick del usuario
     * @param contrasenna contrasenna del usuario
     * @param nombre nombre del usuario
     * @param apellido apellido del usuario
     * @param paises pais del usuario
     */
    public Usuario(int edad, String correo, String nombreUsuario, String contrasenna, String nombre, String apellido, Pais paises) {
        super(nombre, apellido, paises);
        this.edad = edad;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.contrasenna = contrasenna;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Usuario{" + "edad=" + edad + ", correo=" + correo + ", nombreUsuario=" + nombreUsuario + ", contrasenna=" + contrasenna + '}' + super.toString();
    }


}
