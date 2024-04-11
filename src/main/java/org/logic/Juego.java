package org.logic;

import java.util.LinkedList;
import java.util.List;

public class Juego {
    private final int CANTIDAD_ROBOTX1_INICIAL = 8;
    private final int CANTIDAD_ROBOTX2_INICIAL = 2;
    private final int TELEPORTS_SEGUROS_DISPONIBLES = 1;

    private void avanzarNivel() {
        nivelActual++;
        eliminarObstaculos();
        agregarRobots();
    }

    private void eliminarRobots(Enemigo enemigoDestruido) {
        robots.remove(enemigoDestruido);
    }

    private void agregarObstaculos(Coordenadas coordenadas) {
        obstaculos.add(new Obstaculo(coordenadas));
    }

    private void eliminarObstaculos() {
        obstaculos.clear();
    }

    /**
     * Muevo los Robots y verifico si se generaron obstaculos. En caso de que haya obstaculos, elimino esos robots que colisionaron
     * y los remplazo por una explosion. Habria que ver de transformar ese for en otra cosa, porque no queda muy bien.
     */
    private void moverRobots() {
        for (Enemigo robot : robots) {
            mapa.removeCoordenadaEnUso(robot.getCoordenadas());
            robot.moverEnemigo(jugador.getCoordenadas());
            mapa.setCoordenadaEnUso(robot.getCoordenadas());
        }

        // Este codigo es muy feo, deberiamos pensar otra forma, pero no se me ocurre.
        for (Object robot1 : robots.toArray()) {
            for (Object robot2 : robots.toArray()) {
                if (!robot1.equals(robot2)) {
                    Enemigo enemigo1 = (Enemigo) robot1;
                    Enemigo enemigo2 = (Enemigo) robot2;
                    if (enemigo1.getCoordenadas().esIgual(enemigo2.getCoordenadas())) {
                        agregarObstaculos(enemigo1.getCoordenadas());
                        mapa.removeCoordenadaEnUso(enemigo1.getCoordenadas());
                        mapa.removeCoordenadaEnUso(enemigo2.getCoordenadas());
                        eliminarRobots(enemigo1);
                        eliminarRobots(enemigo2);
                    }
                }
            }
        }
    }

    /**
     * Esta funcion lo que hace es verificar (usando el metodo de mapa) si hay algo en la misma posicion que el.
     * Hay que remover la posicion del jugador primero, porque sino siempra va a dar true.
     */
    private void jugadorEliminado() {
        mapa.removeCoordenadaEnUso(jugador.getCoordenadas());
        if (mapa.coordenadaOcupada(jugador.getCoordenadas())) {
            jugador = null;
            return;
        }
        mapa.setCoordenadaEnUso(jugador.getCoordenadas());
    }

    private int nivelActual;
    private int teleportSegurosDisponibles;
    private final Mapa mapa;

    private Jugador jugador;
    private final List<Enemigo> robots;
    private final List<Obstaculo> obstaculos;

    public Juego (Coordenadas dimensionDelMapa) {
        nivelActual = 1;
        teleportSegurosDisponibles = TELEPORTS_SEGUROS_DISPONIBLES;

        mapa = new Mapa(dimensionDelMapa);
        robots = new LinkedList<>();
        obstaculos =  new LinkedList<>();
        jugador = new Jugador(mapa.getCentroMapa());

        this.mapa.setCoordenadaEnUso(jugador.getCoordenadas());
    }

    /**
     * Este metodo agrega los RobotsX1 y X2 a la lista de enemigos. La cantidad inicial siempre es fija,
     * para X1 son 8 y para X2 son 2. A medida que aumentan los niveles se aumentan ambos enemigos, osea en el primer
     * nivel hay 8*1 X1 y en el segundo nivel hay 8*2 y en el tercero 8*3 RobotsX1. Lo mismo para los X2 pero empezando en 2.
     */
    public void agregarRobots() {
        int cantidadRobotX1 = CANTIDAD_ROBOTX1_INICIAL * nivelActual;
        int cantidadRobotX2 = CANTIDAD_ROBOTX2_INICIAL * nivelActual;

        for (int i = 0; i < cantidadRobotX1; i++)
            robots.add(new RobotX1(mapa.generarCoordenada(true)));

        for (int i = 0; i < cantidadRobotX2; i++)
            robots.add(new RobotX2(mapa.generarCoordenada(true)));
    }

    /**
     * Recibimos la posicion del mouse y el Jugador se encarga de moverse acorde a la posicion del mouse, ademas
     * despues movemos instantaneamente a los Robots, para no tener que hacer juego.moverRobots(), porque siempre
     * que se mueva el Jugador, se mueven los Robots.
     */
    public void moverJugador(Coordenadas posicion) {
        mapa.removeCoordenadaEnUso(jugador.getCoordenadas());
        jugador.moverJugador(posicion);
        mapa.setCoordenadaEnUso(jugador.getCoordenadas());
        moverRobots();
        jugadorEliminado();
    }

    /**
     * El Jugador me manda a donde se quiere mover, usando el mouse, y si tiene teleportsSeguros disponibles,
     * entonces lo movemos. Podemos usar directamente el metodo de moverJugador y agregando un par mas de cosas.
     */
    public void teleportSeguroJugador(Coordenadas coordenadas) {
        if (getTeleportSegurosDisponibles() == 0)
            return;

        mapa.removeCoordenadaEnUso(jugador.getCoordenadas());
        moverJugador(coordenadas);
        teleportSegurosDisponibles--;
        jugadorEliminado();
    }

    /**
     * Teletransporta a cualquier lugar del mapa.
     */
    public void teleportAleatorioJugador() {
        mapa.removeCoordenadaEnUso(jugador.getCoordenadas());
        moverJugador(mapa.generarCoordenada(false));
        moverRobots();
        jugadorEliminado();
    }

    /**
     * El juego solo termina si muere el Jugador
     */
    public boolean juegoFinalizado() {
        boolean robotsEliminados = robots.isEmpty();
        boolean jugadorEliminado = jugador == null;

        // Esto cubre el caso de que si hay 2 robots y el jugador pisa uno y el otro se le viene encima, dejando los 3 en la misma posicion
        if (robotsEliminados && !jugadorEliminado)
            avanzarNivel();

        return jugadorEliminado;
    }

    public void setDimensionMapa(Coordenadas dimensionDelMapa) {
        mapa.setDimensionMapa(dimensionDelMapa);
    }

    /**
     * Todo lo que esta aca abajo seria para que la vista pueda mostrar esta informacion por la pantalla.
     */

    public int getNivelActual() {
        return nivelActual;
    }

    public int getTeleportSegurosDisponibles() {
        return teleportSegurosDisponibles;
    }

    public Coordenadas getPosicionJugador() {
        return jugador.getCoordenadas();
    }

    public Coordenadas getPosicionRobotX1(int indice) {
        return null;
    }

    public Coordenadas getPosicionRobotX2(int indice) {
        return null;
    }

    public void borrar() {
        for (int i = 0; i < mapa.getCantidadCoordenadasEnUso(); i++)
            System.out.println("(%d, %d)" .formatted(mapa.getCoordenadaEnUso(i).getX(), mapa.getCoordenadaEnUso(i).getY()));
    }
}
