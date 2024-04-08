import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Motor {
    Sala[][] mapa;
    private final int maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala;
    public Motor(int filas, int columnas, int maxItemsPorSala, int maxMonstruosPorSala, int maxTrampasPorSalas) {
        this.mapa = new Sala[filas][columnas];
        this.maxItemsPorSala = maxItemsPorSala;
        this.maxMonstruosPorSala = maxMonstruosPorSala;
        this.maxTrampasPorSala = maxTrampasPorSalas;
    }

    Sala[][] cargarMapa(String ficheroMapa) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(ficheroMapa));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parametros = linea.split(";");
                int fila = Integer.parseInt(parametros[0]);
                int columna = Integer.parseInt(parametros[1]);
                String descripcion = parametros[2];
                mapa[fila][columna] = new Sala(descripcion, maxItemsPorSala, maxMonstruosPorSala, maxTrampasPorSala, fila, columna);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero " + ficheroMapa);
        } catch (IOException e) {
            System.out.println("Error de lectura del fichero " + ficheroMapa);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el fichero " + ficheroMapa);
                }
            }
        }
        return mapa;
    }

    private void cargarItems(String ficheroItems) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(ficheroItems));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parametros = linea.split(";");
                int fila = Integer.parseInt(parametros[0]);
                int columna = Integer.parseInt(parametros[1]);
                String descripcion = parametros[2];
                double peso = Double.parseDouble(parametros[3]);
                double valor = Double.parseDouble(parametros[4]);

                mapa[fila][columna].agregarItem(new Item(descripcion, peso, valor));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero " + ficheroItems);
        } catch (IOException e) {
            System.out.println("Error de lectura del fichero " + ficheroItems);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el fichero " + ficheroItems);
                }
            }
        }
    }

    private void cargarMonstruos(String ficheroMonstruos) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(ficheroMonstruos));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parametros = linea.split(";");
                int fila = Integer.parseInt(parametros[0]);
                int columna = Integer.parseInt(parametros[1]);
                String nombre = parametros[2];
                int vida = Integer.parseInt(parametros[3]);
                int ataque = Integer.parseInt(parametros[4]);
                int defensa = Integer.parseInt(parametros[5]);
                mapa[fila][columna].agregarMonstruo(new Monstruo(nombre, vida, ataque, defensa));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero " + ficheroMonstruos);
        } catch (IOException e) {
            System.out.println("Error de lectura del fichero " + ficheroMonstruos);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el fichero " + ficheroMonstruos);
                }
            }
        }
    }

    private void cargarTrampas(String ficheroTrampas) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(ficheroTrampas));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parametros = linea.split(";");
                int fila = Integer.parseInt(parametros[0]);
                int columna = Integer.parseInt(parametros[1]);
                String tipo = parametros[2];
                int danyo = Integer.parseInt(parametros[3]);
                mapa[fila][columna].agregarTrampa(new Trampa(tipo, danyo));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero " + ficheroTrampas);
        } catch (IOException e) {
            System.out.println("Error de lectura del fichero " + ficheroTrampas);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el fichero " + ficheroTrampas);
                }
            }
        }
    }

    public void iniciar(String ficheroMapa, String ficheroItems, String ficheroMonstruos, String ficheroTrampas) {
        mapa = cargarMapa(ficheroMapa);
        cargarItems(ficheroItems);
        cargarMonstruos(ficheroMonstruos);
        cargarTrampas(ficheroTrampas);
    }

    public Sala getSala(int fila, int columna) {
        return mapa[fila][columna];
    }

    public String mostrarMapa(int fila, int columna) {
    StringBuilder sb = new StringBuilder();
    sb.append("╔");
    sb.append("═".repeat(mapa[0].length)).append("╗\n");
    for (int i = 0; i < mapa.length; i++) {
        sb.append("║");  // Draw the left border of the frame
        for (int j = 0; j < mapa[i].length; j++) {
            if (mapa[i][j] != null) {
                if (i == fila && j == columna) {
                    sb.append("@");
                } else {
                    sb.append("░");
                }
            } else {
                sb.append(" ");
            }
        }
        sb.append("║\n");
    }
    sb.append("╚");
    sb.append("═".repeat(mapa[0].length)).append("╝\n");
    return sb.toString();
}

    public void jugar(Scanner teclado, Personaje personaje, Random random) {
        int fila = 0;
        int columna = 0;

        System.out.print(mostrarMapa(fila, columna));

        Sala salaActual = getSala(fila, columna);
        while (personaje.getVida() > 0 && (salaActual.getFila() != mapa.length - 1 || salaActual.getColumna() != mapa[0].length - 1)) {
            System.out.println(salaActual.getDescripcion());
            while (salaActual.hayMonstruos() && personaje.getVida() > 0) {
                Monstruo monstruo = salaActual.seleccionarMonstruo(teclado);
                while (personaje.getVida() > 0 && monstruo.getVida() > 0) {
                    System.out.println(personaje + " ataca a " + monstruo + " con " + personaje.getAtaque() + " puntos de daño");
                    monstruo.recibirDanyo(personaje.getAtaque());
                    if (monstruo.getVida() > 0) {
                        personaje.recibirDanyo(monstruo.getAtaque());
                        System.out.println(monstruo + " ataca a " + personaje + " con " + monstruo.getAtaque() + " puntos de daño");

                    }
                }
                if (personaje.getVida() > 0) {
                    System.out.println("¡Has derrotado al monstruo!");
                    salaActual.eliminarMonstruo(monstruo.getNombre());
                } else {
                    System.out.println("¡Has sido derrotado por el monstruo!");
                }
            }
            int t = 0;
            Trampa[] trampas = salaActual.getTrampas();
            while (t < trampas.length && personaje.getVida() > 0) {
                if (trampas[t] != null) {
                    int destreza = personaje.getDestreza();
                    if (random.nextInt(50) < destreza) {
                        System.out.println("¡Has esquivado la trampa! " + trampas[t].getDescripcion());
                    } else {
                        System.out.println("¡Has caído en una trampa! " + trampas[t].getDescripcion());
                        personaje.recibirDanyo(trampas[t].getDanyo());
                        System.out.println("Te ha hecho " + trampas[t].getDanyo() + " puntos de daño");
                    }
                }
                t++;
            }
            if (personaje.getVida() > 0 && salaActual.hayItems()) {
                boolean masItems = true;
                while (salaActual.hayItems() && masItems) {
                    Item item = salaActual.seleccionarItem(teclado);
                    if (item != null) {
                        if (personaje.anyadirItem(item)) {
                            System.out.println("¡Te guardas el objeto! " + item);
                            System.out.println(personaje.infoMochila());
                            salaActual.eliminarItem(item.getDescripcion());
                        } else {
                            System.out.println("No puedes coger el item porque supera el peso máximo o la capacidad de la mochila");
                        }
                    } else {
                        masItems = false;
                    }
                }
            }
            if (personaje.getVida() > 0) {
                salaActual = seleccionarMovimiento(teclado, salaActual);
            }
        }
        if (personaje.getVida() > 0) {
            System.out.println("¡Has escapado del laberinto!");
        } else {
            System.out.println("¡Has muerto en el laberinto!");
        }
        System.out.println(personaje.infoMochila());
    }

    public Sala seleccionarMovimiento(Scanner teclado, Sala salaActual) {
        int fila = salaActual.getFila();
        int columna = salaActual.getColumna();
        Sala salaDestino;
        boolean seleccionado = false;
        while (!seleccionado) {
            System.out.println(this.mostrarMapa(fila, columna));
            String movimiento = Utilidades.leerCadena(teclado, "Introduce el movimiento (N, E, S, O): ");
            switch (movimiento) {
                case "N":
                    if (fila > 0 && mapa[fila - 1][columna] != null) {
                        fila--;
                        seleccionado = true;
                    } else {
                        System.out.println("No puedes moverte al norte");
                    }
                    break;
                case "S":
                    if (fila < mapa.length - 1 && mapa[fila + 1][columna] != null) {
                        fila++;
                        seleccionado = true;
                    } else {
                        System.out.println("No puedes moverte al sur");
                    }
                    break;
                case "E":
                    if (columna < mapa[fila].length - 1 && mapa[fila][columna + 1] != null) {
                        columna++;
                        seleccionado = true;
                    } else {
                        System.out.println("No puedes moverte al este");
                    }
                    break;
                case "O":
                    if (columna > 0 && mapa[fila][columna - 1] != null) {
                        columna--;
                        seleccionado = true;
                    } else {
                        System.out.println("No puedes moverte al oeste");
                    }
                    break;
                default:
                    System.out.println("Movimiento no válido (N, E, S, O)");
                    break;
            }
        }
        salaDestino = getSala(fila, columna);
        return salaDestino;
    }
}
