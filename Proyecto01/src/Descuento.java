package src;

import java.util.Iterator;

/**
 * <p>Clase para representar un descuento en un producto del catalogo.</p>
 */
public class Descuento extends CatalogoComponente {

    /* Componente del catalogo real. */
    protected CatalogoComponente catalogoComponente;
    /* Descuento. */
    protected double descuento;
    /* Nombre del descuento. */
    protected String nombreDescuento;
    /* Departamento del descuento. */
    protected DepartamentoTienda departamento;

    /**
     * Define el estado inicial del descuento.
     * @param catalogoComponente componente del catalogo real.
     * @param descuento descuento.
     * @param nombreDescuento nombre del descuento.
     */
    public Descuento(CatalogoComponente catalogoComponente, 
                     double descuento, 
                     String nombreDescuento,
                     DepartamentoTienda departamento) {
        this.catalogoComponente = catalogoComponente;
        this.descuento = descuento;
        this.nombreDescuento = nombreDescuento;
        this.departamento = departamento;
        aplicaDescuento(this.descuento, this.departamento);
    }

    /**
     * Busca un componente en el catalogo.
     * @param codigoDeBarras el codigo de barras del componente.
     * @return el componente con el codigo de barras especificado.
     */
    @Override
    public CatalogoComponente busca(String codigoDeBarras) {
        return catalogoComponente.busca(codigoDeBarras);
    }

    /**
     * Regresa una copia del componente del catalogo.
     * @return copia del componente del catalogo.
     */
    @Override
    public CatalogoComponente copia() {
        return catalogoComponente.copia();
        // try {
        //     return (CatalogoComponente) clone();
        // } catch (CloneNotSupportedException e) {
        //     return new Descuento(catalogoComponente.copia(), descuento, nombreDescuento, departamento);
        // }
    }

    /**
     * Clona el descuento.
     * @return clon del descuento.
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
        for (CatalogoComponente componente : catalogoComponente)
            if (componente.getDepartamento() == departamento) 
                componente.aplicaDescuento(descuento, departamento);
    }

    /**
     * Regresa el nombre de producto.
     * @return nombre del producto.
     */
    @Override
    public String getNombre() {
        return catalogoComponente.getNombre();
    }

    /**
     * Regresa el departamento del producto.
     * @return departamento del producto.
     */
    @Override
    public DepartamentoTienda getDepartamento() {
        return catalogoComponente.getDepartamento();
    }

    /**
     * Regresa el precio del producto
     * @return precio del producto.
     */
    @Override
    public double getPrecio() {
        return catalogoComponente.getPrecio();
    }

    /**
     * Define el precio del componente del catalogo.
     * @param precio precio del componente.
     */
    @Override
    public void setPrecio(double price) {
        catalogoComponente.setPrecio(price);
    }

    /**
     * Imprime el componente del catalogo.
     */
    @Override
    public String imprime() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombreDescuento+"\n");
        sb.append( catalogoComponente.imprime()+"\n");
        return sb.toString();
    }

    /**
     * Regresa un iterador para el descuento.
     * @return un iterador para el descuento.
     */
    @Override
    public Iterator<CatalogoComponente> iterator() {
        return catalogoComponente.iterator();
    }
}
