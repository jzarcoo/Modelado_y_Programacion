package mx.unam.ciencias.modelado.proyecto2.red;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatos;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosCriticas;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosCarritos;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosProductos;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosUsuarios;
import mx.unam.ciencias.modelado.proyecto2.Carrito;
import mx.unam.ciencias.modelado.proyecto2.Critica;
import mx.unam.ciencias.modelado.proyecto2.Producto;
import mx.unam.ciencias.modelado.proyecto2.TiposBaseDeDatos;

/**
 * <p>Clase abstracta para servidores de bases de datos genericas.</p>
 */
public class ServidorBaseDeDatos implements Sujeto, SujetoConexiones {

    /* La base de datos de criticas. */
    private BaseDeDatosCriticas bddCriticas;
    /* La base de datos de productos. */
    private BaseDeDatosProductos bddProductos;
    /* La base de datos de usuarios. */
    private BaseDeDatosUsuarios bddUsuarios;
    /* La base de datos de carritos. */
    private BaseDeDatosCarritos bddCarritos;

    /* La ruta donde cargar/guardar la base de datos de criticas. */
    private String rutaCriticas;
    /* La ruta donde cargar/guardar la base de datos de productos. */
    private String rutaProductos;
    /* La ruta donde cargar/guardar la base de datos de usuarios. */
    private String rutaUsuarios;
    /*La ruta donde cargar/guardar la base de datos de los carritos */
    private String rutaCarritos;

    /* El servidor de enchufes. */
    private ServerSocket servidor;
    /* El puerto. */
    private int puerto;
    /* Lista con las conexiones. */
    private LinkedList<Conexion> conexiones;
    /* Bandera de continuacion. */
    private boolean continuaEjecucion;
    /* Escuchas del servidor. */
    private LinkedList<EscuchaServidor> escuchas;

    /**
     * Define el estado inicial de un servidor de base de datos.
     * @param puerto el puerto donde escuchar por conexiones.
     * @param rutaCriticas la ruta de la base de datos de criticas.
     * @param rutaProductos la ruta de la base de datos de productos.
     * @param rutaUsuarios la ruta de la base de datos de usuarios.
     * @param rutaCarritos la ruta de la base de datos de carritos.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public ServidorBaseDeDatos(int puerto, 
                                String rutaCriticas,
                                String rutaProductos,
                                String rutaUsuarios,
                                String rutaCarritos) throws IOException {
        this.puerto = puerto;
        this.rutaCriticas = rutaCriticas;
        this.rutaProductos = rutaProductos;
        this.rutaUsuarios = rutaUsuarios;
        this.rutaCarritos = rutaCarritos;
        servidor = new ServerSocket(puerto);
        conexiones = new LinkedList<>();
        escuchas = new LinkedList<>();
        bddCriticas = new BaseDeDatosCriticas();
        bddProductos = new BaseDeDatosProductos();
        bddUsuarios = new BaseDeDatosUsuarios();
        bddCarritos = new BaseDeDatosCarritos();
    }

    /**
     * Comienza a escuchar por conexiones de clientes.
     */
    public void sirve() {
        continuaEjecucion = true;
        anotaMensaje("Escuchando en el puerto : %d.", puerto);
        while (continuaEjecucion) {
            try {
                Socket enchufe = servidor.accept();
                Conexion conexion = new Conexion(bddCriticas, bddProductos, bddUsuarios, bddCarritos,enchufe);
                String hostName = enchufe.getInetAddress().getCanonicalHostName();
                anotaMensaje("Conexion recibida de: %s.", hostName);
                anotaMensaje("Serie de conexion: %d.", conexion.getSerie());
                conexion.agregaEscucha((c, m) -> mensajeRecibido(c, m));
                new Thread(() -> conexion.recibeMensajes()).start();
                conecta(conexion);
            } catch (IOException ioe) {
                if (continuaEjecucion)
                    anotaMensaje("Error al recibir una conexion.");
            }
        }
        anotaMensaje("La ejecucion del servidor ha terminado.");
    }

    /**
     * Agrega una conexion al sujeto de conexiones.
     * @param conexion la conexion a agregar.
     */
    @Override
    public void conecta(Conexion conexion) {
        synchronized (conexiones) {
            conexiones.add(conexion);
        }
    }

    /**
     * Agrega un escucha de servidor.
     * @param escucha el escucha a agregar.
     */
    @Override
    public void agregaEscucha(EscuchaServidor escucha) {
        escuchas.add(escucha);
    }

    /**
     * Elimina un escucha de servidor.
     * @param escucha el escucha a eliminar.
     */
    @Override
    public void eliminaEscucha(EscuchaServidor escucha) {
        escuchas.remove(escucha);
    }

    /**
     * Limpia todos los escuchas del servidor.
     */
    public void limpiaEscuchas() {
        escuchas.clear();
    }

    /* Carga la base de datos del disco duro. */
    public void carga() {
        carga(bddCriticas, rutaCriticas);
        carga(bddProductos, rutaProductos);
        carga(bddUsuarios, rutaUsuarios);
        carga(bddCarritos,rutaCarritos);
        bddUsuarios.llenaBase(bddProductos.getRegistros(), bddCriticas.getRegistros(), bddCarritos.getRegistros());
    }

    /**
     * Carga la base de datos del disco duro.
     * @param bdd la base de datos a cargar.
     * @param ruta la ruta en el disco donde cargar la base de datos.
     */
    private void carga(BaseDeDatos bdd, String ruta){
        try {
            anotaMensaje("Cargando base de datos de %s.", ruta);
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                        new FileInputStream(ruta)));
            bdd.carga(in);
            in.close();
            anotaMensaje("Base de datos cargada exitosamente de %s.", ruta);
        } catch (IOException ioe) {
            anotaMensaje("Ocurrio un error al tratar de cargar %s.", ruta);
            anotaMensaje("La base de datos estara inicialmente vacia.");
        }
    }

    /* Guarda la base de datos en el disco duro. */
    private synchronized void guarda() {
        guarda(bddCriticas, rutaCriticas);
        guarda(bddProductos, rutaProductos);
        guarda(bddCarritos,rutaCarritos);
        guarda(bddUsuarios, rutaUsuarios);
        
    }

    /**
     * Guarda la base de datos en el disco duro.
     * @param bdd la base de datos a guardar.
     * @param ruta la ruta en el disco donde guardar la base de datos.
     */
    private synchronized void guarda(BaseDeDatos bdd, String ruta){
        try {
            BufferedWriter out = new BufferedWriter(
                                    new OutputStreamWriter(
                                        new FileOutputStream(ruta)));
            bdd.guarda(out);
            out.close();
            anotaMensaje("Base de datos guardada.");
        } catch (IOException ioe) {
            anotaMensaje("Ocurrio un error al guardar la base de datos.");
        }
    }

    /** 
     * Recibe los mensajes de la conexion. 
     * @param conexion la conexion que envio el mensaje.
     * @param mensaje el mensaje recibido.
     */
    private void mensajeRecibido(Conexion conexion, Mensaje mensaje) {
        if (!conexion.isActiva())
            return;
        switch(mensaje) {
        case BASE_DE_DATOS:
            baseDeDatos(conexion);
            break;
        case REGISTRO_AGREGADO_CARRITO:
            registroAlteradoCarrito(conexion, mensaje);
            break;
        case REGISTRO_ELIMINADO_CARRITO:
            registroAlteradoCarrito(conexion, mensaje);
            break;
        case REGISTRO_MODIFICADO_CARRITO:
            registroModificadoCarrito(conexion);
            break;
        case REGISTRO_AGREGADO_PRODUCTO:
            registroAlteradoProducto(conexion, mensaje);
            break;
        case REGISTRO_ELIMINADO_PRODUCTO:
            registroAlteradoProducto(conexion, mensaje);
            break;
        case REGISTRO_MODIFICADO_PRODUCTO:
            registroModificadoProducto(conexion);
            break;
        case REGISTRO_AGREGADO_CRITICA:
            registroAlteradoCritica(conexion, mensaje);
            break;
        case REGISTRO_ELIMINADO_CRITICA:
            registroAlteradoCritica(conexion, mensaje);
            break;
        case REGISTRO_MODIFICADO_CRITICA:
            registroModificadoCritica(conexion);
            break;
        case DESCONECTAR:
            desconectar(conexion);
            break;
        case GUARDA:
            guarda();
            break;
        case DETENER_SERVICIO:
            detenerServicio();
            break;
        case ECO:
            eco(conexion);
            break;
        case INVALIDO:
            error(conexion, "Mensaje invalido.");
            break;
        default:
            error(conexion, "Mensaje invalido.");
            break;
        }
    }

    /**
     * Maneja los mensajes de REGISTRO_ELIMINADO_PRODUCTO y REGISTRO_AGREGADO_PRODUCTO.
     * @param conexion la conexion que envio el mensaje.
     * @param mensaje el mensaje recibido.
     */
    private void registroAlteradoProducto(Conexion conexion, Mensaje mensaje) {
        Producto registro;
        try {
            registro = (Producto) conexion.recibeRegistro(TiposBaseDeDatos.PRODUCTOS);
        } catch (IOException ioe) {
            error(conexion, "Error recibiendo registro.");
            return;
        }
        String accion = null;
        if (mensaje == Mensaje.REGISTRO_AGREGADO_PRODUCTO) {
            agregaRegistroProducto(registro);
            accion = "agregado";
        } else {
            eliminaRegistroProducto(registro);
            accion = "eliminado";
        }
        for (Conexion c : conexiones) {
            if (c == conexion) continue;
            try {
                c.enviaMensaje(mensaje);
                c.enviaRegistro(registro);
            } catch (IOException ioe) {
                error(c, "Error enviando registro de producto.");
            }
        }
        anotaMensaje("Registro %s por %d.", accion, conexion.getSerie());
        guarda();
    }

    /**
     * Maneja los mensajes de REGISTRO_ELIMINADO_CARRITO y REGISTRO_AGREGADO_CARRITO.
     * @param conexion la conexion que envio el mensaje.
     * @param mensaje el mensaje recibido.
     */
    private void registroAlteradoCarrito(Conexion conexion, Mensaje mensaje) {
        Carrito registro;
        try {
            registro = (Carrito) conexion.recibeRegistro(TiposBaseDeDatos.CARRITOS);
        } catch (IOException ioe) {
            error(conexion, "Error recibiendo registro.");
            return;
        }
        String accion = null;
        if (mensaje == Mensaje.REGISTRO_AGREGADO_CARRITO) {
            agregaRegistroCarrito(registro);
            accion = "agregado";
        } else {
            eliminaRegistroCarrito(registro);
            accion = "eliminado";
        }
        for (Conexion c : conexiones) {
            if (c == conexion) continue;
            try {
                c.enviaMensaje(mensaje);
                c.enviaRegistro(registro);
            } catch (IOException ioe) {
                error(c, "Error enviando registro de carrito.");
            }
        }
        anotaMensaje("Registro %s por %d.", accion, conexion.getSerie());
        guarda();
    }


    /**
     * Maneja los mensajes de REGISTRO_ELIMINADO_CRITICA y REGISTRO_AGREGADO_CRITICA.
     * @param conexion la conexion que envio el mensaje.
     * @param mensaje el mensaje recibido.
     */
    private void registroAlteradoCritica(Conexion conexion, Mensaje mensaje) {
        Critica registro;
        try {
            registro = (Critica) conexion.recibeRegistro(TiposBaseDeDatos.CRITICAS);
        } catch (IOException ioe) {
            error(conexion, "Error recibiendo registro.");
            return;
        }
        String accion = null;
        if (mensaje == Mensaje.REGISTRO_AGREGADO_CRITICA) {
            agregaRegistroCritica(registro);
            accion = "agregado";
        } else {
            eliminaRegistroCritica(registro);
            accion = "eliminado";
        }
        for (Conexion c : conexiones) {
            if (c == conexion) continue;
            try {
                c.enviaMensaje(mensaje);
                c.enviaRegistro(registro);
            } catch (IOException ioe) {
                error(c, "Error enviando registro de critica.");
            }
        }
        anotaMensaje("Registro %s por %d.", accion, conexion.getSerie());
        guarda();
    }

    /**
     *  Agrega el registro a la base de datos de productos. 
     * @param registro el registro a agregar.
     */
    private synchronized void agregaRegistroProducto(Producto registro) {
        bddProductos.agregaRegistro(registro);
    }

    /**
     *  Agrega el registro a la base de datos de criticas. 
     * @param registro el registro a agregar.
     */
    private synchronized void agregaRegistroCritica(Critica registro) {
        bddCriticas.agregaRegistro(registro);
    }

    /**
     * Elimina el registro de la base de datos de productos.
     * @param registro el registro a eliminar.
     */
    private synchronized void eliminaRegistroProducto(Producto registro) {
        bddProductos.eliminaRegistro(registro);
    }

    /**
     * Elimina el registro de la base de datos de criticas.
     * @param registro el registro a eliminar.
     */
    private synchronized void eliminaRegistroCritica(Critica registro) {
        bddCriticas.eliminaRegistro(registro);
    }

    /** 
     * Maneja el mensaje REGISTRO_MODIFICADO_PRODUCTO.
     * @param conexion la conexion que envio el mensaje.
     */
    private void registroModificadoProducto(Conexion conexion) {
	    Producto registro1;
        Producto registro2;
        try {
            registro1 = (Producto) conexion.recibeRegistro(TiposBaseDeDatos.PRODUCTOS);
            registro2 = (Producto) conexion.recibeRegistro(TiposBaseDeDatos.PRODUCTOS);
        } catch (IOException ioe) {
            error(conexion, "Error recibiendo registros.");
            return;
        }
        modificaRegistroProducto(registro1, registro2);
        for (Conexion c : conexiones) {
            if (c == conexion) continue;
            try {
                c.enviaMensaje(Mensaje.REGISTRO_MODIFICADO_PRODUCTO);
                c.enviaRegistro(registro1);
                c.enviaRegistro(registro2);
            } catch (IOException ioe) {
                error(c, "Error enviando registro de producto.");
            }
        }
        anotaMensaje("Registro modificado por %d.", conexion.getSerie());
        guarda();
    }

     /** 
     * Maneja el mensaje REGISTRO_MODIFICADO_CARRITO.
     * @param conexion la conexion que envio el mensaje.
     */
    private void registroModificadoCarrito(Conexion conexion) {
        Carrito registro1;
        Carrito registro2;
        try {
            registro1 = (Carrito) conexion.recibeRegistro(TiposBaseDeDatos.CARRITOS);
            registro2 = (Carrito) conexion.recibeRegistro(TiposBaseDeDatos.CARRITOS);
        } catch (IOException ioe) {
            error(conexion, "Error recibiendo registros.");
            return;
        }
        modificaRegistroCarrito(registro1, registro2);
        for (Conexion c : conexiones) {
            if (c == conexion) continue;
            try {
                c.enviaMensaje(Mensaje.REGISTRO_MODIFICADO_CARRITO);
                c.enviaRegistro(registro1);
                c.enviaRegistro(registro2);
            } catch (IOException ioe) {
                error(c, "Error enviando registro de carrito.");
            }
        }
        anotaMensaje("Registro modificado por %d.", conexion.getSerie());
        guarda();
    }

    /**
     * Maneja el mensaje REGISTRO_MODIFICADO_CRITICA.
     * @param conexion la conexion que envio el mensaje.
     */
    private void registroModificadoCritica(Conexion conexion) {
        Critica registro1;
        Critica registro2;
        try {
            registro1 = (Critica) conexion.recibeRegistro(TiposBaseDeDatos.CRITICAS);
            registro2 = (Critica) conexion.recibeRegistro(TiposBaseDeDatos.CRITICAS);
        } catch (IOException ioe) {
            error(conexion, "Error recibiendo registros.");
            return;
        }
        modificaRegistroCritica(registro1, registro2);
        for (Conexion c : conexiones) {
            if (c == conexion) continue;
            try {
                c.enviaMensaje(Mensaje.REGISTRO_MODIFICADO_CRITICA);
                c.enviaRegistro(registro1);
                c.enviaRegistro(registro2);
            } catch (IOException ioe) {
                error(c, "Error enviando registro critica.");
            }
        }
        anotaMensaje("Registro modificado por %d.", conexion.getSerie());
        guarda();
    }

    /** 
     * Modifica el registro en la base de datos de productos. 
     * @param registro1 el registro original.
     * @param registro2 el registro modificado.
     */
    private synchronized void modificaRegistroProducto(Producto registro1, Producto registro2) {
        bddProductos.modificaRegistro(registro1, registro2);
    }

    /** 
     * Modifica el registro en la base de datos de criticas. 
     * @param registro1 el registro original.
     * @param registro2 el registro modificado.
     */
    private synchronized void modificaRegistroCritica(Critica registro1, Critica registro2) {
        bddCriticas.modificaRegistro(registro1, registro2);
    }

     /** 
     * Modifica el registro en la base de datos de carritos.
     * @param registro1 el registro original.
     * @param registro2 el registro modificado.
     */
    private synchronized void modificaRegistroCarrito(Carrito registro1, Carrito registro2) {
        bddCarritos.modificaRegistro(registro1, registro2);
    }

    /** 
     * Maneja el mensaje BASE_DE_DATOS.
     * @param conexion la conexion que envio el mensaje.
     */
    private void baseDeDatos(Conexion conexion) {
        try {
            conexion.enviaMensaje(Mensaje.BASE_DE_DATOS);
            conexion.enviaBaseDeDatos(TiposBaseDeDatos.CRITICAS);
            conexion.enviaBaseDeDatos(TiposBaseDeDatos.PRODUCTOS);
            conexion.enviaBaseDeDatos(TiposBaseDeDatos.CARRITOS);
            conexion.enviaBaseDeDatos(TiposBaseDeDatos.USUARIOS);
            
        } catch (IOException ioe) {
            error(conexion, "Error enviando la base de datos.");
        }
        anotaMensaje("Base de datos pedida por %d.", conexion.getSerie());
    }

    /**
     * Maneja el mensaje DESCONECTAR.
     * @param conexion la conexion que envio el mensaje.
     */
    private void desconectar(Conexion conexion) {
        anotaMensaje("Solicitud de desconexion de %d.", conexion.getSerie());
        desconecta(conexion);
    }

    /**
     * Maneja el mensaje DETENER_SERVICIO. 
     */
    private void detenerServicio() {
        anotaMensaje("Solicitud de detener servicio.");
        continuaEjecucion = false;
        for (Conexion c : conexiones)
            desconecta(c);
        try {
            servidor.close();
        } catch (IOException ioe) {}
    }

    /**
     * Maneja el mensaje ECO.
     * @param conexion la conexion que envio el mensaje.
     */
    private void eco(Conexion conexion) {
        anotaMensaje("Solicitud de eco de %d.", conexion.getSerie());
        try {
            conexion.enviaMensaje(Mensaje.ECO);
        } catch (IOException ioe) {
            error(conexion, "Error enviando eco.");
        }
    }

    /**
     * Imprime un mensaje a los escuchas y desconecta la conexion. 
     * @param conexion la conexion a desconectar.
     * @param mensaje el mensaje a imprimir.
     */
    private void error(Conexion conexion, String mensaje) {
        anotaMensaje("Desconectando la conexion %d: %s", conexion.getSerie(), mensaje);
        desconecta(conexion);
    }

    /**
     * Elimina una conexion del sujeto de conexiones.
     * @param conexion la conexion a eliminar.
     */
    @Override
    public void desconecta(Conexion conexion) {
        conexion.desconecta();
        synchronized (conexiones) {
            conexiones.remove(conexion);
        }
        anotaMensaje("La conexion %d ha sido desconectada.", conexion.getSerie());
    }

    /** 
     * Agrega el registro a la base de datos.
     * @param registro el registro a agregar.
     */
     private synchronized void agregaRegistroCarrito(Carrito registro) {
         bddCarritos.agregaRegistro(registro);
     }

    /** 
     * Elimina el registro de la base de datos.
     * @param registro el registro a eliminar.
     */
     private synchronized void eliminaRegistroCarrito(Carrito registro) {
         bddCarritos.eliminaRegistro(registro);
     }

    /**
     * Procesa los mensajes de todos los escuchas.
     * @param formato el formato del mensaje.
     * @param argumentos los argumentos del mensaje.
     */
    @Override
    public void anotaMensaje(String formato, Object ... argumentos) {
        for (EscuchaServidor es : escuchas)
            es.procesaMensaje(formato, argumentos);
    }
}