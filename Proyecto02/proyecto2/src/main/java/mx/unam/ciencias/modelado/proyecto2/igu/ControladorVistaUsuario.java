package mx.unam.ciencias.modelado.proyecto2.igu;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mx.unam.ciencias.modelado.proyecto2.Critica;
import mx.unam.ciencias.modelado.proyecto2.InterfazCliente;
import mx.unam.ciencias.modelado.proyecto2.InterfazVendedor;
import mx.unam.ciencias.modelado.proyecto2.Producto;
import mx.unam.ciencias.modelado.proyecto2.red.Mensaje;
import javafx.scene.text.Text;

/**
 * <p>Clase para el controlador de la ventana principal del usuario.</p>
 */
public class ControladorVistaUsuario extends Controlador{
    
    /** 
     * Inicializa el controlador. 
     */
    @FXML public void initialize() {
        try {
            mostrarClima();
        } catch (Exception e) {
            // dialogoError("Error al cargar el clima", "No se pudo cargar el clima.");
        }
        nombreUsuario.setText(usuarioActual.getNombreUsuario());
        ponerNombreUsuario();
    }

    /**
     * Crea la vista de la tienda de un vendedor.
     * @param vendedor el vendedor cuya tienda se mostrara.
     */
    public void crearVistaTiendaVendedor(InterfazVendedor vendedor) {
        try {
            ClassLoader cl = getClass().getClassLoader();
            String url = cl.getResource(ICONO_CIENCIAS).toString();
            escenario.getIcons().add(new Image(url));
            escenario.setTitle("Vista de usuario");
    
            FXMLLoader cargador = new FXMLLoader(cl.getResource(INTERFAZ_TIENDA_VENDEDOR));
            AnchorPane cristal = (AnchorPane) cargador.load();
            ControladorVistaTienda controlador = cargador.getController();
            controlador.setEscenario(escenario);
            controlador.setVendedor(vendedor);
            Scene escena = new Scene(cristal);
            escenario.setScene(escena);
            escenario.setOnCloseRequest(controlador::salir);
            escenario.show();
            controlador.setVendedor(vendedor);
            AnchorPane anchorPane = (AnchorPane) escena.getRoot();
            double layoutY = 21.0;
            double prefHeight = 200.0;
            double prefWidth = 200.0;
            double margenEntrePaneles = 10.0; // Ajusta segun sea necesario
            String backgroundColor = "#ffc470";
            
            // Crear un VBox para contener todos los productos
            VBox contenedorProductos = new VBox();
            contenedorProductos.setSpacing(margenEntrePaneles); // Separacion entre productos
            contenedorProductos.setAlignment(Pos.CENTER);

            // Crear un titulo para los productos
            Label tituloProductos = new Label("Productos de " + vendedor.getNombreUsuario());
            tituloProductos.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 25px; -fx-fill: #8b322c;");
            tituloProductos.setAlignment(Pos.CENTER);
            tituloProductos.setPadding(new Insets(10)); // Padding para el titulo

            // Agregar el titulo al VBox
            contenedorProductos.getChildren().add(tituloProductos);
    
            for (Producto p : vendedor.getProductos()) {
                String nombreProducto = p.getNombre();
    
                BorderPane borderPane = new BorderPane();
                borderPane.setPrefHeight(prefHeight);
                borderPane.setPrefWidth(prefWidth);
                borderPane.setStyle("-fx-background-color: " + backgroundColor + "; -fx-background-radius: 10;");
                borderPane.setPadding(new Insets(30, 30, 30, 30)); // Margen interno en el BorderPane
    
                VBox contenido = new VBox(5);
                contenido.getChildren().addAll(
                        new Label(nombreProducto),
                        //new Text("Nombre del vendedor: " + vendedor.getNombreUsuario()),
                        new Text("Descripcion del producto: " + p.getDescripcion()),
                        new Text("Precio: " + p.getPrecio()),
                        new Text("Cantidad disponible: " + p.getStockDisponible()),
                        new Text("Categoria del producto: " + p.getCategoria()),
                        new Text("Codigo de barras: " + p.getCodigoBarras())
                );
                contenido.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 12px; -fx-text-fill: #ffc470;");
                // Configuracion del boton
                Button botonAgregar = new Button();
                InterfazCliente clienteAcual = (InterfazCliente) usuarioActual;
                botonAgregar.setOnAction(e -> {

                    if(p.getStockDisponible() > clienteAcual.getCantidadProducto(p))
                        clienteAcual.agregarProducto(p);
                    else{
                        dialogoError("Error al agregar producto", "No hay suficiente stock del producto.");
                        crearVistaTiendaVendedor(vendedor);

                    }
                });
                botonAgregar.setStyle("-fx-background-color: #dd5746;");
                FontAwesomeIconView iconoAgregar = new FontAwesomeIconView();
                iconoAgregar.setId("agregarProductos");
                iconoAgregar.setGlyphName("PLUS");
                botonAgregar.setGraphic(iconoAgregar);
    
                // Agregar efecto de boton al pasar el mouse sobre el recuadro
                botonAgregar.setOnMouseEntered(e -> botonAgregar.setStyle("-fx-background-color: #dd5746; "));
                
                // Restaurar estilo cuando el mouse sale del recuadro
                botonAgregar.setOnMouseExited(e -> botonAgregar.setStyle("-fx-background-color: #dd3446; "));
                
                // Colocando el boton en la esquina inferior derecha
                borderPane.setBottom(botonAgregar);
                BorderPane.setAlignment(botonAgregar, Pos.BOTTOM_RIGHT);
    
                // Agregar el contenido al centro del BorderPane
                borderPane.setCenter(contenido);
    
                // Agregar el BorderPane al VBox
                contenedorProductos.getChildren().add(borderPane);
            }

            // Crear un ScrollPane y agregar el VBox de los productos
            ScrollPane scrollPane = new ScrollPane(contenedorProductos);
            scrollPane.setPrefSize(600, 350); //Cambio de ancho de ScrollPanel
            scrollPane.setLayoutX(188);
            scrollPane.setLayoutY(layoutY);
            scrollPane.setFitToWidth(true);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setStyle("-fx-background-color: transparent;  -fx-border-color: transparent; -fx-control-inner-background: transparent;");
            anchorPane.getChildren().add(scrollPane);

            /* Creacion de criticas */
            layoutY += 363;

            // Crear un VBox para contener todas las criticas
            VBox contenedorCriticas = new VBox();
            contenedorCriticas.setSpacing(10); // Separacion entre criticas

            for (Critica c : vendedor.getCriticas()) {
                String critica = c.getComentario();
                String calif = String.valueOf(c.getPuntuacion());

                // Crear un BorderPane para cada critica
                BorderPane borderPaneCritica = new BorderPane();
                borderPaneCritica.setPadding(new Insets(10)); // Margen interno en el BorderPane
                borderPaneCritica.setStyle("-fx-background-color: #ffc470; -fx-background-radius: 10;");

                // Crear etiquetas para mostrar el comentario y la calificacion
                Label labelCritica = new Label(critica);
                Label labelCalif = new Label("Calificacion: " + calif);
                labelCritica.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 12px; -fx-text-fill: #8b322c;");
                labelCalif.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 12px; -fx-text-fill: #8b322c;");

                // Agregar las etiquetas al BorderPane
                borderPaneCritica.setTop(labelCritica);
                borderPaneCritica.setBottom(labelCalif);

                // Crear y configurar el boton de reportar para esta critica
                Button reportarBtn = new Button("Reportar");
                reportarBtn.setOnAction(event -> {
                    Critica criticaReportada = new Critica(c.getId(), c.getIdVendedor(), c.getIdCliente(), c.getPuntuacion(), c.getComentario(), c.getReportes()+1);
                    try{
                        mercadita.getConexion().enviaMensaje(Mensaje.REGISTRO_MODIFICADO_CRITICA);
                        mercadita.getConexion().enviaRegistro(c);
                        mercadita.getConexion().enviaRegistro(criticaReportada);
                    }catch(IOException ioe){
                        dialogoError("Error al reportar comentario", "No se pudo reportar el comentario.");
                    }
                    dialogoInformacion("Reporte enviado", "Tu reporte ha sido enviado con exito."); 
                    vendedor.reportarCritica(c);
                    mercadita.modificaCritica(c, criticaReportada);
                });
                reportarBtn.setStyle("-fx-background-color: #dd5746;");
                borderPaneCritica.setRight(reportarBtn);

                // Agregar efecto de boton al pasar el mouse sobre el recuadro
                reportarBtn.setOnMouseEntered(e -> reportarBtn.setStyle("-fx-background-color: #dd5746; "));
                
                // Restaurar estilo cuando el mouse sale del recuadro
                reportarBtn.setOnMouseExited(e -> reportarBtn.setStyle("-fx-background-color: #dd3446; "));

                // Agregar el BorderPane de la critica al VBox
                contenedorCriticas.getChildren().add(borderPaneCritica);
            }

            // Crear un ScrollPane y agregar el VBox de las criticas
            ScrollPane scrollPaneCriticas = new ScrollPane(contenedorCriticas);
            scrollPaneCriticas.setPrefSize(600, 100); // Tamano preferido del ScrollPane
            scrollPaneCriticas.setFitToWidth(true); // Hacer que el ancho del ScrollPane se ajuste automaticamente
            scrollPaneCriticas.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Mostrar barras de desplazamiento vertical solo si es necesario
            scrollPaneCriticas.setStyle("-fx-background-color: transparent;  -fx-border-color: transparent; -fx-control-inner-background: transparent;");

            // Posicionar el ScrollPane de las criticas debajo del de los productos
            scrollPaneCriticas.setLayoutX(188);
            scrollPaneCriticas.setLayoutY(layoutY);
            anchorPane.getChildren().add(scrollPaneCriticas);

            /*Creacion de boton critica */
            double layoutCriticasY = 494; // Posicion Y inicial para las criticas
            double prefCriticaHeight = 30.0; // Altura preferida para las criticas
            Button buttonCritica = new Button("Agrega un comentario");
            buttonCritica.setPrefHeight(prefCriticaHeight);
            buttonCritica.setLayoutX(190);
            buttonCritica.setLayoutY(layoutCriticasY);
            buttonCritica.setStyle("-fx-background-color: #4793AF;");
            buttonCritica.setPadding(new Insets(5));
            anchorPane.getChildren().add(buttonCritica);
            /* Evento para abrir el text area */
            buttonCritica.setOnAction(event -> {
                TextArea textArea = new TextArea();
                textArea.setPrefSize(300, 18);
                textArea.setLayoutX(buttonCritica.getLayoutX());
                textArea.setLayoutY(buttonCritica.getLayoutY() + prefCriticaHeight + 10);
                //Para poner la calificacion
                ComboBox<Integer> comboBox = new ComboBox<>();
                comboBox.getItems().addAll(1, 2, 3, 4, 5);
                comboBox.getSelectionModel().selectFirst();
                comboBox.setLayoutX(textArea.getLayoutX());
                comboBox.setLayoutY(textArea.getLayoutY() + textArea.getPrefHeight() + 10);
                comboBox.setStyle("-fx-background-color: #4793af;");

                Button cerrarBtn = new Button("Cerrar");
                cerrarBtn.setLayoutX(textArea.getLayoutX()+120);
                cerrarBtn.setLayoutY(textArea.getLayoutY() + textArea.getPrefHeight() + 10);
                cerrarBtn.setStyle("-fx-background-color: #4793af;");

                Button enviarButton = new Button("Enviar");
                enviarButton.setLayoutX(textArea.getLayoutX()+60);
                enviarButton.setLayoutY(textArea.getLayoutY() + textArea.getPrefHeight() + 10);
                enviarButton.setStyle("-fx-background-color: #4793af;");

                cerrarBtn.setOnAction(closeEvent -> {
                    anchorPane.getChildren().remove(textArea);
                    anchorPane.getChildren().remove(cerrarBtn);
                    anchorPane.getChildren().remove(enviarButton);
                    anchorPane.getChildren().remove(comboBox);
                });

                enviarButton.setOnAction(enviarEvent -> {
                    String comentario = textArea.getText();
                    if(comentario.isEmpty()){
                        dialogoError("Error al enviar comentario", "No se puede enviar un comentario vacio.");
                        return;
                    }
                    int rating = comboBox.getValue();
                    
                    Critica c = vendedor.agregarCritica(comentario, usuarioActual.getId(), rating);
                    mercadita.agregaCritica(c);
                    try{
                        mercadita.getConexion().enviaMensaje(Mensaje.REGISTRO_AGREGADO_CRITICA);
                        mercadita.getConexion().enviaRegistro(c);
                    }catch(IOException ioe){
                        dialogoError("Error al enviar comentario", "No se pudo enviar el comentario.");
                    }
                    dialogoInformacion("Comentario enviado", "Tu comentario ha sido enviado con exito.");
                    anchorPane.getChildren().remove(textArea);
                    anchorPane.getChildren().remove(cerrarBtn);
                    anchorPane.getChildren().remove(enviarButton);
                    anchorPane.getChildren().remove(comboBox);
                    crearVistaTiendaVendedor(vendedor);

                });
        
                anchorPane.getChildren().addAll(textArea, cerrarBtn, enviarButton, comboBox);
            });

        } catch (IOException ioe) {
            // Hacer de dialogo error algo general
            dialogoError("Error al cargar la vista de usuario", "No se pudo cargar la vista de tienda de vendedor.");
        }
    }
    

    /**
     * Abre la vista de cliente.
     * @param escenario el escenario donde se mostrara la vista.
     */
    public void abreVistaCliente(Stage escenario){
        try{
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
                try {
                    controlador.mostrarClima();
                } catch (Exception e) {
                    dialogoError("Error al cargar el clima", "No se pudo cargar el clima.");
                }
                escenario.show();

                // Crear un AnchorPane para contener los recuadros de los vendedores
                AnchorPane anchorPaneContent = new AnchorPane();
                double prefHeight = 125.0;
                int totalVendedores = mercadita.getVendedores().size();
                int columnasNecesarias = (int) Math.ceil((double) totalVendedores / 4); // Calculamos el numero de columnas necesarias
                double totalHeight = (prefHeight + 10) * columnasNecesarias; // Calculamos la altura total del contenido
                anchorPaneContent.setPrefHeight(totalHeight);

                // Agregar los recuadros de los vendedores al AnchorPane
                double layoutX = 0;
                double layoutY = 0;
                for (InterfazVendedor vendedor : mercadita.getVendedores()) {
                    String nombreVendedor = vendedor.getNombreUsuario();
                    BorderPane borderPane = new BorderPane();
                    borderPane.setId("vendedor_" + vendedor.getId());
                    borderPane.setLayoutX(layoutX);
                    borderPane.setLayoutY(layoutY);
                    borderPane.setPrefHeight(prefHeight);
                    borderPane.setPrefWidth(145); // Ancho deseado del recuadro
                    borderPane.setStyle("-fx-background-color: #ffc470;");
                    borderPane.setTop(new Label(nombreVendedor));
                    anchorPaneContent.getChildren().add(borderPane);

                    layoutX += 155; // Espacio entre los recuadros

                    if (layoutX >= 620) { // Si supera el ancho de la ventana, pasa a la siguiente fila
                        layoutX = 0;
                        layoutY += prefHeight + 10; // Espacio entre las filas
                    }
                    
                    borderPane.setOnMouseClicked(e ->
                            controlador.crearVistaTiendaVendedor(vendedor)
                    );
                }

                // Crear un ScrollPane y agregar el AnchorPane de los recuadros de los vendedores
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
}