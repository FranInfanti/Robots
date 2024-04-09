package org.logic;

public class main {
    public static void main(String[] args) {
        Coordenadas j1 = new Coordenadas(10,10);
        Coordenadas r1 = new Coordenadas(5,2);
        Coordenadas r2 = new Coordenadas(10,6);
        Coordenadas r3 = new Coordenadas(5,10);
        Coordenadas r4 = new Coordenadas(20,20);

        Coordenadas resultado = r1.compararCoords(j1);
        System.out.println(String.valueOf(resultado.getX()) +","+ String.valueOf(resultado.getY()));
        resultado = r2.compararCoords(j1);
        System.out.println(String.valueOf(resultado.getX()) +","+ String.valueOf(resultado.getY()));
        resultado = r3.compararCoords(j1);
        System.out.println(String.valueOf(resultado.getX()) +","+ String.valueOf(resultado.getY()));
        resultado = r4.compararCoords(j1);
        System.out.println(String.valueOf(resultado.getX()) +","+ String.valueOf(resultado.getY()));
    }
}
