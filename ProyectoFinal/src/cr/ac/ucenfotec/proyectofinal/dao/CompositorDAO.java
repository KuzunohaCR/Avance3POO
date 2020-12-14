/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Compositor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompositorDAO {

    Connection cnx;

    public CompositorDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public void save(Compositor compositor) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into compositor (nombre,apellido,idpais,edad,idgenero)");
        buildSentence.append(" values ('");
        buildSentence.append(compositor.getNombre());
        buildSentence.append("','");
        buildSentence.append(compositor.getApellido());
        buildSentence.append("',");
        buildSentence.append(compositor.getPaises().getIdPais());
        buildSentence.append(",");
        buildSentence.append(compositor.getEdad());
        buildSentence.append(",");
        buildSentence.append(compositor.getCompGenero().getIdGenero());
        buildSentence.append(")");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<Compositor> findAll() throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Compositor> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from compositor");
        while (result.next()) {
            Compositor compositor = new Compositor();
            PaisDAO pais = new PaisDAO(cnx);
            GeneroDAO genero = new GeneroDAO(cnx);
            compositor.setIdCompositor(result.getInt("idcompositor"));
            compositor.setNombre(result.getString("nombre"));
            compositor.setApellido(result.getString("apellido"));
            compositor.setPaises(pais.findByPaisID(result.getInt("idpais")));
            compositor.setEdad(result.getInt("edad"));
            compositor.setCompGenero(genero.findByGeneroID(result.getInt("idgenero")));
            listOfResults.add(compositor);
        }
        return listOfResults;
    }

    public Compositor findByCompositorID(int idCompositor) throws SQLException, InstantiationException, IllegalAccessException {

        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from compositor");
        buildSentence.append(" where  idcompositor= ");
        buildSentence.append(idCompositor);

        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            Compositor compositor = new Compositor();
            PaisDAO pais = new PaisDAO(cnx);
            GeneroDAO genero = new GeneroDAO(cnx);

            compositor.setNombre(result.getString("nombre"));
            compositor.setApellido(result.getString("apellido"));
            compositor.setPaises(pais.findByPaisID(result.getInt("idpais")));
            compositor.setEdad(result.getInt("edad"));
            compositor.setCompGenero(genero.findByGeneroID(result.getInt("idgenero")));
            return compositor;
        }
        return null;
    }
}
