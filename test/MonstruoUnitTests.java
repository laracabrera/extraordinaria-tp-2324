import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MonstruoUnitTests {
    Monstruo goblin;

    @BeforeEach
    public void setUp() {
        goblin = new Monstruo("Goblin", 100, 10, 5);
    }

    @Test
    public void testMonstruo() {
        assertEquals(100, goblin.getVida(), "La vida del monstruo no es correcta");
        assertEquals(10, goblin.getAtaque(), "El ataque del monstruo no es correcto");
        assertEquals(5, goblin.getDefensa(), "La defensa del monstruo no es correcta");
        assertEquals("Goblin", goblin.getNombre(), "El nombre del monstruo no es correcto");
    }

    @Test
    public void testRecibirDanyo() {
        goblin.recibirDanyo(10);
        assertEquals(95, goblin.getVida(), "La vida del monstruo no es correcta");
        goblin.recibirDanyo(-10);
        assertEquals(95, goblin.getVida(), "La vida del monstruo no es correcta");
    }

    @Test
    public void testToString() {
        assertEquals("[ Goblin (V: 100, A: 10, D: 5) ]", goblin.toString(), "El toString del monstruo no es correcto");
    }

    @Test
    public void testEquals() {
        Monstruo otroGoblin = new Monstruo("Goblin", 100, 10, 5);
        assertEquals(goblin, otroGoblin, "Los monstruos deberían ser iguales");
    }

    @Test
    public void testNotEquals() {
        Monstruo otroGoblin = new Monstruo("Goblin", 100, 10, 6);
        assertNotEquals(goblin, otroGoblin, "Los monstruos deberían ser diferentes");
    }
}
