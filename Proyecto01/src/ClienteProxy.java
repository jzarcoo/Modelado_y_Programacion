package src;

/**
 * <p>Clase para representar a los sustitutos de los clientes de la aplicacion. 
 * Esta clase actua como un proxy para los clientes reales.</p>
 * 
 * <p>Esta clase implementa la interfaz Cliente, por lo que define los metodos:</p>
 * <ul>
 * <li>{@link #isSaldoSuficiente(double)}</li>
 * <li>{@link #pagar(double)}</li>
 * <li>{@link #getContrasena()}</li>
 * <li>{@link #getCuenta()}</li>
 * <li>{@link #getPaisDeOrigen()}</li>
 * <li>{@link #getNombreUsuario()}</li>
 * </ul>
 */
public class ClienteProxy implements Cliente {
    
    /* Cliente real. */
    private ClienteReal cliente;

    /**
     * Define el estado inicial del ClienteProxy.
     * @param cliente el cliente real.
     */
    public ClienteProxy(ClienteReal cliente) {
        this.cliente = cliente;
    }

    /**
     * Verifica si el saldo de la cuenta es suficiente para realizar un pago del monto especificado.
     * @param total el monto total del pago a verificar.
     * @return <code>true</code> si el saldo es suficiente para realizar el pago, 
     *         <code>false</code> de lo contrario.
     */
    @Override
    public boolean isSaldoSuficiente(double total) {
        return cliente.isSaldoSuficiente(total);
    }

    /**
     * Define el pago de un monto especificado desde la cuenta.
     * @param total el monto total del pago a realizar.
     */
    @Override
    public void pagar(double total) {
        cliente.pagar(total);
    }
    
    /**
     * Regresa la contrasena del Cliente.
     * @return contrasena del Cliente.
     */
    @Override
    public String getContrasena() {
        return cliente.getContrasena();
    }

    /**
     * Regresa la cuenta bancaria del Cliente.
     * @return cuenta bancaria del Cliente.
     */
    @Override
    public boolean verificarCuentaBancaria(String cuenta) {
        return cliente.verificarCuentaBancaria(cuenta);
    }

    /**
     * Regresa el pais de origen del Cliente.
     * @return pais del origen del Cliente.
     */
    @Override
    public PaisDeOrigen getPaisDeOrigen() {
        return cliente.getPaisDeOrigen();
    }

    /*
     * Regresa el nombre de usuario del Cliente.
     * @return nombre de usuario del cleinte
     */
    public String getNombreUsuario(){
        return cliente.getNombreUsuario();
    }

    /*
     * Regresa el ID  del Cliente.
     * @return ID del cliente
     */
    public int getId(){
        return cliente.getId();
    }

}