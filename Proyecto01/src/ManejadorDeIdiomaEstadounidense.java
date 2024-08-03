package src;

import java.util.Random;

/**
 * <p>Clase para manejar el idioma estadounidense.</p>
 * 
 * <p>La clase ManejadorDeIdiomaEstadounidense implementa la interfaz ManejadorDeIdioma, 
 * por lo que define los siguientes metodos:</p>
 *
 *  <ul>
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
public class ManejadorDeIdiomaEstadounidense implements ManejadorDelIdioma {

    /**
     * Regresa un saludo al cliente.
     * @return un saludo al cliente.
     */
    @Override
    public String saludar() {
        return "Hello!";
    }

    /**
     * Define el mensaje de despedida.
     * @return mensaje de spedida.
     */
    @Override
    public String despedirse() {
        return "Goodbye!";
    }

    /**
     * Define el mensaje de completar compra.
     * @return Mensaje de completar compra.
     */
    @Override
    public String completarCompra() {
        return "Complete purchase\n";
    }

    /**
     * Define el mensaje de menu principal.
     * @return Menu principal.
     */
    @Override
    public String mostrarMenuPrincipal() {
        return "1. Show products \n2. Buy \n3. Exit ";
    }

    /**
     * Define el mensaje de oferta.
     * @param descuento Porcentaje de descuento.
     * @return Mensaje de oferta.
     */
    @Override
    public String mensajeOferta(double descuento) {
        return "You have a "+ descuento + " % discount\n";
    }

    /**
     * Define el mensaje de menu de compras.
     * @return Menu de compras.
     */
    @Override
    public String mostrarMenuCompras() {
        return "1. Add to cart \n2. Finish purchase \n3. Exit  ";
    }

    /**
     * Define el mensaje de pregunta sobre el producto.
     * @return Pregunta sobre el producto.
     */
    @Override
    public String preguntarProducto() {
        return "What do you want to buy? Enter the product code: ";
    }

    /**
     * Define el mensaje de confirmacion de producto agregado.
     * @return Confirmacion de producto agregado.
     */
    @Override
    public String productoAgregado() {
        return "Product added\n";
    }

    /**
     * Define el mensaje de error.
     * @return Mensaje de error.
     */
    @Override
    public String mensajeError() {
        return "Error message\n";
    }

    /**
     * Define el mensaje de compra exitosa.
     * @return Mensaje de compra exitosa.
     */
    @Override
    public String compraExitosa() {
        return "Successful purchase\n";
    }

    /**
     * Define el mensaje de compra fallida.
     * @return Mensaje de compra fallida.
     */
    @Override
    public String compraFallida() {
        return "Failed purchase\n";
    }

    /**
     * Define el mensaje de carrito vacio.
     * @return Mensaje de carrito vacio.
     */
    @Override
    public String mensajeCarritoVacio() {
        return "Empty cart\n";
    }

    /**
     * Define el mensaje del numero de cuenta.
     * @return Pedido del numero de cuenta.
     */
    @Override
    public String pedirCuenta() {
        return "What is your account number? ";
    }

    /**
     * Define el mensaje de entrega.
     * @return Mensaje de entrega.
     */
    @Override
    public String mensajeEntrega() {
        Random random = new Random();
        int dia = random.nextInt(30) + 1;
        int mes = random.nextInt(12) + 1;
        return "Your product will be delivered on: "+ mes + "/" + dia + "/2024\n";
    }

    /**
     * Define el mensaje de ticket.
     * @return Mensaje de ticket.
     */
    @Override
    public String mensajeTicket() {
        return "Here's your ticket: ";
    }

    /**
     * Define el mensaje del total.
     * @param total Total de la compra.
     * @return Mensaje del total.
     */
    @Override
    public String mensajeTotal(double total) {
        return "Total: "+ total;
    }   


    /**
     * Regresa un mensaje para pedir la opcion de menu.
     * @return un mensaje para pedir opcion de menu.
     */
    @Override
    public String mensajeOpcionMenu(){
        return "Choose an option: \n";
    }

    /**
     * Muestra un mensaje de error cuando el codigo del producto es incorrecto.
     * @return un mensaje de error cuando el codigo del producto es incorrecto.
     */
    public String mensajeErrorCodigoProducto(){
        return "Invalid product code\n";
    }

    /**
     * Muestra un mensaje de error cuando la cuenta bancaria es incorrecta.
     * @return un mensaje de error cuando la cuenta bancaria es incorrecta.
     */
    public String mensajeErrorCuenta(){
        return "Invalid account number\n";
    }
}
