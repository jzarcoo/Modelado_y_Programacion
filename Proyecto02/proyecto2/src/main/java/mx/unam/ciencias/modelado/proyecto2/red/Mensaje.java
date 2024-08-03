package mx.unam.ciencias.modelado.proyecto2.red;

/**
 * <p>Enumeracion para los mensajes que se transmiten en una conexion entre el
 * servidor y el cliente de bases de datos.</p>
 */
public enum Mensaje {

    /**
     * Si el servidor recibe este mensaje, contesta enviando toda la base de
     * datos. Si el cliente recibe este mensaje, entonces comienza a cargar la
     * base de datos.
     */
    BASE_DE_DATOS,

    /**
     * El interlocutor agrego un registro. Si este mensaje es recibido por el
     * servidor o el cliente, inmediatamente despues recibira un registro que
     * debe agregar a la base de datos.
     */
    REGISTRO_AGREGADO,

    /**
     * El interlocutor agrego un registro. Si este mensaje es recibido por el
     * servidor o el cliente, inmediatamente despues recibira un registro que
     * debe agregar a la base de datos y editar al cliente.
     */
    REGISTRO_AGREGADO_CARRITO,

    /**
     * El interlocutor agrego un registro. Si este mensaje es recibido por el
     * servidor o el cliente, inmediatamente despues recibira un registro que
     * debe agregar a la base de datos de productos y editar al vendedor.
     */
    REGISTRO_AGREGADO_PRODUCTO,

    /**
     * El interlocutor agrego un registro. Si este mensaje es recibido por el
     * servidor o el cliente, inmediatamente despues recibira un registro que
     * debe agregar a la base de datos de criticas.
     */
    REGISTRO_AGREGADO_CRITICA,

    /**
     * El interlocutor elimino un registro. Si este mensaje es recibido por el
     * servidor o el cliente, inmediatamente despues recibira un registro que
     * debe eliminar de la base de datos.
     */
    REGISTRO_ELIMINADO,

    /**
     * El interlocutor elimino un registro. Si este mensaje es recibido por el
     * servidor o el cliente, inmediatamente despues recibira un registro que
     * debe eliminar de la base de datos y editar al cliente.
     */
    REGISTRO_ELIMINADO_CARRITO,

    /**
     * El interlocutor elimino un registro. Si este mensaje es recibido por el
     * servidor o el cliente, inmediatamente despues recibira un registro que
     * debe eliminar de la base de datos de criticas.
     */
    REGISTRO_ELIMINADO_CRITICA,

    /**
     * El interlocutor elimino un registro. Si este mensaje es recibido por el
     * servidor o el cliente, inmediatamente despues recibira un registro que
     * debe eliminar de la base de datos de productos y editar al vendedor.
     */
    REGISTRO_ELIMINADO_PRODUCTO,

    /**
     * El interlocutor modifico un registro. Si este mensaje es recibido por el
     * servidor o el cliente, inmediatamente despues recibira dos registros: el
     * primero sera el registro original, y el segundo el registro modificado.
     */
    REGISTRO_MODIFICADO,

    /**
     * El interlocutor modifico un registro del tipo producto. Si este mensaje es recibido por el
     * servidor o el cliente, inmediatamente despues recibira dos registros: el
     * primero sera el registro original, y el segundo el registro modificado.
     */
    REGISTRO_MODIFICADO_PRODUCTO,

    /**
     * El interlocutor modifico un registro del tipo carrito. Si este mensaje es recibido por el
     * servidor o el cliente, inmediatamente despues recibira dos registros: el
     * primero sera el registro original, y el segundo el registro modificado.
     */
    REGISTRO_MODIFICADO_CARRITO,


    /**
     * El interlocutor modifico un registro del tipo critica. Si este mensaje es recibido por el
     * servidor o el cliente, inmediatamente despues recibira dos registros: el
     * primero sera el registro original, y el segundo el registro modificado.
     */
    REGISTRO_MODIFICADO_CRITICA,

    /**
     * El interlocutor solicita una desconexion limpia. Tanto el servidor como
     * cliente cierran la conexion correspondiente.
     */
    DESCONECTAR,

    /**
     * El servidor debe guardar la base de datos en el disco duro. Los clientes
     * ignoran este mensaje.
     */
    GUARDA,

    /**
     * El servidor debe detenerse, desconectando a todos los clientes que
     * pudieran estar conectados. Los clientes ignoran este mensaje.
     */
    DETENER_SERVICIO,

    /**
     * Mensaje de eco. El servidor debe regresar el mensaje de eco cuando reciba
     * el mensaje de eco; el cliente debe ignorarlo. Es necesario para poder
     * probar el servidor.
     */
    ECO,

    /**
     * El mensaje no es reconocido.
     */
    INVALIDO;

    /* El prefijo para mensajes. */
    private static final String PREFIJO = "|=MENSAJE:";

    /**
     * Descifra un cadena recibida y la traduce a un mensaje.
     * @param mensaje la cadena de texto con el mensaje. La cadena recibida debe
     *        comenzar con "|=MENSAJE:", seguido del mensaje, de otra forma se le
     *        considerara invalido.
     * @return el mensaje correspondiente a la linea.
     */
    public static Mensaje getMensaje(String mensaje) {
        if (!mensaje.startsWith(PREFIJO)) return INVALIDO;
	    String msj = mensaje.replace(PREFIJO, "");
	    for(Mensaje m : Mensaje.values())
	        if (msj.equals(m.name())) return m;
	    return INVALIDO;
    }

    /**
     * Genera una cadena con un mensaje valido para {@link getMensaje}.
     * @return la cadena con el mensaje correspondiente al mensaje.
     */
    @Override public String toString() {
	    return PREFIJO + super.name();
    }
}
