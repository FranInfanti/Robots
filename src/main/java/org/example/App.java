package org.example;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import org.logic.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Juego juego = new Juego(new Coordenadas(15,10));
        PantallaJuego pantallaJuego = new PantallaJuego();
        VBox layoutPrincipal = new VBox();
        layoutPrincipal.setSpacing(10);
        layoutPrincipal.setMaxSize(600,600);

        HBox layoutArriba = new HBox();

        Label l = new Label("ROBOTS");
        l.setFont(new Font("Arial Bold",30));
        l.setStyle("-fx-text-fill: #aaccffff");
        layoutArriba.setAlignment(Pos.CENTER);
        layoutArriba.getChildren().add(l);

        HBox layoutAbajo = new HBox();
        layoutAbajo.setMaxSize(600, 400);

        String styleButton = "-fx-background-color: #003380ff;  -fx-background-radius: 20; -fx-text-fill: #aaccffff";

        Button b1 = new Button("Teleport Random");
        b1.setPrefSize(200,40);
        b1.setStyle(styleButton);







        Button b2 = new Button("Teleport Safely");
        b2.setPrefSize(200,40);
        b2.setStyle(styleButton);
        Button b3 = new Button("Wait for Robots");
        b3.setPrefSize(200,40);
        b3.setStyle(styleButton);

        b1.setFont(new Font("Arial Bold",15));
        b2.setFont(new Font("Arial Bold",15));
        b3.setFont(new Font("Arial Bold",15));

        layoutAbajo.getChildren().add(b1);
        layoutAbajo.getChildren().add(b2);
        layoutAbajo.getChildren().add(b3);

        GridPane layoutJuego = new GridPane();
        layoutJuego.setAlignment(Pos.CENTER);
        layoutPrincipal.setAlignment(Pos.CENTER);
        layoutJuego.setHgap(3);
        layoutJuego.setVgap(3);

        String styleFondo = "-fx-background-color: #002255ff";

        layoutPrincipal.setStyle(styleFondo);
        layoutArriba.setStyle(styleFondo);
        layoutAbajo.setStyle(styleFondo);
        layoutJuego.setStyle(styleFondo);

        b1.setOnAction(ActionEvent -> {
                    juego.teleportJugador(null);
                    pantallaJuego.mostrar(juego, layoutJuego);
                }
        );
        layoutJuego.setMaxSize(400,400);
        for (int fil = 0; fil < 15; fil++) {
            for (int col = 0; col < 10; col++) {

                Button boton = new Button();
                boton.setPrefSize(25,25);
                boton.setMaxSize(25,25);
                boton.setMinSize(25,25);
                layoutJuego.add(boton, fil, col);
                boton.setOnAction(ActionEvent -> {
                            int x = layoutJuego.getColumnIndex(boton);
                            int y = layoutJuego.getRowIndex(boton);
                            if (!juego.jugador.getEliminado()) {
                                juego.moverJugador(new Coordenadas(x, y));
                                pantallaJuego.mostrar(juego, layoutJuego);
                            }
                        }
                );
            }
        }

        juego.agregarRobots();
        juego.borrar();
        pantallaJuego.mostrar(juego, layoutJuego);

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