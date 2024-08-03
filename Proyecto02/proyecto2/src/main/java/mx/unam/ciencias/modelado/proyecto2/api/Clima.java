package  mx.unam.ciencias.modelado.proyecto2.api;

/**
 * <p>Clase que representa la informacion de clima actual de un municipio.
 * Tal como la proporciona la API de la CONAGUA.</p>
 */
public class Clima {

    // Cobertura de nubes (%) del cielo
    private String cc;

    // Descripcion del cielo
    private String desciel;

    // Diferencia de hora respecto a hora UTC
    private String dh;

    // Direccion del viento (Cardinal)
    private String dirvienc;

    // Direccion del viento (Grados)
    private String dirvieng;

    // Fecha local. dia local, inicia cuatro horas antes (YYYmmddhhmm)
    private String dloc;

    // Identificador del estado
    private String ides;

    // Identificador del municipio
    private String idmun;  

    // Latitud
    private String lat;

    // Longitud
    private String lon;

    // Numero de dia
    private String ndia;
    
    // Nombre del estado
    private String nes;

    // Nombre del municipio
    private String nmun;

    // Precipitacion (litros/m2)
    private String prec;

    // Probabilidad de precipitacion (%)
    private String probprec;

    // Rafaga de viento
    private String raf;

    // Temperatura maxima (°C)
    private String tmax;

    // Temperatura mimima (°C)
    private String tmin;

    // Velocidad del viento (km/h)
    private String velvien;
    
    /*
     * Constructor por omision.Sirve para inicializar un clima desde un json.
     */
    public Clima() {}

    /**
     * Define un clima con los valores de los atributos.
     * @param cc la cobertura del cielo.
     * @param desciel la descripcion del cielo.
     * @param dh la diferencia de hora respecto a hora UTC.
     * @param dirvienc la direccion del viento en cardinal.
     * @param dirvieng la direccion del viento en grados.
     * @param dloc la fecha local.
     * @param ides el identificador del estado.
     * @param idmun el identificador del municipio.
     * @param lat la latitud.
     * @param lon la longitud.
     * @param ndia el numero de dia.
     * @param nes el nombre del estado.
     * @param nmun el nombre del municipio.
     * @param prec la precipitacion.
     * @param probprec la probabilidad de precipitacion.
     * @param raf la rafaga de viento.
     * @param tmax la temperatura maxima.
     * @param tmin la temperatura minima.
     * @param velvien la velocidad del viento.
     */
    public Clima(String cc, String desciel, String dh, String dirvienc, String dirvieng, String dloc, String ides, String idmun, String lat, String lon, String ndia, String nes, String nmun, String prec, String probprec, String raf, String tmax, String tmin, String velvien) {
        this.cc = cc;
        this.desciel = desciel;
        this.dh = dh;
        this.dirvienc = dirvienc;
        this.dirvieng = dirvieng;
        this.dloc = dloc;
        this.ides = ides;
        this.idmun = idmun;
        this.lat = lat;
        this.lon = lon;
        this.ndia = ndia;
        this.nes = nes;
        this.nmun = nmun;
        this.prec = prec;
        this.probprec = probprec;
        this.raf = raf;
        this.tmax = tmax;
        this.tmin = tmin;
        this.velvien = velvien;
    }

    /**
     * Regresa la cobertura de nubes (%) del cielo. Este valor es un doble. 
     * @return cc la cobertura del cielo.
     */
    public String getCc() {
        return cc;
    }

    /**
     * Regresa la descripcion del cielo. Este valor es una cadena.
     * @return desciel la descripcion del cielo.
     */
    public String getDesciel() {
        return desciel;
    }

    /*
     * Regresa la diferencia de hora respecto a hora UTC. Este valor es un doble.
     * @return dh la diferencia de hora respecto a hora UTC.
     */
    public String getDh() {
        return dh;
    }

    /*
     * Regresa la Direccion del viento (Cardinal). Este valor es un doble.
     * @return dirvienc la direccion del viento en cardinal.
     */
    public String getDirvienc() {
        return dirvienc;
    }

    /**
     * Regresa la Direccion del viento (Grados). Este valor es un doble.
     * @return dirvieng la direccion del viento en grados.
     */
    public String getDirvieng() {
        return dirvieng;
    }

    /**
     * Regresa la fecha local. dia local, inicia cuatro horas antes (YYYmmddhhmm).Este valor es un String.
     * @return dloc la fecha local.
     */
    public String getDloc() {
        return dloc;
    }

    /**
     * Regresa el identificador del estado. Este valor es un entero.
     * @return ides el identificador del estado.
     */
    public String getIdes() {
        return ides;
    }

    /**
     * Regresa el identificador del municipio. Este valor es un entero.
     * @return idmun el identificador del municipio.
     */
    public String getIdmun() {
        return idmun;
    }

    /**
     * Regresa la latitud. Este valor es un doble.
     * @return lat la latitud.
     */
    public String getLat() {
        return lat;
    }

    /**
     * Regresa la longitud. Este valor es un doble.
     * @return lon la longitud.
     */
    public String getLon() {
        return lon;
    }

    /**
     * Regresa el numero de dia. Este valor es un entero.
     * @return ndia el numero de dia.
     */
    public String getNdia() {
        return ndia;
    }

    /**
     * Regresa el nombre del estado. Este valor es una cadena.
     * @return nes el nombre del estado.
     */
    public String getNes() {
        return nes;
    }

    /**
     * Regresa el nombre del municipio. Este valor es una cadena.
     * @return nmun el nombre del municipio.
     */
    public String getNmun() {
        return nmun;
    }

    /*
     * Regresa la precipitacion (litros/m2). Este valor es un doble.
     * @return prec la precipitacion.
     */
    public String getPrec() {
        return prec;
    }

    /**
     * Regresa la Probabilidad de precipitacion (%). Este valor es un doble.
     * @return probprec la probabilidad de precipitacion.
     */
    public String getProbprec() {
        return probprec;
    }

    /**
     * Regresa la rafaga de viento. Este valor es un doble.
     * @return raf la rafaga de viento.
     */
    public String getRaf() {
        return raf;
    }

    /**
     * Regresa la Temperatura maxima (°C). Este valor es un doble.
     * @return tmax la temperatura maxima.
     */
    public String getTmax() {
        return tmax;
    }

    /**
     * Regresa la Temperatura mimima (°C). Este valor es un doble.
     * @return tmin la temperatura minima.
     */
    public String getTmin() {
        return tmin;
    }

    /**
     * Regresa la Velocidad del viento (km/h). Este valor es un doble.
     * @return velvien la velocidad del viento.
     */
    public String getVelvien() {
        return velvien;
    }

}
