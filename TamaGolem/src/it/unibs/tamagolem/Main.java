package it.unibs.tamagolem;

import it.unibs.mylib.EstrazioniCasuali;
import it.unibs.mylib.InputDati;

public class Main {
	
	private static final String SELEZIONARE_LIVELLO_DI_DIFFICOLTA = "Selezionare il livello di difficoltà della partita :\n > 1 facile\n > 2 intermedio\n > 3 difficile\n > 4 random\n >";
	private static final String BENVENUTO = "L'equilibrio del mondo è stato creato! :D";
	private static final String NOME1 = "Giocatore 1, inserisci il tuo nome: \n >";
	private static final String NOME2 = "Giocatore 2, inserisci il tuo nome: \n >";
	private static final String NUOVA_PARTITA_O_ESCI = "Nuova partita?";
	private static final String SVELIAMO_ORA_L_EQUILIBRIO_DEL_MONDO = "Sveliamo ora l'Equilibrio del mondo : ";
	
	private static int N ; //numero di elementi
	private static int P ; //numero di pietre ingurgitabili
	private static int G ; //numero di TamaGolem evocabili
	private static int S ; //numero di pietre nella scorta comune (per pietra)
	private static int V ;

	public static void main(String[] args) throws InterruptedException {
		do {
			//SELELEZIONE E CALCOLO VARIABILI
			setupDifficolta();
			
			//FASE 1 : setup Equilibrio mondo
			Equilibrio equilibrio = new Equilibrio(N, S);
			V = equilibrio.getSup();
			System.out.println(BENVENUTO);
			
			//FASE 2 : SCONTRO
			//giocatore A: 
			Giocatore g1 = new Giocatore(InputDati.leggiStringaNonVuota(NOME1), G);	
			//giocatore B:
			Giocatore g2 = new Giocatore(InputDati.leggiStringaNonVuota(NOME2), G);
			
			//TURNI : + dichiarazione vincitore
			Scontro sc = new Scontro(equilibrio, P, V);
			sc.battaglia(g1, g2);	
			
			//FASE 3 : Rivelazione equilibrio
			System.out.println("-------------------------------------------------");
			System.out.println(SVELIAMO_ORA_L_EQUILIBRIO_DEL_MONDO);
			System.out.println(equilibrio.toString());
			System.out.println("-------------------------------------------------");
			
			// nuova partita o esci ?		
		} while (InputDati.yesOrNo(NUOVA_PARTITA_O_ESCI));
		
	}

	private static void setupDifficolta() {
		switch(InputDati.leggiIntero(SELEZIONARE_LIVELLO_DI_DIFFICOLTA, 1, 4)) {
		case 1 :
			N = 5;	//3,4,5
			break;
		case 2 :
			N = 7;	//6,7,8
			break;
		case 3 :
			N = 10;	//9,10
			break;
		case 4 :
			N = EstrazioniCasuali.estraiIntero(3, 10);
			System.out.println("scelta partita con %d elementi".formatted(N));
			break;
		}
		
		P = (int) Math.ceil((N + 1) / 3.0) + 1;
		G = (int) Math.ceil((N - 1)*(N - 2) / (2.0 * P));
		S = (int) Math.ceil((2.0*G*P) / N);
		
	}
	
}
