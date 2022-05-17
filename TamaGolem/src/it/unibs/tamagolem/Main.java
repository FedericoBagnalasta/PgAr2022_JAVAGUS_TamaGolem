package it.unibs.tamagolem;

import it.unibs.mylib.InputDati;

public class Main {
	
	private static final String SELEZIONARE_LIVELLO_DI_DIFFICOLTÀ = "Selezionare il livello di difficoltà della partita :\n > 0 facile\n > 1 intermedio\n > 2 difficile\n > 3 random";
	private static final String BENVENUTO = "L'equilibrio del mondo è stato creato! :D";
	private static final String NOME1 = "Giocatore 1, inserisci il tuo nome: \n >";
	private static final String NOME2 = "Giocatore 2, inserisci il tuo nome: \n >";
	private static final String NUOVA_PARTITA_O_ESCI = "> Nuova partita?";
	private static final String SVELIAMO_ORA_L_EQUILIBRIO_DEL_MONDO = "Sveliamo ora l'Equilibrio del mondo : ";
	
	private static int N ; //numero di elementi
	private static int P ; //numero di pietre
	private static int G ; //numero di TamaGolem 
	private static int S ; //numero di pietre nella scorta comune

	public static void main(String[] args) throws InterruptedException {
		do {
		//FASE 1 : setup Equilibrio mondo
			//SELELEZIONE E CALCOLO VARIABILI
			setupDifficolta();
			Equilibrio equilibrio = new Equilibrio(N, P);
			
			System.out.println(BENVENUTO);
		//FASE 2 : SCONTRO
		//giocatore A: 
			Giocatore g1 = new Giocatore(InputDati.leggiStringaNonVuota(NOME1));	
		//giocatore B:
			Giocatore g2 = new Giocatore(InputDati.leggiStringaNonVuota(NOME2));
		//TURNI : + dichiarazione vincitore
			Scontro sc = new Scontro(equilibrio);
			sc.battaglia(g1, g2);	
		//FASE 3 : Equilibrio
			System.out.println("-------------------------------------------------");
			System.out.println(SVELIAMO_ORA_L_EQUILIBRIO_DEL_MONDO);
			System.out.println(equilibrio.toString());
			System.out.println("-------------------------------------------------");
		// nuova partita o esci ?		
		}while (InputDati.yesOrNo(NUOVA_PARTITA_O_ESCI));
		
	}

	private static void setupDifficolta() {
		switch(InputDati.leggiIntero(SELEZIONARE_LIVELLO_DI_DIFFICOLTÀ, 0, 3)) {
			case 0 : {
				N = 4;	//3,4,5
			} break;
			case 1 : {
				N = 7;	//6,7,8
			} break;
			case 2 : {
				N = 10;	//9,10
			} break;
			case 3 : {
				N = (int) Math.floor(Math.random()*(10-3+1)+3);
			} break;
		}
		P = (int) Math.ceil((N + 1) / 3) + 1;
		G = (int) Math.ceil((N - 1)*(N - 2) / (2 * P));
		S = (int) Math.ceil((2*G*P) / N) * N;
	}
	
}
