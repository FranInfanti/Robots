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

    public String getTextoAlertaFinal() {
        return "Gracias Por Jugar :)";
    }

    public String getTextoResultado(int nivel, int puntos) {
        return ("Llegaste al Nivel %d \n" +
                "Tu puntaje fue de %d") .formatted(nivel, puntos);
    }

    public String getCssDeTitulo() {
        return "-fx-font-family: Arial; -fx-font-weight: 900; -fx-font-size: 30; -fx-text-fill: #aaccffff";
    }

    public String getCssDeBoton(boolean seleccionado) {
        String color = seleccionado ? "#0044aaff" : "#003380ff";
        return "-fx-font-family: Arial; -fx-font-weight: 900; -fx-text-fill: #aaccffff; -fx-font-size: 20; -fx-background-color: %s;  -fx-background-radius: 20; -fx-min-width: 200; -fx-min-height: 65" .formatted(color);
    }

    public String getCssSlider(boolean seleccionado) {
        double opacidad = seleccionado ? 0.1f : 0.2f;
        return "-fx-opacity: %f; -fx-max-width: 200" .formatted(opacidad);
    }

    public String getCssCasilla(boolean seleccionado) {
        String color = seleccionado ? "#0044aaff" : "#003380ff";
        return "-fx-cursor: pointer; -fx-background-color: %s; -fx-background-radius: " .formatted(color);
    }

    public String getCssDeHbox() {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff";
    }

    public String getCssDeVbox() {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff; -fx-spacing: 20";
    }

    public String getCssDeVboxJuego() {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff; -fx-spacing: 20; -fx-min-width: 600; -fx-min-height: 400";
    }

    public String getCssDeGridPane(int minWidth, int minHeight) {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff; -fx-hgap: 3; -fx-vgap: 3; -fx-min-width: %d; -fx-min-height: %d" .formatted(minWidth, minHeight);
    }

    public String getImagenJugador() {
        return "jugador.png";
    }

    public String getImagenRobotX1() {
        return "robotX1.png";
    }

    public String getImagenRobotX2() {
        return "robotX2.png";
    }

    public String getImagenExplosion() {
        return "Explosion.png";
    }
}
