package mx.unam.ciencias.modelado.proyecto2;

import java.util.HashMap;

/**
 * <p>Clase que representa un inventario de productos.</p>
 */
public class Inventario {

    /* Los productos del inventario. */
    private HashMap<String, Producto> productos;

    /**
     * Constructor de la clase.Crea un inventario vacio.
     */
    public Inventario() {
        productos = new HashMap<>();
    }

    /**
     * Agrega un producto al inventario.
     * @param producto el producto a agregar.
     */
    public void agregaProducto(Producto producto) {
        productos.put(producto.getCodigoBarras(), producto);
    }

    /**
     * Regresa un producto del inventario.
     * @param codigoBarras el codigo de barras del producto a regresar.
     * @return el producto con el codigo de barras especificado.
     */
    public Producto getProducto(String codigoBarras) {
        return productos.get(codigoBarras);
    }

    /**
     * Elimina un producto del inventario.
     * @param codigoBarras el codigo de barras del producto a eliminar.
     */
    public void eliminaProducto(String codigoBarras) {
        productos.remove(codigoBarras);
    }

    /**
     * Modifica un producto del inventario.
     * @param codigoBarras el codigo de barras del producto a modificar.
     * @param producto el producto modificado.
     */
    public void modificaProducto(String codigoBarras, Producto producto) {
        productos.put(codigoBarras, producto);
    }

    /**
     * Establece un nuevo inventario.
     */
    public void setProductos(HashMap<String, Producto> productos) {
        this.productos = productos;
    }

    /**
     * RRevisa si un producto esta en el inventario.
     * @param codigoBarras el codigo de barras del producto a buscar.
     * @return true si el producto esta en el inventario, false en otro caso.
     */
    public boolean contieneProducto(String codigoBarras) {
        return productos.containsKey(codigoBarras);
    }

    /**
     * Revisa si el inventario esta vacio.
     * @return true si el inventario esta vacio, false en otro caso.
     */
    public boolean estaVacio() {
        return productos.isEmpty();
    }

    /**
     * Regresa los productos del inventario.
     * @return los productos del inventario.
     */
    public Iterable<Producto> getProductos(){
        return productos.values();
    }

    /*
     * Limpia los productos del inventario.
     */
    public void limpia() {
        productos.clear();
    }
}