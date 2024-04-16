package org.logic;

public class Main {
    public static void main(String[] args) {
        Juego juego = new Juego(new Coordenadas(10,10));
        juego.agregarRobots();

        do {
            juego.borrar();
            System.out.println("-----------------");
            juego.teleportJugador(null);
            juego.borrar();
            System.out.println("-----------------");
        } while(!juego.finalizado());
    }
}
