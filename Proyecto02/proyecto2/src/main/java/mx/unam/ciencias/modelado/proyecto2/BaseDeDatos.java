package mx.unam.ciencias.modelado.proyecto2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * <p>Clase abstracta para bases de datos genericas. Provee metodos para agregar y
 * eliminar registros, y para guardarse y cargarse de una entrada y salida
 * dados. Ademas, puede hacer busquedas con valores arbitrarios sobre los campos
 * de los registros.</p>
 *
 * <p>Las clases que extiendan a BaseDeDatos deben implementar el metodo {@link
 * #creaRegistro}, que crea un registro generico en blanco.</p>
 *
 * <p>Las modificaciones a la base de datos son notificadas a los escuchas {@link
 * EscuchaBaseDeDatos}.</p>
 *
 * @param <R> El tipo de los registros, que deben implementar la interfaz {@link
 * Registro}.
 * @param <C> El tipo de los campos de los registros, que debe ser una
 * enumeracion {@link Enum}.
 */
public abstract class BaseDeDatos<R extends Registro<R, C>, C extends Enum<?>> {

    /* Lista de registros en la base de datos. */
    protected LinkedList<R> registros;
    /* Lista de escuchas de la base de datos. */
    protected LinkedList<EscuchaBaseDeDatos<R>> escuchas;

    /**
     * Constructor unico.
     */
    protected BaseDeDatos() {
        registros = new LinkedList<R>();
        escuchas = new LinkedList<EscuchaBaseDeDatos<R>>();
    }

    /**
     * Regresa el numero de registros en la base de datos.
     * @return el numero de registros en la base de datos.
     */
    public int getNumRegistros() {
        return registros.size();
    }

    /**
     * Regresa una lista con los registros en la base de datos. Modificar esta
     * lista no cambia a la informacion en la base de datos.
     * @return una lista con los registros en la base de datos.
     */
    public LinkedList<R> getRegistros() {
        return new LinkedList<R>(registros);
    }

    /**
     * Agrega el registro recibido a la base de datos. Los escuchas son
     * notificados con {@link EscuchaBaseDeDatos#baseDeDatosModificada} con el
     * evento {@link EventoBaseDeDatos#REGISTRO_AGREGADO}.
     * @param registro el registro que hay que agregar a la base de datos.
     */
    public void agregaRegistro(R registro) {
        registros.add(registro);
        escuchas.forEach(ebd -> ebd.baseDeDatosModificada(EventoBaseDeDatos.REGISTRO_AGREGADO, registro, null));
    }

    /**
     * Elimina el registro recibido de la base de datos. Los escuchas son
     * notificados con {@link EscuchaBaseDeDatos#baseDeDatosModificada} con el
     * evento {@link EventoBaseDeDatos#REGISTRO_ELIMINADO}.
     * @param registro el registro que hay que eliminar de la base de datos.
     */
    public void eliminaRegistro(R registro) {
        registros.remove(registro);
        escuchas.forEach(ebd -> ebd.baseDeDatosModificada(EventoBaseDeDatos.REGISTRO_ELIMINADO, registro, null));
    }

    /**
     * Modifica el primer registro en la base de datos para que sea identico al
     * segundo. Antes de modificar el registro, los escuchas son notificados con
     * {@link EscuchaBaseDeDatos#baseDeDatosModificada} con el evento {@link
     * EventoBaseDeDatos#REGISTRO_MODIFICADO} y las versiones original y
     * modificada del registro. Si el primer registro no esta en la base de
     * datos, esta no es modificada y no se notifica de nada a los escuchas.
     * @param registro1 un registro igual al que hay que modificar en la base de
     *                  datos.
     * @param registro2 el registro con los nuevos valores.
     * @throws IllegalArgumentException si registro1 o registro2 son
     *         <code>null</code>.
     */
    public void modificaRegistro(R registro1, R registro2) {
        if (registro1 == null || registro2 == null) throw new IllegalArgumentException("Registros invalidos.");
        int i = registros.indexOf(registro1);
        if (i == -1) return;
        escuchas.forEach(ebd -> ebd.baseDeDatosModificada(EventoBaseDeDatos.REGISTRO_MODIFICADO, registro1, registro2));
        registros.get(i).actualiza(registro2);
    }

    /**
     * Limpia la base de datos. Los escuchas son notificados con {@link
     * EscuchaBaseDeDatos#baseDeDatosModificada} con el evento {@link
     * EventoBaseDeDatos#BASE_LIMPIADA}
     */
    public void limpia() {
        registros.clear();
        escuchas.forEach(ebd -> ebd.baseDeDatosModificada(EventoBaseDeDatos.BASE_LIMPIADA, null, null));
    }

    /**
     * Guarda todos los registros en la base de datos en la salida recibida.
     * @param out la salida donde hay que guardar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void guarda(BufferedWriter out) throws IOException {
        if (out == null) return;
        for (R registro : registros)
            out.write(registro.seria());
    }

    /**
     * Carga los registros de la entrada recibida en la base de datos. Si antes
     * de llamar el metodo habia registros en la base de datos, estos son
     * eliminados. Los escuchas son notificados con {@link
     * EscuchaBaseDeDatos#baseDeDatosModificada} con el evento {@link
     * EventoBaseDeDatos#BASE_LIMPIADA}, y por cada registro cargado con {@link
     * EscuchaBaseDeDatos#baseDeDatosModificada} con el evento {@link
     * EventoBaseDeDatos#REGISTRO_AGREGADO}.
     * @param in la entrada de donde hay que cargar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void carga(BufferedReader in) throws IOException {
        if (in == null) return;
        limpia();
        String linea;
        while((linea = in.readLine()) != null){
            if (linea == null) return;
            String cadena = linea.trim();
            if (cadena.isEmpty()) return;
            R registro = creaRegistro();
            try {
                registro.deseria(cadena);
            } catch(ExcepcionLineaInvalida eli) {
                throw new IOException("Error de entrada.");
            }
            agregaRegistro(registro);
        }
    }

    /**
     * Busca registros por un campo especifico.
     * @param campo el campo del registro por el cual buscar.
     * @param valor el valor a buscar.
     * @return una lista con los registros tales que casan el campo especificado
     *         con el valor dado.
     * @throws IllegalArgumentException si el campo no es de la enumeracion
     *         correcta.
     */
    public LinkedList<R> buscaRegistros(C campo, Object valor) {
        LinkedList<R> resultados = new LinkedList<R>();
        for (R registro : registros)
            if (registro.casa(campo, valor))
                    resultados.add(registro);
        return resultados;
    }

    /**
     * Crea un registro en blanco.
     * @return un registro en blanco.
     */
    public abstract R creaRegistro();

    /**
     * Agrega un escucha a la base de datos.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscucha(EscuchaBaseDeDatos<R> escucha) {
        escuchas.add(escucha);
    }

    /**
     * Elimina un escucha de la base de datos.
     * @param escucha el escucha a eliminar.
     */
    public void eliminaEscucha(EscuchaBaseDeDatos<R> escucha) {
        escuchas.remove(escucha);
    }



}
