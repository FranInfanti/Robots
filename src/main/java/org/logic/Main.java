package org.logic;

public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego(new Coordenadas(20, 20));
        juego.agregarRobots();
        juego.borrar();
        System.out.println("--------------");

        while(!juego.juegoFinalizado()) {
            juego.moverJugador(new Coordenadas(10,10));
            juego.borrar();
            System.out.println("--------------");
        }


    }
}
