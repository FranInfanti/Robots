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

public class Pantalla {
    private static final int DISTANCIA_MINIMA = 1;

    private boolean esDistanciaMinima(Coordenadas jugador, Button boton) {
        int x = Math.abs(jugador.getX() - GridPane.getColumnIndex(boton));
        int y = Math.abs(jugador.getY() - GridPane.getRowIndex(boton));

        return x < DISTANCIA_MINIMA && y < DISTANCIA_MINIMA;
    }

    private void setEstiloCasilla(GridPane layoutJuego, Coordenadas jugador, boolean teleportActivado) {
        for (Node nodo : layoutJuego.getChildren()) {
            Button boton = (Button) nodo;
            boton.setStyle(archivo.getCssCasilla(false));
            boton.setGraphic(null);
            if (esDistanciaMinima(jugador, boton) || teleportActivado)
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

    private Button getBotonPorPosicion(Coordenadas posicion, GridPane gridPane) {
        Button buscado = null;
        ObservableList<Node> botones = gridPane.getChildren();
        for (Node boton : botones)
            if (posicion.esIgual(new Coordenadas(GridPane.getColumnIndex(boton), GridPane.getRowIndex(boton)))) buscado = (Button) boton;
        return buscado;
    }

    private final Archivo archivo;

    public Pantalla() {
        archivo = new Archivo();
    }

    public void mostrar(Juego juego, GridPane layoutJuego, EventoDeFin eventoDeFin, boolean teleportActivado) {
        setEstiloCasilla(layoutJuego, juego.getCoordenadasJugador(), teleportActivado);
        layoutJuego.fireEvent(eventoDeFin);

        Button boton = getBotonPorPosicion(juego.getCoordenadasJugador(), layoutJuego);
        colocarImagen(boton, archivo.getImagenJugador());

        for (int i = 0; i < juego.getCantidadEnemigos(); i++) {
            Enemigo enemigo = juego.getEnemigo(i);
            if (enemigo != null)
                colocarImagen(getBotonPorPosicion(enemigo.getCoordenadas(), layoutJuego), archivo.getImagenEnemigo(enemigo.getDesplazamiento()));
        }
    }
}