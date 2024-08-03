package src;
import java.util.LinkedList;

/*
 * Clase para representar a los carritos. Un carrito tiene un total, y una lista
 * para almacenar los productos que se guardaran en el objeto.
 */
public class Carrito {

    /* Total de los carritos. */
    private double total;
    /* Lista de objetos Producto. */
    private LinkedList<Producto> productos;

    /**
     * Constructor de la clase Carrito.
     * Inicializa el total del carrito en 0 y crea una lista vacia de productos.
     */
    public Carrito() {
        this.productos = new LinkedList<Producto>();
    }

    /**
     * Agrega un producto al carrito.
     * @param producto el producto que se va a agregar.
     */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        total += producto.getPrecio();
    }

    /**
     * Vacia el carrito, eliminando todos los productos y estableciendo el total en 0.
     */
    public void vaciar() {
        productos.clear();
        total = 0;
    }

    /**
     * Verifica si el carrito esta vacio.
     * @return <code>true</code> si el carrito esta vacio, 
     *         <code>false</code> de lo contrario.
     */
    public boolean vacio() {
        return productos.isEmpty();
    }

    /**
     * Regresa el total de la compra en el carrito.
     * @return el total de la compra.
     */
    public double getTotal() {
        return total;
    }   

    /**
     * Define el total de la compra en el carrito.
     * @param total total de la compra.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Imprime un ticket con los productos y sus precios en el carrito.
     */
    public String ticket() {
        StringBuffer ticket = new StringBuffer();
        for(Producto producto : productos) {
            ticket.append(producto.getNombre() + " - " + producto.getPrecio()+"\n");
        }
        return ticket.toString();
    }
}
