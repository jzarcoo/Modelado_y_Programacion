package src;

import java.util.Random;

/**
 * <p>Clase para manejar el idioma espanol latino.</p>
 * 
 * <p>La clase ManejadorDeIdiomaLatino implementa la interfaz ManejadorDeIdioma, 
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
public class ManejadorDeIdiomaLatino implements ManejadorDelIdioma {

    /**
     * Regresa un saludo al cliente.
     * @return un saludo al cliente.
     */
    @Override
    public String saludar() {
        return "Hola!";
    }

    /**
     * Define el mensaje de despedida.
     * @return Despedida.
     */
    @Override
    public String despedirse() {
        return "Adios!";
    }

    /**
     * Define el mensaje de completar compra.
     * @return Mensaje de completar compra.
     */
    @Override
    public String completarCompra() {
        return "Compra completa\n";
    }

    /**
     * Define el mensaje de menu principal.
     * @return Menu principal.
     */
    @Override
    public String mostrarMenuPrincipal() {
        return "1. Mostrar productos \n2. Comprar \n3. Salir  ";
    }

    /**
     * Define el mensaje de oferta.
     * @param descuento Porcentaje de descuento.
     * @return Mensaje de oferta.
     */
    @Override
    public String mensajeOferta(double descuento) {
        return "Tienes un descuento del "+ descuento + " %\n";
    }

    /**
     * Define el mensaje de menu de compras.
     * @return Menu de compras.
     */
    @Override
    public String mostrarMenuCompras() {
        return "1. Anade al carrito \n2. Finalizar compra  \n3. Salir" ;
    }

    /**
     * Define el mensaje de pregunta sobre el producto.
     * @return Pregunta sobre el producto.
     */
    @Override
    public String preguntarProducto() {
        return "Que quieres comprar?: ";
    }

    /**
     * Define el mensaje de confirmacion de producto agregado.
     * @return Confirmacion de producto agregado.
     */
    @Override
    public String productoAgregado() {
        return "Producto agregado\n";
    }

    /**
     * Define el mensaje de error.
     * @return Mensaje de error.
     */
    @Override
    public String mensajeError() {
        return "Ups! algo salio mal. Intentalo de nuevo.\n";
    }

    /**
     * Define el mensaje de compra exitosa.
     * @return Mensaje de compra exitosa.
     */
    @Override
    public String compraExitosa() {
        return "Compra exitosa\n";
    }

    /**
     * Define el mensaje de compra fallida.
     * @return Mensaje de compra fallida.
     */
    @Override
    public String compraFallida() {
        return "Hubo un error en la compra. No tienes suficiente saldo\n";
    }

    /**
     * Define el mensaje de carrito vacio.
     * @return Mensaje de carrito vacio.
     */
    @Override
    public String mensajeCarritoVacio() {
        return "Carrito vacio\n";
    }

    /**
     * Define el mensaje del numero de cuenta.
     * @return Pedido del numero de cuenta.
     */
    @Override
    public String pedirCuenta() {
        return "Ingresa tu numero de cuenta: ";
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
        return "Tus productos seran entregados el: "+ dia + "/" + mes + "/2024\n";
    }

    /**
     * Define el mensaje de ticket.
     * @return Mensaje de ticket.
     */
    @Override
    public String mensajeTicket() {
        return "Aqui esta tu ticket: ";
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
        return "Escoge una opcion: ";
    }
    
    /**
     * Muestra un mensaje de error cuando el codigo del producto es incorrecto.
     * @return un mensaje de error cuando el codigo del producto es incorrecto.
     */
    public String mensajeErrorCodigoProducto(){
        return "Codigo incorrecto. Intentalo de nuevo.\n";
    }

    /**
     * Muestra un mensaje de error cuando la cuenta bancaria es incorrecta.
     * @return un mensaje de error cuando la cuenta bancaria es incorrecta.
     */
    public String mensajeErrorCuenta(){
        return "Cuenta incorrecta. Intentalo de nuevo.\n";
    }
}
