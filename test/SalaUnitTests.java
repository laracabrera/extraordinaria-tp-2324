import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SalaUnitTests {
    Sala salaNESO;
    Item espada, pocion;
    Monstruo goblin, orco;
    Trampa trampa, agujero;

    @BeforeEach
    public void setUp() {
        salaNESO = new Sala("Sala NESO", 1, 1, 1, 0, 0);
        espada = new Item("Espada", 5, 5);
        goblin = new Monstruo("Goblin", 5, 5, 5);
        trampa = new Trampa("Trampa", 5);
        pocion = new Item("Poción", 5, 5);
        orco = new Monstruo("Orco", 5, 5, 5);
        agujero = new Trampa("Agujero", 5);
    }

    @Test
    public void testSala() {
        assertEquals("Sala NESO", salaNESO.getDescripcion(), "La descripción de la sala no es correcta");
    }

    @Test
    public void testBuscarItem() {
        assertNull(salaNESO.buscarItem(espada.getDescripcion()), "El item no debería estar en la sala");
        assertTrue(salaNESO.agregarItem(espada));
        assertNotNull(salaNESO.buscarItem(espada.getDescripcion()), "El item debería estar en la sala");
    }

    @Test
    public void testBuscarMonstruo() {
        assertNull(salaNESO.buscarMonstruo(goblin.getNombre()), "El monstruo no debería estar en la sala");
        salaNESO.agregarMonstruo(goblin);
        assertNotNull(salaNESO.buscarMonstruo(goblin.getNombre()), "El monstruo debería estar en la sala");
    }

    @Test
    public void testBuscarTrampa() {
        assertNull(salaNESO.buscarTrampa(trampa.getDescripcion()), "La trampa no debería estar en la sala");
        salaNESO.agregarTrampa(trampa);
        assertNotNull(salaNESO.buscarTrampa(trampa.getDescripcion()), "La trampa debería estar en la sala");
    }

    @Test
    public void testAgregarItem() {
        assertFalse(salaNESO.hayItems(), "La sala no debería tener items");
        assertTrue(salaNESO.agregarItem(espada));
        assertTrue(salaNESO.hayItems(), "La sala debería tener items");
        assertNotNull(salaNESO.buscarItem(espada.getDescripcion()), "El item no se ha añadido correctamente a la sala");
    }

    @Test
    public void testAgregarMonstruo() {
        assertFalse(salaNESO.hayMonstruos(), "La sala no debería tener monstruos");
        salaNESO.agregarMonstruo(goblin);
        assertTrue(salaNESO.hayMonstruos(), "La sala debería tener monstruos");
        assertNotNull(salaNESO.buscarMonstruo(goblin.getNombre()), "El monstruo no se ha añadido correctamente a la sala");
    }

    @Test
    public void testAgregarTrampa() {
        assertFalse(salaNESO.hayTrampas(), "La sala no debería tener trampas");
        salaNESO.agregarTrampa(trampa);
        assertTrue(salaNESO.hayTrampas(), "La sala debería tener trampas");
        assertNotNull(salaNESO.buscarTrampa(trampa.getDescripcion()), "La trampa no se ha añadido correctamente a la sala");
    }

    @Test
    public void testEliminarMonstruo() {
        salaNESO.agregarMonstruo(goblin);
        assertTrue(salaNESO.hayMonstruos(), "La sala debería tener monstruos");
        salaNESO.eliminarMonstruo(goblin.getNombre());
        assertFalse(salaNESO.hayMonstruos(), "La sala no debería tener monstruos");
    }

    @Test
    public void testMaxItems() {
        salaNESO.agregarItem(espada);
        salaNESO.agregarItem(pocion);
        assertNull(salaNESO.buscarItem(pocion.getDescripcion()), "El item no debería estar en la sala");
    }

    @Test
    public void testMaxMonstruos() {
        salaNESO.agregarMonstruo(goblin);
        salaNESO.agregarMonstruo(orco);
        assertNull(salaNESO.buscarMonstruo(orco.getNombre()), "El monstruo no debería estar en la sala");
    }

    @Test
    public void testMaxTrampas() {
        salaNESO.agregarTrampa(trampa);
        salaNESO.agregarTrampa(agujero);
        assertNull(salaNESO.buscarTrampa(agujero.getDescripcion()), "La trampa no debería estar en la sala");
    }

    @Test
    public void testAgregarItemRepetido() {
        salaNESO.agregarItem(espada);
        assertFalse(salaNESO.agregarItem(espada), "No se debería poder añadir un item repetido");
    }

    @Test
    public void testAgregarMonstruoRepetido() {
        salaNESO.agregarMonstruo(goblin);
        assertFalse(salaNESO.agregarMonstruo(goblin), "No se debería poder añadir un monstruo repetido");
    }

    @Test
    public void testAgregarTrampaRepetida() {
        salaNESO.agregarTrampa(trampa);
        assertFalse(salaNESO.agregarTrampa(trampa), "No se debería poder añadir una trampa repetida");
    }
}
