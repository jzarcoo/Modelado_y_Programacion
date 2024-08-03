package src;

/**
 * <p>Clase para representar un producto de electrodomesticos.</p>
 */
public class ProductoElectrodomestico extends Producto {

    /**
     * Define el estado inicial del producto electrodomestico.
     * @param nombre nombre del producto.
     * @param precio precio del producto.
     * @param codigoDeBarras codigo de barras del producto
     */
    public ProductoElectrodomestico (String nombre, 
                                     double precio, 
                                     String codigoDeBarras) {
        super(nombre, precio, DepartamentoTienda.ELECTRODOMESTICOS, codigoDeBarras);
    }
}
