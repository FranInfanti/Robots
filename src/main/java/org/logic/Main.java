package org.logic;

public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego(new Coordenadas(25,25));
        juego.agregarRobots();

        while (!juego.getJugadorEliminado()) {
            juego.estadoJuego();
            juego.borrar();
            System.out.println("---------------");
            juego.teleportJugador(null);
        }
        juego.borrar();

    }
}
