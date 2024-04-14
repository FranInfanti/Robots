package org.logic;

import java.util.ArrayList;

public class Jugador {
    private Coordenadas coordenadas;
    private int teleportsSeguros;
    private boolean eliminado;

    public Jugador(Coordenadas coordenadas, int teleportsDisponibles) {
        this.coordenadas = coordenadas;
        this.teleportsSeguros = teleportsDisponibles;
    }

    public void mover(Coordenadas coordenadas) {
        Coordenadas distanciaMover = coordenadas.calcularDesplazamiento(coordenadas);
        int x = distanciaMover.getX() + getCoordenadas().getX();
        int y = distanciaMover.getY() + getCoordenadas().getY();
        setCoordenadas(new Coordenadas(x,y));
    }

    public void teleportSeguro(Coordenadas coordenadas) {
        if (teleportsSeguros == 0)
            return;

        this.coordenadas = coordenadas;
        teleportsSeguros--;
    }

    public void teleportAleatorio(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void isEliminado(ArrayList<Enemigo> enemigos) {
        for (Enemigo enemigo : enemigos) {
            if (getCoordenadas().esIgual(enemigo.getCoordenadas()))
                setEliminado(true);
        }
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void setTeleportsSeguros(int teleportsSeguros) {
        this.teleportsSeguros = teleportsSeguros;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public int getTeleportsSeguros() {
        return teleportsSeguros;
    }

    public boolean getEliminado() {
        return eliminado;
    }
}

