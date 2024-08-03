package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Clase que representa una cuenta de banco. Una cuenta de banco tiene un numero
 * de cuenta, un nombre de titular, un cvv y un saldo.</p>
 */
public class CuentaBanco {

    /* El numero de cuenta. */
    private String cuenta;
    /* El nombre del titular. */
    private String nombreTitular;
    /* El cvv de la tarjeta. */
    private String cvv;
    /* El saldo de la cuenta. */
    private double saldo;

    /**
     * Define el estado inicial de la cuenta de banco.
     * @param cuenta el numero de cuenta.
     * @param nombreTitular el nombre del titular.
     * @param cvv el cvv de la tarjeta.
     * @param saldo el saldo de la cuenta.
     */
    public CuentaBanco(String cuenta, 
                        String nombreTitular, 
                        String cvv, 
                        double saldo) {
        this.cuenta = cuenta;
        this.nombreTitular = nombreTitular;
        this.cvv = cvv;
        this.saldo = saldo;
    }

    /**
     * Regresa el numero de cuenta.
     * @return el numero de cuenta.
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * Regresa el nombre del titular.
     * @return el nombre del titular.
     */
    public String getNombreTitular() {
        return nombreTitular;
    }

    /**
     * Regresa el cvv de la tarjeta.
     * @return el cvv de la tarjeta.
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Define el numero de cuenta.
     * @param cuenta el numero de cuenta.
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Define el nombre del titular.
     * @param nombreTitular el nombre del titular.
     */
    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    /**
     * Define el cvv de la tarjeta.
     * @param cvv el cvv de la tarjeta.
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * Regresa el saldo de la cuenta.
     * @return el saldo de la cuenta.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Define el saldo de la cuenta.
     * @param saldo el saldo de la cuenta.
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Deposita una cantidad en la cuenta.
     * @param cantidad la cantidad a depositar.
     */
    public void deposita(double cantidad) {
        saldo += cantidad;
    }

    /**
     * Retira una cantidad de la cuenta.
     * @param cantidad la cantidad a retirar.
     */
    public void retira(double cantidad) {
        saldo -= cantidad;
    }

    /**
     * Verifica si la cuenta tiene saldo suficiente para una cantidad dada.
     * @param cantidad la cantidad a verificar.
     * @return <code>true</code> si la cuenta tiene saldo suficiente, <code>false</code> en otro caso.
     */
    public boolean tieneSaldo(double cantidad) {
        return saldo >= cantidad;
    }


}
