package src;

/**
 * <p>Clase para crear manejadores del idioma con base al pais de origen.</p>
 * 
 * <p>Esta clase implementa el patron de diseno de Fabrica Simple.</p>
 */
public class FabricaSimplePaises{

    /**
     * Regresa un manejador del idioma con base al pais de origen.
     * @param paisDeOrigen el pais de origen del cliente.
     * @return un manejador del idioma con base al pais de origen.
     */
    public static ManejadorDelIdioma getManejadorDelIdioma(PaisDeOrigen paisDeOrigen) {
        switch (paisDeOrigen) {
            case ESTADOS_UNIDOS:
                return new ManejadorDeIdiomaEstadounidense();
            case ESPANA:
                return new ManejadorDeIdiomaEspanol();
            case MEXICO:
                return new ManejadorDeIdiomaLatino();
            default:
                return new ManejadorDeIdiomaLatino();
        }
    }

    /**
     * Regresa un catalogo con descuento en base al pais de origen.
     * @param paisDeOrigen el pais de origen del cliente.
     * @return un catalogo con descuento en base al pais de origen.
     */
    public static CatalogoComponente getCatalogoConDescuento(PaisDeOrigen paisDeOrigen, double descuento, CatalogoComponente  c) {
        switch (paisDeOrigen) {
            case ESTADOS_UNIDOS:
                return new DescuentoElectronica(c, descuento);
            case ESPANA:
                return new DescuentoElectrodomestico(c, descuento);
            case MEXICO:
                return new DescuentoAlimento(c, descuento);
            default:
                return  new DescuentoLimpieza(c, descuento);
        }
    }
}
