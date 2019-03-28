package operator;
import database.Database;
import library.Library;
import java.time.LocalDate;

/**
 * Classe User, ovvero la classe associata all'utente/al fruitore dei servizi di prestito temporaneo. 
 * @author Reda Kassame, Simona Ramazzotti.
 * @version 2
 */
public class User extends Admin {
    private String name, surname, password, username;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    /**
     * @param isAdmin isAdmin permette di distinguere l'Admin dall'utente finale. di conseguenza impostiamo che ritorni false.
     */
    private boolean isAdmin;

    /**
     * Costruttore della Classe User, che crea un oggetto di tipo Utente con particolari privilegi e
     *  metodi associati utili nella navigazione e utilizzo del servizio.
     * 
     * @param name Nome dell'user.
     * @param surname Cognome dell'user.
     * @param username Nickname dell'user.
     * @param birthDate Data di nascita dell'user, di tipo LocalDate. E\' richiesto che sia maggiorenne per poter diventare utente dei servizi.
     * @param registrationDate Data di iscrizione ai servizi di prestito temporaneo.
     * @param password Password univoca associata all'user per poter autenticare la propria identita\' .
     */
    public User(String name, String surname, String username, String password, LocalDate birthDate, LocalDate registrationDate){
        super(username,password);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.username = username;
        this.password = password;
        this.isAdmin=false;
    }
    
    /**
     *  Metodo di tipo boolean che permette di verificare se l'utente e\' maggiorenne: true se maggiorenne, false altrimenti.
     * @param user oggetto di tipo user, che rappresenta il fruitore del serizio
     */
    public boolean isUserAdult(User user) {
        return true;
    }
    
    /**
     * Metodi di Set e Get della classe User.
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo per fare il get della data di nascita dell'oggetto di tipo User.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Metodo per fare il set della data di nascita dell'oggetto di tipo User.
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Metodo che permette di poter modificare la data dell'iscrizione del fruitore.
     * @param registrationDate oggetto di tipo LocalDate, che identifica la data di iscrizione dell'utente (o fruitore) ai servizi di prestito temporaneo..
     */
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
