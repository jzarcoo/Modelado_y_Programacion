package src.igu;

import java.util.Scanner;

/**
 * <p>Clase para interactuar con la tienda como un cliente.</p>
 */
public class InicioSesion {

    /**
     * Regresa el nombre de usuario del input del cliente.
     * @param sc el scanner.
     * @return nombre de usuario del input del cliente.
     */
    public static String getNombreUsuario(Scanner sc) {
        System.out.println("Introduce tu nombre de usuario:");
        return sc.next();
    }

    /**
     * Regresa la contrasena del input del cliente.
     * @param sc el scanner.
     * @return contrasena del input del cliente.
     */
    public static String getContrasena(Scanner sc) {
        System.out.println("Introduce tu contrasena:");
        return sc.next();
    }

    /**
     * Muestra un mensaje de error cuando las credenciales son incorrectas.
     */
    public static void mensajeError() {
        System.out.println("-------------------------------------");
        System.out.println("Credenciales invalidas");
        System.out.println("=====================================\n");
    }

    /**
     * Muestra un mensaje de error cuando el nombre de usuario no es valido.
     */
    public static void mensajeErrorNombreUsuario() {
        System.err.println("Error al leer el nombre de usuario");
    }

    /**
     * Muestra un mensaje de error cuando la contrasena no es valida.
     */
    public static void mensajeErrorContrasena() {
        System.err.println("Error al leer la contrasena");
    }

    /**
     * Muestra un mensaje para salir.
     */
    public static void mensajeOpcionSalida() {
        System.out.println("\n=====================================\n");
        System.out.println("Inicio de sesion en CheemsMart\n");
        System.out.println("* Presiona '0' para salir\n");
    }

    /**
     * Muestra un mensaje para salir.
     */
    public static void mensajeSalida() {
        System.out.println("\n=====================================\n");
        System.out.println("Saliendo...\n ");
    }
    
}