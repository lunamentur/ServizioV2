import database.Database;
import library.Library;
import operator.Admin;
import view.View;

import java.time.LocalDate;

import static library.Library.readStringNotNull;
import static library.Library.registrationProcess;
import operator.*;
import resource.*;
import database.*;

public class Main {
    public static void main(String[] args) {

    	 View view=new View();
         String name, surname, username, password;
         LocalDate birthDate, registrationDate;
         User user;

		int choise;
		boolean end;
         /**
          * Scelta da parte dell'utente di iscriversi oppure di autenticarsi.
		  * Se si autentica come Admin gli vengono elencate alcune azioni da poter svolgere.
          */
         view.stampaRichiestaSingola(View.MG_INIZIALE);
		 view.stampaMenuSpecifico(View.RICHIESTE_MENU_INIZIALE);
		 end = false;
     	do
     	{
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
						 * Essendo Admin mostro il menu\' delle azioni che possono essere svolte.
						 */
						choise=3;
					}



         			break;

				/**
				 * Azioni Admin.
 				 */
				case 3:
					/**
					 * Se l'utente accede al servizio coi privilegi di Admin allora puo\' svolgere determinate azioni.
					 * Azioni sono: visualizzare l'elenco risorse, aggiungere nuova risorsa all'elenco e rimuovere risorsa dall'elenco.
					 */
					Library.actionAdmin();

					break;
         	
         		default:
         			view.stampaRichiestaSingola(View.MG_ERRORE);
         			break;

         		case 0:
         		    end=true;
					System.out.println(View.FINE_MENU);
					break;
     		}
     		view.stampaRichiestaSingola(View.MG_ANCORA + View.PREMI);

		}while(!end);
     }


}

