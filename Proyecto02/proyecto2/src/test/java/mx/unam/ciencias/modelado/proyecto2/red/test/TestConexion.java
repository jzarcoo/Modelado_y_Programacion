package mx.unam.ciencias.modelado.proyecto2.red.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosUsuarios;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosCriticas;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosProductos;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosCarritos;
import mx.unam.ciencias.modelado.proyecto2.Usuario;
import mx.unam.ciencias.modelado.proyecto2.Critica;
import mx.unam.ciencias.modelado.proyecto2.Producto;
import mx.unam.ciencias.modelado.proyecto2.Carrito;
import mx.unam.ciencias.modelado.proyecto2.TiposBaseDeDatos;
import mx.unam.ciencias.modelado.proyecto2.red.Conexion;
import mx.unam.ciencias.modelado.proyecto2.red.Mensaje;
import mx.unam.ciencias.modelado.proyecto2.test.TestUsuario;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * <p>Clase para pruebas unitarias de la clase {@link Conexion}.</p>
 */
public class TestConexion {

    /** Expiracion para que ninguna prueba tarde mas de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* El total de usuarios. */
    private int total;
    /* La base de datos de usuarios. */
    private BaseDeDatosUsuarios bddUsuarios;
    /* La base de datos de criticas. */
    private BaseDeDatosCriticas bddCriticas;
    /* La base de datos de productos. */
    private BaseDeDatosProductos bddProductos;
    /* La base de datos de carritos. */
    private BaseDeDatosCarritos bddCarritos;
    /* El puerto. */
    private int puerto;
    /* El enchufe. */
    private Socket enchufe;
    /* La entrada. */
    private BufferedReader in;
    /* La salida. */
    private BufferedWriter out;
    /* El servidor. */
    private ServerSocket servidor;
    /* Generador de numeros aleatorios. */
    private Random random;

    /**
     * Inicializa las pruebas unitarias para {@link Conexion}.
     */
    public TestConexion() {
        random = new Random();
        total = 5 + random.nextInt(10);

        bddUsuarios = new BaseDeDatosUsuarios();
        UtilRed.llenaBaseDeDatosUsuarios(bddUsuarios, total);
        bddCriticas = new BaseDeDatosCriticas();
        // UtilRed.llenaBaseDeDatosCriticas(bddCriticas, total);
        bddProductos = new BaseDeDatosProductos();
        // UtilRed.llenaBaseDeDatosProductos(bddProductos, total);
        bddCarritos = new BaseDeDatosCarritos();
        // UtilRed.llenaBaseDeDatosCarritos(bddCarritos, total);

        creaServidor();
        puerto = servidor.getLocalPort();
        iniciaServidor();
    }

    /* Crea el servidor. */
    private void creaServidor() {
        while (servidor == null) {
            try {
                int p = 1024 + random.nextInt(64500);
                servidor = new ServerSocket(p);
            } catch (BindException be) {
                UtilRed.espera(10);
            } catch (IOException ioe) {
                Assert.fail();
            }
        }
    }

    /* Inicia el servidor. */
    private void iniciaServidor() {
        new Thread(() -> {
            try {
                enchufe = servidor.accept();
                in = new BufferedReader(
                        new InputStreamReader(
                            enchufe.getInputStream()));
                out = new BufferedWriter(
                        new OutputStreamWriter(
                            enchufe.getOutputStream()));
            } catch (IOException ioe) {
                Assert.fail();
            }
        }).start();
        UtilRed.espera(10);
    }

    /**
     * Prueba unitaria para {@link Conexion#recibeMensajes}.
     */
    @Test public void testRecibeMensajes() {
        Mensaje[] mensaje = { null };
        try {
            Socket enchufe = new Socket("localhost", puerto);
            Conexion conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios, bddCarritos, enchufe);
            conexion.agregaEscucha((c, m) -> mensaje[0] = m);
            UtilRed.espera(10);
            new Thread(() -> conexion.recibeMensajes()).start();
            UtilRed.espera(10);
            for (Mensaje m : Mensaje.class.getEnumConstants()) {
                out.write(m.toString());
                out.newLine();
                out.flush();
                UtilRed.espera(10);
                Assert.assertTrue(mensaje[0] == m);
            }
            out.write("T_T");
            out.newLine();
            out.flush();
            UtilRed.espera(10);
            Assert.assertTrue(mensaje[0] == Mensaje.INVALIDO);
            this.enchufe.close();
            UtilRed.espera(10);
            Assert.assertTrue(mensaje[0] == Mensaje.DESCONECTAR);
        } catch (IOException ioe) {
            Assert.fail();
        }
    }

    /**
     * Prueba unitaria para {@link Conexion#recibeBaseDeDatos}.
     */
    @Test public void testRecibeBaseDeDatos() {
        Assert.assertTrue(testRecibeBaseDeDatosUsuarios());
    }

    /**
     * Prueba unitaria para {@link Conexion#recibeBaseDeDatos}, para la base de datos de usuarios.
     */
    public boolean testRecibeBaseDeDatosUsuarios() {
        try {
            BaseDeDatosUsuarios bddUsuarios = new BaseDeDatosUsuarios();
            Socket enchufe = new Socket("localhost", puerto);
            Conexion conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios, bddCarritos, enchufe);
            UtilRed.espera(10);
            this.bddUsuarios.guarda(out);
            out.newLine();
            out.flush();
            UtilRed.espera(10);
            conexion.recibeBaseDeDatos(TiposBaseDeDatos.USUARIOS);
            return this.bddUsuarios.getRegistros().equals(bddUsuarios.getRegistros());
        } catch (IOException ioe) {
            return false;
        }
    }

    /**
     * Prueba unitaria para {@link Conexion#enviaBaseDeDatos}.
     */
    @Test public void testEnviaBaseDeDatos() {
        Assert.assertTrue(testEnviaBaseDeDatosUsuarios());
    }

    /**
     * Prueba unitaria para {@link Conexion#enviaBaseDeDatos}, para la base de datos de usuarios.
     */
    public boolean testEnviaBaseDeDatosUsuarios() {
        try {
            BaseDeDatosUsuarios bddUsuarios = new BaseDeDatosUsuarios();
            UtilRed.llenaBaseDeDatosUsuarios(bddUsuarios, total);
            Socket enchufe = new Socket("localhost", puerto);
            Conexion conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios, bddCarritos, enchufe);
            UtilRed.espera(10);
            this.bddUsuarios.limpia();
            conexion.enviaBaseDeDatos(TiposBaseDeDatos.USUARIOS);
            UtilRed.espera(10);
            this.bddUsuarios.carga(in);
            return this.bddUsuarios.getRegistros().equals(bddUsuarios.getRegistros());
        } catch (IOException ioe) {
            return false;
        }
    }

    /**
     * Prueba unitaria para {@link Conexion#recibeRegistro}.
     */
    public void testRecibeRegistro() {
        testRecibeRegistroBaseDeDatosUsuarios();
    }

    /**
     * Prueba unitaria para {@link Conexion#recibeRegistro}, para la base de datos de usuarios.
     */
    @Test public void testRecibeRegistroBaseDeDatosUsuarios() {
        try {
            BaseDeDatosUsuarios bddUsuarios = new BaseDeDatosUsuarios();
            Socket enchufe = new Socket("localhost", puerto);
            Conexion conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios, bddCarritos, enchufe);
            UtilRed.espera(10);
            Usuario e = TestUsuario.usuarioAleatorio(12345678);
            out.write(e.seria());
            out.flush();
            UtilRed.espera(10);
            Usuario f = (Usuario) conexion.recibeRegistro(TiposBaseDeDatos.USUARIOS);
            Assert.assertTrue(e != f);
            Assert.assertTrue(e.equals(f));
        } catch (IOException ioe) {
            Assert.fail();
        }
    }

    /**
     * Prueba unitaria para {@link Conexion#enviaRegistro}.
     */
    public void testEnviaRegistro() {
        testEnviaRegistroBaseDeDatosUsuarios();
    }

    /**
     * Prueba unitaria para {@link Conexion#enviaRegistro}, para la base de datos de usuarios.
     */
    @Test public void testEnviaRegistroBaseDeDatosUsuarios() {
        try {
            BaseDeDatosUsuarios bddUsuarios = new BaseDeDatosUsuarios();
            UtilRed.llenaBaseDeDatosUsuarios(bddUsuarios, total);
            Socket enchufe = new Socket("localhost", puerto);
            Conexion conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios, bddCarritos, enchufe);
            UtilRed.espera(10);
            Usuario u = TestUsuario.usuarioAleatorio(12345678);
            conexion.enviaRegistro(u);
            UtilRed.espera(10);
            String linea = in.readLine();
            Assert.assertTrue(u.seria().equals(linea + "\n"));
        } catch (IOException ioe) {
            Assert.fail();
        }
    }

    /**
     * Prueba unitaria para {@link Conexion#enviaMensaje}.
     */
    public void testEnviaMensaje() {
        testEnviaMensajeBaseDeDatosUsuarios();
    }

    /**
     * Prueba unitaria para {@link Conexion#enviaMensaje}, para la base de datos de usuarios.
     */
    @Test public void testEnviaMensajeBaseDeDatosUsuarios() {
        try {
            BaseDeDatosUsuarios bddUsuarios = new BaseDeDatosUsuarios();
            UtilRed.llenaBaseDeDatosUsuarios(bddUsuarios, total);
            Socket enchufe = new Socket("localhost", puerto);
            Conexion conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios, bddCarritos, enchufe);
            UtilRed.espera(10);
            String linea;
            for (Mensaje mensaje : Mensaje.class.getEnumConstants()) {
                conexion.enviaMensaje(mensaje);
                UtilRed.espera(10);
                linea = in.readLine();
                Assert.assertTrue(mensaje.toString().equals(linea));
            }
        } catch (IOException ioe) {
            Assert.fail();
        }
    }

    /**
     * Prueba unitaria para {@link Conexion#getSerie}.
     */
    public void testGetSerie() {
        testGetSerieBaseDeDatosUsuarios();
    }

    /**
     * Prueba unitaria para {@link Conexion#getSerie}, para la base de datos de usuarios.
     */
    @Test public void testGetSerieBaseDeDatosUsuarios() {
        try {
            BaseDeDatosUsuarios bddUsuarios = new BaseDeDatosUsuarios();
            UtilRed.llenaBaseDeDatosUsuarios(bddUsuarios, total);
            Socket enchufe = new Socket("localhost", puerto);
            Conexion conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios, bddCarritos, enchufe);
            int serie = conexion.getSerie();
            for (int i = 0; i < total; i++) {
                iniciaServidor();
                enchufe = new Socket("localhost", puerto);
                conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios, bddCarritos, enchufe);
                Assert.assertTrue(conexion.getSerie() == serie + i + 1);
            }
        } catch (IOException ioe) {
            Assert.fail();
        }
    }

    /**
     * Prueba unitaria para {@link Conexion#desconecta}.
     */
    public void testDesconecta() {
        testDesconectaBaseDeDatosUsuarios();
    }

    /**
     * Prueba unitaria para {@link Conexion#desconecta}, para la base de datos de usuarios.
     */
    @Test public void testDesconectaBaseDeDatosUsuarios() {
        try {
            BaseDeDatosUsuarios bddUsuarios = new BaseDeDatosUsuarios();
            UtilRed.llenaBaseDeDatosUsuarios(bddUsuarios, total);
            Socket enchufe = new Socket("localhost", puerto);
            Conexion conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios, bddCarritos, enchufe);
            UtilRed.espera(10);
            conexion.desconecta();
            Assert.assertFalse(conexion.isActiva());
            String linea = in.readLine();
            Assert.assertTrue(linea == null);
        } catch (IOException ioe) {
            Assert.fail();
        }
    }

    /**
     * Prueba unitaria para {@link Conexion#isActiva}.
     */
    public void testIsActiva() {
        testIsActivaBaseDeDatosUsuarios();
    }

    /**
     * Prueba unitaria para {@link Conexion#isActiva}, para la base de datos de usuarios.
     */
    @Test public void testIsActivaBaseDeDatosUsuarios() {
        try {
            BaseDeDatosUsuarios bddUsuarios = new BaseDeDatosUsuarios();
            UtilRed.llenaBaseDeDatosUsuarios(bddUsuarios, total);
            Socket enchufe = new Socket("localhost", puerto);
            Conexion conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios, bddCarritos, enchufe);
            UtilRed.espera(10);
            Assert.assertTrue(conexion.isActiva());
            conexion.desconecta();
            Assert.assertFalse(conexion.isActiva());
            try {
                conexion.enviaMensaje(Mensaje.ECO);
                Assert.fail();
            } catch (IOException ioe) {}
        } catch (IOException ioe) {
            Assert.fail();
        }
    }


    /**
     * Prueba unitaria para {@link Conexion#agregaEscucha}.
     */
    @Test public void testAgregaEscucha() {
        Mensaje[] mensajes = new Mensaje[total];
        try {
            Socket enchufe = new Socket("localhost", puerto);
            Conexion conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios, bddCarritos, enchufe);
            for (int i = 0; i < mensajes.length; i++) {
                int j = i;
                conexion.agregaEscucha((c, m) -> mensajes[j] = m);
            }
            UtilRed.espera(10);
            new Thread(() -> conexion.recibeMensajes()).start();
            UtilRed.espera(10);
            for (Mensaje m : Mensaje.class.getEnumConstants()) {
                out.write(m.toString());
                out.newLine();
                out.flush();
                UtilRed.espera(10);
                for (int i = 0; i < mensajes.length; i++)
                    Assert.assertTrue(mensajes[i] == m);
            }
            out.write("T_T");
            out.newLine();
            out.flush();
            UtilRed.espera(10);
            for (int i = 0; i < mensajes.length; i++)
                Assert.assertTrue(mensajes[i] == Mensaje.INVALIDO);
            this.enchufe.close();
            UtilRed.espera(10);
            for (int i = 0; i < mensajes.length; i++)
                Assert.assertTrue(mensajes[i] == Mensaje.DESCONECTAR);
        } catch (IOException ioe) {
            Assert.fail();
        }
    }
}
