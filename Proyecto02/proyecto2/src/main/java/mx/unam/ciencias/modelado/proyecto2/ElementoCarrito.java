package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Clase para elementos de carrito. Un elemento de carrito es un codigo de barras 
 * de un producto con una cantidad.</p>
 */
public class ElementoCarrito {
    
    /* El codigo de barras del producto. */
    private String codigoBarras;
    /* La cantidad de productos. */
    private int cantidad;

    /**
     * Define el estado inicial de un elemento de carrito.
     * @param codigoBarras el codigo de barras del producto.
     * @param cantidad la cantidad de productos.
     */
    public ElementoCarrito(String codigoBarras, int cantidad) {
        this.codigoBarras = codigoBarras;
        this.cantidad = cantidad;
    }

    /**
     * Define el estado inicial de un elemento de carrito.
     * @param e el elemento de carrito a copiar.
     */
    public ElementoCarrito(ElementoCarrito e){
        this.codigoBarras = e.getCodigoBarras();
        this.cantidad = e.getCantidad();
    }
    
    /**
     * Regresa el codigo de barras del producto.
     * @return el codigo de barras del producto.
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * Define el codigo de barras del producto.
     * @param codigoBarras el nuevo codigo de barras del producto.
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    /**
     * Regresa la cantidad de productos.
     * @return la cantidad de productos.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Define la cantidad de productos.
     * @param cantidad la nueva cantidad de productos.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }    
}
