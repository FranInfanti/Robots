package org.example;

import org.logic.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private boolean telePortActivado = false;
    private final Textos textos = new Textos();
    private final Estilos estilos = new Estilos();

    @Override
    public void start(Stage stage) {
        Juego juego = new Juego(new Coordenadas(15,10));
        juego.agregarRobots();
        juego.borrar();

        PantallaJuego pantallaJuego = new PantallaJuego();
        VBox layoutPrincipal = new VBox();
        layoutPrincipal.setSpacing(10);
        layoutPrincipal.setPrefSize(600,600);

        HBox layoutArriba = new HBox();

        Label tituloDeJuego = new Label(textos.getTituloJuego());
        tituloDeJuego.setStyle(estilos.getTituloEstilo());
        layoutArriba.getChildren().add(tituloDeJuego);

        HBox layoutAbajo = new HBox();
        layoutAbajo.setPrefSize(600, 150);

        Button b1 = new Button(textos.getB1texto());
        Button b2 = new Button(textos.getB2texto());
        Button b3 = new Button(textos.getB3texto());

        layoutAbajo.getChildren().add(b1);
        layoutAbajo.getChildren().add(b2);
        layoutAbajo.getChildren().add(b3);

        for (Node boton : layoutAbajo.getChildren()) {
            Button b = (Button) boton;
            b.setPrefSize(200,40);
            b.setStyle(estilos.getBotonEstilo());
        }

        GridPane layoutJuego = new GridPane();
        layoutJuego.setHgap(3);
        layoutJuego.setVgap(3);

        b1.setOnAction(_ -> {
            if (!juego.getJugadorEliminado()) {
                juego.teleportJugador(null);
                pantallaJuego.mostrar(juego, layoutJuego);
            }
        });

        b2.setOnAction(_ -> {
            if (!juego.getJugadorEliminado())
                telePortActivado = true;
        });

        b3.setOnAction(_ -> {
            if (!juego.getJugadorEliminado()){
                juego.moverJugador(juego.getCoordenadasJugador());
                pantallaJuego.mostrar(juego, layoutJuego);
            }
        });

        layoutPrincipal.setStyle(estilos.getFondoEstilo());
        layoutArriba.setStyle(estilos.getFondoEstilo());
        layoutAbajo.setStyle(estilos.getFondoEstilo());
        layoutJuego.setStyle(estilos.getFondoEstilo());

        layoutJuego.setMaxSize(400,400);
        Coordenadas dimensionesMapa = juego.getDimensionesMapa();
        for (int fil = 0; fil < dimensionesMapa.getX(); fil++) {
            for (int col = 0; col < dimensionesMapa.getY(); col++) {
                Button boton = new Button();
                boton.setMaxSize(25,25);
                boton.setMinSize(25,25);
                layoutJuego.add(boton, fil, col);

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
        }

        pantallaJuego.mostrar(juego, layoutJuego);
        layoutPrincipal.getChildren().addAll(layoutArriba, layoutJuego, layoutAbajo);
        Scene scene = new Scene(layoutPrincipal, 600, 400);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}