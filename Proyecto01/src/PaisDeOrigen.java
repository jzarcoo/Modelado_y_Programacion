package src;

/*
 * <p>Enumeracion para representar los paises de origen de los clientes.</p>
 */
public enum PaisDeOrigen {

    /* Estados Unidos. */
    ESTADOS_UNIDOS(1),
    /* Espana. */
    ESPANA(2),
    /* Mexico. */
    MEXICO(3);

    /* Opciones. */
    private int opcion; 

    /**
     * Constructor de la enumeracion.
     * @param opcion el valor numerico asociado al pais de origen.
     */
    PaisDeOrigen(int opcion) {
        this.opcion = opcion;
    }

    /**
     * Metodo para obtener el valor numerico del pais de origen.
     * @return el valor numerico del pais de origen.
     */
    public int getOpcion() {
        return opcion;
    }

    /**
     * Metodo para obtener el pais de origen en forma de cadena.
     * @return el pais de origen como cadena de texto.
     */
    public String toString() {
        switch (opcion) {
            case 1:
                return "Estados Unidos";
            case 2:
                return "Espana";
            case 3:
                return "Mexico";
            default:
                return "Opcion invalida";
        }
    }
}

