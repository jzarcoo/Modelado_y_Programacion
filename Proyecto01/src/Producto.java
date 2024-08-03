package src;

import java.util.Iterator;

/**
 * <p>Clase para representar producto del catalogo de la tienda. Un producto tiene nombre,
 * precio, departamento y codigo de barras.</p>
 */
public class Producto extends CatalogoComponente {

    /**
    * <p>Clase para representar un iterador nulo.</p>
    */
    private class IteradorNulo implements Iterator<CatalogoComponente> {
    
        /**
         * Regresa el siguiente componente del catalogo.
         * @return el siguiente componente del catalogo.
         */
        @Override
        public CatalogoComponente next() {
            return null;
        }

        /**
         * Verifica si hay un siguiente componente en el catalogo.
         * @return <code>true</code> si hay un siguiente componente en el catalogo,
         *         <code>false</code> en otro caso.
         */
        @Override
        public boolean hasNext() {
            return false;
        }
    }
    
    /* Nombre del producto. */
    private String nombre;
    /* Precio del producto. */
    private double precio;
    /* Departamento del producto. */
    private DepartamentoTienda departamento;
    /* Codigo de barras del producto. */
    private String codigoDeBarras;

    /**
     * Define el estado inicial del producto.
     * @param nombre nombre del producto.
     * @param precio precio del producto.
     * @param departamento departamento del producto.
     * @param codigoDeBarras codigo de barras del producto
     */
    public Producto(String nombre, 
                    double precio, 
                    DepartamentoTienda departamento, 
                    String codigoDeBarras) {
        this.nombre = nombre;
        this.precio = precio;
        this.departamento = departamento;
        this.codigoDeBarras = codigoDeBarras;
    }

    /**
     * Busca un componente en el catalogo.
     * @param codigoDeBarras el codigo de barras del componente.
     * @return el componente con el codigo de barras especificado.
     */
    @Override
    public CatalogoComponente busca(String codigoDeBarras) {
        if (this.codigoDeBarras.equals(codigoDeBarras))
            return this;
        return null;
    }

    /**
     * Regresa una copia del componente del catalogo.
     * @return copia del componente del catalogo.
     */
    @Override
    public CatalogoComponente copia() {
        return new Producto(nombre, precio, departamento, codigoDeBarras);
        // try {
        //     return (CatalogoComponente) clone();
        // } catch (CloneNotSupportedException e) {
        //     return new Producto(nombre, precio, departamento, codigoDeBarras);
        // }
    }

    /**
     * Clona el producto.
     * @return clon del producto.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Aplica un descuento a un componente del catalogo.
     * @param descuento el descuento a aplicar.
     * @param departamento departamento al que se le aplicara el descuento.
     */
    @Override
    public void aplicaDescuento(double descuento, DepartamentoTienda departamento) {
        if (this.departamento == departamento)
            setPrecio(getPrecio() * (1-descuento));
    }

    /**
     * Regresa el nombre de producto.
     * @return nombre del producto.
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Regresa el precio del producto
     * @return precio del producto.
     */
    @Override
    public double getPrecio() {
        return precio;
    }

    /**
     * Regresa el departamento del producto.
     * @return departamento del producto.
     */
    @Override
    public DepartamentoTienda getDepartamento() {
        return departamento;
    }   

    /**
     * Regresa el codigo de barras del producto.
     * @return el codigo de barras del poducto.
     */
    @Override
    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }   

    /**
     * Define el nombre del producto.
     * @param nombre nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Define el precio del producto.
     * @param precio precio del producto.
     */
    @Override
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Define el departamento del producto.
     * @param departamento departamento del producto.
     */
    public void setDepartamento(DepartamentoTienda departamento) {
        this.departamento = departamento;
    }

    /**
     * Define el codigo de barras del producto.
     * @param codigoDeBarras codigo de barras del producto.
     */
    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    /**
     * Imprime la informacion del producto.
     */
    @Override
    public String imprime() {
        return toString();
    }

    /**
     * Regresa un iterador para el producto.
     * @return un iterador para el producto.
     */
    @Override
    public Iterator<CatalogoComponente> iterator() {
        return new IteradorNulo();
    }

    /**
     * Regresa una representacion en cadena del producto.
     * @return una representacion en cadena del producto.
     */
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
               "Precio: " + precio + "\n" +
               "Departamento: " + departamento + "\n" +
               "Codigo de barras: " + codigoDeBarras + "\n";
    }
}
