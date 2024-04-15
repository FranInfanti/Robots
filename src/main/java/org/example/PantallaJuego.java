package org.example;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.logic.Enemigo;
import org.logic.Juego;
import org.logic.RobotX1;
import org.logic.RobotX2;

public class PantallaJuego {
    private void colocarImg(Button boton, String path){
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setPreserveRatio(true);
        boton.setGraphic(imageView);
    }


    public void mostrar(Juego juego, GridPane layoutJuego) {
        BuscadorPos buscadorPos = new BuscadorPos();
        for (Node nodo : layoutJuego.getChildren()) {
            Button boton = (Button) nodo;
            boton.setStyle("-fx-background-color: #003380ff;  -fx-background-radius: 10");
            boton.setGraphic(null);
        }

        Button boton = buscadorPos.getBotonPorPosicion(juego.jugador.getCoordenadas(), layoutJuego);
        colocarImg(boton, "player.png");

        for (Enemigo robot : juego.robots) {
            Button botonRobot = buscadorPos.getBotonPorPosicion(robot.getCoordenadas(), layoutJuego);

            if (robot instanceof RobotX1){
                colocarImg(botonRobot, "X1.png");
            } else if (robot instanceof RobotX2){
                colocarImg(botonRobot, "X2.png");;
            }
        }
        for (Enemigo robot : juego.obstaculos) {
            Button botonObs = buscadorPos.getBotonPorPosicion(robot.getCoordenadas(), layoutJuego);
            colocarImg(botonObs, "explosion.png");
        }
    }
}
