package it.unibs.tamagolem;

import it.unibs.mylib.InputDati;

public class Main {
	
	private static final String NUOVA_PARTITA_O_ESCI = "> Premi 1 per iniziare una nuova partita \n> Premi 0 per uscire";
	private static final String NOME = "Inserisci il tuo nome: ";
	private static final String BENVENUTO = "L'equilibrio del mondo � stato creato! :D";

	public static void main(String[] args) {
		int scelta = 1;
		do {
		//FASE 1 : setup Equilibrio mondo
			//Equilibrio.main();
			// N elementi (3<= N < 10)
			System.out.println(BENVENUTO);
		//FASE 2 : SCONTRO
		//giocatore A: nome, evocazione TamaGolem
			System.out.println(NOME);
			Giocatore g1 = new Giocatore(InputDati.leggiStringaNonVuota(" > "));
		//giocatore B: nome, evocazione TamaGolem
			System.out.println(NOME);
			Giocatore g2 = new Giocatore(InputDati.leggiStringaNonVuota(" > "));
		//inizio TURNI :
			//evocazione--> selezione, inizializzazione vita,(sottofase) selezione pietre degli elementi
			//do{
			//scagliano pietra
			//interazione elementi + calcolo HP
			//rivelazione dmg
			//}while HP > 0
			//	}while( g1.getNumTamaGolemEvocabili() != 0 && g2.getNumTamaGolemEvocabili() != 0);     
			
		//FASE 3 : dichiarazione vincitore + Equilibrio
				
		// nuova partita o esci ?
				scelta = InputDati.leggiIntero(NUOVA_PARTITA_O_ESCI);
				
		}while (scelta == 1);
		
	}
	
}
