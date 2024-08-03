package src.igu;

/**
 * <p>Clase para entradas verificables.</p>
 */
public class EntradaVerificable {

    /* Verificador de la entrada. */
    private Verificador verificador;
    /* Texto de la entrada. */
    private String texto;

    /**
     * Define el estado inicial de una entrada verificable.
     */
    public EntradaVerificable() {}

    /**
     * Define el verificador de la entrada.
     * @param verificador el nuevo verificador de la entrada.
     */
    public void setVerificador(Verificador verificador) {
        this.verificador = verificador;
    }

    /**
     * Nos dice si la entrada es valida.
     * @return <code>true</code> si la entrada es valida, <code>false</code> en
     *         otro caso.
     */
    public boolean esValida() {
        return verificador.verifica(texto);
    }

    /**
     * Define el texto de la entrada.
     * @param texto el nuevo texto de la entrada.
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * Regresa el texto de la entrada.
     * @return texto de la entrada.
     */
    public String getTexto() {
        return texto;
    }
}
