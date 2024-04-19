package org.example;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.eventos.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.logic.Coordenadas;
import org.logic.Juego;

public class Vista {
    private static final int ALTURA_SCENE = 600;
    private static final int ANCHO_SCENE = 600;
    private static final int ALTURA_JUEGO = 400;
    private static final int ANCHO_JUEGO = 600;
    private static final int ALTO_GRILLA = 400;
    private static final int ANCHO_GRILLA = 400;
    private static final int ALTO_BOTON = 65;
    private static final int ANCHO_BOTON = 200;

    private void fireAlerta() {
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.setTitle(archivo.getTextoDeTituloJuego());
        alerta.setContentText("Llegaste al Nivel %d" .formatted(juego.getNivel()));
        alerta.setHeaderText("Gracias Por Jugar :)");
        alerta.show();
    }

    private void setListenerEventoInicio() {
        layoutGeneral.addEventHandler(EventoDeInicio.INICIO_DE_JUEGO_EVENT_TYPE, event -> {
            layoutInicio.setDisable(false);
            layoutInicio.setOpacity(1);
            event.consume();
        });
    }

    private void setListenerEventoFin() {
        layoutGrilla.addEventHandler(EventoDeFin.FIN_DE_JUEGO_EVENT_TYPE, event -> {
            if (juego.getJugadorEliminado()) {
                System.out.println("Nivel: %d" .formatted(juego.getNivel()));
                juego.borrar();
                layoutGeneral.fireEvent(eventoDeInicio);
            }
            else
                juego.estadoJuego();

            teleportSeguro.setText(archivo.getTextoDeTeleportSafely(juego.getTeleportsDisponibles()));
            juego.borrar();
            event.consume();
        });
    }

    private void setListenersBotones() {
        nuevoJuego.setOnAction(_ -> layoutGeneral.fireEvent(eventoDeInicio));

        inicioJuego.setOnAction(_ -> {
            layoutInicio.setDisable(true);
            layoutInicio.setOpacity(0);

            juego = new Juego(new Coordenadas((int) cantidadColumnas.getValue(), (int) cantidadFilas.getValue()));
            juego.agregarRobots();
            setGrillaLayoutJuego();
            pantalla.mostrar(juego, layoutGrilla, eventoDeFin, sizeBoton);
        });

        teleportRandom.setOnAction(_ -> {
            if (juego.getJugadorEliminado() || telePortActivado)
                return;
            juego.teleportJugador(null);
            pantalla.mostrar(juego, layoutGrilla, eventoDeFin, sizeBoton);
        });

        teleportSeguro.setOnAction(_ -> {
            if (!juego.getJugadorEliminado())
                telePortActivado = true;
        });

        waitForRobots.setOnAction(_ -> {
            if (juego.getJugadorEliminado())
                return;
            juego.moverJugador(juego.getCoordenadasJugador());
            pantalla.mostrar(juego, layoutGrilla, eventoDeFin, sizeBoton);
        });
    }

    private void setListenerBotonGrilla(Button boton) {
        boton.setOnAction(_ -> {
            int x = GridPane.getColumnIndex(boton);
            int y = GridPane.getRowIndex(boton);
            if (telePortActivado) {
                telePortActivado = false;
                juego.teleportJugador(new Coordenadas(x,y));
            } else if (!juego.getJugadorEliminado())
                juego.moverJugador(new Coordenadas(x, y));
            pantalla.mostrar(juego, layoutGrilla, eventoDeFin, sizeBoton);
        });
    }

    private int getSizeBoton(Coordenadas dimensionesMapa) {
        if (dimensionesMapa.getX() > dimensionesMapa.getY())
            return ANCHO_GRILLA / dimensionesMapa.getX();
        return ALTO_GRILLA / dimensionesMapa.getY();
    }

    private void setGrillaLayoutJuego() {
        Coordenadas dimensionesMapa = juego.getDimensionesMapa();
        sizeBoton = getSizeBoton(dimensionesMapa);
        layoutGrilla.getChildren().clear();
        for (int fil = 0; fil < dimensionesMapa.getX(); fil++) {
            for (int col = 0; col < dimensionesMapa.getY(); col++) {
                Button boton = new Button();
                boton.setMaxSize(sizeBoton, sizeBoton);
                boton.setMinSize(sizeBoton, sizeBoton);
                layoutGrilla.add(boton, fil, col);
                setListenerBotonGrilla(boton);
            }
        }
    }

    private void setVista() {
        layoutJuego.setMinSize(ANCHO_JUEGO, ALTURA_JUEGO);
        layoutJuego.setStyle(archivo.getEstiloDeVbox());
        layoutJuego.getChildren().addAll(layoutArriba, layoutGrilla, layoutAbajo);

        layoutArriba.getChildren().addAll(tituloJuego, nuevoJuego);
        layoutArriba.setStyle(archivo.getEstiloDeVbox());

        tituloJuego.setStyle(archivo.getEstiloDeTitulo());
        inicioJuego.setStyle(archivo.getEstiloDeBoton());
        nuevoJuego.setStyle(archivo.getEstiloDeBoton());

        cantidadFilas.setMaxWidth(200);
        cantidadColumnas.setMaxWidth(200);

        cantidadColumnas.setStyle(archivo.getEstiloSlider());
        cantidadFilas.setStyle(archivo.getEstiloSlider());
        layoutInicio.setStyle(archivo.getEstiloDeVbox());
        layoutInicio.getChildren().addAll(tituloJuego, cantidadFilas, cantidadColumnas, inicioJuego);

        layoutAbajo.getChildren().addAll(teleportRandom, teleportSeguro, waitForRobots);
        layoutAbajo.setStyle(archivo.getEstiloDeHbox());
        for (Node nodo : layoutAbajo.getChildren()) {
            Button boton = (Button) nodo;

            boton.setPrefSize(ANCHO_BOTON,ALTO_BOTON);
            boton.setStyle(archivo.getEstiloDeBoton());
        }

        layoutGrilla.setMinSize(ALTO_GRILLA, ANCHO_GRILLA);
        layoutGrilla.setStyle(archivo.getEstiloDeGridPane());

        layoutGeneral.getChildren().addAll(layoutJuego, layoutInicio);
    }

    private final Pantalla pantalla;
    private final Archivo archivo;
    private boolean telePortActivado;

    private Juego juego;
    private final EventoDeInicio eventoDeInicio;
    private final EventoDeFin eventoDeFin;

    private final StackPane layoutGeneral;
    private final VBox layoutInicio;
    private final VBox layoutJuego;
    private final VBox layoutArriba;
    private final GridPane layoutGrilla;
    private final HBox layoutAbajo;

    private final Label tituloJuego;
    private final Button teleportRandom;
    private final Button teleportSeguro;
    private final Button waitForRobots;
    private final Button inicioJuego;
    private final Button nuevoJuego;
    private final Slider cantidadColumnas;
    private final Slider cantidadFilas;
    private int sizeBoton;

    public Vista(Stage stage) {
        eventoDeInicio = new EventoDeInicio();
        eventoDeFin = new EventoDeFin();

        telePortActivado = false;
        pantalla = new Pantalla();
        archivo = new Archivo();

        layoutGeneral = new StackPane();
        layoutInicio = new VBox();
        layoutJuego = new VBox();
        layoutArriba = new VBox();
        layoutGrilla = new GridPane();
        layoutAbajo = new HBox();

        inicioJuego = new Button(archivo.getTextoDeInicioJuego());
        nuevoJuego = new Button(archivo.getTextoDeNuevoJuego());
        cantidadFilas = new Slider(10,30,10);
        cantidadColumnas = new Slider(10,30,10);

        tituloJuego = new Label(archivo.getTextoDeTituloJuego());
        teleportRandom = new Button(archivo.getTextoDeTeleportRandom());
        teleportSeguro = new Button();
        waitForRobots = new Button(archivo.getTextoDeWaitForRobots());

        setVista();

        stage.setScene(new Scene(layoutGeneral, ALTURA_SCENE, ANCHO_SCENE));
        stage.setTitle(archivo.getTextoDeTituloJuego());
        stage.setResizable(false);
        stage.show();
    }

    public void iniciar() {
        setListenerEventoInicio();
        setListenerEventoFin();
        setListenersBotones();
        layoutGeneral.fireEvent(eventoDeInicio);
    }
}