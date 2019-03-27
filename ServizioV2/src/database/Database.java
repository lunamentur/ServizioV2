package database;
import operator.User;
import resource.Resource;
import resource.Book;
import view.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;


/**
 * Classe Database che racchiude metodi e gestione del database in cui sono raccolti tutti gli user e oggetti inerenti.
 * @author Reda, Simona
 */
public class Database {
    private static View view=new View();
    private static int i=0;


    /**
     * METODI USER
     */

	/**
	 * Lista di user, oggetti di tipo User, con key il nome utente, username di tipo String.
	 * Mediante l'utilizzo di tale HashMap siamo in grado di collegare ogni utente (con le relative informazioni anagrafiche) con una key, ovvero username.
	 */
    static Map<String, User> userList= new HashMap<>();
    
    /**
     * Medoto che permette l'inserimento di un nuovo user, oggetto di tipo User, all'interno della nostra lista di user, HashMap.
     */
    public static void insertUser(User newuser){
        userList.put(newuser.getUsername(),newuser);
        System.out.println("<+> New user added!");
    }
    /**
     * Metodo, di stampa, che permette di visualizzare a video la lista di tutti gli user all'interno del database, l'HashMap.
     */
    public static void listUsers(){
        for (Map.Entry<String,User> user: userList.entrySet()){
            System.out.println(user.toString());
        }
    }
    
    /**
     * Metodo che permette all'user di effettuare il login. Verifica che esista il nome utente, username, e controlla che la password sia la medesima.
     * Se il login va a buon fine, ritorna true, l'user ha accesso ai servizi di prestito temporaneo, altrimenti false.
     * @param username
     * @param password
     * @return false login non riuscito.
     * @return true login riuscito corettamente.
     */
    public static boolean checkLoginIfTrue(String username, String password){
        if(userList.containsValue(username) && userList.get(username).getPassword() == password) return true;
        else return false;
    }

    /**
     * Metodo che permette di verificare se un user e\' presente nel Database.
     * return true se e\' presente
     * return false altrimenti
     */
    public static boolean checkIfUser(String username){
        return userList.containsValue(username);
    }

    /**
     * Metodo che ritorna l'user, l'oggetto di tipo User, preso dal Database.
     * @param username
     * @return user
     */
    public static User getUser(String username){
        return userList.get(username);
    }

    /**
     * Metodo di controllo che verifica che l'user, oggetto di tipo User, sia maggiorenne.
     * Pertanto confronta la data di nascita, di tipo LocalDate, con la data attuale, LocalDate.now(), affinche\' sia 
     * maggiore o uguale a 18 anni.
     * @param birthDate  Data di nascita dell'user, di tipo LocalDate. E\' richiesto che sia maggiorenne per poter diventare utente dei servizi.
     * @return false se l'user non e\' maggiorenne. (e quindi non ha accesso ai servizi di prestito temporaneo)
     * @return true se l'user e\' maggiorenne.
     */
    public static boolean checkIf18(LocalDate birthDate){
        LocalDate now = LocalDate.now();
        int age = Period.between(birthDate,now).getYears();
        if(age > 18){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * METODI RESOURCE BOOK
     */

    /**
     * Metodo che ritorna la risorsa libro, oggetto di tipo Book, preso dal Database.
     * @param barcode codice a barre univoco associato a un libro.
     * @return Book
     */
    public static Book getBook(int barcode){
        return bookList.get(barcode);
    }

    /**
     * Metodo che permette di verificare se un libro e\' presente nel Database
     * return true se la risorsa e\' presente
     * return false altrimenti
     */
    public static boolean checkIfBook(int barcode){
         return bookList.containsValue(barcode);
    }

    /**
     * Lista di libri, oggetti di tipo Book, con key barcode di tipo Int.
     * Mediante l'utilizzo di tale HashMap siamo in grado di collegare ogni libro (con i relativi campi di informazioni) con una key, ovvero il barcode.
     */
    static Map<Integer, Book> bookList= new HashMap<>();

    /**
     * Medoto che permette l'inserimento di un nuovo libro, oggetto di tipo Book, all'interno della nostra lista di libri, HashMap.
     */
    public static void insertBook(Book newbook){
        int barcode= newbook.getBarcode();
        bookList.put(barcode,newbook); //non capisco perchè mi dia errore.....
        System.out.println("<+> New book added!");
    }
    /**
     * Metodo, di stampa, che permette di visualizzare a video la lista di tutti i libri all'interno del database, l'HashMap.
     */
    public static void listBook(){
        System.out.println(bookList);
    }

    /**
     * Metodo che permette di rimuovere una risorsa dall'elenco di risorse
     */
    public static void removeBook(int barcode){
        if(checkIfBook(barcode)) {
            bookList.remove(barcode);
            view.stampaRichiestaSingola(view.MG_AZIONE_SUCCESSO);
        }
        else view.stampaRichiestaSingola(view.NON_ESISTE);
    }

    /**
     * Metodo che incrementa il numero di copie disponibili al prestito di una risorsa.
     * Ovvero dato il vettore di due componenti con le copie disponibili e quelle in prestivo vado a incrementare la prima.
     */
    public static void incrementLicense(int barcode){
        Integer[] copie= getBook(barcode).getLicense();
        //prende la var in posizione zero, ovvero le copie disponibili.
        copie[0]++;
        //sostituisco il mio nuovo vettore col set della classe Book
        getBook(barcode).setLicense(copie);
    }
}