package ui;

import logica.dominio.Coleccionista;
import logica.dominio.Usuario;
import logica.gestor.Gestor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que maneja todas las operaciones relacionadas con usuarios.
 * Incluye registro, autenticación y listado de usuarios.
 *
 * @author Equipo
 * @version 2.0
 */
public class MenuUsuarios {

    private Gestor gestor;
    private Scanner entrada;

    /**
     * Constructor que recibe el gestor de negocio.
     *
     * @param gestor Instancia del Gestor de la plataforma.
     * @param entrada Scanner para entrada del usuario.
     */
    public MenuUsuarios(Gestor gestor, Scanner entrada) {
        this.gestor = gestor;
        this.entrada = entrada;
    }

    /**
     * Menú de registro de usuarios.
     */
    public void menuRegistroUsuarios() {
        System.out.println("\n--- Registro de Usuarios ---");
        System.out.println("  1. Registrar Coleccionista");
        System.out.println("  2. Registrar Vendedor");
        System.out.println("  0. Volver");
        System.out.print("Opción: ");
        int op = InputHandler.leerEnteroSeguro(entrada);
        switch (op) {
            case 1: registrarColeccionista(); break;
            case 2: registrarVendedor();      break;
            case 0: break;
            default: System.out.println("Opción inválida.");
        }
    }

    /**
     * Solicita datos y registra un coleccionista.
     */
    private void registrarColeccionista() {
        System.out.println("\n-- Nuevo Coleccionista --");
        System.out.print("Nombre completo: ");
        String nombre = entrada.nextLine().trim();
        System.out.print("Identificación: ");
        String id = entrada.nextLine().trim();
        LocalDate fechaNac = InputHandler.leerFecha("Fecha de nacimiento (dd/MM/yyyy): ", entrada);
        System.out.print("Contraseña: ");
        String pass = entrada.nextLine().trim();
        System.out.print("Correo electrónico: ");
        String correo = entrada.nextLine().trim();
        System.out.print("Dirección: ");
        String dir = entrada.nextLine().trim();

        String resultado = gestor.registrarColeccionista(nombre, id, fechaNac, pass, correo, dir);
        System.out.println(resultado);
    }

    /**
     * Solicita datos y registra un vendedor.
     */
    private void registrarVendedor() {
        System.out.println("\n-- Nuevo Vendedor --");
        System.out.print("Nombre completo: ");
        String nombre = entrada.nextLine().trim();
        System.out.print("Identificación: ");
        String id = entrada.nextLine().trim();
        LocalDate fechaNac = InputHandler.leerFecha("Fecha de nacimiento (dd/MM/yyyy): ", entrada);
        System.out.print("Contraseña: ");
        String pass = entrada.nextLine().trim();
        System.out.print("Correo electrónico: ");
        String correo = entrada.nextLine().trim();
        System.out.print("Dirección: ");
        String dir = entrada.nextLine().trim();

        String resultado = gestor.registrarVendedor(nombre, id, fechaNac, pass, correo, dir);
        System.out.println(resultado);
    }

    /**
     * Lista todos los usuarios registrados en el sistema.
     */
    public void menuListadoUsuarios() {
        System.out.println("\n--- Listado de Usuarios ---");
        ArrayList<Usuario> lista = gestor.listarUsuarios();
        if (lista.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }
}
