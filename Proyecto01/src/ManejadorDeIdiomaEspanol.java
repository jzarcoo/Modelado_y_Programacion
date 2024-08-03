package src;

import java.util.Random;

/**
 * <p>Clase para manejar el idioma espanol de Espana.</p>
 * 
 * <p>La clase ManejadorDeIdiomaEspana implementa la interfaz ManejadorDeIdioma, 
 * por lo que define los siguientes metodos:</p>
 * 
 * <ul>
 * <li>{@link #saludar()}</li>
 * <li>{@link #completarCompra()}</li>
 * <li>{@link #mostrarMenuPrincipal()}</li>
 * <li>{@link #mensajeOferta(int)}</li>
 * <li>{@link #mostrarMenuCompras()}</li>
 * <li>{@link #preguntarProducto()}</li>
 * <li>{@link #productoAgregado()}</li>
 * <li>{@link #mensajeError()}</li>
 * <li>{@link #compraExitosa()}</li>
 * <li>{@link #compraFallida()}</li>
 * <li>{@link #mensajeCarritoVacio()}</li>
 * <li>{@link #despedirse()}</li>
 * <li>{@link #pedirCuenta()}</li>
 * <li>{@link #mensajeEntrega()}</li>
 * <li>{@link #mensajeTicket()}</li>
 * <li>{@link #mensajeTotal(double)}</li>
 * <li>{@link #mensajeOpcionMenu()}</li>
 * <li>{@link #mensajeErrorCodigoProducto()}</li>
 * <li>{@link #mensajeErrorCuenta()}</li>
 * </ul>
 */
public class ManejadorDeIdiomaEspanol implements ManejadorDelIdioma {

    /**
     * Define el mensaje de saludo.
     * @return Saludo.
     */
    @Override
    public String saludar() {
        return "Holaaa, Que tal estais?";
    }

    /**
     * Define el mensaje de despedida.
     * @return Despedida.
     */
    @Override
    public String despedirse() {
        return "Hasta luego!!";
    }

    /**
     * Define el mensaje de completar compra.
     * @return Mensaje de completar compra.
     */
    @Override
    public String completarCompra() {
        return "Compra completada, tio.\n";
    }

    /**
     * Define el mensaje de menu principal.
     * @return Menu principal.
     */
    @Override
    public String mostrarMenuPrincipal() {
        return "1. Checa nuestros productos, tio. \n2. Realizad tu coompra, tio. \n3. Salid de la tienda. ";
    }

    /**
     * Nos dice si el cliente recibio un descuento.
     * @param descuento el descuento recibido.
     * @return un mensaje de oferta.
     */
    @Override
    public String mensajeOferta(double descuento) {
        return "Teneis un descuento, tio, del " + descuento + " %\n";
    }

    /**
     * Regresa un mensaje para mostrar el menu de compras.
     * @return un mensaje para mostrar el menu de compras.
     */
    @Override
    public String mostrarMenuCompras() {
        return "1. Anade a tu carrito, tio. \n2. Finalizad tu compra, tio \n3. Salid de la tienda.";
    }
    
    /**
     * Regresa un mensaje para mostrar el menu de productos.
     * @return un mensaje para mostrar el menu de productos.
     */
    @Override
    public String preguntarProducto() {
        return "Que quereis comprar, tio??\n";
    }

    /**
     * Regresa un mensaje cuando se agrega un producto al carrito.
     * @return un mensaje cuando se agrega un producto al carrito.
     */
    @Override
    public String productoAgregado() {
        return "Vale, tu producto se agrego, tio.\n";
    }

    /**
     * Regresa un mensaje de error.
     * @return un mensaje de error.
     */
    @Override
    public String mensajeError() {
        return "Ups! Me parece que haz metido la pata, tio. Intenta de nuevo, tio.\n";
    }

    /**
     * Regresa un mensaje de exito.
     * @return un mensaje de exito.
     */
    @Override
    public String compraExitosa() {
        return "Que guay, tu compra fue un exito, tio!!\n";
    }

    /**
     * Regresa un mensaje de compra fallida.
     * @return un mensaje de compra fallida.
     */
    @Override
    public String compraFallida() {
        return "Ya te pillamos!, no tienes suficiente saldo, tio. Tu compra se ha declinado\n";
    }

    /**
     * Define el mensaje de carrito vacio.
     * @return Mensaje de carrito vacio.
     */
    @Override
    public String mensajeCarritoVacio() {
        return "Tu carrito se ve muy solo, tio.\n";
    }

    /**
     * Regresa un mensaje para pedir la cuenta.
     * @return un mensaje para pedir la cuenta.
     */
    @Override
    public String pedirCuenta() {
        return "Ingresad tu numero de cuenta, tio: ";
    }

    /**
     * Define el mensaje de entrega.
     * @return Mensaje de entrega.buscarProducto
     */
    @Override
    public String mensajeEntrega() {
        Random random = new Random();
        int dia = random.nextInt(30) + 1;
        int mes = random.nextInt(12) + 1;
        return "Tus productos seran entregados el " + dia + "/" + mes + "/2024, tio.\n";
    }

    /**
     * Define el mensaje de ticket.
     * @return Mensaje de ticket.
     */
    @Override
    public String mensajeTicket() {
        return "Aqui teneis tu ticket, tio: \n";
    }

    /**
     * Define el mensaje del total.
     * @param total Total de la compra.
     * @return Mensaje del total.
     */
    @Override
    public String mensajeTotal(double total) {
        return "Su monto fue de: " + total + ", tio.\n";
    }

    /**
     * Regresa un mensaje para pedir la opcion de menu.
     * @return un mensaje para pedir opcion de menu.
     */
    @Override
    public String mensajeOpcionMenu(){
        return "Escoged una opcion, tio: ";
    }

    /**
     * Muestra un mensaje de error cuando el codigo del producto es incorrecto.
     * @return un mensaje de error cuando el codigo del producto es incorrecto.
     */
    public String mensajeErrorCodigoProducto(){
        return "Ese producto no existe, tio\n";
    }

    /**
     * Muestra un mensaje de error cuando la cuenta bancaria es incorrecta.
     * @return un mensaje de error cuando la cuenta bancaria es incorrecta.
     */
    public String mensajeErrorCuenta(){
        return "Numero de cuenta incorrecto, tio\n";
    }
}
