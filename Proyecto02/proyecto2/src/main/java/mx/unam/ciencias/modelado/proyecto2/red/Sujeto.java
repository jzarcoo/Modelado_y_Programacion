package mx.unam.ciencias.modelado.proyecto2.red;

/**
 * <p>Interfaz para los sujetos del servidor.</p>
 */
public interface Sujeto {
    
    /**
     * Agrega un escucha al sujeto del servidor.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscucha(EscuchaServidor escucha);

    /** 
     * Elimina un escucha del sujeto del servidor.
     * @param escucha el escucha a eliminar.
     */
    public void eliminaEscucha(EscuchaServidor escucha);

    /**
     * Procesa los mensajes de todos los escuchas.
     * @param formato el formato del mensaje.
     * @param argumentos los argumentos del mensaje.
     */
    public void anotaMensaje(String formato, Object ... argumentos);
}