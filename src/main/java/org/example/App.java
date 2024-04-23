package org.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Vista vista = new Vista(stage);
        Controlador controlador = new Controlador(vista);
        controlador.iniciar();
    }

    public static void main(String[] args) {
        launch();
    }
}