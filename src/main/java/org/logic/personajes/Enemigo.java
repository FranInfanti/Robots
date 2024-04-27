package org.logic.personajes;

import org.logic.Coordenadas;
import java.util.*;

public abstract class Enemigo extends Personaje {
    public Enemigo(Coordenadas coordenadas) {
        super(coordenadas);
    }

    public abstract void mover(Coordenadas jugador, Collection<?> aux);

    public abstract int getDesplazamiento();

    public void isEliminado(HashSet<Enemigo> enemigos) {
        boolean sigo = true;
        Iterator<Enemigo> iterator = enemigos.iterator();
        while (iterator.hasNext() && sigo) {
            Enemigo enemigo = iterator.next();
            if (this.equals(enemigo) || this.getEliminado())
                sigo = false;
            else {
                if (this.getCoordenadas().esIgual(enemigo.getCoordenadas())) {
                    this.setEliminado(true);
                    enemigo.setEliminado(true);
                }
            }
        }
    }
}
