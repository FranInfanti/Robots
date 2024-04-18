package org.example;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.logic.Coordenadas;
import org.logic.Juego;

public class Vista {
    private static final int ALTURA_SCENE = 600;
    private static final int ANCHO_SCENE = 400;

    private void setListenerBoton(Button boton) {
        boton.setOnAction(_ -> {
            int x = GridPane.getColumnIndex(boton);
            int y = GridPane.getRowIndex(boton);
            if (telePortActivado) {
                telePortActivado = false;
                juego.teleportJugador(new Coordenadas(x,y));
            } else if (!juego.getJugadorEliminado())
                juego.moverJugador(new Coordenadas(x, y));
            teleportSeguro.setText(textos.getB2texto(juego.getTeleportsDisponibles()));
            pantallaJuego.mostrar(juego, layoutJuego, finDeJuego);
        });
    }

    private void setListenersGrillaJuego() {
        Coordenadas dimensionesMapa = juego.getDimensionesMapa();
        for (int x = 0; x < dimensionesMapa.getX(); x++) {
            for (int y = 0; y < dimensionesMapa.getY(); y++) {
                Button boton = new Button();
                boton.setMaxSize(25,25);
                boton.setMinSize(25,25);
                layoutJuego.add(boton, x, y);
                setListenerBoton(boton);
            }
        }
    }

    private void setVista() {
        layoutPrincipal.setSpacing(10);
        layoutPrincipal.setPrefSize(600,600);
        layoutPrincipal.setStyle(estilos.getFondoEstilo());
        layoutPrincipal.getChildren().addAll(layoutArriba, layoutJuego, layoutAbajo);

        layoutArriba.getChildren().add(tituloDeJuego);
        layoutArriba.setStyle(estilos.getFondoEstilo());
        tituloDeJuego.setStyle(estilos.getTituloEstilo());

        layoutAbajo.getChildren().addAll(teleportRandom, teleportSeguro, waitForRobots);
        layoutAbajo.setStyle(estilos.getFondoEstilo());
        for (Node nodo : layoutAbajo.getChildren()) {
            Button boton = (Button) nodo;
            boton.setPrefSize(350,60);
            boton.setStyle(estilos.getBotonEstilo());
        }

        setListenersGrillaJuego();

        layoutJuego.setMaxSize(400,400);
        layoutJuego.setHgap(3);
        layoutJuego.setVgap(3);
        layoutJuego.setStyle(estilos.getFondoEstilo());
    }

    private final Pantalla pantallaJuego;
    private final Textos textos;
    private final Estilos estilos;
    private boolean telePortActivado;

    private final Juego juego;
    private final FinDeJuego finDeJuego;

    private final VBox layoutPrincipal;
    private final HBox layoutArriba;
    private final GridPane layoutJuego;
    private final HBox layoutAbajo;

    private final Label tituloDeJuego;
    private final Button teleportRandom;
    private final Button teleportSeguro;
    private final Button waitForRobots;

    public Vista(Stage stage, Juego juego) {
        this.juego = juego;
        finDeJuego = new FinDeJuego();

        telePortActivado = false;
        pantallaJuego = new Pantalla();
        textos = new Textos();
        estilos = new Estilos();

        layoutPrincipal = new VBox();
        layoutArriba = new HBox();
        layoutJuego = new GridPane();
        layoutAbajo = new HBox();

        tituloDeJuego = new Label(textos.getTituloJuego());
        teleportRandom = new Button(textos.getB1texto());
        teleportSeguro = new Button(textos.getB2texto(juego.getTeleportsDisponibles()));
        waitForRobots = new Button(textos.getB3texto());

        setVista();

        pantallaJuego.mostrar(juego, layoutJuego, finDeJuego);
        stage.setScene(new Scene(layoutPrincipal, ALTURA_SCENE, ANCHO_SCENE));
        stage.setResizable(false);
        stage.show();
    }

    public void setListenerTeleportRandom() {
        teleportRandom.setOnAction(_ -> {
            if (juego.getJugadorEliminado())
                return;
            juego.teleportJugador(null);
            pantallaJuego.mostrar(juego, layoutJuego, finDeJuego);
        });
    }

    public void setListenerTeleportSeguro() {
        teleportSeguro.setOnAction(_ -> {
            if (juego.getJugadorEliminado())
                return;
            telePortActivado = true;
        });
    }

    public void setListenerWaitForRobots() {
        waitForRobots.setOnAction(_ -> {
            if (juego.getJugadorEliminado())
                return;
            juego.moverJugador(juego.getCoordenadasJugador());
            pantallaJuego.mostrar(juego, layoutJuego, finDeJuego);
        });
    }

    public void setListenerFinDeJuego() {
        layoutJuego.addEventHandler(FinDeJuego.FIN_DE_JUEGO_EVENT_TYPE, event -> {
            if (juego.getJugadorEliminado())
                return;
            else
                juego.estadoJuego();

            event.consume();
        });
    }
}
