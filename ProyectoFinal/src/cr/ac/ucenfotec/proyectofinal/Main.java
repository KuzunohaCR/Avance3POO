/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal;

import cr.ac.ucenfotec.proyectofinal.controlador.Controlador;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Controlador ejecutarPrograma = new Controlador();
        ejecutarPrograma.ejecutarMenuLogin();

    }
}
