package ui;

import logica.dominio.Coleccionista;
import logica.dominio.Objeto;
import logica.dominio.Subasta;
import logica.dominio.Usuario;
import logica.gestor.Gestor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que maneja todas las operaciones relacionadas con subastas.
 * Incluye creación y listado de subastas.
 *
 * @author Equipo
 * @version 2.0
 */
public class MenuSubastas {

    private Gestor gestor;
    private Scanner entrada;

    /**
     * Constructor que recibe el gestor de negocio.
     *
     * @param gestor Instancia del Gestor de la plataforma.
     * @param entrada Scanner para entrada del usuario.
     */
    public MenuSubastas(Gestor gestor, Scanner entrada) {
        this.gestor = gestor;
        this.entrada = entrada;
    }

    /**
     * Menú de creación de subastas.
     */
    public void menuCrearSubasta() {
        System.out.println("\n--- Creación de Subasta ---");
        if (gestor.getUsuarioActivo() == null) {
            System.out.println("Debe iniciar sesión para crear subastas.");
            return;
        }

        LocalDateTime fechaVenc = InputHandler.leerFechaHora("Fecha de vencimiento (dd/MM/yyyy HH:mm): ", entrada);
        System.out.print("Precio mínimo ($): ");
        double precioMin = InputHandler.leerDoubleSeguro(entrada);

        // Para simplificar el demo, si el usuario activo es coleccionista
        // se le permite ingresar objetos que se agregarán a su colección
        ArrayList<Objeto> objetos = new ArrayList<>();

        Usuario activo = gestor.getUsuarioActivo();
        if (activo instanceof Coleccionista) {
            Coleccionista col = (Coleccionista) activo;
            if (col.getColeccion().isEmpty()) {
                System.out.println("Su colección está vacía. Agregue un objeto ahora:");
                Objeto obj = InputHandler.leerObjeto(entrada);
                col.agregarObjeto(obj);
            }
            System.out.println("Objetos en su colección:");
            ArrayList<Objeto> coleccion = col.getColeccion();
            for (int i = 0; i < coleccion.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + coleccion.get(i).getNombre());
            }
            System.out.print("Seleccione objeto (número): ");
            int idx = InputHandler.leerEnteroSeguro(entrada) - 1;
            if (idx >= 0 && idx < coleccion.size()) {
                objetos.add(coleccion.get(idx));
            } else {
                System.out.println("Selección inválida.");
                return;
            }
        } else {
            // Vendedor ingresa el objeto directamente
            System.out.println("Ingrese el objeto a subastar:");
            objetos.add(InputHandler.leerObjeto(entrada));
        }

        String resultado = gestor.crearSubasta(fechaVenc, precioMin, objetos);
        System.out.println(resultado);
    }

    /**
     * Lista todas las subastas registradas.
     */
    public void menuListadoSubastas() {
        System.out.println("\n--- Listado de Subastas ---");
        ArrayList<Subasta> lista = gestor.listarSubastas();
        if (lista.isEmpty()) {
            System.out.println("No hay subastas registradas.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }
}
