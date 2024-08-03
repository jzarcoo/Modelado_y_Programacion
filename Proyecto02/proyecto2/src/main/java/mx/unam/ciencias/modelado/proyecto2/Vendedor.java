package mx.unam.ciencias.modelado.proyecto2;

import java.util.LinkedList;

/**
 * <p>Clase que define a un vendedor. Un vendedor tiene un inventario de productos
 * que puede vender, una lista de criticas y una lista de puntos de venta.</p>
 */
public class Vendedor extends Usuario implements InterfazVendedor{

    /* Inventario del vendedor. */
    private Inventario inventario;
    /* Criticas del vendedor. */
    private LinkedList<Critica> criticas;

    /**
     * Constructor de la clase.
     * @param id el id del vendedor.
     * @param nombre el nombre del vendedor.
     * @param contrasena la contrasena del vendedor.
     */
    public Vendedor(int id, 
                    String nombre, 
                    String contrasena,
                    String telefono,
                    String numeroCuenta) {
        super(id, "vendedor", nombre, contrasena, telefono, numeroCuenta);
        inventario = new Inventario();
        criticas = new LinkedList<>();
    }

    /**
     * Agrega un producto al inventario del vendedor.
     * @param producto el producto a agregar.
     */
    @Override
    public void agregaProducto(Producto producto) {
        inventario.agregaProducto(producto);
    }

    /**
     * Elimina un producto del inventario del vendedor.
     * @param producto el producto a eliminar.
     */
    @Override
    public void eliminaProducto(Producto producto) {
        inventario.eliminaProducto(producto.getCodigoBarras());
    }

    /**
     * Modifica el primer producto en el inventario del vendedor para que sea
     * identico al segundo.
     * @param producto1 el producto a modificar.
     * @param producto2 el producto con los nuevos valores.
     */
    @Override
    public void modificaProducto(Producto producto1, Producto producto2) {
        inventario.modificaProducto(producto1.getCodigoBarras(), producto2);
    }

    /**
     * Regresa el inventario del vendedor.
     * @return el inventario del vendedor.
     */
    @Override
    public Iterable<Producto> getProductos(){
        return inventario.getProductos();
    }

    /**
     * Regresa las criticas del vendedor.
     * @return las criticas del vendedor.
     */
    @Override
    public Iterable<Critica> getCriticas(){
        return criticas;
    }

    /**
     * Agrega una critica al vendedor.
     * @param critica la critica a agregar.
     */
    @Override
    public void agregaCritica(Critica critica){
        criticas.add(critica);
    }

    /**
     * Regresa el producto.
     * @param codigoBarras el id del producto a regresar.
     * @return el producto.
     */
    @Override
    public Producto getProducto(String codigoBarras){
        return inventario.getProducto(codigoBarras);
    }
    
    /**
     * Agrega una critica al vendedor.
     * @param critica la critica a agregar.
     * @param idCliente el id del cliente que hizo la critica.
     * @param idProducto el id del producto que se critico.
     */
    public Critica agregarCritica(String comentario, int idCliente, int rating){
        int idCritica = getId() *10 + numeroCriticas()*10  + idCliente;
        Critica critica = new Critica(idCritica, getId(), idCliente, rating, comentario,0);
        criticas.add(critica);
        return critica;
    }

    /**
     * Regresa el numero de criticas.
     * @return el numero de criticas.
     */
    @Override
    public int numeroCriticas(){
        return criticas.size();
    }

    /**
     * Reporta una critica del vendedor.
     * @param critica la critica a reportar.
     */
    @Override
    public Critica reportarCritica(Critica critica){
        return critica.reportar();
    }


    /**
     * Limpia las criticas del vendedor.
     */
    public void limpiarCriticas(){
        criticas.clear();
    }


    /*
     * Limpia los productos del vendedor.
     */
    public void limpiaProductos(){
        inventario.limpia();
    }

}