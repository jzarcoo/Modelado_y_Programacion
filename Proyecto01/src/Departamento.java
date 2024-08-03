package src;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 * <p>Clase para representar un departamento del catalogo de la tienda. Un departamento tiene nombre y
 * contiene productos.</p>
 */
public class Departamento extends CatalogoComponente {

    /* Nombre del departamento. */
    private DepartamentoTienda nombreDepartamento;
    /* Lista que almacena los productos del departamento. */
    private List<CatalogoComponente> catalogoComponentes;

    /**
     * Define el estado inicial del departamento.
     * @param nombreDepartamento nombre del departamento.
     */
    public Departamento(DepartamentoTienda nombreDepartamento){
        this.nombreDepartamento = nombreDepartamento;
        catalogoComponentes = new LinkedList<CatalogoComponente>();
    }

    /**
     * Define el estado inicial del departamento.
     * @param nombreDepartamento nombre del departamento.
     * @param catalogoComponentes productos del departamento.
     */
    public Departamento(DepartamentoTienda nombreDepartamento, 
                        List<CatalogoComponente> catalogoComponentes){
        this.nombreDepartamento = nombreDepartamento;
        this.catalogoComponentes = catalogoComponentes;
    }

    /**
     * Busca un componente en el catalogo.
     * @param codigoDeBarras el codigo de barras del componente.
     * @return el componente con el codigo de barras especificado.
     */
    @Override
    public CatalogoComponente busca(String codigoDeBarras) {
        for (CatalogoComponente catalogoComponente : catalogoComponentes) {
            CatalogoComponente componente = catalogoComponente.busca(codigoDeBarras);
            if (componente != null) 
                return componente;
        }
        return null;
    }

    /**
     * Regresa una copia del componente del catalogo.
     * @return copia del componente del catalogo.
     */
    @Override
    public CatalogoComponente copia() {
        Departamento departamento = new Departamento(nombreDepartamento);
        for (CatalogoComponente catalogoComponente : catalogoComponentes) 
            departamento.agrega(catalogoComponente.copia());
        return departamento;
        // try {
        //     return (CatalogoComponente) clone();
        // } catch (CloneNotSupportedException e) {
        //     Departamento departamento = new Departamento(nombreDepartamento);
        //     for (CatalogoComponente catalogoComponente : catalogoComponentes) 
        //         departamento.agrega(catalogoComponente.copia());
        //     return departamento;
        // }
    }

    /**
     * Clona el departamento.
     * @return clon del departamento.
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
        for (CatalogoComponente componente : catalogoComponentes) 
            if (componente.getDepartamento() == departamento) 
                componente.aplicaDescuento(descuento, departamento);
    }

    /**
     * Agrega un producto al catalogo.
     * @param producto producto a agregar.
     */
    @Override
	public void agrega(CatalogoComponente catalogoComponente) {
        catalogoComponentes.add(catalogoComponente);
    }

    /**
     * Elimina un componente del catalogo.
     * @param catalogoComponente el componente a eliminar.
     */
    @Override
	public void elimina(CatalogoComponente catalogoComponente) {
        catalogoComponentes.remove(catalogoComponente);
    }

    /**
     * Regresa el componente en la posicion especificada.
     * @param i la posicion del componente.
     * @return el componente en la posicion especificada.
     */
    @Override
    public CatalogoComponente getHijo(int i) {
        return (CatalogoComponente) catalogoComponentes.get(i);
    }

    /**
     * Regresa el nombre del departamento.
     * @return nombre del departamento.
     */
    @Override
    public String getNombre() {
        return nombreDepartamento.toString();
    }

    /**
     * Regresa el departamento.
     * @return departamento.
     */
    @Override
    public DepartamentoTienda getDepartamento() {
        return nombreDepartamento;
    }

    /**
     * Imprime el departamento del catalogo.
     */
    @Override
    public String imprime() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n** " + nombreDepartamento + " **\n\n");
        for (CatalogoComponente catalogoComponente : catalogoComponentes)
            sb.append(catalogoComponente.imprime()+"\n");
        return sb.toString();
    }

    /**
     * Regresa un iterador para recorrer los productos del departamento.
     * @return un iterador para recorrer los productos del departamento.
     */
    @Override
    public Iterator<CatalogoComponente> iterator() {
        return catalogoComponentes.iterator();
    }
}

