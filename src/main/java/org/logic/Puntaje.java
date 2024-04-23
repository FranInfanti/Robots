package org.logic;

public class Puntaje {
    private int puntos;

    public Puntaje() {
        puntos = 0;
    }

    public void setPuntos(int puntosEliminado) {
        puntos += puntosEliminado;
    }

    public int getPuntos() {
        return puntos;
    }
}
