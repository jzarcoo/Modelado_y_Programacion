package mx.unam.ciencias.modelado.proyecto2.igu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import mx.unam.ciencias.modelado.proyecto2.CampoProducto;

/**
 * <p>Clase para el controlador del contenido del dialogo para buscar productos.</p>
 */
public class ControladorFormaBuscaProductos extends ControladorFormaProducto {

    /* El combo del campo. */
    @FXML private ComboBox<CampoProducto> opcionesCampo;
    
    /* El campo de texto para el valor. */
    @FXML private EntradaVerificable entradaValor;

    /**
     * Inicializa el estado de la forma.
     */
    @FXML private void initialize() {
        entradaValor.setVerificador(s -> verificaValor(s));
        entradaValor.textProperty().addListener((o, v, n) -> revisaValor(null));
    }

    /** 
     * Revisa el valor despues de un cambio.
     * @param evento el evento que genero la accion.
     */
    @FXML private void revisaValor(ActionEvent evento) {
        Tooltip.install(entradaValor, getTooltip());
        botonAceptar.setDisable(!entradaValor.esValida());
    }

    /**
     * Manejador para cuando se activa el boton aceptar.
     * @param evento el evento que genero la accion.
     */
    @FXML private void aceptar(ActionEvent evento) {
        aceptado = true;
        escenario.close();
    }

    /** 
     * Verifica el valor.
     * @param valor el valor a verificar.
     */
    private boolean verificaValor(String valor) {
        switch (opcionesCampo.getValue()) {
        case CODIGO_DE_BARRAS: return verificaCodigoBarras(valor);
        case NOMBRE: return verificaNombre(valor);
        case DESCRIPCION: return verificaDescripcion(valor);
        case PRECIO: return verificaPrecio(valor);
        case STOCK_DISPONIBLES: return verificaStockDisponible(valor);
        case CATEGORIA: return verificaCategoria(valor);
        default: return false; // No puede ocurrir.
        }
    }

    /**
     * Obtiene la pista.
     */
    private Tooltip getTooltip() {
        String m = "";
        switch (opcionesCampo.getValue()) {
        case CODIGO_DE_BARRAS:
            m = "Buscar por codigo de barras necesita al menos un caracter";
            break;
        case NOMBRE:
            m = "Buscar por nombre necesita al menos un caracter";
            break;
        case DESCRIPCION:
            m = "Buscar por descripcion necesita al menos un caracter";
            break;
        case PRECIO:
            m = "Buscar por precio necesita un numero mayor a 0";
            break;
        case STOCK_DISPONIBLES:
            m = "Buscar por stock disponible necesita un numero mayor a 0";
            break;
        case CATEGORIA:
            m = "Buscar por categoria necesita al menos un caracter";
            break;
        default: break; // No puede ocurrir.
        }
        return new Tooltip(m);
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public Object getValor() {
        switch (opcionesCampo.getValue()) {
        case CODIGO_DE_BARRAS: return entradaValor.getText();
        case NOMBRE: return entradaValor.getText();
        case DESCRIPCION: return entradaValor.getText();
        case PRECIO: return Double.parseDouble(entradaValor.getText());
        case STOCK_DISPONIBLES: return Integer.parseInt(entradaValor.getText());
        case CATEGORIA: return entradaValor.getText();
        default: return entradaValor.getText(); // No puede ocurrir.
        }
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public CampoProducto getCampo() {
        return opcionesCampo.getValue();
    }

    /**
     * Define el foco incial del dialogo.
     */
    @Override public void defineFoco() {
        entradaValor.requestFocus();
    }
}
