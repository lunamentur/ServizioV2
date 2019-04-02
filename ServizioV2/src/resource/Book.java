package resource;
import java.util.ArrayList;
import resource.*;

/**
 * Classe Book che rappresenta una categoria specifica di Resource, con i relativi attributi e metodi set e get.
 * @author Reda Kassame, Simona Ramazzotti.
 * @version 2
 * */
public class Book extends Resource {
    private String gener;
    private int numPage, yearPub;


    public Book(int barcode,String title, ArrayList author, ArrayList langues, int numPage, int yearPub, String gener, Integer [] license){
        super(barcode,title,author,langues, license);
        this.yearPub=yearPub;
        this.gener=gener;
        this.numPage=numPage;
    }

    /**
     * Get and Set
     */

    public int getBarcode() {
        return barcode;
    }

    @Override
    public String toString() {
        return title + "  {" +
                " barcode=  '" + barcode + '\'' +
                ", autori=  '" + author + '\'' +
                ", lingue=  '" + langues + '\'' +
                ", anno di pubblicazione=  " + yearPub +
                ", genere=  " + gener +
                "  }";
    }
}
