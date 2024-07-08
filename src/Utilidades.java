import java.util.Scanner;

/**
 * Clase con métodos de utilidad para la entrada de datos por teclado.
 */
public class Utilidades {

    /**
     * Lee una cadena de texto por teclado.
     * @param teclado Scanner para leer de teclado.
     * @param s Mensaje a mostrar al usuario.
     * @return Cadena de texto introducida por el usuario.
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s);
        return teclado.nextLine();
    }

    /**
     * Lee un número entero por teclado.
     * @param teclado Scanner para leer de teclado.
     * @param mensaje Mensaje a mostrar al usuario.
     * @param minimo Valor mínimo que puede introducir el usuario.
     * @param maximo Valor máximo que puede introducir el usuario.
     * @return Número entero introducido por el usuario.
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int numero;
        do {
            System.out.print(mensaje);
            numero = teclado.nextInt();
        } while (numero < minimo || numero > maximo);
        return numero;
    }
}
