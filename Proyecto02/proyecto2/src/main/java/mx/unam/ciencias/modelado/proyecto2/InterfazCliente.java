package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Interfaz que define los metodos que un cliente puede realizar.</p>
 */
public interface InterfazCliente extends InterfazUsuario{

    /**
     * Agrega un producto al carrito.
     * @param producto el producto a agregar.
     */
    public void agregarProducto(Producto producto);

    /**
     * Guarda los carritos del cliente en la base de datos.
     * @param mercadita la base de datos de la mercadita.
     */
    public void guardaCarritos(InterfazMercadita mercadita);

    /**
     * Metodo que permite al cliente ver un carrito
     * @param idCarrito el id del carrito a ver.
     */
    public Carrito verCarrito(int idCarrito);

    /**
     * Elimina un producto del carrito del cliente.
     * @param producto el producto a eliminar.
     */
    public void eliminarProducto(Producto producto);

    /**
     * Regresa la cantidad de un producto en el carrito del cliente.
     * @param producto el producto a buscar.
     */
    public int getCantidadProducto(Producto producto);

    /**
     * Nos dice si el carrito del cliente esta vacio.
     * @param id el id del carrito a revisar.
     * @return <code>true</code> si el carrito esta vacio, <code>false</code> en otro caso.
     */
    public boolean carritoVacio(int id);

    /**
     * Elimina un carrito del cliente.
     * @param id el id del carrito a eliminar.
     */
    public void eliminaCarrito(int id);

    /**
     * Vacia un carrito del cliente.
     * @param id el id del carrito a vaciar.
     */
    public void vaciaCarrito(int id);

}