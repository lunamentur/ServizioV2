package library;
import database.Database;
import view.View;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import operator.*;
import resource.*;


/**
 * Classe intermedia che permette l'interazione tra la classe User e la classe Database.
 * @author Reda Kassame, Simona Ramazzotti.
 * @version 2
 */
public class Library {
	
	public final static String MG_ERRORE="Hai inserito un valore non valido\n";
	private static boolean end;
	public static LocalDate birthDate;
	private static View view=new View();

	/**
	 * Creazione di variabili e oggetti utili per i metodi di controllo relativi all'User.
	 */
	private static String string, password,username,name, surname;
	private static int year, month, day, choise;
	private static long rangeYear=5;
	private static long rangeDay=-10;
	/**
	 * Definizione dell'istanza isAdmin che permette di distinguere l'Admin dall'utente finale. di conseguenza impostiamo che ritorni false.
	 */
	private static boolean isAdmin=false;

	/**
	 * Creazione di variabili e oggetti utili per i metodi di controllo relativi alle risorse.
	 */
	private static ArrayList<String> arrayList= new ArrayList<String>(); //lingue, autori
	private static Integer [] licenseList= {0,0}; //licenze con due componenti.
	private static int number,barcode;


	/**
	 * METODI CONTROLLO USER
	 */

	/**
	 * Metodo che assembla i metodi per la registrazione con i relativi controlli e salva il nuovo user,
	 * oggetto di tipo User, all'interno del Database.
	 */
    public static void registrationProcess(){
        User user = new User(insertString(view.NOME), insertString(view.COGNOME), insertUserName(), insertString(view.PASSWORD), insertDate(), LocalDate.now());
        if(Database.checkIf18(user.getBirthDate()) != false){
            Database.insertUser(user);
        }
    }

	/**
	 * Metodo che controlla se l'username e\' gia presente nel Database.
	 * Se si continua a ciclare finche\' non ne viene inserito uno nuovo (inesistente).
	 * @return username
	 */
	public static String insertUserName(){
		end=false;
		while(!end){
			username=insertString(view.USER_NAME);
			if(!Database.checkIfUser(username)){
				end=true;
			}
			else view.stampaRichiestaSingola(view.USERNAME_ESISTE);
		}
		return username;
	}

    
    /**
     * Medoto per inserire la data di nascita, di tipo LocalDate, dell'user.
     * Prende da tastiera anno, mese e giorno e crea una data di tipo LocalDate.
	 * @return birthDate
     */
    public static LocalDate insertDate(){
    	end= false;
		view.stampaRichiestaSingola(view.DATA_NASCITA);
    	while(!end){
			view.stampaRichiestaSingola(view.YEAR);
			year= readInt();
    		if(String.valueOf(year).length()==4)
    		{
				view.stampaRichiestaSingola(view.MONTH);
    			month= readInt();
				view.stampaRichiestaSingola(view.DAY);
    			day=readInt();
    			if(String.valueOf(month).length()==2 && String.valueOf(day).length()==2)
    			{
    				birthDate= LocalDate.of(year,month,day);
    				end=true;
    			}
    		}
    		else {
				System.out.println(MG_ERRORE);
			}
    	}
    	return birthDate;
    }

    /**
     * Metooo di controllo del Login da parte dell'User. L'autenticazione va a buon fine se l'username è presente all'interno
	 * del Database e se la password inserita corrisponde.
	 * Se fallisce continua a ciclare finchè non ci si autentica correttamente.
     */
	public static String checkLogin(){
		end = false;
		do
		{
			username=insertString(view.USER_NAME);
			password=insertString(view.PASSWORD);
			if(Database.checkLoginIfTrue(username,password)) {
				System.out.println(view.AUTENTICAZIONE_SUCCESSO);
				end = true;
			}
			else {
				System.out.println(MG_ERRORE+ "Riprova.");
				end=false;
			}
		}while(!end);
		return username;
	}

	/**
	 * Metodo che permette il rinnovo dell'iscrizione dell'user se scaduta (o in procinto di scadere).
	 * @param user
	 */
	public static void renewalRegistration(User user){
		if(isExpired(user)){
			view.stampaRichiestaSingola(view.MG_SCADUTA_ISCRIZIONE);
			view.stampaRichiestaSingola(view.RINNOVO);
			choise= readInt();
			if(choise==0){
				user.setRegistrationDate(LocalDate.now());
			}
		}
	}

    /**
     * Metodo che controlla che l'iscrizione dell'user sia scaduta o nel range impostato per il rinnovo anticipato.
     * @return true se l'iscrizione dell'user è scaduta, quindi può essere rinnovata.
     * @return false se l'iscrizione dell'user non è scaduta e non è nel range dei giorni di rinnovo.
     */
    public static boolean isExpired(User user){
        return user.getRegistrationDate().plusYears(rangeYear).isBefore(LocalDate.now()) && user.getRegistrationDate().plusDays(rangeDay).isBefore(LocalDate.now());
    }


	/**
	 * METODI CONTROLLO ADMIN
	 */

	/**
	 * Metodo che dato un oggetto di tipo user in ingresso ci informa se e\' un Admin oppure un semplice fruitore del servizio.
	 * @param user
	 * @return user.getIsAdmni() true or false.
	 */
	public static boolean checkAdminIfTrue(User user){
		return user.getIsAdmin();
	}

	/**
	 * Metodo che permette di gestire le azioni che possono essere svolte dall'Admin.
	 */
	public static void actionAdmin(){
		view.stampaMenuSpecifico(view.RICHIESTE_MENU_ADMIN);
		end= false;
		do{
			choise= readInt();
			switch(choise)
			{
				/**
				 * Visualizzazione dell'elenco delle risorse.
				 */
				case 1:
					Database.listBook();
					break;
				/**
				 * Aggiungere una risorsa all'elenco.
				 * Se gia\' presente incrementa il numero di licenze disponibili.
				 */
				case 2:
					createBookProcess();
					break;
				/**
				 * Rimozione di una risorsa dall'archivio.
				 */
				case 3:
					view.stampaRichiestaSingola(view.BARCODE);
					number= readInt();
					Database.removeBook(number);
					break;

				/**
				 * Visualizzazione dell'elenco delle risorse.
				 */
				case 4:
					Database.listUsers();
					break;
				/**
				 * Fine della stampa delle richieste del Menu.
				 */
				case 0:
					end = false;
					break;

				/**
				 * Errore, l'inserimento non è corretto
				 */
				default:
					System.out.println(MG_ERRORE);
					break;
			}
			view.stampaRichiestaSingola(view.MG_ANCORA + view.PREMI);
		}while(!end);
	}


	/**
	 * METODI CONTROLLO RESOURCE
	 */

	/**
	 * Metodo che assembla i metodi per la registrazione con i relativi controlli e salva il nuovo book,
	 * oggetto di tipo Book, all'interno del Database.
	 */
	//barcode,String title, ArrayList author, ArrayList langues, int numPage, int yearPub, String gener, Integer [] license
	public static void createBook(int barcode){
		Book book = new Book(barcode, insertString(View.TITOLO), insertArray(view.AUTORI), insertArray(view.LINGUE), insertNum(view.NUM_PAG), insertNumberEqual(view.YEAR,4), insertString(view.GENERE), licenseList);
		Database.insertBook(book);
		view.stampaRichiestaSingola(view.MG_AZIONE_SUCCESSO);
	}

	/**
	 * Metodo che verifica se la risorsa e\' gia\' presente, altrimenti continuo l'inserimento della nuova, richiamando un altro metodo.
	 * Se la risorsa esiste gia\' allora incrementa il numero di licenze.
	 */
	public static void createBookProcess(){
		barcode=insertBarcode(6);
		if(Database.checkIfBook(barcode)){
			/**
			 * Incremenenta il numero di copie disponibili se gia\' presente una risorssa uguale.
			 */
			Database.incrementLicense(barcode);
			view.stampaRichiestaSingola(view.MG_AZIONE_SUCCESSO);
		}
		else createBook(barcode);
	}

	/**
	 * Metodo che permette di inserire un numero che sia più lungo di un vincolo dato. Come il barcode che deve essere un numero con 6 o più cifre.
	 * @param vincolo
	 * @return
	 */
	public static int insertBarcode(int vincolo){
		end= false;
		view.stampaRichiestaSingola(view.BARCODE);
		while(!end){
			barcode= readInt();
			if(String.valueOf(barcode).length()>=vincolo)
			{
				end=true;
			}
			else {
				System.out.println(MG_ERRORE);
			}
		}
		return barcode;
	}

	/**
	 * Metodo che permette di inserire un numero, senza vincoli di lunghezza. Come il numero di pagine.
	 * @param tipoInserimento
	 * @return
	 */
	public static int insertNum(String tipoInserimento){
		view.stampaRichiestaSingola(tipoInserimento);
		number= readInt();
		return number;

	}

	/**
	 * Metodo che permette di inserire numeri e controllare se rispettano la lunghezza. Come nel caso dell'anno, deve avere 4 cifre.
	 * @param tipoInserimento
	 * @param vincolo
	 * @return
	 */
	public static int insertNumberEqual(String tipoInserimento, int vincolo){
		end= false;
		view.stampaRichiestaSingola(tipoInserimento);
		while(!end){
			number= readInt();
			if(String.valueOf(year).length()==vincolo)
			{
				end=true;
			}
			else {
				System.out.println(MG_ERRORE);
			}
		}
		return number;
	}



    /**
     * Metodo che permette di inserire le lingue e gli autori. Uno o più.
     * @param tipoInserimento stringa che permette di generalizzare il metodo di inserimento, stampandola a video.
     * @return arrayList ovvero la lista di stringhe.
     */
    public static ArrayList<String> insertArray(String tipoInserimento){
        boolean end= false;
        ArrayList<String> arrayList = new ArrayList<String>();
        view.stampaRichiestaSingola(tipoInserimento);
        //inserisce al primo giro almeno un autore o una lingua
        try {
            string= readStringNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayList.add(string);
        while(!end){
            view.stampaRichiestaSingola(tipoInserimento + "Altrimenti premi 0 per uscire.");
            try {
                string= readStringNotNull();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(string.equals("0")) end= true;
            else arrayList.add(string);
        }
        return arrayList;
    }

	/**
	 *
	 * @param tipoInserimento
	 * @return
	 */
	public static String insertString(String tipoInserimento) {
		view.stampaRichiestaSingola(tipoInserimento);
		try {
			string = readStringNotNull();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}


	/**
	 * METODI INSERIMENTO GENERICI
	 */

	/**
	 * Metodi di controllo dell'inserimento da tastiera di numeri di tipo interi.
	 * @return numeroInserito numero valido inserito da tastiera.
	 */
	public static int readInt() {
		Scanner readInt = new Scanner(System.in);
	  	int numInserito = 0;
	  	    if(readInt.hasNextInt())
	  		{
	  			end = true ;
	  			numInserito = readInt.nextInt();
	  		}
	  		else System.out.println(view.MG_ERRORE);
	  	return numInserito;
	}
	
	/**
     * Metodo di controllo lettura da tastiera di una stringa.
     * @return stringa tipo String che contiene la stringa inserita da tastiera dall'user.
     */
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	
	public static String readString() throws Exception
	{
		String stringa = read.readLine();
		return stringa;
	}
	
	/**
     * Metodo di controllo lettura da tastiera di una stringa, possibilmente non vuota. Richiama il metodo readString().
     * @return stringa tipo String che contiene la stringa inserita da tastiera dall'user.
     */
	public static String readStringNotNull() throws Exception
	{
	   end = false;
	   String stringa = null;
	   
	   do
	   {
		 stringa = readString();
		 if (stringa.length() > 0)
		  {
			 end = true;
		  }
		 else System.out.println(view.MG_ERRORE);
	   } 
	   while(!end);
	   return stringa;
	}


}
