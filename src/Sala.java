import java.util.Scanner;

/**
 * Clase que representa una sala de un laberinto.
 * Cada sala tiene una descripción, un array de items, un array de monstruos y un array de trampas.
 * Además, cada sala tiene una fila y una columna que indican su posición en el laberinto.
 */
public class Sala {
    /** Descripción de la sala. */
    private final String descripcion;
    /** Array de items de la sala. */
    private final Item[] items;
    /** Array de monstruos de la sala. */
    private final Monstruo[] monstruos;
    /** Array de trampas de la sala. */
    private final Trampa[] trampas;
    /** Fila de la sala en el mapa. */
    private final int fila;
    /** Columna de la sala en el mapa. */
    private final int columna;

    /**
     * Constructor de la clase Sala.
     * @param descripcion Descripción de la sala.
     * @param max_items Número máximo de items que puede haber en la sala.
     * @param max_monstruos Número máximo de monstruos que puede haber en la sala.
     * @param maxTrampasPorSala Número máximo de trampas que puede haber en la sala.
     * @param fila Fila de la sala en el laberinto.
     * @param columna Columna de la sala en el laberinto.
     */
    public Sala(String descripcion, int max_items, int max_monstruos, int maxTrampasPorSala, int fila, int columna) {
        this.descripcion = descripcion;
        this.items = new Item[max_items];
        this.monstruos = new Monstruo[max_monstruos];
        this.trampas = new Trampa[maxTrampasPorSala];
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Método para añadir un item a la sala.
     * Si ya existe un item con la misma descripción, no se añade.
     * Si no hay espacio en la sala para añadir el item, no se añade.
     * @param item Item que se quiere añadir a la sala.
     * @return true si se ha añadido el item, false en caso contrario.
     */
    public boolean agregarItem(Item item) {
        // Si ya existe un item con la misma descripción, no se añade
        if (buscarItem(item.getDescripcion()) != null) {
            return false;
        }

        int i = 0;
        while (i < items.length && items[i] != null) {
            i++;
        }
        if (i < items.length) {
            items[i] = item;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para añadir un monstruo a la sala.
     * Si ya existe un monstruo con el mismo nombre, no se añade.
     * Si no hay espacio en la sala para añadir el monstruo, no se añade.
     * @param monstruo Monstruo que se quiere añadir a la sala.
     * @return true si se ha añadido el monstruo, false en caso contrario.
     */
    public boolean agregarMonstruo(Monstruo monstruo) {
        // Si ya existe un monstruo con el mismo nombre, no se añade
        if (buscarMonstruo(monstruo.getNombre()) != null) {
            return false;
        }

        int i = 0;
        while (i < monstruos.length && monstruos[i] != null) {
            i++;
        }
        if (i < monstruos.length) {
            monstruos[i] = monstruo;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para añadir una trampa a la sala.
     * Si ya existe una trampa con la misma descripción, no se añade.
     * Si no hay espacio en la sala para añadir la trampa, no se añade.
     * @param trampa Trampa que se quiere añadir a la sala.
     * @return true si se ha añadido la trampa, false en caso contrario.
     */
    public boolean agregarTrampa(Trampa trampa) {
        // Si ya existe una trampa con la misma descripción, no se añade
        if (buscarTrampa(trampa.getDescripcion()) != null) {
            return false;
        }

        int i = 0;
        while (i < trampas.length && trampas[i] != null) {
            i++;
        }
        if (i < trampas.length) {
            trampas[i] = trampa;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para obtener la descripción de la sala.
     * @return Descripción de la sala.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método para consultar si quedan monstruos en la sala.
     * @return true si quedan monstruos en la sala, false en caso contrario.
     */
    public boolean hayMonstruos() {
        for (Monstruo monstruo : monstruos) {
            if (monstruo != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método para seleccionar un monstruo de la sala. Muestra los monstruos disponibles y pide al usuario que seleccione uno.
     * @param teclado Scanner para leer la entrada del usuario.
     * @return Monstruo seleccionado.
     */
    public Monstruo seleccionarMonstruo(Scanner teclado) {
        Monstruo monstruo = null;
        listarMonstruos();
        do {
            String nombreMonstruo = Utilidades.leerCadena(teclado, "Escribe el nombre del monstruo que quieres atacar: ");
            monstruo = buscarMonstruo(nombreMonstruo);
        } while (monstruo == null);
        return monstruo;
    }

    /**
     * Método para buscar un monstruo en la sala por su nombre.
     * @param nombreMonstruo Nombre del monstruo que se quiere buscar.
     * @return Monstruo encontrado o null si no se ha encontrado.
     */
    public Monstruo buscarMonstruo(String nombreMonstruo) {
        Monstruo monstruo = null;
        boolean encontrado = false;
        for (int i = 0; i < monstruos.length && !encontrado; i++) {
            if (monstruos[i] != null && monstruos[i].getNombre().equals(nombreMonstruo)) {
                monstruo = monstruos[i];
                encontrado = true;
            }
        }
        return monstruo;
    }

    /**
     * Método para mostrar en un listado los monstruos que hay en la sala.
     * Se muestra el nombre de cada monstruo.
     */
    private void listarMonstruos() {
        System.out.println("Monstruos en la sala:");
        for (Monstruo monstruo : monstruos) {
            if (monstruo != null) {
                System.out.println(monstruo);
            }
        }
    }

    /**
     * Método para eliminar un monstruo de la sala por su nombre.
     * @param nombreMonstruo Nombre del monstruo que se quiere eliminar.
     */
    public void eliminarMonstruo(String nombreMonstruo) {
        int i = 0;
        boolean encontrado = false;
        while (i < monstruos.length && !encontrado) {
            if (monstruos[i] != null && monstruos[i].getNombre().equals(nombreMonstruo)) {
                monstruos[i] = null;
                encontrado = true;
            }
            i++;
        }
    }

    /**
     * Método para consultar si hay trampas en la sala.
     * @return true si hay trampas en la sala, false en caso contrario.
     */
    public boolean hayTrampas() {
        int i = 0;
        boolean encontrado = false;
        while (i < trampas.length && !encontrado) {
            if (trampas[i] != null) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    /**
     * Método para obtener la fila de la sala.
     * @return Fila de la sala.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Método para obtener la columna de la sala.
     * @return Columna de la sala.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Método para consultar si hay items en la sala.
     * @return true si hay items en la sala, false en caso contrario.
     */
    public boolean hayItems() {
        int i = 0;
        boolean encontrado = false;
        while (i < items.length && !encontrado) {
            if (items[i] != null) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    /**
     * Método para buscar un item en la sala por su descripción.
     * @param descripcion Descripción del item que se quiere buscar.
     * @return Item encontrado o null si no se ha encontrado.
     */
    public Item buscarItem(String descripcion) {
        Item item = null;
        boolean encontrado = false;
        for (int i = 0; i < items.length && !encontrado; i++) {
            if (items[i] != null && items[i].getDescripcion().equals(descripcion)) {
                item = items[i];
                encontrado = true;
            }
        }
        return item;
    }

    /**
     * Método para buscar una trampa en la sala por su descripción.
     * @param descripcion Descripción de la trampa que se quiere buscar.
     * @return Trampa encontrada o null si no se ha encontrado.
     */
    public Trampa buscarTrampa(String descripcion) {
        Trampa trampa = null;
        boolean encontrado = false;
        for (int i = 0; i < trampas.length && !encontrado; i++) {
            if (trampas[i] != null && trampas[i].getDescripcion().equals(descripcion)) {
                trampa = trampas[i];
                encontrado = true;
            }
        }
        return trampa;
    }

    /**
     * Método para obtener las trampas de la sala.
     * @return Array de trampas de la sala.
     */
    public Trampa[] getTrampas() {
        return trampas;
    }

    /**
     * Método para seleccionar un item de la sala. Muestra los items disponibles y pide al usuario que seleccione un0.
     * @param teclado Scanner para leer la entrada del usuario.
     * @return Item seleccionado.
     */
    public Item seleccionarItem(Scanner teclado) {
        Item item;
        listarItems();
        String descripcionItem;
        do {
            descripcionItem = Utilidades.leerCadena(teclado, "Escribe la descripción del item que quieres coger (NINGUNO para cancelar): ");
            item = buscarItem(descripcionItem);
        } while (item == null && !descripcionItem.equals("NINGUNO"));
        return item;
    }

    /**
     * Método para mostrar en un listado los items que hay en la sala.
     * Se muestra la descripción de cada item.
     */
    private void listarItems() {
        System.out.println("Items en la sala:");
        for (Item item : items) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }

    /**
     * Método para eliminar un item de la sala por su descripción.
     * @param descripcion Descripción del item que se quiere eliminar.
     */
    public void eliminarItem(String descripcion) {
        int i = 0;
        boolean encontrado = false;
        while (i < items.length && !encontrado) {
            if (items[i] != null && items[i].getDescripcion().equals(descripcion)) {
                items[i] = null;
                encontrado = true;
            }
            i++;
        }
    }
}
