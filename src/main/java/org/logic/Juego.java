package org.logic;

import org.logic.personajes.*;
import java.util.*;

public class Juego {
    private static final int CANTIDAD_ROBOTX1_INICIAL = 8;
    private static final int CANTIDAD_ROBOTX2_INICIAL = 2;
    private static final int TELEPORTS_SEGUROS_DISPONIBLES = 1;

    private void agregarRobots() {
        for (int i = 0; i < CANTIDAD_ROBOTX1_INICIAL * nivelActual; i++)
            robots.add(new RobotX1(mapa.generarCoordenada(robots, jugador)));

        for (int i = 0; i < CANTIDAD_ROBOTX2_INICIAL * nivelActual; i++)
            robots.add(new RobotX2(mapa.generarCoordenada(robots, jugador)));
    }

    private void avanzarNivel() {
        nivelActual++;
        explosiones.clear();
        jugador.setTeleportsSeguros(TELEPORTS_SEGUROS_DISPONIBLES);
        agregarRobots();
    }

    private void jugadorEliminado(HashSet<Enemigo> enemigos) {
        for (Enemigo enemigo : enemigos)
            if (jugador.getCoordenadas().esIgual(enemigo.getCoordenadas())) jugador.setEliminado(true);
    }

    private void agregarExplosion(Coordenadas coordenadas) {
        boolean coordenadaExiste = false;
        for (Enemigo explosion : explosiones)
            if (explosion.getCoordenadas().esIgual(coordenadas)) coordenadaExiste = true;

        if (!coordenadaExiste)
            explosiones.add(new Explosion(coordenadas));
    }

    private void eliminarRobots() {
        Iterator<Enemigo> iterator = robots.iterator();
        while (iterator.hasNext()) {
            Enemigo enemigo = iterator.next();
            if (enemigo.getEliminado()) {
                puntos += enemigo.getPuntaje();
                iterator.remove();
            }
        }
    }

    private void moverEnemigos() {
        HashSet<Enemigo> enemigos = new LinkedHashSet<>();
        enemigos.addAll(explosiones);
        enemigos.addAll(robots);

        for (Enemigo actual : robots) {
            actual.mover(jugador.getCoordenadas(), enemigos);
            if (actual.getEliminado()) agregarExplosion(actual.getCoordenadas());
        }

        jugadorEliminado(enemigos);
        eliminarRobots();
    }

    private int nivelActual;
    private int puntos;
    private final Mapa mapa;

    public final Jugador jugador;
    public final HashSet<Enemigo> robots;
    public final HashSet<Enemigo> explosiones;

    public Juego(Coordenadas dimensionesMapa) {
        nivelActual = 1;
        puntos = 0;
        mapa = new Mapa(dimensionesMapa);
        jugador = new Jugador(mapa.getCentroMapa(), TELEPORTS_SEGUROS_DISPONIBLES);
        robots = new LinkedHashSet<>();
        explosiones = new LinkedHashSet<>();

        agregarRobots();
    }

    public void moverJugador(Coordenadas coordenadas) {
        jugador.mover(coordenadas, null);
        moverEnemigos();
    }

    public void teleportJugador(Coordenadas coordenadas) {
        boolean teleportRealizado;
        if (coordenadas != null)
            teleportRealizado = jugador.teleportSeguro(coordenadas);
        else
            teleportRealizado = jugador.teleportAleatorio(mapa.generarCoordenada(null,null));

        if (teleportRealizado)
            moverEnemigos();
    }

    public void estadoJuego() {
        if (robots.isEmpty() && !jugador.getEliminado())
            avanzarNivel();
    }

    public int getNivel() {
        return nivelActual;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getTeleportsDisponibles() {
        return jugador.getTeleportsSeguros();
    }

    public boolean getJugadorEliminado() {
        return jugador.getEliminado();
    }

    public int getCantidadEnemigos() {
        return explosiones.size() + robots.size();
    }

    public Enemigo getEnemigo(int index) {
        if (index > getCantidadEnemigos())
            return null;

        LinkedList<Enemigo> enemigos = new LinkedList<>();
        enemigos.addAll(explosiones);
        enemigos.addAll(robots);
        return enemigos.get(index);
    }

    public Coordenadas getDimensionesMapa() {
        return mapa.getDimensionMapa();
    }

    public Coordenadas getCoordenadasJugador() {
        return jugador.getCoordenadas();
    }
}
