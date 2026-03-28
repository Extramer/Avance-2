package logica.dominio;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;

/**
 * Representa una subasta activa en la plataforma.
 * Una subasta puede incluir uno o más objetos y recibe ofertas de coleccionistas.
 *
 * @author Equipo
 * @version 2.0
 */
public class Subasta {

    /** Fecha y hora en que vence la subasta. */
    private LocalDateTime fechaVencimiento;

    /** Usuario que creó la subasta (vendedor o coleccionista). */
    private Usuario creador;

    /** Precio mínimo de aceptación para la subasta. */
    private double precioMinimo;

    /** Lista de objetos que se subastan. */
    private ArrayList<Objeto> objetos;

    /** Lista de ofertas recibidas en esta subasta. */
    private ArrayList<Oferta> ofertas;

    /**
     * Estado de la subasta.
     * Valores posibles: "Activa", "Vencida", "Adjudicada".
     */
    private String estado;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    /**
     * Constructor por defecto.
     */
    public Subasta() {
        this.fechaVencimiento = LocalDateTime.now().plusDays(7);
        this.creador = null;
        this.precioMinimo = 0.0;
        this.objetos = new ArrayList<>();
        this.ofertas = new ArrayList<>();
        this.estado = "Activa";
    }

    /**
     * Constructor general que asigna todos los atributos de la subasta.
     *
     * @param fechaVencimiento Fecha y hora de vencimiento.
     * @param creador          Usuario creador de la subasta.
     * @param precioMinimo     Precio mínimo de aceptación.
     * @param estado           Estado inicial de la subasta.
     */
    public Subasta(LocalDateTime fechaVencimiento, Usuario creador,
                   double precioMinimo, String estado) {
        this.fechaVencimiento = fechaVencimiento;
        this.creador = creador;
        this.precioMinimo = precioMinimo;
        this.objetos = new ArrayList<>();
        this.ofertas = new ArrayList<>();
        this.estado = estado;
    }

    // -------------------------------------------------------------------------
    // Métodos de negocio
    // -------------------------------------------------------------------------

    /**
     * Calcula el tiempo restante para que venza la subasta.
     *
     * @return Cadena con días, horas, minutos y segundos restantes, o "Vencida" si ya expiró.
     */
    public String calcularTiempoRestante() {
        LocalDateTime ahora = LocalDateTime.now();
        if (ahora.isAfter(fechaVencimiento)) {
            return "Vencida";
        }
        Duration duracion = Duration.between(ahora, fechaVencimiento);
        long dias    = duracion.toDays();
        long horas   = duracion.toHours()   % 24;
        long minutos = duracion.toMinutes() % 60;
        long segundos= duracion.getSeconds()% 60;
        return dias + "d " + horas + "h " + minutos + "m " + segundos + "s";
    }

    /**
     * Agrega un objeto a la subasta.
     *
     * @param objeto Objeto a agregar.
     */
    public void agregarObjeto(Objeto objeto) {
        if (!objetos.contains(objeto)) {
            objetos.add(objeto);
        }
    }

    /**
     * Registra una oferta en la subasta.
     *
     * @param oferta Oferta a registrar.
     */
    public void agregarOferta(Oferta oferta) {
        ofertas.add(oferta);
    }

    /**
     * Obtiene la mejor oferta (la más alta) registrada en la subasta.
     *
     * @return La oferta con el precio más alto, o {@code null} si no hay ofertas.
     */
    public Oferta getMejorOferta() {
        if (ofertas.isEmpty()) return null;
        Oferta mejor = ofertas.get(0);
        for (Oferta o : ofertas) {
            if (o.getPrecioOfertado() > mejor.getPrecioOfertado()) {
                mejor = o;
            }
        }
        return mejor;
    }

    /**
     * Obtiene la puntuación del creador de la subasta.
     *
     * @return puntuación del creador, o 0.0 si el creador no es vendedor ni coleccionista.
     */
    public double getPuntuacionCreador() {
        if (creador instanceof Vendedor) {
            return ((Vendedor) creador).getPuntuacion();
        } else if (creador instanceof Coleccionista) {
            return ((Coleccionista) creador).getPuntuacion();
        }
        return 0.0;
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    /**
     * Obtiene la fecha de vencimiento.
     * @return fecha de vencimiento.
     */
    public LocalDateTime getFechaVencimiento() { return fechaVencimiento; }

    /**
     * Asigna la fecha de vencimiento.
     * @param fechaVencimiento fecha a asignar.
     */
    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Obtiene el usuario creador de la subasta.
     * @return creador.
     */
    public Usuario getCreador() { return creador; }

    /**
     * Asigna el usuario creador de la subasta.
     * @param creador usuario a asignar.
     */
    public void setCreador(Usuario creador) { this.creador = creador; }

    /**
     * Obtiene el precio mínimo de la subasta.
     * @return precio mínimo.
     */
    public double getPrecioMinimo() { return precioMinimo; }

    /**
     * Asigna el precio mínimo de la subasta.
     * @param precioMinimo precio a asignar.
     */
    public void setPrecioMinimo(double precioMinimo) { this.precioMinimo = precioMinimo; }

    /**
     * Obtiene la lista de objetos subastados.
     * @return lista de objetos.
     */
    public ArrayList<Objeto> getObjetos() { return objetos; }

    /**
     * Asigna la lista de objetos subastados.
     * @param objetos lista a asignar.
     */
    public void setObjetos(ArrayList<Objeto> objetos) { this.objetos = objetos; }

    /**
     * Obtiene la lista de ofertas de la subasta.
     * @return lista de ofertas.
     */
    public ArrayList<Oferta> getOfertas() { return ofertas; }

    /**
     * Asigna la lista de ofertas de la subasta.
     * @param ofertas lista a asignar.
     */
    public void setOfertas(ArrayList<Oferta> ofertas) { this.ofertas = ofertas; }

    /**
     * Obtiene el estado de la subasta.
     * @return estado.
     */
    public String getEstado() { return estado; }

    /**
     * Asigna el estado de la subasta.
     * @param estado estado a asignar.
     */
    public void setEstado(String estado) { this.estado = estado; }

    // -------------------------------------------------------------------------
    // equals, toString
    // -------------------------------------------------------------------------

    /**
     * Compara dos subastas por creador y fecha de vencimiento.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} si son iguales.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Subasta)) return false;
        Subasta otra = (Subasta) obj;
        return this.fechaVencimiento.equals(otra.fechaVencimiento) &&
               this.creador.equals(otra.creador);
    }

    /**
     * Representación en texto de la subasta.
     *
     * @return Cadena con los datos de la subasta.
     */
    @Override
    public String toString() {
        return "Subasta{Creador='" + (creador != null ? creador.getNombre() : "N/A") +
               "', PuntuacionCreador=" + getPuntuacionCreador() +
               ", PrecioMinimo=$" + String.format("%.2f", precioMinimo) +
               ", Estado='" + estado + "', TiempoRestante=" + calcularTiempoRestante() +
               ", Objetos=" + objetos.size() + ", Ofertas=" + ofertas.size() + "}";
    }
}
