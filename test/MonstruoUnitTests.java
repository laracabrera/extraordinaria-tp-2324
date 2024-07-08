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
    public void receivingDamageReducesHealthCorrectly() {
        goblin.recibirDanyo(20);
        assertEquals(85, goblin.getVida(), "La vida del monstruo no se ha reducido correctamente tras recibir daño");
    }

    @Test
    public void receivingNegativeDamageDoesNotChangeHealth() {
        goblin.recibirDanyo(-20);
        assertEquals(100, goblin.getVida(), "La salud del monstruo no debería cambiar si recibe daño negativo");
    }

    @Test
    public void equalsReturnsFalseForDifferentMonsters() {
        Monstruo dragon = new Monstruo("Dragon", 300, 30, 20);
        assertNotEquals(goblin, dragon, "Monstruos diferentes no deberían ser iguales");
    }

    @Test
    public void equalsReturnsTrueForSameMonsterAttributes() {
        Monstruo anotherGoblin = new Monstruo("Goblin", 100, 10, 5);
        assertEquals(goblin, anotherGoblin, "Monstruos con los mismos atributos deberían ser iguales");
    }

    @Test
    public void equalsReturnsFalseForDifferentMonsterNames() {
        Monstruo fakeGoblin = new Monstruo("GoblinFake", 100, 10, 5);
        assertNotEquals(goblin, fakeGoblin, "Monstruos con el mismo nombre deberían ser iguales");
    }

    @Test
    public void equalsReturnsFalseForDifferentMonsterHealth() {
        Monstruo injuredGoblin = new Monstruo("Goblin", 90, 10, 5);
        assertNotEquals(goblin, injuredGoblin, "Monstruos con diferente salud no deberían ser iguales");
    }

    @Test
    public void equalsReturnsFalseForDifferentMonsterAttack() {
        Monstruo strongGoblin = new Monstruo("Goblin", 100, 20, 5);
        assertNotEquals(goblin, strongGoblin, "Monstruos con diferente ataque no deberían ser iguales");
    }

    @Test
    public void equalsReturnsFalseForDifferentMonsterDefense() {
        Monstruo armoredGoblin = new Monstruo("Goblin", 100, 10, 10);
        assertNotEquals(goblin, armoredGoblin, "Monstruos con diferente defensa no deberían ser iguales");
    }

    @Test
    public void testGetDefensa() {
        assertEquals(5, goblin.getDefensa(), "La defensa del monstruo no es correcta");
    }

    @Test
    public void testGetAtaque() {
        assertEquals(10, goblin.getAtaque(), "El ataque del monstruo no es correcto");
    }
}
