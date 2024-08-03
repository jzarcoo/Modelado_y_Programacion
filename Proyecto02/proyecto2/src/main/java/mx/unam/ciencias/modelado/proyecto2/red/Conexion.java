package mx.unam.ciencias.modelado.proyecto2.red;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosCriticas;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosProductos;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosCarritos;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosUsuarios;
import mx.unam.ciencias.modelado.proyecto2.ExcepcionLineaInvalida;
import mx.unam.ciencias.modelado.proyecto2.Registro;
import mx.unam.ciencias.modelado.proyecto2.TiposBaseDeDatos;

/**
 * <p>Clase para conexiones de la base de datos.</p>
 */
public class Conexion {

    /* Contador de numeros de serie. */
    private static int contadorSerie;
    /* La entrada de la conexion. */
    private BufferedReader in;
    /* La salida de la conexion. */
    private BufferedWriter out;
    /* La base de datos de criticas. */
    private BaseDeDatosCriticas bddCriticas;
    /* La base de datos de productos. */
    private BaseDeDatosProductos bddProductos;
    /* La base de datos de carritos. */
    private BaseDeDatosCarritos bddCarritos;
    /* La base de datos de usuarios. */
    private BaseDeDatosUsuarios bddUsuarios;
    /* Lista de escuchas de conexion. */
    private LinkedList<EscuchaConexion> escuchas;
    /* El enchufe. */
    private Socket enchufe;
    /* Si la conexion esta activa. */
    private boolean activa;
    /* El numero de serie unico de la conexion. */
    private int serie;

    /**
     * Define el estado inicial de una nueva conexion.
     * @param bddCriticas la base de datos de criticas.
     * @param bddProductos la base de datos de productos.
     * @param bddUsuarios la base de datos de usuarios.
     * @param bddCarritos la base de datos de carritos.
     * @param enchufe el enchufe de la conexion ya establecida.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public Conexion(BaseDeDatosCriticas bddCriticas,
                    BaseDeDatosProductos bddProductos,
                    BaseDeDatosUsuarios bddUsuarios,
                    BaseDeDatosCarritos bddCarritos,
                    Socket enchufe) throws IOException {
        this.bddCriticas = bddCriticas;
        this.bddProductos = bddProductos;
        this.bddUsuarios = bddUsuarios;
        this.bddCarritos = bddCarritos;
        this.enchufe = enchufe;
        escuchas = new LinkedList<>();
        in = new BufferedReader(
                new InputStreamReader(enchufe.getInputStream()));
        out = new BufferedWriter(
                new OutputStreamWriter(enchufe.getOutputStream()));
        activa = true;
        serie = ++contadorSerie;
    }

    /**
     * Recibe los mensajes de la conexion. El metodo no termina hasta que la
     * conexion sea cerrada. Al ir leyendo su entrada, la conexion convertira lo
     * que lea en mensajes y reportara cada uno a los escuchas.
     */
    public void recibeMensajes() {
        try {
            String linea;
            while ((linea = in.readLine()) != null){    
                for (EscuchaConexion ec : escuchas)
                    ec.mensajeRecibido(this, Mensaje.getMensaje(linea));
            }
            activa = false;
        } catch (IOException ioe) {
            for (EscuchaConexion ec : escuchas)
                ec.mensajeRecibido(this, Mensaje.INVALIDO);
        }
        for(EscuchaConexion ec : escuchas)
            ec.mensajeRecibido(this, Mensaje.DESCONECTAR);
    }

    /**
     * Recibe la base de datos del otro lado de la conexion.
     * @param tipo el tipo de base de datos a recibir.
     * @throws IOException si la base de datos no puede recibirse.
     */
    public void recibeBaseDeDatos(TiposBaseDeDatos tipo) throws IOException {
        switch(tipo) {
        case CRITICAS:
            bddCriticas.carga(in);
            break;
        case PRODUCTOS:
            bddProductos.carga(in);
            break;
        case CARRITOS:
            bddCarritos.carga(in);
            break;
        case USUARIOS:
            bddUsuarios.carga(in);
            bddUsuarios.llenaBase(bddProductos.getRegistros(), bddCriticas.getRegistros(), bddCarritos.getRegistros());
            break;
        default:
            break;
        }
    }

    /**
     * Envia la base de datos al otro lado de la conexion.
     * @param tipo el tipo de base de datos a enviar.
     * @throws IOException si la base de datos no puede enviarse.
     */
    public void enviaBaseDeDatos(TiposBaseDeDatos tipo) throws IOException {
        switch(tipo) {
        case CRITICAS:
            bddCriticas.guarda(out);
            break;
        case PRODUCTOS:
            bddProductos.guarda(out);
            break;
        case USUARIOS:
            bddUsuarios.guarda(out);
            bddUsuarios.llenaBase(bddProductos.getRegistros(), bddCriticas.getRegistros(), bddCarritos.getRegistros());
            break;
        case CARRITOS:
            bddCarritos.guarda(out);
            break;
        default:
            break;
        }
        out.newLine();
        out.flush();
    }

    /**
     * Recibe un registro del otro lado de la conexion.
     * @param tipo el tipo de base de datos a recibir.
     * @return un registro del otro lado de la conexion.
     * @throws IOException si el registro no puede recibirse.
     */
    public Registro recibeRegistro(TiposBaseDeDatos tipo) throws IOException {
        Registro registro = null;
        switch(tipo) {
        case CRITICAS:
            registro = bddCriticas.creaRegistro();
            break;
        case PRODUCTOS:
            registro = bddProductos.creaRegistro();
            break;
        case USUARIOS:
            registro = bddUsuarios.creaRegistro();
            break;
        case CARRITOS:
            registro = bddCarritos.creaRegistro();
            break;
        default:
            break;
        }
        String linea = in.readLine();
        try{
            if(registro != null)
                registro.deseria(linea);
        } catch(ExcepcionLineaInvalida eli){
            throw new IOException("Error de entrada");
        }
        return registro;
    }

    /**
     * Envia un registro al otro lado de la conexion.
     * @param registro el registro a enviar.
     * @throws IOException si el registro no puede enviarse.
     */
    public <R extends Registro<R,?>> void enviaRegistro(R registro) throws IOException {
        out.write(registro.seria());
        out.flush();
    }

    /**
     * Envia mensaje registro al otro lado de la conexion.
     * @param mensaje el mensaje a enviar.
     * @throws IOException si el mensaje no puede enviarse.
     */
    public void enviaMensaje(Mensaje mensaje) throws IOException {
        out.write(mensaje.toString());
        out.newLine();
        out.flush();
    }

    /**
     * Regresa un numero de serie para cada conexion.
     * @return un numero de serie para cada conexion.
     */
    public int getSerie() {
        return serie;
    }

    /**
     * Cierra la conexion.
     */
    public void desconecta() {
        activa = false;
        try {
            enchufe.close();
        } catch (IOException ioe){}
    }

    /**
     * Nos dice si la conexion es activa.
     * @return <code>true</code> si la conexion es activa; <code>false</code> en
     *         otro caso.
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Agrega un escucha de conexion.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscucha(EscuchaConexion escucha) {
        escuchas.add(escucha);
    }
}
