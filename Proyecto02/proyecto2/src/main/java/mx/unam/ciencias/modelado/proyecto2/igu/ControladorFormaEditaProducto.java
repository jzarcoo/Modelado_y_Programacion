package mx.unam.ciencias.modelado.proyecto2.igu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import mx.unam.ciencias.modelado.proyecto2.Producto;

/**
 * <p>Clase para el controlador del contenido del dialogo para editar y crear
 * productos.</p>
 */
public class ControladorFormaEditaProducto extends ControladorFormaProducto {

    /* El id del vendedor. */
    private int idVendedor;
    /* La entrada verificable para el codigo de barras. */
    @FXML private EntradaVerificable entradaCodigo;
    /* La entrada verificable para el nombre. */
    @FXML private EntradaVerificable entradaNombre;
    /* La entrada verificable para la descripcion. */
    @FXML private EntradaVerificable entradaDescripcion;
    /* La entrada verificable para el precio. */
    @FXML private EntradaVerificable entradaPrecio;
    /* La entrada verificable para el stock disponible. */
    @FXML private EntradaVerificable entradaStock;
    /* La entrada verificable para la categoria. */
    @FXML private EntradaVerificable entradaCategoria;

    /* El Producto creado o editado. */
    private Producto producto;

    /**
     * Inicializa el estado de la forma.
     */
    @FXML private void initialize() {
        entradaCodigo.setVerificador(c -> verificaCodigoBarras(c));
        entradaNombre.setVerificador(n -> verificaNombre(n));
        entradaDescripcion.setVerificador(d -> verificaDescripcion(d));
        entradaPrecio.setVerificador(p -> verificaPrecio(p));
        entradaStock.setVerificador(s -> verificaStockDisponible(s));
        entradaCategoria.setVerificador(c -> verificaCategoria(c));

        entradaCodigo.textProperty().addListener(
            (o, v, n) -> verificaProducto());
        entradaNombre.textProperty().addListener(
            (o, v, n) -> verificaProducto());
        entradaDescripcion.textProperty().addListener(
            (o, v, n) -> verificaProducto());
        entradaPrecio.textProperty().addListener(
            (o, v, n) -> verificaProducto());
        entradaStock.textProperty().addListener(
            (o, v, n) -> verificaProducto());
        entradaCategoria.textProperty().addListener(
            (o, v, n) -> verificaProducto());
    }

    /**
     * Deshabilita la entrada de codigo de barras.
     */
    public void deshabilitaEntradaCodigo() {
        entradaCodigo.setDisable(true);
    }

    /**
     * Define el id del vendedor.
     * @param idVendedor el id del vendedor.
     */
    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    /** 
     * Manejador para cuando se activa el boton aceptar.
     * @param evento el evento que genero la accion.
     */
    @FXML private void aceptar(ActionEvent evento) {
        actualizaProducto();
        aceptado = true;
        escenario.close();
    }

    /**
     * Actualiza al Producto, o lo crea si no existe.
     */
    private void actualizaProducto() {
        if (producto != null) {
            producto.setCodigoBarras(codigoBarras);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStockDisponible(stockDisponible);
            producto.setCategoria(categoria);
        } else {
            producto = new Producto(idVendedor, codigoBarras, nombre, descripcion, precio,
                                    stockDisponible, categoria);
        }
    }

    /**
     * Define el Producto del dialogo.
     * @param producto el nuevo Producto del dialogo.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
        if (producto == null)
            return;
        this.producto = new Producto(0, null, null, null, 0, 0, null);
        this.producto.actualiza(producto);
        entradaCodigo.setText(producto.getCodigoBarras());
        entradaNombre.setText(producto.getNombre());
        entradaDescripcion.setText(producto.getDescripcion());
        String p = String.format("%2.2f", producto.getPrecio());
        entradaPrecio.setText(p);
        entradaStock.setText(String.valueOf(producto.getStockDisponible()));
        entradaCategoria.setText(producto.getCategoria());
    }

    /**
     * Regresa el Producto del dialogo.
     * @return el Producto del dialogo.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Define el verbo del boton de aceptar.
     * @param verbo el nuevo verbo del boton de aceptar.
     */
    public void setVerbo(String verbo) {
        botonAceptar.setText(verbo);
    }

    /**
     * Define el foco incial del dialogo.
     */
    @Override public void defineFoco() {
        entradaCodigo.requestFocus();
    }

    /**
     * Verifica que los cuatro campos sean validos.
     */
    private void verificaProducto() {
        boolean c = entradaCodigo.esValida();
        boolean n = entradaNombre.esValida();
        boolean d = entradaDescripcion.esValida();
        boolean p = entradaPrecio.esValida();
        boolean s = entradaStock.esValida();
        boolean ca = entradaCategoria.esValida();
        botonAceptar.setDisable(!c || !n || !d || !p || !s || !ca);
    }

    /**
     * Verifica que el nivel de stockDisponible sea valido.
     * @param stockDisponible el stockDisponible a verificar.
     * @return <code>true</code> si el stockDisponible es valido; <code>false</code> en
     *         otro caso.
     */
    @Override protected boolean verificaStockDisponible(String stockDisponible) {
        return super.verificaStockDisponible(stockDisponible) &&
                this.stockDisponible > 0;
    }

    /**
     * Verifica que el Precio sea valido.
     * @param precio el Precio a verificar.
     * @return <code>true</code> si el Precio es valido; <code>false</code> en
     *         otro caso.
     */
    @Override protected boolean verificaPrecio(String precio) {
        return super.verificaPrecio(precio) &&
                this.precio > 0;
    }
    
}
