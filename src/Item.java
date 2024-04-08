public class Item {
    private final double peso;
    private final double valor;
    private final String descripcion;

    public Item(String descripcion, double peso, double valor) {
        this.peso = peso;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public double getPeso() {
        return peso;
    }

    public double getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "| " + descripcion + " (Peso: " + String.format("%.1f", peso) + ", Valor: " + String.format("%.1f", valor) + ") |";
    }

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
