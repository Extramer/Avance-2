package logica.dominio;

import java.time.LocalDate;
import java.time.Period;

/**
 * Clase base que representa a un usuario del sistema de subastas.
 * Contiene los atributos comunes a todos los tipos de usuario.
 *
 * @author Equipo
 * @version 2.0
 */
public abstract class Usuario {

    /** Nombre completo del usuario. */
    private String nombre;

    /** Identificación única del usuario. */
    private String identificacion;

    /** Fecha de nacimiento del usuario. */
    private LocalDate fechaNacimiento;

    /** Contraseña del usuario. */
    private String contrasena;

    /** Correo electrónico del usuario. */
    private String correo;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    /**
     * Constructor por defecto. Inicializa los atributos con valores vacíos.
     */
    public Usuario() {
        this.nombre = "";
        this.identificacion = "";
        this.fechaNacimiento = LocalDate.now();
        this.contrasena = "";
        this.correo = "";
    }

    /**
     * Constructor general que asigna todos los atributos del usuario.
     *
     * @param nombre          Nombre completo del usuario.
     * @param identificacion  Identificación única.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param contrasena      Contraseña del usuario.
     * @param correo          Correo electrónico.
     */
    public Usuario(String nombre, String identificacion, LocalDate fechaNacimiento,
                   String contrasena, String correo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasena = contrasena;
        this.correo = correo;
    }

    // -------------------------------------------------------------------------
    // Métodos de negocio
    // -------------------------------------------------------------------------

    /**
     * Calcula la edad del usuario a partir de su fecha de nacimiento.
     *
     * @return Edad en años completos.
     */
    public int calcularEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    /**
     * Verifica si el usuario es mayor de edad (18 años o más).
     *
     * @return {@code true} si es mayor de edad, {@code false} en caso contrario.
     */
    public boolean esMayorDeEdad() {
        return calcularEdad() >= 18;
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene el nombre del usuario.
     * @return nombre del usuario.
     */
    public String getNombre() { return nombre; }

    /**
     * Asigna el nombre del usuario.
     * @param nombre nombre a asignar.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Obtiene la identificación del usuario.
     * @return identificación.
     */
    public String getIdentificacion() { return identificacion; }

    /**
     * Asigna la identificación del usuario.
     * @param identificacion identificación a asignar.
     */
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    /**
     * Obtiene la fecha de nacimiento.
     * @return fecha de nacimiento.
     */
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    /**
     * Asigna la fecha de nacimiento.
     * @param fechaNacimiento fecha a asignar.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    /**
     * Obtiene la contraseña del usuario.
     * @return contraseña.
     */
    public String getContrasena() { return contrasena; }

    /**
     * Asigna la contraseña del usuario.
     * @param contrasena contraseña a asignar.
     */
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    /**
     * Obtiene el correo electrónico del usuario.
     * @return correo electrónico.
     */
    public String getCorreo() { return correo; }

    /**
     * Asigna el correo electrónico del usuario.
     * @param correo correo a asignar.
     */
    public void setCorreo(String correo) { this.correo = correo; }

    // -------------------------------------------------------------------------
    // equals, toString
    // -------------------------------------------------------------------------

    /**
     * Compara dos usuarios por su identificación.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} si tienen la misma identificación.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Usuario)) return false;
        Usuario otro = (Usuario) obj;
        return this.identificacion.equals(otro.identificacion);
    }

    /**
     * Representación en texto del usuario.
     *
     * @return Cadena con los datos del usuario.
     */
    @Override
    public String toString() {
        return "Usuario{Nombre='" + nombre + "', ID='" + identificacion +
               "', Edad=" + calcularEdad() + ", Correo='" + correo + "'}";
    }
}
