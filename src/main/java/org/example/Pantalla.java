package org.example;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import org.logic.Juego;
import org.logic.personajes.*;

public class Pantalla {
    private static final int ALTURA_IMAGEN = 25;

    private void setEstiloCasilla(ObservableList <Node> nodos) {
        for (Node nodo : nodos) {
            Button boton = (Button) nodo;
            boton.setStyle(estilos.getCasillaEstilo());
            boton.setGraphic(null);
        }
    }

    private void colocarImagen(Button boton, String path) {
        Image imagen = new Image(path);
        ImageView imageView = new ImageView(imagen);
        imageView.setFitHeight(ALTURA_IMAGEN);
        imageView.setPreserveRatio(true);
        boton.setGraphic(imageView);
    }

    private final Estilos estilos;
    private final Direcciones direcciones;
    private final BuscadorBoton buscadorBoton;

    public Pantalla() {
        estilos = new Estilos();
        direcciones = new Direcciones();
        buscadorBoton = new BuscadorBoton();
    }

    public void mostrar(Juego juego, GridPane layoutJuego, FinDeJuego finDeJuego) {
        setEstiloCasilla(layoutJuego.getChildren());
        layoutJuego.fireEvent(finDeJuego);

        Button boton = buscadorBoton.getBotonPorPosicion(juego.getCoordenadasJugador(), layoutJuego);
        colocarImagen(boton, direcciones.getImagenJugador());

        for (int i = 0; i < juego.getCantidadEnemigos(); i++) {
            Enemigo enemigo = juego.getEnemigo(i);
            if (enemigo != null) {
                Button botonEnemigo = buscadorBoton.getBotonPorPosicion(enemigo.getCoordenadas(), layoutJuego);

                if (enemigo instanceof RobotX1)
                    colocarImagen(botonEnemigo, direcciones.getImagenRobotX1());
                else if (enemigo instanceof RobotX2)
                    colocarImagen(botonEnemigo, direcciones.getImagenRobotX2());
                else
                    colocarImagen(botonEnemigo, direcciones.getImagenExplosion());
            }
        }
    }
}
