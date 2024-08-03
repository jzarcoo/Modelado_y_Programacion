package src;

/**
 * <p>Enumeracion para representar los departamentos de la tienda.</p>
 */
public enum DepartamentoTienda {

    /* Departamento general. */
    GENERAL("General"),
    /* Departamento de electronica. */
    ELECTRONICA("Electronica"),
    /* Departamento de electrodomesticos. */
    ELECTRODOMESTICOS("Electrodomesticos"),
    /* Departamento de alimentos. */
    ALIMENTOS("Alimentos"),
    /* Departamento de limpieza. */
    LIMPIEZA("Limpieza");

    /* Nombre del departamento. */
    private String nombre;

    /**
     * Define el estado inicial del departamento.
     * @param nombre nombre del departamento.
     */
    private DepartamentoTienda(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Descifra un departamento a partir de su nombre.
     * @param nombre nombre del departamento.
     * @return el departamento correspondiente al nombre.
     */
    public static DepartamentoTienda getDepartamento(String nombre) {
        for (DepartamentoTienda departamento : DepartamentoTienda.values()) 
            if (departamento.nombre.equals(nombre)) 
                return departamento;
        return null;
    }

    /**
     * Regresa una representacion en cadena del departamento.
     * @return representacion en cadena del departamento.
     */
    @Override
    public String toString() {
        return nombre;
    }
}