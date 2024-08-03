package mx.unam.ciencias.modelado.proyecto2;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import mx.unam.ciencias.modelado.proyecto2.red.EscuchaServidor;

/**
 * <p>Clase para representar bitacoras. Una bitacora escribe mensajes en 
 * la salida estandar.</p>
 */
public class Bitacora implements EscuchaServidor {

    /* La zona horaria de la bitacora. */
    private ZonedDateTime now;
    /* La marca de tiempo de la bitacora. */
    private String ts;

    /**
     * Define el estado inicial de la bitacora.
     */
    public Bitacora() {
        now = ZonedDateTime.now();
        ts = now.format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }

    /**
     * Procesa un mensaje de la bitacora.
     * @param formato una cadena de formato.
     * @param argumentos los argumentos a formatear.
     */
    @Override 
    public void procesaMensaje(String formato, Object ... argumentos) {
        System.err.printf(ts + " " + formato + "\n", argumentos);
    }

}