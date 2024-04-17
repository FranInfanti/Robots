package org.example;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.logic.Coordenadas;
import org.logic.Juego;

public class Vista {
    private void setListenersLayoutAbajo() {
        teleportRandom.setOnAction(_ -> {
            if (!juego.getJugadorEliminado()) {
                juego.teleportJugador(null);
                pantallaJuego.mostrar(juego, layoutJuego);
            }
        });

        teleportSeguro.setOnAction(_ -> {
            if (!juego.getJugadorEliminado())
                telePortActivado = true;
        });

        waitForRobots.setOnAction(_ -> {
            if (!juego.getJugadorEliminado()) {
                juego.moverJugador(juego.getCoordenadasJugador());
                pantallaJuego.mostrar(juego, layoutJuego);
            }
        });
    }

    private void setListenerBoton(Button boton) {
        boton.setOnAction(_ -> {
            int x = GridPane.getColumnIndex(boton);
            int y = GridPane.getRowIndex(boton);
            if (telePortActivado) {
                telePortActivado = false;
                juego.teleportJugador(new Coordenadas(x,y));
            } else if (!juego.getJugadorEliminado())
                juego.moverJugador(new Coordenadas(x, y));
            pantallaJuego.mostrar(juego, layoutJuego);
        });
    }

    private void setGrillaLayoutJuego() {
        Coordenadas dimensionesMapa = juego.getDimensionesMapa();
        for (int fil = 0; fil < dimensionesMapa.getX(); fil++) {
            for (int col = 0; col < dimensionesMapa.getY(); col++) {
                Button boton = new Button();
                boton.setMaxSize(25,25);
                boton.setMinSize(25,25);
                layoutJuego.add(boton, fil, col);
                setListenerBoton(boton);
            }
        }
    }

    private void setVista() {
        layoutPrincipal.setSpacing(10);
        layoutPrincipal.setPrefSize(600,600);
        layoutPrincipal.setStyle(estilos.getFondoEstilo());
        layoutPrincipal.getChildren().addAll(layoutArriba, layoutJuego, layoutAbajo);

        layoutArriba.getChildren().addAll(tituloDeJuego, menuDesplegable);
        layoutArriba.setStyle(estilos.getFondoEstilo());
        tituloDeJuego.setStyle(estilos.getTituloEstilo());
        menuDesplegable.setStyle(estilos.getMenuEstilo());
        menuDesplegable.setDisable(true);
        menuDesplegable.setPrefSize(5,5);

        layoutAbajo.getChildren().addAll(teleportRandom, teleportSeguro, waitForRobots);
        layoutAbajo.setStyle(estilos.getFondoEstilo());
        for (Node nodo : layoutAbajo.getChildren()) {
            Button boton = (Button) nodo;
            boton.setPrefSize(200,40);
            boton.setStyle(estilos.getBotonEstilo());
        }

        setGrillaLayoutJuego();
        setListenersLayoutAbajo();

        layoutJuego.setMaxSize(400,400);
        layoutJuego.setHgap(3);
        layoutJuego.setVgap(3);
        layoutJuego.setStyle(estilos.getFondoEstilo());
    }

    private final PantallaJuego pantallaJuego;
    private final Textos textos;
    private final Estilos estilos;
    private boolean telePortActivado;

    private final Juego juego;

    private final VBox layoutPrincipal;
    private final HBox layoutArriba;
    private final GridPane layoutJuego;
    private final HBox layoutAbajo;

    private final ChoiceBox menuDesplegable;
    private final Label tituloDeJuego;
    private final Button teleportRandom;
    private final Button teleportSeguro;
    private final Button waitForRobots;

    public Vista(Stage stage, Juego juego) {
        this.juego = juego;

        telePortActivado = false;
        pantallaJuego = new PantallaJuego();
        textos = new Textos();
        estilos = new Estilos();

        layoutPrincipal = new VBox();
        layoutArriba = new HBox();
        layoutJuego = new GridPane();
        layoutAbajo = new HBox();

        menuDesplegable = new ChoiceBox<>();
        tituloDeJuego = new Label(textos.getTituloJuego());
        teleportRandom = new Button(textos.getB1texto());
        teleportSeguro = new Button(textos.getB2texto());
        waitForRobots = new Button(textos.getB3texto());

        setVista();

        pantallaJuego.mostrar(juego, layoutJuego);
        stage.setScene(new Scene(layoutPrincipal, 600, 400));
        stage.setResizable(false);
        stage.show();
    }
}
