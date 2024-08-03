package mx.unam.ciencias.modelado.proyecto2;

import java.util.LinkedList;
import java.util.List;
import mx.unam.ciencias.modelado.proyecto2.red.Conexion;

/**
 * <p>Clase Proxy que actua como intermediario entre la mercadita y su uso.
 * Implementa la interfaz InterfazMercadita.</p>
 */
public class MercaditaProxy implements InterfazMercadita {
    
    /* La mercadita. */
    private Mercadita mercadita;    

    /**
     * Constructor que define a la mercadita real.
     */
    public MercaditaProxy() {
        this.mercadita = new Mercadita();
    }

    /**
     * Desconecta a la mercadita.
     */
    @Override
    public void desconectar(){
        mercadita.desconectar();
    }

    /**
     * Comprueba si un usuario es valido.
     * @param usuario el usuario a comprobar.
     * @param contrasena la contrasena del usuario.
     * @return true si el usuario es valido, false en otro caso.
     */
    @Override
    public boolean comprobarUsuario(String usuario, String contrasena){
        return mercadita.comprobarUsuario(usuario, contrasena);
    }

    /**
     * Regresa un usuario.
     * @param usuario el usuario a regresar.
     * @param contrasena la contrasena del usuario.
     * @return el usuario.
     */
    @Override
    public InterfazUsuario getUsuario(String usuario, String contrasena){
        Usuario u = (Usuario) mercadita.getUsuario(usuario, contrasena);
        if (u == null) return null;
        if (u.getRol().equals("vendedor")) return getVendedor(u.getId());
        if (u.getRol().equals("cliente")) return getCliente(u.getId());
        if (u.getRol().equals("administrador")) return getAdministrador(u.getId());
        return null;
    }

    /**
     * Regresa un vendedor.
     * @param id el id del vendedor.
     * @return el vendedor.
     */
    @Override
    public InterfazVendedor getVendedor(int id){
        return new VendedorProxy((Vendedor)mercadita.getVendedor(id));
    }

    /**
     * Regresa un cliente.
     * @param id el id del cliente.
     * @return el cliente.
     */
    @Override
    public InterfazCliente getCliente(int id){
        InterfazCliente c =mercadita.getCliente(id);
        return new ClienteProxy((Cliente)c);
    }

    /**
     * Regresa un administrador.
     * @param id el id del administrador.
     * @return el administrador.
     */
    @Override
    public InterfazAdministrador getAdministrador(int id){
        return new AdministradorProxy((Administrador)mercadita.getAdministrador(id));
    }

    /**
     * Regresa una lista de vendedores.
     * @return la lista de vendedores.
     */
    @Override
    public List<InterfazVendedor> getVendedores(){
        LinkedList<InterfazVendedor> vendedores = new LinkedList<>();
        for(InterfazVendedor v : mercadita.getVendedores()){
            vendedores.add(new VendedorProxy((Vendedor)v));
        }
        return vendedores;
    }

    /**
     * Regresa la temperatura maxima.
     * @return la temperatura maxima.
     */
    @Override
    public String getTemperaturaMaxima(){
        return mercadita.getTemperaturaMaxima();
    }

    /**
     * Regresa la temperatura minima.
     * @return la temperatura minima.
     */
    @Override
    public String getTemperaturaMinima(){
        return mercadita.getTemperaturaMinima();
    }

    /**
     * Regresa la probabilidad de precipitacion.
     * @return la probabilidad de precipitacion.
     */
    @Override
    public String getProbabilidadPrecipitacion(){
        return mercadita.getProbabilidadPrecipitacion();
    }

    /**
     * Regresa descripcion del cielo
     * @return la descripcion del cielo.
     */
    @Override
    public String getCielo(){
        return mercadita.getCielo();
    }

 
    /**
     * Agrega un producto a la base de datos de productos de la mercadita.
     * @param producto el producto a agregar.
     */
    @Override
    public void agregaProducto(Producto producto) {
        mercadita.agregaProducto(producto);
    }

    /**
     * Elimina un producto de la base de datos de productos de la mercadita.
     * @param producto el producto a eliminar.
     */
    @Override
    public void eliminaProducto(Producto producto) {
        mercadita.eliminaProducto(producto);
    }

    /**
     * Modifica el primer producto en la base de datos de productos de la mercadita
     * para que sea identico al segundo.
     * @param producto1 el producto a modificar.
     * @param producto2 el producto con los nuevos valores.
     */
    @Override
    public void modificaProducto(Producto producto1, Producto producto2){
        mercadita.modificaProducto(producto1, producto2);
    }

    /**
     * Busca productos en la base de datos de productos de la mercadita con respecto al 
     * id del vendedor dado.
     * @param campo el campo por el que se va a buscar.
     * @param valor el valor que se va a buscar.
     * @param idVendedor el id del vendedor.
     * @return una lista con los productos que cumplen con el criterio de busqueda.
     */
    @Override
    public LinkedList<Producto> buscaProductos(CampoProducto campo, Object valor, int idVendedor) {
        return mercadita.buscaProductos(campo, valor, idVendedor);
    }

    /**
     * Regresa la conexion.
     * @return la conexion.
     */
    @Override
    public Conexion getConexion(){
        return mercadita.getConexion();
    }

    /**
     * Regresa un pago con tarjeta.
     * @return el pago con tarjeta.
     */

    @Override
    public PagoConTarjeta getPagoConTarjeta(){
        return mercadita.getPagoConTarjeta();
    }

    /**
     * Regresa un pago con efectivo.
     * @return el pago con efectivo.
     */
    @Override
    public PagoEnEfectivo getPagoConEfectivo(){
        return mercadita.getPagoConEfectivo();
    }

    /**
     * Regresa un pago con transferencia.
     * @return el pago con transferencia.
     */
    @Override
    public PagoTransferencia getPagoConTransferencia(){
        return mercadita.getPagoConTransferencia();
    }
    
    /**
     * Agrega una critica a la base de datos de criticas de la mercadita.
     * @param critica la critica a agregar.
     */
    @Override
    public void agregaCritica(Critica critica){
        mercadita.agregaCritica(critica);
    }

    /**
     * Modifia la critica en la base de datos de criticas de la mercadita.
     * @param critica1 la critica a modificar.
     * @param critica2 la critica con los nuevos valores.
     */
    @Override
    public void modificaCritica(Critica critica1, Critica critica2){
        mercadita.modificaCritica(critica1, critica2);
    }

     /**
     * Verifica si hay un carrito
     * @param id el id del carrito
     * @return true si hay un carrito con el id dado, false en otro caso
     */
    public boolean hayCarrito(int id){
        return mercadita.hayCarrito(id);
    }

    /**
     * Elimina un carrito de la base de datos de carritos de la mercadita.
     * @param carrito el carrito a eliminar.
     */
    @Override
    public void eliminaCarrito(Carrito c){
        mercadita.eliminaCarrito(c);
    }

    /**
     * Agrega un carrito a la base de datos de carritos de la mercadita.
     * @param carrito el carrito a agregar.
     */
    @Override
    public void agregaCarrito(Carrito c){
        mercadita.agregaCarrito(c);
    }

    /**
     * Regresa las criticas de los vendedores.
     * @return las criticas de los vendedores.
     */
    @Override
    public Iterable<Critica> getCriticas() {
        return mercadita.getCriticas();
    }

    /**
     * Elimina una critica de la base de datos de criticas de la mercadita.
     * @param critica la critica a eliminar.
     */
    @Override
    public void eliminaCritica(Critica critica) {
        mercadita.eliminaCritica(critica);
    }

    /**
     * Regresa un carrito con un id dado.
     * @param id el id del carrito.
     * @return el carrito.
     */
    @Override
    public Carrito getCarrito(int id){
        return mercadita.getCarrito(id);
    }
}
