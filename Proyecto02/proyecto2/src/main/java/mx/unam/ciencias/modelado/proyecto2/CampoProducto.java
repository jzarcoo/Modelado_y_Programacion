package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Enumeracion para los campos de una {@link Critica}.</p>
 */
public enum CampoProducto {

    /* El ID del vendedor del producto. */
    ID_VENDEDOR("ID Vendedor"),
    /* Codigo de barras del producto. */
    CODIGO_DE_BARRAS("Codigo de Barras"),
    /* Nombre del producto. */
    NOMBRE("Nombre"),
    /* Descripcion del producto */
    DESCRIPCION("Descripcion"),
    /* Precio del producto */
    PRECIO("Precio"),
    /* Numero de items disponibles */
    STOCK_DISPONIBLES("Disponibles"),
    /* Categoria del producto */
    CATEGORIA("Categoria");

    /* Nombre del CampoProducto. */
    private String nombre;

    /**
     * Crea un CampoProducto con el nombre dado.
     * @param nombre el nombre del CampoProducto.
     */
    private CampoProducto(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Descifra un CampoProducto a partir de su nombre.
     * @param nombre el nombre del CampoProducto.
     * @return el CampoProducto correspondiente al nombre o,
     *         <code>null</code> si no lo encuentra.
     */
    public static CampoProducto getCampoProducto(String nombre) {
        for (CampoProducto campo : CampoProducto.values())
            if (campo.toString().equals(nombre))
                return campo;
        return null;
    }

    /**
     * Regresa una representacion en cadena del CampoProducto.
     * @return una representacion en cadena del CampoProducto.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
