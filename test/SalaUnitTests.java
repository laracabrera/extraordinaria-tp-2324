import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Scanner;

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

    @Test
    public void testSeleccionarMonstruo() {
        Sala sala = new Sala("Sala", 5, 5, 5, 1, 1);
        sala.agregarMonstruo(goblin);
        sala.agregarMonstruo(orco);
        String entrada = """
                kashjdf
                weroqi
                sd
                Goblin""";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals(goblin, sala.seleccionarMonstruo(new Scanner(entrada)),
                "La selección de monstruos es incorrecta");
        assertTrue(outContent.toString().contains("[ Goblin (V: 5, A: 5, D: 5) ]"), "No se listan los monstruos correctamente");
        assertTrue(outContent.toString().contains("[ Orco (V: 5, A: 5, D: 5) ]"), "No se listan los monstruos correctamente");
    }

    @Test
    public void testSeleccionaItem() {
        Sala sala = new Sala("Sala", 5, 5, 5, 1, 1);
        sala.agregarItem(espada);
        sala.agregarItem(pocion);
        String entrada = """
                kashjdf
                weroqi
                sd
                Espada""";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals(espada, sala.seleccionarItem(new Scanner(entrada)),
                "La selección de items es incorrecta");
        assertTrue(outContent.toString().contains("| Espada (Peso: 5,0, Valor: 5,0) |"), "No se listan los items correctamente");
        assertTrue(outContent.toString().contains("| Poción (Peso: 5,0, Valor: 5,0) |"), "No se listan los items correctamente");
    }

    @Test
    public void testGetFila() {
        assertEquals(0, salaNESO.getFila(), "La fila de la sala no es correcta");
    }

    @Test
    public void testGetColumna() {
        assertEquals(0, salaNESO.getColumna(), "La columna de la sala no es correcta");
    }

    @Test
    public void testGetTrampas() {
        Sala sala = new Sala("Sala", 5, 5, 5, 1, 1);
        sala.agregarTrampa(trampa);
        sala.agregarTrampa(agujero);
        assertArrayEquals(new Trampa[]{trampa, agujero, null, null, null}, sala.getTrampas(),
                "Las trampas no son correctas");
    }

    @Test
    public void testEliminarItem() {
        Sala sala = new Sala("Sala", 5, 5, 5, 1, 1);
        sala.agregarItem(espada);
        sala.agregarItem(pocion);
        sala.eliminarItem(espada.getDescripcion());
        assertNull(sala.buscarItem(espada.getDescripcion()), "El item no se ha eliminado correctamente");
        assertNotNull(sala.buscarItem(pocion.getDescripcion()), "Se ha eliminado un item incorrecto");
    }
}
