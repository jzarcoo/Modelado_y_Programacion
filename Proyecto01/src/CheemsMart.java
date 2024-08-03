package src;

import java.util.HashMap;
import java.util.Random;
/**
 * <p>Clase para representar la tienda CheemsMart.</p>
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
public class CheemsMart implements Tienda {

    /* Catalogo de productos de la tienda CheemsMart. */
    private CatalogoComponente catalogo;
    /* Base de datos de la tienda CheemsMart. */
    private BaseDeDatosClientes baseDeDatosClientes;
    /* Unica instancia de la tienda CheemsMart. */
    // Variable comprartida entre todos los hilos pues esta en memoria principal.
    private volatile static CheemsMart cheemsMart;
    /* Ofertas paises */
    private HashMap<PaisDeOrigen, Double> ofertas;

    /**
     * Define el estado inicial de la tienda CheemsMart.
     */
    private CheemsMart() {
        catalogo = new Departamento(DepartamentoTienda.GENERAL);
        baseDeDatosClientes = BaseDeDatosClientes.getInstance();
        carga();
        ofertas = new HashMap<>();
        creaOfertas();
    }

    /**
     * Carga los productos de la tienda.
     */
    public void carga(){
        CatalogoComponente depoElectronica = new DepartamentoElectronica();
        depoElectronica.agrega(new ProductoElectronica("Laptop", 15000, "001"));
        depoElectronica.agrega(new ProductoElectronica("Tablet", 5000, "002"));

        CatalogoComponente depoAlimentos = new DepartamentoAlimentos();
        depoAlimentos.agrega(new ProductoAlimento("Leche", 20, "101"));
        depoAlimentos.agrega(new ProductoAlimento("Pan", 10, "102"));
        depoAlimentos.agrega(new ProductoAlimento("Huevo", 5, "103"));

        CatalogoComponente depoElectrodomesticos = new DepartamentoElectrodomesticos();
        depoElectrodomesticos.agrega(new ProductoElectrodomestico("Refrigerador", 15000, "201"));
        depoElectrodomesticos.agrega(new ProductoElectrodomestico("Lavadora", 10000, "202"));

        CatalogoComponente depoLimpieza = new DepartamentoLimpieza();
        depoLimpieza.agrega(new ProductoLimpieza("Jabon", 10, "301"));
        depoLimpieza.agrega(new ProductoLimpieza("Escoba", 50, "302"));

        catalogo.agrega(depoLimpieza);
        catalogo.agrega(depoElectrodomesticos);
        catalogo.agrega(depoAlimentos);
        catalogo.agrega(depoElectronica);

    }

    /**
     * Regresa la instancia de la tienda CheemsMart.
     * Este metodo implementa el patron Singleton, debe ser sincronizado en 
     * caso de que dos o mas hilos accedan al metodo a la vez. Ademas, realiza
     * un bloque comprobado doble para garantizar que la instancia sea unica.
     * @return la instancia de la base de datos de la tienda.
     */
	public static CheemsMart getInstance() {
		if (cheemsMart == null) {
			synchronized (CheemsMart.class) {
				if (cheemsMart == null) 
                    cheemsMart = new CheemsMart();
			}
		}
		return cheemsMart;
	}
    
    /**
     * Verifica las credenciales de un cliente.
     * @param nombreUsuario Nombre de usuario del cliente.
     * @param contrasena Contrasena del cliente.
     * @return <code>true</code> si las credenciales son validas,
     *          <code>false</code> de lo contrario.
     */
    @Override
    public boolean verificarCredenciales(String nombreUsuario, String contrasena) {
        Cliente c = baseDeDatosClientes.buscaCliente(nombreUsuario);
        return c != null && c.getContrasena().equals(contrasena);
    }

    /**
     * Regresa una instancia del cliente con base a su nombre de usuario.
     * @param nombre el nombre de usuario del cliente.
     * @return el cliente con el nombre de usuario especificado.
     */
    @Override
    public Cliente getCliente(String nombre) {
        return baseDeDatosClientes.buscaCliente(nombre);
    }

    /**
     * Regresa una instancia del cliente con base a su nombre de usuario.
     * @param id el nombre de usuario del cliente.
     * @return el cliente con el nombre de usuario especificado.
     */
    @Override
    public Cliente getCliente(Integer id) {
        return baseDeDatosClientes.buscaCliente(id);
    }

    /**
     * Verifica si la cuenta bancaria es correcta.
     * @param nombre el nombre del cliente.
     * @param cuenta la cuenta bancaria del cliente.
     * @return <code>true</code> si la cuenta bancaria es correcta, 
     *         <code>false</code> de lo contrario.
     */
    @Override
    public boolean verificarCuentaBancaria(Integer id, String cuenta) {
        Cliente c = baseDeDatosClientes.buscaCliente(id);
        return c != null && c.verificarCuentaBancaria(cuenta);
    }

    /**
     * Realiza una compra con base al total y el nombre del cliente.
     * @param total el total de la compra.
     * @param id el id del cliente.
     * @return <code>true</code> si la compra se realizo con exito, 
     *         <code>false</code> de lo contrario.
     */
    @Override
    public boolean realizarCompra(double total, Integer id) {
        Cliente c = baseDeDatosClientes.buscaCliente(id);
        if (c == null || !c.isSaldoSuficiente(total)) 
            return false;
        c.pagar(total);
        return true;
    }

    /**
     * Regresa el pais de origen del cliente.
     * @param id el nombre del cliente.
     * @return el pais de origen del cliente.
     */
    @Override
    public PaisDeOrigen getPaisDeOrigen(Integer id) {
        Cliente c = baseDeDatosClientes.buscaCliente(id);
        return c != null ? c.getPaisDeOrigen() : null;
    }

    /**
     * Regresa la oferta de la tienda.
     * @return la oferta de la tienda.
     */
    @Override
    public double getOferta() {
        return ofertas.get(PaisDeOrigen.ESTADOS_UNIDOS);
    }

    /**
     * Busca un producto en el catalogo y lo devuelve
     * @param codigo el codigo del producto
     * @return el producto
     */
    @Override
    public Producto buscarProducto(String codigo){
        return (Producto) catalogo.busca(codigo);
    }

    /**
     * Regresa el catalogo de productos de la tienda
     * @return el catalogo de productos de la tienda
     */
    public CatalogoComponente getCatalogoCopia(){
        return catalogo.copia();
    }

    /**
     * Asigna una oferta para esta sesion de la tienda.
     * @param paisDeOrigen pais de origen del cliente.
     */
    @Override
    public void asignarOferta(PaisDeOrigen paisDeOrigen){
        Random random = new Random();
        int oferta = random.nextInt(1);
        catalogo = FabricaSimplePaises.getCatalogoConDescuento(paisDeOrigen, oferta, cheemsMart.getCatalogoCopia());
    }

    /**
     * Regresa el catalogo de productos de la tienda.
     * @return el catalogo de productos de la tienda
     */
    @Override
    public Iterable<CatalogoComponente> getCatalogo(){
        return catalogo;
    }

    /**
     * Crea las ofertas de la tienda.
     */
    private void creaOfertas(){
        Random random = new Random();
        ofertas.put(PaisDeOrigen.MEXICO, random.nextInt(5) /10.0 );
        ofertas.put(PaisDeOrigen.ESTADOS_UNIDOS, random.nextInt(5) /10.0);
        ofertas.put(PaisDeOrigen.ESPANA, random.nextInt(5) /10.0);
    }

    /**
     * Regresa la oferta de un pais.
     * @param pais pais de origen.
     * @return la oferta de un pais.
     */
    public double getOfertaPais(PaisDeOrigen pais){
        return ofertas.get(pais);
    }

}

