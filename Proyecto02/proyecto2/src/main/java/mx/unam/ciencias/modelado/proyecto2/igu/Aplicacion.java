package mx.unam.ciencias.modelado.proyecto2.igu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * <p>Clase para los usuarios del sistema con interfaz grafica del servidor.</p>.
 */
public class Aplicacion extends Application {

    /* Vista de la interfaz del inicio de sesion de la mercadita */
    private static final String INTERFAZ_MERCADITA_FXML = "fxml/interfaz-mercadita.fxml";

    /* icono de la Facultad de Ciencias. */
    private static final String ICONO_CIENCIAS = "icons/ciencias.png";

    /**
     * Inicia la aplicacion.
     * @param escenario la ventana principal de la aplicacion.
     * @throws Exception si algo sale mal.
     */
    @Override public void start(Stage escenario) throws Exception {
        try{
            ClassLoader cl = getClass().getClassLoader();
            String url = cl.getResource(ICONO_CIENCIAS).toString();
            escenario.getIcons().add(new Image(url));
            escenario.setTitle("Mercadita");

            FXMLLoader cargador = new FXMLLoader(cl.getResource(INTERFAZ_MERCADITA_FXML));
            BorderPane cristal = (BorderPane) cargador.load();
            ControladorInterfazMercadita controlador = cargador.getController();
            controlador.setEscenario(escenario);

            Scene escena = new Scene(cristal);
            escenario.setScene(escena);
            escenario.setOnCloseRequest(e -> controlador.salir(e));
            escenario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
