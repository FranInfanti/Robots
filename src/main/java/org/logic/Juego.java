package org.logic;

import javafx.scene.robot.Robot;

import java.util.ArrayList;
import java.util.Random;

public class Juego {
    private Jugador jugador;
    private ArrayList<Enemigo> robots;
    private ArrayList<Obstaculo> obstaculos;

    private final Coordenadas tamanio;
    private Coordenadas posicionInicial;
    private int nivelActual;
    private Random rand = new Random();


    public Juego (Coordenadas tamanio, int nivelActual) {
        this.nivelActual = nivelActual;
        this.robots = new ArrayList<Enemigo>();
        this.obstaculos =  new ArrayList<Obstaculo>();
        this.tamanio = tamanio;

        posicionInicial = new Coordenadas(tamanio.getX() / 2, tamanio.getY() / 2);
        jugador = new Jugador(posicionInicial);
    }

    //buscar una forma de calcular la cantidad de robots a traves del tamaño del mapa (me salió esto no se) borrar los prints despues
    public void agregarRobots() {
        int cantidad = (tamanio.getX() * tamanio.getY()) / (tamanio.getX() + tamanio.getY());
        cantidad += nivelActual * 3;

        for (int i = 0; i < cantidad; i++) {
            Coordenadas posicion = nuevaPosicion();
            if (rand.nextInt(10) < 6){
                robots.add(new RobotX1(posicion));
                System.out.println("Se creo un robot 1 en la posicion (%d,%d)".formatted(posicion.getX(),posicion.getY()));
            } else {
                robots.add(new RobotX2(posicion));
                System.out.println("Se creo un robot 2 en la posicion (%d,%d)".formatted(posicion.getX(),posicion.getY()));
            }
        }
    }

    //saca un robot de la lista,
    //si habia una explosion ahi, no se hace nada
    //si habia otro robot, se elimina y crea un obstaculo ahi
    //si no hay nada lo vuelve a agregar al final de la lista
    public void eliminarRobots() {
        int n = robots.size();
        while (n > 0) {
            Enemigo robot = robots.removeFirst();

            if (existeObstaculoPosicion(robot.getCoordenadas()) == null) {
                Enemigo robotChocado = existeRobotPosicion(robot.getCoordenadas());
                if (robotChocado != null){
                    obstaculos.add(new Obstaculo(robot.getCoordenadas()));
                    robots.remove(robotChocado);
                    System.out.println("Explosion en posicion (%d,%d)".formatted(robot.getCoordenadas().getX(),robot.getCoordenadas().getY()));
                    n -= 1;
                } else {
                    robots.addLast(robot);
                }
            }
            n -= 1;
        }
    }

    public void moverRobots() {
        for (Enemigo robot : robots) {
            robot.moverEnemigo(jugador.getCoordenadas());
        }
    }

    //funcion temporal (luego borrar)
    public void mostrarPosiciones() {
        for (Enemigo robot : robots) {
            System.out.println("(%d, %d)" .formatted(robot.getCoordenadas().getX(), robot.getCoordenadas().getY()));
        }
        for (Enemigo obstaculo : obstaculos) {
            System.out.println("-(%d, %d)-" .formatted(obstaculo.getCoordenadas().getX(), obstaculo.getCoordenadas().getY()));
        }

    }

    //genera una posicion valida
    private Coordenadas nuevaPosicion(){
        Coordenadas posicion = new Coordenadas(0,0);
        do {
            posicion.setX((int) rand.nextInt(tamanio.getX()));
            posicion.setY((int) rand.nextInt(tamanio.getY()));
        } while (existeRobotPosicion(posicion) != null || posicion.esIgual(posicionInicial));
        return posicion;
    };

    //recorre ambas listas a ver si hay una posicion que coinciden
    private Enemigo existeRobotPosicion(Coordenadas posicion) {
        for (Enemigo robot : robots) {
            if (posicion.esIgual(robot.getCoordenadas())){
                return robot;
            }
        }
        return null;
    }

    private Enemigo existeObstaculoPosicion(Coordenadas posicion) {
        for (Enemigo obstaculo : obstaculos) {
            if (posicion.esIgual(obstaculo.getCoordenadas())){
                return obstaculo;
            }
        }
        return null;
    }
}
