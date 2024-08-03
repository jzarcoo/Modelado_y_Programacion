package mx.unam.ciencias.modelado.proyecto2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <p>Clase para representar usuarios de la tienda. Un usuario tiene ID, nombre de 
 * usuario, CONTRASENA y ROL. La clase implementa {@link Registro}, por lo que
 * puede seriarse en una linea de texto y deseriarse de una linea de
 * texto; ademas de determinar si sus campos casan valores arbitrarios y
 * actualizarse con los valores de otro usuario.</p>
 */
public class Usuario implements Registro<Usuario, CampoUsuario>, InterfazUsuario  {

    /* Mensaje por error al deseriar. */
    private static final String ERROR_DESERIA = "Linea invalida de usuario.";

    /* ID del Usuario. */
    private final IntegerProperty ID;
    /* Rol del usuario. */
    private final StringProperty ROL;
    /* Nombre de Usuario. */
    private final StringProperty NOMBRE_USUARIO;
    /* Contrasena del usuario. */
    private final StringProperty CONTRASENA;
    /* Telefono del usuario. */
    private final StringProperty TELEFONO;
    /* Numero de cuenta del usuario. */
    private final StringProperty NUMERO_CUENTA;

    /**
     * Define el estado inicial de un Usuario.
     * @param ID el ID del Usuario.
     * @param ROL el ROL del Usuario.
     * @param NOMBRE_USUARIO el nombre de Usuario.
     * @param CONTRASENA la CONTRASENA del Usuario.
     * @param TELEFONO el TELEFONO del Usuario.
     * @param NUMERO_CUENTA el numero de cuenta del Usuario.
     */
    public Usuario(int ID, 
                   String ROL,
                   String NOMBRE_USUARIO, 
                   String CONTRASENA,
                   String TELEFONO,
                   String NUMERO_CUENTA) {
        this.ID = new SimpleIntegerProperty(ID);
        this.ROL = new SimpleStringProperty(ROL);
        this.NOMBRE_USUARIO = new SimpleStringProperty(NOMBRE_USUARIO);
        this.CONTRASENA = new SimpleStringProperty(CONTRASENA);
        this.TELEFONO = new SimpleStringProperty(TELEFONO);
        this.NUMERO_CUENTA = new SimpleStringProperty(NUMERO_CUENTA); 
    }
    
    /**
     * Regresa el ID del Usuario.
     * @return el ID del Usuario.
     */
    @Override
    public int getId() {
        return ID.get();
    }

    /**
     * Define el ID del Usuario.
     * @param ID el nuevo ID del Usuario.
     */
    public void setId(int ID) {
        this.ID.set(ID);
    }

    /**
     * Regresa la propiedad del ID del Usuario.
     * @return la propiedad del ID del Usuario.
     */
    public IntegerProperty idProperty() {
        return ID;
    }

    /**
     * Regresa el ROL del Usuario.
     * @return el ROL del Usuario.
     */
    @Override
    public String getRol() {
        return ROL.get();
    }

    /**
     * Define el ROL del Usuario.
     * @param ROL el nuevo ROL del Usuario.
     */
    public void setRol(String ROL) {
        this.ROL.set(ROL);
    }

    /**
     * Regresa la propiedad del ROL del Usuario.
     * @return la propiedad del ROL del Usuario.
     */
    public StringProperty rolProperty() {
        return ROL;
    }

    /**
     * Regresa el nombre de Usuario.
     * @return el nombre de Usuario.
     */
    @Override
    public String getNombreUsuario() {
        return NOMBRE_USUARIO.get();
    }

    /**
     * Define el nombre de Usuario.
     * @param NOMBRE_USUARIO el nuevo nombre de Usuario.
     */
    public void setNombreUsuario(String NOMBRE_USUARIO) {
        this.NOMBRE_USUARIO.set(NOMBRE_USUARIO);
    }

    /**
     * Regresa la propiedad del nombre de Usuario.
     * @return la propiedad del nombre de Usuario.
     */
    public StringProperty nombreUsuarioProperty() {
        return NOMBRE_USUARIO;
    }

    /**
     * Regresa la CONTRASENA del Usuario.
     * @return la CONTRASENA del Usuario.
     */
    @Override
    public String getContrasena() {
        return CONTRASENA.get();
    }

    /**
     * Define la CONTRASENA del Usuario.
     * @param CONTRASENA la nueva CONTRASENA del Usuario.
     */
    public void setContrasena(String CONTRASENA) {
        this.CONTRASENA.set(CONTRASENA);
    }

    /**
     * Regresa la propiedad de CONTRASENA del Usuario.
     * @return la propiedad de CONTRASENA del Usuario.
     */
    public StringProperty contrasenaProperty() {
        return CONTRASENA;
    }

    /**
     * Regresa el TELEFONO del Usuario.
     * @return el TELEFONO del Usuario.
     */
    @Override
    public String getTelefono() {
        return TELEFONO.get();
    }

    /**
     * Define el TELEFONO del Usuario.
     * @param TELEFONO el nuevo TELEFONO del Usuario.
     */
    public void setTelefono(String TELEFONO) {
        this.TELEFONO.set(TELEFONO);
    }

    /**
     * Regresa la propiedad del TELEFONO del Usuario.
     * @return la propiedad del TELEFONO del Usuario.
     */
    public StringProperty telefonoProperty() {
        return TELEFONO;
    }

    /**
     * Regresa el numero de cuenta del Usuario.
     * @return el numero de cuenta del Usuario.
     */
    @Override
    public String getNumeroCuenta() {
        return NUMERO_CUENTA.get();
    }

    /**
     * Define el numero de cuenta del Usuario.
     * @param NUMERO_CUENTA el nuevo numero de cuenta del Usuario.
     */
    public void setNumeroCuenta(String NUMERO_CUENTA) {
        this.NUMERO_CUENTA.set(NUMERO_CUENTA);
    }

    /**
     * Regresa la propiedad del numero de cuenta del Usuario.
     * @return la propiedad del numero de cuenta del Usuario.
     */
    public StringProperty numeroCuentaProperty() {
        return NUMERO_CUENTA;
    }

    /**
     * Regresa una representacion en cadena del Usuario.
     * @return una representacion en cadena del Usuario.
     */
    @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID                      : %d%n", getId()));
        sb.append(String.format("Rol                     : %s%n", getRol()));
        sb.append(String.format("Nombre de Usuario       : %s%n", getNombreUsuario()));
        sb.append(String.format("Contrasena              : %s%n", getContrasena()));
        sb.append(String.format("Telefono                : %s%n", getTelefono()));
        sb.append(String.format("Numero de Cuenta        : %s%n", getNumeroCuenta()));
        return sb.toString();
    }

    /**
     * Nos dice si el objeto recibido es un Usuario igual al que manda llamar
     * el metodo.
     * @param objeto el objeto con el que el Usuario se comparara.
     * @return <code>true</code> si el objeto recibido es un Usuario con las
     *         mismas propiedades que el objeto que manda llamar al metodo,
     *         <code>false</code> en otro caso.
     */
    @Override 
    public boolean equals(Object objeto) {
        if (!(objeto instanceof Usuario)) return false;
        Usuario usuario = (Usuario) objeto;
        return getId() == usuario.getId() &&
               getRol().equals(usuario.getRol()) &&
               getNombreUsuario().equals(usuario.getNombreUsuario()) &&
               getContrasena().equals(usuario.getContrasena()) &&
               getTelefono().equals(usuario.getTelefono()) &&
                getNumeroCuenta().equals(usuario.getNumeroCuenta());
    }

    /**
     * Regresa el Usuario seriado en una linea de texto. La linea de
     * texto que este metodo regresa debe ser aceptada por el metodo {@link
     * Usuario#deseria}.
     * @return la seriacion del Usuario en una linea de texto.
     */
    @Override 
    public String seria() {
        return String.format("%d\t%s\t%s\t%s\t%s\t%s%n",
               getId(), getRol(), getNombreUsuario(), getContrasena(), getTelefono(), getNumeroCuenta());
    }

    /**
     * Deseria una linea de texto en las propiedades del Usuario. La
     * seriacion producida por el metodo {@link Usuario#seria} debe
     * ser aceptada por este metodo.
     * @param linea la linea a deseriar.
     * @throws ExcepcionLineaInvalida si la linea recibida es nula, vacia o no
     *         es una seriacion valida de un Usuario.
     */
    @Override
    public void deseria(String linea) {
        if (linea == null) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        String cadena = linea.trim();
        if (cadena.isEmpty()) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        String[] campos = cadena.split("\t");
        if (campos.length != 6) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        try {
            setId(Integer.parseInt(campos[0]));
        } catch (NumberFormatException nfe) {
            throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        }
        setRol(campos[1]);
        setNombreUsuario(campos[2]);
        setContrasena(campos[3]);
        setTelefono(campos[4]);
        setNumeroCuenta(campos[5]);
    }

    /**
     * Actualiza los valores del usuario con los del Usuario recibido.
     * @param usuario el Usuario con el cual actualizar los valores.
     * @throws IllegalArgumentException si el Usuario es <code>null</code>.
     */
    @Override 
    public void actualiza(Usuario usuario) {
        if (usuario == null) throw new IllegalArgumentException("Usuario invalido.");
        setId(usuario.getId());
        setRol(usuario.getRol());
        setNombreUsuario(usuario.getNombreUsuario());
        setContrasena(usuario.getContrasena());
        setTelefono(usuario.getTelefono());
        setNumeroCuenta(usuario.getNumeroCuenta());
    }

    /**
     * Nos dice si el Usuario casa el valor dado en el campo especificado.
     * @param campo el campo que hay que casar.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoUsuario#ID} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual al ID del Usuario.</li>
     *           <li><code>campo</code> es {@link CampoUsuario#ROL} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del ROL del Usuario.</li>
     *           <li><code>campo</code> es {@link CampoUsuario#NOMBRE_USUARIO} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre de Usuario.</li>
     *           <li><code>campo</code> es {@link CampoUsuario#CONTRASENA} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena de la CONTRASENA del Usuario.</li>
     *          <li><code>campo</code> es {@link CampoUsuario#TELEFONO} y
     *             <code>valor</code> es instancia de {@link String} y es una
     *             subcadena del TELEFONO del Usuario.</li>
     *         <li><code>campo</code> es {@link CampoUsuario#NUMERO_CUENTA} y
     *             <code>valor</code> es instancia de {@link String} y es una
     *             subcadena del numero de cuenta del Usuario.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override 
    public boolean casa(CampoUsuario campo, Object valor) {
	    if (!(campo instanceof CampoUsuario)) throw new IllegalArgumentException("Campo invalido.");
	    CampoUsuario campoUsuario = campo;
        switch (campoUsuario) {
            case ID: return casaID(valor);
            case ROL: return casaRol(valor);
            case NOMBRE_USUARIO: return casaNombreUsuario(valor);
            case CONTRASENA: return casaContrasena(valor);
            case TELEFONO: return casaTelefono(valor);
            case NUMERO_CUENTA: return casaNumeroCuenta(valor);
            default: return false;
        }
    }
    
    /**
     * Nos dice si el Usuario casa el ID.
     * @param valor el valor con el que debe casar el ID del Usuario.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Integer} y su
     *         valor entero es menor o igual al ID del Usuario.</li>
     *         <code>false</code> en otro caso.
     */
    private boolean casaID(Object valor) {
	    if (!(valor instanceof Integer)) return false;
        Integer valorID = (Integer) valor;
        return valorID.intValue() <= getId();
    }
    
    /**
     * Nos dice si el Usuario casa el ROL del Usuario.
     * @param valor el valor con el que debe casar el ROL del Usuario.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link String} y
     *         es una subcadena del ROL del Usuario.
     *         <code>false</code> en otro caso.
     */
    private boolean casaRol(Object valor) {
	    if (!(valor instanceof String)) return false;
	    String cadena = (String) valor;
	    String valorRol = cadena.trim();
	    if (valorRol.isEmpty()) return false;
	    return getRol().contains(valorRol);
    }
    
    /**
     * Nos dice si el Usuario casa el nombre de Usuario.
     * @param valor el valor con el que debe casar el nombre de Usuario.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link String} y
     *         es una subcadena del nombre de Usuario.
     *         <code>false</code> en otro caso.
     */
    private boolean casaNombreUsuario(Object valor) {
	    if (!(valor instanceof String)) return false;
	    String cadena = (String) valor;
	    String valorNombreUsuario = cadena.trim();
	    if (valorNombreUsuario.isEmpty()) return false;
	    return getNombreUsuario().contains(valorNombreUsuario);
    }
    
    /**
     * Nos dice si el Usuario casa la CONTRASENA del Usuario.
     * @param valor el valor con el que debe casar la CONTRASENA del Usuario.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link String} y
     *         es una subcadena de la CONTRASENA del Usuario.
     *         <code>false</code> en otro caso.
     */
    private boolean casaContrasena(Object valor) {
	    if (!(valor instanceof String)) return false;
	    String cadena = (String) valor;
	    String valorContrasena = cadena.trim();
	    if (valorContrasena.isEmpty()) return false;
	    return getContrasena().contains(valorContrasena);
    }

    /**
     * Nos dice si el Usuario casa el TELEFONO del Usuario.
     * @param valor el valor con el que debe casar el TELEFONO del Usuario.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link String} y
     *         es una subcadena del TELEFONO del Usuario.
     *         <code>false</code> en otro caso.
     */
    private boolean casaTelefono(Object valor) {
        if (!(valor instanceof String)) return false;
        String cadena = (String) valor;
        String valorTelefono = cadena.trim();
        if (valorTelefono.isEmpty()) return false;
        return getTelefono().contains(valorTelefono);
    }

    /**
     * Nos dice si el Usuario casa el numero de cuenta del Usuario.
     * @param valor el valor con el que debe casar el numero de cuenta del Usuario.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link String} y
     *        es una subcadena del numero de cuenta del Usuario.
     *       <code>false</code> en otro caso.
     */
    private boolean casaNumeroCuenta(Object valor) {
        if (!(valor instanceof String)) return false;
        String cadena = (String) valor;
        String valorNumeroCuenta = cadena.trim();
        if (valorNumeroCuenta.isEmpty()) return false;
        return getNumeroCuenta().contains(valorNumeroCuenta);
    }
}
