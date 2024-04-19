package org.logic;

import org.logic.personajes.Enemigo;
import org.logic.personajes.Jugador;
import java.util.LinkedList;
import java.util.Random;

public class Mapa {
    private boolean coordenadaOcupada(Coordenadas coordenada, LinkedList<Enemigo> enemigos, Jugador jugador) {
        if (enemigos == null || jugador == null)
            return false;

        if (coordenada.esIgual(jugador.getCoordenadas()))
            return true;

        boolean ocupada = false;
        for (Enemigo enemigo : enemigos) {
            if (coordenada.esIgual(enemigo.getCoordenadas()))
                ocupada = true;
        }
        return ocupada;
    }

    private final Coordenadas dimensionMapa;
    private final Random rand;

    public Mapa(Coordenadas dimensionMapa) {
        this.dimensionMapa = dimensionMapa;
        rand = new Random();
    }

    public Coordenadas generarCoordenada(LinkedList<Enemigo> enemigos, Jugador jugador) {
        Coordenadas aleatorias = new Coordenadas(0,0);
        do {
            aleatorias.setX(rand.nextInt(dimensionMapa.getX()));
            aleatorias.setY(rand.nextInt(dimensionMapa.getY()));
        } while (coordenadaOcupada(aleatorias, enemigos, jugador));
        return aleatorias;
    }

    public Coordenadas getCentroMapa() {
        return new Coordenadas(dimensionMapa.getX() / 2, dimensionMapa.getY() / 2);
    }

    public Coordenadas getDimensionMapa() {
        return dimensionMapa;
    }
}
