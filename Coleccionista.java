package logica.dominio;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representa a un coleccionista de la plataforma.
 * El coleccionista puede crear subastas y realizar ofertas,
 * pero no puede ofertar en sus propias subastas.
 *
 * @author Equipo
 * @version 2.0
 */
public class Coleccionista extends Usuario {

    /** Puntuación del coleccionista en la plataforma. */
    private double puntuacion;

    /** Dirección física del coleccionista. */
    private String direccion;

    /** Lista de intereses del coleccionista (categorías, temas, etc.). */
    private ArrayList<String> intereses;

    /** Lista de objetos que pertenecen a la colección del coleccionista. */
    private ArrayList<Objeto> coleccion;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    /**
     * Constructor por defecto.
     */
    public Coleccionista() {
        super();
        this.puntuacion = 0.0;
        this.direccion = "";
        this.intereses = new ArrayList<>();
        this.coleccion = new ArrayList<>();
    }

    /**
     * Constructor general que asigna todos los atributos del coleccionista.
     *
     * @param nombre          Nombre completo.
     * @param identificacion  Identificación única.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param contrasena      Contraseña.
     * @param correo          Correo electrónico.
     * @param puntuacion      Puntuación en la plataforma.
     * @param direccion       Dirección física.
     */
    public Coleccionista(String nombre, String identificacion, LocalDate fechaNacimiento,
                         String contrasena, String correo, double puntuacion, String direccion) {
        super(nombre, identificacion, fechaNacimiento, contrasena, correo);
        this.puntuacion = puntuacion;
        this.direccion = direccion;
        this.intereses = new ArrayList<>();
        this.coleccion = new ArrayList<>();
    }

    // -------------------------------------------------------------------------
    // Métodos de negocio
    // -------------------------------------------------------------------------

    /**
     * Agrega un interés a la lista del coleccionista.
     *
     * @param interes Interés a agregar.
     */
    public void agregarInteres(String interes) {
        if (!intereses.contains(interes)) {
            intereses.add(interes);
        }
    }

    /**
     * Agrega un objeto a la colección del coleccionista.
     *
     * @param objeto Objeto a agregar.
     */
    public void agregarObjeto(Objeto objeto) {
        if (!coleccion.contains(objeto)) {
            coleccion.add(objeto);
        }
    }

    /**
     * Verifica si un objeto pertenece a la colección del coleccionista.
     *
     * @param objeto Objeto a verificar.
     * @return {@code true} si el objeto está en la colección.
     */
    public boolean tieneObjeto(Objeto objeto) {
        return coleccion.contains(objeto);
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene la puntuación del coleccionista.
     * @return puntuación.
     */
    public double getPuntuacion() { return puntuacion; }

    /**
     * Asigna la puntuación del coleccionista.
     * @param puntuacion puntuación a asignar.
     */
    public void setPuntuacion(double puntuacion) { this.puntuacion = puntuacion; }

    /**
     * Obtiene la dirección del coleccionista.
     * @return dirección.
     */
    public String getDireccion() { return direccion; }

    /**
     * Asigna la dirección del coleccionista.
     * @param direccion dirección a asignar.
     */
    public void setDireccion(String direccion) { this.direccion = direccion; }

    /**
     * Obtiene la lista de intereses del coleccionista.
     * @return lista de intereses.
     */
    public ArrayList<String> getIntereses() { return intereses; }

    /**
     * Asigna la lista de intereses del coleccionista.
     * @param intereses lista a asignar.
     */
    public void setIntereses(ArrayList<String> intereses) { this.intereses = intereses; }

    /**
     * Obtiene la colección de objetos del coleccionista.
     * @return lista de objetos.
     */
    public ArrayList<Objeto> getColeccion() { return coleccion; }

    /**
     * Asigna la colección de objetos del coleccionista.
     * @param coleccion lista a asignar.
     */
    public void setColeccion(ArrayList<Objeto> coleccion) { this.coleccion = coleccion; }

    // -------------------------------------------------------------------------
    // equals, toString
    // -------------------------------------------------------------------------

    /**
     * Compara dos coleccionistas por su identificación.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} si tienen la misma identificación.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Coleccionista)) return false;
        return super.equals(obj);
    }

    /**
     * Representación en texto del coleccionista.
     *
     * @return Cadena con los datos del coleccionista.
     */
    @Override
    public String toString() {
        return "Coleccionista{Nombre='" + getNombre() + "', ID='" + getIdentificacion() +
               "', Edad=" + calcularEdad() + ", Correo='" + getCorreo() +
               "', Puntuacion=" + puntuacion + ", Direccion='" + direccion +
               "', Intereses=" + intereses + ", Objetos en coleccion=" + coleccion.size() + "}";
    }
}
