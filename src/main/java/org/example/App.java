package org.example;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.logic.Coordenadas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.logic.Juego;

import java.util.Objects;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Juego juego = new Juego(new Coordenadas(15,10));

        VBox layoutPrincipal = new VBox();
        layoutPrincipal.setMaxSize(600,600);

        HBox layoutArriba = new HBox();
        Label l = new Label("Robots");
        layoutArriba.getChildren().add(l);

        HBox layoutAbajo = new HBox();
        layoutAbajo.setMaxSize(600, 400);

        Button b1 = new Button("Teleport Random");
        b1.setPrefSize(200,80);
        Button b2 = new Button("Teleport Safely");
        b2.setPrefSize(200,80);
        Button b3 = new Button("Wait for Robots");
        b3.setPrefSize(200,80);

        layoutAbajo.getChildren().add(b1);
        layoutAbajo.getChildren().add(b2);
        layoutAbajo.getChildren().add(b3);

        GridPane layoutJuego = new GridPane();
        layoutJuego.setMaxSize(400,300);
        for (int fil = 0; fil < 15; fil++) {
            for (int col = 0; col < 10; col++) {
                //Image image = new Image(getClass().getResourceAsStream(""));
                layoutJuego.add(new ImageView(), fil, col);
            }
        }

        layoutPrincipal.getChildren().addAll(layoutArriba, layoutJuego, layoutAbajo);
        Scene scene = new Scene(layoutPrincipal, 600, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}