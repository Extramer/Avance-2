package ui;

import logica.gestor.Gestor;

import java.util.Scanner;

/**
 * Clase controladora del menú principal de la aplicación.
 * Orquesta el flujo de la aplicación y delega a otros menús.
 *
 * @author Equipo
 * @version 2.0
 */
public class MenuPrincipal {

    private Gestor gestor;
    private Scanner entrada;
    
    // Menús especializados
    private MenuModerador menuModerador;
    private MenuUsuarios menuUsuarios;
    private MenuSesion menuSesion;
    private MenuSubastas menuSubastas;
    private MenuOfertas menuOfertas;

    /**
     * Constructor que inicializa el menú principal y todos sus submenús.
     *
     * @param gestor Instancia del Gestor de la plataforma.
     * @param entrada Scanner para entrada del usuario.
     */
    public MenuPrincipal(Gestor gestor, Scanner entrada) {
        this.gestor = gestor;
        this.entrada = entrada;
        
        // Inicializar submenús
        this.menuModerador = new MenuModerador(gestor, entrada);
        this.menuUsuarios = new MenuUsuarios(gestor, entrada);
        this.menuSesion = new MenuSesion(gestor, entrada);
        this.menuSubastas = new MenuSubastas(gestor, entrada);
        this.menuOfertas = new MenuOfertas(gestor, entrada);
    }

    /**
     * Inicia el ciclo principal de la aplicación.
     */
    public void iniciar() {
        System.out.println("============================================");
        System.out.println("  PLATAFORMA DIGITAL DE SUBASTAS ESPECIALIZADAS");
        System.out.println("============================================");

        // Regla de negocio 1 y 2: Verificar si existe moderador al iniciar
        menuModerador.validarModeradorAlInicio();

        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = InputHandler.leerEnteroSeguro(entrada);
            salir = procesarOpcion(opcion);
        }
    }

    /**
     * Muestra el menú principal con todas las opciones disponibles.
     */
    private void mostrarMenuPrincipal() {
        System.out.println("\n============================================");
        String sesion = gestor.getUsuarioActivo() != null
                ? "Sesión: " + gestor.getUsuarioActivo().getNombre()
                : "Sin sesión activa";
        System.out.println("  " + sesion);
        System.out.println("============================================");
        System.out.println("  1. Registro de usuarios");
        System.out.println("  2. Listado de usuarios");
        System.out.println("  3. Creación de subastas");
        System.out.println("  4. Listado de subastas");
        System.out.println("  5. Creación de ofertas");
        System.out.println("  6. Listado de ofertas");
        System.out.println("  7. Inicio de sesión");
        System.out.println("  8. Validar existencia de moderador");
        System.out.println("  9. Cerrar sesión");
        System.out.println("  0. Salir");
        System.out.println("============================================");
        System.out.print("Elija una opción: ");
    }

    /**
     * Procesa la opción seleccionada por el usuario.
     *
     * @param opcion Opción del menú seleccionada.
     * @return true si el usuario desea salir, false en caso contrario.
     */
    private boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1: menuUsuarios.menuRegistroUsuarios();   break;
            case 2: menuUsuarios.menuListadoUsuarios();    break;
            case 3: menuSubastas.menuCrearSubasta();       break;
            case 4: menuSubastas.menuListadoSubastas();    break;
            case 5: menuOfertas.menuCrearOferta();         break;
            case 6: menuOfertas.menuListadoOfertas();      break;
            case 7: menuSesion.menuInicioSesion();         break;
            case 8: menuModerador.menuValidarModerador();  break;
            case 9: menuSesion.menuCerrarSesion();         break;
            case 0:
                System.out.println("Cerrando la plataforma. ¡Hasta luego!");
                return true;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
                return false;
        }
        return false;
    }
}
