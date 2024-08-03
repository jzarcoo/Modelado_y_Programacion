package mx.unam.ciencias.modelado.proyecto2.igu;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import mx.unam.ciencias.modelado.proyecto2.MercaditaProxy;
import mx.unam.ciencias.modelado.proyecto2.InterfazVendedor;
import mx.unam.ciencias.modelado.proyecto2.InterfazAdministrador;

/**
 * <p>Clase que define el controlador de la interfaz de la mercadita.</p>
 */
public class ControladorInterfazMercadita extends Controlador {

    /* El boton de log in. */
    @FXML 
    private Button botonLogin;

    /* Dato de usuario. */
    @FXML
    private TextField datoUsuario;

    /* Dato de contrasena. */
    @FXML   
    private PasswordField datoContrasena;

    /* Inicializa el controlador. */
    @FXML public void initialize() {
        mercadita = new MercaditaProxy();
    }

    /**
     * Realiza el log in.
     */
    @FXML private void logIn(ActionEvent evento) {
        String usuario = datoUsuario.getText();
        String contrasena = datoContrasena.getText();
        datoUsuario.setText("");
        datoContrasena.setText("");
        if(!mercadita.comprobarUsuario(usuario, contrasena)){
            dialogoError("Error al iniciar sesion", "Usuario o contrasena incorrectos.");
            return;
        }

        setUsuarioActual(mercadita.getUsuario(usuario, contrasena));

        switch(usuarioActual.getRol()){
            case "cliente":
                cambiaVentana();
                break;
            case "vendedor":
                cambiaVentanaVendedor();
                break;
            case "administrador":
                cambiaVentanaAdministrador();
                break;
            default:
                dialogoError("Error al iniciar sesion", "Rol desconocido.");
        }
    }   


    /**
     * Cambia la ventana a la vista de usuario de un cliente.
     */
    public void cambiaVentana() {
        try {
            ClassLoader cl = getClass().getClassLoader();
            String url = cl.getResource(ICONO_CIENCIAS).toString();
            escenario.getIcons().add(new Image(url));

            FXMLLoader cargador;
            cargador = new FXMLLoader(cl.getResource(INTERFAZ_MERCADITA_FXML));
            AnchorPane cristal = (AnchorPane) cargador.load();

            ControladorVistaUsuario controlador = cargador.getController();
            controlador.setEscenario(escenario);
            Scene escena = new Scene(cristal);
            escenario.setScene(escena);
            escenario.setOnCloseRequest(controlador::salir);

            escenario.show();

            double anchoDisponible = 600 / 3; // Dividir el ancho total del ScrollPane entre 3 columnas

            // Crear un AnchorPane para contener los recuadros de los vendedores
            AnchorPane anchorPaneContent = new AnchorPane();
            double prefHeight = 125.0;
            int totalVendedores = mercadita.getVendedores().size();
            int numColumnas = 3;
            int filasNecesarias = (int) Math.ceil((double) totalVendedores / numColumnas); // Calculamos el numero de filas necesarias
            double totalHeight = (prefHeight + 10) * filasNecesarias; // Calculamos la altura total del contenido
            anchorPaneContent.setPrefHeight(totalHeight);

            // Agregar los recuadros de los vendedores al AnchorPane
            double layoutX = 0.0; // Empieza en la primera columna
            double layoutY = 0.0; // Empieza en la primera fila
            for (InterfazVendedor vendedor : mercadita.getVendedores()) {
                String nombreVendedor = vendedor.getNombreUsuario();
                String telefonoVendedor = vendedor.getTelefono();

                BorderPane borderPane = new BorderPane();
                borderPane.setId("vendedor_" + vendedor.getId());
                borderPane.setLayoutX(layoutX);
                borderPane.setLayoutY(layoutY);
                borderPane.setPrefWidth(anchoDisponible + 50);
                borderPane.setPrefHeight(prefHeight); // Tamano fijo para cada recuadro
                borderPane.setStyle("-fx-background-color: #ffc470; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0.0, 0, 1);");

                // Agregar efecto de boton al pasar el mouse sobre el recuadro
                borderPane.setOnMouseEntered(e -> borderPane.setStyle("-fx-background-color: #ffcc80; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0.0, 0, 1);"));

                // Restaurar estilo cuando el mouse sale del recuadro
                borderPane.setOnMouseExited(e -> borderPane.setStyle("-fx-background-color: #ffc470; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0.0, 0, 1);"));

                // Crear texto para el nombre del vendedor
                Text textoNombre = new Text();
                textoNombre.setLayoutX(15.0);
                textoNombre.setLayoutY(47.0);
                textoNombre.setStrokeType(StrokeType.OUTSIDE);
                textoNombre.setStrokeWidth(0.0);
                textoNombre.setText("Nombre del vendedor: " + nombreVendedor);

                textoNombre.setStyle("-fx-font-weight: bold; -fx-fill: #8b322c;");

                // Crear texto para el telefono del vendedor
                Text textoTelefono = new Text();
                textoTelefono.setLayoutX(15.0);
                textoTelefono.setLayoutY(78.0);
                textoTelefono.setStrokeType(StrokeType.OUTSIDE);
                textoTelefono.setStrokeWidth(0.0);
                textoTelefono.setText("Telefono del vendedor: " + telefonoVendedor);

                borderPane.getChildren().addAll(textoNombre, textoTelefono);

                anchorPaneContent.getChildren().add(borderPane);

                // Actualizar las coordenadas para el siguiente recuadro
                layoutX += anchoDisponible + 70; // Mover a la siguiente columna

                // Si excede el ancho disponible, pasar a la siguiente fila
                if (layoutX >= 2 * anchoDisponible) {
                    layoutX = 0; // Volver a la primera columna
                    layoutY += prefHeight + 20; // Mover a la siguiente fila
                }

                borderPane.setOnMouseClicked(e -> controlador.crearVistaTiendaVendedor(vendedor));
            }

            ScrollPane scrollPane = new ScrollPane(anchorPaneContent);
            scrollPane.setPrefSize(600, 480); // Tamano deseado del ScrollPane
            scrollPane.setLayoutX(185); // Posicion X deseada
            scrollPane.setLayoutY(110); // Posicion Y deseada
            scrollPane.setFitToWidth(true);

            // Mover el ScrollBar al borde derecho de la ventana
            scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent; -fx-control-inner-background: transparent;");

            // Agregar el ScrollPane a la escena
            ((AnchorPane) escena.getRoot()).getChildren().add(scrollPane);

        } catch (IOException ioe) {
            dialogoError("Error al cargar la vista de usuario", "No se pudo cargar la vista de usuario.");
        }

    }

    /**
     * Cambia la ventana a la vista de usuario de un vendedor.
     */
    public void cambiaVentanaVendedor() {
        if (usuarioActual instanceof InterfazVendedor) {
            InterfazVendedor vendedor = (InterfazVendedor) usuarioActual;
            try {
                ClassLoader cl = getClass().getClassLoader();
                String url = cl.getResource(ICONO_CIENCIAS).toString();
                escenario.getIcons().add(new Image(url));

                FXMLLoader cargador = new FXMLLoader(cl.getResource(INTERFAZ_VENDEDOR_FXML));
                AnchorPane vistaVendedor = (AnchorPane) cargador.load();

                ControladorVistaVendedor controlador = cargador.getController();
                controlador.setEscenario(escenario);
                controlador.setVendedor(vendedor);

                Scene escena = new Scene(vistaVendedor);
                escenario.setScene(escena);
                escenario.setOnCloseRequest(controlador::salir);
                escenario.show();
                controlador.setEscena(escena);
                controlador.cargarProductosVendedor();
            } catch (IOException ioe) {
                dialogoError("Error", "No se pudo cargar la vista de vendedor.");
            }
        }
    }

    /**
     * Cambia la ventana a la vista de usuario de un administrador.
     */
    public void cambiaVentanaAdministrador() {
        if (usuarioActual instanceof InterfazAdministrador) {
            try {
                ClassLoader cl = getClass().getClassLoader();
                String url = cl.getResource(ICONO_CIENCIAS).toString();
                escenario.getIcons().add(new Image(url));

                FXMLLoader cargador = new FXMLLoader(cl.getResource(INTERFAZ_ADMINISTRADOR_FXML));
                AnchorPane vistaVendedor = (AnchorPane) cargador.load();

                ControladorVistaAdministrador controlador = cargador.getController();
                controlador.setEscenario(escenario);

                Scene escena = new Scene(vistaVendedor);
                escenario.setScene(escena);
                escenario.setOnCloseRequest(controlador::salir);
                escenario.show();

                controlador.setEscena(escena);
                controlador.cargaCriticas();
            } catch (IOException ioe) {
                dialogoError("Error", "No se pudo cargar la vista de administrador.");
            }
        }
    }
} 