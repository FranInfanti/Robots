package org.example.controller;

import javafx.scene.control.Alert;

import org.example.view.Archivo;
import org.example.view.Vista;
import org.example.model.Coordenadas;
import org.example.model.Juego;

public class Controlador {
    private Juego juego;
    private final Vista vista;
    private final Archivo archivo;

    public Controlador(Vista vista) {
        this.vista = vista;
        archivo = new Archivo();
    }

    private void fireAlerta() {
        var alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(archivo.getTextoDeTituloJuego());
        alerta.setContentText(archivo.getTextoResultado(juego.getNivel(), juego.getPuntos()));
        alerta.setHeaderText(archivo.getTextoAlertaFinal());
        alerta.show();
    }

    public void iniciar() {
        vista.setListenerEventoFin(_ -> {
            if (juego.getJugadorEliminado())
                fireAlerta();
            juego.estadoJuego();
            vista.setTeleportSeguro(juego.getTeleportsDisponibles());
        });

        vista.setListenerNuevoJuego(_ -> vista.iniciarJuego());

        vista.setListenerInicioJuego(_ -> {
            vista.setMenuInicio(false);
            juego = new Juego(new Coordenadas(vista.getCantidadColumnas(), vista.getCantidadFilas()));
            vista.setJuego(juego);
            vista.mostrarPantalla();
        });

        vista.setListenerTeleportRandom(_ -> {
            if (juego.getJugadorEliminado() || vista.getTeleportActivado())
                return;
            juego.teleportJugador(null);
            vista.mostrarPantalla();
        });

        vista.setListenerTeleportSeguro(_ -> {
            if (!juego.getJugadorEliminado())
                vista.setTeleportActivado(true);
        });

        vista.setListenerWaitForRobots(_ -> {
            if (juego.getJugadorEliminado() || vista.getTeleportActivado())
                return;
            juego.moverJugador(juego.getCoordenadasJugador());
            vista.mostrarPantalla();
        });

        vista.setListenerKeyPressed(_ -> vista.actualizarEstiloNodos());

        vista.setListenerMouseReleased(_ -> vista.actualizarEstiloNodos());
    }
}
