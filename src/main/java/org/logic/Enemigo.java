package org.logic;

import java.util.ArrayList;

public abstract class Enemigo {
    void isEliminado(ArrayList<Enemigo> enemigos) {
        for (Enemigo enemigo : enemigos) {
            if (!this.equals(enemigo) && !this.getEliminado()) {
                if (this.getCoordenadas().esIgual(enemigo.getCoordenadas())) {
                    this.setEliminado(true);
                    enemigo.setEliminado(true);
                }
            }
        }
    }

    private boolean eliminado;
    private Coordenadas coordenadas;

    public Enemigo(Coordenadas coordenadas) {
        eliminado = false;
        this.coordenadas = coordenadas;
    }

    public abstract void mover(Coordenadas coordenadasJugador, ArrayList<Enemigo> enemigos);

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
