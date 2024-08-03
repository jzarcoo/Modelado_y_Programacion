package mx.unam.ciencias.modelado.proyecto2.test;

import java.util.Random;
import mx.unam.ciencias.modelado.proyecto2.CampoUsuario;
import mx.unam.ciencias.modelado.proyecto2.Usuario;
import mx.unam.ciencias.modelado.proyecto2.ExcepcionLineaInvalida;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * <p>Clase para pruebas unitarias de la clase {@link Usuario}.</p>
 */
public class TestUsuario {

    /** Expiracion para que ninguna prueba tarde mas de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Roles. */
    private static final String[] ROLES = {
        "vendedor", "cliente", "administrador"
    };
     
    /* Nombres. */
    private static final String[] NOMBRES = {
        "MarketMaster", "ShopperSavvy", "ProductPro", "ServiceSavvy", "DealDynamo",
        "BargainBuddy", "CartCaptain", "CheckoutChamp", "ClickCommerce", "CyberCustomer",
        "ShopaholicSage", "eBuyEnthusiast", "TradeTreasure"
    };

    /* Contrasenas. */
    private static final String[] CONTRASENAS = {
        "P@ssw0rd", "Secr3t!", "123456", "qwerty", "password123",
        "letmein", "admin", "abc123", "iloveyou", "welcome"
    };

    /* Numeros de telefono de 10 digitos. */
    private static final String[] TELEFONOS = {
        "5551234567", "5559876543", "5558765432", "5552345678", "5553456789",
        "5554567890", "5555678901", "5556789012", "5557890123", "5558901234"
    };

    /* Numero de cuenta aleatorios */
    private static final String[] NUMEROS_CUENTA = {
        "1234567890123456", "2345678901234567", "3456789012345678", "4567890123456789", "5678901234567890",
        "6789012345678901", "7890123456789012", "8901234567890123", "9012345678901234", "0123456789012345"
    };

    /* Generador de numeros aleatorios. */
    private static Random random;

    /**
     * Genera un rol aleatorio.
     * @return un rol aleatorio.
     */
    public static String rolAleatorio() {
        int n = random.nextInt(ROLES.length);
        return ROLES[n];
    }

    /**
     * Genera un nombre aleatorio.
     * @return un nombre aleatorio.
     */
    public static String nombreAleatorio() {
        int n = random.nextInt(NOMBRES.length);
        return NOMBRES[n];
    }

    /**
     * Genera una contrasena aleatoria.
     * @return una contrasena aleatoria.
     */
    public static String contrasenaAleatoria() {
        int n = random.nextInt(CONTRASENAS.length);
        return CONTRASENAS[n];
    }

    /**
     * Genera un numero de telefono aleatorio.
     * @return un numero de telefono aleatorio.
     */
    public static String telefonoAleatorio() {
        int n = random.nextInt(TELEFONOS.length);
        return TELEFONOS[n];
    }

    /**
     * Genera un numero de cuenta aleatorio.
     * @return un numero de cuenta aleatorio.
     */
    public static String numeroCuentaAleatorio() {
        int n = random.nextInt(NUMEROS_CUENTA.length);
        return NUMEROS_CUENTA[n];
    }
    
    /**
     * Genera un id de usuario aleatorio.
     * @return un id de usuario aleatorio.
     */
    public static int idAleatorio() {
        return 50 + random.nextInt(70);
    }

    /**
     * Genera un Usuario aleatorio.
     * @return un Usuario aleatorio.
     */
    public static Usuario usuarioAleatorio() {
        return new Usuario(idAleatorio(),
    			            rolAleatorio(),
    			            nombreAleatorio(),
     			            contrasenaAleatoria(),
    			            telefonoAleatorio(),
                            numeroCuentaAleatorio());
    }
    
    /**
     * Genera un Usuario aleatorio con un id dado.
     * @param id el id del usuario.
     * @return un Usuario aleatorio.
     */
    public static Usuario usuarioAleatorio(int id) {
        return new Usuario(id,
    			            rolAleatorio(),
    			            nombreAleatorio(),
     			            contrasenaAleatoria(),
    			            telefonoAleatorio(),
                            numeroCuentaAleatorio());
    }

    /* El Usuario. */
    private Usuario usuario;

    /**
     * Prueba unitaria para {@link
     * Usuario#Usuario(int,String,Strign,String,String)}.
     */
    @Test public void testConstructor() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertTrue(usuario.getId() == id);
        Assert.assertTrue(usuario.getRol().equals(rol));
        Assert.assertTrue(usuario.getNombreUsuario().equals(nombre));
        Assert.assertTrue(usuario.getContrasena().equals(contrasena));
        Assert.assertTrue(usuario.getTelefono().equals(telefono));
        Assert.assertTrue(usuario.getNumeroCuenta().equals(numeroCuenta));
    }

    /**
     * Prueba unitaria para {@link Usuario#getId}.
     */
    @Test public void testGetId() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertTrue(usuario.getId() == id);
        Assert.assertFalse(usuario.getId() == id + 100);
    }

    /**
     * Prueba unitaria para {@link Usuario#setId}.
     */
    @Test public void testSetId() {
        int id = idAleatorio();
        int nuevoId = id + 100;
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertTrue(usuario.getId() == id);
        Assert.assertFalse(usuario.getId() == nuevoId);
        usuario.setId(nuevoId);
        Assert.assertFalse(usuario.getId() == id);
        Assert.assertTrue(usuario.getId() == nuevoId);
    }

    /**
     * Prueba unitaria para {@link Usuario#idProperty}.
     */
    @Test public void testIdProperty() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertTrue(usuario.idProperty().get() == id);
    }

    /**
     * Prueba unitaria para {@link Usuario#getRol}.
     */
    @Test public void testGetRol() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
	    Assert.assertTrue(usuario.getRol().equals(rol));
        Assert.assertFalse(usuario.getRol().equals(rol + " X"));
    }

    /**
     * Prueba unitaria para {@link Usuario#setRol}.
     */
    @Test public void testSetRol() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String nuevoRol = rol + " X";
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
	    Assert.assertTrue(usuario.getRol().equals(rol));
        Assert.assertFalse(usuario.getRol().equals(nuevoRol));
        usuario.setRol(nuevoRol);
        Assert.assertFalse(usuario.getRol().equals(rol));
        Assert.assertTrue(usuario.getRol().equals(nuevoRol));
    }

    /**
     * Prueba unitaria para {@link Usuario#rolProperty}.
     */
    @Test public void testRolProperty() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertTrue(usuario.rolProperty().get().equals(rol));
    }

    /**
     * Prueba unitaria para {@link Usuario#getNombreUsuario}.
     */
    @Test public void testGetNombreUsuario() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
	    Assert.assertTrue(usuario.getNombreUsuario().equals(nombre));
        Assert.assertFalse(usuario.getNombreUsuario().equals(nombre + " X"));
    }

    /**
     * Prueba unitaria para {@link Usuario#setNombreUsuario}.
     */
    @Test public void testSetNombreUsuario() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String nuevoNombre = nombre + " X";
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
	    Assert.assertTrue(usuario.getNombreUsuario().equals(nombre));
        Assert.assertFalse(usuario.getNombreUsuario().equals(nuevoNombre));
        usuario.setNombreUsuario(nuevoNombre);
        Assert.assertFalse(usuario.getNombreUsuario().equals(nombre));
        Assert.assertTrue(usuario.getNombreUsuario().equals(nuevoNombre));
    }

    /**
     * Prueba unitaria para {@link Usuario#nombreUsuarioProperty}.
     */
    @Test public void testNombreUsuarioProperty() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertTrue(usuario.nombreUsuarioProperty().get().equals(nombre));
    }

    /**
     * Prueba unitaria para {@link Usuario#getContrasena}.
     */
    @Test public void testGetContrasena() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
	    Assert.assertTrue(usuario.getContrasena().equals(contrasena));
        Assert.assertFalse(usuario.getContrasena().equals(contrasena + " X"));
    }

    /**
     * Prueba unitaria para {@link Usuario#setContrasena}.
     */
    @Test public void testSetContrasena() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String nuevaContrasena = contrasena + " X";
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
	    Assert.assertTrue(usuario.getContrasena().equals(contrasena));
        Assert.assertFalse(usuario.getContrasena().equals(nuevaContrasena));
        usuario.setContrasena(nuevaContrasena);
        Assert.assertFalse(usuario.getContrasena().equals(contrasena));
        Assert.assertTrue(usuario.getContrasena().equals(nuevaContrasena));
    }

    /**
     * Prueba unitaria para {@link Usuario#contrasenaProperty}.
     */
    @Test public void testContrasenaProperty() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertTrue(usuario.contrasenaProperty().get().equals(contrasena));
    }

    /**
     * Prueba unitaria para {@link Usuario#getTelefono}.
     */
    @Test public void testGetTelefono() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
	    Assert.assertTrue(usuario.getTelefono().equals(telefono));
        Assert.assertFalse(usuario.getTelefono().equals(telefono + " X"));
    }

    /**
     * Prueba unitaria para {@link Usuario#setTelefono}.
     */
    @Test public void testSetTelefono() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String nuevoTelefono = telefono + " X";
        String numeroCuenta = numeroCuentaAleatorio();
	    usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
	    Assert.assertTrue(usuario.getTelefono().equals(telefono));
        Assert.assertFalse(usuario.getTelefono().equals(nuevoTelefono));
        usuario.setTelefono(nuevoTelefono);
        Assert.assertFalse(usuario.getTelefono().equals(telefono));
        Assert.assertTrue(usuario.getTelefono().equals(nuevoTelefono));
    }

    /**
     * Prueba unitaria para {@link Usuario#telefonoProperty}.
     */
    @Test public void testTelefonoProperty() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertTrue(usuario.telefonoProperty().get().equals(telefono));
    }

    /**
     * Prueba unitaria para {@link Usuario#getNumeroCuenta}.
     */
    @Test public void testGetNumeroCuenta() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertTrue(usuario.getNumeroCuenta().equals(numeroCuenta));
        Assert.assertFalse(usuario.getNumeroCuenta().equals(numeroCuenta + " X"));
    }

    /**
     * Prueba unitaria para {@link Usuario#setNumeroCuenta}.
     */
    @Test public void testSetNumeroCuenta() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        String nuevoNumeroCuenta = numeroCuenta + " X";
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertTrue(usuario.getNumeroCuenta().equals(numeroCuenta));
        Assert.assertFalse(usuario.getNumeroCuenta().equals(nuevoNumeroCuenta));
        usuario.setNumeroCuenta(nuevoNumeroCuenta);
        Assert.assertFalse(usuario.getNumeroCuenta().equals(numeroCuenta));
        Assert.assertTrue(usuario.getNumeroCuenta().equals(nuevoNumeroCuenta));
    }

    /**
     * Prueba unitaria para {@link Usuario#numeroCuentaProperty}.
     */
    @Test public void testNumeroCuentaProperty() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertTrue(usuario.numeroCuentaProperty().get().equals(numeroCuenta));
    }

    /**
     * Prueba unitaria para {@link Usuario#toString}.
     */
    @Test public void testToString() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
	    StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID                      : %d%n", id));
        sb.append(String.format("Rol                     : %s%n", rol));
        sb.append(String.format("Nombre de Usuario       : %s%n", nombre));
        sb.append(String.format("Contrasena              : %s%n", contrasena));
        sb.append(String.format("Telefono                : %s%n", telefono));
        sb.append(String.format("Numero de Cuenta        : %s%n", numeroCuenta));
        String cadena = sb.toString();
        Assert.assertTrue(usuario.toString().equals(cadena));
        id = 213;
        nombre = "X";
        usuario.setId(id);
        usuario.setNombreUsuario(nombre);
        sb.setLength(0); 
        sb.append(String.format("ID                      : %d%n", id));
        sb.append(String.format("Rol                     : %s%n", rol));
        sb.append(String.format("Nombre de Usuario       : %s%n", nombre));
        sb.append(String.format("Contrasena              : %s%n", contrasena));
        sb.append(String.format("Telefono                : %s%n", telefono));
        sb.append(String.format("Numero de Cuenta        : %s%n", numeroCuenta));
        cadena = sb.toString();
        Assert.assertTrue(usuario.toString().equals(cadena));
    }

    /**
     * Prueba unitaria para {@link Usuario#equals}.
     */
    @Test public void testEquals() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        Usuario igual = new Usuario(id, new String(rol), new String(nombre), new String(contrasena), new String(telefono), new String(numeroCuenta));
        Assert.assertTrue(usuario.equals(igual));
        int otroId = id + 1;
        String otroNombre = nombre + " Segundo";
        String otroRol = rol + " Segundo";
        String otraContrasena = contrasena + " Segundo";
        String otroTelefono = telefono + " Segundo";
        String otroNumeroCuenta = numeroCuenta + " Segundo";
        Usuario distinto = new Usuario(otroId, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertFalse(usuario.equals(distinto));
        distinto = new Usuario(id, otroRol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertFalse(usuario.equals(distinto));
        distinto = new Usuario(id, rol, otroNombre, contrasena, telefono, numeroCuenta);
        Assert.assertFalse(usuario.equals(distinto));
        distinto = new Usuario(id, rol, nombre, otraContrasena, telefono, numeroCuenta);
        Assert.assertFalse(usuario.equals(distinto));
        distinto = new Usuario(id, rol, nombre, contrasena, otroTelefono, numeroCuenta);
        Assert.assertFalse(usuario.equals(distinto));
        distinto = new Usuario(id, rol, nombre, contrasena, telefono, otroNumeroCuenta);
        Assert.assertFalse(usuario.equals(distinto));
        distinto = new Usuario(otroId, otroRol, otroNombre, otraContrasena, otroTelefono, otroNumeroCuenta);
        Assert.assertFalse(usuario.equals(distinto));
        Assert.assertFalse(usuario.equals("Una cadena"));
        Assert.assertFalse(usuario.equals(null));
    }

    /**
     * Prueba unitaria para {@link Usuario#seria}.
     */
    @Test public void testSeria() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);
        String linea = String.format("%d\t%s\t%s\t%s\t%s\t%s%n",
                                    id, rol, nombre, contrasena, telefono, numeroCuenta);
        Assert.assertTrue(usuario.seria().equals(linea));
    }

    /**
     * Prueba unitaria para {@link Usuario#deseria}.
     */
    @Test public void testDeseria() {
        usuario = new Usuario(0, "", "", "", "", "");
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        String linea = String.format("%d\t%s\t%s\t%s\t%s\t%s%n",
                                    id, rol, nombre, contrasena, telefono, numeroCuenta);
        try {
            usuario.deseria(linea);
        } catch (ExcepcionLineaInvalida eli) {
            Assert.fail();
        }
        Assert.assertTrue(usuario.getId() == id);
        Assert.assertTrue(usuario.getRol().equals(rol));
        Assert.assertTrue(usuario.getNombreUsuario().equals(nombre));
        Assert.assertTrue(usuario.getContrasena().equals(contrasena));
        Assert.assertTrue(usuario.getTelefono().equals(telefono));
        Assert.assertTrue(usuario.getNumeroCuenta().equals(numeroCuenta));
	    String[] invalidas = {"", " ", "\t", "  ", "\t\t",
			                " \t", "\t ", "\n", "a\ta\ta",
			                "a\ta\ta\ta"};
	    for (String l : invalidas) {
	        try {
                usuario.deseria(l);
                Assert.fail();
            } catch (ExcepcionLineaInvalida eli) {}
	    }
    }

    /**
     * Prueba unitaria para {@link Usuario#actualiza}.
     */
    @Test public void testActualiza() {
        usuario = new Usuario(0, "B", "B", "B", "B", "B");
        Usuario u = new Usuario(1, "A", "A", "A", "A", "A");
        Assert.assertFalse(usuario == u);
        Assert.assertFalse(usuario.equals(u));
        usuario.actualiza(u);
        Assert.assertFalse(usuario == u);
        Assert.assertTrue(usuario.equals(u));
        Assert.assertTrue(usuario.getRol().equals("A"));
        Assert.assertTrue(usuario.getNombreUsuario().equals("A"));
        Assert.assertTrue(usuario.getContrasena().equals("A"));
        Assert.assertTrue(usuario.getTelefono().equals("A"));
        Assert.assertTrue(usuario.getNumeroCuenta().equals("A"));
        Assert.assertFalse(usuario.idProperty() ==
                            u.idProperty());
        Assert.assertFalse(usuario.rolProperty() ==
                            u.rolProperty());
        Assert.assertFalse(usuario.nombreUsuarioProperty() ==
                            u.nombreUsuarioProperty());
        Assert.assertFalse(usuario.contrasenaProperty() ==
                            u.contrasenaProperty());
        Assert.assertFalse(usuario.telefonoProperty() ==
                            u.telefonoProperty());
        Assert.assertFalse(usuario.numeroCuentaProperty() ==
                            u.numeroCuentaProperty());
        try {
            usuario.actualiza(null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
    }

    /**
     * Prueba unitaria para {@link Usuario#casa}.
     */
    @Test public void testCasa() {
        int id = idAleatorio();
        String nombre = nombreAleatorio();
        String rol = rolAleatorio();
        String contrasena = contrasenaAleatoria();
        String telefono = telefonoAleatorio();
        String numeroCuenta = numeroCuentaAleatorio();
        usuario = new Usuario(id, rol, nombre, contrasena, telefono, numeroCuenta);

        int i = usuario.getId();
        Assert.assertTrue(usuario.casa(CampoUsuario.ID, i));
        i = usuario.getId() - 100;
        Assert.assertTrue(usuario.casa(CampoUsuario.ID, i));
        i = usuario.getId() + 100;
        Assert.assertFalse(usuario.casa(CampoUsuario.ID, i));
        Assert.assertFalse(usuario.casa(CampoUsuario.ID, "XXX"));
        Assert.assertFalse(usuario.casa(CampoUsuario.ID, null));

        String r = usuario.getRol();
        int l = usuario.getRol().length();
        Assert.assertTrue(usuario.casa(CampoUsuario.ROL, r));
        r = usuario.getRol().substring(0, l >> 1);
        Assert.assertTrue(usuario.casa(CampoUsuario.ROL, r));
        r = usuario.getRol().substring(l >> 1, l);
        Assert.assertTrue(usuario.casa(CampoUsuario.ROL, r));
        r = usuario.getRol().substring(l / 3, 2 * (l / 3));
        Assert.assertTrue(usuario.casa(CampoUsuario.ROL, r));
        Assert.assertFalse(usuario.casa(CampoUsuario.ROL, ""));
        Assert.assertFalse(usuario.casa(CampoUsuario.ROL, "XXX"));
        Assert.assertFalse(usuario.casa(CampoUsuario.ROL, 1000));
        Assert.assertFalse(usuario.casa(CampoUsuario.ROL, null));
        
        String n = usuario.getNombreUsuario();
        l = usuario.getNombreUsuario().length();
        Assert.assertTrue(usuario.casa(CampoUsuario.NOMBRE_USUARIO, n));
        n = usuario.getNombreUsuario().substring(0, l >> 1);
        Assert.assertTrue(usuario.casa(CampoUsuario.NOMBRE_USUARIO, n));
        n = usuario.getNombreUsuario().substring(l >> 1, l);
        Assert.assertTrue(usuario.casa(CampoUsuario.NOMBRE_USUARIO, n));
        n = usuario.getNombreUsuario().substring(l / 3, 2 * (l / 3));
        Assert.assertTrue(usuario.casa(CampoUsuario.NOMBRE_USUARIO, n));
        Assert.assertFalse(usuario.casa(CampoUsuario.NOMBRE_USUARIO, ""));
        Assert.assertFalse(usuario.casa(CampoUsuario.NOMBRE_USUARIO, "XXX"));
        Assert.assertFalse(usuario.casa(CampoUsuario.NOMBRE_USUARIO, 1000));
        Assert.assertFalse(usuario.casa(CampoUsuario.NOMBRE_USUARIO, null));
        
        String c = usuario.getContrasena();
        l = usuario.getContrasena().length();
        Assert.assertTrue(usuario.casa(CampoUsuario.CONTRASENA, c));
        c = usuario.getContrasena().substring(0, l >> 1);
        Assert.assertTrue(usuario.casa(CampoUsuario.CONTRASENA, c));
        c = usuario.getContrasena().substring(l >> 1, l);
        Assert.assertTrue(usuario.casa(CampoUsuario.CONTRASENA, c));
        c = usuario.getContrasena().substring(l / 3, 2 * (l / 3));
        Assert.assertTrue(usuario.casa(CampoUsuario.CONTRASENA, c));
        Assert.assertFalse(usuario.casa(CampoUsuario.CONTRASENA, ""));
        Assert.assertFalse(usuario.casa(CampoUsuario.CONTRASENA, "XXX"));
        Assert.assertFalse(usuario.casa(CampoUsuario.CONTRASENA, 1000));
        Assert.assertFalse(usuario.casa(CampoUsuario.CONTRASENA, null));

        String t = usuario.getTelefono();
        l = usuario.getTelefono().length();
        Assert.assertTrue(usuario.casa(CampoUsuario.TELEFONO, t));
        t = usuario.getTelefono().substring(0, l >> 1);
        Assert.assertTrue(usuario.casa(CampoUsuario.TELEFONO, t));
        t = usuario.getTelefono().substring(l >> 1, l);
        Assert.assertTrue(usuario.casa(CampoUsuario.TELEFONO, t));
        t = usuario.getTelefono().substring(l / 3, 2 * (l / 3));
        Assert.assertTrue(usuario.casa(CampoUsuario.TELEFONO, t));
        Assert.assertFalse(usuario.casa(CampoUsuario.TELEFONO, ""));
        Assert.assertFalse(usuario.casa(CampoUsuario.TELEFONO, "XXX"));
        Assert.assertFalse(usuario.casa(CampoUsuario.TELEFONO, 1000));
        Assert.assertFalse(usuario.casa(CampoUsuario.TELEFONO, null));

        String nc = usuario.getNumeroCuenta();
        l = usuario.getNumeroCuenta().length();
        Assert.assertTrue(usuario.casa(CampoUsuario.NUMERO_CUENTA, nc));
        nc = usuario.getNumeroCuenta().substring(0, l >> 1);
        Assert.assertTrue(usuario.casa(CampoUsuario.NUMERO_CUENTA, nc));
        nc = usuario.getNumeroCuenta().substring(l >> 1, l);
        Assert.assertTrue(usuario.casa(CampoUsuario.NUMERO_CUENTA, nc));
        nc = usuario.getNumeroCuenta().substring(l / 3, 2 * (l / 3));
        Assert.assertTrue(usuario.casa(CampoUsuario.NUMERO_CUENTA, nc));
        Assert.assertFalse(usuario.casa(CampoUsuario.NUMERO_CUENTA, ""));
        Assert.assertFalse(usuario.casa(CampoUsuario.NUMERO_CUENTA, "XXX"));
        Assert.assertFalse(usuario.casa(CampoUsuario.NUMERO_CUENTA, 1000));
        Assert.assertFalse(usuario.casa(CampoUsuario.NUMERO_CUENTA, null));

        try {
            usuario.casa(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
    }

    /* Inicializa el generador de numeros aleatorios. */
    static {
        random = new Random();
    }
}
