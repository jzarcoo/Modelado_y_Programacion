package mx.unam.ciencias.modelado.proyecto2.igu;

/**
 * <p>Interfaz funcional para objetos que pueden verificar texto.</p>
 */
@FunctionalInterface
public interface Verificador {

    /**
     * Verifica la cadena de texto.
     * @param texto la cadena de texto.
     * @return <code>true</code> si la cadena de texto es valida,
     *         <code>false</code> en otro caso.
     */
    public boolean verifica(String texto);
}
