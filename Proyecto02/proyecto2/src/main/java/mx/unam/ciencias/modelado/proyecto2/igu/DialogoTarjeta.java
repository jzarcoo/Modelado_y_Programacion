package mx.unam.ciencias.modelado.proyecto2.igu;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * <p>Clase para definir un dialogo para obtener datos de tarjeta.</p>
 */
public class DialogoTarjeta extends Stage{

    /* Vista de la forma para ingresar datos de tarjeta. */
    private static final String TARJETA = "fxml/formaDialogoTarjeta.fxml";
    
    /* El controlador. */
    private ControladorFormaDialogoTarjeta controlador;

    /**
     * Define el estado inicial de dialogo para obtener datos de tarjeta.
     * @param escenario el escenario al que el dialogo pertenece.
     * @throws IOException si no se puede cargar el archivo FXML.
     */
    public DialogoTarjeta(Stage escenario) throws IOException {
        ClassLoader cl = getClass().getClassLoader();
        FXMLLoader cargador = new FXMLLoader(cl.getResource(TARJETA));
        AnchorPane cristal = (AnchorPane)cargador.load();
        setTitle("Ingresa datos de tarjeta:");
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
     * Regresa el cvv
     * @return el cvv
     */
    public String getCVV() {
        return controlador.getCVV();
    }

    /**
     * Regresa el nombre del titular
     * @return el nombre del titular
     */
    public String getNombreTitular() {
        return controlador.getNombre();
    }

    /**
     * Regresa el numero de cuenta
     * @return el numero de cuenta
     */
    public String getCuenta() {
        return controlador.getCuenta();
    }
}
