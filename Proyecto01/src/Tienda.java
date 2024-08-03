package src;

/**
 * <p>Interfaz que define los metodos que debe implementar una tienda.</p>
 */
public interface Tienda {

    /**
     * Verifica si las credenciales son correctas.
     * @param nombre el nombre del cliente.
     * @param contrasena la contrasena del cliente.
     * @return <code>true</code> si las credenciales son correctas, 
     *          <code>false</code> de lo contrario.
     */
    public boolean verificarCredenciales(String nombre, String contrasena);

    /**
     * Regresa una instancia del cliente con base a su nombre de usuario.
     * @param id el id del usuario.
     * @return el cliente con el nombre de usuario especificado.
     */
    public Cliente getCliente(Integer id);

    /**
     * Regresa una instancia del cliente con base a su nombre de usuario.
     * @param nombre el nombre de usuario del cliente.
     * @return el cliente con el nombre de usuario especificado.
     */
    public Cliente getCliente(String nombre);
    /**
     * Verifica si la cuenta bancaria es correcta.
    * @param id el id del clinte.
    * @param cuenta la cuenta bancaria del cliente.
    * @return <code>true</code> si la cuenta bancaria es correcta, 
    *         <code>false</code> de lo contrario.
    */
    public boolean verificarCuentaBancaria(Integer id, String cuenta);

    /**
     * Realiza una compra con base al total y el nombre del cliente.
     * @param total el total de la compra.
     * @param id el id del cliente.
     * @return <code>true</code> si la compra se realizo con exito, 
     *         <code>false</code> de lo contrario.
     */
    public boolean realizarCompra(double total, Integer id);

    /**
     * Regresa el pais de origen del cliente.
     * @param id el id del cliente.
     * @return el pais de origen del cliente.
     */
    public PaisDeOrigen getPaisDeOrigen(Integer id);

    /**
     * Regresa la oferta de la tienda.
     * @return la oferta de la tienda.
     */
    public double getOferta();

    /**
     * Busca un producto en el catalogo y lo devuelve
     * @param codigo el codigo del producto
     * @return el producto
     */
    public Producto buscarProducto(String codigo);

    /**
     * Asigna una oferta para esta sesion de la tienda.
     * @param paisDeOrigen pais de origen del cliente.
     */
    public void asignarOferta(PaisDeOrigen paisDeOrigen);
    
    /**
     * Regresa del catalogo de productos de la tienda en solo lectura.
     * @return un catalogo de productos de la tienda.
     */
    public Iterable<CatalogoComponente>  getCatalogo();

}
