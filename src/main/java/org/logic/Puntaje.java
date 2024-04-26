package org.logic;

public class Puntaje {
    private int puntos;
    private static final int PUNTOS_BASE = 10;

    public Puntaje() {
        puntos = 0;
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumarPuntos(int desplazamiento) {
        this.puntos += desplazamiento * PUNTOS_BASE;
    }
}