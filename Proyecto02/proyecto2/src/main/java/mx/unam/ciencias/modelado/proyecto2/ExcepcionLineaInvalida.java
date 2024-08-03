package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Clase para excepciones de lineas invalidas.<p>
 */
public class ExcepcionLineaInvalida extends IllegalArgumentException {

    /**
     * Constructor vacio.
     */
    public ExcepcionLineaInvalida() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que vera el usuario cuando ocurra la excepcion.
     */
    public ExcepcionLineaInvalida(String mensaje) {
        super(mensaje);
    }
}
