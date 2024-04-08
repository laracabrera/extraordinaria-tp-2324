import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemUnitTests {
    @Test
    public void testItem() {
        Item item = new Item("Espada", 10.5, 10);
        assertEquals("Espada", item.getDescripcion(), "La descripción del item no es correcta");
        assertEquals(10, item.getValor(), "El valor del item no es correcto");
        assertEquals(10.5, item.getPeso(),  "El peso del item no es correcto");
    }

    @Test
    public void testToString() {
        Item item = new Item("Espada", 10.5, 10);
        assertEquals("| Espada (Peso: 10,5, Valor: 10,0) |", item.toString(), "El método toString no es correcto");
    }

    @Test
    public void testEquals() {
        Item item1 = new Item("Espada", 10.5, 10);
        Item item2 = new Item("Espada", 10.5, 10);
        assertEquals(item1, item2, "Los items deberían ser iguales");
    }

    @Test
    public void testNotEquals() {
        Item item1 = new Item("Espada", 10.5, 10);
        Item item2 = new Item("Espada", 10.5, 11);
        assertNotEquals(item1, item2, "Los items no deberían ser iguales");
    }
}
