/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.AlbumArtista;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Cancion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlbumCancionDAO {

    Connection cnx;

    public AlbumCancionDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public void save(int idAlbum, int idCancion) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into album_cancion(idcancion,idalbumartista)");
        buildSentence.append(" values (");
        buildSentence.append(idCancion);
        buildSentence.append(",");
        buildSentence.append(idAlbum);
        buildSentence.append(")");

        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public ArrayList<Cancion> findByAlbumArtistaID(int idAlbumArtista) throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Cancion> listaCancion = new ArrayList();
        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from album_cancion");
        buildSentence.append(" where idalbumartista = ");
        buildSentence.append(idAlbumArtista);
        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            //  AlbumArtista albumArtista = new AlbumArtista();

            CancionDAO dao = new CancionDAO(cnx);
            listaCancion.add((dao.findByCancionID(result.getInt("idcancion"))));

        }
        return listaCancion;
    }
}
