package src;

/*
 * <p>Clase para representar una cuenta bancaria de un cliente. Una cuenta bancaria
 * contiene un dinero y un numero de cuenta.</p>
 * 
 * <p>Nos permite realizar pagos y verificar si el saldo es suficiente para realizar
 * un pago.</p>
 */
public class CuentaBancaria {
    
    /* Dinero de la Cuenta bancaria. */
    private double dinero;
    /* Numero de cuenta de la Cuenta bancaria. */
    private String numeroCuenta;

    /**
     * Define el estado inicial de la Cuenta bancaria.
     * @param dinero dinero de la cuenta bancaria.
     * @param numeroCuenta numero de cuenta.
     */
    public CuentaBancaria(double dinero, 
                        String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.dinero = dinero;
    }
    
    /**
     * Regresa el dinero de la cuenta.
     * @return dinero de la cuenta.
     */
    public double getDinero() {
        return dinero;
    }

    /**
     * Define el nuevo dinero de la cuenta.
     * @param dinero el dinero a establecer.
     */
    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    /**
     * Regresa el numero de la cuenta.
     * @return numero de la cuenta.
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Define el nuevo numero de cuenta.
     * @param numeroCuenta el numero de cuenta a establecer.
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Realiza un pago de una cantidad especificada.
     * @param cantidad la cantidad a retirar.
     */
    public void pagar(double cantidad) {
        if (!isSaldoSuficiente(cantidad))
            System.err.println("No tienes suficiente dinero en tu cuenta");
        else
            dinero -= cantidad;
    }

    /**
     * Verificar si el dinero de la cuenta es suficiente para pagar un monto.
     * @param cantidad la cantidad a retirar.
     * @return <code>true</code> si el saldo es suficiente, <code>false</code> de lo contrario.
     */
    public boolean isSaldoSuficiente(double cantidad) {
        return cantidad <= dinero;
    }
    
    /**
     * Nos dice si el objeto recibido es igual al que manda llamar 
     * el metodo.
     * @param obj el objeto con el que se comparara.
     * @return <code>true</code> si el objeto recibido es una cuenta bancaria con las 
     *          mismas propiedades que la que manda llamar el metodo, <code>false</code>
     *          en otro caso.
     */
    @Override
    public boolean equals (Object obj) {
        if (obj == null || !(obj instanceof CuentaBancaria))
            return false;
        CuentaBancaria cuenta = (CuentaBancaria) obj;
        return cuenta.getNumeroCuenta().equals(numeroCuenta) && cuenta.getDinero() == dinero;
    }

    /**
     * Regresa una representacion en cadena de la cuenta bancaria.
     * @return una representacion en cadena de la cuenta bancaria.
     */
    @Override
    public String toString() {
        return "CuentaBancaria{" + "dinero=" + dinero + ", numeroCuenta=" + numeroCuenta + '}';
    }
}
