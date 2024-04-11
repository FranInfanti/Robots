package org.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Mapa {
    private Coordenadas dimensionMapa;
    private final List<Coordenadas> coordenadasEnUso;
    private final Random rand;

    public Mapa(Coordenadas dimensionMapa) {
        this.dimensionMapa = dimensionMapa;
        coordenadasEnUso = new LinkedList<>();
        rand = new Random();
    }

    public boolean coordenadaOcupada(Coordenadas coordenada) {
        boolean ocupada = false;
        for (Coordenadas c : coordenadasEnUso) {
            if (c.esIgual(coordenada))
                ocupada = true;
        }
        return ocupada;
    }

    public Coordenadas generarCoordenada(boolean segura) {
        Coordenadas aleatorias = new Coordenadas(0,0);
        do {
            aleatorias.setX(rand.nextInt(dimensionMapa.getX()));
            aleatorias.setY(rand.nextInt(dimensionMapa.getY()));
        } while (coordenadaOcupada(aleatorias) && segura);
        setCoordenadaEnUso(aleatorias);
        return aleatorias;
    }

    public void setDimensionMapa(Coordenadas dimensionMapa) {
        this.dimensionMapa = dimensionMapa;
    }

    public void setCoordenadaEnUso(Coordenadas coordenada) {
        coordenadasEnUso.add(coordenada);
    }

    public void removeCoordenadaEnUso(Coordenadas coordenada) {
        coordenadasEnUso.remove(coordenada);
    }

    public Coordenadas getCoordenadaEnUso(int indice) {
        return coordenadasEnUso.get(indice);
    }

    public int getCantidadCoordenadasEnUso() {
        return coordenadasEnUso.size();
    }

    public Coordenadas getCentroMapa() {
        return new Coordenadas(dimensionMapa.getX() / 2, dimensionMapa.getY() / 2);
    }
}
