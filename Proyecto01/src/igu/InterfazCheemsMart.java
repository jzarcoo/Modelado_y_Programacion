package src.igu;

import java.util.Scanner;
import src.ManejadorDelIdioma;

/**
 * <p>Clase para interactuar con la tienda como un cliente.</p>
 */
public class InterfazCheemsMart {

    /* Manejador de idioma de la tienda. */
    private ManejadorDelIdioma manejadorDelIdioma;

    /**
     * Define el estado inicial de la interfaz de la tienda.
     * @param manejadorDelIdioma el manejador de idioma de la tienda.
     */
    public InterfazCheemsMart(ManejadorDelIdioma manejadorDelIdioma) {
        this.manejadorDelIdioma = manejadorDelIdioma;
    }

    /**
     * Regresa la opcion del menu elegida por el cliente
     * @param sc el scanner.
     * @return opcion del menu elegida por el cliente.
     */
    public String getOpcionMenu(Scanner sc) {
        System.out.println(manejadorDelIdioma.mensajeOpcionMenu());
        return sc.nextLine();
    }

    /**
     * Muestra un mensaje de error cuando la opcion del menu es incorrecta.
     */
    public  void mensajeError() {
        System.out.println(manejadorDelIdioma.mensajeError());
    }

    /**
     * Muestra un mensaje de bienvenida y la oferta
     */
    public void saludar() {
        System.out.println("************* CHEEMSMART ****************\n");
        System.out.println(manejadorDelIdioma.saludar());
    }

    /**
     * Muestra un mensaje de la oferta
     */
    public void oferta(double oferta) {
        System.out.println(manejadorDelIdioma.mensajeOferta(oferta));
    }

    /**
     * Muestra un mensaje de despedida
     */
    public void despedir() {
        System.out.println(manejadorDelIdioma.despedirse());
        System.out.println("*****************************************");
    }

    /**
     * Imprime el menu principal de la tienda
     */
    public void menuPrincipal() {
        System.out.println(manejadorDelIdioma.mostrarMenuPrincipal());
    }

    /**
     * Muestra el menu de compra de la tienda
     */
    public void menuCompra() {
        System.out.println(manejadorDelIdioma.mostrarMenuCompras());
    }

    /**
     * Pregunta el codigo del producto que quiere comprar
     */
    public void preguntarProducto() {
        System.out.println(manejadorDelIdioma.preguntarProducto());
    }

    /**
     * Regresa el codigo de barras del input del cliente.
     * @param sc el scanner.
     * @return codigo del input del cliente.
     */
    public String getCodigoProducto(Scanner sc) {
        System.out.println(manejadorDelIdioma.preguntarProducto());
        return sc.nextLine();
    }

    /**
     * Muestra un mensaje de error cuando el codigo del producto es incorrecto.
     */
    public void mensajeErrorCodigoProducto() {
        System.out.println(manejadorDelIdioma.mensajeErrorCodigoProducto());
    }

    /**
     * Muestra un mensaje de que se agrego el pructo al carrito
     */
    public void productoAgregado(){
        System.out.println(manejadorDelIdioma.productoAgregado());
    }

    /**
     * Muestra un mensaje de que el carrito esta vacio
     */
    public void carritoVacio(){
        System.out.println(manejadorDelIdioma.mensajeCarritoVacio());
    }
    
    /**
     * Muestra un mensaje para pedir la cuenta
     */
    public void pedirCuenta(){
        System.out.println(manejadorDelIdioma.pedirCuenta());
    }

    /**
     * Regresa el numero de cuenta input del cliente.
     * @param sc el scanner.
     * @return numero de cuenta del input del cliente.
     */
    public String getNumeroCuenta(Scanner sc) {
        System.out.println(manejadorDelIdioma.pedirCuenta());
        return sc.nextLine();
    }

    /**
     * Muestra un mensaje de error cuando el numero de cuenta es incorrecto.
     */
    public void mensajeErrorCuenta() {
        System.out.println(manejadorDelIdioma.mensajeErrorCuenta());
    }

    /**
     * Muestra un mensaje de que la compra fue exitosa
     */
    public void compraExitosa(){
        System.out.println(manejadorDelIdioma.compraExitosa());
    }

    /**
     * Muestra un mensaje de que la compra fue cancelada
     */
    public void compraCancelada(){
        System.out.println(manejadorDelIdioma.compraFallida());
    }

    /**
     * Muestra un mensaje de que se imprimira el ticket
     */
    public void mensajeTicket(){
        System.out.println(manejadorDelIdioma.mensajeTicket());
    }
    /**
     * Muestra un mensaje de que se tiene fecha de entrega
     */
    public void mensajeEntrega(){
        System.out.println(manejadorDelIdioma.mensajeEntrega());
    }

    /**
     * Muestra el total de la compra
     * @param total el total de la compra
     */
    public void mensajeTotal(double total){
        System.out.println(manejadorDelIdioma.mensajeTotal(total));
    }

    /**
     * Muestra el ticket de la compra
     * @param ticket el ticket de la compra
     */
    public void imprimeTicket(String ticket){
        System.out.println("****************** TICKET ******************");
        System.out.println(ticket);
    }

    /**
     * Imprime el catalogo de productos
     */
    public void imprimeCatalogo(String catalogo){
        System.out.println("****************** CATALOGO ******************");
        System.out.println(catalogo);
    }
}