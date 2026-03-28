package logica.dominio;

import java.time.LocalDate;

/**
 * Representa a un vendedor de la plataforma.
 * El vendedor puede crear subastas pero no puede realizar ofertas.
 *
 * @author Equipo
 * @version 2.0
 */
public class Vendedor extends Usuario {

    /** Puntuación del vendedor en la plataforma. */
    private double puntuacion;

    /** Dirección del vendedor. */
    private String direccion;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    /**
     * Constructor por defecto.
     */
    public Vendedor() {
        super();
        this.puntuacion = 0.0;
        this.direccion = "";
    }

    /**
     * Constructor general que asigna todos los atributos del vendedor.
     *
     * @param nombre          Nombre completo.
     * @param identificacion  Identificación única.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param contrasena      Contraseña.
     * @param correo          Correo electrónico.
     * @param puntuacion      Puntuación en la plataforma.
     * @param direccion       Dirección física.
     */
    public Vendedor(String nombre, String identificacion, LocalDate fechaNacimiento,
                    String contrasena, String correo, double puntuacion, String direccion) {
        super(nombre, identificacion, fechaNacimiento, contrasena, correo);
        this.puntuacion = puntuacion;
        this.direccion = direccion;
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene la puntuación del vendedor.
     * @return puntuación.
     */
    public double getPuntuacion() { return puntuacion; }

    /**
     * Asigna la puntuación del vendedor.
     * @param puntuacion puntuación a asignar.
     */
    public void setPuntuacion(double puntuacion) { this.puntuacion = puntuacion; }

    /**
     * Obtiene la dirección del vendedor.
     * @return dirección.
     */
    public String getDireccion() { return direccion; }

    /**
     * Asigna la dirección del vendedor.
     * @param direccion dirección a asignar.
     */
    public void setDireccion(String direccion) { this.direccion = direccion; }

    // -------------------------------------------------------------------------
    // equals, toString
    // -------------------------------------------------------------------------

    /**
     * Compara dos vendedores por su identificación.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} si tienen la misma identificación.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Vendedor)) return false;
        return super.equals(obj);
    }

    /**
     * Representación en texto del vendedor.
     *
     * @return Cadena con los datos del vendedor.
     */
    @Override
    public String toString() {
        return "Vendedor{Nombre='" + getNombre() + "', ID='" + getIdentificacion() +
               "', Edad=" + calcularEdad() + "', Correo='" + getCorreo() +
               "', Puntuacion=" + puntuacion + ", Direccion='" + direccion + "'}";
    }
}
