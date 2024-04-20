package org.example;

import javafx.scene.control.Alert;
import org.logic.Coordenadas;
import org.logic.Juego;

public class Controlador {
    private void fireAlerta() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(archivo.getTextoDeTituloJuego());
        alerta.setContentText(archivo.getTextoResultado(juego.getNivel(), juego.getPuntos()));
        alerta.setHeaderText(archivo.getTextoAlertaFinal());
        alerta.show();
        vista.iniciarJuego();
    }


    private Juego juego;
    private final Vista vista;
    private final Archivo archivo;

    public Controlador(Vista vista) {
        this.vista = vista;
        archivo = new Archivo();
    }

    public void iniciar() {
        vista.setListenerEventoInicio(_ -> {
            vista.setMenuInicio(true);
            vista.actualizarEstilosNodos();
        });

        vista.setListenerEventoFin(_ -> {
            if (juego.getJugadorEliminado())
                fireAlerta();
            juego.estadoJuego();
            vista.setTeleportSeguro(juego.getTeleportsDisponibles());
        });

        vista.setListenerNuevoJuego(_ -> vista.iniciarJuego());

        vista.setListenerInicioJuego(_ -> {
            vista.setTelePortActivado(false);
            vista.setMenuInicio(false);
            juego = new Juego(new Coordenadas(vista.getCantidadColumnas(), vista.getCantidadFilas()));
            vista.setJuego(juego);
            vista.setGrillaLayoutJuego();
            vista.mostrarPantalla();
        });

        vista.setListenerTeleportRandom(_ -> {
            if (juego.getJugadorEliminado() || vista.getTelePortActivado())
                return;
            juego.teleportJugador(null);
            vista.mostrarPantalla();
        });

        vista.setListenerTeleportSeguro(_ -> {
            if (!juego.getJugadorEliminado())
                vista.setTelePortActivado(true);
            vista.mostrarPantalla();
        });

        vista.setListenerWaitForRobots(_ -> {
            if (juego.getJugadorEliminado() || vista.getTelePortActivado())
                return;
            juego.moverJugador(juego.getCoordenadasJugador());
            vista.mostrarPantalla();
        });

        vista.setListenerKeyPressed(_ -> vista.actualizarEstilosNodos());

        vista.setListenerMouseReleased(_ -> vista.actualizarEstilosNodos());

    }
}
