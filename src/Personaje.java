import java.util.Scanner;

public class Personaje {
    private final String nombre;
    private int vida;
    private final int ataque, defensa, destreza;
    private final Item[] items;

    private final double maxPesoPorPersonaje;

    public Personaje(String nombre, int vida, int ataque, int defensa, int destreza, int maxItemsPorPersonaje, double maxPesoPorPersonaje) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.destreza = destreza;
        this.items = new Item[maxItemsPorPersonaje];
        this.maxPesoPorPersonaje = maxPesoPorPersonaje;
    }

    public static Personaje crearPersonaje(Scanner teclado) {
        System.out.println("¡Bienvenido a la aventura!");
        String nombre = Utilidades.leerCadena(teclado, "¿Cómo te llamas? ");
        System.out.println("¡Hola, " + nombre + "! Tienes 250 puntos para repartir entre vida, ataque, defensa y destreza.");
        int puntos = 250;
        int vida = Utilidades.leerNumero(teclado, "¿Cuánta vida quieres tener? (50-247): ", 50, 247);
        puntos -= vida;
        int ataque = Utilidades.leerNumero(teclado, "¿Cuánto ataque quieres tener? (1-" + (puntos-2) + "): ", 1, puntos-2);
        puntos -= ataque;
        int defensa = Utilidades.leerNumero(teclado, "¿Cuánta defensa quieres tener? (1-" + (puntos-1) + "): ", 1, puntos-1);
        puntos -= defensa;
        int destreza = Utilidades.leerNumero(teclado, "¿Cuánta destreza quieres tener? (1-" + puntos + "): ", 1, puntos);
        return new Personaje(nombre, vida, ataque, defensa, destreza, Integer.max(1, destreza/4), Double.max(1.0, ataque/4.0));
    }

    public String getNombre() {
        return nombre;
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

    public int getDestreza() {
        return destreza;
    }

    public Item[] getItems() {
        return items;
    }

    public Item getItem(int indice) {
        if (indice < 0 || indice >= items.length) {
            return null;
        } else {
            return items[indice];
        }
    }

    public void recibirDanyo(int danyo) {
        if (danyo < 0) {
            return;
        }
        vida -= danyo + defensa;
    }

    public boolean anyadirItem(Item item) {
        if (item == null) {
            return false;
        }
        double pesoTotal = 0;
        for (Item i : items) {
            if (i != null) {
                pesoTotal += i.getPeso();
            }
        }
        if (pesoTotal + item.getPeso() > maxPesoPorPersonaje) {
            return false;
        }
        int i = 0;
        while (i < items.length && items[i] != null) {
            i++;
        }
        if (i < items.length) {
            items[i] = item;
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "{ " + nombre + " (V: " + vida + ", A: " + ataque + ", D: " + defensa + ", X: " + destreza + ") }";
    }

    public double getPesoMochila() {
        double pesoTotal = 0;
        for (Item i : items) {
            if (i != null) {
                pesoTotal += i.getPeso();
            }
        }
        return pesoTotal;
    }

    public double getValorMochila() {
        double valorTotal = 0;
        for (Item i : items) {
            if (i != null) {
                valorTotal += i.getValor();
            }
        }
        return valorTotal;
    }

    public String infoMochila() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mochila de ").append(nombre).append(":\n");
        for (Item i : items) {
            if (i != null) {
                sb.append(i).append("\n");
            }
        }
        sb.append("\nPeso ").append(getPesoMochila()).append(" kg (").append(maxPesoPorPersonaje).append(")\n");
        sb.append("Tu mochila vale ").append(getValorMochila()).append(" monedas");
        return sb.toString();
    }
}
