package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Enumeracion para los campos de una {@link Critica}.</p>
 */
public enum CampoCritica {

    /** El ID de la Critica. */
    ID("ID"),
    /** El ID del Vendedor al que referencia. */
    ID_VENDEDOR("ID Vendedor"),
    /* El ID del Cliente que la publico. */
    ID_CLIENTE("ID Cliente"),
    /* La puntuacion de la Critica. */
    PUNTUACION("Puntuacion"),
    /* El comentario de la Critica. */
    COMENTARIO("Comentario"),
    /* El conteo de reportes de la Critica */
    REPORTE("Reporte");

    /* Nombre del CampoCritica. */
    private String nombre;

    /**
     * Crea un CampoCritica con el nombre dado.
     * @param nombre el nombre del CampoCritica.
     */
    private CampoCritica(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Descifra un CampoCritica a partir de su nombre.
     * @param nombre el nombre del CampoCritica.
     * @return el CampoCritica correspondiente al nombre o,
     *         <code>null</code> si no lo encuentra.
     */
    public static CampoCritica getCampoCritica(String nombre) {
        for (CampoCritica campo : CampoCritica.values())
            if (campo.toString().equals(nombre))
                return campo;
        return null;
    }

    /**
     * Regresa una representacion en cadena del CampoCritica.
     * @return una representacion en cadena del CampoCritica.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
