package resource;

import resource.Resource;
import java.util.ArrayList;
import java.util.Vector;
import resource.*;


public class Book extends Resource {

    private String gener;
    private int numPage, yearPub;


    public Book(int barcode,String title, ArrayList author, ArrayList langues, int numPage, int yearPub, String gener, Integer [] license){
        super(barcode,title,author,langues, license);
        this.yearPub=yearPub;
        this.gener=gener;
    }

    /**
     * Get and Set
     */



    public String getGener() {
        return gener;
    }

    public void setGener(String gener) {
        this.gener = gener;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public int getNumPage() {
        return numPage;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }


    public int getYearPub() {
        return yearPub;
    }

    public void setYearPub(int yearPub) {
        this.yearPub = yearPub;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getLangues() {
        return langues;
    }

    public void setLangues(ArrayList<String> langues) {
        this.langues = langues;
    }

    public ArrayList<String> getAuthor() {
        return author;
    }

    public void setAuthor(ArrayList<String> author) {
        this.author = author;
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
