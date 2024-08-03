package mx.unam.ciencias.modelado.proyecto2.red;

/**
 * <p>Interfaz para los escuchas de un servidor.</p>
 */
@FunctionalInterface
public interface EscuchaServidor {

    /**
     * Los escuchas de servidor unicamente procesan mensajes, utilizando el
     * formato de {@link java.io.PrintWriter#printf(String,Object...)}.
     * @param formato una cadena de formato.
     * @param argumentos los argumentos a formatear.
     */
    public void procesaMensaje(String formato, Object ... argumentos);
}
