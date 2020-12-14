/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.bl.entidades;

public class Compositor extends Persona {
    
    private int idCompositor;
    private int edad;
    private Genero compGenero;

    public int getIdCompositor() {
        return idCompositor;
    }

    public void setIdCompositor(int idCompositor) {
        this.idCompositor = idCompositor;
    }

    public Genero getCompGenero() {
        return compGenero;
    }

    public int getEdad() {
        return edad;
    }

    public void setCompGenero(Genero compGenero) {
        this.compGenero = compGenero;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    /**
     * Constructor con parametros del compositor
     * @param edad edad del compositor
     * @param compGenero genero del compositor
     * @param nombre nombre del compositor
     * @param apellido apellido del compositor
     * @param paises pais del compositor
     */
    public Compositor(int edad, Genero compGenero, String nombre, String apellido, Pais paises) {
        super(nombre, apellido, paises);
        this.edad = edad;
        this.compGenero = compGenero;
    }
    
    /**
     * Constructor vacio
     */
    public Compositor() {
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Compositor{" + "edad=" + edad + ", compGenero=" + compGenero + '}' + super.toString();
    }

}
