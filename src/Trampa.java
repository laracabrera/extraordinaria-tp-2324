/**
 * Clase que representa una trampa en el juego.
 */
public class Trampa {
    /** Descripción de la trampa */
    private final String descripcion;
    /** Daño que hace la trampa */
    private final int danyo; // Daño que hace la trampa.

    /**
     * Constructor de la clase Trampa.
     * @param descripcion Descripción de la trampa.
     * @param danyo Daño que hace la trampa.
     */
    public Trampa(String descripcion, int danyo) {
        this.descripcion = descripcion;
        this.danyo = danyo;
    }

    /**
     * Método que devuelve la descripción de la trampa.
     * @return Descripción de la trampa.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método que devuelve el daño que hace la trampa.
     * @return Daño que hace la trampa.
     */
    public int getDanyo() {
        return danyo;
    }
}
