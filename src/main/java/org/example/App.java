package org.example;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import org.logic.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


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
        b1.setStyle("-fx-background-color: grey; -fx-border-color: black");
        Button b2 = new Button("Teleport Safely");
        b2.setPrefSize(200,80);
        b2.setStyle("-fx-background-color: grey; -fx-border-color: black");
        Button b3 = new Button("Wait for Robots");
        b3.setPrefSize(200,80);
        b3.setStyle("-fx-background-color: grey; -fx-border-color: black");

        layoutAbajo.getChildren().add(b1);
        layoutAbajo.getChildren().add(b2);
        layoutAbajo.getChildren().add(b3);

        GridPane layoutJuego = new GridPane();
        layoutJuego.setStyle("-fx-background-color: black");
        layoutJuego.setMaxSize(600,420);
        for (int fil = 0; fil < 15; fil++) {
            for (int col = 0; col < 10; col++) {
                //Image image = new Image(getClass().getResourceAsStream(""));
                Button boton = new Button();
                boton.setPrefSize(40,40);
                boton.setStyle("-fx-background-color: skyblue; -fx-border-color: slateblue");
                layoutJuego.add(boton, fil, col);
                boton.setOnAction(ActionEvent ->  {
                            int x = layoutJuego.getColumnIndex(boton);
                            int y = layoutJuego.getRowIndex(boton);
                            juego.moverJugador(new Coordenadas(x,y));
                            mostrar(juego, layoutJuego);
                        }
                );
            }
        }

        juego.agregarRobots();
        juego.borrar();
        mostrar(juego, layoutJuego);

        layoutPrincipal.getChildren().addAll(layoutArriba, layoutJuego, layoutAbajo);
        Scene scene = new Scene(layoutPrincipal, 600, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void mostrar(Juego juego, GridPane layoutJuego){
        BuscadorPos buscadorPos = new BuscadorPos();
        for (Node nodo : layoutJuego.getChildren()) {
            Button boton = (Button) nodo;
            boton.setStyle("-fx-background-color: skyblue; -fx-border-color: slateblue");
        }

        for (Enemigo robot : juego.robots) {
            Button botonRobot = buscadorPos.getBotonPorPosicion(robot.getCoordenadas(), layoutJuego);
            if (robot instanceof RobotX1) {
                botonRobot.setStyle("-fx-background-color: grey; -fx-border-color: slateblue");
            } else if (robot instanceof RobotX2){
                botonRobot.setStyle("-fx-background-color: lightgrey; -fx-border-color: slateblue");
            }
        }
        for (Enemigo robot : juego.obstaculos) {
            Button botonObs = buscadorPos.getBotonPorPosicion(robot.getCoordenadas(), layoutJuego);
            botonObs.setStyle("-fx-background-color: orange; -fx-border-color: slateblue");
        }

        Button boton = buscadorPos.getBotonPorPosicion(juego.jugador.getCoordenadas(), layoutJuego);
        boton.setStyle("-fx-background-color: blue; -fx-border-color: black");
    }

    public static void main(String[] args) {
        launch();
    }

}