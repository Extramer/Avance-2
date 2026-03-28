package ui;

import logica.gestor.Gestor;

import java.util.Scanner;

/**
 * Clase que maneja las operaciones de inicio y cierre de sesión.
 *
 * @author Equipo
 * @version 2.0
 */
public class MenuSesion {

    private Gestor gestor;
    private Scanner entrada;

    /**
     * Constructor que recibe el gestor de negocio.
     *
     * @param gestor Instancia del Gestor de la plataforma.
     * @param entrada Scanner para entrada del usuario.
     */
    public MenuSesion(Gestor gestor, Scanner entrada) {
        this.gestor = gestor;
        this.entrada = entrada;
    }

    /**
     * Menú de inicio de sesión.
     */
    public void menuInicioSesion() {
        if (gestor.getUsuarioActivo() != null) {
            System.out.println("Ya tiene una sesión activa como: " +
                               gestor.getUsuarioActivo().getNombre());
            System.out.println("Cierre la sesión actual (opción 9) antes de iniciar una nueva.");
            return;
        }
        System.out.println("\n--- Inicio de Sesión ---");
        System.out.print("Identificación: ");
        String id = entrada.nextLine().trim();
        System.out.print("Contraseña: ");
        String pass = entrada.nextLine().trim();

        String resultado = gestor.iniciarSesion(id, pass);
        System.out.println(resultado);
    }

    /**
     * Cierra la sesión activa.
     */
    public void menuCerrarSesion() {
        System.out.println(gestor.cerrarSesion());
    }
}
