package mx.unam.ciencias.modelado.proyecto2.igu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * <p>Clase para el controlador del contenido del dialogo para editar y crear
 * productos.</p>
 */
public class ControladorFormaDialogoTarjeta extends ControladorForma{

    /* La entrada verificable para el Cuenta de barras. */
    @FXML private EntradaVerificable entradaCuenta;
    /* La entrada verificable para el nombre. */
    @FXML private EntradaVerificable entradaNombre;
    /* La entrada verificable para la CVV. */
    @FXML private EntradaVerificable entradaCVV;

    /**
     * Inicializa el estado de la forma. 
     */
    @FXML private void initialize() {
        entradaCuenta.setVerificador(this::verificaCuenta);
        entradaNombre.setVerificador(this::verificaNombre);
        entradaCVV.setVerificador(this::verificaCVV);
        entradaCuenta.textProperty().addListener(
            (c, n, e) -> verificaTarjeta());
        entradaNombre.textProperty().addListener(
            (c, n,e) -> verificaTarjeta());
            entradaCVV.textProperty().addListener(
            (c, n, e) -> verificaTarjeta());
    }

    /**
     * Deshabilita la entrada de codigo de barras.
     */
    public void deshabilitaEntradaCuenta() {
        entradaCuenta.setDisable(true);
    }


    /* Manejador para cuando se activa el boton aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        aceptado = true;
        escenario.close();
    }

    /**
     * Define el verbo del boton de aceptar.
     * @param verbo el nuevo verbo del boton de aceptar.
     */
    public void setVerbo(String verbo) {
        botonAceptar.setText(verbo);
    }

    /**
     * Define el foco incial del dialogo.
     */
    @Override 
    public void defineFoco() {
        entradaCuenta.requestFocus();
    }

    /**
     * Verifica que la cuenta sea valida.
     * @param cuenta la cuenta a verificar.
     * @return <code>true</code> si la cuenta es valida, <code>false</code> en otro caso.
     */
    public boolean verificaCuenta(String cuenta) {
        return cuenta != null && !cuenta.isEmpty();
    }

    /**
     * Verifica que el nombre sea valido.
     * @param nombre el nombre a verificar.
     * @return <code>true</code> si el nombre es valido, <code>false</code> en otro caso.
     */
    public boolean verificaNombre(String nombre) {
        return nombre != null && !nombre.isEmpty();
    }

    /**
     * Verifica que el CVV sea valido.
     * @param cvv el CVV a verificar.
     * @return <code>true</code> si el CVV es valido, <code>false</code> en otro caso.
     */
    public boolean verificaCVV(String cvv) {
        if( cvv == null || cvv.isEmpty())
            return false;
        try {
            Integer.parseInt(cvv);
            if(cvv.length() != 3) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    /**
     * Regresa la cuenta.
     * @return la cuenta.
     */
    public String getCuenta() {
        return entradaCuenta.getText();
    }

    /**
     * Regresa el nombre.
     * @return el nombre.
     */
    public String getNombre() {
        return entradaNombre.getText();
    }

    /**
     * Regresa el CVV.
     * @return el CVV.
     */
    public String getCVV() {
        return entradaCVV.getText();
    }

    /**
     * Verifica que la tarjeta sea valida.
     */
    public void verificaTarjeta() {
        boolean c = entradaCuenta.esValida();
        boolean n = entradaNombre.esValida();
        boolean d = entradaCVV.esValida();
        botonAceptar.setDisable(!c || !n || !d);
    }

}
