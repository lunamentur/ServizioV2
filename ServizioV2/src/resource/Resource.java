package resource;

import java.util.ArrayList;

public class Resource {

    String title;
    int barcode;
    ArrayList<String> langues, author;

    /**
     * Array license
     * Indice in posizione 0 indica il numero di copie totali
     * indice in posizione 1 indica il numero di copie in prestito
     * @param license La differenza fra i valori all'interno dell'array indica le copie he sono disponibili: se minore o uguale a zero non vi sono copie disponibili al prestito
     *                ovvero non vi sono licenze disponibili.
     */
    private Integer [] license;

    /**
     * Costruttore della superCLASSE
     */
    public Resource(int barcode, String title, ArrayList author, ArrayList langues, Integer [] license){
        this.author= author;
        this.barcode= barcode;
        this.langues= langues;
        this.title=title;
        this.license=license;
    }


    /**
     * GET & SET
     */
    public Integer[] getLicense() {
        return license;
    }

    public void setLicense(Integer[] license) {
        this.license = license;
    }
}
