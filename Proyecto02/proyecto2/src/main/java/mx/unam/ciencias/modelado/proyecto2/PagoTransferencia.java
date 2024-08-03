package mx.unam.ciencias.modelado.proyecto2;

import java.util.HashMap;

/**
 * <p>Clase que define el pago por transferencia.</p>
 */
public class PagoTransferencia implements FormaPago{
    
    /**
     * Metodo que regresa el mensaje de pago por transferencia.
     * @param cantidad la cantidad a pagar
     * @param vendedor el vendedor
     * @return el mensaje de pago por transferencia
     */
    @Override
    public String mensajePago(double cantidad, InterfazVendedor vendedor) {
        return "Debes realizar el pago por transferencia de: " + cantidad + " a la cuenta " + vendedor.getNumeroCuenta() + " a nombre de " + vendedor.getNombreUsuario() + " el dia de entrega que puedes acordar comunicandote al telefono: " + vendedor.getTelefono();
    }

    /**
     * Metodo que verifica la informacion de pago.
     * @param infoPago la informacion de pago
     * @return true ya que el pago por transferencia siempre es exitoso
     */
    @Override
    public boolean verificarInfoDePago(HashMap<String, String> infoPago, InterfazUsuario usuario) {
        return true;
    }

    /**
     * Metodo que realiza el pago por transferencia.
     * @param cantidad la cantidad a pagar
     * @param infoPago la informacion de pago
     * @param beneficiario el beneficiario del pago
     * @return true ya que el pago por transferencia siempre es exitoso
     */
    @Override
    public boolean pagar(double cantidad, HashMap<String, String> infoPago,  String beneficiario) {
        return true;
    }
}
