package ui;

import logica.dominio.Objeto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Clase auxiliar para manejo de entrada de datos desde la consola.
 * Centraliza todos los métodos de lectura de entrada del usuario.
 *
 * @author Equipo
 * @version 2.0
 */
public class InputHandler {

    private static Scanner entrada;

    /** Formato de fecha para entrada/salida. */
    private static final DateTimeFormatter FMT_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /** Formato de fecha y hora para subastas. */
    private static final DateTimeFormatter FMT_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Inicializa el scanner compartido.
     */
    public static void inicializar() {
        if (entrada == null) {
            entrada = new Scanner(System.in);
        }
    }

    /**
     * Lee un objeto de valor desde la consola.
     *
     * @param entrada Scanner para lectura de entrada.
     * @return Objeto creado con los datos ingresados.
     */
    public static Objeto leerObjeto(Scanner entrada) {
        System.out.print("  Nombre del objeto: ");
        String nombre = entrada.nextLine().trim();
        System.out.print("  Descripción: ");
        String desc = entrada.nextLine().trim();
        System.out.println("  Estado: 1. Nuevo  2. Usado  3. Antiguo sin abrir");
        System.out.print("  Opción: ");
        int est = leerEnteroSeguro(entrada);
        String estado = est == 2 ? "Usado" : est == 3 ? "Antiguo sin abrir" : "Nuevo";
        LocalDate fechaCompra = leerFecha("  Fecha de compra (dd/MM/yyyy): ", entrada);
        return new Objeto(nombre, desc, estado, fechaCompra);
    }

    /**
     * Lee una fecha desde la consola con formato dd/MM/yyyy.
     *
     * @param prompt Mensaje a mostrar al usuario.
     * @param entrada Scanner para lectura de entrada.
     * @return Fecha leída.
     */
    public static LocalDate leerFecha(String prompt, Scanner entrada) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(entrada.nextLine().trim(), FMT_FECHA);
            } catch (DateTimeParseException e) {
                System.out.println("Formato incorrecto. Use dd/MM/yyyy.");
            }
        }
    }

    /**
     * Lee una fecha y hora desde la consola con formato dd/MM/yyyy HH:mm.
     *
     * @param prompt Mensaje a mostrar al usuario.
     * @param entrada Scanner para lectura de entrada.
     * @return Fecha y hora leída.
     */
    public static LocalDateTime leerFechaHora(String prompt, Scanner entrada) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDateTime.parse(entrada.nextLine().trim(), FMT_FECHA_HORA);
            } catch (DateTimeParseException e) {
                System.out.println("Formato incorrecto. Use dd/MM/yyyy HH:mm.");
            }
        }
    }

    /**
     * Lee un número entero de forma segura, sin lanzar excepciones.
     *
     * @param entrada Scanner para lectura de entrada.
     * @return Entero leído, o -1 si la entrada es inválida.
     */
    public static int leerEnteroSeguro(Scanner entrada) {
        try {
            int val = Integer.parseInt(entrada.nextLine().trim());
            return val;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Lee un número decimal de forma segura.
     *
     * @param entrada Scanner para lectura de entrada.
     * @return Double leído, o 0.0 si la entrada es inválida.
     */
    public static double leerDoubleSeguro(Scanner entrada) {
        try {
            return Double.parseDouble(entrada.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Se usará 0.0");
            return 0.0;
        }
    }
}
