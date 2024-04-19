package org.example;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import org.example.eventos.EventoDeFin;
import org.logic.Coordenadas;
import org.logic.Juego;
import org.logic.personajes.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Pantalla {

    private void setEstiloCasilla(GridPane layoutJuego, Coordenadas posicionJugador, boolean telePortActivado) {
        for (Node nodo : layoutJuego.getChildren()) {
            Button boton = (Button) nodo;
            boton.setStyle(archivo.getEstiloCasilla() + boton.getMaxWidth() / 5);
            boton.setGraphic(null);
            boton.setDisable(true);
            if ((Math.abs(posicionJugador.getX() - layoutJuego.getColumnIndex(boton)) <= 1 &&  Math.abs(posicionJugador.getY() - layoutJuego.getRowIndex(boton)) <= 1) || telePortActivado)
                boton.setDisable(false);
        }
    }

    private void colocarImagen(Button boton, String path) {
        Image imagen = new Image(path);
        ImageView imageView = new ImageView(imagen);
        imageView.setFitHeight(boton.getMaxWidth());
        imageView.setPreserveRatio(true);
        boton.setGraphic(imageView);
    }

    private final Archivo archivo;
    private final BuscadorBoton buscadorBoton;

    public Pantalla() {
        archivo = new Archivo();
        buscadorBoton = new BuscadorBoton();
    }

    public void mostrar(Juego juego, GridPane layoutJuego, EventoDeFin eventoDeFin, boolean telePortActivado) {
        setEstiloCasilla(layoutJuego, juego.jugador.getCoordenadas(), telePortActivado);
        layoutJuego.fireEvent(eventoDeFin);

        Button boton = buscadorBoton.getBotonPorPosicion(juego.getCoordenadasJugador(), layoutJuego);
        colocarImagen(boton, archivo.getImagenJugador());

        for (int i = 0; i < juego.getCantidadEnemigos(); i++) {
            Enemigo enemigo = juego.getEnemigo(i);
            if (enemigo != null) {
                Button botonEnemigo = buscadorBoton.getBotonPorPosicion(enemigo.getCoordenadas(), layoutJuego);

                if (enemigo instanceof RobotX1)
                    colocarImagen(botonEnemigo, archivo.getImagenRobotX1());
                else if (enemigo instanceof RobotX2)
                    colocarImagen(botonEnemigo, archivo.getImagenRobotX2());
                else
                    colocarImagen(botonEnemigo, archivo.getImagenExplosion());
            }
        }
    }
}
