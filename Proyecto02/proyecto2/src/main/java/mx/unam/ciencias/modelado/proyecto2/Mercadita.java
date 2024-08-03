package mx.unam.ciencias.modelado.proyecto2;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Platform;
import mx.unam.ciencias.modelado.proyecto2.api.CargadorClima;
import mx.unam.ciencias.modelado.proyecto2.api.ControladorClimas;
import mx.unam.ciencias.modelado.proyecto2.Mercadita;
import mx.unam.ciencias.modelado.proyecto2.red.Conexion;
import mx.unam.ciencias.modelado.proyecto2.red.Mensaje;

/**
 * <p>Clase para representar al servidor de la mercadita.</p>
 */
public class Mercadita implements InterfazMercadita {

    /* La base de datos de criticas. */
    private BaseDeDatosCriticas bddCriticas;
    /* La base de datos de productos. */
    private BaseDeDatosProductos bddProductos;
    /* La base de datos de usuarios. */
    private BaseDeDatosUsuarios bddUsuarios;
    /*La base de carritos */
    private BaseDeDatosCarritos bddCarritos;
    /* La conexion del cliente. */
    private Conexion conexion;
    /* Si hay o no conexion. */
    private boolean conectado;

    /* Banco  */
    private Banco banco;

    /** 
     * Define una nueva mercadita con copias de las bases de datos y hace la conexion con el servidor
     */
    public Mercadita() {
        setConectado(false);
        bddCriticas = new BaseDeDatosCriticas();
        bddProductos = new BaseDeDatosProductos();
        bddUsuarios = new BaseDeDatosUsuarios();
        bddCarritos = new BaseDeDatosCarritos();
        conectar();
        estableceClimas();
        banco = new Banco();
    }

    /* Cambia la interfaz grafica dependiendo si estamos o no conectados. */
    private void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    /**
     * Conecta el cliente con el servidor.
     */
    private void conectar() {
        String direccion = "localhost";
        int puerto = 1234;
        try {
            Socket enchufe = new Socket(direccion, puerto);
            conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios,bddCarritos, enchufe);
            new Thread(() -> conexion.recibeMensajes()).start();
            conexion.agregaEscucha((c, m) -> mensajeRecibido(c, m));
            conexion.enviaMensaje(Mensaje.BASE_DE_DATOS);
        } catch (IOException ioe) {
            conexion = null;
            String mensaje =
                String.format("Ocurrio un error al tratar de " +
                                "conectarnos a %s:%d.\n", direccion, puerto);
            dialogoError("Error al establecer conexion", mensaje);
            return;
        }
        setConectado(true);
    }

    /**
     * Recibe los mensajes de la conexion.
     * @param conexion la conexion.
     * @param mensaje el mensaje.
     */
    private void mensajeRecibido(Conexion conexion, Mensaje mensaje ) {
        switch (mensaje) {
        case BASE_DE_DATOS:
            baseDeDatos(conexion);
            break;
        case REGISTRO_AGREGADO_CARRITO:
            registroAlteradoCarrito(conexion, mensaje);
            break;
        case REGISTRO_ELIMINADO_CARRITO:
            registroAlteradoCarrito(conexion, mensaje);
            break;
        case REGISTRO_MODIFICADO_CARRITO:
            registroModificadoCarrito(conexion);
            break;
        case REGISTRO_AGREGADO_PRODUCTO:
            registroAlteradoProducto(conexion, mensaje);
            break;
        case REGISTRO_ELIMINADO_PRODUCTO:
            registroAlteradoProducto(conexion, mensaje);
            break;
        case REGISTRO_MODIFICADO_PRODUCTO:
            registroModificadoProducto(conexion);
            break;
        case REGISTRO_AGREGADO_CRITICA:
            registroAlteradoCritica(conexion, mensaje);
            break;
        case REGISTRO_ELIMINADO_CRITICA:
            registroAlteradoCritica(conexion, mensaje);
            break;
        case REGISTRO_MODIFICADO_CRITICA:
            registroModificadoCritica(conexion);
            break;
        case DESCONECTAR:
            Platform.runLater(() -> desconectar());
            break;
        case DETENER_SERVICIO: // Se ignora.
            break;
        case ECO: // Se ignora.
            break;
        case INVALIDO:
            Platform.runLater(() -> dialogoError("Error con el servidor",
                                                "Mensaje invalido recibido. " + mensaje+
                                                "Se finalizara la conexion."));
            break;
        }
    }

    /**
     * Maneja los mensajes REGISTRO_AGREGADO_PRODUCTO y REGISTRO_ELIMINADO_PRODUCTO.
     * @param conexion la conexion.
     * @param mensaje el mensaje.
     */
    private void registroAlteradoProducto(Conexion conexion, Mensaje mensaje) {
        Producto producto;
        try {
            producto = (Producto) conexion.recibeRegistro(TiposBaseDeDatos.PRODUCTOS);
        } catch (IOException ioe) {
            String m = "No se pudo recibir un registro. " +
                        "Se finalizara la conexion.";
            Platform.runLater(() -> dialogoError("Error con el servidor", m));
            return;
        }
        if (mensaje == Mensaje.REGISTRO_AGREGADO_PRODUCTO)
            bddProductos.agregaRegistro(producto);
        else
            bddProductos.eliminaRegistro(producto);
    }

    /**
     * Maneja los mensajes REGISTRO_AGREGADO_CRITICA y REGISTRO_ELIMINADO_CRITICA.
     * @param conexion la conexion.
     * @param mensaje el mensaje.
     */
    private void registroAlteradoCritica(Conexion conexion, Mensaje mensaje) {
        Critica critica;
        try {
            critica = (Critica) conexion.recibeRegistro(TiposBaseDeDatos.CRITICAS);
        } catch (IOException ioe) {
            String m = "No se pudo recibir un registro. " +
                        "Se finalizara la conexion.";
            Platform.runLater(() -> dialogoError("Error con el servidor", m));
            return;
        }
        if (mensaje == Mensaje.REGISTRO_AGREGADO_CRITICA)
            bddCriticas.agregaRegistro(critica);
        else
            bddCriticas.eliminaRegistro(critica);
        bddUsuarios.limpiarCriticas();
        bddUsuarios.llenaCriticas(bddCriticas.getRegistros());
    }

    /**
     * Maneja los mensajes REGISTRO_AGREGADO_CARRITO y REGISTRO_ELIMINADO_CARRITO
     * @param conexion la conexion.
     * @param mensaje el mensaje.
     */
    private void registroAlteradoCarrito(Conexion conexion, Mensaje mensaje) {
        Carrito carrito;
        try {
            carrito = (Carrito) conexion.recibeRegistro(TiposBaseDeDatos.CARRITOS);
        } catch (IOException ioe) {
            String m = "No se pudo recibir un registro. " +
                        "Se finalizara la conexion.";
            Platform.runLater(() -> dialogoError("Error con el servidor", m));
            return;
        }
        if (mensaje == Mensaje.REGISTRO_AGREGADO_CARRITO)
            bddCarritos.agregaRegistro(carrito);
        else
            bddCarritos.eliminaRegistro(carrito);

        bddUsuarios.limpiarCarritos();
        bddUsuarios.llenaCarritos(bddCarritos.getRegistros());
    }

    /** 
     * Maneja el mensaje REGISTRO_MODIFICADO_PRODUCTO.
     * @param conexion la conexion.
     */
    private void registroModificadoProducto(Conexion conexion) {
        Producto producto1;
        Producto producto2;
        try {
            producto1 = (Producto) conexion.recibeRegistro(TiposBaseDeDatos.PRODUCTOS);
            producto2 = (Producto) conexion.recibeRegistro(TiposBaseDeDatos.PRODUCTOS);
        } catch (IOException ioe) {
            String m = "No se pudieron recibir registros." +
                        "Se finalizara la conexion.";
            Platform.runLater(() -> dialogoError("Error con el servidor", m));
            return;
        }
        bddProductos.modificaRegistro(producto1, producto2);
        bddUsuarios.limpiaProductos();
        bddUsuarios.llenaProductos(bddProductos.getRegistros());
    }

     /** 
     * Maneja el mensaje REGISTRO_MODIFICADO_PRODUCTO.
     * @param conexion la conexion.
     */
    private void registroModificadoCarrito(Conexion conexion) {
        Carrito carrito1;
        Carrito carrito2;
        try {
            carrito1 = (Carrito) conexion.recibeRegistro(TiposBaseDeDatos.CARRITOS);
            carrito2 = (Carrito) conexion.recibeRegistro(TiposBaseDeDatos.CARRITOS);
        } catch (IOException ioe) {
            String m = "No se pudieron recibir registros." +
                        "Se finalizara la conexion.";
            Platform.runLater(() -> dialogoError("Error con el servidor", m));
            return;
        }
        bddCarritos.modificaRegistro(carrito1, carrito2);

        bddUsuarios.limpiarCarritos();
        bddUsuarios.llenaCarritos(bddCarritos.getRegistros());
    }

    /** 
     * Maneja el mensaje REGISTRO_MODIFICADO_CRITICA.
     * @param conexion la conexion.
     */
    private void registroModificadoCritica(Conexion conexion) {
        Critica critica1;
        Critica critica2;
        try {
            critica1 = (Critica) conexion.recibeRegistro(TiposBaseDeDatos.CRITICAS);
            critica2 = (Critica) conexion.recibeRegistro(TiposBaseDeDatos.CRITICAS);
        } catch (IOException ioe) {
            String m = "No se pudieron recibir registros." +
                        "Se finalizara la conexion.";
            Platform.runLater(() -> dialogoError("Error con el servidor", m));
            return;
        }
        bddCriticas.modificaRegistro(critica1, critica2);
    }

    /** 
     * Maneja el mensaje BASE_DE_DATOS. 
     * @param conexion la conexion.
     */
    private void baseDeDatos(Conexion conexion) {
        try {
            conexion.recibeBaseDeDatos(TiposBaseDeDatos.CRITICAS);
            conexion.recibeBaseDeDatos(TiposBaseDeDatos.PRODUCTOS);
            conexion.recibeBaseDeDatos(TiposBaseDeDatos.CARRITOS);
            conexion.recibeBaseDeDatos(TiposBaseDeDatos.USUARIOS);
        } catch (IOException ioe) {
            String m = "No se pudo recibir la base de datos. " +
                "Se finalizara la conexion.";
            Platform.runLater(() -> dialogoError("Error con el servidor", m));
        }
    }

    /**
     * Muestra un dialogo de error.
     * @param titulo el titulo del dialogo.
     * @param mensaje el mensaje del dialogo.
     */
    private void dialogoError(String titulo, String mensaje) {
        if (conectado)
            desconectar();
        System.err.println(titulo);
        System.err.println(mensaje);
    }

    /** 
     * Desconecta el cliente del servidor. 
     */
    @Override
    public void desconectar() {
        if (!conectado) return;
        setConectado(false);
        conexion.desconecta();
        conexion = null;
        bddCriticas.limpia();
        bddProductos.limpia();
        bddUsuarios.limpia();
        bddCarritos.limpia();
    }

    /**
     * Devuelve un usuario, si existe
     * @param usuario el nombre de usuario 
     * @param contrasena la contrasena
     * @return el usuario, si existe, null en otro caso
     */
    @Override
    public InterfazUsuario getUsuario(String usuario, String contrasena){
        return bddUsuarios.getRegistro(usuario, contrasena);
    }

    /**
     * Comprueba si un usuario y contrasena son validos. 
     * @param usuario el nombre de usuario
     * @param contrasena la contrasena
     * @return true si el usuario y contrasena son validos, false en otro caso
     */
    @Override
    public boolean comprobarUsuario(String usuario, String contrasena) {
        Usuario u = (Usuario) getUsuario(usuario, contrasena);
        return u != null;
    }

    /**
     * Devuelve un vendedor
     * @param id el id del vendedor
     * @return el vendedor
     */
    @Override
    public InterfazVendedor getVendedor(int id){
        return bddUsuarios.getVendedor(id);
    }

    /**
     * Devuelve un cliente
     * @param id el id del cliente
     * @return el cliente
     */
    @Override
    public InterfazCliente getCliente(int id){
        return bddUsuarios.getCliente(id);
    }

    /**
     * Devuelve un administrador.
     * @param id el id del administrador.
     * @return el administrador.
     */
    @Override
    public InterfazAdministrador getAdministrador(int id){
        return bddUsuarios.getAdministrador(id);
    }

    /**
     * Devuelve los vendedores
     * @return una lista con los vendedores
     */
    @Override
    public List<InterfazVendedor> getVendedores(){
        return bddUsuarios.getVendedores();
    }

    /**
     * Se encarga de establecer el clima en la aplicacion
     * Actualiza el clima cada 4 horas despues de la primera actualizacion
     */
    public void estableceClimas(){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::actualizarClima, 0, 4, TimeUnit.HOURS);
    }

    /**
     * Actualiza el clima
     */
    public void actualizarClima(){
        CargadorClima.cargaClima();
    }

    /**
     * Devuelve la temperatura maxima
     * @return la temperatura maxima
     */
    @Override
    public String getTemperaturaMaxima(){
        return ControladorClimas.getInstance().mostrarTemperaturaMaxima("Coyoacán");
    }

    /**
     * Devuelve la temperatura minima
     * @return la temperatura minima
     */
    @Override
    public String getTemperaturaMinima(){
        return ControladorClimas.getInstance().mostrarTemperaturaMinima("Coyoacán");
    }

    /**
     * Devuelve la probabilidad de precipitacion
     * @return la probabilidad de precipitacion
     */
    @Override
    public String getProbabilidadPrecipitacion(){
        return ControladorClimas.getInstance().mostrarProbabilidadLluvia("Coyoacán");
    }

    /**
     * Devuelve la velocidad del viento
     * @return la velocidad del viento
     */
    @Override
    public String getCielo(){
        return ControladorClimas.getInstance().mostrarDescripcionCielo("Coyoacán");
    }

    /***
     * Agrega un producto a la base de datos de productos de la mercadita.
     * @param producto el producto a agregar.
     */
    @Override
    public void agregaProducto(Producto producto) {
        bddProductos.agregaRegistro(producto);
    }

    /**
     * Elimina un producto de la base de datos de productos de la mercadita.
     * @param producto el producto a eliminar.
     */
    @Override
    public void eliminaProducto(Producto producto) {
        bddProductos.eliminaRegistro(producto);
    }

    /**
     * Modifica el primer producto en la base de datos de productos de la mercadita
     * para que sea identico al segundo.
     * @param producto1 el producto a modificar.
     * @param producto2 el producto con los nuevos valores.
     */
    @Override
    public void modificaProducto(Producto producto1, Producto producto2){
        bddProductos.modificaRegistro(producto1, producto2);
    }

    /**
     * Busca productos en la base de datos de productos de la mercadita con respecto al 
     * id del vendedor dado.
     * @param campo el campo por el que se va a buscar.
     * @param valor el valor que se va a buscar.
     * @param idVendedor el id del vendedor.
     * @return una lista con los productos que cumplen con el criterio de busqueda.
     */
    @Override
    public LinkedList<Producto> buscaProductos(CampoProducto campo, Object valor, int idVendedor) {
        LinkedList<Producto> resultados = bddProductos.buscaRegistros(campo, valor);
        LinkedList<Producto> copia = new LinkedList<>();
        for (Producto p : resultados)
            if (p.getIdVendedor() == idVendedor)
                copia.add(p);
        return copia;
    }

    /**
     * Regresa la conexion.
     * @return la conexion.
     */
    @Override
    public Conexion getConexion(){
        return conexion;
    }

    /**
     * Regresa un pago con tarjeta
     * @return un pago con tarjeta
     */
    @Override
    public PagoConTarjeta getPagoConTarjeta(){
        return new PagoConTarjeta(banco);
    }

    /**
     * Regresa un pago con efectivo
     * @return un pago con efectivo
     */
    @Override
    public PagoEnEfectivo getPagoConEfectivo(){
        return new PagoEnEfectivo();
    }

    /**
     * Regresa un pago con transferencia
     * @return un pago con transferencia
     */
    @Override
    public PagoTransferencia getPagoConTransferencia(){
        return new PagoTransferencia();
    }

    /*
     * Agrega una critica a la base de datos de criticas de la mercadita.
     * @param critica la critica a agregar.
     */
    @Override
    public void agregaCritica(Critica critica){
        bddCriticas.agregaRegistro(critica);
    }

    /**
     * Modifia la critica en la base de datos de criticas de la mercadita.
     * @param critica1 la critica a modificar.
     * @param critica2 la critica con los nuevos valores.
     */
    @Override
    public void modificaCritica(Critica critica1, Critica critica2){
        bddCriticas.modificaRegistro(critica1, critica2);
    }

    /**
     * Regresa las criticas de los vendedores.
     * @return las criticas de los vendedores.
     */
    @Override
    public Iterable<Critica> getCriticas() {
        return bddCriticas.getRegistros();
    }

    /**
     * Elimina una critica de la base de datos de criticas de la mercadita.
     * @param critica la critica a eliminar.
     */
    @Override
    public void eliminaCritica(Critica critica) {
        bddCriticas.eliminaRegistro(critica);
    }

    /**
     * Verifica si hay un carrito
     * @param id el id del carrito
     * @return true si hay un carrito con el id dado, false en otro caso
     */
    @Override
    public boolean hayCarrito(int id){
        for (Carrito c : bddCarritos.getRegistros())
            if (c.getId() == id)
                return true;
        return false;
    }

    /**
     * Elimina un carrito de la base de datos de carritos de la mercadita.
     * @param carrito el carrito a eliminar.
     */
    @Override
    public void eliminaCarrito(Carrito carrito){
        bddCarritos.eliminaRegistro(carrito);
    }

    /**
     * Agrega un carrito a la base de datos de carritos de la mercadita.
     * @param carrito el carrito a agregar.
     */
    @Override
    public void agregaCarrito(Carrito carrito){
        bddCarritos.agregaRegistro(carrito);
    }

    /**
     * Devuelve un carrito de la base de datos de carritos de la mercadita con un id dado.
     * @param id el id del carrito.
     * @return el carrito con el id dado.
     */
    @Override
    public Carrito getCarrito(int id){
        for (Carrito c : bddCarritos.getRegistros())
            if (c.getId() == id)
                return c;
        return null;
    }
}