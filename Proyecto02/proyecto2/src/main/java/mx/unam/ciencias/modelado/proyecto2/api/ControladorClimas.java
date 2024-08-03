package mx.unam.ciencias.modelado.proyecto2.api;

import java.util.List;

/**
 * <p>Clase que se encarga de manejar los climas de las alcaldias.
 * Implementa el patron Singleton. Permite manjar mas facilmente la informacion proveniente de la api.</p>
 */
public class ControladorClimas {

    /* Lista de climas */
    private List<Clima> climas;

    /* Unica instancia del controlador clima. */
    // Variable comprartida entre todos los hilos pues esta en memoria principal.
    private volatile static ControladorClimas controladorClimas;

    // Constructor privado para evitar instancias de la clase
    private ControladorClimas() {}
    
    /**
     * Establece los climas.
     * @param climas los climas a establecer.
     */
    public void estableceClimas(List<Clima> climas) {
        this.climas = climas;
    }

    /**
     * Regresa la instancia del controlador de climas.
     * Este metodo implementa el patron Singleton, debe ser sincronizado en 
     * caso de que dos o mas hilos accedan al metodo a la vez. Ademas, realiza
     * un bloque comprobado doble para garantizar que la instancia sea unica.
     * @return la instancia del controlador de climas.
     */
	public static ControladorClimas getInstance(){
		if (controladorClimas == null) {
			synchronized (ControladorClimas.class) {
				if (controladorClimas == null) {
					controladorClimas = new ControladorClimas();
                }
			}
		}
		return controladorClimas;
	}

    /**
     * Obtiene el clima de una alcaldia un dia.
     * @param alcaldia la alcaldia de la que se desea obtener el clima.
     * @return el clima de la alcaldia especificada.
     */
    public Clima obtenerClima(String alcaldia) {
        for (Clima clima : climas) {
            if(clima.getNmun().equals(alcaldia) && clima.getNdia().equals("0")){
                return clima;
            }
        }
        return null;
    }   
    
    /**
     * Muestra la probabilidad de lluvia de una alcaldia.
     * @param alcaldia la alcaldia de la que se desea mostrar la probabilidad de lluvia.
     * @return la probabilidad de lluvia y un mensaje acorde.
     */
    public String mostrarProbabilidadLluvia(String alcaldia ) {
        Clima clima = obtenerClima(alcaldia);
        return mostrarProbabilidadLluvia(clima);
    }

    /**
     * Muestra la probabilidad de lluvia de una alcaldia.
     * @param clima el clima del que se desea mostrar la probabilidad de lluvia.
     * @return la probabilidad de lluvia y un mensaje acorde.
     */
    private String mostrarProbabilidadLluvia(Clima clima ) {

        StringBuilder mensaje = new StringBuilder();
        mensaje.append("La probabilidad de lluvia es de ");
        double probll = Double.parseDouble(clima.getProbprec());
        mensaje.append(probll);
        mensaje.append("%");
        if(probll > 50){
            mensaje.append(" lleva tu paraguas");
        }
        mensaje.append(".");
        return mensaje.toString();
    }

    /**
     * Muestra la temperatura minima de una alcaldia en un dia.
     * @param alcaldia la alcaldia de la que se desea mostrar la temperatura minima.
     * @return la temperatura minima y un mensaje acorde.
     */
    public String mostrarTemperaturaMinima(String alcaldia) {
        Clima clima = obtenerClima(alcaldia);
        return mostrarTemperaturaMinima(clima);
    }

    /**
     * Muestra la temperatura minima de una alcaldia.
     * @param clima el clima del que se desea mostrar la temperatura minima.
     * @return la temperatura minima y un mensaje acorde.
     */
    private String mostrarTemperaturaMinima(Clima clima) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("La temperatura minima es de ");
        double tmin = Double.parseDouble(clima.getTmin());
        mensaje.append(tmin);
        mensaje.append("°C.");
        if(tmin < 15){
            mensaje.append(" Abrigate mucho.");
        }
        return mensaje.toString();
    }

    /**
     * Muestra la temperatura maxima de una alcaldia.
     * @param alcaldia la alcaldia de la que se desea mostrar la temperatura maxima.
     * @return la temperatura maxima y un mensaje acorde.
     */
    public String mostrarTemperaturaMaxima(String alcaldia) {
        Clima clima = obtenerClima(alcaldia);
        return mostrarTemperaturaMaxima(clima);
    }

    /**
     * Muestra la temperatura maxima de una alcaldia.
     * @param clima el clima del que se desea mostrar la temperatura maxima.
     * @return la temperatura maxima y un mensaje acorde.
     */
    private String mostrarTemperaturaMaxima(Clima clima) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("La temperatura maxima es de ");
        double tmax = Double.parseDouble(clima.getTmax());
        mensaje.append(tmax);
        mensaje.append("°C.");
        if(tmax > 30){
            mensaje.append(" Lleva mucha agua.");
        }
        return mensaje.toString();
    }

    /**
     * Muestra la descricion del cielo de una alcaldia.
     * @param alcaldia la alcaldia de la que se desea mostrar la descripcion del cielo.
     * @return la descripcion del cielo.
     */
    public String mostrarDescripcionCielo(String alcaldia) {
        Clima clima = obtenerClima(alcaldia);
        return mostrarDescripcionCielo(clima);
    }

    /**
     * Muestra la descricion del cielo de una alcaldia.
     * @param clima el clima del que se desea mostrar la descripcion del cielo.
     * @return la descripcion del cielo.
     */
    private String mostrarDescripcionCielo(Clima clima) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Hoy tenemos ");
        mensaje.append(clima.getDesciel());
        return mensaje.toString();
    }

}
