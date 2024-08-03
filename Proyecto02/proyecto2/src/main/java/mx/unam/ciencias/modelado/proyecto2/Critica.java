package mx.unam.ciencias.modelado.proyecto2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <p>Clase para representar criticas para los vendedores de la tienda. Una critica 
 * para vendedor tiene el ID de la critica, el ID del vendedor al que hace referencia, el 
 * ID del cliente que la publico, una PUNTUACION, un COMENTARIO y un contador de REPORTES. La clase implementa 
 * {@link Registro}, por lo que puede seriarse en una linea de texto y deseriarse de una 
 * linea de texto; ademas de determinar si sus campos casan valores arbitrarios y
 * actualizarse con los valores de otra critica.</p>
 */
public class Critica implements Registro<Critica, CampoCritica> {

    /* Mensaje por error al deseriar. */
    private static final String ERROR_DESERIA = "Linea invalida de critica para vendedor.";

    /* ID de la Critica. */
    private final IntegerProperty ID;
    /* ID del Vendedor al que referencia. */
    private final IntegerProperty ID_VENDEDOR;
    /* ID del Cliente que la publico. */
    private final IntegerProperty ID_CLIENTE;
    /* Puntuacion de la Critica. */
    private final IntegerProperty PUNTUACION;
    /* Comentario de la Critica. */
    private final StringProperty COMENTARIO;
    /* Conteo de REPORTES de la Critica. */
    private final IntegerProperty REPORTES;

    /**
     * Define el estado inicial de una Critica.
     * @param ID el ID de la Critica.
     * @param ID_VENDEDOR el ID del Vendedor al que hace referencia.
     * @param ID_CLIENTE el ID del Cliente que la publico.
     * @param PUNTUACION la PUNTUACION de la Critica.
     * @param COMENTARIO el COMENTARIO de la Critica.
     * @param REPORTES el conteo de REPORTES de la Critica.
     */
    public Critica(int ID, 
                    int ID_VENDEDOR,
                    int ID_CLIENTE, 
                    int PUNTUACION,
                    String COMENTARIO,
                    int REPORTES) {
        this.ID = new SimpleIntegerProperty(ID);
        this.ID_VENDEDOR = new SimpleIntegerProperty(ID_VENDEDOR);
        this.ID_CLIENTE = new SimpleIntegerProperty(ID_CLIENTE);
        this.PUNTUACION = new SimpleIntegerProperty(PUNTUACION);
        this.COMENTARIO = new SimpleStringProperty(COMENTARIO);
        this.REPORTES = new SimpleIntegerProperty(REPORTES);
    }

    /**
     * Regresa el ID de la Critica.
     * @return el ID de la Critica.
     */
    public int getId() {
        return ID.get();
    }

    /**
     * Define el ID de la Critica.
     * @param ID el nuevo ID de la Critica.
     */
    public void setId(int ID) {
        this.ID.set(ID);
    }

    /**
     * Regresa la propiedad del ID de la Critica.
     * @return la propiedad del ID de la Critica.
     */
    public IntegerProperty idProperty() {
        return ID;
    }
    
    /**
     * Regresa el ID del Vendedor al que referencia la Critica.
     * @return el ID del Vendedor al que referencia la Critica.
     */
    public int getIdVendedor() {
        return ID_VENDEDOR.get();
    }

    /**
     * Define el ID del Vendedor al que referencia la Critica.
     * @param ID_VENDEDOR el nuevo ID del Vendedor al que referencia la Critica.
     */
    public void setIdVendedor(int ID_VENDEDOR) {
        this.ID_VENDEDOR.set(ID_VENDEDOR);
    }

    /**
     * Regresa la propiedad del ID del Vendedor al que referencia la Critica.
     * @return la propiedad del ID del Vendedor al que referencia la Critica.
     */
    public IntegerProperty idVendedorProperty() {
        return ID_VENDEDOR;
    }
    
    /**
     * Regresa el ID del Cliente que publico la Critica.
     * @return el ID del Cliente que publico la Critica.
     */
    public int getIdCliente() {
        return ID_CLIENTE.get();
    }

    /**
     * Define el ID del Cliente que publico la Critica.
     * @param ID_CLIENTE el nuevo ID del Cliente que publico la Critica.
     */
    public void setIdCliente(int ID_CLIENTE) {
        this.ID_CLIENTE.set(ID_CLIENTE);
    }

    /**
     * Regresa la propiedad del ID del Cliente que publico la Critica.
     * @return la propiedad del ID del Cliente que publico la Critica.
     */
    public IntegerProperty idClienteProperty() {
        return ID_CLIENTE;
    }
    
    /**
     * Regresa la PUNTUACION de la Critica.
     * @return la PUNTUACION de la Critica.
     */
    public int getPuntuacion() {
        return PUNTUACION.get();
    }

    /**
     * Define la PUNTUACION de la Critica.
     * @param PUNTUACION la nueva PUNTUACION de la Critica.
     */
    public void setPuntuacion(int PUNTUACION) {
        this.PUNTUACION.set(PUNTUACION);
    }

    /**
     * Regresa la propiedad de PUNTUACION de la Critica.
     * @return la propiedad de PUNTUACION de la Critica.
     */
    public IntegerProperty puntuacionProperty() {
        return PUNTUACION;
    }

    /**
     * Regresa el COMENTARIO de la Critica.
     * @return el COMENTARIO de la Critica.
     */
    public String getComentario() {
        return COMENTARIO.get();
    }

    /**
     * Define el COMENTARIO de la Critica.
     * @param COMENTARIO el nuevo COMENTARIO de la Critica.
     */
    public void setComentario(String COMENTARIO) {
        this.COMENTARIO.set(COMENTARIO);
    }

    /**
     * Regresa la propiedad del COMENTARIO de la Critica.
     * @return la propiedad del COMENTARIO de la Critica.
     */
    public StringProperty comentarioProperty() {
        return COMENTARIO;
    }

    /**
     * Regresa el conteo de REPORTES de la Critica.
     * @return el conteo de REPORTES de la Critica.
     */
    public int getReportes() {
        return REPORTES.get();
    }

    /**
     * Define el conteo de REPORTES de la Critica.
     * @param REPORTES el nuevo conteo de REPORTES de la Critica.
     */
    public void setReportes(int REPORTES) {
        this.REPORTES.set(REPORTES);
    }

    /**
     * Regresa la propiedad del conteo de REPORTES de la Critica.
     * @return la propiedad del conteo de REPORTES de la Critica.
     */
    public IntegerProperty reportesProperty() {
        return REPORTES;
    }

    /**
     * Regresa una representacion en cadena de la Critica.
     * @return una representacion en cadena de la Critica.
     */
    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID                              : %d%n", getId()));
        sb.append(String.format("ID Vendedor                     : %d%n", getIdVendedor()));
        sb.append(String.format("ID Cliente                      : %d%n", getIdCliente()));
        sb.append(String.format("Puntuacion                      : %d%n", getPuntuacion()));
        sb.append(String.format("Comentario                      : %s%n", getComentario()));
        sb.append(String.format("Reportes                        : %d%n", getReportes()));
        return sb.toString();
    }

    /**
     * Nos dice si el objeto recibido es una Critica igual al que manda llamar
     * el metodo.
     * @param objeto el objeto con el que la Critica se comparara.
     * @return <code>true</code> si el objeto recibido es una Critica con las
     *         mismas propiedades que el objeto que manda llamar al metodo,
     *         <code>false</code> en otro caso.
     */
    @Override 
    public boolean equals(Object objeto) {
        if (!(objeto instanceof Critica)) return false;
        Critica critica = (Critica) objeto;
        return getId() == critica.getId() &&
               getIdVendedor() == critica.getIdVendedor() &&
               getIdCliente() == critica.getIdCliente() &&
               getPuntuacion() == critica.getPuntuacion() &&
               getComentario().equals(critica.getComentario()) &&
                getReportes() == critica.getReportes();
    }

    /**
     * Regresa la Critica seriada en una linea de texto. La linea de
     * texto que este metodo regresa debe ser aceptada por el metodo {@link
     * Critica#deseria}.
     * @return la seriacion de la Critica en una linea de texto.
     */
    @Override 
    public String seria() {
        return String.format("%d\t%d\t%d\t%d\t%s\t%d%n",
               getId(), getIdVendedor(), getIdCliente(), getPuntuacion(), getComentario(), getReportes());
    }

    /**
     * Deseria una linea de texto en las propiedades de la Critica. La
     * seriacion producida por el metodo {@link Critica#seria} debe
     * ser aceptada por este metodo.
     * @param linea la linea a deseriar.
     * @throws ExcepcionLineaInvalida si la linea recibida es nula, vacia o no
     *         es una seriacion valida de una Critica.
     */
    @Override
    public void deseria(String linea) {
        if (linea == null) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        String cadena = linea.trim();
        if (cadena.isEmpty()) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        String[] campos = cadena.split("\t");
        if (campos.length != 6) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        setComentario(campos[4]);
        try {
            setId(Integer.parseInt(campos[0]));
            setIdVendedor(Integer.parseInt(campos[1]));
            setIdCliente(Integer.parseInt(campos[2]));
            setPuntuacion(Integer.parseInt(campos[3]));
            setReportes(Integer.parseInt(campos[5]));
        } catch (NumberFormatException nfe) {
            throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        }
    }

    /**
     * Actualiza los valores de la Critica con los de la Critica recibido.
     * @param critica la Critica con el cual actualizar los valores.
     * @throws IllegalArgumentException si la Critica es <code>null</code>.
     */
    @Override 
    public void actualiza(Critica critica) {
        if (critica == null) throw new IllegalArgumentException("Critica invalido.");
        setId(critica.getId());
        setIdVendedor(critica.getIdVendedor());
        setIdCliente(critica.getIdCliente());
        setPuntuacion(critica.getPuntuacion());
        setComentario(critica.getComentario());
        setReportes(critica.getReportes());
    }

    /**
     * Nos dice si la Critica casa el valor dado en el campo especificado.
     * @param campo el campo que hay que casar.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoCritica#ID} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual al ID de la Critica.</li>
     *           <li><code>campo</code> es {@link CampoCritica#ID_VENDEDOR} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual al ID_VENDEDOR de la Critica.</li>
     *           <li><code>campo</code> es {@link CampoCritica#ID_CLIENTE} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual al ID_CLIENTE de la Critica.</li>
     *           <li><code>campo</code> es {@link CampoCritica#PUNTUACION} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual al PUNTUACION de la Critica.</li>
     *           <li><code>campo</code> es {@link CampoCritica#COMENTARIO} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del Comentario de la Critica.</li>
     *          <li><code>campo</code> es {@link CampoCritica#REPORTE} y
     *             <code>valor</code> es instancia de {@link Integer} y su
     *            valor entero es menor o igual al REPORTE de la Critica.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override 
    public boolean casa(CampoCritica campo, Object valor) {
	    if (!(campo instanceof CampoCritica)) throw new IllegalArgumentException("Campo invalido.");
	    CampoCritica campoCritica = (CampoCritica) campo;
        switch (campoCritica) {
            case ID: return casaID(valor);
            case ID_CLIENTE: return casaIDCliente(valor);
            case ID_VENDEDOR: return casaIDVendedor(valor);
            case PUNTUACION: return casaPuntuacion(valor);
            case COMENTARIO: return casaComentario(valor);
            case REPORTE: return casaReporte(valor);
            default: return false;
        }
    }
    
    /**
     * Nos dice si la Critica casa el ID.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Integer} y su
     *         valor entero es menor o igual al ID de la Critica.</li>
     *         <code>false</code> en otro caso.
     */
    private boolean casaID(Object valor) {
	    if (!(valor instanceof Integer)) return false;
        Integer valorID = (Integer) valor;
        return valorID.intValue() <= getId();
    }
    
    /**
     * Nos dice si la Critica casa el ID_VENDEDOR.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Integer} y su
     *         valor entero es menor o igual al ID_VENDEDOR de la Critica.</li>
     *         <code>false</code> en otro caso.
     */
    private boolean casaIDVendedor(Object valor) {
	    if (!(valor instanceof Integer)) return false;
        Integer valorID = (Integer) valor;
        return valorID.intValue() <= getIdVendedor();
    }
    
    /**
     * Nos dice si la Critica casa el ID_CLIENTE.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Integer} y su
     *         valor entero es menor o igual al ID_CLIENTE de la Critica.</li>
     *         <code>false</code> en otro caso.
     */
    private boolean casaIDCliente(Object valor) {
	    if (!(valor instanceof Integer)) return false;
        Integer valorID = (Integer) valor;
        return valorID.intValue() <= getIdCliente();
    }
    
    /**
     * Nos dice si la Critica casa la PUNTUACION.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Integer} y su
     *         valor entero es menor o igual a la PUNTUACION de la Critica.</li>
     *         <code>false</code> en otro caso.
     */
    private boolean casaPuntuacion(Object valor) {
	    if (!(valor instanceof Integer)) return false;
        Integer valorID = (Integer) valor;
        return valorID.intValue() <= getPuntuacion();
    }
    
    /**
     * Nos dice si la Critica casa el COMENTARIO.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link String} y
     *         es una subcadena del COMENTARIO de la Critica.
     *         <code>false</code> en otro caso.
     */
    private boolean casaComentario(Object valor) {
	    if (!(valor instanceof String)) return false;
	    String cadena = (String) valor;
	    String valorContrasena = cadena.trim();
	    if (valorContrasena.isEmpty()) return false;
	    return getComentario().contains(valorContrasena);
    }

    /**
     * Nos dice si la Critica casa el REPORTE.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Integer} y su
     *         valor entero es menor o igual al REPORTE de la Critica.</li>
     *         <code>false</code> en otro caso.
     */
    private boolean casaReporte(Object valor) {
        if (!(valor instanceof Integer)) return false;
        Integer valorID = (Integer) valor;
        return valorID.intValue() <= getReportes();
    }

    /**
     * Reporta la critica.
     * @return la critica reportada.
     */
    public Critica reportar() {
        setReportes(getReportes() + 1);
        return this;
    }
}
