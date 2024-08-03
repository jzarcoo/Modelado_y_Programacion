package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Interfaz para registros. Los registros deben de poder seriarse a y
 * deseriarse de una linea de texto. Tambien deben poder determinar si sus
 * campos casan valores arbitrarios y actualizarse con los valores de otro
 * registro.</p>
 *
 * @param <R> El tipo de los registros, para poder actualizar registros del
 * mismo tipo.
 * @param <C> El tipo de los campos de los registros, que debe ser una
 * enumeracion {@link Enum}.
 */
public interface Registro<R extends Registro<R, C>, C extends Enum> {

    /**
     * Regresa el registro seriado en una linea de texto. La linea de texto
     * que este metodo regresa debe ser aceptada por el metodo {@link
     * Registro#deseria}.
     * @return la seriacion del registro en una linea de texto.
     */
    public String seria();

    /**
     * Deseria una linea de texto en las propiedades del registro. La
     * seriacion producida por el metodo {@link Registro#seria} debe
     * ser aceptada por este metodo.
     * @param linea la linea a deseriar.
     * @throws ExcepcionLineaInvalida si la linea recibida es nula, vacia o no
     *         es una seriacion valida de un registro.
     */
    public void deseria(String linea);

    /**
     * Actualiza los valores del registro con los del registro recibido.
     * @param registro el registro con el cual actualizar los valores.
     */
    public void actualiza(R registro);

    /**
     * Nos dice si el registro casa el valor dado en el campo especificado.
     * @param campo el campo que hay que casar.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el campo especificado del registro casa el
     *         valor dado, <code>false</code> en otro caso.
     */
    public boolean casa(C campo, Object valor);
}