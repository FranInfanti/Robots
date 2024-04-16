package org.logic;

import org.logic.personajes.Enemigo;
import java.util.LinkedList;
import java.util.Random;

public class Mapa {
    private boolean coordenadaOcupada(Coordenadas coordenada, LinkedList<Enemigo> enemigos) {
        if (enemigos == null)
            return false;

        boolean ocupada = false;
        for (Enemigo enemigo : enemigos) {
            if (coordenada.esIgual(enemigo.getCoordenadas()) || coordenada.esIgual(getCentroMapa()))
                ocupada = true;
        }
        return ocupada;
    }

    private Coordenadas dimensionMapa;
    private final Random rand;

    public Mapa(Coordenadas dimensionMapa) {
        this.dimensionMapa = dimensionMapa;
        rand = new Random();
    }

    public Coordenadas generarCoordenada(LinkedList<Enemigo> enemigos) {
        Coordenadas aleatorias = new Coordenadas(0,0);
        do {
            aleatorias.setX(rand.nextInt(dimensionMapa.getX()));
            aleatorias.setY(rand.nextInt(dimensionMapa.getY()));
        } while (coordenadaOcupada(aleatorias, enemigos));
        return aleatorias;
    }

    public void setDimensionMapa(Coordenadas dimensionMapa) {
        this.dimensionMapa = dimensionMapa;
    }

    public Coordenadas getCentroMapa() {
        return new Coordenadas(dimensionMapa.getX() / 2, dimensionMapa.getY() / 2);
    }

    public Coordenadas getDimensionMapa() {
        return dimensionMapa;
    }
}
