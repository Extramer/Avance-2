package ui;

import logica.dominio.Oferta;
import logica.dominio.Subasta;
import logica.gestor.Gestor;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que maneja todas las operaciones relacionadas con ofertas.
 * Incluye creación y listado de ofertas.
 *
 * @author Equipo
 * @version 2.0
 */
public class MenuOfertas {

    private Gestor gestor;
    private Scanner entrada;

    /**
     * Constructor que recibe el gestor de negocio.
     *
     * @param gestor Instancia del Gestor de la plataforma.
     * @param entrada Scanner para entrada del usuario.
     */
    public MenuOfertas(Gestor gestor, Scanner entrada) {
        this.gestor = gestor;
        this.entrada = entrada;
    }

    /**
     * Menú de creación de ofertas.
     */
    public void menuCrearOferta() {
        System.out.println("\n--- Creación de Oferta ---");
        if (gestor.getUsuarioActivo() == null) {
            System.out.println("Debe iniciar sesión para realizar ofertas.");
            return;
        }

        ArrayList<Subasta> subastas = gestor.listarSubastas();
        if (subastas.isEmpty()) {
            System.out.println("No hay subastas disponibles.");
            return;
        }

        System.out.println("Subastas disponibles:");
        for (int i = 0; i < subastas.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + subastas.get(i));
        }
        System.out.print("Seleccione subasta (número): ");
        int idx = InputHandler.leerEnteroSeguro(entrada) - 1;

        System.out.print("Precio ofertado ($): ");
        double precio = InputHandler.leerDoubleSeguro(entrada);

        String resultado = gestor.crearOferta(idx, precio);
        System.out.println(resultado);
    }

    /**
     * Lista todas las ofertas registradas.
     */
    public void menuListadoOfertas() {
        System.out.println("\n--- Listado de Ofertas ---");
        ArrayList<Oferta> lista = gestor.listarOfertas();
        if (lista.isEmpty()) {
            System.out.println("No hay ofertas registradas.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }
}
