package src.igu;

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import src.Cliente;
import src.ClienteProxy;
import src.ClienteReal;
import src.Tienda;


/**
 * <p>Clase para el controlador del inicio de sesion.</p>
 */
public class ControladorInicioSesion {

    /* Campo verificable para el nombre de usuario. */
    private EntradaVerificable entradaNombreUsuario;
    /* Campo verificable para la contrasena. */
    private EntradaVerificable entradaContrasena;
    /* Tienda. */
    private Tienda tienda;
    /* Nombre de usuario del Cliente. */
    private String nombreUsuario;


    /**
     * Define el estado inicial del controlador de inicio de sesion.
     * @param tienda tienda.
     */
    public ControladorInicioSesion(Tienda tienda) {
        this.tienda = tienda;
        entradaNombreUsuario = new EntradaVerificable();
        entradaNombreUsuario.setVerificador(n -> vertificaNombreUsuario(n));
        entradaContrasena = new EntradaVerificable();
        entradaContrasena.setVerificador(c -> vertificaContrasena(c));
        
    }

    /**
     * Verifica si el nombre de usuario es valido.
     * @param nombreUsuario nombre de usuario.
     * @return <code>true</code> si el nombre de usuario es valido, <code>false</code> de lo contrario.
     */
    private boolean vertificaNombreUsuario(String nombreUsuario) {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty())
            return false;
        this.nombreUsuario = nombreUsuario.trim();
        return true;
    }

    /**
     * Verifica si la contrasena es valida.
     * @param contrasena contrasena.
     * @return <code>true</code> si la contrasena es valida, <code>false</code> de lo contrario.
     */
    private boolean vertificaContrasena(String contrasena) {
        if (contrasena == null || contrasena.trim().isEmpty())
            return false;
        return true;
    }

    /**
     * Inicia sesion en la tienda.
     */
    public void inicioSesion() {
        InicioSesion.mensajeOpcionSalida();
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        boolean iscredencialesCorrectas = false;
        do {
            entradaNombreUsuario.setTexto(getNombreUsuario(sc));
            entradaContrasena.setTexto(getContrasena(sc));
            iscredencialesCorrectas = entradaNombreUsuario.esValida() && entradaContrasena.esValida() &&
                                    tienda.verificarCredenciales(entradaNombreUsuario.getTexto(), entradaContrasena.getTexto());
            if (!iscredencialesCorrectas) 
                InicioSesion.mensajeError();
        } while (!iscredencialesCorrectas);
        // Obtener al cliente y crear el nuevo controlador del cliente
        Cliente cliente = tienda.getCliente(nombreUsuario);
        Cliente clienteProxy = new ClienteProxy((ClienteReal) cliente);
        ControladorCheemsMart controladorTienda = new ControladorCheemsMart(tienda, cliente.getPaisDeOrigen(),clienteProxy );
        controladorTienda.menuPrincipal();
    }

    /**
     * Regresa el nombre de usuario del input del cliente.
     * @param sc el scanner.
     * @return nombre de usuario del input del cliente.
     */
    private String getNombreUsuario(Scanner sc) {
        boolean isValido = false;
        String inputNombreUsuario = "";
        while(!isValido) {
            try {
                inputNombreUsuario = InicioSesion.getNombreUsuario(sc);
                isValido = true;
            } catch (NoSuchElementException nee) {
                InicioSesion.mensajeErrorNombreUsuario();
            } catch (IllegalStateException ise) {
                InicioSesion.mensajeErrorNombreUsuario();
            }
        }
        if(inputNombreUsuario.equals ("0")){
            InicioSesion.mensajeSalida();
            System.exit(0);
        }
            
        return inputNombreUsuario;
    }

    /**
     * Regresa la contrasena del input del cliente.
     * @param sc el scanner.
     * @return contrasena del input del cliente.
     */
    private String getContrasena(Scanner sc) {
        boolean isValido = false;
        String inputContrasena = "";
        while(!isValido) {
            try {
                inputContrasena = InicioSesion.getContrasena(sc);
                isValido = true;
            } catch (NoSuchElementException nee) {
                InicioSesion.mensajeErrorContrasena();
            } catch (IllegalStateException ise) {
                InicioSesion.mensajeErrorContrasena();
            }
        }
        if(inputContrasena.equals ("0")){
            InicioSesion.mensajeSalida();
            System.exit(0);
        }
        return inputContrasena;
    }

}
