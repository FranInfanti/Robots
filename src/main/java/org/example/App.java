package org.example;

import org.logic.Coordenadas;
import org.logic.Juego;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Vista vista = new Vista(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}