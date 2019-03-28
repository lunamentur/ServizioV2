import database.Database;
import library.Library;
import view.View;
import java.time.LocalDate;
import operator.*;

/**
 * Classe main del programma servizio temporaneo di prestiti.
 * @author Reda Kassame, Simona Ramazzotti.
 * @version 2
 */
public class Main {
    public static void main(String[] args) {

         String name, surname, username, password;
         LocalDate birthDate, registrationDate;
         User user;

		/**
		 * credo degli oggetti preimpostati e li carico nel programma per i test.
		 */
		Database.initAllObject();

		int choise;
		boolean end;
         /**
          * Scelta da parte dell'utente di iscriversi oppure di autenticarsi.
		  * Se si autentica come Admin gli vengono elencate alcune azioni da poter svolgere.
          */
         end = false;
     	do
     	{
			View.stampaRichiestaSingola(View.MG_INIZIALE);
			View.stampaMenuSpecifico(View.RICHIESTE_MENU_INIZIALE);
			choise=Library.readInt();
     		switch(choise){
         		
     		/**
     		 * Iscrizione
     		 */
     			case 1:
     				
     				/**
     				 * L'utente inserisce il nome, il cognome, la password e la data di nascita.
					 * L'username viene ricercato all'interno del database e, se vi sono doppioni, viene richiesto l'inserimento.
					 * Sulla data di nascita viene fatto un controllo: se l'utente è maggiorenne allora può essere inserito nel database.
     				 */

					Library.registrationProcess();
					System.out.println(View.GRAZIE_ISCRIZIONE);
					View.stampaRichiestaSingola(View.MG_ANCORA + View.PREMI);
					break;
         			
         	/**
         	 * Autenticazione (Login) e Rinnovo.
         	 */
         		case 2:
         			/**
         			 * L'utente inserisce il proprio nickname e viene cercato all'interno del database.
					 * Se la password è la stessa allora viene autenticato con successo, altrimenti
         			 * continua a ciclare oppure si termina il programma.
                     */
					username=Library.checkLogin();

					/**
					 * Una volta che il Login è andato a buon fine controlliamo che l'iscrizione dell'user sia ancora valida.
					 * Se non lo è, ovvero sono decaduti i privilegi, può avvenire il rinnovo dell'iscrizione.
					 */
					Library.renewalRegistration(Database.getUser(username));

					/**
					 * Verifichiamo se e\' un Admin.
					 */
					if(Library.checkAdminIfTrue(Database.getUser(username))) {
						/**
						 * Se l'utente accede al servizio coi privilegi di Admin allora puo\' svolgere determinate azioni.
						 * Azioni sono: visualizzare elenco utenti, visualizzare l'elenco risorse, aggiungere nuova risorsa all'elenco e rimuovere risorsa dall'elenco.
						 */
						Library.actionAdmin();
					}
					View.stampaRichiestaSingola(View.MG_ANCORA + View.PREMI);
         			break;

				/**
				 * Metodo nascosto creazione Admin.
 				 */
				case 666:
					//Library.actionAdmin();
					View.stampaRichiestaSingola(View.MG_ANCORA + View.PREMI);
					break;
         	
         		default:
         			View.stampaRichiestaSingola(View.MG_ERRORE);
         			break;

         		case 0:
         		    end=true;
					System.out.println(View.FINE_MENU);
					break;
     		}
		}while(!end);
     }


}

