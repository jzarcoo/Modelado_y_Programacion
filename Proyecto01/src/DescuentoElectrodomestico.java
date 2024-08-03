package src;

/**
 * <p>Clase para representar un descuento de electrodomesticos.</p>
 */
public class DescuentoElectrodomestico extends Descuento {

    /**
     * Define el estado inicial del descuento de electrodomesticos.
     * @param porcentaje porcentaje de descuento.
     */
    public DescuentoElectrodomestico (CatalogoComponente componenteCatalogo,
                                      double descuento) {
        super(componenteCatalogo, descuento, "Descuento de electrodomesticos", DepartamentoTienda.ELECTRODOMESTICOS);
    }
}