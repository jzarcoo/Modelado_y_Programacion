package mx.unam.ciencias.modelado.proyecto2;

import java.util.HashMap;

/**
 * Interfaz que define los metodos que deben implementar las formas de pago.
 */
public interface FormaPago {

    /**
     * Metodo que regresa el mensaje de pago.
     * @param cantidad la cantidad a pagar
     * @param vendedor el vendedor
     * @return el mensaje de pago
     */
    public String mensajePago(double cantidad, InterfazVendedor vendedor);

    /**
     * Metodo que verifica la informacion de pago.
     * @param infoPago la informacion de pago
     * @return true si la informacion de pago es correcta, false en otro caso
     */
    public boolean verificarInfoDePago(HashMap<String, String> infoPago, InterfazUsuario usuario) ;

    /**
     * Metodo que realiza el pago.
     * @param cantidad la cantidad a pagar
     * @param infoPago la informacion de pago
     * @param beneficiario el beneficiario del pago
     * @return true si el pago se realizo, false en otro caso
     */
    public boolean pagar(double cantidad, HashMap<String, String> infoPago, String beneficiario);


}
