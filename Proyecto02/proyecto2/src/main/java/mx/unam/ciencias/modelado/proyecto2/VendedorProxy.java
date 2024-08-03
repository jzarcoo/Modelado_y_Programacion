package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Clase Proxy que actua como intermediario entre el vendedor y su uso.
 * Implementa la interfaz InterfazVendedor.<p/>
 */
public class VendedorProxy implements InterfazVendedor {

    /* El vendedor. */
    private Vendedor vendedor;

    /**
     * Constructor que define al vendedor real.
     * @param vendedor el vendedor.
     */
    public VendedorProxy(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * Regresa el id del vendedor.
     * @return el id del vendedor.
     */
    @Override
    public int getId() {
        return vendedor.getId();
    }

    /**
     * Regresa el rol del vendedor.
     * @return el rol del vendedor, "vendedor";
     */
    @Override
    public String getRol() {
        return vendedor.getRol();
    }

    /**
     * Regresa el nombre del vendedor.
     * @return el nombre del vendedor.
     */
    @Override
    public String getNombreUsuario() {
        return vendedor.getNombreUsuario();
    }

    /**
     * Regresa la contrasena del vendedor.
     * @return la contrasena del vendedor.
     */
    @Override
    public String getContrasena() {
        return vendedor.getContrasena();
    }

    /**
     * Regresa el telefono del vendedor.
     * @return el telefono del vendedor.
     */
    @Override
    public String getTelefono() {
        return vendedor.getTelefono();
    }

    /**
     * Regresa el numero de cuenta del vendedor.
     * @return el numero de cuenta del vendedor.
     */
    @Override
    public String getNumeroCuenta() {
        return vendedor.getNumeroCuenta();
    }

    /**
     * Agrega un producto al inventario del vendedor.
     * @param producto el producto a agregar.
     */
    @Override
    public void agregaProducto(Producto producto) {
        vendedor.agregaProducto(producto);
    }

    /**
     * Elimina un producto del inventario del vendedor.
     * @param producto el producto a eliminar.
     */
    @Override
    public void eliminaProducto(Producto producto) {
        vendedor.eliminaProducto(producto);
    }

    /**
     * Modifica el primer producto en el inventario del vendedor para que sea
     * identico al segundo.
     * @param producto1 el producto a modificar.
     * @param producto2 el producto con los nuevos valores.
     */
    @Override
    public void modificaProducto(Producto producto1, Producto producto2) {
        vendedor.modificaProducto(producto1, producto2);
    }

    /**
     * Regresa un iterable con los productos del vendedor.
     * @return un iterable con los productos del vendedor.
     */ 
    @Override
    public Iterable<Producto> getProductos(){
        return vendedor.getProductos();
    }

    /**
     * Regresa un iterable con las criticas del vendedor.
     * @return un iterable con las criticas del vendedor.
     */
    @Override
    public Iterable<Critica> getCriticas(){
        return vendedor.getCriticas();
    }

    /**
     * Agrega una critica al vendedor.
     * @param critica la critica a agregar.
     */
    @Override
    public void agregaCritica(Critica critica){
        vendedor.agregaCritica(critica);
    }

    /**
     * Regresa un producto del vendedor.
     * @param codigoBarras el id del producto a regresar.
     * @return el producto.
     */
    @Override
    public Producto getProducto(String codigoBarras){
        return vendedor.getProducto(codigoBarras);
    }

    /**
     * Agrega una critica al vendedor.
     * @param critica la critica a agregar.
     * @param idCliente el id del cliente que agrega la critica.
     * @param idProducto el id del producto al que se le agrega la critica.
     */
    @Override
    public Critica agregarCritica(String comentario, int idCliente, int rating){
        return vendedor.agregarCritica(comentario, idCliente, rating);
    }
    /**
     * Regresa el numero de criticas.
     * @return el numero de criticas.
     */
    @Override
    public int numeroCriticas(){
        return vendedor.numeroCriticas();
    }

    /**
     * Reportar critica
     * @param critica la critica a reportar.
     */
    @Override
    public Critica reportarCritica(Critica critica){
        return vendedor.reportarCritica(critica);
    }
}