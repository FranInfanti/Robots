package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.example.eventos.EventoDeFin;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.logic.Coordenadas;
import org.logic.Juego;

public class Vista {
    private void setCssInteractivos() {
        cantidadFilas.setStyle(archivo.getCssSlider());
        cantidadColumnas.setStyle(archivo.getCssSlider());
        cantidadFilas.setBlockIncrement(Archivo.INCREMENTO_SLIDER);
        cantidadColumnas.setBlockIncrement(Archivo.INCREMENTO_SLIDER);
        inicioJuego.setStyle(archivo.getCssDeBoton(inicioJuego.isFocused()));
        nuevoJuego.setStyle(archivo.getCssDeBoton(nuevoJuego.isFocused()));
        for (Node nodo : layoutAbajo.getChildren())
            nodo.setStyle(archivo.getCssDeBoton(nodo.isFocused()));

        for (Node nodo : layoutGrilla.getChildren())
            nodo.setStyle(archivo.getCssCasilla(nodo.isFocused()));
    }

    private void setCssLayoutInicio() {
        tituloJuego.setStyle(archivo.getCssDeTitulo());
        layoutInicio.setStyle(archivo.getCssDeVbox());
        textoCantidadFilas.setStyle(archivo.getCssTextos());
        textoCantidadColumnas.setStyle(archivo.getCssTextos());
    }

    private void setCssLayoutJuego() {
        layoutJuego.setStyle(archivo.getCssDeVboxJuego());
    }

    private void setCssLayoutArriba() {
        instrucciones.setStyle(archivo.getCssTextos());
        layoutArriba.setStyle(archivo.getCssDeVbox());
    }

    private void setCssGrilla() {
        layoutGrilla.setStyle(archivo.getCssDeGridPane(Archivo.ANCHO_GRILLA, Archivo.ALTO_GRILLA));
    }

    private void setCssLayoutAbajo() {
        layoutAbajo.setStyle(archivo.getCssDeHbox());
    }

    private void setLayouts() {
        layoutInicio.getChildren().addAll(tituloJuego, textoCantidadFilas, cantidadFilas, textoCantidadColumnas, cantidadColumnas, inicioJuego);
        layoutJuego.getChildren().addAll(layoutArriba, layoutGrilla, layoutAbajo);
        layoutArriba.getChildren().addAll(nuevoJuego, instrucciones);
        layoutAbajo.getChildren().addAll(teleportRandom, teleportSeguro, waitForRobots);
        layoutGeneral.getChildren().addAll(layoutJuego, layoutInicio);

        setCssInteractivos();
        setCssLayoutInicio();
        setCssLayoutJuego();
        setCssLayoutArriba();
        setCssGrilla();
        setCssLayoutAbajo();
    }

    private void setListenerBotonGrilla(Button boton) {
        boton.setOnAction(_ -> {
            int x = GridPane.getColumnIndex(boton);
            int y = GridPane.getRowIndex(boton);
            if (teleportActivado) {
                teleportActivado = false;
                juego.teleportJugador(new Coordenadas(x,y));
            } else if (!juego.getJugadorEliminado())
                juego.moverJugador(new Coordenadas(x, y));
            pantalla.mostrar(juego, layoutGrilla, eventoDeFin);
        });
    }

    private int getSizeBoton(Coordenadas dimensionesMapa) {
        if (dimensionesMapa.getX() > dimensionesMapa.getY())
            return Archivo.ANCHO_GRILLA / dimensionesMapa.getX() - Archivo.ESPACIO_CASILLAS;
        return Archivo.ALTO_GRILLA / dimensionesMapa.getY() - Archivo.ESPACIO_CASILLAS;
    }

    private final Pantalla pantalla;
    private final Archivo archivo;
    private boolean teleportActivado;

    private Juego juego;
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
    private final Label textoCantidadFilas;
    private final Label textoCantidadColumnas;
    private final Label instrucciones;

    public Vista(Stage stage) {
        teleportActivado = false;
        archivo = new Archivo();

        pantalla = new Pantalla();
        eventoDeFin = new EventoDeFin();

        layoutGeneral = new StackPane();
        layoutInicio = new VBox();
        layoutJuego = new VBox();
        layoutArriba = new VBox();
        layoutGrilla = new GridPane();
        layoutAbajo = new HBox();

        cantidadFilas = new Slider(Archivo.MIN_FILA, Archivo.MAX_FILA, Archivo.MIN_FILA);
        cantidadColumnas = new Slider(Archivo.MIN_COLUMNA, Archivo.MAX_COLUMNA, Archivo.MIN_COLUMNA);
        textoCantidadFilas =  new Label(archivo.getTextoCantidadFilas());
        textoCantidadColumnas =  new Label(archivo.getTextoCantidadColumnas());
        instrucciones =  new Label(archivo.getInstrucciones());
        tituloJuego = new Label(archivo.getTextoDeTituloJuego());
        inicioJuego = new Button(archivo.getTextoDeInicioJuego());
        nuevoJuego = new Button(archivo.getTextoDeNuevoJuego());
        teleportRandom = new Button(archivo.getTextoDeTeleportAleatorio());
        teleportSeguro = new Button();
        waitForRobots = new Button(archivo.getTextoEsperarRobots());

        setLayouts();

        stage.setScene(new Scene(layoutGeneral, Archivo.ALTURA_SCENE, Archivo.ANCHO_SCENE));
        stage.setTitle(archivo.getTextoDeTituloJuego());
        stage.setResizable(false);
        stage.show();
    }

    public void iniciarJuego() {
        setMenuInicio(true);
        cantidadFilas.setValue(Archivo.MIN_FILA);
        cantidadColumnas.setValue(Archivo.MIN_COLUMNA);
    }

    public void mostrarPantalla() {
        pantalla.mostrar(juego, layoutGrilla, eventoDeFin);
    }

    public void actualizarEstiloNodos() {
        setCssInteractivos();
    }

    public void setMenuInicio(boolean activado) {
        layoutInicio.setDisable(!activado);
        layoutJuego.setDisable(activado);
        layoutInicio.setOpacity(activado ? Archivo.VISIBLE : 0);
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
        teleportSeguro.setText(archivo.getTextoDeTeleportSeguros(cantidad));
    }

    public void setTeleportActivado(boolean teleportActivado) {
        this.teleportActivado = teleportActivado;
    }

    public boolean getTeleportActivado() {
        return teleportActivado;
    }

    public int getCantidadColumnas() {
        return (int) cantidadColumnas.getValue();
    }

    public int getCantidadFilas() {
        return (int) cantidadFilas.getValue();
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