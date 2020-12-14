/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Cancion;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.ListaReproduccion;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Pais;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaReproduccionDAO {

    Connection cnx;

    public ListaReproduccionDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public ListaReproduccion save(ListaReproduccion listaReproduccion) throws SQLException {
        Statement stmt = cnx.createStatement();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder buildSentence = new StringBuilder("insert into listareproduccion(nombrelista,calificacion,fechacreacion,idnoadmin)");

        buildSentence.append(" values ('");
        buildSentence.append(listaReproduccion.getNombreLista());
        buildSentence.append("',");
        buildSentence.append(listaReproduccion.getCalificacion());
        buildSentence.append(",'");
        buildSentence.append(dateFormat.format(listaReproduccion.getFechaCreacion()));
        buildSentence.append("',");
        buildSentence.append(listaReproduccion.getNoAdmin().getIdNoAdmin());
        buildSentence.append(")");
        System.out.println(buildSentence.toString());

        PreparedStatement stmtmp = cnx.prepareStatement(buildSentence.toString(), Statement.RETURN_GENERATED_KEYS);

        int affected = stmtmp.executeUpdate();

        if (affected == 1) {
            ResultSet keys = stmtmp.getGeneratedKeys();
            keys.next();
            listaReproduccion.setIdListaReproduccion(keys.getInt(1));
        }

        saveCanciones(listaReproduccion.getIdListaReproduccion(), listaReproduccion.getCanciones());
        return null;

    }

    public ListaReproduccion saveCanciones(int idListaReproduccion, List<Cancion> listaCanciones) throws SQLException {
        //  Statement stmt = cnx.createStatement();
        Statement stmt = cnx.createStatement();

        for (Cancion cancion : listaCanciones) {

            StringBuilder buildSentence = new StringBuilder("insert into cancionesporlistareproduccion(idlistareproduccion,idcancion)");

            buildSentence.append(" values (");
            buildSentence.append(idListaReproduccion);
            buildSentence.append(",");
            buildSentence.append(cancion.getIdCancion());
            buildSentence.append(")");
            System.out.println(buildSentence.toString());
            stmt.execute(buildSentence.toString());

        }
        return null;

    }
}
