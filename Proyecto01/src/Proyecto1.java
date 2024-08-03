package src;

import src.igu.ControladorInicioSesion;

public class Proyecto1 {
    public static void main(String[] args) {
        CheemsMart tienda = CheemsMart.getInstance();
        Tienda tiendaProxy = new TiendaHelper(tienda);
        ControladorInicioSesion c = new ControladorInicioSesion(tiendaProxy);
        c.inicioSesion();
    }
}

