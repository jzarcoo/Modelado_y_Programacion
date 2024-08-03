package mx.unam.ciencias.modelado.proyecto2.igu;

import javafx.scene.control.TextField;

/**
 * <p>Clase para entradas verificables. Una entrada verificable tiene un verificador
 * para el campo de texto de la entrada</p>
 */
public class EntradaVerificable extends TextField {

    /* El verificador de la entrada. */
    private Verificador verificador;

    /**
     * Define el estado inicial de una entrada verificable.
     */
    public EntradaVerificable() {
        verificador = e -> false;
    }

    /**
     * Define el verificador de la entrada.
     * @param verificador el nuevo verificador de la entrada.
     */
    public void setVerificador(Verificador verificador) {
        this.verificador = verificador;
    }

    /**
     * Nos dice si la entrada es valida.
     * @return <code>true</code> si la entrada es valida, <code>false</code> en
     *         otro caso.
     */
    public boolean esValida() {
        boolean b = verificador.verifica(getText());
        String s = b ? "" : "-fx-background-color:FFCCCCCC;"; // gris claro (boton deshabilitado)
        setStyle(s);
        return b;
    }
}
