package ui;

import logica.gestor.Gestor;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Clase que maneja todas las operaciones relacionadas con moderadores.
 * Incluye validación inicial, registro y verificación del moderador.
 *
 * @author Equipo
 * @version 2.0
 */
public class MenuModerador {

    private Gestor gestor;
    private Scanner entrada;

    /**
     * Constructor que recibe el gestor de negocio.
     *
     * @param gestor Instancia del Gestor de la plataforma.
     * @param entrada Scanner para entrada del usuario.
     */
    public MenuModerador(Gestor gestor, Scanner entrada) {
        this.gestor = gestor;
        this.entrada = entrada;
    }

    /**
     * Verifica si hay un moderador registrado. Si no lo hay, solicita su registro.
     */
    public void validarModeradorAlInicio() {
        if (!gestor.existeModerador()) {
            System.out.println("\n[SISTEMA] No hay un moderador registrado.");
            System.out.println("Por favor registre el moderador del sistema:");
            registrarModeradorConsola();
        } else {
            System.out.println("[SISTEMA] Moderador verificado.");
        }
    }

    /**
     * Menú de validación de moderador (opción 8 del menú principal).
     */
    public void menuValidarModerador() {
        System.out.println("\n--- Validación de Moderador ---");
        if (gestor.existeModerador()) {
            System.out.println("Estado: Existe un moderador registrado.");
            System.out.println(gestor.getModerador());
        } else {
            System.out.println("Estado: No hay moderador registrado.");
            System.out.println("Proceda a registrar el moderador:");
            registrarModeradorConsola();
        }
    }

    /**
     * Solicita datos por consola y registra el moderador a través del Gestor.
     */
    private void registrarModeradorConsola() {
        System.out.print("Nombre completo: ");
        String nombre = entrada.nextLine().trim();
        System.out.print("Identificación: ");
        String id = entrada.nextLine().trim();
        LocalDate fechaNac = InputHandler.leerFecha("Fecha de nacimiento (dd/MM/yyyy): ", entrada);
        System.out.print("Contraseña: ");
        String pass = entrada.nextLine().trim();
        System.out.print("Correo electrónico: ");
        String correo = entrada.nextLine().trim();

        String resultado = gestor.registrarModerador(nombre, id, fechaNac, pass, correo);
        System.out.println(resultado);
    }
}
