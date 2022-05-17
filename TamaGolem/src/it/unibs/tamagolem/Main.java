package it.unibs.tamagolem;

import java.util.Arrays;

import it.unibs.mylib.InputDati;

public class Main {
	
	private static final String BENVENUTO = "L'equilibrio del mondo è stato creato! :D";
	private static final String NOME1 = "Giocatore 1, inserisci il tuo nome: ";
	private static final String NOME2 = "Giocatore 2, inserisci il tuo nome: ";
	private static final String NUOVA_PARTITA_O_ESCI = "> Inserisci 1 per iniziare una nuova partita \n> Inserisci 0 per uscire";
	
	private static int N = 7;
	private static int P = 4;

	public static void main(String[] args) throws InterruptedException {
		int scelta = 1;
		do {
		//FASE 1 : setup Equilibrio mondo
			//Equilibrio.main();
			Equilibrio equilibrio = new Equilibrio(N, P);
			System.out.println(BENVENUTO);
		//FASE 2 : SCONTRO
		//giocatore A: 
			System.out.println(NOME1);
			Giocatore g1 = new Giocatore(InputDati.leggiStringaNonVuota(" > "));	
		//giocatore B:
			System.out.println(NOME2);
			Giocatore g2 = new Giocatore(InputDati.leggiStringaNonVuota(" > "));
		//TURNI : + dichiarazione vincitore
			Scontro sc = new Scontro(equilibrio);
			sc.battaglia(g1, g2);	
		//FASE 3 : Equilibrio
			System.out.println("-------------------------------------------------");
			System.out.println("Sveliamo ora l'Equilibrio del mondo : ");
			System.out.println(equilibrio.toString());
			System.out.println("-------------------------------------------------");
		// nuova partita o esci ?
			scelta = InputDati.leggiIntero(NUOVA_PARTITA_O_ESCI);
				
		}while (scelta == 1);
		
	}
	
}
