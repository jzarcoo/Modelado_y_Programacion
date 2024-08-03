package src;

/**
 * <p>Interfaz para representar a los clientes de la aplicacion.</p>
 */
public interface Cliente {

    /**
     * Verifica si el saldo de la cuenta es suficiente para realizar un pago del monto especificado.
     * @param total el monto total del pago a verificar.
     * @return <code>true</code> si el saldo es suficiente para realizar el pago, 
     *         <code>false</code> de lo contrario.
     */
    public boolean isSaldoSuficiente(double total);

    /**
     * Define el pago de un monto especificado desde la cuenta.
     * @param total el monto total del pago a realizar.
     */
    public void pagar(double total);

    /**
     * Regresa la contrasena del Cliente.
     * @return contrasena del Cliente.
     */
    public String getContrasena();

    /**
     * Determina si la cuenta ingresada por el Cliente es correcta.
     * @param cuenta cuenta bancaria del Cliente.
     * @return <code>true</code> si la cuenta es correcta, <code>false</code> de lo contrario.
     */
    public boolean verificarCuentaBancaria(String cuenta);

    /**
     * Regresa el pais de origen del Cliente.
     * @return pais del origen del Cliente.
     */
    public PaisDeOrigen getPaisDeOrigen();
    
    /*
     * Regresa el nombre de usuario del Cliente.
     * @return nombre de usuario del cleinte
     */
    public String getNombreUsuario();

    /*
     * Regresa el ID  del Cliente.
     * @return ID del cliente
     */
    public int getId();
}