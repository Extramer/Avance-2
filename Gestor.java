package logica.gestor;

import logica.dominio.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Clase gestora de la lógica de negocio de la plataforma de subastas.
 * Administra usuarios, subastas y ofertas en memoria mediante ArrayLists.
 * Aplica todas las reglas de negocio definidas en la plataforma.
 *
 * @author Equipo
 * @version 2.0
 */
public class Gestor {

    /** Lista de todos los usuarios registrados (moderador, vendedores, coleccionistas). */
    private ArrayList<Usuario> usuarios;

    /** Lista de subastas activas y vencidas. */
    private ArrayList<Subasta> subastas;

    /** Lista global de ofertas realizadas en la plataforma. */
    private ArrayList<Oferta> ofertas;

    /** Moderador único del sistema. */
    private Moderador moderador;

    /** Usuario con la sesión activa. Null si no hay sesión. */
    private Usuario usuarioActivo;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    /**
     * Constructor por defecto. Inicializa las listas en memoria.
     */
    public Gestor() {
        this.usuarios   = new ArrayList<>();
        this.subastas   = new ArrayList<>();
        this.ofertas    = new ArrayList<>();
        this.moderador  = null;
        this.usuarioActivo = null;
    }

    // -------------------------------------------------------------------------
    // Gestión de moderador
    // -------------------------------------------------------------------------

    /**
     * Verifica si ya existe un moderador registrado en el sistema.
     *
     * @return {@code true} si hay un moderador registrado.
     */
    public boolean existeModerador() {
        return moderador != null;
    }

    /**
     * Registra el moderador único del sistema.
     * Solo se puede registrar si no existe uno previamente.
     *
     * @param nombre          Nombre completo.
     * @param identificacion  Identificación.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param contrasena      Contraseña.
     * @param correo          Correo electrónico.
     * @return Mensaje de resultado de la operación.
     */
    public String registrarModerador(String nombre, String identificacion,
                                     LocalDate fechaNacimiento, String contrasena, String correo) {
        if (existeModerador()) {
            return "ERROR: Ya existe un moderador en el sistema.";
        }
        Moderador m = new Moderador(nombre, identificacion, fechaNacimiento, contrasena, correo);
        if (!m.esMayorDeEdad()) {
            return "ERROR: El moderador debe ser mayor de edad.";
        }
        moderador = m;
        usuarios.add(m);
        return "Moderador registrado exitosamente.";
    }

    // -------------------------------------------------------------------------
    // Gestión de usuarios
    // -------------------------------------------------------------------------

    /**
     * Registra un nuevo coleccionista en la plataforma.
     * El coleccionista debe ser mayor de edad.
     *
     * @param nombre          Nombre completo.
     * @param identificacion  Identificación.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param contrasena      Contraseña.
     * @param correo          Correo electrónico.
     * @param direccion       Dirección física.
     * @return Mensaje de resultado.
     */
    public String registrarColeccionista(String nombre, String identificacion,
                                         LocalDate fechaNacimiento, String contrasena,
                                         String correo, String direccion) {
        Coleccionista c = new Coleccionista(nombre, identificacion, fechaNacimiento,
                                            contrasena, correo, 0.0, direccion);
        if (!c.esMayorDeEdad()) {
            return "ERROR: Debe ser mayor de edad para registrarse como coleccionista.";
        }
        if (existeUsuario(identificacion)) {
            return "ERROR: Ya existe un usuario con esa identificación.";
        }
        usuarios.add(c);
        return "Coleccionista '" + nombre + "' registrado exitosamente.";
    }

    /**
     * Registra un nuevo vendedor en la plataforma.
     * El vendedor debe ser mayor de edad.
     *
     * @param nombre          Nombre completo.
     * @param identificacion  Identificación.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param contrasena      Contraseña.
     * @param correo          Correo electrónico.
     * @param direccion       Dirección física.
     * @return Mensaje de resultado.
     */
    public String registrarVendedor(String nombre, String identificacion,
                                    LocalDate fechaNacimiento, String contrasena,
                                    String correo, String direccion) {
        Vendedor v = new Vendedor(nombre, identificacion, fechaNacimiento,
                                  contrasena, correo, 0.0, direccion);
        if (!v.esMayorDeEdad()) {
            return "ERROR: Debe ser mayor de edad para registrarse como vendedor.";
        }
        if (existeUsuario(identificacion)) {
            return "ERROR: Ya existe un usuario con esa identificación.";
        }
        usuarios.add(v);
        return "Vendedor '" + nombre + "' registrado exitosamente.";
    }

    /**
     * Verifica si ya existe un usuario con la identificación dada.
     *
     * @param identificacion Identificación a verificar.
     * @return {@code true} si ya existe.
     */
    public boolean existeUsuario(String identificacion) {
        for (Usuario u : usuarios) {
            if (u.getIdentificacion().equals(identificacion)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna la lista completa de usuarios registrados.
     *
     * @return lista de usuarios.
     */
    public ArrayList<Usuario> listarUsuarios() {
        return usuarios;
    }

    // -------------------------------------------------------------------------
    // Inicio de sesión
    // -------------------------------------------------------------------------

    /**
     * Inicia sesión con un usuario registrado.
     * Valida identificación y contraseña.
     *
     * @param identificacion Identificación del usuario.
     * @param contrasena     Contraseña del usuario.
     * @return Mensaje de resultado del inicio de sesión.
     */
    public String iniciarSesion(String identificacion, String contrasena) {
        for (Usuario u : usuarios) {
            if (u.getIdentificacion().equals(identificacion)) {
                if (u.getContrasena().equals(contrasena)) {
                    usuarioActivo = u;
                    return "Sesión iniciada correctamente. Bienvenido/a, " + u.getNombre() + ".";
                } else {
                    return "ERROR: Contraseña incorrecta.";
                }
            }
        }
        return "ERROR: No existe un usuario con esa identificación.";
    }

    /**
     * Cierra la sesión del usuario activo.
     *
     * @return Mensaje de confirmación.
     */
    public String cerrarSesion() {
        if (usuarioActivo == null) {
            return "No hay sesión activa.";
        }
        String nombre = usuarioActivo.getNombre();
        usuarioActivo = null;
        return "Sesión cerrada. Hasta luego, " + nombre + ".";
    }

    /**
     * Obtiene el usuario con la sesión activa actualmente.
     *
     * @return usuario activo o {@code null} si no hay sesión.
     */
    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    // -------------------------------------------------------------------------
    // Gestión de subastas
    // -------------------------------------------------------------------------

    /**
     * Crea una nueva subasta asociada al usuario activo.
     * Aplica las reglas de negocio correspondientes.
     *
     * @param fechaVencimiento Fecha y hora de vencimiento.
     * @param precioMinimo     Precio mínimo de aceptación.
     * @param objetos          Lista de objetos a subastar.
     * @return Mensaje de resultado.
     */
    public String crearSubasta(LocalDateTime fechaVencimiento, double precioMinimo,
                               ArrayList<Objeto> objetos) {
        if (usuarioActivo == null) {
            return "ERROR: Debe iniciar sesión para crear una subasta.";
        }
        if (usuarioActivo instanceof Moderador) {
            return "ERROR: El moderador no puede crear subastas.";
        }
        if (objetos == null || objetos.isEmpty()) {
            return "ERROR: La subasta debe tener al menos un objeto asociado.";
        }
        // Si el creador es coleccionista, solo puede subastar objetos de su colección
        if (usuarioActivo instanceof Coleccionista) {
            Coleccionista col = (Coleccionista) usuarioActivo;
            for (Objeto o : objetos) {
                if (!col.tieneObjeto(o)) {
                    return "ERROR: Solo puede subastar objetos registrados en su colección.";
                }
            }
        }
        Subasta subasta = new Subasta(fechaVencimiento, usuarioActivo, precioMinimo, "Activa");
        for (Objeto o : objetos) {
            subasta.agregarObjeto(o);
        }
        subastas.add(subasta);
        return "Subasta creada exitosamente.";
    }

    /**
     * Retorna la lista completa de subastas.
     *
     * @return lista de subastas.
     */
    public ArrayList<Subasta> listarSubastas() {
        return subastas;
    }

    // -------------------------------------------------------------------------
    // Gestión de ofertas
    // -------------------------------------------------------------------------

    /**
     * Crea una nueva oferta en una subasta indicada por índice.
     * Aplica todas las reglas de negocio sobre quién puede ofertar.
     *
     * @param indiceSubasta  Índice de la subasta en la lista (base 0).
     * @param precioOfertado Precio ofertado.
     * @return Mensaje de resultado.
     */
    public String crearOferta(int indiceSubasta, double precioOfertado) {
        if (usuarioActivo == null) {
            return "ERROR: Debe iniciar sesión para realizar una oferta.";
        }
        if (usuarioActivo instanceof Moderador) {
            return "ERROR: El moderador no puede realizar ofertas.";
        }
        if (usuarioActivo instanceof Vendedor) {
            return "ERROR: Los vendedores no pueden realizar ofertas.";
        }
        if (indiceSubasta < 0 || indiceSubasta >= subastas.size()) {
            return "ERROR: Subasta no encontrada.";
        }
        Subasta subasta = subastas.get(indiceSubasta);
        if (!"Activa".equals(subasta.getEstado())) {
            return "ERROR: La subasta no está activa.";
        }
        // Un coleccionista no puede ofertar en su propia subasta
        if (subasta.getCreador().equals(usuarioActivo)) {
            return "ERROR: No puede ofertar en su propia subasta.";
        }
        if (precioOfertado < subasta.getPrecioMinimo()) {
            return "ERROR: El precio ofertado debe ser mayor o igual al precio mínimo ($" +
                   String.format("%.2f", subasta.getPrecioMinimo()) + ").";
        }
        Coleccionista col = (Coleccionista) usuarioActivo;
        Oferta oferta = new Oferta(col.getNombre(), col.getPuntuacion(), precioOfertado);
        subasta.agregarOferta(oferta);
        ofertas.add(oferta);
        return "Oferta de $" + String.format("%.2f", precioOfertado) + " registrada exitosamente.";
    }

    /**
     * Retorna la lista global de ofertas.
     *
     * @return lista de ofertas.
     */
    public ArrayList<Oferta> listarOfertas() {
        return ofertas;
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    /**
     * Obtiene el moderador registrado en el sistema.
     * @return moderador, o {@code null} si no existe.
     */
    public Moderador getModerador() { return moderador; }
}
