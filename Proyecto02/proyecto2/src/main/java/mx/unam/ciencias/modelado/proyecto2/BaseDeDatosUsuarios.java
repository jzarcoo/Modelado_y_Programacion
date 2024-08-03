package mx.unam.ciencias.modelado.proyecto2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Clase para bases de datos de usuarios.</p>
 */
public class BaseDeDatosUsuarios extends BaseDeDatos<Usuario, CampoUsuario> {

    /* Los vendedores de la base de datos de usuarios. */
    private HashMap<Integer, Vendedor> vendedores;
    /* Los clientes de la base de datos de usuarios. */
    private HashMap<Integer, Cliente> clientes;
    /* Los administradores de la base de datos de usuarios. */
    private HashMap<Integer, Administrador> administradores;

    /**
     * Crea una base de datos de usuarios.
     */
    public BaseDeDatosUsuarios() {
        super();
        vendedores = new HashMap<>();
        clientes = new HashMap<>();
        administradores = new HashMap<>();
    }

    /**
     * Crea un usuario en blanco.
     * @return un usuario en blanco.
     */
    @Override 
    public Usuario creaRegistro() {
        return new Usuario(0, null, null, null, null, null);
    }

    /**
     * Obtiene un registro de la base de datos de usuarios.
     * @param usuario el nombre de usuario.
     * @param contrasena la contrasena.
     * @return el registro de la base de datos de usuarios.
     */
    public Usuario getRegistro(String usuario, String contrasena) {
        for(Usuario u : registros){
            if(u.getNombreUsuario().equals(usuario) && u.getContrasena().equals(contrasena)){
                return u;
            }
        }
        return null;
    }

    /**
     * Agrega vendedores y clientes en sus respectivas tablas
     * @param productos la lista de productos
     * @param criticas la lista de criticas
     * @param carritos la lista de carritos
     */
    public void llenaBase(List<Producto> productos, List<Critica> criticas, List<Carrito> carritos){
        for(Usuario u : registros) {
            if(u.getRol().equals("vendedor")){
                Vendedor v = new Vendedor(u.getId(), u.getNombreUsuario(), u.getContrasena(), u.getTelefono(), u.getNumeroCuenta());
                vendedores.put(v.getId(), v);
                for(Producto p : productos)
                    if(p.getIdVendedor() == v.getId())
                        v.agregaProducto(p);
                    
                
                for(Critica c : criticas){
                    if(c.getIdVendedor() == v.getId()){
                        v.agregaCritica(c);
                    }
                }          
            } else if(u.getRol().equals("cliente")){
                clientes.put(u.getId(), new Cliente(u.getId(), u.getNombreUsuario(), u.getContrasena(), u.getTelefono(), u.getNumeroCuenta()));
                for(Carrito c : carritos){
                    if(c.getIdCliente() == u.getId()){
                        clientes.get(u.getId()).agregaCarrito(c);
                    }
                }

            } else if(u.getRol().equals("administrador")){
                Administrador administrador = new Administrador(u.getId(), u.getNombreUsuario(), u.getContrasena(), u.getTelefono(), u.getNumeroCuenta());
                administradores.put(administrador.getId(), administrador);
            }
        }
    
    }

    /**
     * Devuelve un vendendor
     * @param id el id del vendedor
     */
    public Vendedor getVendedor(int id){
        return vendedores.get(id);
    }

    /**
     * Devuelve un cliente
     * @param id el id del cliente
     */
    public Cliente getCliente(Integer id){
        return clientes.get(id);
    }

    /**
     * Devuelve un administrador
     * @param id el id del administrador
     */
    public Administrador getAdministrador(int id){
        return administradores.get(id);
    }

    /**
     * Devuelve una lista con los vendedores
     */
    public List<InterfazVendedor> getVendedores(){
        LinkedList<InterfazVendedor> v = new LinkedList<>();

        for(Vendedor vendedor : vendedores.values()){
            v.add(vendedor);
        }
        return v;
    }  

    /**
     * Limpia las criticas de los vendedores
     */
    public void limpiarCriticas(){
        for(Vendedor v : vendedores.values()){
            v.limpiarCriticas();
        }
    }

    /**
     * Agrega una critica a un vendedor
     * @param critica la critica a agregar
     */
    public void llenaCriticas(List<Critica> criticas){
        for(Critica c : criticas){
            Vendedor v = vendedores.get(c.getIdVendedor());
            v.agregaCritica(c);
        }
    }

    /**
     * Agrega un carrito a un cliente
     * @param carritos la lista de carritos
     */
    public void llenaCarritos(List<Carrito> carritos){
        for(Carrito c : carritos){
            Cliente cliente = clientes.get(c.getIdCliente());
            cliente.agregaCarrito(c);
        }
    }

    /**
     * Limpia los carritos de los clientes
     */
    public void limpiarCarritos(){
        for(Cliente c : clientes.values()){
            c.limpiaCarritos();
        }
    }

    /**
     * Limpia los productos de los vendedores
     */
    public void limpiaProductos(){
        for(Vendedor v : vendedores.values()){
            v.limpiaProductos();
        }
    }

    /**
     * Llena los productos de los vendedores
     * @param productos la lista de productos
     */
    public void llenaProductos(List<Producto> productos){
        for(Producto p : productos){
            Vendedor v = vendedores.get(p.getIdVendedor());
            v.agregaProducto(p);
        }
    }

}
