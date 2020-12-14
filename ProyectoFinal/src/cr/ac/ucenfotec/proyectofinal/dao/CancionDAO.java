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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CancionDAO {

    Connection cnx;

    public CancionDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public Cancion save(Cancion cancion) throws SQLException {
       
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder buildSentence = new StringBuilder("insert into cancion(nombrecancion,generos,compositor,fechalanzamiento)");
      
        buildSentence.append(" values ('");
        buildSentence.append(cancion.getNombreCancion());
        buildSentence.append("',");
        buildSentence.append(cancion.getGeneros().getIdGenero());
        buildSentence.append(",");
        buildSentence.append(cancion.getCancCompositor().getIdCompositor());
        buildSentence.append(",'");
        buildSentence.append(dateFormat.format(cancion.getFechaLanzamiento()));
        buildSentence.append("')"  );
        System.out.println(buildSentence.toString());
        
        
       PreparedStatement stmt  = cnx.prepareStatement(buildSentence.toString(), Statement.RETURN_GENERATED_KEYS);
      //  stmt.execute(buildSentence.toString());
      
      int affected = stmt.executeUpdate();

        if (affected == 1) {
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            cancion.setIdCancion(keys.getInt(1));
        }
        return cancion;
        
     
        
    }

    public List<Cancion> findAll() throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Cancion> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from cancion");
        while (result.next()) {
            Cancion cancion = new Cancion();
            GeneroDAO genero = new GeneroDAO(cnx);
            CompositorDAO compositor = new CompositorDAO(cnx);
            cancion.setIdCancion(result.getInt("idcancion"));
            cancion.setNombreCancion(result.getString("nombrecancion"));
            cancion.setGeneros(genero.findByGeneroID(result.getInt("generos")));
            cancion.setCancCompositor(compositor.findByCompositorID(result.getInt("compositor")));
            cancion.setFechaLanzamiento(result.getDate("fechalanzamiento"));
            listOfResults.add(cancion);
        }
        return listOfResults;
    }

    public Cancion findByCancionID(int idCancion) throws SQLException, InstantiationException, IllegalAccessException {

        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from cancion");
        buildSentence.append(" where idcancion= ");
        buildSentence.append(idCancion);
      

        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            Cancion cancion = new Cancion();
            GeneroDAO genero = new GeneroDAO(cnx);
            CompositorDAO compositor = new CompositorDAO(cnx);
            cancion.setIdCancion(result.getInt("idcancion"));
            cancion.setNombreCancion(result.getString("nombrecancion"));
            cancion.setGeneros(genero.findByGeneroID(result.getInt("generos")));
            cancion.setCancCompositor(compositor.findByCompositorID(result.getInt("compositor")));
            cancion.setFechaLanzamiento(result.getDate("fechalanzamiento"));
            return cancion;
        }
        return null;
    }
}
