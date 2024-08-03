package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Enumeracion para los campos de un {@link Carrito}.</p>
 */
public enum CampoCarrito {

     /** El ID del carrito. */
     ID("ID"),
     /** El ID del Vendedor al que referencia. */
     ID_VENDEDOR("ID Vendedor"),
     /* El ID del Cliente al que le pertenece. */
     ID_CLIENTE("ID Cliente"),
     /* Producto del carrito. */
    PRODUCTO("Producto");
 
     /* Nombre del CampoCarrito. */
     private String nombre;
 
     /**
      * Crea un CampoCarrito con el nombre dado.
      * @param nombre el nombre del CampoCarrito.
      */
     private CampoCarrito(String nombre) {
         this.nombre = nombre;
     }
 
     /**
      * Descifra un CampoCarrito a partir de su nombre.
      * @param nombre el nombre del CampoCarrito.
      * @return el CampoCarrito correspondiente al nombre o,
      *         <code>null</code> si no lo encuentra.
      */
     public static CampoCarrito getCampoCarrito(String nombre) {
         for (CampoCarrito campo : CampoCarrito.values())
             if (campo.toString().equals(nombre))
                 return campo;
         return null;
     }
 
     /**
      * Regresa una representacion en cadena del CampoCarrito.
      * @return una representacion en cadena del CampoCarrito.
      */
     @Override
     public String toString() {
         return nombre;
     }
}
