package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Clase Proxy que actua como intermediario entre el cliente y su uso.
 * Implementa la interfaz InterfazCliente.</P>
 */
public class ClienteProxy implements InterfazCliente {

    /* El cliente. */
    private Cliente cliente;

    /**
     * Constructor que define al cliente real.
     * @param cliente el cliente.
     */
    public ClienteProxy(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Regresa el id del cliente.
     * @return el id del cliente.
     */
    @Override
    public int getId() {
        return cliente.getId();
    }

    /**
     * Regresa el rol del cliente.
     * @return el rol del cliente, "cliente";
     */
    @Override
    public String getRol() {
        return cliente.getRol();
    }

    /**
     * Regresa el nombre del cliente.
     * @return el nombre del cliente.
     */
    @Override
    public String getNombreUsuario() {
        return cliente.getNombreUsuario();
    }
    
    /**
     * Regresa la contrasena del cliente.
     * @return la contrasena del cliente.
     */
    @Override
    public String getContrasena() {
        return cliente.getContrasena();
    }

    /**
     * Regresa el telefono del cliente.
     * @return el telefono del cliente.
     */
    @Override
    public String getTelefono() {
        return cliente.getTelefono();
    }

    /**
     * Regresa el numero de cuenta del cliente.
     * @return el numero de cuenta del cliente.
     */
    @Override
    public String getNumeroCuenta() {
        return cliente.getNumeroCuenta();
    }

    /**
     * Agrega un producto al carrito.
     * @param producto el producto a agregar.
     */
    @Override
    public void agregarProducto(Producto producto) {
        cliente.agregarProducto(producto);
    }

    /**
     * Guarda los carritos del cliente en la base de datos.
     * @param mercadita la base de datos de la mercadita.
     */
    @Override
    public void guardaCarritos(InterfazMercadita mercadita){
        cliente.guardaCarritos(mercadita);
    }

    /**
     * Metodo que permite al cliente ver un carrito
     * @param idCarrito el id del carrito a ver.
     */
    @Override
    public Carrito verCarrito(int idCarrito){
        return cliente.verCarrito(idCarrito);
    }
    
    /**
     * Elimina un producto del carrito del cliente.
     * @param producto el producto a eliminar.
     */
    @Override
    public void eliminarProducto(Producto producto){
        cliente.eliminarProducto(producto);
    }

    /**
     * Regresa la cantidad de un producto en el carrito del cliente.
     * @param producto el producto a buscar.
     */
    @Override
    public int getCantidadProducto(Producto producto){
        return cliente.getCantidadProducto(producto);
    }

    /**
     * Nos dice si el carrito del cliente esta vacio.
     * @param id el id del carrito a revisar.
     * @return <code>true</code> si el carrito esta vacio, <code>false</code> en otro caso.
     */
    @Override
    public boolean carritoVacio(int id){
        return cliente.carritoVacio( id);
    }

    /**
     * Elimina un carrito del cliente.
     * @param id el id del carrito a eliminar.
     */
    @Override
    public void eliminaCarrito(int id){
        cliente.eliminaCarrito(id);
    }

    /**
     * Vacia un carrito del cliente.
     * @param id el id del carrito a vaciar.
     */
    public void vaciaCarrito(int id){
        cliente.vaciaCarrito(id);
    }
}