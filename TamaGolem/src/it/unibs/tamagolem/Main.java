package it.unibs.tamagolem;

import it.unibs.mylib.InputDati;

public class Main {
	
	private static final String NUOVA_PARTITA_O_ESCI = "> Premi 1 per iniziare una nuova partita \n> Premi 0 per uscire";
	private static final String NOME1 = "Giocatore 1, inserisci il tuo nome: ";
	private static final String NOME2 = "Giocatore 2, inserisci il tuo nome: ";
	private static final String BENVENUTO = "L'equilibrio del mondo è stato creato! :D";

	public static void main(String[] args) throws InterruptedException {
		int scelta = 1;
		do {
		//FASE 1 : setup Equilibrio mondo
			//Equilibrio.main();
			// N elementi (3<= N < 10)
			System.out.println(BENVENUTO);
		//FASE 2 : SCONTRO
		//giocatore A: nome, evocazione TamaGolem
			System.out.println(NOME1);
			Giocatore g1 = new Giocatore(InputDati.leggiStringaNonVuota(" > "));
		//giocatore B: nome, evocazione TamaGolem
			System.out.println(NOME2);
			Giocatore g2 = new Giocatore(InputDati.leggiStringaNonVuota(" > "));
		//inizio TURNI :
			//evocazione--> selezione, inizializzazione vita,(sottofase) selezione pietre degli elementi
			//do{
			//scagliano pietra
			//interazione elementi + calcolo HP
			//rivelazione dmg
			//}while HP > 0
			//	}while( g1.getNumTamaGolemEvocabili() != 0 && g2.getNumTamaGolemEvocabili() != 0); 
			Scontro sc = new Scontro();
			sc.battaglia(g1, g2);
			
		//FASE 3 : dichiarazione vincitore + Equilibrio
				
		// nuova partita o esci ?
				scelta = InputDati.leggiIntero(NUOVA_PARTITA_O_ESCI);
				
		}while (scelta == 1);
		
	}
	
}
