package mx.unam.ciencias.modelado.proyecto2.igu;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import mx.unam.ciencias.modelado.proyecto2.CampoCritica;
import mx.unam.ciencias.modelado.proyecto2.Critica;
import mx.unam.ciencias.modelado.proyecto2.InterfazAdministrador;
import mx.unam.ciencias.modelado.proyecto2.red.Mensaje;

/**
 * <p>Clase que define el controlador de la vista del administrador.</p>
 */
public class ControladorVistaAdministrador extends Controlador {

    /* La escena */
    private Scene escena;
    
    /* Las criticas del vendedor. */
    private ObservableList<Critica> criticas;

    /* Critica seleccionada. */
    private Critica criticaSeleccionada;

    /**
     * Inicializa el controlador.
     */
    public void initialize() {
        criticas = FXCollections.observableArrayList();
        try {
            mostrarClima();
        } catch (Exception e) {
            //dialogoError("Error al cargar el clima", "No se pudo cargar el clima.");
        }
        nombreUsuario.setText(usuarioActual.getNombreUsuario());
        ponerNombreUsuario();
        for(Critica critica : mercadita.getCriticas())
            criticas.add(critica);
    }

    /**
     * Define la escena.
     * @param vistaVendedor la escena.
     */
    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    /**
     * Carga las criticas de los vendedores.
     */
    public void cargaCriticas() {
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
        } else if(criticas.size() <= 6) {
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

            // Boton para eliminar critica
            FontAwesomeIconView icon = new FontAwesomeIconView();
            icon.setGlyphName("TRASH_ALT");
            Button button = new Button();
            button.setGraphic(icon);

            button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #3e3e3e;"));
            button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #4793af;"));

            button.setOnAction(e -> {
                // todos no seleccionados
                for (javafx.scene.Node child : gridPane.getChildren())
                    child.setStyle("-fx-background-color: " + backgroundColor + "; -fx-background-radius: 10;");
                vBox.setStyle("-fx-background-color: #ffc700; -fx-background-radius: 10;");
                criticaSeleccionada = c;
                eliminaCritica(e);
            });
            button.setStyle("-fx-background-color: #4793af;");

            // Crear un HBox para contener el boton
            HBox hboxBoton = new HBox();
            hboxBoton.getChildren().add(button);
            hboxBoton.setAlignment(Pos.TOP_RIGHT);
            hboxBoton.setPadding(new Insets(3)); // Ajustar el espaciado segun sea necesario

            // Agregar el HBox al VBox
            vBox.getChildren().add(hboxBoton);
            
            Label labelId = new Label(CampoCritica.ID + ": " + c.getId());
            labelId.setWrapText(true);
            vBox.getChildren().add(labelId);
            Label labelVendedor = new Label(CampoCritica.ID_VENDEDOR + ": " + c.getIdVendedor());
            labelVendedor.setWrapText(true);
            vBox.getChildren().add(labelVendedor);
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
     * Elimina la critica seleccionada.
     * @param evento el evento que desencadeno la accion.
     */
    @FXML private void eliminaCritica(ActionEvent evento) {
        String titulo = "Eliminar critica";
        String mensaje = "Esto eliminara la critica seleccionada"; 
        String aceptar = titulo;
        String cancelar = "Consevar critica";
        if (!dialogoDeConfirmacion(titulo, mensaje, "¿Esta seguro?", aceptar, cancelar))
            return;
        // Red
        try {
            mercadita.getConexion().enviaMensaje(Mensaje.REGISTRO_ELIMINADO_CRITICA);
            mercadita.getConexion().enviaRegistro(criticaSeleccionada);
        } catch (IOException ioe) {
            dialogoError("Error con el servidor",
                         "No se pudo enviar una critica a eliminar");
        }
        // Recargar la vista
        eliminaCritica(criticaSeleccionada);
    }

    /**
     * Elimina una critica de la vista.
     * @param critica la critica a eliminar.
     */
    private void eliminaCritica(Critica critica) {
        mercadita.eliminaCritica(criticaSeleccionada);
        criticas.remove(critica);
        cargaCriticas();
    }

}