import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemUnitTests {
    @Test
    public void itemsWithDifferentDescriptionsAreNotEqual() {
        Item item1 = new Item("Espada", 10.5, 10);
        Item item2 = new Item("Arco", 10.5, 10);
        assertNotEquals(item1, item2, "Items with different descriptions should not be equal");
    }

    @Test
    public void itemsWithDifferentWeightsAreNotEqual() {
        Item item1 = new Item("Espada", 10.5, 10);
        Item item2 = new Item("Espada", 11.5, 10);
        assertNotEquals(item1, item2, "Items with different weights should not be equal");
    }

    @Test
    public void toStringHandlesNegativeValuesCorrectly() {
        Item item = new Item("Espada", -10.5, -10);
        assertEquals("| Espada (Peso: -10,5, Valor: -10,0) |", item.toString(), "toString should handle negative values correctly");
    }

    @Test
    public void equalsIsReflexive() {
        Item item = new Item("Espada", 10.5, 10);
        assertEquals(item, item, "equals should be reflexive");
    }

    @Test
    public void equalsIsSymmetric() {
        Item item1 = new Item("Espada", 10.5, 10);
        Item item2 = new Item("Espada", 10.5, 10);
        assertTrue(item1.equals(item2) && item2.equals(item1), "equals should be symmetric");
    }

    @Test
    public void equalsIsTransitive() {
        Item item1 = new Item("Espada", 10.5, 10);
        Item item2 = new Item("Espada", 10.5, 10);
        Item item3 = new Item("Espada", 10.5, 10);
        assertTrue(item1.equals(item2) && item2.equals(item3) && item1.equals(item3), "equals should be transitive");
    }

    @Test
    public void equalsWithNullReturnsFalse() {
        Item item = new Item("Espada", 10.5, 10);
        assertNotEquals(item, null, "equals should return false when compared with null");
    }
}
