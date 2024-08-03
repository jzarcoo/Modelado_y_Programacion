package src;

/**
 * <p>Clase que actua como un proxy para la tienda CheemsMart.</p>
 * 
 * <p>La clase CheemsMart implementa la interfaz Tienda, por lo que 
 * define los siguientes metodos:</p>
 * 
 * <ul>
 * <li>{@link #verificarCredenciales()}</li>
 * <li>{@link #getCliente()}</li>
 * <li>{@link #verificarCuentaBancaria()}</li>
 * <li>{@link #realizarCompra()}</li>
 * <li>{@link #getPaisDeOrigen()}</li>
 * <li>{@link #getOferta()}</li>
 * <li>{@link #buscarProducto()}</li>
 * </ul>
 */

public class TiendaHelper implements Tienda {

    /* Tienda real. */
    private CheemsMart cheemsMart;
    /* Copia del catalogo de productos de la tienda CheemsMart. */
    private Iterable<CatalogoComponente> catalogo;
    /* Oferta de la tienda CheemsMart para esta sesion. */
    private double oferta;
    
    /**
     * Define el estado inicial de la TiendaHelper.
     * @param cheemsMart la tienda real .
     */
    public TiendaHelper(CheemsMart cheemsMart) {
        this.cheemsMart = cheemsMart;
    }

    /**
     * Asigna una oferta para esta sesion de la tienda.
     * @param paisDeOrigen pais de origen del cliente.
     */
    @Override
    public void asignarOferta(PaisDeOrigen paisDeOrigen){
        oferta = cheemsMart.getOfertaPais(paisDeOrigen);
        catalogo = FabricaSimplePaises.getCatalogoConDescuento(paisDeOrigen, oferta, cheemsMart.getCatalogoCopia());
    }

    /**
     * Verifica con el objeto tienda real si las credenciales del cliente son correctas.
     * @param nombreUsuario nombre de usuario del cliente.
     * @param contrasena contrasena del cliente.
     * @return <code>true</code> si las credenciales son correctas, <code>false</code> de lo contrario.
     */
    @Override
    public boolean verificarCredenciales(String nombreUsuario, String contrasena) {
        return cheemsMart.verificarCredenciales(nombreUsuario, contrasena);
    }

    /**
     * Regresa el cliente con el nombre de usuario especificado.
     * @param nombre nombre de usuario del cliente.
     * @return el cliente con el nombre de usuario especificado.
     */
    @Override
    public Cliente getCliente(String nombre) {
        return cheemsMart.getCliente(nombre);
    }

    /**
     * Regresa el cliente con el nombre de usuario especificado.
     * @param id el id del usuario.
     * @return el cliente con el nombre de usuario especificado.
     */
    @Override
    public Cliente getCliente(Integer id) {
        return cheemsMart.getCliente(id);
    }

    /**
     * Verifica si la cuenta bancaria del cliente es correcta.
     * @param id id del cliente.
     * @param cuenta cuenta bancaria del cliente.
     * @return <code>true</code> si la cuenta bancaria es correcta, <code>false</code> de lo contrario.
     */
    @Override
    public boolean verificarCuentaBancaria(Integer id, String cuenta) {
        return cheemsMart.verificarCuentaBancaria(id, cuenta);
    }

    /**
     * Realiza una compra con base al total y el nombre del cliente.
     * @param total total de la compra.
     * @param id id del cliente.
     * @return <code>true</code> si la compra se realizo con exito, <code>false</code> de lo contrario.
     */
    @Override
    public boolean realizarCompra(double total, Integer id) {
        return cheemsMart.realizarCompra(total, id);
    }

    /**
     * Regresa el pais de origen del cliente.
     * @param id id del cliente.
     * @return el pais de origen del cliente.
     */
    @Override
    public PaisDeOrigen getPaisDeOrigen(Integer id) {
        return cheemsMart.getPaisDeOrigen(id);
    }

    /**
     * Regresa la oferta de la tienda.
     * @return la oferta de la tienda.
     */
    @Override
    public double getOferta() {
        return oferta;
    }

    /**
     * Busca un producto en el catalogo y lo devuelve
     * @param codigo el codigo del producto
     * @return el producto
     */
    @Override
    public Producto buscarProducto(String codigo){
        for (CatalogoComponente c : catalogo) {
            Producto p = (Producto) c.busca(codigo);
            if (p != null) {
                return p;
            }
        }
        return null;
    }

    /**
     * Regresa el catalogo de productos de la tienda modo lectura.
     * @return el catalogo de productos de la tienda modo lectura.
     */
    @Override
    public Iterable<CatalogoComponente>  getCatalogo(){
        return catalogo;
    }
}