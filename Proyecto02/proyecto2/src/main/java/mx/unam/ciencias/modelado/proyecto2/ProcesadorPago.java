package mx.unam.ciencias.modelado.proyecto2;

import java.util.HashMap;

/**
 * <p>Clase que define el procesador de pagos. Permite realizar pagos con diferentes formas de pago.</p>
 */
public class ProcesadorPago {

    /* La forma de pago. */
    private FormaPago formaPago;

    /**
     * Constructor de la clase.
     */
    public ProcesadorPago() {
        this.formaPago = null;
    }

    /**
     * Metodo que asigna una forma de pago.
     * @param formaPago la forma de pago a asignar
     */
    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * Metodo que verifica si hay una forma de pago.
     * @return true si hay una forma de pago, false en otro caso
     */
    public boolean hayFormaPago() {
        return formaPago != null;
    }

    /**
     * Metodo que realiza un pago.
     * @param monto el monto a pagar
     * @param infoPago la informacion de pago
     * @return true si el pago se realizo, false en otro caso
     */
    public boolean realizarPago(double monto, HashMap<String, String> infoPago, String beneficiario) {
        if (formaPago != null) {
            return formaPago.pagar(monto, infoPago, beneficiario);
        }
        return false;
    }

    /**
     * Metodo que verifica si el pago es con tarjeta.
     * @return true si el pago es con tarjeta, false en otro caso
     */
    public boolean esPagoConTarjeta() {
        return formaPago instanceof PagoConTarjeta;
    }

    /**
     * Metodo que regresa el mensaje de pago.
     * @param monto el monto a pagar
     * @param vendedor el vendedor
     * @return el mensaje de pago
     */
    public String mensajePago(double monto, InterfazVendedor vendedor) {
        if (formaPago != null) {
            return formaPago.mensajePago(monto, vendedor);
        }
        return "No hay forma de pago";
    }

    /**
     * Metodo que verifica la informacion de pago.
     * @param nombre nombre del titular de pago
     * @param numero nuero de cuenta
     * @param cvv cvv de la tarjeta
     * @return true si la informacion es correcta, false en otro caso
     */
    public boolean verificarInfoDePago(HashMap<String, String> infoPago, InterfazUsuario usuario) {
        if (formaPago != null) 
            return formaPago.verificarInfoDePago(infoPago, usuario);
        return false;
    }
}

