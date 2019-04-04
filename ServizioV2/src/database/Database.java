package database;
import operator.User;
import resource.Book;
import view.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Classe Database che racchiude metodi e gestione del database in cui sono raccolti tutti gli user e oggetti inerenti.
 * @author Reda Kassame, Simona Ramazzotti.
 * @version 2
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
     * Metodo che permette di rimuovere un'utente dall'archivio.
     */
    public static void removeUser(String username){
        userList.remove(username);
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
        if(userList.containsKey(username) && userList.get(username).getPassword().equals(password)) return true;
        else return false;
    }

    /**
     * Metodo che permette di verificare se un user e\' presente nel Database.
     * return true se e\' presente
     * return false altrimenti
     */
    public static boolean checkIfUser(String username){
        return userList.containsKey(username);
    }


    /**
     * Metodo che dato un oggetto di tipo user in ingresso ci informa se e\' un Admin oppure un semplice fruitore del servizio.
     * @param username
     * @return user.getIsAdmni() true or false.
     */
    public static boolean checkAdminIfTrue(String username){
        return userList.get(username).getIsAdmin();
    }

    public static void isAdmin(String username){
        userList.get(username).setAdmin(true);
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
        return age >= 18;
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
         return bookList.containsKey(barcode);
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
        bookList.put(newbook.getBarcode(), newbook); //non capisco perchè mi dia errore.....
        System.out.println("<+> New book added!");
    }
    /**
     * Metodo, di stampa, che permette di visualizzare a video la lista di tutti i libri all'interno del database, l'HashMap.
     */
    public static void listBook(){
        for (Map.Entry<Integer,Book> book: bookList.entrySet()){
            System.out.println(book.toString());
        }
    }

    /**
     * Metodo che permette di rimuovere una risorsa dall'elenco di risorse oppure ne diminuisce le copie nell'archivio.
     */
    public static void removeBook(int barcode){
        if(checkIfBook(barcode)) {
            Integer[] copie= getBook(barcode).getLicense();
            if(copie[0]==1){
                bookList.remove(barcode);
            }else decrementLicense(barcode);
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

    /**
     * Metodo che decrementa il numero di copie di una specifica risorsa, lavorando sul primo indice del vettore licenze.
     */
    public static void decrementLicense(int barcode){
        Integer[] copie= getBook(barcode).getLicense();
        //prende la var in posizione zero, ovvero le copie disponibili.
        copie[0]--;
        //sostituisco il mio nuovo vettore col set della classe Book
        getBook(barcode).setLicense(copie);
    }

    /**
     * Creazione di oggetti preimpostati.
     */
    public static void initAllObject() {
        //genero utenti
        User user1 = new User("test", "test", "test1", "test1", LocalDate.of(1996, 12, 01), LocalDate.of(2018, 01, 01));
        User user2 = new User("test", "test", "test2", "test2", LocalDate.of(2000, 12, 01), LocalDate.of(1996, 01, 01));
        User user3 = new User("test", "test", "test3", "test3", LocalDate.of(1988, 12, 01), LocalDate.of(2019, 01, 01));
        User userRinnovo= new User("test","test","rinnovo","rinnovo",LocalDate.of(1996, 01, 01), LocalDate.of(2014, 04, 18));
        userList.put(userRinnovo.getUsername(), userRinnovo);
        userList.put(user1.getUsername(), user1);
        userList.put(user2.getUsername(), user2);
        userList.put(user3.getUsername(), user3);


        User userScaduto= new User("test","test","scaduto","scaduto",LocalDate.of(1996, 01, 01), LocalDate.of(2012, 02, 03));
        userList.put(userScaduto.getUsername(), userScaduto);

        //genero admin
        User admin = new User("admin", "admin", "admin", "admin", LocalDate.of(2000, 12, 01), LocalDate.of(2018, 01, 01));
        admin.setAdmin(true);
        userList.put(admin.getUsername(), admin);

        //genero libri
        ArrayList<String> langues_test = new ArrayList<String>();
        ArrayList<String> author_test = new ArrayList<String>();
        Integer [] license_book1={4,0};
        Integer [] license_book2={2,0};
        Integer [] license_book3={1,0};

        langues_test.add("inglese"); langues_test.add("spagnolo"); author_test.add("Gino"); author_test.add("Pino");
        Book book1= new Book(111,"title1", author_test,langues_test,200,1999,"Romanzo",license_book1 );
        Book book2= new Book(222,"title1", author_test,langues_test,100,2001,"Saggio", license_book2);
        Book book3= new Book(333,"title1", author_test,langues_test,200,2019,"Giallo",license_book3 );
        bookList.put(book1.getBarcode(), book1);
        bookList.put(book2.getBarcode(), book2);
        bookList.put(book3.getBarcode(), book3);

    }

}