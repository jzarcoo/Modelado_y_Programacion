package mx.unam.ciencias.modelado.proyecto2.igu;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mx.unam.ciencias.modelado.proyecto2.CampoProducto;
import mx.unam.ciencias.modelado.proyecto2.CampoCritica;
import mx.unam.ciencias.modelado.proyecto2.InterfazVendedor;
import mx.unam.ciencias.modelado.proyecto2.Critica;
import mx.unam.ciencias.modelado.proyecto2.Producto;
import mx.unam.ciencias.modelado.proyecto2.red.Mensaje;
import java.io.IOException;
import java.util.LinkedList;

/**
 * <p>Clase que define el controlador de la vista del vendedor. Se encarga de
 * realizar acciones como agregar, editar y eliminar productos.</p>
 */
public class ControladorVistaVendedor extends Controlador {

    /* El boton de ver inventario. */
    @FXML private Button botonVerInventario;

    /* El boton de ver criticas */
    @FXML private Button botonVerCriticas;

    /* El boton de agregar. */
    @FXML private Button botonAgregar;

    /* El boton de editar. */
    @FXML private Button botonEditar;

    /* El boton de eliminar. */
    @FXML private Button botonEliminar;

    /* El boton de buscar. */
    @FXML private Button botonBuscar;

    /* El vendedor. */
    private InterfazVendedor vendedor;

    /* La escena */
    private Scene escena;

    /* Los productos del vendedor. */
    private ObservableList<Producto> productos;

    /* Las criticas del vendedor. */
    private ObservableList<Critica> criticas;

    /* Producto seleccionado. */
    private Producto productoSeleccionado;
    
    /* Critica seleccionada. */
    private Critica criticaSeleccionada;

    /**
     * Inicializa el controlador.
     */
    public void initialize() {
        productos = FXCollections.observableArrayList();
        botonEditar.setDisable(true);
        botonEliminar.setDisable(true);
        try {
            mostrarClima();
        } catch (Exception e) {
            // dialogoError("Error al cargar el clima", "No se pudo cargar el clima.");
        }
        nombreUsuario.setText(usuarioActual.getNombreUsuario());
        ponerNombreUsuario();
    }

    /**
     * Define el vendedor.
     * @param vendedor el vendedor.
     */
    public void setVendedor(InterfazVendedor vendedor) {
        this.vendedor = vendedor;
        for (Producto p : vendedor.getProductos())
            productos.add(p);
    }

    /**
     * Define la escena.
     * @param escena la escena.
     */
    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    /**
     * Carga los productos del vendedor.
     */
    public void cargarProductosVendedor() {
        AnchorPane anchorPane = (AnchorPane) escena.getRoot();
        anchorPane.getChildren().removeIf(GridPane.class::isInstance);

        int maxColumnas;
        int columna = 0;
        int fila = 0; 
        int gap = 10;
    
        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(GridPane.USE_COMPUTED_SIZE);
        gridPane.setHgap(gap);
        gridPane.setVgap(gap);
        
        if(productos.isEmpty()){
            maxColumnas = 1;
            Label label = new Label("No hay productos");
            gridPane.add(label, 0, 0);
        } else if(productos.size() <= 2){
            maxColumnas = 1;
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(100);
            gridPane.getColumnConstraints().addAll(column1);
        }else if(productos.size() <= 6) {
            maxColumnas = 2;
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(50);
            ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(50);
            gridPane.getColumnConstraints().addAll(column1, column2);
        }else{
            maxColumnas = 3;
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(33.33); 
            ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(33.33);
            ColumnConstraints column3 = new ColumnConstraints();
            column3.setPercentWidth(33.33); 
            gridPane.getColumnConstraints().addAll(column1, column2, column3);
        }

        for (Producto p : productos) {
            String backgroundColor = "#ffc470";
            int padding = 10;
    
            VBox vBox = new VBox();
            vBox.setPadding(new Insets(padding));
            vBox.setStyle("-fx-background-color: " + backgroundColor + "; -fx-background-radius: 10;");
    
            Label labelCodigo = new Label(CampoProducto.CODIGO_DE_BARRAS + ": " + p.getCodigoBarras());
            labelCodigo.setWrapText(true);
            vBox.getChildren().add(labelCodigo);
            Label labelNombre = new Label("Nombre: "+p.getNombre());
            labelNombre.setWrapText(true);
            vBox.getChildren().add(labelNombre);
            Label labelDescripcion = new Label(CampoProducto.DESCRIPCION + ": "+ p.getDescripcion());
            labelDescripcion.setWrapText(true);
            vBox.getChildren().add(labelDescripcion);
            Label labelPrecio = new Label(CampoProducto.PRECIO + ": " + p.getPrecio());
            labelPrecio.setWrapText(true);
            vBox.getChildren().add(labelPrecio);
            Label labelExistencias = new Label(CampoProducto.STOCK_DISPONIBLES + ": " + p.getStockDisponible());
            labelExistencias.setWrapText(true);
            vBox.getChildren().add(labelExistencias);
            Label labelCategoria = new Label(CampoProducto.CATEGORIA + ": " + p.getCategoria());
            labelCategoria.setWrapText(true);
            vBox.getChildren().add(labelCategoria);
    
            vBox.setOnMouseClicked(e -> {
                productoSeleccionado = p;
                for (javafx.scene.Node child : gridPane.getChildren())
                    child.setStyle("-fx-background-color: #ffc470;");
                vBox.setStyle("-fx-background-color: #ff8c00; -fx-background-radius: 10;");
                botonEditar.setStyle("-fx-background-color: #ffc700;");
                botonEliminar.setStyle("-fx-background-color: #ffc700;");
                botonEditar.setDisable(false);
                botonEliminar.setDisable(false);
            });
            
            vBox.setOnMouseEntered(e -> {
                if (!vBox.getStyle().contains("#ff8c00"))
                    vBox.setStyle("-fx-background-color: #ffc700; -fx-background-radius: 10;");
            });
            vBox.setOnMouseExited(e -> {
                if (!vBox.getStyle().contains("#ff8c00"))
                    vBox.setStyle("-fx-background-color: " + backgroundColor + "; -fx-background-radius: 10;");
            });
            
            gridPane.add(vBox, columna, fila);
            if (++columna == maxColumnas) {
                columna = 0;
                fila++;
            }
        }
    
        double layoutY = 110.0;
        double layoutX = 200.0;
        double prefWidth = 570.0;
        double prefHeight = 445.0;

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setPrefSize(prefWidth, prefHeight);
        scrollPane.setLayoutX(layoutX);
        scrollPane.setLayoutY(layoutY);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background-color: transparent;  -fx-border-color: transparent; -fx-control-inner-background: transparent;");
        
        anchorPane.getChildren().add(scrollPane);
    }
    
    /**
     * Agrega un producto.
     * @param evento el evento que desencadeno la accion.
     */
    @FXML private void agregaProducto(ActionEvent evento) {
        DialogoEditaProducto dialogo;
        try {
            dialogo = new DialogoEditaProducto(escenario, null, vendedor.getId());
        } catch (IOException ioe) {
            String mensaje = ("Ocurrio un error al tratar de " +
                              "cargar el dialogo de Producto");
            dialogoError("Error al cargar interfaz", mensaje);
            return;
        }
        dialogo.showAndWait();
        if (!dialogo.isAceptado())
            return;
        Producto producto = dialogo.getProducto(); // Producto a agregar 
        vendedor.agregaProducto(producto); // Agregar producto a la lista de productos del vendedor
        mercadita.agregaProducto(producto);// Agregar producto a la copia de la base de datos del servidor
        // Red
        try {
            mercadita.getConexion().enviaMensaje(Mensaje.REGISTRO_AGREGADO_PRODUCTO);
            mercadita.getConexion().enviaRegistro(producto);
        } catch (IOException ioe) {
            dialogoError("Error con el servidor",
                         "No se pudo enviar un producto a agregar.");
        }
        // Recargar la vista
        agregaProducto(producto);
    }

    /**
     * Agrega un producto a la vista.
     * @param producto el producto a agregar.
     */
    private void agregaProducto(Producto producto) {
        productos.add(producto);
        cargarProductosVendedor();
    }

    /**
     * Edita un producto.
     * @param evento el evento que desencadeno la accion.
     */
    @FXML private void editaProducto(ActionEvent evento) {
        DialogoEditaProducto dialogo;
        try {
            dialogo = new DialogoEditaProducto(escenario, productoSeleccionado, vendedor.getId());
        } catch (IOException ioe) {
            String mensaje = ("Ocurrio un error al tratar de " +
                              "cargar el dialogo de Producto");
            dialogoError("Error al cargar interfaz", mensaje);
            return;
        }
        dialogo.showAndWait();
        if (!dialogo.isAceptado())
            return;
        // Modificar producto
        Producto productoViejo = productoSeleccionado;
        Producto productoNuevo = dialogo.getProducto();
        // Red
        try {
            mercadita.getConexion().enviaMensaje(Mensaje.REGISTRO_MODIFICADO_PRODUCTO);
            mercadita.getConexion().enviaRegistro(productoViejo);
            mercadita.getConexion().enviaRegistro(productoNuevo);
        } catch (IOException ioe) {
            dialogoError("Error con el servidor",
                         "No se pudieron enviar productos a modificar.");
        }
        // Recargar la vista
        modificaProducto(productoViejo, productoNuevo);
    }

    /**
     * Modifica un producto en la vista.
     * @param producto1 el producto a modificar.
     * @param producto2 el producto con los nuevos valores.
     */
    private void modificaProducto(Producto producto1, Producto producto2) {
        vendedor.modificaProducto(producto1, producto2);
        mercadita.modificaProducto(producto1, producto2);
        productos.remove(producto1);
        productos.add(producto2);
        cargarProductosVendedor();
    }

    /**
     * Elimina un producto.
     * @param evento el evento que desencadeno la accion.
     */
    @FXML private void eliminaProducto(ActionEvent evento) {
        String titulo = "Eliminar producto";
        String mensaje = "Esto eliminara el producto seleccionado"; 
        String aceptar = titulo;
        String cancelar = "Consevar producto";
        if (!dialogoDeConfirmacion(titulo, mensaje, "¿Esta seguro?", aceptar, cancelar))
            return;
        vendedor.eliminaProducto(productoSeleccionado);
        mercadita.eliminaProducto(productoSeleccionado);
        // Red
        try {
            mercadita.getConexion().enviaMensaje(Mensaje.REGISTRO_ELIMINADO_PRODUCTO);
            mercadita.getConexion().enviaRegistro(productoSeleccionado);
        } catch (IOException ioe) {
            dialogoError("Error con el servidor",
                         "No se pudo enviar un producto a eliminar");
        }
        // Recargar la vista
        eliminaProducto(productoSeleccionado);
    }

    /**
     * Elimina un producto de la vista.
     * @param producto el producto a eliminar.
     */
    private void eliminaProducto(Producto producto) {
        productos.remove(producto);
        cargarProductosVendedor();
    }

    /**
     * Busca productos.
     * @param evento el evento que desencadeno la accion.
     */
    @FXML private void buscaProductos(ActionEvent evento) {
        DialogoBuscaProductos dialogo;
        try {
            dialogo = new DialogoBuscaProductos(escenario);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            String mensaje = ("Ocurrio un error al tratar de " +
                              "cargar el dialogo de busqueda");
            dialogoError("Error al cargar interfaz", mensaje);
            return;
        }
        dialogo.showAndWait();
        if (!dialogo.isAceptado())
            return;
        LinkedList<Producto> resultados = mercadita.buscaProductos(dialogo.getCampo(), dialogo.getValor(), vendedor.getId());
        // Sustituir productos por los resultados
        productos = FXCollections.observableArrayList(resultados);
        cargarProductosVendedor();
    }

    /**
     * Muestra el inventario.
     * @param evento el evento que desencadeno la accion.
     */
    @FXML private void verInventario(ActionEvent evento) {
        botonAgregar.setDisable(false);
        botonBuscar.setDisable(false);

        productos.clear();
        for (Producto p : vendedor.getProductos())
            productos.add(p);
        cargarProductosVendedor();
    }

    /**
     * Muestra las criticas.
     * @param evento el evento que desencadeno la accion.
     */
    @FXML private void verCriticas(ActionEvent evento) {
        botonAgregar.setDisable(true);
        botonEditar.setDisable(true);
        botonEliminar.setDisable(true);
        botonBuscar.setDisable(true);

        criticas = FXCollections.observableArrayList();
        for (Critica c : vendedor.getCriticas())
            criticas.add(c);
        cargarCriticasVendedor();
    }

    /**
     * Carga las criticas del vendedor.
     */
    public void cargarCriticasVendedor() {
        AnchorPane anchorPane = (AnchorPane) escena.getRoot();
        anchorPane.getChildren().removeIf(GridPane.class::isInstance);

        int maxColumnas;
        int columna = 0;
        int fila = 0; 
        int gap = 10;
    
        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(GridPane.USE_COMPUTED_SIZE);
        gridPane.setHgap(gap);
        gridPane.setVgap(gap);
        
        if(criticas.isEmpty()){
            maxColumnas = 1;
            Label label = new Label("No hay criticas");
            gridPane.add(label, 0, 0);
        } else if(criticas.size() <= 2){
            maxColumnas = 1;
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(100);
            gridPane.getColumnConstraints().addAll(column1);
        }else if(criticas.size() <= 6) {
            maxColumnas = 2;
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(50);
            ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(50);
            gridPane.getColumnConstraints().addAll(column1, column2);
        }else{
            maxColumnas = 3;
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(33.33); 
            ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(33.33);
            ColumnConstraints column3 = new ColumnConstraints();
            column3.setPercentWidth(33.33); 
            gridPane.getColumnConstraints().addAll(column1, column2, column3);
        }
    
        for (Critica c : criticas) {
            String backgroundColor = "#ffc470";
            int padding = 10;

            VBox vBox = new VBox();
            vBox.setPadding(new Insets(padding));
            vBox.setStyle("-fx-background-color: " + backgroundColor + "; -fx-background-radius: 10;");
    
            // Boton para reportar
            FontAwesomeIconView icon = new FontAwesomeIconView();
            icon.setGlyphName("EXCLAMATION_CIRCLE");
            Button button = new Button();
            button.setGraphic(icon);
    
            // Efecto sutil al pasar el raton sobre el boton
            button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #3e3e3e;"));
            button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #4793af;"));
    
            button.setOnAction(e -> {
                // todos no seleccionados
                for (javafx.scene.Node child : gridPane.getChildren())
                    child.setStyle("-fx-background-color: " + backgroundColor + "; -fx-background-radius: 10;");
                vBox.setStyle("-fx-background-color: #ffc700; -fx-background-radius: 10;");
                criticaSeleccionada = c;
                reportaCritica(e);
            });
            button.setStyle("-fx-background-color: #4793af;");
            
            // Crear un HBox para contener el boton
            HBox hboxBoton = new HBox();
            hboxBoton.getChildren().add(button);
            hboxBoton.setAlignment(Pos.TOP_RIGHT);
            hboxBoton.setPadding(new Insets(3)); // Ajustar el espaciado segun sea necesario
            vBox.getChildren().add(hboxBoton);
          
            // Informacion de la critica
            Label labelUsuario = new Label(CampoCritica.ID_CLIENTE + ": " + c.getIdCliente());
            labelUsuario.setWrapText(true);
            vBox.getChildren().add(labelUsuario);
            Label labelCalificacion = new Label(CampoCritica.PUNTUACION + ": " + c.getPuntuacion());
            labelCalificacion.setWrapText(true);
            vBox.getChildren().add(labelCalificacion);
            Label labelComentario = new Label(CampoCritica.COMENTARIO + ": " + c.getComentario());
            labelComentario.setWrapText(true);
            vBox.getChildren().add(labelComentario);
            Label labelReportada = new Label(CampoCritica.REPORTE + ": " + c.getReportes());
            labelReportada.setWrapText(true);
            vBox.getChildren().add(labelReportada);

            gridPane.add(vBox, columna, fila);
            if (++columna == maxColumnas) {
                columna = 0;
                fila++;
            }
        }
    
        gridPane.setStyle("-fx-background-radius: 10;");
    
        double layoutY = 110.0;
        double layoutX = 200.0;
        double prefWidth = 570.0;
        double prefHeight = 445.0;

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setPrefSize(prefWidth, prefHeight);
        scrollPane.setLayoutX(layoutX);
        scrollPane.setLayoutY(layoutY);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background-color: transparent;  -fx-border-color: transparent; -fx-control-inner-background: transparent;");
        
        anchorPane.getChildren().add(scrollPane);
    }
    

    /**
     * Reporta una critica
     * @param evento el evento que desencadeno la accion.
     */
    @FXML private void reportaCritica(ActionEvent evento) {
        String titulo = "Reporta critica";
        String mensaje = "Esto reportara la critica seleccionada"; 
        String aceptar = titulo;
        String cancelar = "No reportar";
        if (!dialogoDeConfirmacion(titulo, mensaje, "¿Esta seguro?", aceptar, cancelar))
            return;
        // Reportar critica
        Critica criticaVieja = criticaSeleccionada;
        // Red
        try {
            mercadita.getConexion().enviaMensaje(Mensaje.REGISTRO_MODIFICADO_CRITICA);
            mercadita.getConexion().enviaRegistro(criticaVieja);
            mercadita.getConexion().enviaRegistro(vendedor.reportarCritica(criticaSeleccionada));
        } catch (IOException ioe) {
            dialogoError("Error con el servidor",
                         "No se pudo enviar una critica a reportar");
        }
        // Recargar la vista
        reportaCritica(criticaVieja, criticaSeleccionada);
    }

    /**
     * Reporta una critica en la vista.
     * @param critica1 la critica a reportar.
     * @param critica2 la critica reportada.
     */
    private void reportaCritica(Critica critica1, Critica critica2) {
        mercadita.modificaCritica(critica1, critica2);
        criticas.remove(critica1);
        criticas.add(critica2);
        cargarCriticasVendedor();
    }
    
}
