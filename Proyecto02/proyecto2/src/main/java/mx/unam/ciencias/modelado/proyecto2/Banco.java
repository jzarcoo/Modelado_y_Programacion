package mx.unam.ciencias.modelado.proyecto2;

import java.util.HashMap;

/**
 * <p>Clase para definir un banco.</p>
 */
public class Banco {

    /* Las cuentas bancarias. */
    private HashMap<String, CuentaBanco> cuentas;

    /**
     * Define el estado inicial de un banco.
     */
    public Banco() {
        cuentas = new HashMap<>();
        llenaBase();
    }

    /**
     * Llena la base de datos con cuentas bancarias.
     */
    public void llenaBase() {
        // Ejemplos de agregar cuentas bancarias
        agregaCuenta("1234567890123456", new CuentaBanco("1234567890123456", "Susana Duarte", "123", 8000));
        agregaCuenta("6789012345678901", new CuentaBanco("6789012345678901", "Mario Lopez", "321", 3000));
        agregaCuenta("2345678901234567", new CuentaBanco("2345678901234567", "Laura Martinez", "456", 15000));
        agregaCuenta("7890123456789012", new CuentaBanco("7890123456789012", "Antonio Sanchez", "654", 5500));
        agregaCuenta("4567890123456780", new CuentaBanco("4567890123456780", "Tamara Gomez", "789", 2800));
    }

    /**
     * Agrega una cuenta bancaria al banco.
     * @param cuenta la cuenta bancaria.
     * @param cuentaBanco la cuenta bancaria.
     */
    public void agregaCuenta(String cuenta, CuentaBanco cuentaBanco) {
        cuentas.put(cuenta, cuentaBanco);
    }

    /**
     * Nos dice si se autoriza un pago.
     * @param cantidad la cantidad a pagar.
     * @param cuenta la cuenta a pagar.
     * @param beneficiario el beneficiario.
     * @return <code>true</code> si se autoriza el pago, <code>false</code> en otro caso.
     */
    public boolean autorizaPago(double cantidad, String cuenta, String beneficiario) {
        CuentaBanco cuentaBanco = cuentas.get(cuenta);
        CuentaBanco cuentaBeneficiario = cuentas.get(beneficiario);
        if (cuentaBanco == null || cuentaBeneficiario == null) {
            return false;
        }
        return cuentaBanco.tieneSaldo(cantidad);
        
    }

    /**
     * Paga una cantidad a un beneficiario.
     * @param cuenta la cuenta a pagar.
     * @param cantidad la cantidad a pagar.
     * @param beneficiario el beneficiario.
     */
    public void pagar(String cuenta, double cantidad, String beneficiario) {
        CuentaBanco cuentaBanco = cuentas.get(cuenta);
        CuentaBanco cuentaBeneficiario = cuentas.get(beneficiario);
        if (cuentaBanco == null || cuentaBeneficiario == null) {
            return;
        }
        cuentaBanco.retira(cantidad);
        cuentaBeneficiario.deposita(cantidad);
    }

    /**
     * Nos dice si una cuenta es valida.
     * @param infoPago la informacion de la cuenta.
     * @return <code>true</code> si la cuenta es valida, <code>false</code> en otro caso.
     */
    public boolean cuentaValida(HashMap<String, String> infoPago) {
        String cuenta = infoPago.get("cuenta");
        String nombreTitular = infoPago.get("nombre");
        String cvv = infoPago.get("cvv");
        if (cuentas.containsKey(cuenta)){
            CuentaBanco cuentaBanco = cuentas.get(cuenta);
            return cuentaBanco.getNombreTitular().equals(nombreTitular) && cuentaBanco.getCvv().equals(cvv);
        } else {
            return false;
        }
    }
}
