package logica.dominio;

import java.time.LocalDate;
import java.time.Period;

/**
 * Representa un objeto de valor ofrecido en la plataforma de subastas.
 *
 * @author Equipo
 * @version 2.0
 */
public class Objeto {

    /** Nombre del objeto. */
    private String nombre;

    /** Descripción del objeto. */
    private String descripcion;

    /**
     * Estado del objeto.
     * Valores posibles: "Nuevo", "Usado", "Antiguo sin abrir".
     */
    private String estado;

    /** Fecha de compra del objeto. */
    private LocalDate fechaCompra;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    /**
     * Constructor por defecto.
     */
    public Objeto() {
        this.nombre = "";
        this.descripcion = "";
        this.estado = "Nuevo";
        this.fechaCompra = LocalDate.now();
    }

    /**
     * Constructor general que asigna todos los atributos del objeto.
     *
     * @param nombre      Nombre del objeto.
     * @param descripcion Descripción del objeto.
     * @param estado      Estado del objeto (Nuevo, Usado, Antiguo sin abrir).
     * @param fechaCompra Fecha en que fue adquirido.
     */
    public Objeto(String nombre, String descripcion, String estado, LocalDate fechaCompra) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
    }

    // -------------------------------------------------------------------------
    // Métodos de negocio
    // -------------------------------------------------------------------------

    /**
     * Calcula la antigüedad del objeto desde su fecha de compra.
     *
     * @return Cadena con la antigüedad en años, meses y días.
     */
    public String calcularAntiguedad() {
        Period periodo = Period.between(fechaCompra, LocalDate.now());
        return periodo.getYears() + " año(s), " + periodo.getMonths() +
               " mes(es), " + periodo.getDays() + " día(s)";
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene el nombre del objeto.
     * @return nombre.
     */
    public String getNombre() { return nombre; }

    /**
     * Asigna el nombre del objeto.
     * @param nombre nombre a asignar.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Obtiene la descripción del objeto.
     * @return descripción.
     */
    public String getDescripcion() { return descripcion; }

    /**
     * Asigna la descripción del objeto.
     * @param descripcion descripción a asignar.
     */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    /**
     * Obtiene el estado del objeto.
     * @return estado.
     */
    public String getEstado() { return estado; }

    /**
     * Asigna el estado del objeto.
     * @param estado estado a asignar.
     */
    public void setEstado(String estado) { this.estado = estado; }

    /**
     * Obtiene la fecha de compra del objeto.
     * @return fecha de compra.
     */
    public LocalDate getFechaCompra() { return fechaCompra; }

    /**
     * Asigna la fecha de compra del objeto.
     * @param fechaCompra fecha a asignar.
     */
    public void setFechaCompra(LocalDate fechaCompra) { this.fechaCompra = fechaCompra; }

    // -------------------------------------------------------------------------
    // equals, toString
    // -------------------------------------------------------------------------

    /**
     * Compara dos objetos por su nombre y fecha de compra.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} si son iguales.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Objeto)) return false;
        Objeto otro = (Objeto) obj;
        return this.nombre.equals(otro.nombre) && this.fechaCompra.equals(otro.fechaCompra);
    }

    /**
     * Representación en texto del objeto.
     *
     * @return Cadena con los datos del objeto.
     */
    @Override
    public String toString() {
        return "Objeto{Nombre='" + nombre + "', Descripcion='" + descripcion +
               "', Estado='" + estado + "', Antiguedad=" + calcularAntiguedad() + "}";
    }
}
