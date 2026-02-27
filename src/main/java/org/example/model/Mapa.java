package org.example.model;

import org.example.model.characters.Enemigo;
import org.example.model.characters.Jugador;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class Mapa {
    private static final int MITAD = 2;

    private final Coordenadas dimensionMapa;

    public Mapa(Coordenadas dimensionMapa) {
        this.dimensionMapa = dimensionMapa;
    }

    private boolean coordenadaOcupada(Coordenadas coordenada, HashSet<Enemigo> enemigos, Jugador jugador) {
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

    public Coordenadas generarCoordenada(Collection<?> aux, Object object) {
        Coordenadas aleatorias = new Coordenadas(0,0);
        Random rand = new Random();
        do {
            aleatorias.setX(rand.nextInt(dimensionMapa.getX()));
            aleatorias.setY(rand.nextInt(dimensionMapa.getY()));
        } while (coordenadaOcupada(aleatorias, (HashSet<Enemigo>) aux, (Jugador) object));
        return aleatorias;
    }

    public Coordenadas getCentroMapa() {
        return new Coordenadas(dimensionMapa.getX() / MITAD, dimensionMapa.getY() / MITAD);
    }

    public Coordenadas getDimensionMapa() {
        return dimensionMapa;
    }
}
