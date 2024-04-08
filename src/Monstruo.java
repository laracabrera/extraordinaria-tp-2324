public class Monstruo {
    private int vida;
    private final int ataque;
    private final int defensa;
    private final String nombre;

    public Monstruo(String nombre, int vida, int ataque, int defensa) {
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public String getNombre() {
        return nombre;
    }

    public void recibirDanyo(int ataque) {
        if (ataque < 0) {
            return;
        }
        vida -= ataque + defensa;
    }

    @Override
    public String toString() {
        return "[ " + nombre + " (V: " + vida + ", A: " + ataque + ", D: " + defensa + ") ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Monstruo otro) {
            return nombre.equals(otro.nombre) && vida == otro.vida && ataque == otro.ataque && defensa == otro.defensa;
        }
        return false;
    }
}
