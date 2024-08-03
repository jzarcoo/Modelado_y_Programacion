package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Interfaz que define a un vendedor.</p>
 */
public interface InterfazVendedor extends InterfazUsuario {

    /**
     * Agrega un producto al inventario del vendedor.
     * @param producto el producto a agregar.
     */
    public void agregaProducto(Producto producto);

    /**
     * Elimina un producto del inventario del vendedor.
     * @param producto el producto a eliminar.
     */
    public void eliminaProducto(Producto producto);

    /**
     * Modifica el primer producto en el inventario del vendedor para que sea
     * identico al segundo.
     * @param producto1 el producto a modificar.
     * @param producto2 el producto con los nuevos valores.
     */
    public void modificaProducto(Producto producto1, Producto producto2);

    /**
     * Regresa los productos del vendedor.
     * @return los productos del vendedor.
     */
    public Iterable<Producto> getProductos();

    /**
     * Regresa las criticas del vendedor.
     * @return las criticas del vendedor.
     */
    public Iterable<Critica> getCriticas();

    /**
     * Agrega una critica al vendedor.
     * @param critica la critica a agregar.
     */
    public void agregaCritica(Critica critica);

    /**
     * Regresa el producto .
     * @param codigoBarras el id del producto a regresar.
     * @return el producto.
     */
    public Producto getProducto(String codigoBarras);

    /**
     * Regresa la critica.
     * @param idCritica el id de la critica a regresar.
     * @return la critica.
     */

    public Critica agregarCritica(String comentario, int idCliente, int rating);

    /**
     * Regresa el numero de criticas.
     * @return el numero de criticas.
     */
    public int numeroCriticas();

    /**
     * Reporta una critica del vendedor.
     * @param critica la critica a reportar.
     */
    public Critica reportarCritica(Critica critica);
}