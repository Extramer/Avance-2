package logica.dominio;

/**
 * Representa una oferta económica presentada por un coleccionista en una subasta.
 *
 * @author Equipo
 * @version 2.0
 */
public class Oferta {

    /** Nombre del oferente (coleccionista). */
    private String nombreOferente;

    /** Puntuación del oferente al momento de la oferta. */
    private double puntuacionOferente;

    /** Precio ofertado por el coleccionista. */
    private double precioOfertado;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    /**
     * Constructor por defecto.
     */
    public Oferta() {
        this.nombreOferente = "";
        this.puntuacionOferente = 0.0;
        this.precioOfertado = 0.0;
    }

    /**
     * Constructor general que asigna todos los atributos de la oferta.
     *
     * @param nombreOferente      Nombre del coleccionista que oferta.
     * @param puntuacionOferente  Puntuación del oferente.
     * @param precioOfertado      Monto ofertado.
     */
    public Oferta(String nombreOferente, double puntuacionOferente, double precioOfertado) {
        this.nombreOferente = nombreOferente;
        this.puntuacionOferente = puntuacionOferente;
        this.precioOfertado = precioOfertado;
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene el nombre del oferente.
     * @return nombre del oferente.
     */
    public String getNombreOferente() { return nombreOferente; }

    /**
     * Asigna el nombre del oferente.
     * @param nombreOferente nombre a asignar.
     */
    public void setNombreOferente(String nombreOferente) { this.nombreOferente = nombreOferente; }

    /**
     * Obtiene la puntuación del oferente.
     * @return puntuación.
     */
    public double getPuntuacionOferente() { return puntuacionOferente; }

    /**
     * Asigna la puntuación del oferente.
     * @param puntuacionOferente puntuación a asignar.
     */
    public void setPuntuacionOferente(double puntuacionOferente) {
        this.puntuacionOferente = puntuacionOferente;
    }

    /**
     * Obtiene el precio ofertado.
     * @return precio ofertado.
     */
    public double getPrecioOfertado() { return precioOfertado; }

    /**
     * Asigna el precio ofertado.
     * @param precioOfertado precio a asignar.
     */
    public void setPrecioOfertado(double precioOfertado) { this.precioOfertado = precioOfertado; }

    // -------------------------------------------------------------------------
    // equals, toString
    // -------------------------------------------------------------------------

    /**
     * Compara dos ofertas por oferente y precio.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} si tienen el mismo oferente y precio.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Oferta)) return false;
        Oferta otra = (Oferta) obj;
        return this.nombreOferente.equals(otra.nombreOferente) &&
               Double.compare(this.precioOfertado, otra.precioOfertado) == 0;
    }

    /**
     * Representación en texto de la oferta.
     *
     * @return Cadena con los datos de la oferta.
     */
    @Override
    public String toString() {
        return "Oferta{Oferente='" + nombreOferente + "', Puntuacion=" + puntuacionOferente +
               ", Precio=$" + String.format("%.2f", precioOfertado) + "}";
    }
}
