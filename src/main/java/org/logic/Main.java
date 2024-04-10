package org.logic;

public class Main {
    public static void main(String[] args) {
        // Supongo Matriz de 10x10
        Juego juego = new Juego(new Coordenadas(10,10), 0);

        juego.agregarRobots();
        System.out.println("--------------------");
        juego.moverRobots();
        juego.mostrarPosiciones();
        juego.eliminarRobots();
        System.out.println("--------------------");

        juego.moverRobots();
        juego.mostrarPosiciones();
        juego.eliminarRobots();
        System.out.println("--------------------");

        juego.moverRobots();
        juego.mostrarPosiciones();
        juego.eliminarRobots();
        System.out.println("--------------------");

        juego.moverRobots();
        juego.mostrarPosiciones();
        juego.eliminarRobots();
        System.out.println("--------------------");

    }
}
