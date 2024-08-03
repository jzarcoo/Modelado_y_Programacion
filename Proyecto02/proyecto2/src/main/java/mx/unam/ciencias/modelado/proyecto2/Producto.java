package mx.unam.ciencias.modelado.proyecto2;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <p>Clase para representar productos de los vendedores de la tienda. Un producto tiene
 * el id del vendedor que vende el producto, uncodigo de barras, NOMBRE, DESCRIPCION, PRECIO, 
 * numero de items disponibles, CATEGORIA del producto. La clase implementa {@link Registro}, 
 * por lo que puede seriarse en una linea de texto y deseriarse de una linea de texto; ademas 
 * de determinar si sus campos casan valores arbitrarios y actualizarse con los valores de otro 
 * producto.</p>
 */
public class Producto implements Registro<Producto, CampoProducto> {

    /* Mensaje por error al deseriar. */
    private static final String ERROR_DESERIA = "Linea invalida de producto de vendedor.";

    /* ID del vendedor que vende el producto. */
    private final IntegerProperty ID_VENDEDOR;
    /* Codigo de barras del producto (es como el id). */
    private final StringProperty CODIGO_BARRAS;
    /* Nombre del producto. */
    private final StringProperty NOMBRE;
    /* Descripcion del producto. */
    private final StringProperty DESCRIPCION;
    /* Precio del producto. */
    private final DoubleProperty PRECIO;
    /* Numero de items disponibles */
    private final IntegerProperty STOCK_DISPONIBLE;
    /* Categoria del producto */
    private final StringProperty CATEGORIA;

    /**
     * Define el estado inicial de un Producto.
     * @param ID_VENDEDOR el id del vendedor que vende el producto.
     * @param CODIGO_BARRAS el codigo de barras del producto.
     * @param NOMBRE el NOMBRE del producto.
     * @param DESCRIPCION la DESCRIPCION del producto.
     * @param PRECIO el PRECIO del producto.
     * @param STOCK_DISPONIBLE el stock disponible del producto.
     * @param CATEGORIA la CATEGORIA del producto.
     */
    public Producto(int ID_VENDEDOR,
                    String CODIGO_BARRAS, 
                    String NOMBRE, 
                    String DESCRIPCION, 
                    double PRECIO,
                    int STOCK_DISPONIBLE, 
                    String CATEGORIA) {
        this.ID_VENDEDOR = new SimpleIntegerProperty(ID_VENDEDOR);
        this.CODIGO_BARRAS = new SimpleStringProperty(CODIGO_BARRAS);
        this.NOMBRE = new SimpleStringProperty(NOMBRE);
        this.DESCRIPCION = new SimpleStringProperty(DESCRIPCION);
        this.PRECIO = new SimpleDoubleProperty(PRECIO);
        this.STOCK_DISPONIBLE = new SimpleIntegerProperty(STOCK_DISPONIBLE);
        this.CATEGORIA = new SimpleStringProperty(CATEGORIA);
    }
    
    /**
     * Regresa el ID del Vendedor al que referencia el producto.
     * @return el ID del Vendedor al que referencia el producto.
     */
    public int getIdVendedor() {
        return ID_VENDEDOR.get();
    }

    /**
     * Define el ID del Vendedor al que referencia el producto.
     * @param ID_VENDEDOR el nuevo ID del Vendedor al que referencia el producto.
     */
    public void setIdVendedor(int ID_VENDEDOR) {
        this.ID_VENDEDOR.set(ID_VENDEDOR);
    }

    /**
     * Regresa la propiedad del ID del Vendedor al que referencia el producto.
     * @return la propiedad del ID del Vendedor al que referencia el producto.
     */
    public IntegerProperty idVendedorProperty() {
        return ID_VENDEDOR;
    }

    /**
     * Regresa el codigo de barras del producto.
     * @return el codigo de barras del producto.
     */
    public String getCodigoBarras() {
        return CODIGO_BARRAS.get();
    }

    /**
     * Define el codigo de barras del producto.
     * @param CODIGO_BARRAS el nuevo codigo de barras del producto.
     */
    public void setCodigoBarras(String CODIGO_BARRAS) {
        this.CODIGO_BARRAS.set(CODIGO_BARRAS);
    }

    /**
     * Regresa la propiedad del codigo de barras del producto.
     * @return la propiedad del codigo de barras del producto.
     */
    public StringProperty codigoBarrasProperty() {
        return CODIGO_BARRAS;
    }

    /**
     * Regresa el NOMBRE del producto.
     * @return el NOMBRE del producto. 
     */
    public String getNombre() {
        return NOMBRE.get();
    }

    /**
     * Define el NOMBRE del producto. 
     * @param NOMBRE el nuevo NOMBRE del producto. 
     */
    public void setNombre(String NOMBRE) {
        this.NOMBRE.set(NOMBRE);
    }

    /**
     * Regresa la propiedad del NOMBRE del producto.
     * @return la propiedad del NOMBRE del producto.
     */
    public StringProperty nombreProperty() {
        return NOMBRE;
    }

    /**
     * Regresa la DESCRIPCION del producto.
     * @return la DESCRIPCION del producto.
     */
    public String getDescripcion() {
        return DESCRIPCION.get();
    }

    /**
     * Define la DESCRIPCION del producto.
     * @return la nueva DESCRIPCION del producto.
     */
    public void setDescripcion(String DESCRIPCION) {
        this.DESCRIPCION.set(DESCRIPCION);
    }

    /**
     * Regresa la propiedad de DESCRIPCION del producto.
     * @return la propiedad de DESCRIPCION del producto.
     */
    public StringProperty descripcionProperty() {
        return DESCRIPCION;
    }

    /**
     * Regresa el PRECIO del producto.
     * @return el PRECIO del producto.
     */
    public double getPrecio() {
        return PRECIO.get();
    }

    /**
     * Define el PRECIO del producto.
     * @return el nuevo PRECIO del producto.
     */
    public void setPrecio(double PRECIO) {
        this.PRECIO.set(PRECIO);
    }

    /**
     * Regresa la propiedad del PRECIO del producto.
     * @return la propiedad del PRECIO del producto.
     */
    public DoubleProperty precioProperty() {
        return PRECIO;
    }

    /**
     * Regresa el numero de items disponibles del producto.
     * @return el numero de items disponibles del producto.
     */
    public int getStockDisponible() {
        return STOCK_DISPONIBLE.get();
    }

    /**
     * Define el numero de items disponibles del producto.
     * @return la nueva cantidad de items disponibles del producto.
     */
    public void setStockDisponible(int STOCK_DISPONIBLE) {
        this.STOCK_DISPONIBLE.set(STOCK_DISPONIBLE);
    }

    /**
     * Regresa la propiedad del numero de items disponibles del producto.
     * @return la propiedad del numero de items disponibles del producto.
     */
    public IntegerProperty stockDisponibleProperty() {
        return STOCK_DISPONIBLE;
    }

    /**
     * Regresa la CATEGORIA del producto. 
     * @return la CATEGORIA del producto.
     */
    public String getCategoria() {
        return CATEGORIA.get();
    }

    /**
     * Define la CATEGORIA del producto.
     * @return la nueva CATEGORIA del producto. 
     */
    public void setCategoria(String CATEGORIA) {
        this.CATEGORIA.set(CATEGORIA);
    }

    /**
     * Regresa la propiedad de CATEGORIA del producto.
     * @return la propiedad de CATEGORIA del producto.
     */
    public StringProperty categoriaProperty() {
        return CATEGORIA;
    }

    /**
     * Regresa una representacion en cadena del Producto.
     * @return una representacion en cadena del Producto.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID Vendedor : %d%n", getIdVendedor()));
        sb.append(String.format("Codigo de Barras : %s%n", getCodigoBarras()));
        sb.append(String.format("Nombre : %s%n", getNombre()));
        sb.append(String.format("Descripcion : %s%n", getDescripcion()));
        sb.append(String.format("Precio : %2.2f%n", getPrecio()));
        sb.append(String.format("Disponibles : %d%n", getStockDisponible()));
        sb.append(String.format("Categoria : %s%n", getCategoria()));
        return sb.toString();
    }

    /**
     * Nos dice si el objeto recibido es un Producto igual al que manda llamar
     * el metodo.
     * @param objeto el objeto con el que el Producto se comparara.
     * @return <code>true</code> si el objeto recibido es un Producto con las
     *         mismas propiedades que el objeto que manda llamar al metodo,
     *         <code>false</code> en otro caso.
     */
    @Override 
    public boolean equals(Object objeto) {
        if (!(objeto instanceof Producto)) return false;
        Producto producto = (Producto) objeto;
        return getIdVendedor() == producto.getIdVendedor() &&
               getCodigoBarras().equals(producto.getCodigoBarras()) &&
               getNombre().equals(producto.getNombre()) &&
               getDescripcion().equals(producto.getDescripcion()) &&
               getPrecio() == producto.getPrecio() &&
               getStockDisponible() == producto.getStockDisponible() &&
               getCategoria().equals(producto.getCategoria());
    }

    /**
     * Regresa el Producto seriado en una linea de texto. La linea de
     * texto que este metodo regresa debe ser aceptada por el metodo {@link
     * Producto#deseria}.
     * @return la seriacion del Producto en una linea de texto.
     */
    @Override
    public String seria() {
        return String.format("%d\t%s\t%s\t%s\t%2.2f\t%d\t%s%n",
               getIdVendedor(), getCodigoBarras(), getNombre(), getDescripcion(), getPrecio(),
               getStockDisponible(), getCategoria());
    }

    /**
     * Deseria una linea de texto en las propiedades del Producto. La
     * seriacion producida por el metodo {@link Producto#seria} debe
     * ser aceptada por este metodo.
     * @param linea la linea a deseriar.
     * @throws ExcepcionLineaInvalida si la linea recibida es nula, vacia o no
     *         es una seriacion valida de un Producto.
     */
    @Override
    public void deseria(String linea) {
        if (linea == null) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        String cadena = linea.trim();
        if (cadena.isEmpty()) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        String[] campos = cadena.split("\t");
        if (campos.length != 7) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        setCodigoBarras(campos[1]);
        setNombre(campos[2]);
        setDescripcion(campos[3]);
        setCategoria(campos[6]);
        try {
            setIdVendedor(Integer.parseInt(campos[0]));
            setPrecio(Double.parseDouble(campos[4]));
            setStockDisponible(Integer.parseInt(campos[5]));
        } catch (NumberFormatException nfe) {
            throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        }
    }

    /**
     * Actualiza los valores del producto con los del Producto recibido.
     * @param producto el Producto con el cual actualizar los valores.
     * @throws IllegalArgumentException si el Producto es <code>null</code>.
     */
    @Override
    public void actualiza(Producto producto) {
        if (producto == null) throw new IllegalArgumentException("Producto invalido.");
        setIdVendedor(producto.getIdVendedor());
        setCodigoBarras(producto.getCodigoBarras());
        setNombre(producto.getNombre());
        setDescripcion(producto.getDescripcion());
        setPrecio(producto.getPrecio());
        setStockDisponible(producto.getStockDisponible());
        setCategoria(producto.getCategoria());
    }

    /**
     * Nos dice si el Producto casa el valor dado en el campo especificado.
     * @param campo el campo que hay que casar.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoProducto#ID_VENDEDOR} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual al id del vendedor del Producto.</li>
     *           <li><code>campo</code> es {@link CampoProducto#CODIGO_DE_BARRAS} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del codigo de barras del Producto.</li>
     *           <li><code>campo</code> es {@link CampoProducto#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del NOMBRE del Producto.</li>
     *           <li><code>campo</code> es {@link CampoProducto#DESCRIPCION} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena de la DESCRIPCION del Producto.</li>
     *           <li><code>campo</code> es {@link CampoProducto#PRECIO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al PRECIO del producto.</li>
     *           <li><code>campo</code> es {@link CampoProducto#STOCK_DISPONIBLES} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la cantidad de items disponibles 
     *              del Producto.</li>
     *           <li><code>campo</code> es {@link CampoProducto#CATEGORIA} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del NOMBRE de la CATEGORIA del producto.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override 
    public boolean casa(CampoProducto campo, Object valor) {
	    if (!(campo instanceof CampoProducto)) throw new IllegalArgumentException("Campo invalido.");
	    CampoProducto campoProducto = (CampoProducto) campo;
        switch (campoProducto) {
            case ID_VENDEDOR: return casaIDVendedor(valor);
            case CODIGO_DE_BARRAS: return casaCodigoBarras(valor);
            case NOMBRE: return casaNombre(valor);
            case DESCRIPCION: return casaDescripcion(valor);
            case PRECIO: return casaPrecio(valor);
            case STOCK_DISPONIBLES: return casaStockDisponibles(valor);
            case CATEGORIA: return casaCategoria(valor);
            default: return false;
        }
    }
    
    /**
     * Nos dice si el producto casa el ID_VENDEDOR.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Integer} y su
     *         valor entero es menor o igual al ID_VENDEDOR de el producto.</li>
     *         <code>false</code> en otro caso.
     */
    private boolean casaIDVendedor(Object valor) {
	    if (!(valor instanceof Integer)) return false;
        Integer valorID = (Integer) valor;
        return valorID.intValue() <= getIdVendedor();
    }
    
    /**
     * Nos dice si el Producto casa la CodigoBarras del Producto.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link String} y
     *         es una subcadena de la CodigoBarras del Producto.
     *         <code>false</code> en otro caso.
     */
    private boolean casaCodigoBarras(Object valor) {
	    if (!(valor instanceof String)) return false;
	    String cadena = (String) valor;
	    String valorCodigoBarras = cadena.trim();
	    if (valorCodigoBarras.isEmpty()) return false;
	    return getCodigoBarras().contains(valorCodigoBarras);
    }
    
    /**
     * Nos dice si el producto casa la Nombre del producto.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link String} y
     *         es una subcadena de la Nombre del producto.
     *         <code>false</code> en otro caso.
     */
    private boolean casaNombre(Object valor) {
	    if (!(valor instanceof String)) return false;
	    String cadena = (String) valor;
	    String valorNombre = cadena.trim();
	    if (valorNombre.isEmpty()) return false;
	    return getNombre().contains(valorNombre);
    }
    
    /**
     * Nos dice si el producto casa la Descripcion del producto.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link String} y
     *         es una subcadena de la Descripcion del producto.
     *         <code>false</code> en otro caso.
     */
    private boolean casaDescripcion(Object valor) {
	    if (!(valor instanceof String)) return false;
	    String cadena = (String) valor;
	    String valorDescripcion = cadena.trim();
	    if (valorDescripcion.isEmpty()) return false;
	    return getDescripcion().contains(valorDescripcion);
    }
    
    /**
     * Nos dice si el producto casa el PRECIO  del producto.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Double} y su
     *         valor doble es menor o igual al PRECIO del producto.</li>
     *         <code>false</code> en otro caso.
     */
    private boolean casaPrecio(Object valor) {
	    if (!(valor instanceof Double)) return false;
	    Double valorPrecio = (Double) valor;
	    return valorPrecio.doubleValue() <= getPrecio();
    }
    
    /**
     * Nos dice si el producto casa el STOCK_DISPONIBLES.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Integer} y su
     *         valor entero es menor o igual al STOCK_DISPONIBLES de el producto.</li>
     *         <code>false</code> en otro caso.
     */
    private boolean casaStockDisponibles(Object valor) {
	    if (!(valor instanceof Integer)) return false;
        Integer valorStockDisponibles = (Integer) valor;
        return valorStockDisponibles.intValue() <= getStockDisponible();
    }
    
    /**
     * Nos dice si el producto casa la Categoria del producto.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link String} y
     *         es una subcadena de la Categoria del producto.
     *         <code>false</code> en otro caso.
     */
    private boolean casaCategoria(Object valor) {
	    if (!(valor instanceof String)) return false;
	    String cadena = (String) valor;
	    String valorCategoria = cadena.trim();
	    if (valorCategoria.isEmpty()) return false;
	    return getNombre().contains(valorCategoria);
    }
}