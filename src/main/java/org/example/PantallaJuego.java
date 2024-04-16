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
    Estilos estilos = new Estilos();
    Direcciones direcciones = new Direcciones();

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
            boton.setStyle(estilos.getCasillaEstilo());
            boton.setGraphic(null);
        }

        Button boton = buscadorPos.getBotonPorPosicion(juego.jugador.getCoordenadas(), layoutJuego);
        colocarImg(boton, direcciones.getPlayerDir());

        for (Enemigo robot : juego.robots) {
            Button botonRobot = buscadorPos.getBotonPorPosicion(robot.getCoordenadas(), layoutJuego);

            if (robot instanceof RobotX1){
                colocarImg(botonRobot, direcciones.getX1Dir());
            } else if (robot instanceof RobotX2){
                colocarImg(botonRobot, direcciones.getX2Dir());;
            }
        }
        for (Enemigo robot : juego.obstaculos) {
            Button botonObs = buscadorPos.getBotonPorPosicion(robot.getCoordenadas(), layoutJuego);
            colocarImg(botonObs, direcciones.getX2Dir());
        }
    }
}
