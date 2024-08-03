package mx.unam.ciencias.modelado.proyecto2.red;

/**
 * <p>Interfaz para los sujetos de conexiones. Un sujeto de conexiones es un
 * objeto que puede tener conexiones, agregarlas y eliminarlas.</p>
 */
public interface SujetoConexiones {

    /**
     * Agrega una conexion al sujeto de conexiones.
     * @param conexion la conexion a agregar.
     */
    public void conecta(Conexion conexion);

    /**
     * Elimina una conexion del sujeto de conexiones.
     * @param conexion la conexion a eliminar.
     */
    public void desconecta(Conexion conexion);

}
