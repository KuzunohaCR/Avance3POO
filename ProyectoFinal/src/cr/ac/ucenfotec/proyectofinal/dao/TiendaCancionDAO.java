/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Cancion;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.TiendaCancion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TiendaCancionDAO {

    Connection cnx;

    public TiendaCancionDAO(Connection cnx) {
        this.cnx = cnx;

    }

    public void save(TiendaCancion tiendaCancion) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into tiendacancion (idcancion,precio)");
        buildSentence.append(" values (");
        buildSentence.append(tiendaCancion.getCanciones().getIdCancion());;
        buildSentence.append(",");
        buildSentence.append(tiendaCancion.getPrecio());
        buildSentence.append(")");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public ArrayList<TiendaCancion> findCancionEnTiendaByID(int idCancion) throws SQLException, InstantiationException, IllegalAccessException {
        TiendaCancion tiendaCancion = new TiendaCancion();
        ArrayList<TiendaCancion> listaCancion = new ArrayList<>();
        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from tiendacancion");
        buildSentence.append(" where idcancion = ");
        buildSentence.append(idCancion);
        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {

            CancionDAO dao = new CancionDAO(cnx);
            tiendaCancion.setCanciones((dao.findByCancionID(result.getInt("idcancion"))));
            tiendaCancion.setPrecio(result.getInt("precio"));
            listaCancion.add(tiendaCancion);
        }
        return listaCancion;
    }

    public List<TiendaCancion> findAll() throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<TiendaCancion> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from tiendacancion");
        while (result.next()) {
            TiendaCancion tiendaCancion = new TiendaCancion();
            CancionDAO cancion = new CancionDAO(cnx);
            tiendaCancion.setCanciones(cancion.findByCancionID(result.getInt("idcancion")));
            tiendaCancion.setPrecio(result.getInt("precio"));
            listOfResults.add(tiendaCancion);
        }
        return listOfResults;
    }

}
