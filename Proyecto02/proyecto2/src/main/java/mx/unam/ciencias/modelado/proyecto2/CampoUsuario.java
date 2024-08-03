package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Enumeracion para los campos de un {@link Usuario}.</p>
 */
public enum CampoUsuario {

    /** El ID del Usuario. */
    ID("ID"),
    /** El rol del Usuario. */
    ROL("Rol"),
    /** El nombre de Usuario. */
    NOMBRE_USUARIO("Nombre de Usuario"),
    /** La contrasena del Usuario. */
    CONTRASENA("Contrasena"),
    /** El telefono del Usuario. **/
    TELEFONO("Telefono"),
    /** El numero de cuenta del Usuario. */
    NUMERO_CUENTA("Numero de Cuenta");

    /* Nombre del CampoUsuario. */
    private String nombre;

    /**
     * Crea un CampoUsuario con el nombre dado.
     * @param nombre el nombre del CampoUsuario.
     */
    private CampoUsuario(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Descifra un CampoUsuario a partir de su nombre.
     * @param nombre el nombre del CampoUsuario.
     * @return el CampoUsuario correspondiente al nombre o,
     *         <code>null</code> si no lo encuentra.
     */
    public static CampoUsuario getCampoUsuario(String nombre) {
        for (CampoUsuario campo : CampoUsuario.values())
            if (campo.toString().equals(nombre))
                return campo;
        return null;
    }

    /**
     * Regresa una representacion en cadena del CampoUsuario.
     * @return una representacion en cadena del CampoUsuario.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
