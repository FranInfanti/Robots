package org.logic.personajes;

import org.logic.Coordenadas;
import java.util.Collection;

public class Jugador extends Personaje {
    private int teleportSeguros;

    public Jugador(Coordenadas coordenadas, int teleportsIniciales) {
        super(coordenadas);
        this.teleportSeguros = teleportsIniciales;
    }

    @Override
    public void mover(Coordenadas coordenadas, Collection<?> aux) {
        Coordenadas distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadas);
        int x = distanciaMover.getX() + getCoordenadas().getX();
        int y = distanciaMover.getY() + getCoordenadas().getY();
        setCoordenadas(new Coordenadas(x,y));
    }

    public boolean teleportSeguro(Coordenadas coordenadas) {
        if (teleportSeguros == 0)
            return false;

        setCoordenadas(coordenadas);
        teleportSeguros--;
        return true;
    }

    public boolean teleportAleatorio(Coordenadas coordenadas) {
        setCoordenadas(coordenadas);
        return true;
    }

    public void setTeleportsSeguros(int teleportSeguros) {
        this.teleportSeguros = teleportSeguros;
    }

    public int getTeleportsSeguros() {
        return teleportSeguros;
    }
}
