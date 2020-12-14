/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.dao;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Administrador;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {

    Connection cnx;

    public AdministradorDAO(Connection cnx) {
        this.cnx = cnx;
    }

    public void save(Administrador administrador) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into administrador (nombre,apellido,idpais,edad,correo,nombreusuario,contrasenna)");
        buildSentence.append(" values ('");
        buildSentence.append(administrador.getNombre());
        buildSentence.append("','");
        buildSentence.append(administrador.getApellido());
        buildSentence.append("',");
        buildSentence.append(administrador.getPaises().getIdPais());
        buildSentence.append(",");
        buildSentence.append(administrador.getEdad());
        buildSentence.append(",'");
        buildSentence.append(administrador.getCorreo());
        buildSentence.append("','");
        buildSentence.append(administrador.getNombreUsuario());
        buildSentence.append("','");
        buildSentence.append(administrador.getContrasenna());
        buildSentence.append("')");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<Administrador> findAll() throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Administrador> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from administrador");
        while (result.next()) {
            Administrador administrador = new Administrador();
            PaisDAO pais = new PaisDAO(cnx);

            administrador.setNombre(result.getString("nombre"));
            administrador.setApellido(result.getString("apellido"));

            administrador.setPaises(pais.findByPaisID(result.getInt("idpais")));
            administrador.setEdad(result.getInt("edad"));
            administrador.setCorreo(result.getString("correo"));
            administrador.setNombreUsuario(result.getString("nombreUsuario"));
            administrador.setContrasenna(result.getString("contrasenna"));

            listOfResults.add(administrador);
        }
        return listOfResults;
    }

    public Administrador findByNameAndPassword(String nombreUsuario, String password) throws SQLException, InstantiationException, IllegalAccessException {

        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from administrador");
        buildSentence.append(" where (nombreusuario= '");
        buildSentence.append(nombreUsuario);
        buildSentence.append("' and contrasenna='");
        buildSentence.append(password);
        buildSentence.append("')");
        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            Administrador Admin = new Administrador();
            PaisDAO pais = new PaisDAO(cnx);

            Admin.setNombre(result.getString("nombre"));
            Admin.setApellido(result.getString("apellido"));

            Admin.setPaises(pais.findByPaisID(result.getInt("idpais")));
            Admin.setEdad(result.getInt("edad"));
            Admin.setCorreo(result.getString("correo"));
            Admin.setNombreUsuario(result.getString("nombreUsuario"));
            Admin.setContrasenna(result.getString("contrasenna"));
            return Admin;
        }
        return null;
    }
}
