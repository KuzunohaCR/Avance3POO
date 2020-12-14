/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Cancion;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.NoAdmin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CancionUsuarioDAO {

    Connection cnx;

    public CancionUsuarioDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public Cancion save(Cancion cancion, NoAdmin nuevoUsuario) throws SQLException {

        StringBuilder buildSentence = new StringBuilder("insert into cancionusuario(idusuario,idcancion)");

        buildSentence.append(" values (");
        buildSentence.append(nuevoUsuario.getIdNoAdmin());
        buildSentence.append(",");
        buildSentence.append(cancion.getIdCancion());
        buildSentence.append(")");

        System.out.println(buildSentence.toString());

        PreparedStatement stmt = cnx.prepareStatement(buildSentence.toString(), Statement.RETURN_GENERATED_KEYS);

        int affected = stmt.executeUpdate();

        if (affected == 1) {
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            cancion.setIdCancion(keys.getInt(1));
        }
        return cancion;

    }

    public ArrayList<Cancion> findCancionesDelUsuarioByID(int idNoAdmin) throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Cancion> listaCancion = new ArrayList<>();
        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from cancionusuario");
        buildSentence.append(" where idcancionusuario = ");
        buildSentence.append(idNoAdmin);
        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {

            CancionDAO dao = new CancionDAO(cnx);

            listaCancion.add(dao.findByCancionID(result.getInt("idcancion")));
        }
        return listaCancion;
    }
}
