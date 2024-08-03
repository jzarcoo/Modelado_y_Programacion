package mx.unam.ciencias.modelado.proyecto2.test;

import mx.unam.ciencias.modelado.proyecto2.CampoUsuario;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * <p>Clase para pruebas unitarias de la enumeracion {@link CampoUsuario}.</p>
 */
public class TestCampoUsuario {

    /** Expiracion para que ninguna prueba tarde mas de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /**
     * Prueba unitaria para {@link CampoUsuario#toString}.
     */
    @Test public void testToString() {
        String s = CampoUsuario.ID.toString();
        Assert.assertTrue(s.equals("ID"));
        s = CampoUsuario.ROL.toString();
        Assert.assertTrue(s.equals("Rol"));
        s = CampoUsuario.NOMBRE_USUARIO.toString();
        Assert.assertTrue(s.equals("Nombre de Usuario"));
        s = CampoUsuario.CONTRASENA.toString();
        Assert.assertTrue(s.equals("Contrasena"));
        s = CampoUsuario.TELEFONO.toString();
        Assert.assertTrue(s.equals("Telefono"));
        s = CampoUsuario.NUMERO_CUENTA.toString();
        Assert.assertTrue(s.equals("Numero de Cuenta"));
    }
}
