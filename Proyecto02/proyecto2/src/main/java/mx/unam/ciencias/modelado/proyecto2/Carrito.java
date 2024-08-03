package mx.unam.ciencias.modelado.proyecto2;

import java.util.HashMap;

/**
 * <p>Clase para los carritos de compra. Un carrito de compra es un conjunto de
 * productos que un cliente desea comprar, tiene un identificador, el identificador
 * del cliente que lo creo y el identificador del vendedor al que pertenece.
 * La clase implementa {@link Registro}, por lo que
 * puede seriarse en una linea de texto y deseriarse de una linea de
 * texto; ademas de determinar si sus campos casan valores arbitrarios y
 * actualizarse con los valores de otro Carrito.</p>
 */
public class Carrito implements Registro<Carrito, CampoCarrito> {
    
    /* Mensaje por error al deseriar. */
    private static final String ERROR_DESERIA = "Linea invalida de carrito.";
    /* Identificador del carrito. */
    private int id;
    /* Identificador del cliente. */
    private int idCliente;
    /* Identificador del vendedor. */
    private int idVendedor;
    /* Productos del carrito. */
    private HashMap<String,ElementoCarrito > productos;

    /**
     * Define el estado inicial de un carrito.
     * @param id el identificador del carrito.
     * @param idCliente el identificador del cliente.
     * @param idVendedor el identificador del vendedor.
     */
    public Carrito(int id, int idCliente, int idVendedor) {
        this.id = id;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.productos = new HashMap<>();
    }

    /**
     * Define el estado inicial de un carrito.
     * @param c el carrito a copiar.
     */
    public Carrito(Carrito c){
        this.id = c.getId();
        this.idCliente = c.getIdCliente();
        this.idVendedor = c.getIdVendedor();
        this.productos = new HashMap<>();
        for (ElementoCarrito elementoCarrito : c.getProductos().values()) {
            productos.put(elementoCarrito.getCodigoBarras(), new ElementoCarrito(elementoCarrito));
        }
    }

    /**
     * Regresa el identificador del carrito.
     * @return el identificador del carrito.
     */
    public int getId() {
        return id;
    }

    /**
     * Regresa el identificador del cliente.
     * @return el identificador del cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Define el identificador del carrito.
     * @param id el identificador del carrito.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Define el identificador del cliente.
     * @param idCliente el identificador del cliente.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Regresa el identificador del vendedor.
     * @return el identificador del vendedor.
     */
    public int getIdVendedor() {
        return idVendedor;
    }

    /**
     * Define el identificador del vendedor.
     * @param idVendedor el identificador del vendedor.
     */
    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    /**
     * Regresa los productos del carrito.
     * @return los productos del carrito.
     */
    public HashMap<String, ElementoCarrito> getProductos() {
        return productos;
    }

    /**
     * Define los productos del carrito.
     * @param productos los productos del carrito.
     */
    public void setProductos(HashMap<String, ElementoCarrito> productos) {
        this.productos = productos;
    }

    /**
     * Agrega un producto al carrito.
     * @param idProducto el identificador del producto.
     * @param elementoCarrito el elemento del carrito.
     */
    public void agregaProducto(String idProducto, ElementoCarrito elementoCarrito){
        productos.put(idProducto, elementoCarrito);
    }

    /**
     * Agrega un producto al carrito.
     * @param producto el producto a agregar.
     */
    public void agregaProducto(Producto producto){
        idVendedor = producto.getIdVendedor();
        String idProducto = producto.getCodigoBarras();
        //Si ya esta se aumenta la cantidad
        if(productos.containsKey(idProducto)){
            int cantidadOriginal = productos.get(idProducto).getCantidad();
            productos.get(idProducto).setCantidad(cantidadOriginal+1);
            return;
        }

        ElementoCarrito elementoCarrito = new ElementoCarrito(idProducto, 1);
        productos.put(idProducto, elementoCarrito);
    }

    /**
     * Elimina un producto del carrito.
     * @param idProducto el identificador del producto.
     */
    public void eliminarProducto(int idProducto){
        productos.remove(idProducto);
    }

    /**
     * Limpia el carrito.
     */
    public void limpiarCarrito(){
        productos.clear();
    }
    
    /**
     * Regresa la cantidad de productos en el carrito.
     * @return la cantidad de productos en el carrito.
     */
    public int getCantidadProductos(){
        return productos.size();
    }

    /**
     * Nos dice si el carrito esta vacio.
     * @return <code>true</code> si el carrito esta vacio, <code>false</code> en otro caso.
     */
    public boolean estaVacio(){
        return productos.isEmpty();
    }

    /**
     * Nos dice si el carrito contiene un producto.
     * @param idProducto el identificador del producto.
     * @return <code>true</code> si el carrito contiene el producto, <code>false</code> en otro caso.
     */
    public boolean contieneProducto(int idProducto){
        return productos.containsKey(idProducto);
    }

    /**
     * Regresa un elemento del carrito.
     * @param idProducto el identificador del producto.
     * @return el elemento del carrito.
     */
    public ElementoCarrito getElementoCarrito(int idProducto){
        return productos.get(idProducto);
    }

    /**
     * Actualiza la cantidad de un producto en el carrito.
     * @param idProducto el identificador del producto.
     * @param cantidad la nueva cantidad del producto.
     */
    public void actualizarCantidad(int idProducto, int cantidad){
        ElementoCarrito elementoCarrito = productos.get(idProducto);
        elementoCarrito.setCantidad(cantidad);
    }

    /**
     * Regresa el registro seriado en una linea de texto. La linea de texto
     * que este metodo regresa debe ser aceptada por el metodo {@link
     * Registro#deseria}.
     * @return la seriacion del registro en una linea de texto.
     */
    @Override
    public String seria(){
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("\t").append(idCliente).append("\t").append(idVendedor);
        for (ElementoCarrito elementoCarrito : productos.values()) {
            sb.append("\t").append(elementoCarrito.getCodigoBarras()).append("\t").append(elementoCarrito.getCantidad());
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Deseria una linea de texto en las propiedades del registro. La
     * seriacion producida por el metodo {@link Registro#seria} debe
     * ser aceptada por este metodo.
     * @param linea la linea a deseriar.
     * @throws ExcepcionLineaInvalida si la linea recibida es nula, vacia o no
     *         es una seriacion valida de un registro.
     */
    @Override
    public void deseria(String linea){
        if (linea == null) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        String cadena = linea.trim();
        if (cadena.isEmpty()) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        String[] campos = cadena.split("\t");
        if (campos.length < 3 || campos.length%2 != 1) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        try {
            setId(Integer.parseInt(campos[0]));
            setIdVendedor(Integer.parseInt(campos[2]));
            setIdCliente(Integer.parseInt(campos[1]));
        } catch (NumberFormatException nfe) {
            throw new ExcepcionLineaInvalida(ERROR_DESERIA);
        }

        
        for (int i = 3; i < campos.length; i+=2) {
            if(i+1 >= campos.length) throw new ExcepcionLineaInvalida(ERROR_DESERIA);
            if(productos.keySet().contains(campos[i])){
                int cantidad_original = productos.get(campos[i]).getCantidad();
                productos.get(campos[i]).setCantidad(cantidad_original+ Integer.parseInt(campos[i+1]));
                break;
            }
            ElementoCarrito elementoCarrito = new ElementoCarrito(campos[i], Integer.parseInt(campos[i+1]));
            productos.put(elementoCarrito.getCodigoBarras(), elementoCarrito);
        }
    }

    /**
     * Actualiza los valores del registro con los del registro recibido.
     * @param registro el registro con el cual actualizar los valores.
     */
    @Override
    public void actualiza(Carrito carrito){
        if (carrito == null) throw new IllegalArgumentException("Carrito invalido.");
        setId(carrito.getId());
        setIdCliente(carrito.getIdCliente());
        setIdVendedor(carrito.getIdVendedor());
        setProductos(carrito.getProductos());
    }

    /**
     * Nos dice si el registro casa el valor dado en el campo especificado.
     * @param campo el campo que hay que casar.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si el campo especificado del registro casa el
     *         valor dado, <code>false</code> en otro caso.
     */
    @Override
    public boolean casa(CampoCarrito campo, Object valor){
        if (!(campo instanceof CampoCarrito)) throw new IllegalArgumentException("Campo invalido.");
        CampoCarrito campoUsuario = campo;
        switch (campoUsuario) {
            case ID: return casaID(valor);
            case ID_CLIENTE: return casaIDCliente(valor);
            case ID_VENDEDOR: return casaIDVendedor(valor);
            case PRODUCTO: return casaProducto(valor);
            default: return false;
        }
    }

    /**
     * Nos dice si el carrito casa el id.
     * @param valor el valor a casar.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Integer} y su
     *         valor entero es menor o igual al ID del Carrito,
     *         <code>false</code> en otro caso.
     */
    private boolean casaID(Object valor){
        if (!(valor instanceof Integer)) return false;
        Integer valorID = (Integer) valor;
        return valorID.intValue() == getId();
    }

    /**
     * Nos dice si el carrito casa el id del cliente.
     * @param valor el valor a casar.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Integer} y su
     *        valor entero es menor o igual al ID del Cliente,
     *       <code>false</code> en otro caso.
     */
    private boolean casaIDCliente(Object valor){
        if (!(valor instanceof Integer)) return false;
        Integer valorIDCliente = (Integer) valor;
        return valorIDCliente.intValue() == getIdCliente();
    }

    /**
     * Nos dice si el carrito casa el id del vendedor.
     * @param valor el valor a casar.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Integer} y su
     *        valor entero es menor o igual al ID del Vendedor,
     *       <code>false</code> en otro caso.
     */
    private boolean casaIDVendedor(Object valor){
        if (!(valor instanceof Integer)) return false;
        Integer valorIDVendedor = (Integer) valor;
        return valorIDVendedor.intValue() == getIdVendedor();
    }

    /**
     * Nos dice si el carrito casa un producto.
     * @param valor el valor a casar.
     * @return <code>true</code> si el <code>valor</code> es instancia de {@link Producto} y el
     *         carrito contiene el producto,
     *         <code>false</code> en otro caso.
     */
    private boolean casaProducto(Object valor){
        if (!(valor instanceof Producto)) return false;
        Producto valorProductos = (Producto) valor;
        return getProductos().containsKey(valorProductos.getCodigoBarras());
    }

    /**
     * Regresa un iterable de los elementos del carrito.
     * @return un iterable de los elementos del carrito.
     */
    public Iterable<ElementoCarrito> getElementosCarrito(){
        return productos.values();
    }

    /**
     * Elimina un producto del carrito.
     * @param producto el producto a eliminar.
     */
    public void eliminarProducto(Producto producto){
        productos.get(producto.getCodigoBarras()).setCantidad(productos.get(producto.getCodigoBarras()).getCantidad()-1);
        if(productos.get(producto.getCodigoBarras()).getCantidad() == 0)
            productos.remove(producto.getCodigoBarras());
    }

    /**
     * Regresa la cantidad de un producto en el carrito.
     * @param producto el producto a buscar.
     * @return la cantidad de un producto en el carrito.
     */
    public int getCantidadProducto(Producto producto){
        if(productos.containsKey(producto.getCodigoBarras())){
            return productos.get(producto.getCodigoBarras()).getCantidad();
        }
        return 0;
    }
    
    /*
     * Regresa el precio total del carrito
     * @param vendedor el vendedor que contiene los productos
     * @return el precio total del carrito
     */
    public double getPrecioTotal(InterfazVendedor vendedor){
        double precioTotal = 0;
        for (ElementoCarrito elemento : productos.values()) {
            Producto p = vendedor.getProducto(elemento.getCodigoBarras());
            precioTotal += p.getPrecio() * elemento.getCantidad();
        }
        return precioTotal;
    }

    /**
     * Nos dice si el objeto recibido es un carrito igual al que manda llamar
     * el metodo.
     * @param objeto el objeto con el que el carrito se comparara.
     * @return <code>true</code> si el objeto recibido es un carrito con las
     *         mismas propiedades que el objeto que manda llamar al metodo,
     *         <code>false</code> en otro caso.
     */
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o.getClass() != this.getClass()) return false;
        Carrito c = (Carrito) o;
        return c.getId() == this.getId();
    }
}
