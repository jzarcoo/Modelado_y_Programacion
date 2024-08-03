package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Enumeracion para los eventos generados por la base de datos al ser
 * modificada.</p>
 */
public enum EventoBaseDeDatos {

    /** La base de datos fue limpiada.  */
    BASE_LIMPIADA,
    /** Un registro fue agregado. */
    REGISTRO_AGREGADO,
    /** Un registro fue eliminado. */
    REGISTRO_ELIMINADO,
    /** Un registro fue modificado. */
    REGISTRO_MODIFICADO;
}
