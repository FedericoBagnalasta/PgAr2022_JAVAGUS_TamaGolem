package it.unibs.tamagolem;

import java.util.ArrayList;

import it.unibs.mylib.InputDati;

public class Scontro {
	
	private ArrayList<Pietra> pietre = new ArrayList<>();
	private Equilibrio parametri;
	private final int P,V;
	
	/**
	 * Costruttore di scontro, recupera le pietre disponibili impostate dalla classe Equilibrio
	 * 
	 * @param equilibrio
	 */
	public Scontro(Equilibrio equilibrio, int P, int V) {
        this.parametri = equilibrio;
        for (Pietra pietra : equilibrio) {
            pietre.add(pietra);
        }
        this.P = P;
        this.V = V;
    }
	
	
	/**
	 * Metodo che fa combattere due giocatori fino a che uno dei due non rimanga senza TamaGolem
	 * 
	 * @param g1 giocatore1
	 * @param g2 giocatore2
	 * @throws InterruptedException
	 */
	public void battaglia(Giocatore g1, Giocatore g2) throws InterruptedException {
		
		evocazioneTama(g1);
		evocazioneTama(g2, g1);
		
		System.out.println("che cominci la battaglia");
		System.out.println("in caso si voglia velocizzare la battaglia, si digiti \"#\"");
		Giocatore perdente;
		
		while (true) {
			perdente = battagliaTama(g1, g2);				
			System.out.print("Giocatore %s il tuo TamaGolem è morto in battaglia con onore, ".formatted(perdente.getNome()));
				
			if (perdente.canEvocareTamagolem()) {
				System.out.println("bisogna sostituirlo");
				System.out.println("Puoi evocare ancora %d TamaGolem".formatted(perdente.getNumTamaGolemEvocabili()));
				evocazioneTama(perdente, perdente==g1 ? g2 : g1);
			}
			else {
				System.out.println("ma...");
				break;
			}
			
		};
		
		
		proclamaVincitore(perdente==g1 ? g2 : g1, perdente);
		
	}
	
	
	
	/**
	 * Metodo per l'evocazione di un tamaGolem
	 * 
	 * @param g il giocatore proprietario del tamagolem
	 * @param avversario l'altro giocatore del quale non deve copiare le pietre
	 */
	private void evocazioneTama(Giocatore g, Giocatore avversario) {
		String nomeTama = InputDati.leggiStringaNonVuota("%s inserisci il nome del TamaGolem:\n > ".formatted(g.getNome()));
		TamaGolem golem = new TamaGolem(nomeTama, V);
		System.out.println("è il momento di combattere, scegli le pietre con cui combatterà il tuo Tamagolem ");
		
		ArrayList<Pietra> pietreScelte = new ArrayList<>();
		ArrayList<Pietra> reset = new ArrayList<>(pietre);
		boolean copione;
		do {
			sceltaPietre(pietreScelte);
			copione = avversario!=null && avversario.getTamagolem().arePietreCopyOf(pietreScelte);
			if (copione) {
				System.out.println("copione!!!");
				pietre.clear();
				pietre.addAll(reset);
				pietre.forEach(p -> p.incrementaPresenza());
				pietreScelte.clear();
				System.out.println("riscegli le tue pietre "+g.getNome());
			}
		} while (copione);
		
		golem.impostaPietre(pietreScelte);
		g.aggiungiGolem(golem);
		System.out.println();
		
	}
	private void evocazioneTama(Giocatore g) {
		evocazioneTama(g, null);
	}
	
	
	/**
	 * metodo che permette ad un giocatore di scegliere delle pietre da usare su un TamaGolem 
	 */
	private void sceltaPietre(ArrayList<Pietra> pietrePerTamaGolem) {
		while (true) {
			stampaPietreDisponibili();
			System.out.println("[%d/%d] pietre selezionate".formatted(pietrePerTamaGolem.size(), P));
			String[] nomePietre = InputDati.leggiStringaNonVuota("inserire i nomi o i simboli per selezionare le pietre associate\n")
					.split(" ");
			
			for (String nomePietra : nomePietre) {
				Pietra p = findPietraByName(nomePietra);
				
				if (p==null) { // pietra non trovata
					System.out.println("il nome \"%s\" non è valido".formatted(nomePietra));
					break;
				}
				
				if (p.isScortaFinita()) { // pietra trovata ma scorta finita
					System.out.println("non ci sono più pietre di tipo %s selezionabili".formatted(p.toString()));
					break;
				}
				
				// pietra trovata con scorta
				p.diminuisciPresenza();
				pietrePerTamaGolem.add(p);
				if (pietrePerTamaGolem.size()==P) break; // non voglio più di P pietre
				
			}
			
			pietre.removeIf(Pietra::isScortaFinita);
			if (pietrePerTamaGolem.size()==P) return;
			
			System.out.println("scegliere un'altra pietra");
			
		}
	}
	
	
	/**
	 * metodo che stampa le pietre disonibili, di supporto alla scelta pietre
	 */
	private void stampaPietreDisponibili() {
		for (Pietra pietra : pietre) {
			System.out.println("pietre del tipo > %-12s(%-2s) <, quantità:%d".formatted(
				pietra.getNome(), pietra.getSimbolo(), pietra.getPresenza()
			));
		}
	}
	
	/**
	 * Trova la prima pietra il cui nome o simbolo corrisponda alla stringa specificata
	 * 
	 * @param str il nome o il simbolo della pietra da trovare
	 * @return la prima pietra il cui nome o simbolo corrisponda alla stringa specificata
	 */
	private Pietra findPietraByName(String str) {
		for (Pietra pietra : pietre) {
			if (pietra.matchNameOrSimbol(str))
				return pietra;
		}
		return null;
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * Metodo che fa combattere due TamaGolem tra loro, finchè uno dei due rimane senza vita
	 * 
	 * @param g1 il giocatore 1
	 * @param g2 il giocatore 2
	 * @return il giocatore il cui tamagolem è morto
	 */
	private Giocatore battagliaTama(Giocatore g1, Giocatore g2) {
		TamaGolem tama1 = g1.getTamagolem();
		TamaGolem tama2 = g2.getTamagolem();
		Pietra a=null, b=null;
		int potenza = 99;
		
		boolean entrambiVivi, veloce=false;
		do {
			if (!veloce) {
				stampaSituazione(tama1, tama2, potenza);
				veloce = InputDati.leggiStringa("next>").equals("#");
				
			} else {
				System.out.printf("%s + %s = %d\n", a, b, potenza);
				System.out.printf("%s: %d,\t\t%s: %d\n",
						tama1.getNome(), tama1.getPuntiVita(),
						tama2.getNome(), tama2.getPuntiVita());
				System.out.println("-----------------------------------");
			}
			
			a = tama1.scagliaPietre();
			b = tama2.scagliaPietre();
			potenza = parametri.confrontaElementi(a, b);
			
			if (potenza > 0) tama2.perdiVita(potenza);
			else tama1.perdiVita(-potenza);
			
			entrambiVivi = tama1.isVivo() && tama2.isVivo();
		} while (entrambiVivi);
		
		return tama1.isMorto() ? g1 : g2;
	}
	
	
	/**
	 * Metodo che mostra la situazione dello scontro fra due TamaGolem ad ogni turno, vengono stampati i pv, e l'interazione
	 * fra le pietre
	 * 
	 * @param tama1 TamaGolem del giocatore1
	 * @param tama2 TamaGolem del giocatore2
	 * @param potenza interazione fra le pietre
	 */
	private void stampaSituazione(TamaGolem tama1, TamaGolem tama2, int potenza) {
		boolean isInizio = potenza==99;
		tama1.stampaTamaGolem(isInizio);
		
		if (isInizio) {
			System.out.println();
			System.out.println("  >situazione iniziale<");
			System.out.println();
		}
		else if (potenza == 0) {//gli elementi sono uguali
			System.out.println( "   ||        /\\");
			System.out.println("  \\||/      /||\\" + "  potenza interazione nulla" );
			System.out.println("   \\/        ||");
		}
		else if (potenza > 0) {//tama1 vince su tama2
			System.out.println( "   ||    ");
			System.out.println("  \\||/   potenza interazione: " + potenza);
			System.out.println("   \\/    ");
		}
		else if (potenza < 0) {//tama2 vince su tama1
			System.out.println("              /\\");
			System.out.println("             /||\\" + "  potenza interazione: " + -potenza);
			System.out.println("              ||");
		}
		
		tama2.stampaTamaGolem(isInizio);
		char c = (char)165;
		System.out.println(String.valueOf(c).repeat(35));
	}
	
	
	/**
	 * Metodo che proclama il vincitore
	 * 
	 * @param vincitore giocatore che ha vinto
	 * @param sconfitto giocatore che ha perso
	 * @throws InterruptedException
	 */
	private void proclamaVincitore(Giocatore vincitore, Giocatore sconfitto) throws InterruptedException {
		String presentazioneTama[] = {"il temibile ", "il feroce ", "il distruttore ", "il cannibale ", "il gigante "};
		Thread.sleep(1800);
		System.out.println("la battaglia è giunta al termine, complimenti ad entrambi");
		Thread.sleep(1800);
		System.out.println("ma gloria eterna a " + vincitore.getNome());
		Thread.sleep(1800);
		System.out.println("che ha saputo sconfiggere tutti i mostri avversari, che ricordiamo essere:");
		int i = 0;
		for (TamaGolem golem : sconfitto) {
			System.out.println(presentazioneTama[i] + golem.getNome());
			i = (i+1) % presentazioneTama.length;
			Thread.sleep(1500);
		}
		System.out.println();
		System.out.println(vincitore.getNome() + ", tu verrai ricordato come");
		for (i = 0; i < 3; i++) {
			System.out.printf(".");
			Thread.sleep(1500);
		}
		Thread.sleep(1800);
		System.out.println("Il Guardiano dell'Equilibrio\n\n");
		Thread.sleep(3000);
		
	}
	
	
	
}