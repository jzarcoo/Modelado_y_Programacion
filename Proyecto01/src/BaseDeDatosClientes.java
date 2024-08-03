package src;

import java.util.HashMap;

/**
 * <p>Clase para representar la base de datos de los clientes registrados de la tienda.</p> 
 */
public class BaseDeDatosClientes {

    /* Clientes de la tienda. */
    private HashMap<Integer, Cliente> clientes;

    /* Unica instancia de la base de datos tienda. */
    // Variable comprartida entre todos los hilos pues esta en memoria principal.
    private volatile static BaseDeDatosClientes baseDeDatosTiendaClientes;

    /**
     * Define el estado inicial de la base de datos de la tienda.
     */
    private BaseDeDatosClientes() {
        this.clientes = new HashMap<Integer, Cliente>();
        carga();
    }

    /**
     * Regresa la instancia de la base de datos de la tienda.
     * Este metodo implementa el patron Singleton, debe ser sincronizado en 
     * caso de que dos o mas hilos accedan al metodo a la vez. Ademas, realiza
     * un bloque comprobado doble para garantizar que la instancia sea unica.
     * @return la instancia de la base de datos de la tienda.
     */
	public static BaseDeDatosClientes getInstance(){
		if (baseDeDatosTiendaClientes == null) {
			synchronized (BaseDeDatosClientes.class) {
				if (baseDeDatosTiendaClientes == null) {
					baseDeDatosTiendaClientes = new BaseDeDatosClientes();
                }
			}
		}
		return baseDeDatosTiendaClientes;
	}

    /**
     * Carga los clientes de la tienda.
     */
    private void carga() {
        agregarCliente(new ClienteReal("Pancho", "1234", "Francisco Rodriguez", "5528203722", "Tlalpan, CDMX", new CuentaBancaria(1500, "1234567890"), PaisDeOrigen.MEXICO, 1));
        agregarCliente(new ClienteReal("Luillilol", "c0ntr4senA", "Luis Perez", "5587203732", "Venustiano Carranza, CDMX", new CuentaBancaria(99999, "DFNF93RR"), PaisDeOrigen.ESTADOS_UNIDOS, 2) );
        agregarCliente(new ClienteReal("Lalo", "0123", "Eduardo", "5561443440", "Espana", 10, "DJNFKABFVR9JF", PaisDeOrigen.ESPANA ,3));
    }

    /**
     * Obtiene el numero de clientes en la tienda.
     * @return Numero de clientes en la tienda.
     */
    public int getNumClientes() {
        return clientes.size();
    }

    /**
     * Obtiene una copia de la lista de clientes.
     * @return Copia de la lista de clientes.
     */
    @SuppressWarnings("unchecked")
    public HashMap<Integer, Cliente> getClientes() {
        //@SuppressWarnings("unchecked")
        return (HashMap<Integer, Cliente>) clientes.clone();
    }

    /**
     * Limpia la lista de clientes.
     */
    public void limpia() {
        clientes.clear();
    }

    /**
     * Agrega un cliente a la lista de clientes.
     * @param cliente Cliente a agregar.
     */
    public void agregarCliente(Cliente cliente) {
        
        clientes.put(cliente.getId(), cliente);
    }

    /**
     * Busca un cliente por el nombre de usuario.
     * @param nombreUsuario Nombre de usuario del cliente a buscar.
     * @return Cliente encontrado, o <code>null</code> si no existe el cliente.
     */
    public Cliente buscaCliente(String nombreUsuario) {
        for (Cliente c : clientes.values()) {
            if (c.getNombreUsuario().equals(nombreUsuario)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Busca un cliente por su id.
     * @param id Id del cliente a buscar.
     * @return Cliente encontrado, o <code>null</code> si no existe el cliente.
     */
    public Cliente buscaCliente(int id) {
        return clientes.get(id);
    }

}
