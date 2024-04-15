package org.logic;

import java.util.ArrayList;

public abstract class Enemigo extends Personaje {
    public Enemigo(Coordenadas coordenadas) {
        super(coordenadas);
    }

    public abstract void mover(Coordenadas coordenadasJugador, ArrayList<Enemigo> enemigos);

    @Override
    public void isEliminado(ArrayList<Enemigo> enemigos) {
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
