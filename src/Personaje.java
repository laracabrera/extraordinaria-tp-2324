import java.util.Scanner;

/**
 * Clase que representa a un personaje de la aventura.
 * Un personaje tiene un nombre, una cantidad de vida, un ataque, una defensa, una destreza, una mochila con items y un peso máximo para la mochila.
 * Un personaje puede recibir daño, añadir un item a su mochila, obtener un item de su mochila, obtener el peso total de su mochila, obtener el valor total de su mochila y obtener información sobre su mochila.
 */
public class Personaje {
    /** Nombre del personaje. */
    private final String nombre;
    /** Cantidad de vida del personaje. */
    private int vida;
    /** Cantidad de ataque del personaje. */
    private final int ataque;
    /** Cantidad de defensa del personaje. */
    private final int defensa;
    /** Cantidad de destreza del personaje. */
    private final int destreza;
    /** Mochila del personaje. */
    private final Item[] items;
    /** Peso máximo que puede llevar el personaje. */
    private final double maxPesoPorPersonaje; // Peso máximo que puede llevar el personaje

    /**
     * Constructor de la clase Personaje.
     * @param nombre Nombre del personaje.
     * @param vida Cantidad de vida del personaje.
     * @param ataque Cantidad de ataque del personaje.
     * @param defensa Cantidad de defensa del personaje.
     * @param destreza Cantidad de destreza del personaje.
     * @param maxItemsPorPersonaje Número máximo de items que puede llevar el personaje.
     * @param maxPesoPorPersonaje Peso máximo que puede llevar el personaje.
     */
    public Personaje(String nombre, int vida, int ataque, int defensa, int destreza, int maxItemsPorPersonaje, double maxPesoPorPersonaje) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.destreza = destreza;
        this.items = new Item[maxItemsPorPersonaje];
        this.maxPesoPorPersonaje = maxPesoPorPersonaje;
    }

    /**
     * Método que crea un personaje a partir de la información introducida por el usuario.
     * @param teclado Scanner para leer la información introducida por el usuario.
     * @return Personaje creado.
     */
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

    /**
     * Método que devuelve el nombre del personaje.
     * @return Nombre del personaje.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que devuelve la cantidad de vida del personaje.
     * @return Cantidad de vida del personaje.
     */
    public int getVida() {
        return vida;
    }

    /**
     * Método que devuelve la cantidad de ataque del personaje.
     * @return Cantidad de ataque del personaje.
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Método que devuelve la cantidad de defensa del personaje.
     * @return Cantidad de defensa del personaje.
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Método que devuelve la cantidad de destreza del personaje.
     * @return Cantidad de destreza del personaje.
     */
    public int getDestreza() {
        return destreza;
    }

    /**
     * Método que devuelve el listado de items del personaje.
     * @return Listado de items del personaje.
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Método que devuelve el item que se encuentra en la posición indicada.
     * @param indice Posición del item.
     * @return Item que se encuentra en la posición indicada.
     */
    public Item getItem(int indice) {
        if (indice < 0 || indice >= items.length) {
            return null;
        } else {
            return items[indice];
        }
    }

    /**
     * Método que recibe una cantidad de daño y la resta a la vida del personaje.
     * @param danyo Cantidad de daño recibida.
     */
    public void recibirDanyo(int danyo) {
        if (danyo < 0) {
            return;
        }
        vida -= danyo - defensa;
    }

    /**
     * Método que añade un item a la mochila del personaje.
     * @param item Item a añadir.
     * @return true si se ha añadido el item, false en caso contrario.
     */
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

    /**
     * Método que crea una representación en forma de cadena del personaje.
     * @return Representación en forma de cadena del personaje.
     */
    public String toString() {
        return "{ " + nombre + " (V: " + vida + ", A: " + ataque + ", D: " + defensa + ", X: " + destreza + ") }";
    }

    /**
     * Método que devuelve el peso total de la mochila del personaje.
     * @return Peso total de la mochila del personaje.
     */
    public double getPesoMochila() {
        double pesoTotal = 0;
        for (Item i : items) {
            if (i != null) {
                pesoTotal += i.getPeso();
            }
        }
        return pesoTotal;
    }

    /**
     * Método que devuelve el valor total de la mochila del personaje.
     * @return Valor total de la mochila del personaje.
     */
    public double getValorMochila() {
        double valorTotal = 0;
        for (Item i : items) {
            if (i != null) {
                valorTotal += i.getValor();
            }
        }
        return valorTotal;
    }

    /**
     * Método que devuelve información sobre la mochila del personaje.
     * @return Información sobre la mochila del personaje.
     */
    public String infoMochila() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mochila de ").append(nombre).append(":\n");
        for (Item i : items) {
            if (i != null) {
                sb.append(i).append("\n");
            }
        }
        sb.append("\nPeso ").append(String.format("%.1f",getPesoMochila())).append(" kg (").append(maxPesoPorPersonaje).append(")\n");
        sb.append("Tu mochila vale ").append(getValorMochila()).append(" monedas");
        return sb.toString();
    }
}
