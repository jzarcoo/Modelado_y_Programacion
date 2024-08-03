package mx.unam.ciencias.modelado.proyecto2;

import java.util.HashMap;

/**
 * <p>Clase que define el pago con tarjeta.</p>
 */
public class PagoConTarjeta implements FormaPago {

    /* El banco. */
    private Banco banco;

    /**
     * Constructor de la clase.
     * @param banco el banco
     */
    public PagoConTarjeta(Banco banco) {
        this.banco = banco;
    }

    /**
     * Metodo que regresa el mensaje de pago con tarjeta.
     * @param cantidad la cantidad a pagar
     * @param vendedor el vendedor
     * @return el mensaje de pago con tarjeta
     */
    @Override
    public String mensajePago(double cantidad, InterfazVendedor vendedor) {
        return "Pago realizado con tarjeta de: " + cantidad+ " contacta a: "+ vendedor.getNombreUsuario() + " al número de teléfono: " + vendedor.getTelefono() + " para coordinar la entrega.";
    }

    /**
     * Metodo que verifica la informacion de pago.
     * @param infoPago la informacion de pago
     * @return true si la informacion de pago es correcta, false en otro caso
     */
    @Override
    public boolean verificarInfoDePago(HashMap<String, String> infoPago, InterfazUsuario usuario) {
        if(!usuario.getNumeroCuenta().equals(infoPago.get("cuenta")))
            return false;
        return banco.cuentaValida(infoPago);
    }

    /**
     * Metodo que realiza el pago con tarjeta.
     * @param cantidad la cantidad a pagar
     * @param infoPago la informacion de pago
     * @param beneficiario el beneficiario del pago
     * @return true si el pago se realizo, false en otro caso
     */
    @Override
    public boolean pagar(double cantidad, HashMap<String, String> infoPago, String beneficiario){   
        if (banco.autorizaPago(cantidad, infoPago.get("cuenta"), beneficiario)){
            banco.pagar(infoPago.get("cuenta"), cantidad, beneficiario);
            return true;
        } else {
            return false;
        }
    }
}