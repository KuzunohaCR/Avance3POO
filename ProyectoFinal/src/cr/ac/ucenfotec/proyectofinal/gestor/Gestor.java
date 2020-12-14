/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucenfotec.proyectofinal.gestor;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Administrador;
import java.util.ArrayList;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Album;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.AlbumArtista;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Artista;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Cancion;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Compositor;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Genero;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.ListaReproduccion;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.NoAdmin;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Pais;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Tienda;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.TiendaCancion;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Usuario;
import cr.ac.ucenfotec.proyectofinal.dao.AdministradorDAO;
import cr.ac.ucenfotec.proyectofinal.dao.AlbumArtistaDAO;
import cr.ac.ucenfotec.proyectofinal.dao.AlbumCancionDAO;
import cr.ac.ucenfotec.proyectofinal.dao.ArtistaDAO;
import cr.ac.ucenfotec.proyectofinal.dao.CancionDAO;
import cr.ac.ucenfotec.proyectofinal.dao.CancionUsuarioDAO;
import cr.ac.ucenfotec.proyectofinal.dao.CompositorDAO;
import cr.ac.ucenfotec.proyectofinal.dao.GeneroDAO;
import cr.ac.ucenfotec.proyectofinal.dao.ListaReproduccionDAO;
import cr.ac.ucenfotec.proyectofinal.dao.NoAdminDAO;
import cr.ac.ucenfotec.proyectofinal.dao.PaisDAO;
import cr.ac.ucenfotec.proyectofinal.dao.TiendaCancionDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor {

    Connection conection;
    Configuracion conf;

    private Administrador nuevoAdmin = new Administrador();
    private AdministradorDAO repoAdministradorDB;
    private PaisDAO repoPaisDAODB;
    private GeneroDAO repoGeneroDAODB;
    private NoAdminDAO repoNoAdminDAODB;
    private CompositorDAO repoCompoDAODB;
    private ArtistaDAO repoArtistaDAODB;
    private AlbumArtistaDAO repoAlbumArtistaDAODB;
    private AlbumCancionDAO repoAlbumCancionDAO;
    private CancionDAO repoCancionDAODB;
    private TiendaCancionDAO repoTiendaCancionDAODB;
    private CancionUsuarioDAO repoCancionUsuarioDAODB;
    private ListaReproduccionDAO repoListaReproduccionDAODB;

    private ArrayList<Pais> listaPaises = new ArrayList<>();
    private ArrayList<Genero> listaGenero = new ArrayList();
    private ArrayList<Compositor> listaCompositores = new ArrayList<>();
    private ArrayList<Artista> listaArtistas = new ArrayList<>();
    private ArrayList<Cancion> listaCanciones = new ArrayList<>();
    private ArrayList<AlbumArtista> listaAlbumArtistas = new ArrayList<>();
    private ArrayList<Album> listaAlbums = new ArrayList<>();
    private ArrayList<ListaReproduccion> listaListasReproduccion = new ArrayList<>();
    private ArrayList<Tienda> listaTienda = new ArrayList<>();
    private ArrayList<Usuario> nuevoUsuario = new ArrayList<>();

    public Gestor() {
        this.conf = new Configuracion();
        try {
            Optional<String> driver = conf.getDriver();
            Optional<String> cnxStr = conf.getDBUrl();
            Optional<String> usr = conf.getUserName();
            Optional<String> pwd = conf.getPassword();
            if (driver.isPresent() && cnxStr.isPresent() && usr.isPresent() && pwd.isPresent()) {

                Class.forName(driver.get()).newInstance();
                this.conection = DriverManager.getConnection(cnxStr.get(),
                        usr.get(), pwd.get());
                this.repoAdministradorDB = new AdministradorDAO(this.conection);
                this.repoNoAdminDAODB = new NoAdminDAO(this.conection);
                this.repoCompoDAODB = new CompositorDAO(this.conection);
                this.repoPaisDAODB = new PaisDAO(this.conection);
                this.repoGeneroDAODB = new GeneroDAO(this.conection);
                this.repoArtistaDAODB = new ArtistaDAO(this.conection);
                this.repoAlbumArtistaDAODB = new AlbumArtistaDAO(this.conection);
                this.repoAlbumCancionDAO = new AlbumCancionDAO(this.conection);
                this.repoCancionDAODB = new CancionDAO(this.conection);
                this.repoTiendaCancionDAODB = new TiendaCancionDAO(this.conection);
                this.repoCancionUsuarioDAODB = new CancionUsuarioDAO(this.conection);
                this.repoListaReproduccionDAODB = new ListaReproduccionDAO(this.conection);
            }
        } catch (Exception e) {
            System.out.println("Cant connect to db");
            System.out.println(e.getMessage());
        }
    }

    public Administrador findAdministrador() throws SQLException, InstantiationException, IllegalAccessException {
        if (repoAdministradorDB.findAll().isEmpty()) {
            return null;
        }
        return repoAdministradorDB.findAll().get(0);
    }

    public List<Pais> listaPais() throws InstantiationException, IllegalAccessException, SQLException {

        try {
            return repoPaisDAODB.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public NoAdmin LoginNoAdmin(String nombreUsuario, String password) throws InstantiationException, IllegalAccessException, SQLException {

        try {
            return repoNoAdminDAODB.findByUserAndPassword(nombreUsuario, password);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Administrador LoginAdmin(String nombreUsuario, String password) throws InstantiationException, IllegalAccessException, SQLException {

        try {
            return this.repoAdministradorDB.findByNameAndPassword(nombreUsuario, password);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<AlbumArtista> listaAlbumArtista() {

        try {
            return this.repoAlbumArtistaDAODB.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Cancion> listaCancion() {

        try {
            return repoCancionDAODB.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Compositor> listaCompositor() {

        try {
            return repoCompoDAODB.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Genero> listaGenero() {

        try {
            return repoGeneroDAODB.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Cancion> listaCancionesEnAlbum(int idAlbum) {

        try {
            return repoAlbumCancionDAO.findByAlbumArtistaID(idAlbum);

        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<TiendaCancion> listaCancionesEnTienda() {

        try {
            return this.repoTiendaCancionDAODB.findAll();

        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void agregarCompositor(String nombreCompositor, String apellidoCompositor, Pais nuevPais, int edadCompo, Genero nuevGenero) throws SQLException {

        Compositor compositores = new Compositor(edadCompo, nuevGenero, nombreCompositor, apellidoCompositor, nuevPais);
        this.repoCompoDAODB.save(compositores);
    }

    public Cancion agregarCancion(String nombreCancion, Genero nuevoGeneroCancion, Compositor cancionCompositor, String fechaLanzamiento) {
        Date fecha = null;

        try {
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaLanzamiento);
        } catch (ParseException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        Cancion canciones = new Cancion(nombreCancion, nuevoGeneroCancion, cancionCompositor, fecha);
        try {
            return this.repoCancionDAODB.save(canciones);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void agregarCancionAlaTienda(Cancion cancion, int precioCancion) {

        TiendaCancion tiendaCancion = new TiendaCancion(cancion, precioCancion);

        try {
            this.repoTiendaCancionDAODB.save(tiendaCancion);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void agregarAlbumArtista(String nombreAlbumArtista) {
        AlbumArtista albumArtistas = new AlbumArtista(nombreAlbumArtista);
        try {
            this.repoAlbumArtistaDAODB.save(albumArtistas);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarAlbumArtistaCancion(int idAlbum, int idCancion) {

        try {
            this.repoAlbumCancionDAO.save(idAlbum, idCancion);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarAlbum(String nombreAlbum, String fechaAlbum/*, Artista artistaEnAlbum*/, Cancion albumCancion, String imagenAlbum) {
        Album albunes = new Album(nombreAlbum, fechaAlbum/*, artistaEnAlbum*/, albumCancion, imagenAlbum);
        listaAlbums.add(albunes);
    }

    public ArrayList<Album> getAlbum() {
        return this.listaAlbums;
    }

    public ArrayList<AlbumArtista> getAlbumArtitas() {
        return this.listaAlbumArtistas;

    }

    public void agregarArtista(String nombreArtistico, String fechaNacimiento, String fechaDefuncion, Genero nuevGenero, int edad, String descripcion/*, AlbumArtista nuevoAlbumEnArtista*/, String nombreArtista, String apellidoArtista, Pais nuevPais) throws SQLException {
        Artista artistas = new Artista(nombreArtistico, fechaNacimiento, fechaDefuncion, nuevGenero, edad, descripcion/*, nuevoAlbumEnArtista*/, nombreArtista, apellidoArtista, nuevPais);
        this.repoArtistaDAODB.save(artistas);

    }

    public ArrayList<Artista> getArtista() {
        return this.listaArtistas;
    }

    public ArrayList<ListaReproduccion> agregarListaCancion(NoAdmin user, ArrayList listaCancion, String nombreLista, int listaCalificacion, Date listaFecha) {
        ListaReproduccion litasReproducciones = new ListaReproduccion(user, listaCancion, nombreLista, listaCalificacion, listaFecha);
        try {
            this.repoListaReproduccionDAODB.save(litasReproducciones);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaListasReproduccion;
    }

    public Administrador getAdmin() {
        return this.nuevoAdmin;
    }

    public void crearAdmin(String adminNombre, String adminApellido, Pais nuevPais, int adminEdad, String adminCorreo, String adminNick, String adminContrasenna) throws SQLException {
        Administrador unAdmin = new Administrador(adminEdad, adminCorreo, adminNick, adminContrasenna, adminNombre, adminApellido, nuevPais);
        repoAdministradorDB.save(unAdmin);
    }

    public NoAdmin crearUsuario(String nombre, String apellido, Pais nuevPais, int edad, int identificacion, String correoUsuario, String nombreUsuario, String contrasennaUsuario) {
        try {
            NoAdmin nuevoUsuario = new NoAdmin(edad, identificacion, correoUsuario, nombreUsuario, contrasennaUsuario, nombre, apellido, nuevPais);
            return this.repoNoAdminDAODB.save(nuevoUsuario);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void crearGenero(String nombreGenero, String descripcion) {
        Genero nuevoGenero = new Genero(nombreGenero, descripcion);
        try {
            this.repoGeneroDAODB.save(nuevoGenero);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agrecarCancionAlUsuario(Cancion tempUsuario, NoAdmin usuario) {
        try {
            this.repoCancionUsuarioDAODB.save(tempUsuario, usuario);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Cancion> listaCancionesDelUsuario(int idNoAdmin) {

        try {
            return this.repoCancionUsuarioDAODB.findCancionesDelUsuarioByID(idNoAdmin);

        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
