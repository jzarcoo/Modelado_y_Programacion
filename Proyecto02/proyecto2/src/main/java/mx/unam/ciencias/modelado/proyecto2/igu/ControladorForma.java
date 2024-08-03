package mx.unam.ciencias.modelado.proyecto2.igu;

import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * <p>Clase abstracta para controladores del contenido de dialogo con formas que se
 * aceptan o rechazan.</p>
 */
public abstract class ControladorForma {

    /** El boton para aceptar. */
    @FXML protected Button botonAceptar;

    /** La ventana del dialogo. */
    protected Stage escenario;
    
    /** Si el usuario acepto la forma. */
    protected boolean aceptado;

    /**
     * Manejador para cuando se activa el boton cancelar.
     * @param evento el evento que genero la accion.
     */
    @FXML protected void cancelar(ActionEvent evento) {
        aceptado = false;
        escenario.close();
    }

    /**
     * Define el escenario del dialogo.
     * @param escenario el nuevo escenario del dialogo.
     */
    public void setEscenario(Stage escenario) {
        this.escenario = escenario;
        Scene escena = escenario.getScene();
        KeyCodeCombination combinacion;
        combinacion = new KeyCodeCombination(KeyCode.ENTER,
                                            KeyCombination.CONTROL_DOWN);
        ObservableMap<KeyCombination, Runnable> accs = escena.getAccelerators();
        accs.put(combinacion, () -> botonAceptar.fire());
    }

    /**
     * Nos dice si el usuario activo el boton de aceptar.
     * @return <code>true</code> si el usuario activo el boton de aceptar,
     *         <code>false</code> en otro caso.
     */
    public boolean isAceptado() {
        return aceptado;
    }

    /**
     * Define el foco incial del dialogo.
     */
    public abstract void defineFoco();
}
