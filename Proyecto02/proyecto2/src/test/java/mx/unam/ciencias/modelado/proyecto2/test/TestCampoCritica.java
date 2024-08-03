package mx.unam.ciencias.modelado.proyecto2.test;

import mx.unam.ciencias.modelado.proyecto2.CampoCritica;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * <p>Clase para pruebas unitarias de la enumeracion {@link CampoCritica}.</p>
 */
public class TestCampoCritica {

    /** Expiracion para que ninguna prueba tarde mas de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /**
     * Prueba unitaria para {@link CampoCritica#toString}.
     */
    @Test public void testToString() {
        String s = CampoCritica.ID.toString();
        Assert.assertTrue(s.equals("ID"));
        s = CampoCritica.ID_VENDEDOR.toString();
        Assert.assertTrue(s.equals("ID Vendedor"));
        s = CampoCritica.ID_CLIENTE.toString();
        Assert.assertTrue(s.equals("ID Cliente"));
        s = CampoCritica.PUNTUACION.toString();
        Assert.assertTrue(s.equals("Puntuacion"));
        s = CampoCritica.COMENTARIO.toString();
        Assert.assertTrue(s.equals("Comentario"));
        s = CampoCritica.REPORTE.toString();
        Assert.assertTrue(s.equals("Reporte"));
    }
}
