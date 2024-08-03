package mx.unam.ciencias.modelado.proyecto2;

import java.io.IOException;
import java.util.HashMap;
import mx.unam.ciencias.modelado.proyecto2.red.Mensaje;

/**
 * <p>Clase para representar a un cliente. Un cliente tiene pedidos y carritos.</p>
 */
public class Cliente extends Usuario implements InterfazCliente{

    /* Carritos del cliente. */
    private HashMap<Integer, Carrito> carritos; 

    /**
     * Constructor que define al cliente.
     * @param id el id del cliente.
     * @param nombre el nombre del cliente.
     * @param contrasena la contrasena del cliente
     * @param telefono el telefono del cliente.
     * @param numeroCuenta el numero de cuenta del cliente.
     */
    public Cliente(int id, 
                    String nombre, 
                    String contrasena,
                    String telefono,
                    String numeroCuenta) {
        super(id, "cliente", nombre, contrasena, telefono, numeroCuenta);
        carritos = new HashMap<>();
    }

    /**
     * Metodo que permite al cliente ver un carrito
     * @param idCarrito el id del carrito a ver.
     */
    @Override
    public Carrito verCarrito(int idCarrito){
        return carritos.get(idCarrito);
    }

    /**
     * Metodo que permite al cliente agregar un producto a su carrito.
     * @param producto el producto a agregar.
     */
    @Override
    public void agregarProducto(Producto producto){
        int idCarrito = producto.getIdVendedor() * 1000 + getId();
        if(carritos.containsKey(idCarrito)){
            carritos.get(idCarrito).agregaProducto(producto);
        }
        else{
            Carrito carrito = new Carrito(idCarrito, getId(), producto.getIdVendedor());
            carrito.agregaProducto(producto);
            carritos.put(idCarrito, carrito);
        }
    }

    /**
     * Agrega un carrito al cliente.
     * @param carrito el carrito a agregar.
     */
    public void agregaCarrito(Carrito carrito){
        carritos.put(carrito.getId(), new Carrito(carrito));
    }  

    /**
     * Guarda los carritos del cliente en la base de datos.
     * @param mercadita la base de datos de la mercadita.
     */
    @Override
    public void guardaCarritos(InterfazMercadita mercadita){
        for (Carrito valor : carritos.values()) {
            try {
                if(mercadita.hayCarrito(valor.getId())){
                    Carrito viejo = mercadita.getCarrito(valor.getId());
                    mercadita.getConexion().enviaMensaje(Mensaje.REGISTRO_MODIFICADO_CARRITO);
                    mercadita.getConexion().enviaRegistro(viejo);
                    mercadita.getConexion().enviaRegistro(valor);
                    mercadita.eliminaCarrito(viejo);
                    mercadita.agregaCarrito(valor);
                }else{
                    mercadita.agregaCarrito(valor);
                    mercadita.getConexion().enviaMensaje(Mensaje.REGISTRO_AGREGADO_CARRITO);
                    mercadita.getConexion().enviaRegistro(valor);
                }
            } catch (IOException ioe) {
                System.err.println("Error al enviar carrito");
            }
        }
    }

    /**
     * Elimina un carrito del cliente.
     * @param id el id del carrito a eliminar.
     */
    @Override
    public void eliminaCarrito(int id){
        carritos.remove(id);
    }

    /**
     * Metodo que permite ver si el cliente ya tiene un id de carrito
     * @param id el id del carrito a buscar.
     * @return <code>true</code> si el cliente ya tiene un carrito con ese id, <code>false</code> en otro caso.
     */
    public boolean tieneCarrito(int id){
        return carritos.containsKey(id);
    }

    /**
     * Regresa un iterable con los carritos del cliente.
     * @return un iterable con los carritos del cliente.
     */
    public Iterable<Carrito> getCarritos(){
        return carritos.values();
    }

    /**
     * Elimina un producto del carrito del cliente.
     * @param producto el producto a eliminar.
     */
    @Override
    public void eliminarProducto(Producto producto){
        int idCarrito = producto.getIdVendedor() * 1000 + getId();
        if(carritos.containsKey(idCarrito)){
            carritos.get(idCarrito).eliminarProducto(producto);
        }
    
    }

    /**
     * Regresa la cantidad de un producto en el carrito del cliente.
     * @param producto el producto a buscar.
     */
    @Override
    public int getCantidadProducto(Producto producto){
        int idCarrito = producto.getIdVendedor() * 1000 + getId();
        if(carritos.containsKey(idCarrito)){
            return carritos.get(idCarrito).getCantidadProducto(producto);
        }
        return 0;
    }

    /**
     * Nos dice si el carrito del cliente esta vacio.
     * @param id el id del carrito a revisar.
     * @return <code>true</code> si el carrito esta vacio, <code>false</code> en otro caso.
     */
    @Override
    public boolean carritoVacio(int id){
        if(!carritos.containsKey(id))
            return true;
        return carritos.get(id).estaVacio();
    }

    /*
     * Limpia los carritos del cliente.
     */
    public void limpiaCarritos(){
        carritos.clear();
    }

    /**
     * Vacia un carrito del cliente.
     * @param id el id del carrito a vaciar.
     */
    public void vaciaCarrito(int id){
        carritos.get(id).limpiarCarrito();
    }   
}