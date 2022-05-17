package it.unibs.tamagolem;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class Scontro {
	
	private ArrayList<Pietra> pietre = new ArrayList<>();
	private ArrayList<Pietra> pietrePerTamaGolem = new ArrayList<>();
	Equilibrio parametri;
	private int interazioneNulla=0;
	
	/**
	 * Costruttore di scontro, recupera le pietre disponibili impostate dalla classe Equilibrio
	 * @param equilibrio
	 */
	public Scontro(Equilibrio equilibrio) {
        this.parametri = equilibrio;
        for (Pietra pietra : equilibrio) {
            pietre.add(pietra);
        }
    }
	
	
	
	
	
	/**
	 * Metodo per l'evocazione di un tamaGolem
	 * 
	 * @param g un oggetto di tipo giocatore
	 * @return TamaGolem evocato
	 */
	private TamaGolem evocazioneTama(Giocatore g) {
				
		System.out.println(g.getNome() + " inserisci il nome del TamaGolem:");
		String nomeTama = InputDati.leggiStringa(" > ");
		TamaGolem tamaG = new TamaGolem(nomeTama);
		System.out.println("� il momento di combattere, scegli le pietre con cui combatter� il tuo"
				+ "Tamagolem ");
		sceltaPietre();
		tamaG.impostaPietre(pietrePerTamaGolem);
		g.aggiornaNumTama();
		g.aggiungiGolem(tamaG);
		
		return tamaG;
	}
	
	
	/**
	 * metodo che permette ad un giocatore di scegliere delle pietre da usare su un TamaGolem 
	 */
	public void sceltaPietre() {
		pietrePerTamaGolem.clear();
		boolean esci = true;
		int scelta=0;
		
		do {
			esci =true;
			int i=0;
			stampaPietreDisponibili();
			String nomePietra = InputDati.leggiStringa("inserire il nome per selezionare la pietra associata\n");
			while (i < pietre.size() && esci) {
				if (nomePietra.equals(pietre.get(i).getNome())) {
					System.out.println("Pietra impostata con successo");
					System.out.println();
					pietrePerTamaGolem.add(pietre.get(i));
					pietre.get(i).diminuisciPresenza();
					int quante =pietre.get(i).getPresenza();
					if ( quante == 0) {
						pietre.remove(i);
					}
					esci = false;
					scelta++;
					if (scelta < 3)
						System.out.println("scegliere un'altra pietra");
				}
				i++;
			} 
			if (esci) {
				System.out.println("Il nome inserito non � valido o non corrisponde, riprovare");
			}
		} while (scelta != 3);		
	}
	
	
	/**
	 * metodo che stampa le pietre disonibili, di supporto alla scelta pietre
	 */
	private void stampaPietreDisponibili() {

		for (int i = 0; i < pietre.size(); i++) {
			String nome = pietre.get(i).getNome();
			int quante =pietre.get(i).getPresenza();
			System.out.println("pietre del tipo >" + nome+ "<, quantit�:" + quante);	
		}
	}
	
//---------------------------------------------------verifca del vincitore----------------------------------------------------------
	
	/**
	 * Metodo che verifica se un giocatore ha ancora a disposizione TamaGolem da evocare e se c'� ancora un TamaGolem vivo, 
	 * in caso contrario il giocatore ha perso, e viene proclamato vincitore l'avversario
	 * 
	 * @param g1 giocatore1
	 * @param g2 giocatore2
	 * @return false in caso un giocatore abbia perso
	 * @throws InterruptedException
	 */
	public boolean verificaVincitore(Giocatore g1, Giocatore g2) throws InterruptedException {
		
		if(g1.getNumTamaGolemEvocabili() <= 0 && !g1.getTamaVivi()) {
			proclamaVincitore(g2,g1);
			return false;
		}
		else if (g2.getNumTamaGolemEvocabili() <= 0  && !g2.getTamaVivi()) {
			proclamaVincitore(g1,g2);
			return false;
		}
		
		return true;
		
	}
	
	
	/**
	 * Metodo che proclama il vincitore
	 * 
	 * @param vincitore giocatore che ha vinto
	 * @param sconfitto giocatore che ha perso
	 * @throws InterruptedException
	 */
	private void proclamaVincitore(Giocatore vincitore, Giocatore sconfitto) throws InterruptedException {
		String presentazionetama[] = {"il temibile ", "il feroce ", "il distruttore ", "il cannibale ", "il gigante "};
		Thread.sleep(1800);
		System.out.println("la battaglia � giunta al termine, complimenti ad entrambi");
		Thread.sleep(1800);
		System.out.println("ma gloria eterna a " + vincitore.getNome());
		Thread.sleep(1800);
		System.out.println("che ha saputo sconfiggere tutti i mostri avversari, che ricordiamo essere:");
		for (int i = 0; i < sconfitto.tamaGolemEvocabili.size(); i++) {
			System.out.println(presentazionetama[i] + sconfitto.tamaGolemEvocabili.get(i).getNome());
			Thread.sleep(1500);
		}
		System.out.println();
		System.out.println(vincitore.getNome() + ", tu verrai ricordato come");
		for (int i = 0; i < 3; i++) {
			System.out.printf(".");
			Thread.sleep(1000);
		}
		Thread.sleep(1800);
		System.out.println("Il Guardiano dell'Equilibrio");
		System.out.println();
		System.out.println();
		
	}

//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Metodo che fa combattere due giocatori fino a che uno dei due non rimanga senza TamaGolem
	 * 
	 * @param g1 giocatore1
	 * @param g2 giocatore2
	 * @throws InterruptedException
	 */
	public void battaglia(Giocatore g1, Giocatore g2) throws InterruptedException {
		
		TamaGolem tama1 = evocazioneTama(g1);
		TamaGolem tama2 = evocazioneTama(g2);
		
		System.out.println("che cominci la battaglia");
		
		do {
			if (battagliaTama(tama1, tama2) == 1) {				
				
				if (g1.getNumTamaGolemEvocabili() > 0) {
					System.out.println("Giocatore " + g1.getNome() + " il tuo TamaGolem � morto in battaglia con onore, bisogna sostituirlo");
					tama1 = evocazioneTama(g1);
				}
				else
					System.out.println("Giocatore " + g1.getNome() + " il tuo TamaGolem � morto in battaglia con onore, ma...");
				
			}
			else {
				
				if (g2.getNumTamaGolemEvocabili() > 0) {
					System.out.println("Giocatore " + g2.getNome() + " il tuo TamaGolem � morto in battaglia con onore, bisogna sostituirlo");
					tama2 = evocazioneTama(g2);
				}
				else
					System.out.println("Giocatore " + g2.getNome() + " il tuo TamaGolem � morto in battaglia con onore, ma...");
				
			}
				
		} while (verificaVincitore(g1, g2));
	}

	/**
	 * Metodo che fa combattere due TamaGolem tra loro, finch� uno dei due rimane senza vita
	 * 
	 * @param tama1
	 * @param tama2
	 * @return
	 * @throws InterruptedException
	 */
	private int battagliaTama(TamaGolem tama1, TamaGolem tama2) throws InterruptedException{
		int vita1;
		int vita2;
		int potenza = 17;
		stampaSituazione(tama1, tama2, potenza);
		Thread.sleep(2000);
		
		do {
			
			Pietra a = tama1.scagliaPietre();
			Pietra b = tama2.scagliaPietre();
			potenza = parametri.confrontaElementi(a, b);
			if (potenza == 0)
				interazioneNulla++;
			else if (potenza > 0) {
				tama2.perdiVita(potenza);
				interazioneNulla = 0;
			}
			else {
				tama1.perdiVita(potenza);
				interazioneNulla = 0;
			}
			
			vita1=tama1.getPuntiVita();
			vita2=tama2.getPuntiVita();
			if (vita1 > 0 && vita2 > 0) {
				stampaSituazione(tama1, tama2, potenza);
				Thread.sleep(2000);
			}
		} while (vita1 > 0 && vita2 > 0);
		
		if (vita1 <= 0)
			return 1;
		else
			return 2;
	}



	
	/**
	 * Metodo che mostra la situazione dello scontro fra due TamaGolem ad ogni turno, vengono stampati i pv, e l'ineterazione
	 * fra le pietre
	 * 
	 * @param tama1 TamaGolem del giocatore1
	 * @param tama2 TamaGolem del giocatore2
	 * @param potenza interazione fra le pietre
	 */
	private void stampaSituazione(TamaGolem tama1, TamaGolem tama2, int potenza) {
		tama1.stampaTamaGolem();
		if (potenza == 17) {
			System.out.println();
			System.out.println("  >situazione iniziale<");
			System.out.println();
		}
		else if (potenza == 0) {//gli elementi sono uguali
			System.out.println("   ||        /\\");
			System.out.println("  \\||/      /||\\" + "  interazione nulla" );
			System.out.println("   \\/        ||");
		}
		else if (potenza > 0) {//tama2 vince su tama1
			System.out.println("   ||    ");
			System.out.println("  \\||/   interazione " + Math.abs(potenza));
			System.out.println("   \\/    ");
		}
		else if (potenza < 0) {//tama1 vince su tama2
			System.out.println("              /\\");
			System.out.println("             /||\\" + "  interazione " + Math.abs(potenza));
			System.out.println("              ||");
		}

		tama2.stampaTamaGolem();
		for (int i = 0; i < 35; i++) {
			System.out.print((char) 165);
		}System.out.println();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}