package mx.unam.ciencias.modelado.proyecto2.igu;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import mx.unam.ciencias.modelado.proyecto2.Carrito;
import mx.unam.ciencias.modelado.proyecto2.ElementoCarrito;
import mx.unam.ciencias.modelado.proyecto2.InterfazCliente;
import mx.unam.ciencias.modelado.proyecto2.InterfazVendedor;
import mx.unam.ciencias.modelado.proyecto2.Producto;
import mx.unam.ciencias.modelado.proyecto2.red.Mensaje;
import mx.unam.ciencias.modelado.proyecto2.ProcesadorPago;
import java.io.IOException;
import java.util.HashMap;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

/**
 * p>Clase para controlar la vista de ver compra.</p>
 */
public class ControladorVistaVerCompra extends Controlador {
    
    /* El id del carrito. */
    private int idCarrito;
    /* El vendedor. */
    private InterfazVendedor vendedor;

    /**
     * Muestra el carrito.
     * @param escena la escena a la que se agregara el carrito.
     */
    public void mostrarCarrito(Scene escena) {
        AnchorPane anchorPane = (AnchorPane) escena.getRoot();
        double layoutY = 21.0;
        double prefHeight = 100.0;
        double prefWidth = 200.0;
        double margenEntrePaneles = 30.0;
        String backgroundColor = "#ffc470";
    
        VBox contenedorProductos = new VBox();
        contenedorProductos.setSpacing(margenEntrePaneles);
        contenedorProductos.setAlignment(Pos.CENTER);

        InterfazCliente cliente = (InterfazCliente) usuarioActual;
        Carrito carrito = cliente.verCarrito(idCarrito);
        if(carrito == null) {
            return;
        }
        Label tituloProductos = new Label("Productos de " + vendedor.getNombreUsuario());
        tituloProductos.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 25px; -fx-fill: #8b322c;");
        tituloProductos.setAlignment(Pos.CENTER);
        tituloProductos.setPadding(new Insets(10)); 
        contenedorProductos.getChildren().add(tituloProductos);
        for (ElementoCarrito elemento : carrito.getElementosCarrito()) {
            Producto p = vendedor.getProducto(elemento.getCodigoBarras());

            String nombreProducto = p.getNombre();
            BorderPane borderPane = new BorderPane();
            borderPane.setPrefHeight(prefHeight);
            borderPane.setPrefWidth(prefWidth);
            borderPane.setStyle("-fx-background-color: " + backgroundColor + "; -fx-background-radius: 10;");
            borderPane.setPadding(new Insets(30, 30, 30, 30)); 

            VBox contenido = new VBox(5);
            contenido.getChildren().addAll(
                    new Label(nombreProducto),
                    new Text("Cantidad: " + elemento.getCantidad()),
                    new Text("Descripcion del producto: " + p.getDescripcion()),
                    new Text("Precio unitario: " + p.getPrecio()),
                    new Text("Precio total: " + p.getPrecio() * elemento.getCantidad()),
                    new Text("Cantidad disponible: " + p.getStockDisponible()),
                    new Text("Categoria del producto: " + p.getCategoria()),
                    new Text("Codigo de barras: " + p.getCodigoBarras())
            );
            contenido.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 12px; -fx-text-fill: #ffc470;");
                Button botonQuitar = new Button();
                InterfazCliente clienteActual = (InterfazCliente) usuarioActual;
                botonQuitar.setOnAction(e -> {
                clienteActual.eliminarProducto(p);
                contenido.getChildren().clear();
                if(elemento.getCantidad() == 0) 
                    contenedorProductos.getChildren().remove(borderPane);
                else {
                    contenido.getChildren().addAll(
                        new Label(p.getNombre()),
                        new Text("Cantidad: " + elemento.getCantidad()),
                        new Text("Descripcion del producto: " + p.getDescripcion()),
                        new Text("Precio unitario: " + p.getPrecio()),
                        new Text("Precio total: " + p.getPrecio() * elemento.getCantidad()),
                        new Text("Cantidad disponible: " + p.getStockDisponible()),
                        new Text("Categoria del producto: " + p.getCategoria()),
                        new Text("Codigo de barras: " + p.getCodigoBarras())
                    );
                }
            });
            botonQuitar.setStyle("-fx-background-color: #dd5746;");
            FontAwesomeIconView iconoAgregar = new FontAwesomeIconView();
            iconoAgregar.setId("agregarProductos");
            iconoAgregar.setGlyphName("MINUS");
            botonQuitar.setGraphic(iconoAgregar);
    
            // Agregar efecto de boton al pasar el mouse sobre el recuadro
            botonQuitar.setOnMouseEntered(e -> {
                botonQuitar.setStyle("-fx-background-color: #dd3446; ");
                borderPane.setStyle("-fx-background-color: #dd5746; -fx-background-radius: 10;");
            });

            // Restaurar estilo cuando el mouse sale del recuadro
            botonQuitar.setOnMouseExited(e -> {
                botonQuitar.setStyle("-fx-background-color: #dd5746; ");
                borderPane.setStyle("-fx-background-color:"+backgroundColor+";");
            });

            // Mantener el color del panel cuando se hace clic en el boton
            botonQuitar.setOnMouseClicked(e -> botonQuitar.setStyle("-fx-background-color: "+backgroundColor+"; "));

            // Colocando el boton en la esquina inferior derecha
            borderPane.setBottom(botonQuitar);
            BorderPane.setAlignment(botonQuitar, Pos.BOTTOM_RIGHT);

            // Agregar el contenido al centro del BorderPane
            borderPane.setCenter(contenido);

            // Agregar el BorderPane al VBox
            contenedorProductos.getChildren().add(borderPane);


        }
        ScrollPane scrollPane = new ScrollPane(contenedorProductos);
        scrollPane.setPrefSize(600, 480);
        scrollPane.setLayoutX(188);
        scrollPane.setLayoutY(layoutY);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background-color: transparent;  -fx-border-color: transparent; -fx-control-inner-background: transparent;");
        anchorPane.getChildren().add(scrollPane);
    }

    /**
     * Establece el id del carrito.
     * @param idCarrito el id del carrito a establecer.
     */
    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    /**
     * Establece el vendedor.
     * @param vendedor el vendedor a establecer.
     */
    public void setVendedor(InterfazVendedor vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * Regresa a la vista de la tienda.
     * @param vendedor el vendedor que se esta utilizando.
     */
    public void regresa(InterfazVendedor vendedor) {
        try {
            ClassLoader cl = getClass().getClassLoader();
            String url = cl.getResource(ICONO_CIENCIAS).toString();
            escenario.getIcons().add(new Image(url));

            FXMLLoader cargador;
            cargador = new FXMLLoader(cl.getResource(INTERFAZ_TIENDA_VENDEDOR));
            AnchorPane cristal = (AnchorPane) cargador.load();

            ControladorVistaTienda controlador = cargador.getController();
            controlador.setEscenario(escenario);
            controlador.setVendedor(vendedor);
            Scene escena = new Scene(cristal);
            escenario.setScene(escena);
            escenario.setOnCloseRequest(controlador::salir);
            escenario.show();
            
            controlador.abrirVistaTienda(escena, vendedor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /* 
     * Metodo para proceder al pago 
     * @param e el evento que desencadena la accion.
    */
    @FXML
    public void procedePago(Event e) {
        InterfazCliente cliente = (InterfazCliente) usuarioActual;
        Carrito carrito = cliente.verCarrito(idCarrito);
        if(cliente.carritoVacio(idCarrito)){
            dialogoError("Agrega algo!", "No hay productos en el carrito.");
            return;
        }
        //Checar que siga el stock disponible
        for(ElementoCarrito elemento : carrito.getElementosCarrito()) {
            Producto p = vendedor.getProducto(elemento.getCodigoBarras());
            if(p.getStockDisponible() < elemento.getCantidad()) {
                dialogoError("Error al realizar la compra", "No hay suficiente stock para el producto: " + p.getNombre()+ " quedan " + p.getStockDisponible() + " unidades.");
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
                return;
            }
        }
        
        double total = carrito.getPrecioTotal(vendedor);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Forma de Pago");
        alert.setHeaderText("Selecciona la forma de pago:");

        ButtonType tarjetaButton = new ButtonType("Tarjeta");
        ButtonType efectivoButton = new ButtonType("Efectivo");
        ButtonType transferenciaButton = new ButtonType("Transferencia");
        ButtonType cancelButton = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(tarjetaButton, efectivoButton, transferenciaButton, cancelButton);

        alert.initModality(Modality.APPLICATION_MODAL);
        ProcesadorPago procesadorPago = new ProcesadorPago();
        alert.showAndWait().ifPresent(result -> {
            if (result == tarjetaButton) {
                procesadorPago.setFormaPago( mercadita.getPagoConTarjeta());
            } else if (result == efectivoButton) {
                procesadorPago.setFormaPago( mercadita.getPagoConEfectivo());
            }else if (result == transferenciaButton) {
                procesadorPago.setFormaPago( mercadita.getPagoConTransferencia());
            }
        });

        if(!procesadorPago.hayFormaPago()) {
            dialogoError("No hay forma de pago", "Elije la forma de pago");
            return;
        }
        //Forma de pago con tarjeta:
        //Pide los datos de la tarjeta y realiza el pago con el banco

        HashMap<String,String> datosPago = new HashMap<>();
        datosPago.put("cuenta", "");
        datosPago.put("nombre", "");
        datosPago.put("cvv", "");
        if(procesadorPago.esPagoConTarjeta()) {
            DialogoTarjeta infoTarjeta = muestraDialogoTarjeta();
            if(infoTarjeta == null){
                dialogoError("Error al realizar la compra", "No se pudo realizar el pago porque falta informacion para procesarlo.");
                return;
            }else{
                datosPago.put("cuenta", infoTarjeta.getCuenta());
                datosPago.put("nombre", infoTarjeta.getNombreTitular());
                datosPago.put("cvv", infoTarjeta.getCVV());
            }
        }
        if(!procesadorPago.verificarInfoDePago(datosPago, usuarioActual)){
            dialogoError("Error al realizar la compra", "No se pudo realizar el pago porque la informacion no es valida.");
            return;
        }
        if (!procesadorPago.realizarPago(total, datosPago, vendedor.getNumeroCuenta())) {
            dialogoError("Error al realizar la compra", "No se pudo realizar el pago porque no hay recursos necesarios.");
            return;
        }
        //Aqui ya se autorizo la forma de pago
        //Se manda mensaje de exito
        Alert alerta2 = new Alert(AlertType.INFORMATION);
        alerta2.setTitle("Forma de pago");
        alerta2.setHeaderText("Ya definiste tu forma de pago");
        alerta2.setContentText(procesadorPago.mensajePago(total, vendedor));
        alerta2.showAndWait();

        
        //Se actualiza el stock --  Hay que hacer la peticion al servidor
        for (ElementoCarrito elemento : carrito.getElementosCarrito()) {
            Producto p = vendedor.getProducto(elemento.getCodigoBarras());
            Producto productoActualizado = new Producto(vendedor.getId(), p.getCodigoBarras(), p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getStockDisponible() - elemento.getCantidad(), p.getCategoria());
            try {
                mercadita.getConexion().enviaMensaje(Mensaje.REGISTRO_MODIFICADO_PRODUCTO);
                mercadita.getConexion().enviaRegistro(p);
                mercadita.getConexion().enviaRegistro(productoActualizado);
            } catch (IOException ioe) {
                dialogoError("Error con el servidor",
                            "No se pudieron enviar productos a modificar.");
            }
            mercadita.modificaProducto(p,productoActualizado);
            vendedor.modificaProducto(p, productoActualizado);
        }

        //Se elimina el carrito 
        cliente.vaciaCarrito(idCarrito);

        //Regresar a la vista de la tienda
        regresa(vendedor);
    }

    /**
     * Muestra un dialogo para pedir los datos de la tarjeta.
     * @return el dialogo de tarjeta.
     */
    public DialogoTarjeta muestraDialogoTarjeta() {
        DialogoTarjeta dialogo;
        try {
            dialogo = new DialogoTarjeta(escenario);
        } catch (IOException ioe) {
            String mensaje = ("Ocurrio un error al tratar de " +
                            "cargar el dialogo de tarjeta.");
            dialogoError("Error al cargar interfaz", mensaje);
            ioe.printStackTrace();
            return null;
        }
        dialogo.showAndWait();
        if (!dialogo.isAceptado())
            return null;
        dialogo.getEventDispatcher();
        return dialogo;
    }

}
