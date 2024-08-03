package mx.unam.ciencias.modelado.proyecto2.igu;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mx.unam.ciencias.modelado.proyecto2.Producto;

/**
 * <p>Clase para dialogos con formas para editar productos.</p>
 */
public class DialogoEditaProducto extends Stage {

    /* Vista de la forma para agregar/editar productos. */
    private static final String EDITA_PRODUCTO_FXML =
        "fxml/forma-edita-producto.fxml";

    /* El controlador. */
    private ControladorFormaEditaProducto controlador;

    /**
     * Define el estado inicial de un dialogo para producto.
     * @param escenario el escenario al que el dialogo pertenece.
     * @param producto el producto; puede ser <code>null</code> para agregar
     *                   un producto.
     * @param idVendedor el identificador del vendedor.
     * @throws IOException si no se puede cargar el archivo FXML.
     */
    public DialogoEditaProducto(Stage escenario, 
                                Producto producto,
                                int idVendedor) 
                                throws IOException {
        ClassLoader cl = getClass().getClassLoader();
        FXMLLoader cargador = new FXMLLoader(cl.getResource(EDITA_PRODUCTO_FXML));
        AnchorPane cristal = (AnchorPane) cargador.load();
        if (producto == null)
            setTitle("Agregar producto");
        else
            setTitle("Editar producto");
        initOwner(escenario);
        initModality(Modality.WINDOW_MODAL);
        Scene escena = new Scene(cristal);
        setScene(escena);
        controlador = cargador.getController();
        controlador.setEscenario(this);
        controlador.setProducto(producto);
        controlador.setIdVendedor(idVendedor);
        if (producto != null)
            controlador.deshabilitaEntradaCodigo();
        if (producto == null)
            controlador.setVerbo("Agregar");
        else
            controlador.setVerbo("Actualizar");
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
     * Regresa el Producto del dialogo.
     * @return el Producto del dialogo.
     */
    public Producto getProducto() {
        return controlador.getProducto();
    }
}
