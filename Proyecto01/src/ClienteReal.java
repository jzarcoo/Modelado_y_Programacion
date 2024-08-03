package src;

/**
 * <p>Clase para representar clientes de la tienda. Un cliente contiene un nombre de usuario, 
 * contrasena, nombre, telefono, direccion, cuenta bancaria y pais de origen.</p>
 * 
 * <p>Esta clase implementa la interfaz Cliente, por lo que define los metodos:</p>
 * <ul>
 * <li>{@link #isSaldoSuficiente(double)}</li>
 * <li>{@link #pagar(double)}</li>
 * <li>{@link #getContrasena()}</li>
 * <li>{@link #getCuenta()}</li>
 * <li>{@link #getPaisDeOrigen()}</li>
 * <li>{@link #getNombreUsuario()}</li>
 * </ul>
 */
public class ClienteReal implements Cliente {

    /* Nombre de usuario del Cliente. */
    private String nombreUsuario;
    /* Contrasena del Cliente. */
    private String contrasena;
    /* Nombre del Cliente. */
    private String nombre;
    /* Telefono del Cliente. */
    private String telefono;
    /* Direccion del Cliente. */
    private String direccion;
    /* Cuenta del Cliente. */
    private CuentaBancaria cuenta;
    /* Pais de Origen del Cliente. */
    private PaisDeOrigen paisDeOrigen;
    /*Identificador unico */
    private int id;
    /**
     * Define el estado inicial del Cliente.
     * @param nombreUsuario nombre de usuario del Cliente.
     * @param contrasena contrasena del Cliente.
     * @param nombre nombre del Cliene.
     * @param telefono telefono del Cliente.
     * @param direccion direccion del Cliente.
     * @param dinero dinero del Cliente.
     * @param numeroCuenta numero de cuenta del Cliente.
     * @param paisDeOrigen pais de origen del Cliente.
     */
    public ClienteReal(String nombreUsuario, 
                       String contrasena, 
                       String nombre, 
                       String telefono, 
                       String direccion, 
                       int dinero,
                       String numeroCuenta, 
                       PaisDeOrigen paisDeOrigen,
                       int id) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cuenta = new CuentaBancaria(dinero, numeroCuenta);
        this.paisDeOrigen = paisDeOrigen;
        this.id = id;
    }

    /**
     * Define el estado inicial del Cliente.
     * @param nombreUsuario nombre de usuario del Cliente.
     * @param contrasena contrasena del Cliente.
     * @param nombre nombre del Cliene.
     * @param telefono telefono del Cliente.
     * @param direccion direccion del Cliente.
     * @param cuenta cuenta del Cliente.
     * @param paisDeOrigen pais de origen del Cliente.
     */
    public ClienteReal(String nombreUsuario,
                       String contrasena,
                       String nombre,
                       String telefono,
                       String direccion,
                       CuentaBancaria cuenta,
                       PaisDeOrigen paisDeOrigen, 
                       int id) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cuenta = cuenta;
        this.paisDeOrigen = paisDeOrigen;
        this.id = id;
    }

    /**
     * Regresa el nombre de usuario del Cliente.
     * @return nombre de usuario del Cliente.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Define el nombre de usuario del Cliente.
     * @param nombreUsuario nombre de usuario del Cliente.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Regresa la contrasena del Cliente.
     * @return contrasena del Cliente.
     */
    @Override
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Define la contrasena del Cliente.
     * @param contrasena contrasena del Cliente.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Regresa el nombre del Cliente.
     * @return nombre del Cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre del Cliente.
     * @param nombre nombre del Cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa el telefono del Cliente.
     * @return telefono del Cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Define el telefono del Cliente.
     * @param telefono telefono del Cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }   

    /**
     * Regresa la direccion del Cliente.
     * @return direccion del Cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Define la direccion del Cliente.
     * @param direccion direccion del Cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }   

    /**
     * Determina si la cuenta ingresada por el Cliente es correcta.
     * @param cuenta cuenta bancaria del Cliente.
     * @return <code>true</code> si la cuenta es correcta, <code>false</code> de lo contrario.
     */
    @Override
    public boolean verificarCuentaBancaria(String cuenta) {
        return this.cuenta.getNumeroCuenta().equals(cuenta);
    }

    /**
     * Define la cuenta bancaria del Cliente.
     * @param cuenta cuenta bancaria del Cliente.
     */
    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Regresa el pais de origen del Cliente.
     * @return pais del origen del Cliente.
     */
    @Override
    public PaisDeOrigen getPaisDeOrigen() {
        return paisDeOrigen;
    }

    /**
     * Define el pais de origen del Cliente.
     * @param paisDeOrigen pais de origen del Cliente.
     */
    public void setPaisDeOrigen(PaisDeOrigen paisDeOrigen) {
        this.paisDeOrigen = paisDeOrigen;
    }

    /**
     * Verifica si el saldo de la cuenta es suficiente para realizar un pago del monto especificado.
     * @param total el monto total del pago a verificar.
     * @return <code>true</code> si el saldo es suficiente para realizar el pago, 
     *         <code>false</code> de lo contrario.
     */
    @Override
    public boolean isSaldoSuficiente(double total) {
        return cuenta.isSaldoSuficiente(total);
    }

    /**
     * Define el pago de un monto especificado desde la cuenta.
     * @param total el monto total del pago a realizar.
     */
    @Override
    public void pagar(double total) {
        cuenta.pagar(total);
    }
    
    /**
     * Nos dice si el objeto recibido es igual al que manda llamar 
     * el metodo.
     * @param obj el objeto con el que se comparara.
     * @return <code>true</code> si el objeto recibido es un cliente con las 
     *          mismas propiedades que el que manda llamar el metodo, <code>false</code>
     *          en otro caso.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Cliente))
            return false;
        ClienteReal cliente = (ClienteReal) obj;
        return nombreUsuario.equals(cliente.nombreUsuario) &&
                contrasena.equals(cliente.contrasena) &&
                nombre.equals(cliente.nombre) &&
                telefono == cliente.telefono &&
                direccion.equals(cliente.direccion) &&
                cuenta.equals(cliente.cuenta) &&
                paisDeOrigen.equals(cliente.paisDeOrigen);
    }

    /**
     * Regresa una representacion en cadena del Cliente.
     * @return una representacion en cadena del Cliente.
     */
    @Override
    public String toString() {
        return "Cliente{" + "nombreUsuario=" + nombreUsuario + 
                ", contrasena=" + contrasena + 
                ", nombre=" + nombre + 
                ", telefono=" + telefono + 
                ", direccion=" + direccion + 
                ", cuenta=" + cuenta + 
                ", paisDeOrigen=" + paisDeOrigen + 
                ", id=" + id +
                '}';
    }

    /*
     * Regresa el ID  del Cliente.
     * @return ID del cliente
     */
    public int getId(){
        return id;
    }
}