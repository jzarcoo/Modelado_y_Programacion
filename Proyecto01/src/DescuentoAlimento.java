package src;

/**
 * <p>Clase para representar un descuento de alimento.</p>
 */
public class DescuentoAlimento extends Descuento {

    /**
     * Define el estado inicial del descuento de alimento.
     * @param porcentaje porcentaje de descuento.
     */
    public DescuentoAlimento (CatalogoComponente componenteCatalogo,
                              double descuento) {
        super(componenteCatalogo, descuento, "Descuento de alimentos", DepartamentoTienda.ALIMENTOS);
    }
}