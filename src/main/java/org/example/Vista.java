package org.example;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.logic.Coordenadas;
import org.logic.Juego;

public class Vista {
    private void setListenersLayoutAbajo() {
        teleportRandom.setOnAction(_ -> {
            if (!juego.getJugadorEliminado()) {
                juego.teleportJugador(null);
                pantallaJuego.mostrar(juego, layoutJuego,sizeButton);
            }
        });

        teleportSeguro.setOnAction(_ -> {
            if (!juego.getJugadorEliminado())
                telePortActivado = true;
        });

        waitForRobots.setOnAction(_ -> {
            if (!juego.getJugadorEliminado()) {
                juego.moverJugador(juego.getCoordenadasJugador());
                pantallaJuego.mostrar(juego, layoutJuego,sizeButton);
            }
        });
    }


    private void setListenersNuevoJuego() {
        nuevoJuego.setOnAction(_ -> {
            layoutInicio.setDisable(false);
            layoutInicio.setOpacity(1);
        });
    }

    private void setListenersInicioJuego() {
        inicioJuego.setOnAction(_ -> {
            layoutInicio.setDisable(true);
            layoutInicio.setOpacity(0);

            juego = new Juego(new Coordenadas((int) columnas.getValue(), (int) filas.getValue()));
            setGrillaLayoutJuego();
            juego.agregarRobots();
            pantallaJuego.mostrar(juego, layoutJuego, sizeButton);
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
            pantallaJuego.mostrar(juego, layoutJuego,sizeButton);
        });
    }



    private int getSizeBoton(Coordenadas dimensionesMapa) {
        if (dimensionesMapa.getX() > dimensionesMapa.getY()) {
            return 400 / dimensionesMapa.getX();
        }
        return 400 / dimensionesMapa.getY();
    }

    private void setGrillaLayoutJuego() {
        Coordenadas dimensionesMapa = juego.getDimensionesMapa();

        sizeButton = getSizeBoton(dimensionesMapa);
        System.out.println(sizeButton);
        layoutJuego.getChildren().clear();
        for (int fil = 0; fil < dimensionesMapa.getX(); fil++) {
            for (int col = 0; col < dimensionesMapa.getY(); col++) {
                Button boton = new Button();
                boton.setMaxSize(sizeButton,sizeButton);
                boton.setMinSize(sizeButton,sizeButton);
                layoutJuego.add(boton, fil, col);
                setListenerBoton(boton);
            }
        }
    }

    private void setVista() {
        layoutPrincipal.setSpacing(10);
        layoutPrincipal.setMinSize(600,400);
        layoutPrincipal.setStyle(estilos.getFondoEstilo());
        layoutPrincipal.getChildren().addAll(layoutArriba, layoutJuego, layoutAbajo);

        layoutArriba.getChildren().addAll(tituloDeJuego, nuevoJuego);

        layoutArriba.setSpacing(20);
        layoutArriba.setStyle(estilos.getFondoEstilo());
        tituloDeJuego.setStyle(estilos.getTituloEstilo());

        inicioJuego.setStyle(estilos.getBotonEstilo());
        nuevoJuego.setStyle(estilos.getBotonEstilo());

        layoutInicio.setSpacing(20);
        filas.setMaxWidth(200);
        columnas.setMaxWidth(200);
        filas.setOpacity(0.2);
        columnas.setOpacity(0.2);

        layoutInicio.setStyle(estilos.getFondoEstilo());
        layoutInicio.getChildren().addAll(tituloDeJuego, filas, columnas, inicioJuego);


        layoutAbajo.getChildren().addAll(teleportRandom, teleportSeguro, waitForRobots);
        layoutAbajo.setStyle(estilos.getFondoEstilo());
        for (Node nodo : layoutAbajo.getChildren()) {
            Button boton = (Button) nodo;
            boton.setPrefSize(200,40);
            boton.setStyle(estilos.getBotonEstilo());
        }


        setListenersInicioJuego();
        setListenersNuevoJuego();
        setListenersLayoutAbajo();

        layoutJuego.setMinSize(400,400);
        layoutJuego.setHgap(3);
        layoutJuego.setVgap(3);
        layoutJuego.setStyle(estilos.getFondoEstilo());
    }

    private final PantallaJuego pantallaJuego;
    private final Textos textos;
    private final Estilos estilos;
    private boolean telePortActivado;

    private Juego juego;

    private final StackPane layoutGeneral;
    private final VBox layoutInicio;
    private final VBox layoutPrincipal;
    private final VBox layoutArriba;
    private final GridPane layoutJuego;
    private final HBox layoutAbajo;

    private final Label tituloDeJuego;
    private final Button teleportRandom;
    private final Button teleportSeguro;
    private final Button waitForRobots;
    private final Button inicioJuego;
    private final Button nuevoJuego;
    private final Slider columnas;
    private final Slider filas;
    private int sizeButton;

    public Vista(Stage stage) {
        telePortActivado = false;
        pantallaJuego = new PantallaJuego();
        textos = new Textos();
        estilos = new Estilos();

        layoutGeneral = new StackPane();
        layoutInicio = new VBox();

        layoutPrincipal = new VBox();
        layoutArriba = new VBox();
        layoutJuego = new GridPane();
        layoutAbajo = new HBox();

        inicioJuego = new Button("Iniciar Juego");
        nuevoJuego = new Button("Nuevo Juego");
        filas = new Slider(10,30,10);
        columnas = new Slider(10,30,10);



        tituloDeJuego = new Label(textos.getTituloJuego());
        teleportRandom = new Button(textos.getB1texto());
        teleportSeguro = new Button(textos.getB2texto());
        waitForRobots = new Button(textos.getB3texto());

        setVista();

        layoutGeneral.getChildren().addAll(layoutPrincipal, layoutInicio);
        stage.setScene(new Scene(layoutGeneral, 600, 600));
        stage.setResizable(false);
        stage.show();
    }
}
