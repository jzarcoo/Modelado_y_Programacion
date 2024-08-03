package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Clase para la base de datos de carritos.</p
 */
public class BaseDeDatosCarritos extends BaseDeDatos<Carrito, CampoCarrito>{
    
    /**
     * Crea un carrito en blanco.
     * @return un carrito en blanco.
     */
    @Override 
    public Carrito creaRegistro() {
        return new Carrito(0, 0, 0);
    }

}
