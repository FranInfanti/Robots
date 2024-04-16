package org.example;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import org.logic.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


/**
 * JavaFX App
 */
public class App extends Application {

    private boolean tp = false;
    Textos textos = new Textos();
    Estilos estilos = new Estilos();

    @Override
    public void start(Stage stage) {
        Juego juego = new Juego(new Coordenadas(15,10));
        PantallaJuego pantallaJuego = new PantallaJuego();
        VBox layoutPrincipal = new VBox();
        layoutPrincipal.setSpacing(10);
        layoutPrincipal.setPrefSize(600,600);

        HBox layoutArriba = new HBox();

        Label l = new Label(textos.getTituloJuego());
        l.setStyle(estilos.getTituloEstilo());
        layoutArriba.getChildren().add(l);

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

        b1.setOnAction(ActionEvent -> {
                if (!juego.finalizado()) {
                        juego.teleportJugador(null);
                        pantallaJuego.mostrar(juego, layoutJuego);
                }
            }
        );

        b2.setOnAction(ActionEvent -> {
                if (juego.jugador.getTeleportsSeguros() > 0 && !juego.finalizado()) {
                        tp = true;
                }
            }
        );

        b3.setOnAction(ActionEvent -> {
                if (!juego.finalizado()){
                    juego.moverJugador(juego.jugador.getCoordenadas());
                    pantallaJuego.mostrar(juego, layoutJuego);
                }
            }
        );

        layoutPrincipal.setStyle(estilos.getFondoEstilo());
        layoutArriba.setStyle(estilos.getFondoEstilo());
        layoutAbajo.setStyle(estilos.getFondoEstilo());
        layoutJuego.setStyle(estilos.getFondoEstilo());

        layoutJuego.setMaxSize(400,400);
        for (int fil = 0; fil < 15; fil++) {
            for (int col = 0; col < 10; col++) {
                Button boton = new Button();
                boton.setMaxSize(25,25);
                boton.setMinSize(25,25);
                layoutJuego.add(boton, fil, col);
                boton.setOnAction(ActionEvent -> {
                            int x = layoutJuego.getColumnIndex(boton);
                            int y = layoutJuego.getRowIndex(boton);
                            if (tp) {
                                tp = false;
                                juego.jugador.teleportSeguro(new Coordenadas(x,y));
                                juego.jugador.setTeleportsSeguros(juego.jugador.getTeleportsSeguros() - 1);

                            } else if (!juego.jugador.getEliminado()) {
                                juego.moverJugador(new Coordenadas(x, y));
                                pantallaJuego.mostrar(juego, layoutJuego);
                            }
                        }
                );
            }
        }

        juego.agregarRobots();
        juego.borrar();
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