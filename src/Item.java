/**
 * Clase que representa un item con un peso, un valor y una descripción.
 * Un item es un objeto que se puede meter en una mochila.
 */
public class Item {
    /** Peso del item. */
    private final double peso;
    /** Valor del item. */
    private final double valor;
    /** Descripción del item. */
    private final String descripcion; // Descripción del item.

    /**
     * Constructor de la clase Item.
     * @param descripcion Descripción del item.
     * @param peso Peso del item.
     * @param valor Valor del item.
     */
    public Item(String descripcion, double peso, double valor) {
        this.peso = peso;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    /**
     * Método que devuelve el peso del item.
     * @return Peso del item.
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Método que devuelve el valor del item.
     * @return Valor del item.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Método que devuelve la descripción del item.
     * @return Descripción del item.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método que devuelve la representación en cadena del item.
     * @return Representación en cadena del item.
     */
    @Override
    public String toString() {
        return "| " + descripcion + " (Peso: " + String.format("%.1f", peso) + ", Valor: " + String.format("%.1f", valor) + ") |";
    }

    /**
     * Método que compara dos items. Dos items son iguales si tienen el mismo peso, valor y descripción.
     * @param obj Objeto a comparar.
     * @return True si los items son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Item item = (Item) obj;
        return Double.compare(item.peso, peso) == 0 && Double.compare(item.valor, valor) == 0 && descripcion.equals(item.descripcion);
    }
}
