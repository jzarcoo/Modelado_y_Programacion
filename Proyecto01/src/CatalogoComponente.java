package src;

/**
 * <p>Clase abstracta para representar un componente del catalogo de la tienda.</p>
 * 
 * <p>Esta clase implementa el patron de diseno de Composite. Un componente del catalogo
 * puede ser un producto o un departamento, o bien un descuento que los envuelve.</p>
 * 
 * <p>Esta clase implementa la interfaz Cloneable, por lo que se puede clonar. Es decir, 
 * implementa el patron de diseno Prototype. Ademas, utiliza clonacion profunda, donde
 * se clonan los terceros objetos dando lugar a nuevas referencias independientes.</p>
 * 
 * <p>Esta clase implementa la interfaz Iterable, por lo que se puede iterar con 
 * el operador <code>for-each</code> sobre los componentes del catalogo.</p>
 */
public abstract class CatalogoComponente implements Iterable<CatalogoComponente>, Cloneable {
    
    /**
     * Agrega un componente al catalogo.
     * @param catalogoComponente el componente a agregar.
     */
	public void agrega(CatalogoComponente catalogoComponente) {
		throw new UnsupportedOperationException();
	}

    /**
     * Elimina un componente del catalogo.
     * @param catalogoComponente el componente a eliminar.
     */
	public void elimina(CatalogoComponente catalogoComponente) {
		throw new UnsupportedOperationException();
	}

    /**
     * Regresa el componente en la posicion especificada.
     * @param i la posicion del componente.
     * @return el componente en la posicion especificada.
     */
    public CatalogoComponente getHijo(int i) {
        throw new UnsupportedOperationException();
    }

    /**
     * Regresa el precio del producto
     * @return precio del producto.
     */
    public double getPrecio() {
        throw new UnsupportedOperationException();
    }

    /**
     * Regresa el codigo de barras del producto.
     * @return el codigo de barras del poducto.
     */
    public String getCodigoDeBarras() {
        throw new UnsupportedOperationException();
    }

    /**
     * Define el precio del componente del catalogo.
     * @param precio precio del componente.
     */
    public void setPrecio(double price) {
        throw new UnsupportedOperationException();
    }

    /**
     * Aplica un descuento a un componente del catalogo.
     * @param descuento el descuento a aplicar.
     * @param departamento departamento al que se le aplicara el descuento.
     */
    public void aplicaDescuento(double descuento, DepartamentoTienda departamento) {
        throw new UnsupportedOperationException();
    }

    /**
     * Regresa el nombre de producto.
     * @return nombre del producto.
     */
    public abstract String getNombre();

    /**
     * Regresa el departamento del producto.
     * @return departamento del producto.
     */
    public abstract DepartamentoTienda getDepartamento();

    /**
     * Imprime el componente del catalogo.
     */
    public abstract String imprime();

    /**
     * Regresa una copia del componente del catalogo.
     * @return copia del componente del catalogo.
     */
    public abstract CatalogoComponente copia();

    /**
     * Busca un componente en el catalogo.
     * @param codigoDeBarras el codigo de barras del componente.
     * @return el componente con el codigo de barras especificado.
     */
    public abstract CatalogoComponente busca(String codigoDeBarras);
}
