/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Artista;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArtistaDAO {

    Connection cnx;

    public ArtistaDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public void save(Artista artista) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into artista(nombre,apellido,idpais,edad,nombreartistico,fechanacimiento,fechadefuncion,idGenero,descripcion)");
        buildSentence.append(" values ('");
        buildSentence.append(artista.getNombre());
        buildSentence.append("','");
        buildSentence.append(artista.getApellido());
        buildSentence.append("',");
        buildSentence.append(artista.getPaises().getIdPais());
        buildSentence.append(",");
        buildSentence.append(artista.getEdad());
        buildSentence.append(",'");
        buildSentence.append(artista.getNombreArtistico());
        buildSentence.append("','");
        buildSentence.append(artista.getFechaNacimiento());
        buildSentence.append("','");
        buildSentence.append(artista.getFechaDefuncion());
        buildSentence.append("',");
        buildSentence.append(artista.getArtistaGenero().getIdGenero());
        buildSentence.append(",'");
        buildSentence.append(artista.getDescripcion());
        buildSentence.append("')");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<Artista> findAll() throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Artista> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from artista");
        while (result.next()) {
            Artista artista = new Artista();
            PaisDAO pais = new PaisDAO(cnx);

            artista.setNombre(result.getString("nombre"));
            artista.setApellido(result.getString("apellido"));

            GeneroDAO genero = new GeneroDAO(cnx);
            artista.setPaises(pais.findByPaisID(result.getInt("idpais")));
            artista.setEdad(result.getInt("edad"));
            artista.setNombreArtistico(result.getString("nombreartistico"));
            artista.setFechaNacimiento(result.getString("fechanacimiento"));
            artista.setFechaDefuncion(result.getString("nombreUsuario"));
            artista.setArtistaGenero(genero.findByGeneroID(result.getInt("idgenero")));
            artista.setDescripcion(result.getString("descripcion"));
            listOfResults.add(artista);
        }
        return listOfResults;
    }

    public Artista findByArtistaID(int idArtista) throws SQLException, InstantiationException, IllegalAccessException {

        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from artista");
        buildSentence.append(" where ('idartista=");
        buildSentence.append(idArtista);
        buildSentence.append("')");

        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            Artista artista = new Artista();
            PaisDAO pais = new PaisDAO(cnx);
            GeneroDAO genero = new GeneroDAO(cnx);

            artista.setNombre(result.getString("nombre"));
            artista.setApellido(result.getString("apellido"));
            artista.setPaises(pais.findByPaisID(result.getInt("idpais")));
            artista.setEdad(result.getInt("edad"));
            artista.setNombreArtistico(result.getString("nombreartistico"));
            artista.setFechaNacimiento(result.getString("fechanacimiento"));
            artista.setFechaDefuncion(result.getString("nombreUsuario"));
            artista.setArtistaGenero(genero.findByGeneroID(result.getInt("idgenero")));
            artista.setDescripcion(result.getString("descripcion"));
            return artista;
        }
        return null;
    }
}
