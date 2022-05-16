package it.unibs.tamagolem;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class Scontro {
	
	private ArrayList<Elemento> pietre = new ArrayList<>();
	EquilibrioProva parametri = new EquilibrioProva();
	
	//costruttore di scontro, recupera le pietre disponibili impostate dalla classe Equilibrio
	public Scontro() {
		parametri.getPietre(pietre);
	}
	
	
	//metodo che permette ad un giocatore di scegliere tre pietre
	public void sceltaPietre() {
		boolean esci = true;
		int i=0;int scelta=0;
		
		do {
			stampaPietreDisponibili();
			String nomePietra = InputDati.leggiStringa("inserire il nome per selezionare la pietra associata\n");
			while (i < pietre.size() && esci) {
				if (nomePietra.equals(pietre.get(i).getNome())) {
					System.out.println("Pietra impostata");
					pietre.get(i).diminuisciPresenza();
					esci = false;
					scelta++;
				}
				i++;
			} 
			if (esci) {
				System.out.println("Il nome inserito non è valido o non corrisponde, riprovare");
			}
		} while (scelta != 3);		
	}
	
	
	//metodo che stampa le pietre disonibili
	private void stampaPietreDisponibili() {

		for (int i = 0; i < pietre.size(); i++) {
			String nome = pietre.get(i).getNome();
			int quante =pietre.get(i).getPresenza();
			if ( quante > 0) {
				System.out.println("pietre del tipo >" + nome+ "<, quantità:" + quante);
			}		
		}
	}
	
//---------------------------------------------------verifca del vincitore----------------------------------------------------------
	
	public void verificaVincitoreTamaGolem() {
		
	}
	
	public boolean verificaVincitore() {
		return false;
		
	}
	
//----------------------------------------------------------------------------------------------------------------------------------
	
	public void battaglia(Giocatore g1, Giocatore g2) {
		
		System.out.println("Giocatore" + g1.getNome() + " è il momento di combattere, scegli le pietre ccon cui combatterà il tuo"
				+ "Tamagolem ");
		sceltaPietre();
		System.out.println("Giocatore" + g2.getNome() + " è il momento di combattere, scegli le pietre ccon cui combatterà il tuo"
				+ "Tamagolem ");
		sceltaPietre();
		
		
		System.out.println("che comici la battaglia");
		
		do {
			
			//battaglia tra TamaGolem
			
			
			
			
		} while (verificaVincitore());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}