package view;
/**
 * Classe View col compito di generare il menu' iniziale con cui si interfaccera' l'utente finale e
 * altri metodi del tipo .toString() al fine di visualizzare a video messaggi di errore, domande, simboli e altro.
 * @author Reda Kassame, Simona Ramazzotti.
 * @version 2
 */
public class View {

    public final static String FINE_MENU ="Arrivederci, grazie per aver usato i Servizi di prestito temporaneo";
    public final static String CORNICETTA ="**************************************";
    public final static String MG_ERRORE="Riprova, hai inserito un valore non valido\n";
    public final static String MG_ANCORA ="Cosa desideri fare?";
    public final static String MG_SCADUTA_ISCRIZIONE ="La sua iscrizione è scaduta poiche\' ha superato i termini prescritti.";
    public final static String MG_INIZIALE="Benvenuto nel Pannello dei Servizi di prestiti temporanei";
    public final static String AUTENTICAZIONE_SUCCESSO="Autenticazione avvenuta con successo, bentornato!";
    public final static String MG_AZIONE_SUCCESSO="L'azione appena completata e\' avvenuta con successo, grazie!";
    public final static String PREMI=" Premi 0 per uscire.";
    public final static String GRAZIE_ISCRIZIONE="Grazie per esserti Iscritto ai Servizi di Prestito Temporaneo.";

    /**
     * Creazione di una stringa COSTANTE
     * @param RICHIESTE_MENU rappresenta le voci del menu che si vuole creare nel momento iniziale di accesso da parte dell'utente: ovvero se iscriversi come nuovo
     * utente oppure, se gia\' in possesso di nome utente e password
     */
    public final static String [] RICHIESTE_MENU_INIZIALE ={" ISCRIVITI ai servizi di prestito temporaneo se sei un nuovo utente;",
            " AUTENTICAZIONE se sei gia\' un utente registrato ai servizi di prestito temporaneo;"};

    /**
     * @param RINNOVO rappresenta la richiesta di rinnovo dell'iscrizione ai servizi di prestito temporaneo da sottoporre all'utente.
     */
    public final static String RINNOVO ="Desideri RINNOVARE l'iscrizione ai servizi? premi 0";
    public final static String NOME="Inserisci NOME:";
    public final static String COGNOME="Inserisci COGNOME:";
    public final static String USER_NAME="Inserisci USERNAME:";
    public final static String DATA_NASCITA="Inserisci DATA DI NASCITA:";
    public final static String PASSWORD ="Inserisci PASSWORD:";
    public final static String MONTH ="Inserisci MESE:";
    public final static String DAY ="Inserisci GIORNO:";
    public final static String YEAR ="Inserisci ANNO:";
    public final static String USERNAME_ESISTE="Riprova, hai inserito un USERNAME esistente, scegline uno diverso.\n";
    public final  static  String SCADUTA_NON_RINNOVABILE="Devi reistrarti nuovamente, la tua iscrizione e\' scaduta e non puo\' essere rinnovata.";


    public final static String MINORENNE=" Non puoi iscriverti servizio poiche\' sei minorenne.";


    /**
     * Stringhe COSTANTI associate all'Admin e Risorse.
     */
    public final static String [] RICHIESTE_MENU_ADMIN ={" VISUALIZZARE l'elenco delle risorse.",
            " AGGIUNGERE una risorsa dall'elenco. ",
            " RIMUOVERE una risorsa dall'elenco. " ,
            " VISUALIZZARE l'elenco degli attuali utenti"};
    public final static String BARCODE =" Inserisci BARCODE: ";
    public final static String GENERE =" Inserisci GENERE: ";
    public final static String AUTORI =" Inserisci AUTORE/I:  ";
    public final static String NUM_PAG =" Inserisci NUMERO DI PAGINE: ";
    public final static String LINGUE =" Inserisci LINGUA/E: ";
    public final static String NON_ESISTE =" La risorsa NON e\' presente dell'elenco risorse. ";
    public final static String TITOLO =" Inserisci TITOLO:";


    /**
     * Metodo che permette di stampare il Menu'
     */
    public static void stampaMenuSpecifico(String[] richiesteMenu)
    {
        System.out.println(CORNICETTA);
        for (int k=0; k < richiesteMenu.length; k++)
        {
            System.out.println((k+1) + " - " + richiesteMenu[k]);
        }
        System.out.println(CORNICETTA);
    }

    /**
     * Metodo che permette di stampare una singola richiesta con cornicette annesse.
     */
    public static void stampaRichiestaSingola(String richiestaMenu) {
        System.out.println(CORNICETTA);
        System.out.println(richiestaMenu);
        System.out.println(CORNICETTA);
    }
}
