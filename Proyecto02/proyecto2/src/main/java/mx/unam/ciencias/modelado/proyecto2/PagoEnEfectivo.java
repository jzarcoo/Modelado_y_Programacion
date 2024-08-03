package mx.unam.ciencias.modelado.proyecto2;

import java.util.HashMap;

/**
 * <p>Clase que define el pago en efectivo.</p>
 */
public class PagoEnEfectivo implements FormaPago {

    /*
     * Metodo que regresa el mensaje de pago en efectivo.
     * @param cantidad - la cantidad a pagar.
     * @param vendedor - el vendedor al que se le pagara.
     * @return el mensaje de pago en efectivo.
     */
    @Override
    public String mensajePago(double cantidad, InterfazVendedor vendedor) {
        return "Debes realizar el pago en efectivo de: " + cantidad+ " el dia de entrega a "+ vendedor.getNombreUsuario()+ " que puedes acordar comunicandote al telefono "+ vendedor.getTelefono();
    }

    /*
     * Metodo que verifica la informacion de pago.
     * @param infoPago - la informacion de pago.
     * @return true ya que el pago en efectivo siempre es exitoso.
     */
    @Override
    public boolean verificarInfoDePago(HashMap<String, String> infoPago, InterfazUsuario usuario) {
        return true;
    }

    /*
     * Metodo que realiza el pago en efectivo.
     * @param cantidad - la cantidad a pagar.
     * @param infoPago - la informacion de pago.
     * @param beneficiario - el beneficiario del pago.
     * @return true ya que el pago en efectivo siempre es exitoso.
     */
    @Override
    public boolean pagar(double cantidad, HashMap<String, String> infoPago,  String beneficiario) {
        return true;
    }
    
}