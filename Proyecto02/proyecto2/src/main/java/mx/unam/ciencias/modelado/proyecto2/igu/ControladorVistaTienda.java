package mx.unam.ciencias.modelado.proyecto2.igu;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import mx.unam.ciencias.modelado.proyecto2.Critica;
import mx.unam.ciencias.modelado.proyecto2.InterfazCliente;
import mx.unam.ciencias.modelado.proyecto2.InterfazVendedor;
import mx.unam.ciencias.modelado.proyecto2.Producto;
import mx.unam.ciencias.modelado.proyecto2.red.Mensaje;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;


/**
 * <p>Clase para el controlador de la ventana principal del usuario.</p>
 */
public class ControladorVistaTienda extends Controlador{

    /* El vendedor cuya tienda se esta mostrando */
    private InterfazVendedor vendedor;


    /**
     * Establece el id del vendedor.
     * @param id el id del vendedor.
     */
    public void setVendedor(InterfazVendedor vendedor){
        this.vendedor = vendedor;
    }


    /**
     * Regresa a la ventana principal.
     * @param evento el evento que genero la accion.
     */
    @FXML public void regresa(Event evento) {
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
            for (InterfazVendedor v : mercadita.getVendedores()) {
                String nombreVendedor = v.getNombreUsuario();
                String telefonoVendedor = v.getTelefono();

                BorderPane borderPane = new BorderPane();
                borderPane.setId("vendedor_" + v.getId());
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

                borderPane.setOnMouseClicked(e -> controlador.crearVistaTiendaVendedor(v));
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
     * Termina la compra.
     * @param e el evento que genero la accion.
     */
    @FXML
    public void terminaCompra(Event e){
        int idCarrito = vendedor.getId() * 1000 + usuarioActual.getId();
        InterfazCliente cliente = (InterfazCliente) usuarioActual;
        if(cliente.carritoVacio(idCarrito)){
            dialogoError("Agrega algo!", "No hay productos en el carrito.");
            return;
        }
        try {
            ClassLoader cl = getClass().getClassLoader();
            String url = cl.getResource(ICONO_CIENCIAS).toString();
            escenario.getIcons().add(new Image(url));
            FXMLLoader cargador;    
            cargador = new FXMLLoader(cl.getResource(INTERFAZ_VISTA_COMPRA));
            AnchorPane cristal = (AnchorPane) cargador.load();
            ControladorVistaVerCompra controlador = cargador.getController();
            controlador.setEscenario(escenario);
            controlador.setIdCarrito(vendedor.getId() * 1000 + usuarioActual.getId());
            controlador.setVendedor(vendedor);
            
            Scene escena = new Scene(cristal);
            escenario.setScene(escena);
            escenario.setOnCloseRequest(ev -> {
                cierraSesion(ev);
                controlador.salir(ev);
            });
            escenario.show();
            
            controlador.mostrarCarrito(escena);
            Button botonRegresar = new Button();
            botonRegresar.setLayoutX(15.0);
            botonRegresar.setLayoutY(264.0);
            botonRegresar.setMnemonicParsing(false);
            botonRegresar.setPrefHeight(1.0);
            botonRegresar.setPrefWidth(135.0);
            botonRegresar.setStyle("-fx-background-color: #dd5746;");
            botonRegresar.setText("Regresar");
            FontAwesomeIconView iconoLibro = new FontAwesomeIconView();
            iconoLibro.setGlyphName("BOOK");
            botonRegresar.setGraphic(iconoLibro);
            botonRegresar.setOnAction(ev -> controlador.regresa(vendedor));
            ((AnchorPane) escena.getRoot()).getChildren().add(botonRegresar);
        } catch (IOException ioe) {
            dialogoError("Error al cargar la vista de compra", "No se pudo cargar la vista de compra.");
        }
    }


    /**
     * Abre la vista de la tienda.
     * @param escena la escena actual.
     * @param vendedor el vendedor cuya tienda se va a mostrar.
     */
    public void abrirVistaTienda(Scene escena, InterfazVendedor vendedor) {
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
            borderPane.setPadding(new Insets(10, 10, 10, 10)); // Margen interno en el BorderPane

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
                else
                    dialogoError("Error al agregar producto", "No hay suficiente stock del producto.");
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
            reportarBtn.setOnMouseEntered(e -> reportarBtn.setStyle("-fx-background-color: #dd5746; ") );
            
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
            abrirVistaTienda(escena,vendedor);

            });

            anchorPane.getChildren().addAll(textArea, cerrarBtn, enviarButton, comboBox);
        });
    }
}


