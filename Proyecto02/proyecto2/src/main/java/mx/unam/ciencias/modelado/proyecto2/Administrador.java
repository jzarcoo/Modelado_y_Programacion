package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Clase para representar a un administrador.</p>
 */
public class Administrador extends Usuario implements InterfazAdministrador {

    /**
     * Define el estado inicial de un administrador.
     * @param id el id del administrador.
     * @param nombre el nombre del administrador.
     * @param contrasena la contrasena del administrador.
     * @param telefono el telefono del administrador.
     * @param numeroCuenta el numero de cuenta del administrador.
     */
    public Administrador(int id, 
                        String nombre, 
                        String contrasena,
                        String telefono,
                        String numeroCuenta) {
        super(id, "administrador", nombre, contrasena, telefono, numeroCuenta);
    }

}