package mx.unam.ciencias.modelado.proyecto2.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.Random;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatos;
import mx.unam.ciencias.modelado.proyecto2.BaseDeDatosUsuarios;
import mx.unam.ciencias.modelado.proyecto2.CampoUsuario;
import mx.unam.ciencias.modelado.proyecto2.Usuario;
import mx.unam.ciencias.modelado.proyecto2.EscuchaBaseDeDatos;
import mx.unam.ciencias.modelado.proyecto2.EventoBaseDeDatos;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * <p>Clase para pruebas unitarias de la clase {@link BaseDeDatosUsuarios}.</p>
 */
public class TestBaseDeDatosUsuarios {

    /** Expiracion para que ninguna prueba tarde mas de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Generador de numeros aleatorios. */
    private Random random;
    /* Base de datos de Usuarios. */
    private BaseDeDatosUsuarios bdd;
    /* Numero total de Usuarios. */
    private int total;

    /**
     * Crea un generador de numeros aleatorios para cada prueba y una base de
     * datos de Usuarios.
     */
    public TestBaseDeDatosUsuarios() {
        random = new Random();
        bdd = new BaseDeDatosUsuarios();
        total = 2 + random.nextInt(100);
    }

    /**
     * Prueba unitaria para {@link
     * BaseDeDatosUsuarios#BaseDeDatosUsuarios}.
     */
    @Test public void testConstructor() {
        LinkedList<Usuario> usuarios = bdd.getRegistros();
        Assert.assertFalse(usuarios == null);
        Assert.assertTrue(usuarios.size() == 0);
        Assert.assertTrue(bdd.getNumRegistros() == 0);
        bdd.agregaEscucha((e, r1, r2) -> {});
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getNumRegistros}.
     */
    @Test public void testGetNumRegistros() {
        Assert.assertTrue(bdd.getNumRegistros() == 0);
        for (int i = 0; i < total; i++) {
            Usuario u = TestUsuario.usuarioAleatorio();
            bdd.agregaRegistro(u);
            Assert.assertTrue(bdd.getNumRegistros() == i+1);
        }
        Assert.assertTrue(bdd.getNumRegistros() == total);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getRegistros}.
     */
    @Test public void testGetRegistros() {
        LinkedList<Usuario> l = bdd.getRegistros();
        LinkedList<Usuario> r = bdd.getRegistros();
        Assert.assertTrue(l.equals(r));
        Assert.assertFalse(l == r);
        Usuario[] usuarios = new Usuario[total];
        for (int i = 0; i < total; i++) {
            usuarios[i] = TestUsuario.usuarioAleatorio();
            bdd.agregaRegistro(usuarios[i]);
        }
        l = bdd.getRegistros();
        int c = 0;
        for (Usuario u : l)
            Assert.assertTrue(usuarios[c++].equals(u));
        l.remove(usuarios[0]);
        Assert.assertFalse(l.equals(bdd.getRegistros()));
        Assert.assertFalse(l.size() == bdd.getNumRegistros());
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#agregaRegistro}.
     */
    @Test public void testAgregaRegistro() {
        for (int i = 0; i < total; i++) {
            Usuario u = TestUsuario.usuarioAleatorio();
            Assert.assertFalse(bdd.getRegistros().contains(u));
            bdd.agregaRegistro(u);
            Assert.assertTrue(bdd.getRegistros().contains(u));
            LinkedList<Usuario> l = bdd.getRegistros();
            Assert.assertTrue(l.get(l.size() - 1).equals(u));
        }
        boolean[] llamado =  { false };
        bdd.agregaEscucha((u, r1, r2) -> {
                Assert.assertTrue(u == EventoBaseDeDatos.REGISTRO_AGREGADO);
                Assert.assertTrue(r1.equals(new Usuario(1, "A", "A", "A", "A", "A")));
                Assert.assertTrue(r2 == null);
                llamado[0] = true;
            });
        bdd.agregaRegistro(new Usuario(1, "A", "A", "A", "A", "A"));
        Assert.assertTrue(llamado[0]);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#eliminaRegistro}.
     */
    @Test public void testeliminaRegistro() {
        int ini = random.nextInt(1000000);
        for (int i = 0; i < total; i++) {
            Usuario u = TestUsuario.usuarioAleatorio(ini + i);
            bdd.agregaRegistro(u);
        }
        while (bdd.getNumRegistros() > 0) {
            int i = random.nextInt(bdd.getNumRegistros());
            Usuario u = bdd.getRegistros().get(i);
            Assert.assertTrue(bdd.getRegistros().contains(u));
            bdd.eliminaRegistro(u);
            Assert.assertFalse(bdd.getRegistros().contains(u));
        }
        boolean[] llamado = { false };
        Usuario usuario = new Usuario(1, "A", "A", "A", "A", "A");
        bdd.agregaRegistro(usuario);
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.REGISTRO_ELIMINADO);
                Assert.assertTrue(r1.equals(new Usuario(1, "A", "A", "A", "A", "A")));
                Assert.assertTrue(r2 == null);
                llamado[0] = true;
            });
        bdd.eliminaRegistro(usuario);
        Assert.assertTrue(llamado[0]);
        bdd = new BaseDeDatosUsuarios();
        llamado[0] = false;
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.REGISTRO_ELIMINADO);
                Assert.assertTrue(r1.equals(new Usuario(1, "A", "A", "A", "A", "A")));
                Assert.assertTrue(r2 == null);
                llamado[0] = true;
            });
        bdd.eliminaRegistro(usuario);
        Assert.assertTrue(llamado[0]);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#modificaRegistro}.
     */
    @Test public void testModificaRegistro() {
        for (int i = 0; i < total; i++) {
            Usuario u = TestUsuario.usuarioAleatorio(total + i);
            Assert.assertFalse(bdd.getRegistros().contains(u));
            bdd.agregaRegistro(u);
            Assert.assertTrue(bdd.getRegistros().contains(u));
            LinkedList<Usuario> l = bdd.getRegistros();
            Assert.assertTrue(l.get(l.size() - 1).equals(u));
        }
        Usuario a = new Usuario(1, "A", "A", "A", "A", "A");
        Usuario b = new Usuario(2, "B", "B", "B", "B", "B");
        bdd.agregaRegistro(a);
        boolean[] llamado = { false };
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.REGISTRO_MODIFICADO);
                Assert.assertTrue(r1.equals(new Usuario(1, "A", "A", "A", "A", "A")));
                Assert.assertTrue(r2.equals(new Usuario(2, "B", "B", "B", "B", "B")));
                llamado[0] = true;
            });
        Usuario c = new Usuario(1, "A", "A", "A", "A", "A");
        bdd.modificaRegistro(c, b);
        Assert.assertTrue(llamado[0]);
        Assert.assertTrue(c.equals(new Usuario(1, "A", "A", "A", "A", "A")));
        Assert.assertTrue(b.equals(new Usuario(2, "B", "B", "B", "B", "B")));
        int ca = 0;
        int cb = 0;
        for (Usuario u : bdd.getRegistros()) {
            ca += u.equals(c) ? 1 : 0;
            cb += u.equals(b) ? 1 : 0;
        }
        Assert.assertTrue(ca == 0);
        Assert.assertTrue(cb == 1);
        bdd = new BaseDeDatosUsuarios();
        a = new Usuario(1, "A", "A", "A", "A", "A");
        b = new Usuario(2, "B", "B", "B", "B", "B");
        bdd.agregaRegistro(a);
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.REGISTRO_MODIFICADO);
                Assert.assertTrue(r1.equals(new Usuario(1, "A", "A", "A", "A", "A")));
                Assert.assertTrue(r2.equals(new Usuario(2, "B", "B", "B", "B", "B")));
                llamado[0] = true;
            });
        bdd.modificaRegistro(a, b);
        Assert.assertTrue(a.equals(b));
        Assert.assertTrue(llamado[0]);
        bdd = new BaseDeDatosUsuarios();
        llamado[0] = false;
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.REGISTRO_MODIFICADO);
                llamado[0] = true;
            });
        bdd.modificaRegistro(new Usuario(1, "A", "A", "A", "A", "A"),
                             new Usuario(2, "B", "B", "B", "B", "B"));
        Assert.assertFalse(llamado[0]);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#limpia}.
     */
    @Test public void testLimpia() {
        for (int i = 0; i < total; i++) {
            Usuario u = TestUsuario.usuarioAleatorio();
            bdd.agregaRegistro(u);
        }
        boolean[] llamado = { false };
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.BASE_LIMPIADA);
                Assert.assertTrue(r1 == null);
                Assert.assertTrue(r2 == null);
                llamado[0] = true;
            });
        LinkedList<Usuario> registros = bdd.getRegistros();
        Assert.assertFalse(registros.isEmpty());
        Assert.assertFalse(registros.size() == 0);
        bdd.limpia();
        registros = bdd.getRegistros();
        Assert.assertTrue(registros.isEmpty());
        Assert.assertTrue(registros.size() == 0);
        Assert.assertTrue(llamado[0]);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#guarda}.
     */
    @Test public void testGuarda() {
        for (int i = 0; i < total; i++) {
            Usuario u = TestUsuario.usuarioAleatorio();
            bdd.agregaRegistro(u);
        }
        String guardado = "";
        try {
            StringWriter swOut = new StringWriter();
            BufferedWriter out = new BufferedWriter(swOut);
            bdd.guarda(out);
            out.close();
            guardado = swOut.toString();
        } catch (IOException ioe) {
            Assert.fail();
        }
        String[] lineas = guardado.split("\n");
        Assert.assertTrue(lineas.length == total);
        LinkedList<Usuario> l = bdd.getRegistros();
        int c = 0;
        for (Usuario u : l) {
            String ul = String.format("%d\t%s\t%s\t%s\t%s\t%s",
                                        u.getId(), 
                                        u.getRol(), 
                                        u.getNombreUsuario(), 
                                        u.getContrasena(), 
                                        u.getTelefono(),
                                        u.getNumeroCuenta());
            Assert.assertTrue(lineas[c++].equals(ul));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#carga}.
     */
    @Test public void testCarga() {
        int ini = random.nextInt(1000000);
        String entrada = "";
        Usuario[] usuarios = new Usuario[total];
        for (int i = 0; i < total; i++) {
            usuarios[i] = TestUsuario.usuarioAleatorio(ini + i);
            entrada += String.format("%d\t%s\t%s\t%s\t%s\t%s%n",
                                    usuarios[i].getId(), 
                                    usuarios[i].getRol(), 
                                    usuarios[i].getNombreUsuario(), 
                                    usuarios[i].getContrasena(), 
                                    usuarios[i].getTelefono(),
                                    usuarios[i].getNumeroCuenta());
            bdd.agregaRegistro(usuarios[i]);
        }
        int[] contador = { 0 };
        boolean[] llamado = { false };
        bdd.agregaEscucha((e, r1, r2) -> {
                if (e == EventoBaseDeDatos.BASE_LIMPIADA)
                    llamado[0] = true;
                if (e == EventoBaseDeDatos.REGISTRO_AGREGADO) {
                    contador[0] ++;
                    Assert.assertTrue(r1 != null);
                    Assert.assertTrue(r2 == null);
                }
            });
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        LinkedList<Usuario> l = bdd.getRegistros();
        Assert.assertTrue(l.size() == total);
        int c = 0;
        for (Usuario u : l)
            Assert.assertTrue(usuarios[c++].equals(u));
        Assert.assertTrue(llamado[0]);
        Assert.assertTrue(contador[0] == total);
        contador[0] = 0;
        llamado[0] = false;
        entrada = String.format("%d\t%s\t%s\t%s\t%s\t%s%n",
                                usuarios[0].getId(), 
                                usuarios[0].getRol(), 
                                usuarios[0].getNombreUsuario(), 
                                usuarios[0].getContrasena(), 
                                usuarios[0].getTelefono(),
                                usuarios[0].getNumeroCuenta());
        entrada += " \n";
        entrada +=  String.format("%d\t%s\t%s\t%s\t%s\t%s%n",
                                usuarios[1].getId(), 
                                usuarios[1].getRol(), 
                                usuarios[1].getNombreUsuario(), 
                                usuarios[1].getContrasena(), 
                                usuarios[1].getTelefono(),
                                usuarios[1].getNumeroCuenta());
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == 1);
        Assert.assertTrue(llamado[0]);
        Assert.assertTrue(contador[0] == 1);
        entrada = "";
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosUsuarios#creaRegistro}.
     */
    @Test public void testCreaRegistro() {
        Usuario u = bdd.creaRegistro();
        Assert.assertTrue(u != null);
        Assert.assertTrue(u.getId() == 0);
        Assert.assertTrue(u.getRol() == null);
        Assert.assertTrue(u.getNombreUsuario() == null);
        Assert.assertTrue(u.getContrasena() == null);
        Assert.assertTrue(u.getTelefono() == null);
        Assert.assertTrue(u.getNumeroCuenta() == null);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosUsuarios#buscaRegistros}.
     */
    @Test public void testBuscaRegistros() {
        Usuario[] usuarios = new Usuario[total];
	    int ini = 1000000 + random.nextInt(999999);
        for (int i = 0; i < total; i++) {
            Usuario u = new Usuario(ini+i, 
                                    String.valueOf(ini+i),
                                    String.valueOf(ini+i), 
                                    String.valueOf(ini+i),
                                    String.valueOf(ini+i),
                                    String.valueOf(ini+i));
            usuarios[i] = u;
            bdd.agregaRegistro(u);
        }
        Usuario usuario;
        LinkedList<Usuario>  l;
        int i;
        for (int k = 0; k < total/10 + 3; k++) {
            i = random.nextInt(total);
            usuario = usuarios[i];
            
            int id = usuario.getId();
            l = bdd.buscaRegistros(CampoUsuario.ID, id);
            Assert.assertTrue(l.size() > 0);
            Assert.assertTrue(l.contains(usuario));
            for (Usuario u : l)
                Assert.assertTrue(u.getId() >= id);
            int bi = id - 10;
            l = bdd.buscaRegistros(CampoUsuario.ID, bi);
            Assert.assertTrue(l.size() > 0);
            Assert.assertTrue(l.contains(usuario));
            for (Usuario u : l)
                Assert.assertTrue(u.getId() >= bi);

            String rol = usuario.getRol();
            l = bdd.buscaRegistros(CampoUsuario.ROL, rol);
            Assert.assertTrue(l.size() > 0);
            Assert.assertTrue(l.contains(usuario));
            for (Usuario u : l)
                Assert.assertTrue(u.getRol().indexOf(rol) > -1);
            int n = rol.length();
            String br = rol.substring(random.nextInt(2),
                                      2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoUsuario.ROL, br);
            Assert.assertTrue(l.size() > 0);
            Assert.assertTrue(l.contains(usuario));
            for (Usuario u : l)
                Assert.assertTrue(u.getRol().indexOf(br) > -1);

            String nombreUsuario = usuario.getNombreUsuario();
            l = bdd.buscaRegistros(CampoUsuario.NOMBRE_USUARIO, nombreUsuario);
            Assert.assertTrue(l.size() > 0);
            Assert.assertTrue(l.contains(usuario));
            for (Usuario u : l)
                Assert.assertTrue(u.getNombreUsuario().indexOf(nombreUsuario) > -1);
            n = nombreUsuario.length();
            String bn = nombreUsuario.substring(random.nextInt(2),
                                               2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoUsuario.NOMBRE_USUARIO, bn);
            Assert.assertTrue(l.size() > 0);
            Assert.assertTrue(l.contains(usuario));
            for (Usuario u : l)
                Assert.assertTrue(u.getNombreUsuario().indexOf(bn) > -1);

            String contrasena = usuario.getContrasena();
            l = bdd.buscaRegistros(CampoUsuario.CONTRASENA, contrasena);
            Assert.assertTrue(l.size() > 0);
            Assert.assertTrue(l.contains(usuario));
            for (Usuario u : l)
                Assert.assertTrue(u.getContrasena().indexOf(contrasena) > -1);
            n = contrasena.length();
            String bc = contrasena.substring(random.nextInt(2),
                                            2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoUsuario.CONTRASENA, bc);
            Assert.assertTrue(l.size() > 0);
            Assert.assertTrue(l.contains(usuario));
            for (Usuario u : l)
                Assert.assertTrue(u.getContrasena().indexOf(bc) > -1);

            String telefono = usuario.getTelefono();
            l = bdd.buscaRegistros(CampoUsuario.TELEFONO, telefono);
            Assert.assertTrue(l.size() > 0);
            Assert.assertTrue(l.contains(usuario));
            for (Usuario u : l)
                Assert.assertTrue(u.getTelefono().indexOf(telefono) > -1);
            n = telefono.length();
            String bt = telefono.substring(random.nextInt(2),
                                         2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoUsuario.TELEFONO, bt);
            Assert.assertTrue(l.size() > 0);
            Assert.assertTrue(l.contains(usuario));
            for (Usuario u : l)
                Assert.assertTrue(u.getTelefono().indexOf(bt) > -1);

            String numeroCuenta = usuario.getNumeroCuenta();
            l = bdd.buscaRegistros(CampoUsuario.NUMERO_CUENTA, numeroCuenta);
            Assert.assertTrue(l.size() > 0);
            Assert.assertTrue(l.contains(usuario));
            for (Usuario u : l)
                Assert.assertTrue(u.getNumeroCuenta().indexOf(numeroCuenta) > -1);
            n = numeroCuenta.length();
            String bnc = numeroCuenta.substring(random.nextInt(2),
                                             2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoUsuario.NUMERO_CUENTA, bnc);
            Assert.assertTrue(l.size() > 0);
            Assert.assertTrue(l.contains(usuario));
            for (Usuario u : l)
                Assert.assertTrue(u.getNumeroCuenta().indexOf(bnc) > -1);
        }

        l = bdd.buscaRegistros(CampoUsuario.ID, 9123456);
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.ROL, "xxx-rol");
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.NOMBRE_USUARIO, "xxx-nombreUsuario");
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.CONTRASENA, "xxx-contrasena");
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.TELEFONO, "xxx-telefono");
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.NUMERO_CUENTA, "xxx-numeroCuenta");
        Assert.assertTrue(l.isEmpty());

        l = bdd.buscaRegistros(CampoUsuario.ID, Integer.MAX_VALUE);
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.ROL, "");
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.NOMBRE_USUARIO, "");
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.CONTRASENA, "");
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.TELEFONO, "");
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.NUMERO_CUENTA, "");
        Assert.assertTrue(l.isEmpty());

        l = bdd.buscaRegistros(CampoUsuario.ID, null);
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.ROL, null);
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.NOMBRE_USUARIO, null);
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.CONTRASENA, null);
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.TELEFONO, null);
        Assert.assertTrue(l.isEmpty());
        l = bdd.buscaRegistros(CampoUsuario.NUMERO_CUENTA, null);
        Assert.assertTrue(l.isEmpty());

        try {
            l = bdd.buscaRegistros(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#agregaEscucha}.
     */
    @Test public void testAgregaEscucha() {
        int[] c = new int[total];
        for (int i = 0; i < total; i++) {
            final int j = i;
            bdd.agregaEscucha((e, r1, r2) -> c[j]++);
        }
        bdd.agregaRegistro(new Usuario(1, "A", "A", "A", "A", "A"));
        for (int i = 0; i < total; i++)
            Assert.assertTrue(c[i] == 1);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#eliminaEscucha}.
     */
    @Test public void testEliminaEscucha() {
        int[] c = new int[total];
        LinkedList<EscuchaBaseDeDatos<Usuario>> escuchas = new LinkedList<EscuchaBaseDeDatos<Usuario>>();
        for (int i = 0; i < total; i++) {
            final int j = i;
            EscuchaBaseDeDatos<Usuario> escucha = (e, r1, r2) -> c[j]++;
            bdd.agregaEscucha(escucha);
            escuchas.add(escucha);
        }
        int i = 0;
        while (!escuchas.isEmpty()) {
            bdd.agregaRegistro(TestUsuario.usuarioAleatorio(i++));
            EscuchaBaseDeDatos<Usuario> escucha = escuchas.removeFirst();
            bdd.eliminaEscucha(escucha);
        }
        for (i = 0; i < total; i++)
            Assert.assertTrue(c[i] == i + 1);
    }
}
