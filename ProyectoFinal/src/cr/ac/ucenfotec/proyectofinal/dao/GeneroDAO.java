/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Genero;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneroDAO {

    Connection cnx;

    public GeneroDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public void save(Genero genero) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into genero (nombregenero,descripcion)");
        buildSentence.append(" values ('");
        buildSentence.append(genero.getNombreGenero());
        buildSentence.append("','");
        buildSentence.append(genero.getDescripcion());
        buildSentence.append("')");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<Genero> findAll() throws SQLException {
        ArrayList<Genero> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from genero");
        while (result.next()) {
            Genero genero = new Genero();
            genero.setNombreGenero(result.getString("nombregenero"));
            genero.setDescripcion(result.getString("descripcion"));
            genero.setIdGenero(result.getInt("idgenero"));
            listOfResults.add(genero);
        }
        return listOfResults;
    }

    public Genero findByGeneroID(int idGenero) throws SQLException {

        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from genero");
        buildSentence.append(" where idgenero = ");
        buildSentence.append(idGenero);

        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            Genero genero = new Genero();
            genero.setNombreGenero(result.getString("nombregenero"));
            genero.setDescripcion(result.getString("descripcion"));
            genero.setIdGenero(result.getInt("idgenero"));

            return genero;
        }
        return null;
    }
}
