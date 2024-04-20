package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.example.eventos.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.logic.Coordenadas;
import org.logic.Juego;

public class Vista {
    private static final int ALTURA_SCENE = 650;
    private static final int ANCHO_SCENE = 650;
    private static final int ALTO_GRILLA = 400;
    private static final int ANCHO_GRILLA = 400;
    private static final int MIN_FIL = 10;
    private static final int MAX_FIL = 30;
    private static final int MIN_COL = 10;
    private static final int MAX_COL = 30;

    private void setLayoutInicio() {
        cantidadColumnas.setStyle(archivo.getCssSlider(false));
        cantidadFilas.setStyle(archivo.getCssSlider(false));
        inicioJuego.setStyle(archivo.getCssDeBoton(false));
        layoutInicio.setStyle(archivo.getCssDeVbox());
        layoutInicio.getChildren().addAll(tituloJuego, cantidadFilas, cantidadColumnas, inicioJuego);
    }

    private void setLayoutJuego() {
        layoutJuego.setStyle(archivo.getCssDeVboxJuego());
        layoutJuego.getChildren().addAll(layoutArriba, layoutGrilla, layoutAbajo);
    }

    private void setLayoutArriba() {
        tituloJuego.setStyle(archivo.getCssDeTitulo());
        nuevoJuego.setStyle(archivo.getCssDeBoton(false));
        layoutArriba.setStyle(archivo.getCssDeVbox());
        layoutArriba.getChildren().addAll(tituloJuego, nuevoJuego);
    }

    private void setGrilla() {
        layoutGrilla.setStyle(archivo.getCssDeGridPane(ANCHO_GRILLA, ALTO_GRILLA));
    }

    private void setLayoutAbajo() {
        layoutAbajo.getChildren().addAll(teleportRandom, teleportSeguro, waitForRobots);
        layoutAbajo.setStyle(archivo.getCssDeHbox());
        for (Node nodo : layoutAbajo.getChildren())
            nodo.setStyle(archivo.getCssDeBoton(false));
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
            pantalla.mostrar(juego, layoutGrilla, eventoDeFin, telePortActivado);
        });
    }

    private int getSizeBoton(Coordenadas dimensionesMapa) {
        if (dimensionesMapa.getX() > dimensionesMapa.getY())
            return ANCHO_GRILLA / dimensionesMapa.getX();
        return ALTO_GRILLA / dimensionesMapa.getY();
    }

    private void setEstiloNodo(Node nodo) {
        boolean esSlider = nodo instanceof Slider;
        nodo.setStyle(esSlider ? archivo.getCssSlider(true) : archivo.getCssDeBoton(false));
        if (nodo.isFocused())
            nodo.setStyle(esSlider ? archivo.getCssSlider(true) : archivo.getCssDeBoton(true));
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
        cantidadFilas = new Slider(MIN_FIL, MAX_FIL, MIN_FIL);
        cantidadColumnas = new Slider(MIN_COL, MAX_COL, MIN_COL);

        tituloJuego = new Label(archivo.getTextoDeTituloJuego());
        teleportRandom = new Button(archivo.getTextoDeTeleportRandom());
        teleportSeguro = new Button();
        waitForRobots = new Button(archivo.getTextoDeWaitForRobots());

        setLayoutJuego();
        setLayoutArriba();
        setLayoutInicio();
        setLayoutAbajo();
        setGrilla();
        layoutGeneral.getChildren().addAll(layoutJuego, layoutInicio);

        stage.setScene(new Scene(layoutGeneral, ALTURA_SCENE, ANCHO_SCENE));
        stage.setTitle(archivo.getTextoDeTituloJuego());
        stage.setResizable(false);
        stage.show();
    }

    public void iniciarJuego() {
        layoutGeneral.fireEvent(eventoDeInicio);
    }

    public void mostrarPantalla() {
        pantalla.mostrar(juego, layoutGrilla, eventoDeFin, telePortActivado);
    }

    public void actualizarEstilosNodos() {
        setEstiloNodo(teleportRandom);
        setEstiloNodo(teleportSeguro);
        setEstiloNodo(waitForRobots);
        setEstiloNodo(inicioJuego);
        setEstiloNodo(nuevoJuego);
        setEstiloNodo(cantidadColumnas);
        setEstiloNodo(cantidadFilas);

        if (cantidadColumnas.isFocused())
            cantidadColumnas.setStyle(archivo.getCssSlider(true));

        if (cantidadFilas.isFocused())
            cantidadFilas.setStyle(archivo.getCssSlider(true));

        for (Node nodo : layoutGrilla.getChildren()) {
            Button boton = (Button) nodo;
            boton.setStyle(archivo.getCssCasilla(false) + boton.getWidth() / 5);
            if (boton.isFocused())
                boton.setStyle(archivo.getCssCasilla(true) + boton.getWidth() / 5);
        }
    }

    public void setMenuInicio(boolean activado) {
        layoutInicio.setDisable(!activado);
        layoutInicio.setOpacity(activado ? 1 : 0);
        layoutJuego.setDisable(activado);
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public void setGrillaLayoutJuego() {
        layoutGrilla.getChildren().clear();
        int sizeBoton = getSizeBoton(juego.getDimensionesMapa());
        for (int x = 0; x < juego.getDimensionesMapa().getX(); x++) {
            for (int y = 0; y < juego.getDimensionesMapa().getY(); y++) {
                Button boton = new Button();
                boton.setMaxSize(sizeBoton, sizeBoton);
                boton.setMinSize(sizeBoton, sizeBoton);
                layoutGrilla.add(boton, x, y);
                setListenerBotonGrilla(boton);
            }
        }
    }

    public void setTeleportSeguro(int cantidad) {
        teleportSeguro.setText(archivo.getTextoDeTeleportSafely(cantidad));
    }

    public void setTelePortActivado(boolean telePortActivado) {
        this.telePortActivado = telePortActivado;
    }

    public boolean getTelePortActivado() {
        return telePortActivado;
    }

    public int getCantidadColumnas() {
        return (int) cantidadColumnas.getValue();
    }

    public int getCantidadFilas() {
        return (int) cantidadFilas.getValue();
    }

    public void setListenerEventoInicio(EventHandler<EventoDeInicio> event) {
        layoutGeneral.addEventHandler(EventoDeInicio.INICIO_DE_JUEGO_EVENT_TYPE, event);
    }

    public void setListenerEventoFin(EventHandler<EventoDeFin> event) {
        layoutGeneral.addEventHandler(EventoDeFin.FIN_DE_JUEGO_EVENT_TYPE, event);
    }

    public void setListenerInicioJuego(EventHandler<ActionEvent> event) {
        inicioJuego.setOnAction(event);
    }

    public void setListenerNuevoJuego(EventHandler<ActionEvent> event) {
        nuevoJuego.setOnAction(event);
    }

    public void setListenerTeleportRandom(EventHandler<ActionEvent> event) {
        teleportRandom.setOnAction(event);
    }

    public void setListenerTeleportSeguro(EventHandler<ActionEvent> event) {
        teleportSeguro.setOnAction(event);
    }

    public void setListenerWaitForRobots(EventHandler<ActionEvent> event) {
        waitForRobots.setOnAction(event);
    }

    public void setListenerKeyPressed(EventHandler<KeyEvent> event) {
        layoutGeneral.addEventHandler(KeyEvent.KEY_RELEASED, event);
    }

    public void setListenerMouseReleased(EventHandler<MouseEvent> event) {
        layoutGeneral.setOnMouseReleased(event);
    }
}