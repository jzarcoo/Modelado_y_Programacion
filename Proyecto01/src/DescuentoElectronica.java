package src;

/**
 * <p>Clase para representar un descuento de electronica.</p>
 */
public class DescuentoElectronica extends Descuento {

    /**
     * Define el estado inicial del descuento de electronica.
     * @param porcentaje porcentaje de descuento.
     */
    public DescuentoElectronica (CatalogoComponente componenteCatalogo,
                                 double descuento) {
        super(componenteCatalogo, descuento, "Descuento de electronicas", DepartamentoTienda.ELECTRONICA);
    }
}