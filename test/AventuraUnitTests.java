import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AventuraUnitTests {
    @TempDir
    Path tempDir;

    @Test
    public void testGuardarLeerPuntuacion() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Personaje p = new Personaje("Jugador", 100, 10, 5, 20, 5, 2.5);
        Personaje p2 = new Personaje("Jugador2", 50, 5, 5, 10, 5, 2.5);
        Item i = new Item("Espada", 10, 5);
        p.anyadirItem(i);
        Item i2 = new Item("Escudo", 5, 10);
        p2.anyadirItem(i2);

        Method guardarPuntuacion = Aventuras.class.getDeclaredMethod("guardarPuntuacion", String.class, Personaje.class);
        Method mostrarPuntuaciones = Aventuras.class.getDeclaredMethod("mostrarPuntuaciones", String.class);

        String ficheroPuntuaciones = tempDir.resolve("puntuaciones.txt").toString();

        guardarPuntuacion.setAccessible(true);
        mostrarPuntuaciones.setAccessible(true);

        guardarPuntuacion.invoke(null, ficheroPuntuaciones, p);
        guardarPuntuacion.invoke(null, ficheroPuntuaciones, p2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        mostrarPuntuaciones.invoke(null, ficheroPuntuaciones);

        String[] lines = outContent.toString().split("\n");

        assertEquals(
                lines[1].trim(),
                LocalDate.now() + "\t" + p + ", " + p.getValorMochila() + " monedas",
                "Las puntuaciones no se almacenan correctamente"
        );
        assertEquals(
                lines[2].trim(),
                LocalDate.now() + "\t" + p2 + ", " + p2.getValorMochila() + " monedas",
                "Las puntuaciones no se almacenan correctamente"
        );
    }
}
