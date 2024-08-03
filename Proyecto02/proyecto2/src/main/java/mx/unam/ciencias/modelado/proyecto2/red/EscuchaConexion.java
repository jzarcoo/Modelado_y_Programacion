package mx.unam.ciencias.modelado.proyecto2.red;

/**
 * <p>Escucha para eventos de conexiones.</p>
 */
@FunctionalInterface
public interface EscuchaConexion {

    /**
     * Notifica de un mensaje que se recibio en la comunicacion entre el cliente
     * y el servidor.
     * @param conexion la conexion que recibio el mensaje.
     * @param mensaje el mensaje recibido.
     */
    public void mensajeRecibido(Conexion conexion, Mensaje mensaje);
}
