package org.example.model;

public class Puntaje {
    private int puntos;

    public Puntaje() {
        puntos = 0;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public int getPuntos() {
        return puntos;
    }
}