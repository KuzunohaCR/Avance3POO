/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.iu;

import java.io.PrintStream;
import java.util.Scanner;

public class IU {

    private PrintStream output = new PrintStream(System.out);
    private Scanner input = new Scanner(System.in).useDelimiter("\n");

    public void mostrarMenuLogin() {
        output.println("Bienvenido a El Baul de los Recuerdos");
        output.println("1. Administrador");
        output.println("2. Login Usuario");
        output.println("3. Registrar Usuario");
        output.println("4. Salir");
    }

    public void mostrarMenuAdmin() {
        output.println("Esta en menu Administrador");
        output.println("1. Crear artista");
        output.println("2. Crear compositor");
        output.println("3. Crear genero");
        output.println("4. Crear album");
        output.println("5. Crear cancion");
        output.println("6. agregar cancion a album");
        output.println("7. Listar cancion en album");
        output.println("8. Listar canciones en la tienda");
        output.println("9. Salir");
    }

    public void mostrarMenuNoAdmin() {
        output.println("Esta en menu Usuario");
        output.println("1. Agregar cancion");
        output.println("2. Comprar Cancion");
        output.println("3. Crear lista de reproduccion");
        output.println("4. Salir");
    }
    
     public void mostrarMenuLoginNoAdminNombreUsuario() {
        output.println("Ingrese su nombre de usuario ");
 
    }
     
     public void mostrarMenuLoginNoAdminPasswordUsuario() {
        output.println("Ingrese su password ");
 
    }

    public int leerNumero() {

        return input.nextInt();
    }

    public String leerTexto() {
        return input.next();
    }

    public void imprimirMensaje(String mensaje) {
        output.println(mensaje);
    }
}
