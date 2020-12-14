/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Pais;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaisDAO {

    Connection cnx;

    public PaisDAO(Connection cnx) {
        this.cnx = cnx;

    }

    public List<Pais> findAll() throws SQLException {
        ArrayList<Pais> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from pais");
        while (result.next()) {
            Pais pais = new Pais();
            pais.setNombrePais(result.getString("nombrepais"));
            pais.setIdPais(result.getInt("idpais"));

            listOfResults.add(pais);
        }
        return listOfResults;
    }

    public Pais findByPaisID(int idPais) throws SQLException {

        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from pais");
        buildSentence.append(" where idpais= ");
        buildSentence.append(idPais);

        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            Pais pais = new Pais();
            pais.setNombrePais(result.getString("nombrePais"));
            return pais;
        }
        return null;
    }

}
