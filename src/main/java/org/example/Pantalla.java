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

    private void setEstiloCasilla(GridPane layoutJuego) {
        for (Node nodo : layoutJuego.getChildren()) {
            Button boton = (Button) nodo;
            boton.setStyle(archivo.getCssCasilla(false));
            boton.setGraphic(null);
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

    public void mostrar(Juego juego, GridPane layoutJuego, EventoDeFin eventoDeFin) {
        setEstiloCasilla(layoutJuego);
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