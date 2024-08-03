package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Interfaz que define a un usuario.</p>
 */
public interface InterfazUsuario {

    /**
     * Regresa el id del usuario.
     * @return el id del usuario.
     */
    public int getId();

    /**
     * Regresa el rol del usuario.
     * @return el rol del usuario.
     */
    public String getRol();

    /**
     * Regresa el nombre del usuario.
     * @return el nombre del usuario.
     */
    public String getNombreUsuario();

    /**
     * Regresa la contrasena del usuario.
     * @return la contrasena del usuario.
     */
    public String getContrasena();

    /**
     * Regresa el telefono del usuario.
     * @return el telefono del usuario.
     */
    public String getTelefono();

    /**
     * Regresa el numero de cuenta del usuario.
     * @return el numero de cuenta del usuario.
     */
    public String getNumeroCuenta();
}