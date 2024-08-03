package src;

/**
 * <p>Clase para representar un descuento de limpieza.</p>
 */
public class DescuentoLimpieza extends Descuento {

    /**
     * Define el estado inicial del descuento de limpieza.
     * @param porcentaje porcentaje de descuento.
     */
    public DescuentoLimpieza (CatalogoComponente componenteCatalogo,
                              double descuento) {
        super(componenteCatalogo, descuento, "Descuento de limpiezas", DepartamentoTienda.LIMPIEZA);
    }
}