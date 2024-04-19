package org.example;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import org.example.eventos.EventoDeFin;
import org.logic.Juego;
import org.logic.personajes.*;

public class Pantalla {
    private void setEstiloCasilla(ObservableList <Node> nodos, int sizeButton) {
        for (Node nodo : nodos) {
            Button boton = (Button) nodo;
            boton.setStyle(archivo.getEstiloCasilla() + sizeButton / 5);
            boton.setGraphic(null);
        }
    }

    private void colocarImagen(Button boton, String path, int sizeBoton) {
        Image imagen = new Image(path);
        ImageView imageView = new ImageView(imagen);
        imageView.setFitHeight(sizeBoton);
        imageView.setPreserveRatio(true);
        boton.setGraphic(imageView);
    }

    private final Archivo archivo;
    private final BuscadorBoton buscadorBoton;

    public Pantalla() {
        archivo = new Archivo();
        buscadorBoton = new BuscadorBoton();
    }

    public void mostrar(Juego juego, GridPane layoutJuego, EventoDeFin eventoDeFin, int sizeBoton) {
        setEstiloCasilla(layoutJuego.getChildren(), sizeBoton);
        layoutJuego.fireEvent(eventoDeFin);

        Button boton = buscadorBoton.getBotonPorPosicion(juego.getCoordenadasJugador(), layoutJuego);
        colocarImagen(boton, archivo.getImagenJugador(), sizeBoton);

        for (int i = 0; i < juego.getCantidadEnemigos(); i++) {
            Enemigo enemigo = juego.getEnemigo(i);
            if (enemigo != null) {
                Button botonEnemigo = buscadorBoton.getBotonPorPosicion(enemigo.getCoordenadas(), layoutJuego);

                if (enemigo instanceof RobotX1)
                    colocarImagen(botonEnemigo, archivo.getImagenRobotX1(), sizeBoton);
                else if (enemigo instanceof RobotX2)
                    colocarImagen(botonEnemigo, archivo.getImagenRobotX2(), sizeBoton);
                else
                    colocarImagen(botonEnemigo, archivo.getImagenExplosion(), sizeBoton);
            }
        }
    }
}
