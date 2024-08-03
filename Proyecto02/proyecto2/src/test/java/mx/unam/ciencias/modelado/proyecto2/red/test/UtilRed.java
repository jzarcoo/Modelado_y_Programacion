package mx.unam.ciencias.modelado.proyecto2.red.test;

import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosUsuarios;
import mx.unam.ciencias.modelado.proyecto2.Usuario;
import mx.unam.ciencias.modelado.proyecto2.test.TestUsuario;

/**
 * <p>Clase con metodos estaticos utilitarios para las pruebas unitarias de red.</p>
 */
public class UtilRed {

    /* Evitamos instanciacion. */
    private UtilRed() {}

    /* ID inicial. */
    public static final int ID_INICIAL = 1000000;

    /* Contador de numeros de id. */
    private static int contador;

    /**
     * Espera el numero de milisegundos de forma segura.
     * @param milisegundos el numero de milisegundos a esperar.
     */
    public static void espera(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException ie) {}
    }

    /**
     * Llena una base de datos de usuarios.
     * @param bdd la base de datos a llenar.
     * @param total el total de usuarios.
     */
    public static void llenaBaseDeDatosUsuarios(BaseDeDatosUsuarios bdd, int total) {
        for (int i = 0; i < total; i++) {
            int id = ID_INICIAL + i;
            bdd.agregaRegistro(TestUsuario.usuarioAleatorio(id));
        }
    }

    /**
     * Crea un usuario aleatorio.
     * @param total el total de usuarios.
     * @return un usuario aleatorio con un id unico
     */
    public static Usuario usuarioAleatorio(int total) {
        int id = ID_INICIAL + total*2 + contador++;
        return TestUsuario.usuarioAleatorio(id);
    }

}
