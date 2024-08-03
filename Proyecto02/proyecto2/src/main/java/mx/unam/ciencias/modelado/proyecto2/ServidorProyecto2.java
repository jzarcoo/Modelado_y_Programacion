package mx.unam.ciencias.modelado.proyecto2;

import java.io.IOException;
import mx.unam.ciencias.modelado.proyecto2.red.ServidorBaseDeDatos;

/**
 * <p>Servidor Proyecto 2.</p>
 */
public class ServidorProyecto2 {

    /**
     * Metodo principal.
     * @param args argumentos de la linea de comandos.
     */
    public static void main(String[] args) {
        int puerto = 1234;
        String criticas = "criticas.txt";
        String productos = "productos.txt";
        String usuarios = "usuarios.txt";
        String carritos = "carritos.txt";
        Bitacora bitacora = new Bitacora();
        try {
            ServidorBaseDeDatos servidor = new ServidorBaseDeDatos(puerto, criticas, productos, usuarios, carritos);
            servidor.agregaEscucha(bitacora);
            servidor.carga();
            servidor.sirve();
        } catch (IOException ioe) {
            bitacora.procesaMensaje("Error al crear el servidor.");
        }
    }
}
