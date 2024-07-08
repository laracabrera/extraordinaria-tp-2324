import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class PersonajeUnitTests {
    Personaje personaje, personajeLimitado;
    Item espada, casco, escudo;

    @BeforeEach
    public void setUp() {
        personaje = new Personaje("Guerrero", 100, 10, 5, 10, 5, 50.0);
        personajeLimitado = new Personaje("Hada", 100, 10, 5, 10, 2, 50.0);
        espada = new Item("Espada", 20.3, 10.5);
        casco = new Item("Casco", 30.0, 15.0);
        escudo = new Item("Escudo", 10.0, 5.0);
    }

    @Test
    public void testPersonaje() {
        assertEquals(100, personaje.getVida(), "La vida del personaje no es correcta");
        assertEquals(10, personaje.getAtaque(), "El ataque del personaje no es correcto");
        assertEquals(5, personaje.getDefensa(), "La defensa del personaje no es correcta");
        assertEquals("Guerrero", personaje.getNombre(), "El nombre del personaje no es correcto");
    }

    @Test
    public void testRecibirDanyo() {
        personaje.recibirDanyo(10);
        assertEquals(95, personaje.getVida(), "La vida del personaje no es correcta tras recibir daño");
        personaje.recibirDanyo(-10);
        assertEquals(95, personaje.getVida(), "La vida del personaje no es correcta tras recibir daño negativo");
    }

    @Test
    public void testAnyadirItem() {
        assertTrue(personajeLimitado.anyadirItem(espada));
        assertEquals(espada.getDescripcion(), personajeLimitado.getItems()[0].getDescripcion(), "El item del personaje no es correcto");
        assertEquals(espada.getPeso(), personajeLimitado.getItems()[0].getPeso(), "El item del personaje no es correcto");
        assertEquals(espada.getValor(), personajeLimitado.getItems()[0].getValor(), "El item del personaje no es correcto");
        assertFalse(personajeLimitado.anyadirItem(casco), "No se debería poder añadir un item que excede el peso máximo del personaje");
        assertTrue(personajeLimitado.anyadirItem(escudo));
        assertEquals(escudo.getDescripcion(), personajeLimitado.getItems()[1].getDescripcion(), "El item del personaje no es correcto");
        assertEquals(escudo.getPeso(), personajeLimitado.getItems()[1].getPeso(), "El item del personaje no es correcto");
        assertEquals(escudo.getValor(), personajeLimitado.getItems()[1].getValor(), "El item del personaje no es correcto");
        assertFalse(personajeLimitado.anyadirItem(null), "No se debería poder añadir un item nulo");
        assertFalse(personajeLimitado.anyadirItem(escudo), "No se debería superar el número máximo de items del personaje");
    }

    @Test
    public void testGetItem() {
        personaje.anyadirItem(espada);
        assertEquals(espada.getDescripcion(), personaje.getItem(0).getDescripcion(), "El item del personaje no es correcto");
        assertEquals(espada.getPeso(), personaje.getItem(0).getPeso(), "El item del personaje no es correcto");
        assertEquals(espada.getValor(), personaje.getItem(0).getValor(), "El item del personaje no es correcto");
        assertNull(personaje.getItem(-1), "Acceder a un índice negativo no debería devolver un item");
        assertNull(personaje.getItem(5), "Acceder a un índice mayor que el tamaño del array no debería devolver un item");
    }

    @Test
    public void testGetItems() {
        personaje.anyadirItem(espada);
        assertEquals(espada.getDescripcion(), personaje.getItems()[0].getDescripcion(), "El item del personaje no es correcto");
        assertEquals(espada.getPeso(), personaje.getItems()[0].getPeso(), "El item del personaje no es correcto");
        assertEquals(espada.getValor(), personaje.getItems()[0].getValor(), "El item del personaje no es correcto");
    }

    @Test
    public void testToString() {
        assertEquals("{ Guerrero (V: 100, A: 10, D: 5, X: 10) }", personaje.toString(), "El método toString no es correcto");
    }

    @Test
    public void testCrearPersonaje() {
        String entrada = """
                Petarder
                100
                50
                50
                50""";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Personaje nuevoPersonaje = Personaje.crearPersonaje(new Scanner(entrada));
        assertEquals("Petarder", nuevoPersonaje.getNombre(), "El nombre del personaje no es correcto");
        assertEquals(100, nuevoPersonaje.getVida(), "La vida del personaje no es correcta");
        assertEquals(50, nuevoPersonaje.getAtaque(), "El ataque del personaje no es correcto");
        assertEquals(50, nuevoPersonaje.getDefensa(), "La defensa del personaje no es correcta");
        assertEquals(50, nuevoPersonaje.getDestreza(), "La destreza del personaje no es correcta");
        assertTrue(outContent.toString().contains("¿Cuánto ataque quieres tener? (1-148):"), "El rango del valor de ataque no se calcula correctamente");
        assertTrue(outContent.toString().contains("¿Cuánta defensa quieres tener? (1-99):"), "El rango del valor de defensa no se calcula correctamente");
        assertTrue(outContent.toString().contains("¿Cuánta destreza quieres tener? (1-50):"), "El rango del valor de destreza no se calcula correctamente");
    }
}
