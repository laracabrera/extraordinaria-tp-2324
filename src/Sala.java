import java.util.Scanner;

public class Sala {
    private final String descripcion;
    private final Item[] items;
    private final Monstruo[] monstruos;
    private final Trampa[] trampas;

    private final int fila;
    private final int columna;

    public Sala(String descripcion, int max_items, int max_monstruos, int maxTrampasPorSala, int fila, int columna) {
        this.descripcion = descripcion;
        this.items = new Item[max_items];
        this.monstruos = new Monstruo[max_monstruos];
        this.trampas = new Trampa[maxTrampasPorSala];
        this.fila = fila;
        this.columna = columna;
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public boolean hayMonstruos() {
        for (Monstruo monstruo : monstruos) {
            if (monstruo != null) {
                return true;
            }
        }
        return false;
    }

    public Monstruo seleccionarMonstruo(Scanner teclado) {
        Monstruo monstruo = null;
        listarMonstruos();
        do {
            String nombreMonstruo = Utilidades.leerCadena(teclado, "Escribe el nombre del monstruo que quieres atacar: ");
            monstruo = buscarMonstruo(nombreMonstruo);
        } while (monstruo == null);
        return monstruo;
    }

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

    private void listarMonstruos() {
        System.out.println("Monstruos en la sala:");
        for (Monstruo monstruo : monstruos) {
            if (monstruo != null) {
                System.out.println(monstruo);
            }
        }
    }

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

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

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

    public Trampa[] getTrampas() {
        return trampas;
    }

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

    private void listarItems() {
        System.out.println("Items en la sala:");
        for (Item item : items) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }

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
