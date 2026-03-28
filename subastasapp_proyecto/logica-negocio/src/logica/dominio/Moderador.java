package logica.dominio;

import java.time.LocalDate;

/**
 * Representa al moderador único de la plataforma.
 * El moderador no puede participar en subastas ni realizar ofertas.
 * Solo puede existir un moderador en el sistema.
 *
 * @author Equipo
 * @version 2.0
 */
public class Moderador extends Usuario {

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    /**
     * Constructor por defecto.
     */
    public Moderador() {
        super();
    }

    /**
     * Constructor general que asigna todos los atributos del moderador.
     *
     * @param nombre          Nombre completo.
     * @param identificacion  Identificación única.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param contrasena      Contraseña.
     * @param correo          Correo electrónico.
     */
    public Moderador(String nombre, String identificacion, LocalDate fechaNacimiento,
                     String contrasena, String correo) {
        super(nombre, identificacion, fechaNacimiento, contrasena, correo);
    }

    // -------------------------------------------------------------------------
    // equals, toString
    // -------------------------------------------------------------------------

    /**
     * Compara dos moderadores por su identificación.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} si tienen la misma identificación.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Moderador)) return false;
        return super.equals(obj);
    }

    /**
     * Representación en texto del moderador.
     *
     * @return Cadena con los datos del moderador.
     */
    @Override
    public String toString() {
        return "Moderador{Nombre='" + getNombre() + "', ID='" + getIdentificacion() +
               "', Edad=" + calcularEdad() + ", Correo='" + getCorreo() + "'}";
    }
}
