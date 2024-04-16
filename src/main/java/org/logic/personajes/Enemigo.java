package org.logic.personajes;

import org.logic.Coordenadas;

import java.util.ArrayList;

public abstract class Enemigo extends Personaje {
    public Enemigo(Coordenadas coordenadas) {
        super(coordenadas);
    }

    public abstract void mover(Coordenadas coordenadasJugador, ArrayList<?> aux);

    @Override
    public void isEliminado(ArrayList<?> aux) {
        ArrayList<Enemigo> enemigos = (ArrayList<Enemigo>) aux;
        for (Enemigo enemigo : enemigos) {
            if (!this.equals(enemigo) && !this.getEliminado()) {
                if (this.getCoordenadas().esIgual(enemigo.getCoordenadas())) {
                    this.setEliminado(true);
                    enemigo.setEliminado(true);
                }
            }
        }
    }
}
