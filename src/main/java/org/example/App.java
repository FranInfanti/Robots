package org.example;

import org.logic.Coordenadas;
import org.logic.Juego;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Juego juego = new Juego(new Coordenadas(15,10));
        juego.agregarRobots();
        Vista vista = new Vista(stage, juego);

        vista.setListenerTeleportRandom();
        vista.setListenerTeleportSeguro();
        vista.setListenerWaitForRobots();
        vista.setListenerFinDeJuego();
    }

    public static void main(String[] args) {
        launch();
    }
}