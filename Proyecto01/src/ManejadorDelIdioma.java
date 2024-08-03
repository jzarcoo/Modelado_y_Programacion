package src;

/**
 * <p>Interfaz que define los metodos que debe implementar un manejador de idioma.</p>
 * 
 * <p>Un manejador de idioma nos permite comunicarnos con el cliente en diferentes idiomas, 
 * permitiendo una mejor experiencia de usuario.</p>
 */
public interface ManejadorDelIdioma {

    /**
     * Regresa un saludo al cliente.
     * @return un saludo al cliente.
     */
    public String saludar();

    /**
     * Regresa un mensaje para completar la compra.
     * @return un mensaje para completar la compra.
     */
    public String completarCompra();

    /**
     * Regresa un mensaje para mostrar el menu principal.
     * @return un mensaje para mostrar el menu principal.
     */
    public String mostrarMenuPrincipal();

    /**
     * Nos dice si el cliente recibio un descuento.
     * @param descuento el descuento recibido.
     * @return un mensaje de oferta.
     */
    public String mensajeOferta(double descuento);

    /**
     * Regresa un mensaje para mostrar el menu de compras.
     * @return un mensaje para mostrar el menu de compras.
     */
    public String mostrarMenuCompras();

    /**
     * Regresa un mensaje para mostrar el menu de productos.
     * @return un mensaje para mostrar el menu de productos.
     */
    public String preguntarProducto();

    /**
     * Regresa un mensaje cuando se agrega un producto al carrito.
     * @return un mensaje cuando se agrega un producto al carrito.
     */
    public String productoAgregado();

    /**
     * Regresa un mensaje de error.
     * @return un mensaje de error.
     */
    public String mensajeError();

    /**
     * Regresa un mensaje de exito.
     * @return un mensaje de exito.
     */
    public String compraExitosa();

    /**
     * Regresa un mensaje de compra fallida.
     * @return un mensaje de compra fallida.
     */
    public String compraFallida();

    /**
     * Regresa un mensaje de carrito vacio.
     * @return un mensaje de carrito vacio.
     */
    public String mensajeCarritoVacio();

    /**
     * Regresa un mensaje de despedida al cliente.
     * @return un mensaje de despedida al cliente.
     */
    public String despedirse();

    /**
     * Regresa un mensaje para pedir la cuenta.
     * @return un mensaje para pedir la cuenta.
     */
    public String pedirCuenta();

    /**
     * Regresa un mensaje de entrega.
     * @return un mensaje de entrega.
     */
    public String mensajeEntrega();

    /**
     * Regresa un mensaje de ticket.
     * @return un mensaje de ticket.
     */
    public String mensajeTicket();

    /**
     * Regresa un mensaje de total.
     * @param total el total de la compra.
     * @return un mensaje de total.
     */
    public String mensajeTotal(double total);

    /**
     * Regresa un mensaje para pedir la opcion de menu.
     * @return un mensaje para pedir opcion de menu.
     */
    public String mensajeOpcionMenu();

    /**
     * Muestra un mensaje de error cuando el codigo del producto es incorrecto.
     * @return un mensaje de error cuando el codigo del producto es incorrecto.
     */
    public String mensajeErrorCodigoProducto();

    /**
     * Muestra un mensaje de error cuando la cuenta bancaria es incorrecta.
     * @return un mensaje de error cuando la cuenta bancaria es incorrecta.
     */
    public String mensajeErrorCuenta();
}

