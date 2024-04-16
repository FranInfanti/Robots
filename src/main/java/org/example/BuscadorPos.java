package org.example;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.logic.Coordenadas;

public class BuscadorPos {
    public Button getBotonPorPosicion(Coordenadas posicion, GridPane gridPane) {
        Button buscado = null;
        ObservableList<Node> botones = gridPane.getChildren();
        for (Node boton : botones) {
            if (posicion.esIgual(new Coordenadas(GridPane.getColumnIndex(boton), GridPane.getRowIndex(boton))))
                buscado = (Button) boton;
        }
        return buscado;
    }
}
