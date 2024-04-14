package org.logic;

import java.util.ArrayList;

public class RobotX1 extends Enemigo {
    public RobotX1(Coordenadas coordenadas) {
        super(coordenadas);
    }

    @Override
    public void mover(Coordenadas coordenadasJugador, ArrayList<Enemigo> enemigos) {
        if (this.getEliminado())
            return;

        Coordenadas distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadasJugador);
        int x = distanciaMover.getX() + getCoordenadas().getX();
        int y = distanciaMover.getY() + getCoordenadas().getY();
        setCoordenadas(new Coordenadas(x,y));
        this.isEliminado(enemigos);
    }
}
