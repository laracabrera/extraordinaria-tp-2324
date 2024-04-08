import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class Aventuras {
    public static void main(String[] args) {
        int filas = Integer.parseInt(args[0]);
        int columnas = Integer.parseInt(args[1]);
        int maxItemsPorSala = Integer.parseInt(args[2]);
        int maxMonstruosPorSala = Integer.parseInt(args[3]);
        int maxTrampasPorSala = Integer.parseInt(args[4]);
        String fichero_mapa = args[5];
        String fichero_items = args[6];
        String fichero_monstruos = args[7];
        String fichero_trampas = args[8];
        String fichero_puntuaciones = args[9];
        Motor motor = new Motor(filas, columnas, maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala);
        motor.iniciar(fichero_mapa, fichero_items, fichero_monstruos, fichero_trampas);

        mostrarPuntuaciones(fichero_puntuaciones);

        Scanner teclado = new Scanner(System.in);
        Random rng = new Random();

        Personaje jugador = Personaje.crearPersonaje(teclado);

        motor.jugar(teclado, jugador, rng);

        guardarPuntuacion(fichero_puntuaciones, jugador);
    }

    private static void guardarPuntuacion(String ficheroPuntuaciones, Personaje jugador) {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(ficheroPuntuaciones, true))) {
            pw.println(LocalDate.now() +"\t" + jugador + ", " + jugador.getValorMochila() + " monedas");
        } catch (java.io.IOException e) {
            System.out.println("No se ha podido guardar la puntuación.");
        }
    }

    private static void mostrarPuntuaciones(String ficheroPuntuaciones) {
        System.out.println("Puntuaciones:");
        try (Scanner sc = new Scanner(new File(ficheroPuntuaciones))) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido leer el fichero de puntuaciones.");
        }
    }
}
