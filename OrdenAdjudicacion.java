package logica.dominio;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representa la orden de adjudicación generada cuando un ganador acepta
 * la asignación de una subasta.
 *
 * @author Equipo
 * @version 2.0
 */
public class OrdenAdjudicacion {

    /** Nombre completo del ganador de la subasta. */
    private String nombreGanador;

    /** Fecha en que se generó la orden. */
    private LocalDate fechaOrden;

    /** Lista de objetos adjudicados al ganador. */
    private ArrayList<Objeto> objetosAdjudicados;

    /** Precio total de la orden (precio de la oferta ganadora). */
    private double precioTotal;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    /**
     * Constructor por defecto.
     */
    public OrdenAdjudicacion() {
        this.nombreGanador = "";
        this.fechaOrden = LocalDate.now();
        this.objetosAdjudicados = new ArrayList<>();
        this.precioTotal = 0.0;
    }

    /**
     * Constructor general que asigna todos los atributos de la orden.
     *
     * @param nombreGanador       Nombre del ganador.
     * @param fechaOrden          Fecha de la orden.
     * @param objetosAdjudicados  Lista de objetos adjudicados.
     * @param precioTotal         Precio total de la orden.
     */
    public OrdenAdjudicacion(String nombreGanador, LocalDate fechaOrden,
                              ArrayList<Objeto> objetosAdjudicados, double precioTotal) {
        this.nombreGanador = nombreGanador;
        this.fechaOrden = fechaOrden;
        this.objetosAdjudicados = objetosAdjudicados;
        this.precioTotal = precioTotal;
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene el nombre del ganador.
     * @return nombre del ganador.
     */
    public String getNombreGanador() { return nombreGanador; }

    /**
     * Asigna el nombre del ganador.
     * @param nombreGanador nombre a asignar.
     */
    public void setNombreGanador(String nombreGanador) { this.nombreGanador = nombreGanador; }

    /**
     * Obtiene la fecha de la orden.
     * @return fecha de la orden.
     */
    public LocalDate getFechaOrden() { return fechaOrden; }

    /**
     * Asigna la fecha de la orden.
     * @param fechaOrden fecha a asignar.
     */
    public void setFechaOrden(LocalDate fechaOrden) { this.fechaOrden = fechaOrden; }

    /**
     * Obtiene la lista de objetos adjudicados.
     * @return lista de objetos.
     */
    public ArrayList<Objeto> getObjetosAdjudicados() { return objetosAdjudicados; }

    /**
     * Asigna la lista de objetos adjudicados.
     * @param objetosAdjudicados lista a asignar.
     */
    public void setObjetosAdjudicados(ArrayList<Objeto> objetosAdjudicados) {
        this.objetosAdjudicados = objetosAdjudicados;
    }

    /**
     * Obtiene el precio total de la orden.
     * @return precio total.
     */
    public double getPrecioTotal() { return precioTotal; }

    /**
     * Asigna el precio total de la orden.
     * @param precioTotal precio a asignar.
     */
    public void setPrecioTotal(double precioTotal) { this.precioTotal = precioTotal; }

    // -------------------------------------------------------------------------
    // equals, toString
    // -------------------------------------------------------------------------

    /**
     * Compara dos órdenes por ganador y fecha.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} si son iguales.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof OrdenAdjudicacion)) return false;
        OrdenAdjudicacion otra = (OrdenAdjudicacion) obj;
        return this.nombreGanador.equals(otra.nombreGanador) &&
               this.fechaOrden.equals(otra.fechaOrden);
    }

    /**
     * Representación en texto de la orden de adjudicación.
     *
     * @return Cadena con los datos de la orden.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OrdenAdjudicacion{Ganador='").append(nombreGanador)
          .append("', Fecha=").append(fechaOrden)
          .append(", PrecioTotal=$").append(String.format("%.2f", precioTotal))
          .append(", Objetos=[");
        for (int i = 0; i < objetosAdjudicados.size(); i++) {
            sb.append(objetosAdjudicados.get(i).getNombre());
            if (i < objetosAdjudicados.size() - 1) sb.append(", ");
        }
        sb.append("]}");
        return sb.toString();
    }
}
