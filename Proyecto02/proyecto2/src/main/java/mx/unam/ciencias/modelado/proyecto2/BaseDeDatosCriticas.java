package mx.unam.ciencias.modelado.proyecto2;

/**
 * <p>Clase para bases de datos de Criticas.</p>
 */
public class BaseDeDatosCriticas extends BaseDeDatos<Critica, CampoCritica> {

    /**
     * Crea un Critica en blanco.
     * @return un Critica en blanco.
     */
    @Override 
    public Critica creaRegistro() {
        return new Critica(0, 0, 0, 0, null, 0);
    }
}
