package org.logic.personajes;

import org.logic.Coordenadas;
import java.util.Collection;
import java.util.HashSet;

public class RobotX1 extends Enemigo {

    public RobotX1(Coordenadas coordenadas) {
        super(coordenadas, 1);
    }

    @Override
    public void mover(Coordenadas coordenadasJugador, Collection<?> aux) {
        if (this.getEliminado())
            return;

        Coordenadas distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadasJugador);
        int x = distanciaMover.getX() + getCoordenadas().getX();
        int y = distanciaMover.getY() + getCoordenadas().getY();
        setCoordenadas(new Coordenadas(x,y));
        this.isEliminado((HashSet<Enemigo>) aux);
    }
}
