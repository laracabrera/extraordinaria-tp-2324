import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MotorUnitTests {
    private Motor motor;
    private Sala sala;
    private Monstruo monstruo;
    private Trampa trampa;
    private Item item;
    private Personaje personaje;

    @BeforeEach
    public void setup() {
        sala = new Sala("description", 3, 3, 3, 0, 0);
        monstruo = new Monstruo("monster", 100, 10, 10);
        trampa = new Trampa("trap", 100);
        item = new Item("item", 1.0, 1.0);
        personaje = new Personaje("character", 100, 10, 10, 15, 10, 10);
        motor = new Motor(5, 5, 3, 3, 3);
    }

    @Test
    public void testCargarMapa() {
        motor.cargarMapa("configs/mapa.txt");
    }
}
