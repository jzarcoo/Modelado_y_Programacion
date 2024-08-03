package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Clase para bases de datos de Productos.</p>
 */
public class BaseDeDatosProductos extends BaseDeDatos<Producto, CampoProducto> {

    /**
     * Crea un Producto en blanco.
     * @return un Producto en blanco.
     */
    @Override 
    public Producto creaRegistro() {
        return new Producto(0, null, null, null, 0, 0, null);
    }
}
