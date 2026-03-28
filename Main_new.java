package ui;

import logica.gestor.Gestor;

import java.util.Scanner;

/**
 * Clase principal con el punto de entrada de la plataforma de subastas.
 * Delega toda la lógica a MenuPrincipal.
 *
 * @author Equipo
 * @version 2.0
 */
public class Main_new {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        Scanner entrada = new Scanner(System.in);
        
        MenuPrincipal menu = new MenuPrincipal(gestor, entrada);
        menu.iniciar();
        
        entrada.close();
    }
}
