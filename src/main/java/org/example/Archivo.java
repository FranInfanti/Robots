package org.example;

public class Archivo {
    public String getTextoDeTituloJuego() {
        return "Robots";
    }

    public String getTextoDeInicioJuego() {
        return "Iniciar Juego";
    }

    public String getTextoDeNuevoJuego() {
        return "Nuevo Juego";
    }

    public String getTextoDeTeleportRandom() {
        return "Teleport Random";
    }

    public String getTextoDeTeleportSafely(int teleportsDisponibles) {
        return "Teleport Safely\n" +
                " (Remaining: %d)" .formatted(teleportsDisponibles);
    }

    public String getTextoDeWaitForRobots() {
        return "Wait for robots";
    }

    public String getEstiloDeTitulo() {
        return "-fx-font-family: Arial; -fx-font-weight: 900; -fx-font-size: 30; -fx-text-fill: #aaccffff";
    }

    public String getEstiloDeBoton() {
        return "-fx-font-family: Arial; -fx-font-weight: 900; -fx-text-fill: #aaccffff; -fx-font-size: 20; -fx-background-color: #003380ff;  -fx-background-radius: 20;";
    }

    public String getEstiloSlider() {
        return "-fx-opacity: 0.2";
    }

    public String getEstiloCasilla() {
        return "-fx-cursor: pointer; -fx-background-color: #003380ff;  -fx-background-radius: ";
    }

    public String getEstiloDeHbox() {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff";
    }

    public String getEstiloDeVbox() {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff; -fx-spacing: 20";
    }

    public String getEstiloDeGridPane() {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff; -fx-hgap: 3; -fx-vgap: 3";
    }

    public String getImagenJugador() {
        return "player.png";
    }

    public String getImagenRobotX1() {
        return "X1.png";
    }

    public String getImagenRobotX2() {
        return "X2.png";
    }

    public String getImagenExplosion() {
        return "Explosion.png";
    }
}
