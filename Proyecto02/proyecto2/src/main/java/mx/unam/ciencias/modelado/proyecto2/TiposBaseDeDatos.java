package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Enumeracion para los tipos de base de datos.</p>
 */
public enum TiposBaseDeDatos {

    /* La base de datos de usuarios. */
    USUARIOS("Base de Datos de Usuarios"),
    /* La base de datos de productos. */
    PRODUCTOS("Base de Datos de Productos"),
    /* La base de datos de criticas. */
    CRITICAS("Base de Datos de Criticas"),
    /* La base de datos de carritos. */
    CARRITOS("Base de Datos de Carritos");

    /* Nombre del TiposBaseDeDatos. */
    private String nombre;

    /**
     * Crea un TiposBaseDeDatos con el nombre dado.
     * @param nombre el nombre del TiposBaseDeDatos.
     */
    private TiposBaseDeDatos(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Descifra un TiposBaseDeDatos a partir de su nombre.
     * @param nombre el nombre del TiposBaseDeDatos.
     * @return el TiposBaseDeDatos correspondiente al nombre o,
     *         <code>null</code> si no lo encuentra.
     */
    public static TiposBaseDeDatos getTiposBaseDeDatos(String nombre) {
        for (TiposBaseDeDatos campo : TiposBaseDeDatos.values())
            if (campo.toString().equals(nombre))
                return campo;
        return null;
    }

    /**
     * Regresa una representacion en cadena del TiposBaseDeDatos.
     * @return una representacion en cadena del TiposBaseDeDatos.
     */
    @Override
    public String toString() {
        return nombre;
    }
}