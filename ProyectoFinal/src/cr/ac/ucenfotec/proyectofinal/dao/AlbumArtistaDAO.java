/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.AlbumArtista;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlbumArtistaDAO {

    Connection cnx;

    public AlbumArtistaDAO(Connection cnx) {
        this.cnx = cnx;

    }

    public void save(AlbumArtista albumArtista) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into albumartista(nombrealbumartista)");
        buildSentence.append(" values ('");
        buildSentence.append(albumArtista.getNombreAlbumArtista());
        buildSentence.append("')");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<AlbumArtista> findAll() throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<AlbumArtista> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from albumartista");
        while (result.next()) {
            AlbumArtista albumArtista = new AlbumArtista();
            PaisDAO pais = new PaisDAO(cnx);

            albumArtista.setIdAlbumArtista(result.getInt("idalbumartista"));
            albumArtista.setNombreAlbumArtista(result.getString("nombrealbumartista"));
            listOfResults.add(albumArtista);
        }
        return listOfResults;
    }

    public AlbumArtista findByAlbumArtistaID(int idAlbumArtista) throws SQLException {

        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from albumartista");
        buildSentence.append(" where ('idalbumartista=");
        buildSentence.append(idAlbumArtista);
        buildSentence.append("')");

        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            AlbumArtista albumArtista = new AlbumArtista();
            albumArtista.setNombreAlbumArtista(result.getString("nombrealbumartista"));
            return albumArtista;
        }
        return null;
    }

}
