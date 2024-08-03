package mx.unam.ciencias.modelado.proyecto2.igu;

/**
 * <p>Clase abstracta para controladores del contenido de dialogo con formas con
 * datos de productos que se aceptan o rechazan.</p>
 */
public abstract class ControladorFormaProducto extends ControladorForma {

    /** El valor del codigo de barras */
    protected String codigoBarras;
    /** El valor del nombre. */
    protected String nombre;
    /** El valor de la descripcion. */
    protected String descripcion;
    /** El valor del precio. */
    protected double precio;
    /** El valor del stock disponible. */
    protected int stockDisponible;
    /** El valor de la categoria. */
    protected String categoria;

    /**
     * Verifica que el codigoBarras sea valido.
     * @param codigoBarras el codigoBarras a verificar.
     * @return <code>true</code> si el codigoBarras es valido; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaCodigoBarras(String codigoBarras) {
        if (codigoBarras == null || codigoBarras.isEmpty()) 
            return false;
        this.codigoBarras = codigoBarras;
        return true;
    }

    /**
     * Verifica que el nombre sea valido.
     * @param nombre el nombre a verificar.
     * @return <code>true</code> si el nombre es valido; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) 
            return false;
        this.nombre = nombre;
        return true;
    }

    /**
     * Verifica que el descripcion sea valido.
     * @param descripcion el descripcion a verificar.
     * @return <code>true</code> si el descripcion es valido; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()) 
            return false;
        this.descripcion = descripcion;
        return true;
    }

    /**
     * Verifica que el precio sea valido.
     * @param precio el precio a verificar.
     * @return <code>true</code> si el precio es valido; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaPrecio(String precio) {
        if (precio == null || precio.isEmpty()) return false;
        try {
            this.precio = Double.parseDouble(precio);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Verifica que el stockDisponible sea valida.
     * @param stockDisponible el stockDisponible a verificar.
     * @return <code>true</code> si el stockDisponible es valido; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaStockDisponible(String stockDisponible) {
        if (stockDisponible == null || stockDisponible.isEmpty()) return false;
        try {
            this.stockDisponible = Integer.parseInt(stockDisponible);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Verifica que el categoria sea valido.
     * @param categoria el categoria a verificar.
     * @return <code>true</code> si el categoria es valido; <code>false</code> en
     *         otro caso.
     */
    protected boolean verificaCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty()) 
            return false;
        this.categoria = categoria;
        return true;
    }

    
}
