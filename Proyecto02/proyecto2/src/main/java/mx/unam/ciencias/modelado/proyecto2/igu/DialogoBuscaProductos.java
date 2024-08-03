package mx.unam.ciencias.modelado.proyecto2.igu;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.unam.ciencias.modelado.proyecto2.CampoProducto;

/**
 * <p>Clase para dialogos con formas de busquedas de productos.</p>
 */
public class DialogoBuscaProductos extends Stage {

    /* Vista de la forma para realizar busquedas de productos. */
    private static final String BUSCA_PRODUCTOS_FXML =
        "fxml/forma-busca-productos.fxml";

    /* El controlador. */
    private ControladorFormaBuscaProductos controlador;

    /**
     * Define el estado inicial de un dialogo para busquedas de productos.
     * @param escenario el escenario al que el dialogo pertenece.
     * @throws IOException si no se puede cargar el archivo FXML.
     */
    public DialogoBuscaProductos(Stage escenario) throws IOException {
        ClassLoader cl = getClass().getClassLoader();
        FXMLLoader cargador = new FXMLLoader(cl.getResource(BUSCA_PRODUCTOS_FXML));
        AnchorPane cristal = (AnchorPane)cargador.load();
        setTitle("Buscar productos");
        initOwner(escenario);
        initModality(Modality.WINDOW_MODAL);
        Scene escena = new Scene(cristal);
        setScene(escena);
        controlador = cargador.getController();
        controlador.setEscenario(this);
        setOnShown(w -> controlador.defineFoco());
        setResizable(false);
    }

    /**
     * Nos dice si el usuario activo el boton de aceptar.
     * @return <code>true</code> si el usuario activo el boton de aceptar,
     *         <code>false</code> en otro caso.
     */
    public boolean isAceptado() {
        return controlador.isAceptado();
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public CampoProducto getCampo() {
        return controlador.getCampo();
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public Object getValor() {
        return controlador.getValor();
    }
}
