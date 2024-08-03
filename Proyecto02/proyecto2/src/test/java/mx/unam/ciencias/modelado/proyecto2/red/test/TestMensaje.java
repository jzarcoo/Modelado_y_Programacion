package mx.unam.ciencias.modelado.proyecto2.red.test;

import mx.unam.ciencias.modelado.proyecto2.red.Mensaje;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link Mensaje}.
 */
public class TestMensaje {

    /** Expiracion para que ninguna prueba tarde mas de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /**
     * Prueba unitaria para {@link Mensaje#toString}.
     */
    @Test public void testToString() {
        String p = "|=MENSAJE:";
        for (Mensaje e : Mensaje.values()) {
            String s = e.toString();
            switch (e) {
            case BASE_DE_DATOS:
                Assert.assertTrue(s.equals(p + "BASE_DE_DATOS"));
                break;
            case REGISTRO_AGREGADO:
                Assert.assertTrue(s.equals(p + "REGISTRO_AGREGADO"));
                break;
            case REGISTRO_ELIMINADO:
                Assert.assertTrue(s.equals(p + "REGISTRO_ELIMINADO"));
                break;
            case REGISTRO_MODIFICADO:
                Assert.assertTrue(s.equals(p + "REGISTRO_MODIFICADO"));
                break;
            case DESCONECTAR:
                Assert.assertTrue(s.equals(p + "DESCONECTAR"));
                break;
            case GUARDA:
                Assert.assertTrue(s.equals(p + "GUARDA"));
                break;
            case DETENER_SERVICIO:
                Assert.assertTrue(s.equals(p + "DETENER_SERVICIO"));
                break;
            case ECO:
                Assert.assertTrue(s.equals(p + "ECO"));
                break;
            case INVALIDO:
                Assert.assertTrue(s.equals(p + "INVALIDO"));
                break;
            }
        }
    }
}