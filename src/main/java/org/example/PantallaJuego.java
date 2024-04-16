package org.example;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import org.logic.Juego;
import org.logic.personajes.*;

public class PantallaJuego {
    Estilos estilos = new Estilos();
    Direcciones direcciones = new Direcciones();

    private void colocarImagen(Button boton, String path) {
        Image imagen = new Image(path);
        ImageView imageView = new ImageView(imagen);
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

        Button boton = buscadorPos.getBotonPorPosicion(juego.getCoordenadasJugador(), layoutJuego);
        colocarImagen(boton, direcciones.getImagenJugador());

        for (int i = 0; i < juego.getCantidadEnemigos(); i++) {
            Enemigo enemigo = juego.getEnemigo(i);
            if (enemigo != null) {
                Button botonEnemigo = buscadorPos.getBotonPorPosicion(enemigo.getCoordenadas(), layoutJuego);

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
