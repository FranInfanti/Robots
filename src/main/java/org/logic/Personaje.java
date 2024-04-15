package org.logic;

import java.util.ArrayList;

public abstract class Personaje {
    private Coordenadas coordenadas;
    private boolean eliminado;

    public Personaje(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
        eliminado = false;
    }

    public abstract void mover(Coordenadas coordenadasJugador, ArrayList<Enemigo> enemigos);

    public abstract void isEliminado(ArrayList<Enemigo> enemigos);

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }
}