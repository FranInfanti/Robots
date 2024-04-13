package org.example;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.logic.Coordenadas;

public class BuscadorPos {
    public Button getBotonPorPosicion(Coordenadas posicion, GridPane gridPane) {
        Button botonEncontrado = null;
        ObservableList<Node> botones = gridPane.getChildren();
        for (Node boton : botones) {
            if(gridPane.getRowIndex(boton) == posicion.getY() && gridPane.getColumnIndex(boton) == posicion.getX()) {
                botonEncontrado = (Button) boton;
            }
        }
        return botonEncontrado;
    }
}
