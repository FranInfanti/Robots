package org.logic;

public abstract class Enemigo {
    private Coordenadas posicionEnMapa;

    public Enemigo(Coordenadas posicionInicial) {
        posicionEnMapa = posicionInicial;
    }

    public abstract void moverEnemigo(Coordenadas coordenadasJugador);

    public void setCoordenadas(Coordenadas posicionEnMapa) {
        this.posicionEnMapa = posicionEnMapa;
    }

    public Coordenadas getCoordenadas() {
        return posicionEnMapa;
    }
}
