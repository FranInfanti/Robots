package org.logic;

public class Main {
    public static void main(String[] args) {
        // Supongo Matriz de 10x10
        Jugador jugador = new Jugador(new Coordenadas(5,5));

        // Inicializo robotsX1 en posiciones random
        RobotX2 robot1 = new RobotX2(new Coordenadas(1,1));
        RobotX2 robot2 = new RobotX2(new Coordenadas(2,6));
        RobotX2 robot3 = new RobotX2(new Coordenadas(5,8));
        RobotX2 robot4 = new RobotX2(new Coordenadas(9,8));
        RobotX2 robot5 = new RobotX2(new Coordenadas(8,5));
        RobotX2 robot6 = new RobotX2(new Coordenadas(8,1));

        robot1.moverEnemigo(jugador.getCoordenadas());
        robot2.moverEnemigo(jugador.getCoordenadas());
        robot3.moverEnemigo(jugador.getCoordenadas());
        robot4.moverEnemigo(jugador.getCoordenadas());
        robot5.moverEnemigo(jugador.getCoordenadas());
        robot6.moverEnemigo(jugador.getCoordenadas());

        System.out.println("(%d, %d)" .formatted(robot1.getCoordenadas().getX(), robot1.getCoordenadas().getY()));
        System.out.println("(%d, %d)" .formatted(robot2.getCoordenadas().getX(), robot2.getCoordenadas().getY()));
        System.out.println("(%d, %d)" .formatted(robot3.getCoordenadas().getX(), robot3.getCoordenadas().getY()));
        System.out.println("(%d, %d)" .formatted(robot4.getCoordenadas().getX(), robot4.getCoordenadas().getY()));
        System.out.println("(%d, %d)" .formatted(robot5.getCoordenadas().getX(), robot5.getCoordenadas().getY()));
        System.out.println("(%d, %d)" .formatted(robot6.getCoordenadas().getX(), robot6.getCoordenadas().getY()));
    }
}
