package org.logic;

import java.util.ArrayList;
import java.util.Iterator;

public class Juego {
    private final int CANTIDAD_ROBOTX1_INICIAL = 8;
    private final int CANTIDAD_ROBOTX2_INICIAL = 2;
    private final int TELEPORTS_SEGUROS_DISPONIBLES = 1;

    /**
     * Aumentamos la variable de nivelActual en uno, borramos todos los Obstaculos, reestablecenmos los teleportsSeguros
     * y agregamos nuevamente los Robots.
     */
    private void avanzarNivel() {
        nivelActual++;
        obstaculos.clear();
        jugador.setTeleportsSeguros(TELEPORTS_SEGUROS_DISPONIBLES);
        agregarRobots();
    }

    /**
     * Hace lo que dice que hace, agregar Obstaculos al ArrayList en la posicion que le mando por parametro.
     */
    private void agregarObstaculos(Coordenadas coordenadas) {
        obstaculos.add(new Obstaculo(coordenadas));
    }

    /**
     * Recorre los Robots y verifica cuales fueron eliminados, y si lo fueron, agrega un Obstaculo en su posicion.
     * Aca esta agregando un Obstaculo por c/u de los Robots destruidos, habria que ver una forma de que solo agrege
     * si colisionaron dos Robots, o recorrer el ArrayList de los Obstaculos y eliminar los Obstaculos que coinciden
     * en la misma posicion.
     */
    private void eliminarRobots() {
        Iterator<Enemigo> iterator = robots.iterator();
        while (iterator.hasNext()) {
            Enemigo robot = iterator.next();
            if (robot.getEliminado()) {
                agregarObstaculos(robot.getCoordenadas());
                iterator.remove();
            }
        }
    }

    /**
     * Agarra y crea un ArrayList con todos los Enemigos y se lo manda al metodo mover() del Robot.
     * Antes de eliminar los Robots que fueron eliminados, verifica si el Jugador fue eliminado.
     */
    private void moverRobots() {
        ArrayList<Enemigo> enemigos = new ArrayList<>();
        enemigos.addAll(robots);
        enemigos.addAll(obstaculos);

        for (Enemigo robot : robots)
            robot.mover(jugador.getCoordenadas(), enemigos);

        jugador.isEliminado(enemigos);
        eliminarRobots();
    }

    private int nivelActual;
    private final Mapa mapa;

    private final Jugador jugador;
    private final ArrayList<Enemigo> robots;
    private final ArrayList<Enemigo> obstaculos;

    public Juego(Coordenadas dimensionesMapa) {
        nivelActual = 1;
        mapa = new Mapa(dimensionesMapa);
        jugador = new Jugador(mapa.getCentroMapa(), TELEPORTS_SEGUROS_DISPONIBLES);
        robots = new ArrayList<>();
        obstaculos = new ArrayList<>();
    }

    public void agregarRobots() {
        for (int i = 0; i < CANTIDAD_ROBOTX1_INICIAL * nivelActual; i++)
            robots.add(new RobotX1(mapa.generarCoordenada(robots)));

        for (int i = 0; i < CANTIDAD_ROBOTX2_INICIAL * nivelActual; i++)
            robots.add(new RobotX2(mapa.generarCoordenada(robots)));
    }

    public void moverJugador(Coordenadas coordenadas) {
        jugador.mover(coordenadas);
        moverRobots();
    }

    public void teleportJugador(Coordenadas coordenadas) {
        if (coordenadas != null)
            jugador.teleportSeguro(coordenadas);
        else
            jugador.teleportAleatorio(mapa.generarCoordenada(null));

        moverRobots();
    }

    public boolean finalizado() {
        if (robots.isEmpty() && !jugador.getEliminado())
            avanzarNivel();

        return jugador.getEliminado();
    }



    public void borrar() {
        ArrayList<Enemigo> enemigos = new ArrayList<>();
        enemigos.addAll(robots);
        enemigos.addAll(obstaculos);

        for (Enemigo enemigo : enemigos)
            System.out.println("Enemigo: (%d, %d)" .formatted(enemigo.getCoordenadas().getX(), enemigo.getCoordenadas().getY()));
        System.out.println("Jugador (%d, %d)" .formatted(jugador.getCoordenadas().getX(), jugador.getCoordenadas().getY()));
    }
}
