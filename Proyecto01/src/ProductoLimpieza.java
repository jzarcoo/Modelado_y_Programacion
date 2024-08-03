package src;

/**
 * <p>Clase para representar un producto de limpieza.</p>
 */
public class ProductoLimpieza extends Producto {

    /**
     * Define el estado inicial del producto de limpieza.
     * @param nombre nombre del producto.
     * @param precio precio del producto.
     * @param codigoDeBarras codigo de barras del producto
     */
    public ProductoLimpieza (String nombre, 
                             double precio, 
                             String codigoDeBarras) {
        super(nombre, precio, DepartamentoTienda.LIMPIEZA, codigoDeBarras);
    }
}