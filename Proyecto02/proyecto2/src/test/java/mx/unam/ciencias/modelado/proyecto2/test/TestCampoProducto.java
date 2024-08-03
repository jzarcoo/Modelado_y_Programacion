package mx.unam.ciencias.modelado.proyecto2.test;

import mx.unam.ciencias.modelado.proyecto2.CampoProducto;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * <p>Clase para pruebas unitarias de la enumeracion {@link CampoProducto}.</p>
 */
public class TestCampoProducto {

    /** Expiracion para que ninguna prueba tarde mas de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /**
     * Prueba unitaria para {@link CampoProducto#toString}.
     */
    @Test public void testToString() {
        String s = CampoProducto.ID_VENDEDOR.toString();
        Assert.assertTrue(s.equals("ID Vendedor"));
        s = CampoProducto.CODIGO_DE_BARRAS.toString();
        Assert.assertTrue(s.equals("Codigo de Barras"));
        s = CampoProducto.NOMBRE.toString();
        Assert.assertTrue(s.equals("Nombre"));
        s = CampoProducto.DESCRIPCION.toString();
        Assert.assertTrue(s.equals("Descripcion"));
        s = CampoProducto.PRECIO.toString();
        Assert.assertTrue(s.equals("Precio"));
        s = CampoProducto.STOCK_DISPONIBLES.toString();
        Assert.assertTrue(s.equals("Disponibles"));
        s = CampoProducto.CATEGORIA.toString();
        Assert.assertTrue(s.equals("Categoria"));
    }
}
