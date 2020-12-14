/*
mirar nota crear cancion
mirar nota crear compositor
mirar la nota en crear artista
 */
package cr.ac.ucenfotec.proyectofinal.controlador;

import cr.ac.ucenfotec.proyectofinal.bl.entidades.Administrador;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Album;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.AlbumArtista;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Artista;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Cancion;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Compositor;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Genero;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.ListaReproduccion;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.NoAdmin;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.Pais;
import cr.ac.ucenfotec.proyectofinal.bl.entidades.TiendaCancion;
import cr.ac.ucenfotec.proyectofinal.dao.CancionDAO;
import cr.ac.ucenfotec.proyectofinal.gestor.Gestor;
import cr.ac.ucenfotec.proyectofinal.iu.IU;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controlador {

    private IU interfaz = new IU();
    private Gestor nuevoGestor = new Gestor();
    private NoAdmin usarioLogeado = new NoAdmin();
    private Administrador usuarioAdmin = new Administrador();

    //Pantalla de Login
    public void ejecutarMenuLogin() throws SQLException {

        try {
            int opcionLogin = 0;
            do {
                if (nuevoGestor.findAdministrador() == null) {
                    this.crearAdministrador();
                }

            } while (nuevoGestor.findAdministrador().getNombre() == null);

            do {
                interfaz.mostrarMenuLogin();
                opcionLogin = interfaz.leerNumero();
                procesarLogin(opcionLogin);
            } while (opcionLogin != 4);
        } catch (InstantiationException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Opciones del menu Administrador
    public void ejecutarMenuAdmin() {
        int contador = 0;
        try {
            int opcion = 0;
            String nombreUsuario, passwordUsuario;
            do {

                if (contador != 0) {
                    interfaz.imprimirMensaje("Nombre de usuario o password incorrectos");
                }
                interfaz.mostrarMenuLoginNoAdminNombreUsuario();
                nombreUsuario = interfaz.leerTexto();
                interfaz.mostrarMenuLoginNoAdminPasswordUsuario();
                passwordUsuario = interfaz.leerTexto();
                contador++;
            } while (this.nuevoGestor.LoginAdmin(nombreUsuario, passwordUsuario) == null);
            this.usuarioAdmin = this.nuevoGestor.LoginAdmin(nombreUsuario, passwordUsuario);
            do {
                interfaz.mostrarMenuAdmin();
                opcion = interfaz.leerNumero();
                this.procesarOpcion(opcion);
            } while (opcion != 9);
        } catch (InstantiationException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Opciones del menu Usuario
    public Pais seleccionarPais() throws InstantiationException, IllegalAccessException, SQLException {
        for (Pais paises : nuevoGestor.listaPais()) {
            System.out.println(paises.getIdPais() + "-" + paises.getNombrePais());
        }
        System.out.println("Elegir el numero del pais");
        int opcionPais = interfaz.leerNumero() - 1;
        if (opcionPais >= nuevoGestor.listaPais().size()) {
            System.out.println("Valor invalido");
        }
        return nuevoGestor.listaPais().get(opcionPais);
    }

    public Compositor seleccionarCompositor() {
        for (Compositor compositor : nuevoGestor.listaCompositor()) {
            System.out.println(compositor.getIdCompositor() + "-" + compositor.getNombre());
        }
        System.out.println("Elegir el numero del compositor");
        int opcionCompositor = interfaz.leerNumero() - 1;
        if (opcionCompositor >= nuevoGestor.listaCompositor().size()) {
            System.out.println("Valor invalido");
        }
        return nuevoGestor.listaCompositor().get(opcionCompositor);
    }

    public Genero seleccionarGenero() {

        for (Genero generos : nuevoGestor.listaGenero()) {
            System.out.println(generos.getIdGenero() + "-" + generos.getNombreGenero());
        }
        System.out.println("Elegir el numero del genero");
        int opcionGenero = interfaz.leerNumero() - 1;
        if (opcionGenero > nuevoGestor.listaGenero().size()) {
            System.out.println("Valor invalido");
        }
        return nuevoGestor.listaGenero().get(opcionGenero);
    }

    public void ejecutarMenuNoAdmin() {
        int contador = 0;
        try {
            String nombreUsuario, passwordUsuario;
            int opcion;
            do {
                if (contador != 0) {
                    interfaz.imprimirMensaje("Nombre de usuario o password incorrectos");
                }
                interfaz.mostrarMenuLoginNoAdminNombreUsuario();
                nombreUsuario = interfaz.leerTexto();
                interfaz.mostrarMenuLoginNoAdminPasswordUsuario();
                passwordUsuario = interfaz.leerTexto();
                contador++;

            } while (this.nuevoGestor.LoginNoAdmin(nombreUsuario, passwordUsuario) == null);

            this.usarioLogeado = this.nuevoGestor.LoginNoAdmin(nombreUsuario, passwordUsuario);
            
            do{
            interfaz.mostrarMenuNoAdmin();
            opcion = interfaz.leerNumero();
            this.procesarNoAdmin(opcion);
            }while(opcion!=4);   
        } catch (InstantiationException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void procesarOpcion(int opcion) {

        switch (opcion) {
            case 1:
                crearArtista();
                break;
            case 2: {
                try {
                    crearCompositor();
                } catch (InstantiationException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case 3:
                agregarGenero();
                break;

            case 4:
                this.crearAlbumArtista();
                break;
            case 5:
                this.crearCancionAdmin();
                break;
            case 6:
                this.agregarCancionUnAlbum();
                break;
            case 7:
                this.ListarCancionesEnAlbum();
                break;
            case 8:
                this.listarCancionEnTienda();
                break;
            case 9:
                break;
            default:
                interfaz.imprimirMensaje("Opcion desconocida");
        }

    }

    public void procesarNoAdmin(int opcion) {

        switch (opcion) {
            case 1:
                crearCancionUsuario();
                break;
            case 2:
                //crearAlbum();
                comprarCancion();
                break;
            case 3:
                crearListaRepro();
                ;
                break;
            case 4:
                break;
            /*case 5:
                break;*/
            default:
                interfaz.imprimirMensaje("Opcion desconocida");
        }

    }

    public void procesarLogin(int opcion) {
        switch (opcion) {
            case 1:
                ejecutarMenuAdmin();
                break;
            case 2:
                ejecutarMenuNoAdmin();
                break;
            case 3:
                this.crearUsuario();

                break;
            default:
                interfaz.imprimirMensaje("Opcion desconocida");
        }
    }

    public Cancion crearCancion() {

        interfaz.imprimirMensaje("Nombre de la cancion");
        String nombreCancion = interfaz.leerTexto();
        Genero nuevoGeneroCancion = new Genero();
        nuevoGeneroCancion = this.seleccionarGenero();
        Compositor cancionCompositor = new Compositor();

        cancionCompositor = this.seleccionarCompositor();

        interfaz.imprimirMensaje("Fecha de lanzamiento (yyyy-MM-dd)");
        String fechaLanzamiento = interfaz.leerTexto();

        return nuevoGestor.agregarCancion(nombreCancion, nuevoGeneroCancion, cancionCompositor, fechaLanzamiento);
    }

    public void crearCancionAdmin() {

        Cancion temp = crearCancion();
        interfaz.imprimirMensaje("Escriba el precio de la cancion");
        int precio = interfaz.leerNumero();
        nuevoGestor.agregarCancionAlaTienda(temp, precio);

    }

    public void crearCancionUsuario() {
        NoAdmin usuario = new NoAdmin();
        usuario.setIdNoAdmin(1);
        Cancion tempUsuario = crearCancion();
        nuevoGestor.agrecarCancionAlUsuario(tempUsuario, usuario);

    }

    public void listarCancionEnTienda() {
        for (TiendaCancion tiendaCancion : this.nuevoGestor.listaCancionesEnTienda()) {
            System.out.println(tiendaCancion.getCanciones().getIdCancion() + "-" + tiendaCancion.getCanciones().getNombreCancion() + "-" + tiendaCancion.getPrecio());
        }
    }

    public void comprarCancion() {
        List<TiendaCancion> cancionesEnLaTienda = this.nuevoGestor.listaCancionesEnTienda();
        int cancion = 0;
        int opcionCancion = 0;
        int idUsuario = this.usarioLogeado.getIdNoAdmin();
        do {
            for (TiendaCancion tiendaCancion : cancionesEnLaTienda) {
                System.out.println(tiendaCancion.getCanciones().getIdCancion() + "-" + tiendaCancion.getCanciones().getNombreCancion() + "-" + tiendaCancion.getPrecio());
            }

            opcionCancion = interfaz.leerNumero();

            for (TiendaCancion tiendaCancion : cancionesEnLaTienda) {
                if (tiendaCancion.getCanciones().getIdCancion() == opcionCancion) {
                    cancion = opcionCancion;
                }
            }
        } while (cancion != opcionCancion);
        Cancion nuevaCancionUsuario = new Cancion();
        NoAdmin usuarioActual = new NoAdmin();
        usuarioActual.setIdNoAdmin(idUsuario);
        nuevaCancionUsuario.setIdCancion(cancion);
        this.nuevoGestor.agrecarCancionAlUsuario(nuevaCancionUsuario, usuarioActual);

    }

    public void agregarCancionUnAlbum() {
        interfaz.imprimirMensaje("Seleccione un album");
        int idAlbumArtista = this.seleccionarAlbumArtista().getIdAlbumArtista();
        int idCancion = this.seleccionarCancion().getIdCancion();
        this.nuevoGestor.agregarAlbumArtistaCancion(idAlbumArtista, idCancion);

    }

    public void ListarCancionesEnAlbum() {
        interfaz.imprimirMensaje("Seleccione el album que desea listar canciones");
        int idAlbumArtista = this.seleccionarAlbumArtista().getIdAlbumArtista();

        for (Cancion cancion : this.nuevoGestor.listaCancionesEnAlbum(idAlbumArtista)) {
            System.out.println(cancion.getIdCancion() + "-" + cancion.getNombreCancion());
        }

    }

    public ListaReproduccion crearListaRepro() {

        List<Cancion> listaCanciones = nuevoGestor.listaCancionesDelUsuario(1);

        List<Cancion> cancionesParaListaReproducion = new ArrayList<>();

        for (Cancion cancion : listaCanciones) {
            System.out.println(cancion.getIdCancion() + "-" + cancion.getNombreCancion());
        }
        System.out.println("Elegir el numero de la cancion");

        int opcionCancion = interfaz.leerNumero();
        for (Cancion cancion : listaCanciones) {
            if (cancion.getIdCancion() == opcionCancion) {
                cancionesParaListaReproducion.add(cancion);
            }
        }

        Date fechaCreacion = new Date();
        interfaz.imprimirMensaje("Nombre de la lista");
        String nombreLista = interfaz.leerTexto();
        //Modificar esta parte por el promedio
        interfaz.imprimirMensaje("Calificacion");
        int listaCalificacion = interfaz.leerNumero();

        nuevoGestor.agregarListaCancion(this.usarioLogeado, (ArrayList) cancionesParaListaReproducion, nombreLista, listaCalificacion, fechaCreacion);

        return null;

    }

    public Compositor crearCompositor() throws InstantiationException {
        interfaz.imprimirMensaje("Nombre del compositor");
        String nombreCompositor = interfaz.leerTexto();
        interfaz.imprimirMensaje("Apellido");
        String apellidoCompositor = interfaz.leerTexto();

        //Cambiar por displey de paises
        Pais nuevPais = new Pais();
        try {
            nuevPais = this.seleccionarPais();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        interfaz.imprimirMensaje("Edad");
        int edadCompo = interfaz.leerNumero();

        //Cambiar lista por la correcta con menu
        Genero nuevGenero = this.seleccionarGenero();

        Compositor nuevoCompositor = new Compositor(edadCompo, nuevGenero, nombreCompositor, apellidoCompositor, nuevPais);

        try {
            nuevoGestor.agregarCompositor(nombreCompositor, apellidoCompositor, nuevPais, edadCompo, nuevGenero);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        interfaz.imprimirMensaje("Compositor Agregado");
        return nuevoCompositor;
    }

    public void crearArtista() {

        interfaz.imprimirMensaje("Nombre del artista");
        String nombreArtista = interfaz.leerTexto();
        interfaz.imprimirMensaje("Apellido del artista");
        String apellidoArtista = interfaz.leerTexto();

        Pais nuevPais = new Pais();
        try {
            nuevPais = this.seleccionarPais();
        } catch (InstantiationException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        interfaz.imprimirMensaje("Nombre artistico");
        String nombreArtistico = interfaz.leerTexto();
        //Cambiar la fecha de nacimiento por el formato correcto
        interfaz.imprimirMensaje("Fecha de nacimiento");
        String fechaNacimiento = interfaz.leerTexto();
        interfaz.imprimirMensaje("Fecha de defuncion");
        String fechaDefuncion = interfaz.leerTexto();
        interfaz.imprimirMensaje("Edad del artista");
        int edad = interfaz.leerNumero();

        Genero nuevGenero = new Genero();
        nuevGenero = this.seleccionarGenero();

        interfaz.imprimirMensaje("Descripcion del artista");
        String descripcion = interfaz.leerTexto();
        try {
            //AlbumArtista nuevoAlbumEnArtista = new AlbumArtista();
            //nuevoAlbumEnArtista = this.crearAlbumArtista()
            nuevoGestor.agregarArtista(nombreArtistico, fechaNacimiento, fechaDefuncion, nuevGenero, edad, descripcion/*, null*/, nombreArtista, apellidoArtista, nuevPais);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        interfaz.imprimirMensaje("Artista Agregado");

    }

    public Album crearAlbum() {
        interfaz.imprimirMensaje("Nombre del Album");
        String nombreAlbum = interfaz.leerTexto();
        Cancion albumCancion = new Cancion();
        albumCancion = this.seleccionarCancion();
        interfaz.imprimirMensaje("Fecha de lanzamiento");
        String fechaAlbum = interfaz.leerTexto();
        /*Artista artistaEnAlbum = new Artista();
        artistaEnAlbum = this.seleccionarArtista();*/
        interfaz.imprimirMensaje("Suba una imagen");
        String imagenAlbum = ("Foto.jpg");
        Album nuevoAlbum = new Album(nombreAlbum, fechaAlbum/*, artistaEnAlbum*/, albumCancion, imagenAlbum);
        nuevoGestor.agregarAlbum(nombreAlbum, fechaAlbum/*, artistaEnAlbum*/, albumCancion, imagenAlbum);

        return nuevoAlbum;
    }

    public AlbumArtista crearAlbumArtista() {
        interfaz.imprimirMensaje("Nombre del Album");
        String nombreAlbumArtista = interfaz.leerTexto();
        AlbumArtista nuevoAlbumArtista = new AlbumArtista(nombreAlbumArtista);
        nuevoGestor.agregarAlbumArtista(nombreAlbumArtista);

        return nuevoAlbumArtista;
    }

    public void crearAdministrador() throws SQLException {
        interfaz.imprimirMensaje("Escriba su nombre");
        String adminNombre = interfaz.leerTexto();
        interfaz.imprimirMensaje("Escriba su apellido");
        String adminApellido = interfaz.leerTexto();
        Pais nuevPais = new Pais();
        try {
            nuevPais = this.seleccionarPais();
        } catch (InstantiationException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        interfaz.imprimirMensaje("Escriba su edad");
        int adminEdad = interfaz.leerNumero();
        interfaz.imprimirMensaje("Escriba su correo");
        String adminCorreo = interfaz.leerTexto();
        interfaz.imprimirMensaje("Escriba su nombre de usuario");
        String adminNick = interfaz.leerTexto();
        interfaz.imprimirMensaje("Escriba su contraseña");
        String adminContrasenna = interfaz.leerTexto();
        nuevoGestor.crearAdmin(adminNombre, adminApellido, nuevPais, adminEdad, adminCorreo, adminNick, adminContrasenna);
    }

    public void crearUsuario() {
        interfaz.imprimirMensaje("Escriba su nombre");
        String nombre = interfaz.leerTexto();
        interfaz.imprimirMensaje("Escriba su apellido");
        String apellido = interfaz.leerTexto();
        Pais nuevPais = new Pais();
        try {
            nuevPais = this.seleccionarPais();
        } catch (InstantiationException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        interfaz.imprimirMensaje("Escriba su edad");
        int edad = interfaz.leerNumero();
        interfaz.imprimirMensaje("Digite el numero de identificacion");
        int identificacion = interfaz.leerNumero();
        interfaz.imprimirMensaje("Escriba su correo");
        String correoUsuario = interfaz.leerTexto();
        interfaz.imprimirMensaje("Escriba su nombre de usuario");
        String nombreUsuario = interfaz.leerTexto();
        interfaz.imprimirMensaje("Escriba su contrasennia");
        String contrasennaUsuario = interfaz.leerTexto();
        this.usarioLogeado = nuevoGestor.crearUsuario(nombre, apellido, nuevPais, edad, identificacion, correoUsuario, nombreUsuario, contrasennaUsuario);
        this.ejecutarMenuNoAdmin();
    }

    public AlbumArtista seleccionarAlbumArtista() {
        for (AlbumArtista albumArtista : nuevoGestor.listaAlbumArtista()) {
            System.out.println(albumArtista.getIdAlbumArtista() + "-" + albumArtista.getNombreAlbumArtista());
        }
        System.out.println("Elegir el numero del album");
        int opcionAlbumArtista = interfaz.leerNumero() - 1;
        if (opcionAlbumArtista >= nuevoGestor.listaAlbumArtista().size()) {
            System.out.println("Valor invalido");
        }
        return nuevoGestor.listaAlbumArtista().get(opcionAlbumArtista);
    }

    public Album seleccionarAlbum() {
        int cont = 0;
        for (Album album : nuevoGestor.getAlbum()) {
            interfaz.imprimirMensaje(cont++ + ". " + album.getNombreAlbum());
        }
        return nuevoGestor.getAlbum().get(this.interfaz.leerNumero());
    }

    public Cancion seleccionarCancion() {

        for (Cancion canciones : nuevoGestor.listaCancion()) {
            System.out.println(canciones.getIdCancion() + "-" + canciones.getNombreCancion());
        }
        System.out.println("Elegir el numero del la cancion");
        int opcionGenero = interfaz.leerNumero() - 1;
        if (opcionGenero > nuevoGestor.listaCancion().size()) {
            System.out.println("Valor invalido");
        }
        return nuevoGestor.listaCancion().get(opcionGenero);
    }

    public Artista seleccionarArtista() {
        int cont = 0;

        if (nuevoGestor.getArtista().isEmpty()) {
            this.crearArtista();
        }
        interfaz.imprimirMensaje("Seleccione el artista del album");
        for (Artista artistas : nuevoGestor.getArtista()) {
            interfaz.imprimirMensaje(cont++ + ". " + artistas.getNombreArtistico());
        }

        return nuevoGestor.getArtista().get(this.interfaz.leerNumero());

    }

    private void agregarGenero() {
        interfaz.imprimirMensaje("Nombre del genero");
        String nombreGenero = interfaz.leerTexto();
        interfaz.imprimirMensaje("Descripcion del genero");
        String descripcion = interfaz.leerTexto();
        nuevoGestor.crearGenero(nombreGenero, descripcion);
    }

}
