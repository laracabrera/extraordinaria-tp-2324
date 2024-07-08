import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MotorUnitTests {
    Motor m;

    @BeforeEach
    public void setUp() {
        m = new Motor(4, 3, 5, 5, 5);
        m.iniciar("test_res/mapa_simple.txt",
                "test_res/items_simple.txt",
                "test_res/monstruos_simple.txt",
                "test_res/trampas_simple.txt");
    }

    @Test
    public void cargarMapaConFicheroInexistenteMuestraMensajeError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Motor motor = new Motor(5, 5, 3, 3, 3);
        motor.cargarMapa("ficheroInexistente.txt");
        String expectedOutput = "No se ha encontrado el fichero ficheroInexistente.txt\n";
        assertEquals(expectedOutput, outContent.toString(), "Debe mostrar mensaje de fichero no encontrado");
    }

    @Test
    public void iniciarConFicheroInexistenteDeItemsMuestraMensajeError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Motor motor = new Motor(5, 5, 3, 3, 3);
        motor.iniciar("test_res/mapa_simple.txt",
                "ficheroInexistente.txt",
                "test_res/monstruos_simple.txt",
                "test_res/trampas_simple.txt");
        String expectedOutput = "No se ha encontrado el fichero ficheroInexistente.txt\n";
        assertEquals(expectedOutput, outContent.toString(), "Debe mostrar mensaje de fichero no encontrado");
    }

    @Test
    public void iniciarConFicheroInexistenteDeMonstruosMuestraMensajeError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Motor motor = new Motor(5, 5, 3, 3, 3);
        motor.iniciar("test_res/mapa_simple.txt",
                "test_res/items_simple.txt",
                "ficheroInexistente.txt",
                "test_res/trampas_simple.txt");
        String expectedOutput = "No se ha encontrado el fichero ficheroInexistente.txt\n";
        assertEquals(expectedOutput, outContent.toString(), "Debe mostrar mensaje de fichero no encontrado");
    }

    @Test
    public void iniciarConFicheroInexistenteDeTrampasMuestraMensajeError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Motor motor = new Motor(5, 5, 3, 3, 3);
        motor.iniciar("test_res/mapa_simple.txt",
                "test_res/items_simple.txt",
                "test_res/monstruos_simple.txt",
                "ficheroInexistente.txt");
        String expectedOutput = "No se ha encontrado el fichero ficheroInexistente.txt\n";
        assertEquals(expectedOutput, outContent.toString(), "Debe mostrar mensaje de fichero no encontrado");
    }

    @Test
    public void testGetSala() {
        Sala sala = m.getSala(0, 0);
        assertNotNull(sala, "La sala no debe ser nula");
        assertEquals(0, sala.getFila(), "La fila de la sala debe ser 0");
        assertEquals(0, sala.getColumna(), "La columna de la sala debe ser 0");
    }

    @Test
    public void testSalasCargadasCorrectamente() {
        Sala cerocero = m.getSala(0, 0);
        assertNotNull(cerocero, "La sala 0,0 no debe ser nula");
        assertFalse(cerocero.hayTrampas(), "La sala 0,0 no debe tener trampas");
        assertFalse(cerocero.hayMonstruos(), "La sala 0,0 no debe tener monstruos");
        assertFalse(cerocero.hayItems(), "La sala 0,0 debe tener items");
        Sala cerouno = m.getSala(0, 1);
        assertNotNull(cerouno, "La sala 0,1 no debe ser nula");
        assertFalse(cerouno.hayItems(), "La sala 0,1 no debe tener items");
        assertTrue(cerouno.hayMonstruos(), "La sala 0,1 debe tener monstruos");
        assertFalse(cerouno.hayTrampas(), "La sala 0,1 no debe tener trampas");
        Sala unouno = m.getSala(1, 1);
        assertNotNull(unouno, "La sala 1,1 no debe ser nula");
        assertTrue(unouno.hayItems(), "La sala 1,1 debe tener items");
        assertFalse(unouno.hayMonstruos(), "La sala 1,1 no debe tener monstruos");
        assertFalse(unouno.hayTrampas(), "La sala 1,1 no debe tener trampas");
        Sala doscero = m.getSala(2, 0);
        assertNotNull(doscero, "La sala 2,0 no debe ser nula");
        assertFalse(doscero.hayItems(), "La sala 2,0 no debe tener items");
        assertFalse(doscero.hayMonstruos(), "La sala 2,0 no debe tener monstruos");
        assertTrue(doscero.hayTrampas(), "La sala 2,0 debe tener trampas");
        Sala tresuno = m.getSala(3, 1);
        assertNotNull(tresuno, "La sala 3,1 no debe ser nula");
        assertTrue(tresuno.hayItems(), "La sala 3,1 debe tener items");
        assertTrue(tresuno.hayMonstruos(), "La sala 3,1 debe tener monstruos");
        assertTrue(tresuno.hayTrampas(), "La sala 3,1 debe tener trampas");
    }

    @Test
    public void testMostrarMapa() {
        String mapa_esperado = """
                ╔═══╗
                ║░░ ║
                ║ ░ ║
                ║░░░║
                ║ ░@║
                ╚═══╝
                """;
        assertEquals(mapa_esperado, m.mostrarMapa(3, 2), "El mapa no se dibuja correctamente");
    }

    @Test
    public void testSeleccionarMovimiento() {
        String norte = "N";
        String sur = "S";
        String este = "E";
        String oeste = "O";

//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
        assertEquals(
                m.getSala(1, 1),
                m.seleccionarMovimiento(new Scanner(norte), m.getSala(2, 1)), "Debería moverse al norte"
        );
        assertEquals(
                m.getSala(3, 1),
                m.seleccionarMovimiento(new Scanner(sur), m.getSala(2, 1)), "Debería moverse al sur"
        );
        assertEquals(
                m.getSala(2, 2),
                m.seleccionarMovimiento(new Scanner(este), m.getSala(2, 1)), "Debería moverse al este"
        );
        assertEquals(
                m.getSala(2, 0),
                m.seleccionarMovimiento(new Scanner(oeste), m.getSala(2, 1)), "Debería moverse al oeste"
        );

        // Movimientos incorrectos
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        m.seleccionarMovimiento(new Scanner("E\nN\nX\nS"), m.getSala(2, 2));
        assertTrue(outContent.toString().contains("No puedes moverte al este"), "Debería mostrar mensaje de error al moverse al este desde 2,2");
        assertTrue(outContent.toString().contains("No puedes moverte al norte"), "Debería mostrar mensaje de error al moverse al norte desde 2,2");
        assertTrue(outContent.toString().contains("Movimiento no válido (N, E, S, O)"), "Debería mostrar mensaje de error al moverse en dirección incorrecta");

        outContent.reset();

        m.seleccionarMovimiento(new Scanner("O\nE"), m.getSala(2, 0));
        assertTrue(outContent.toString().contains("No puedes moverte al oeste"), "Debería mostrar mensaje de error al moverse al oeste desde 2,0");

        outContent.reset();
        m.seleccionarMovimiento(new Scanner("S\nE"), m.getSala(3, 1));
        assertTrue(outContent.toString().contains("No puedes moverte al sur"), "Debería mostrar mensaje de error al moverse al sur desde 3,1");
    }

    @Test
    public void testJugarPersonajeMuere() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Personaje p = new Personaje("Test", 1, 1, 1, 10, 10, 10);
        m.jugar(
                new Scanner("E\nOrco"),
                p,
                new Random(1111)
        );
        assertTrue(outContent.toString().contains("¡Has sido derrotado por el monstruo!"), "El personaje debe morir al entrar en una sala con monstruos");
        assertTrue(outContent.toString().contains("¡Has muerto en el laberinto!"), "El personaje debe morir al quedarse sin vida");
    }

    @Test
    public void testJugarPersonajeGana() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Personaje p = new Personaje("Test", 10000, 100000, 100000, 10000, 1000, 10000);
        String entrada = """
                E
                Orco
                S
                Armadura Encantada
                S
                Trasgo
                Bruja
                Sombrero de Mago
                Oro del Goblin
                E
                S""";

        m.jugar(
                new Scanner(entrada),
                p,
                new Random(1111)
        );
        assertTrue(outContent.toString().contains("¡Has escapado del laberinto!"), "El personaje debe ganar al llegar a la salida");
        assertEquals(p.getValorMochila(), 330, "El valor final de la mochila debe coincidir con los objetos recogidos");
        assertEquals(p.getPesoMochila(), 5.7, "El peso final de la mochila debe coincidir con los objetos recogidos");
    }

    @Test
    public void testJugarPersonajeGanaSinObjetos() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Personaje p = new Personaje("Test", 10000, 100000, 100000, 10000, 1000, 10000);
        String entrada = """
                E
                Orco
                S
                NINGUNO
                S
                Trasgo
                Bruja
                NINGUNO
                E
                S""";

        m.jugar(
                new Scanner(entrada),
                p,
                new Random(1111)
        );
        assertTrue(outContent.toString().contains("¡Has escapado del laberinto!"), "El personaje debe ganar al llegar a la salida");
        assertEquals(p.getValorMochila(), 0, "El valor final de la mochila debe coincidir con los objetos recogidos");
        assertEquals(p.getPesoMochila(), 0, "El peso final de la mochila debe coincidir con los objetos recogidos");
    }
}
