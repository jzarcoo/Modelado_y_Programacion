package src;

/**
 * <p>Clase para representar un producto de alimento.</p>
 */
public class ProductoAlimento extends Producto {

    /**
     * Define el estado inicial del producto de alimento.
     * @param nombre nombre del producto.
     * @param precio precio del producto.
     * @param codigoDeBarras codigo de barras del producto
     */
    public ProductoAlimento (String nombre, 
                             double precio, 
                             String codigoDeBarras) {
        super(nombre, precio, DepartamentoTienda.ALIMENTOS, codigoDeBarras);
    }
}