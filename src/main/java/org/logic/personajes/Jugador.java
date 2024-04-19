package org.logic.personajes;

import org.logic.Coordenadas;
import java.util.LinkedList;

public class Jugador extends Personaje {
    private int teleportsSeguros;

    public Jugador(Coordenadas coordenadas, int teleportsDisponibles) {
        super(coordenadas);
        this.teleportsSeguros = teleportsDisponibles;
    }

    @Override
    public void mover(Coordenadas coordenadas, LinkedList<?> aux) {
        Coordenadas distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadas);
        int x = distanciaMover.getX() + getCoordenadas().getX();
        int y = distanciaMover.getY() + getCoordenadas().getY();
        setCoordenadas(new Coordenadas(x,y));
    }

    public boolean teleportSeguro(Coordenadas coordenadas) {
        if (teleportsSeguros == 0)
            return false;

        setCoordenadas(coordenadas);
        teleportsSeguros--;
        return true;
    }

    public boolean teleportAleatorio(Coordenadas coordenadas) {
        setCoordenadas(coordenadas);
        return true;
    }

    public void setTeleportsSeguros(int teleportsSeguros) {
        this.teleportsSeguros = teleportsSeguros;
    }

    public int getTeleportsSeguros() {
        return teleportsSeguros;
    }
}
