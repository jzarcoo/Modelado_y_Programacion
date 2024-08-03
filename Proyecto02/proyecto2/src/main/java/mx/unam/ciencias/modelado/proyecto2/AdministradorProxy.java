package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Clase para representar a un administrador.</p>
 */
public class AdministradorProxy implements InterfazAdministrador {

    /* El administrador. */
    private Administrador administrador;

    /**
     * Constructor que define al administrador real.
     * @param administrador el administrador.
     */
    public AdministradorProxy(Administrador administrador) {
        this.administrador = administrador;
    }

    /**
     * Regresa el id del administrador.
     * @return el id del administrador.
     */
    @Override
    public int getId() {
        return administrador.getId();
    }

    /**
     * Regresa el rol del administrador.
     * @return el rol del administrador, "administrador";
     */
    @Override
    public String getRol() {
        return administrador.getRol();
    }

    /**
     * Regresa el nombre del administrador.
     * @return el nombre del administrador.
     */
    @Override
    public String getNombreUsuario() {
        return administrador.getNombreUsuario();
    }

    /**
     * Regresa la contrasena del administrador.
     * @return la contrasena del administrador.
     */
    @Override
    public String getContrasena() {
        return administrador.getContrasena();
    }

    /**
     * Regresa el telefono del administrador.
     * @return el telefono del administrador.
     */
    @Override
    public String getTelefono() {
        return administrador.getTelefono();
    }

    /**
     * Regresa el numero de cuenta del administrador.
     * @return el numero de cuenta del administrador.
     */
    @Override
    public String getNumeroCuenta() {
        return administrador.getNumeroCuenta();
    }
}