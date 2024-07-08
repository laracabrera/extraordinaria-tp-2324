/**
 * Clase que representa un monstruo con vida, ataque, defensa y nombre.
 * Un monstruo es un ser que puede atacar y recibir daño.
 */
public class Monstruo {
    /** Vida del monstruo. */
    private int vida;
    /** Ataque del monstruo. */
    private final int ataque;
    /** Defensa del monstruo. */
    private final int defensa;
    /** Nombre del monstruo. */
    private final String nombre;

    /**
     * Constructor de la clase Monstruo.
     * @param nombre Nombre del monstruo.
     * @param vida Puntos de vida del monstruo.
     * @param ataque Ataque del monstruo.
     * @param defensa Defensa del monstruo.
     */
    public Monstruo(String nombre, int vida, int ataque, int defensa) {
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.nombre = nombre;
    }

    /**
     * Método que devuelve la vida del monstruo.
     * @return Vida del monstruo.
     */
    public int getVida() {
        return vida;
    }

    /**
     * Método que devuelve si el monstruo está vivo.
     * @return true si el monstruo está vivo, false en caso contrario.
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Método que devuelve el ataque del monstruo.
     * @return Ataque del monstruo.
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Método que devuelve la defensa del monstruo.
     * @return Defensa del monstruo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para que el monstruo reciba daño.
     * @param ataque Daño que recibe el monstruo.
     */
    public void recibirDanyo(int ataque) {
        if (ataque < 0) {
            return;
        }
        vida -= ataque - defensa;
    }

    /**
     * Método que devuelve la represetación textual del monstruo.
     * @return Representación textual del monstruo.
     */
    @Override
    public String toString() {
        return "[ " + nombre + " (V: " + vida + ", A: " + ataque + ", D: " + defensa + ") ]";
    }

    /**
     * Método que compara dos monstruos. Dos monstruos son iguales si tienen el mismo nombre, vida, ataque y defensa.
     * @param obj Objeto a comparar.
     * @return true si los monstruos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Monstruo otro) {
            return nombre.equals(otro.nombre) && vida == otro.vida && ataque == otro.ataque && defensa == otro.defensa;
        }
        return false;
    }
}
