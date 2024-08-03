package mx.unam.ciencias.modelado.proyecto2.igu;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;
import mx.unam.ciencias.modelado.proyecto2.InterfazCliente;
import mx.unam.ciencias.modelado.proyecto2.InterfazMercadita;
import mx.unam.ciencias.modelado.proyecto2.InterfazUsuario;

/**
 * <p>Clase abstracta que define el comportamiento de los controladores de lasinterfaces graficas de usuario.</p>
 */
public abstract class Controlador {

    /* La ventana. */
    protected Stage escenario;

    /* Vista de la interfaz de la tienda del vendedor. */   
    protected static final String INTERFAZ_TIENDA_VENDEDOR = "fxml/vista-tiendaVendedor.fxml";

    /* Vista de la interfaz de la mercadita como cliente. */
    protected static final String INTERFAZ_MERCADITA_FXML = "fxml/vista-usuario.fxml";

    /* Vista de la interfaz de la mercadita inicio de sesion. */
    protected static final String INTERFAZ_INICIO_SESION = "fxml/interfaz-mercadita.fxml";

    /* Vista de la interfaz de la mercadita como vendedor */
    protected static final String INTERFAZ_VENDEDOR_FXML = "fxml/vista-vendedor.fxml";

    /*Vista de la interfaz de la mercadita como administrador */
    protected static final String INTERFAZ_ADMINISTRADOR_FXML = "fxml/vista-administrador.fxml";

    /*Vista de la interfaz de la mercadita  al momento de realizar la compra*/
    protected static final String INTERFAZ_VISTA_COMPRA = "fxml/vista-usuario-compra.fxml";

     /* icono de la Facultad de Ciencias. */
    protected static final String ICONO_CIENCIAS = "icons/ciencias.png";

    /* La interfaz de la mercadita. */
    protected static InterfazMercadita mercadita;

    /*El usuario actual */
    protected static InterfazUsuario usuarioActual; 

    /* El texto para la temperatura maxima */
    @FXML protected Text tempMaxText;

    /* El texto para la temperatura minima */
    @FXML protected Text tempMinText;

    /* El texto para la probabilidad de precipitacion */
    @FXML protected Text probPrepText;

    /* El texto para la descripcion del cielo */
    @FXML protected Text cieloText;

    /* El texto para el nombre del usuario */
    @FXML protected Text nombreUsuario;   
    
    /**
     * Inicializa la interfaz grafica.Se encarga de poner el nombre del usuario en la interfaz grafica.
     */
    @FXML 
    protected void initialize() {
        nombreUsuario.setText(usuarioActual.getNombreUsuario());
        ponerNombreUsuario();
    }

    /**
     * Pone el nombre del usuario en la interfaz grafica.
     */
    protected void ponerNombreUsuario() {
        //Que quede centrado el nombre del usuario
        int mitad = nombreUsuario.getText().length() / 2;
        int tamano = mitad * 10;
        int columna = 164;
        nombreUsuario.setLayoutX(columna/2 - tamano);
    }

    /**
     * Metodo que se encarga de establecer el escenario de la interfaz grafica.
     * @param escenario el escenario de la interfaz grafica.
     */
    public void setEscenario(Stage escenario) {
        this.escenario = escenario;
    }

    /**
     * Define el usuario actual.
     * @param usuario el nuevo usuario actual.
     */
    public static void setUsuarioActual(InterfazUsuario usuario) {
        usuarioActual = usuario;
    }

    /**
     * Muestra una alerta de error 
     * @param titulo el titulo de la alerta.
     * @param mensaje el mensaje de la alerta.
    */
    protected void dialogoError(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Crear un dialogo de confirmacion.
     * @param titulo el titulo del dialogo.
     * @param mensaje el mensaje del dialogo.
     * @param pregunta la pregunta del dialogo.
     * @param aceptar el texto del boton aceptar.
     * @param cancelar el texto del boton cancelar.
     * @return <code>true</code> si el usuario acepta, <code>false</code> en otro caso.
     */
    protected boolean dialogoDeConfirmacion(String titulo,
                                            String mensaje,
                                            String pregunta,
                                            String aceptar,
                                            String cancelar) {
        Alert dialogo = new Alert(AlertType.CONFIRMATION);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(mensaje);
        dialogo.setContentText(pregunta);
        ButtonType si = new ButtonType(aceptar);
        ButtonType no = new ButtonType(cancelar, ButtonData.CANCEL_CLOSE);
        dialogo.getButtonTypes().setAll(si, no);
        Optional<ButtonType> resultado = dialogo.showAndWait();
        if(!resultado.isPresent())
            return false;
        return resultado.get() == si;
    }

    /**
     * Devuelve al inicio de sesion
     * @param evento el evento que desencadena la accion.
     */
    @FXML 
    public void cierraSesion(Event evento) {
        if(usuarioActual.getRol().equals("cliente")) {
            InterfazCliente cliente = (InterfazCliente) usuarioActual;
            cliente.guardaCarritos(mercadita);
        }
        try{
            ClassLoader cl = getClass().getClassLoader();
            String url = cl.getResource(ICONO_CIENCIAS).toString();
            escenario.getIcons().add(new Image(url));
            escenario.setTitle("Mercadita");
            FXMLLoader cargador;
            cargador = new FXMLLoader(cl.getResource(INTERFAZ_INICIO_SESION));
            BorderPane cristal = (BorderPane) cargador.load();
            ControladorInterfazMercadita controlador = cargador.getController();
            controlador.setEscenario(escenario);
            Scene escena = new Scene(cristal);
            escenario.setScene(escena);
            escenario.setOnCloseRequest(controlador::salir);
            escenario.show();
        }
        catch (IOException ioe) {
            dialogoError("Error al cargar la vista de usuario", "No se pudo cargar la vista de usuario.");
        }

    }

    /**
     * Muestra el clima actual.
     */
    public void mostrarClima(){
        tempMaxText.setText(mercadita.getTemperaturaMaxima());
        tempMinText.setText(mercadita.getTemperaturaMinima());
        probPrepText.setText(mercadita.getProbabilidadPrecipitacion());
        cieloText.setText(mercadita.getCielo());
    }

    /**
     * Termina el programa.
     * @param evento el evento que genero la accion.
     */
    @FXML 
    public void salir(Event e) {
        cierraSesion(e);
        mercadita.desconectar();
        Platform.exit();
        System.exit(0);
    }

    /**
     * Muestra un dialogo de informacion.
     * @param titulo el titulo del dialogo.
     * @param mensaje el mensaje del dialogo.
     */
    protected void dialogoInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
