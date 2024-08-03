package src.igu;

import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import src.Carrito;
import src.CatalogoComponente;
import src.Cliente;
import src.FabricaSimplePaises;
import src.PaisDeOrigen;
import src.Producto;
import src.Tienda;

/**
 * <p>Clase para el controlador del la interfaz de la tienda.</p>
 */
public class ControladorCheemsMart {

    /* Campo verificable para la opcion del menu*/
    private EntradaVerificable entradaMenu;
    /* Interfaz a la que controla */
    private InterfazCheemsMart interfaz;
    /*Cliente */
    private Cliente cliente;
    /* Tienda. */
    private Tienda tienda;
    /* Carrito de la tienda. */
    private Carrito carrito;


    /**
     * Define el estado inicial del controlador de la tienda.
     * @param tienda tienda.
     */
    public ControladorCheemsMart(Tienda tienda, PaisDeOrigen paisDeOrigen, Cliente c) {
        interfaz = new InterfazCheemsMart (FabricaSimplePaises.getManejadorDelIdioma(paisDeOrigen));
        this.tienda = tienda;
        entradaMenu = new EntradaVerificable();
        entradaMenu.setVerificador(n -> vertificaOpcionMenu(n));
        cliente = c;
        carrito = new Carrito();
        this.tienda.asignarOferta(paisDeOrigen);

    }

    /**
     * Verifica si la ocion del menu es valida.
     * @param entradaMenu opcion del menu.
     * @return <code>true</code> si la entrada es valida, <code>false</code> de lo contrario.
     */
    private boolean vertificaOpcionMenu(String entradaMenu) {
        return entradaMenu != null && !entradaMenu.trim().isEmpty(); 
    }

    /**
     * Regresa la opcion del menu elegida por el cliente
     * @param sc el scanner.
     * @return opcion elegida por el cliente
     */
    private int getOpcionMenu(Scanner sc) {
        boolean isValido = false;
        int opcionMenu = -1;
        while(!isValido) {
            try {
                opcionMenu = Integer.parseInt( interfaz.getOpcionMenu(sc) );
                isValido = true;
            }catch(NumberFormatException e) {
                interfaz.mensajeError();
            }
        }
        return opcionMenu;
    }

    /**
     * Regresa el codigo de barras del input del cliente.
     * @param sc el scanner.
     * @return codigo del input del cliente.
     */
    private String getCodigoProducto(Scanner sc) {
        boolean isValido = false;
        String inputCodigo = "";
        while(!isValido) {
            try {
                inputCodigo = interfaz.getCodigoProducto(sc);
                isValido = true;
            } catch (NoSuchElementException nee) {
                interfaz.mensajeErrorCodigoProducto();
            } catch (IllegalStateException ise) {
                interfaz.mensajeErrorCodigoProducto();
            }
        }
        return inputCodigo;
    }


    /**
     * Regresa el numero de cuenta input del cliente.
     * @param sc el scanner.
     * @return numero de cuenta del input del cliente.
     */
    private String getNumeroCuenta(Scanner sc) {
        boolean isValido = false;
        String inputCuenta = "";
        while(!isValido) {
            try {
                inputCuenta = interfaz.getNumeroCuenta(sc);
                isValido = true;
            } catch (NoSuchElementException nee) {
                interfaz.mensajeErrorCuenta();
            } catch (IllegalStateException ise) {
                interfaz.mensajeErrorCuenta();
            }
        }
        return inputCuenta;
    }


    /**
     * Maneja el menu principal de la tienda
     */
    public void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        interfaz.saludar();
        if(tienda.getOferta()>0)
            interfaz.oferta(tienda.getOferta()*100);

        boolean isOpcionCorrecta = false;
        int opcionMenu;

        do {
            interfaz.menuPrincipal();
            opcionMenu = getOpcionMenu(sc);
            isOpcionCorrecta = opcionMenu > 0 && opcionMenu < 4;
            if (!isOpcionCorrecta)
                interfaz.mensajeError();
        } while (!isOpcionCorrecta);

        switch (opcionMenu) {
            case 1:
                verCatalogo();
                menuPrincipal();
                break;
            case 2:
                verCatalogo();
                comprar();
                break;
            case 3:
                cerrarSesion();
                break;
        }
    }

    /**
     * Muestra el catalogo de la tienda
     */
    private void verCatalogo() {
        StringBuffer sb = new StringBuffer();
        Iterable<CatalogoComponente> catalogo = tienda.getCatalogo();
        for (CatalogoComponente c : catalogo) {
            sb.append(c.imprime()+"\n");
        }
        interfaz.imprimeCatalogo(sb.toString());
    }

    /**
     * Te permite comprar productos de la tienda
     */
    private void comprar() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        boolean isOpcionCorrecta = false;
        int opcionMenu;
        do {
            interfaz.menuCompra();
            opcionMenu = getOpcionMenu(sc);
            isOpcionCorrecta = opcionMenu > 0 && opcionMenu < 4;
            if (!isOpcionCorrecta)
                interfaz.mensajeError();
        } while (!isOpcionCorrecta);

        switch (opcionMenu) {
            case 1:
                agregarAlCarrito();
                comprar();
                break;
            case 2:
                if (carrito.vacio()) {
                    interfaz.carritoVacio();
                    comprar();
                    break;
                }
                terminarCompra();
                break;
            case 3:
                cerrarSesion();
                break;
        }
    }

    /**
     * Agrega un producto al carrito
     */
    private void agregarAlCarrito() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        Producto producto = null;

        do {
            entradaMenu.setTexto(getCodigoProducto(sc));
            if(!entradaMenu.esValida()){
                interfaz.mensajeError();
                continue;
            }
            producto = tienda.buscarProducto(entradaMenu.getTexto());
            if(producto == null)
                interfaz.mensajeErrorCodigoProducto();
        } while (producto == null);
        carrito.agregarProducto(producto);
        interfaz.productoAgregado();
    }

    /**
     * Termina la compra
     */
    private void terminarCompra() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        
        boolean isOpcionCorrecta = false;
        do {
            entradaMenu.setTexto(getNumeroCuenta(sc));
            if(!entradaMenu.esValida()){
                interfaz.mensajeError();
                continue;
            }
            isOpcionCorrecta = tienda.verificarCuentaBancaria(cliente.getId(), entradaMenu.getTexto());
            if (!isOpcionCorrecta)
                interfaz.mensajeErrorCuenta();
        } while (!isOpcionCorrecta);

        if (tienda.realizarCompra(carrito.getTotal(), cliente.getId())) {
            interfaz.compraExitosa();
            finalizarCompra();
        } else {
            interfaz.compraCancelada();
            carrito.vaciar();
        }
        menuPrincipal();
    }
    
    /**
     * Finaliza el proceso de compra mostrando el ticket y el total.
     */
    private void finalizarCompra() {
        interfaz.mensajeTicket();
        generaTicket();
        interfaz.mensajeTotal( carrito.getTotal() );
        interfaz.mensajeEntrega();
        carrito.vaciar();
        menuPrincipal();
    }


    /**
     * Cierra la sesion del cliente
     */
    private void cerrarSesion() {
        interfaz.despedir();
        ControladorInicioSesion c = new ControladorInicioSesion(tienda);
        c.inicioSesion();
    }

    /*
     * Genera un ticket con los productos del carrito.
     */
    public void generaTicket(){
        interfaz.imprimeTicket(carrito.ticket());
    }
}
