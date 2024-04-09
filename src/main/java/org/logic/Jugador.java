package org.logic;

public class Jugador {
    private Coordenadas posicionEnMapa;

    public Jugador(Coordenadas posicionIncial) {
        this.posicionEnMapa = posicionIncial;
    }

    public void setCoordenadas(Coordenadas posicionEnMapa) {
        this.posicionEnMapa = posicionEnMapa;
    }

    public Coordenadas getCoordenadas() {
        return posicionEnMapa;
    }

    public void moverJugador(Coordenadas coordenadas) {
        Coordenadas distanciaMover = posicionEnMapa.calcularDesplazamiento(coordenadas);
        int x = distanciaMover.getX() + getCoordenadas().getX();
        int y = distanciaMover.getY() + getCoordenadas().getY();
        setCoordenadas(new Coordenadas(x,y));
    }
}
