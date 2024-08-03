package src;

/**
 * <p>Clase para representar un producto de electronica.</p>
 */
public class ProductoElectronica extends Producto {

    /**
     * Define el estado inicial del producto de electronica.
     * @param nombre nombre del producto.
     * @param precio precio del producto.
     * @param codigoDeBarras codigo de barras del producto
     */
    public ProductoElectronica (String nombre, 
                                 double precio, 
                                 String codigoDeBarras) {
        super(nombre, precio, DepartamentoTienda.ELECTRONICA, codigoDeBarras);
    }
}