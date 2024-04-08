import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrampaUnitTests {
    @Test
    public void testTrampa() {
        Trampa trampa = new Trampa("Trampa de fuego", 10);
        assertEquals("Trampa de fuego", trampa.getDescripcion());
        assertEquals(10, trampa.getDanyo());
    }
}
