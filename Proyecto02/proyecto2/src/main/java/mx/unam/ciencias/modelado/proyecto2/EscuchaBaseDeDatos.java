package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Interfaz para escuchas de bases de datos.</p>
 */
@FunctionalInterface
public interface EscuchaBaseDeDatos<R extends Registro<R, ?>> {

    /**
     * Nos dice si hubo algun cambio en la base de datos.
     * @param evento el tipo de cambio.
     * @param registro1 el registro afectado, o <code>null</code> si la base de
     *                  datos fue limpiada.
     * @param registro2 el registro donde obtener la informacion de modificacion
     *                   del registro, <code>null</code> en otro caso.
     */
    public void baseDeDatosModificada(EventoBaseDeDatos evento, R registro1, R registro2);
}
