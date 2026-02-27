package org.example.model.characters;

import org.example.model.Coordenadas;
import java.util.Collection;

public abstract class Personaje {
    private Coordenadas coordenadas;
    private boolean eliminado;

    public Personaje(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
        eliminado = false;
    }

    public abstract void mover(Coordenadas coordenadasJugador, Collection<?> aux);

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