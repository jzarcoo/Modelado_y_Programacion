package mx.unam.ciencias.modelado.proyecto2;

import java.util.List;
import java.util.LinkedList;
import mx.unam.ciencias.modelado.proyecto2.red.Conexion;

/**
 * <p>Interfaz que define los metodos que se pueden realizar en la mercadita.</p>
 */
public interface InterfazMercadita {

    /**
     * Desconecta a la mercadita.
     */
    public void desconectar();

    /**
     * Comprueba si un usuario es valido.
     * @param usuario el usuario a comprobar.
     * @param contrasena la contrasena del usuario.
     * @return <code>true</code> si el usuario es valido, <code>false</code> en otro caso.
     */
    public boolean comprobarUsuario(String usuario, String contrasena);

    /**
     * Regresa un usuario.
     * @param usuario el usuario a regresar.
     * @param contrasena la contrasena del usuario.
     * @return el usuario.
     */
    public InterfazUsuario getUsuario(String usuario, String contrasena);


    /**
     * Regresa un vendedor.
     * @param id el id del vendedor.
     * @return el vendedor.
     */
    public InterfazVendedor getVendedor(int id);

    /**
     * Devuelve un administrador.
     * @param id el id del administrador.
     * @return el administrador.
     */
    public InterfazAdministrador getAdministrador(int id);

    /**
     * Regresa un cliente.
     * @param id el id del cliente.
     * @return el cliente.
     */
    public InterfazCliente getCliente(int id);

    /**
     * Regresa los vendedores.
     * @return una lista con los vendedores.
     */
    public List<InterfazVendedor> getVendedores();

    /**
     * Regresa la temperatura maxima.
     * @return la temperatura maxima.
     */
    public String getTemperaturaMaxima();

    /**
     * Regresa la temperatura minima.
     * @return la temperatura minima.
     */
    public String getTemperaturaMinima();

    /**
     * Regresa la probabilidad de precipitacion.
     * @return la probabilidad de precipitacion.
     */
    public String getProbabilidadPrecipitacion();

    /**
     * Regresa la descripcion del cielo
     * @return la descripcion del cielo
     */
    public String getCielo();

    /**
     * Regresa la conexion.
     * @return la conexion.
     */
    public Conexion getConexion();

    /**
     * Regresa las criticas de los vendedores.
     * @return las criticas de los vendedores.
     */
    public Iterable<Critica> getCriticas();

    /**
     * Elimina una critica de la base de datos de criticas de la mercadita.
     * @param critica la critica a eliminar.
     */
    public void eliminaCritica(Critica critica);

    /**
     * Agrega un producto a la base de datos de productos de la mercadita.
     * @param producto el producto a agregar.
     */
    public void agregaProducto(Producto producto);

    /**
     * Elimina un producto de la base de datos de productos de la mercadita.
     * @param producto el producto a eliminar.
     */
    public void eliminaProducto(Producto producto);

    /**
     * Regresa un pago con tarjeta
     * @return el pago con tarjeta
     */
    public PagoConTarjeta getPagoConTarjeta();

    /**
     * Regresa un pago con efectivo
     * @return el pago con efectivo
     */
    public PagoEnEfectivo getPagoConEfectivo();

    /**
     * Regresa un pago con transferencia
     * @return el pago con transferencia
     */
    public PagoTransferencia getPagoConTransferencia();

    /**
     * Modifica el primer producto en la base de datos de productos de la mercadita
     * para que sea identico al segundo.
     * @param producto1 el producto a modificar.
     * @param producto2 el producto con los nuevos valores.
     */
    public void modificaProducto(Producto producto1, Producto producto2);

    /**
     * Busca productos en la base de datos de productos de la mercadita con respecto al 
     * id del vendedor dado.
     * @param campo el campo por el que se va a buscar.
     * @param valor el valor que se va a buscar.
     * @param idVendedor el id del vendedor.
     * @return una lista con los productos que cumplen con el criterio de busqueda.
     */
    public LinkedList<Producto> buscaProductos(CampoProducto campo, Object valor, int idVendedor);

    /**
     * Agrega una critica a la base de datos de criticas de la mercadita.
     * @param critica la critica a agregar.
     */
    public void agregaCritica(Critica critica);

    /**
     * Modifia la critica en la base de datos de criticas de la mercadita.
     * @param critica1 la critica a modificar.
     * @param critica2 la critica con los nuevos valores.
     */
    public void modificaCritica(Critica critica1, Critica critica2);

    /**
     * Verifica si hay un carrito con un id dado en la base de datos de carritos de la mercadita.
     */
    public boolean hayCarrito(int idCarrito);

    /**
     * Elimina un carrito de la base de datos de carritos de la mercadita.
     * @param carrito el carrito a eliminar.
     */
    public void eliminaCarrito(Carrito carrito);

    /*
     * Agrega un carrito a la base de datos de carritos de la mercadita.
     * @param carrito el carrito a agregar.
     */
    public void agregaCarrito(Carrito carrito);

    /**
     * Regresa un carrito con un id dado.
     * @param id el id del carrito.
     * @return el carrito.
     */
    public Carrito getCarrito(int id);
}   