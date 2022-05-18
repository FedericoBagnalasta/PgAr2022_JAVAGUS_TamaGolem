package it.unibs.tamagolem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class TamaGolem {
	
	private final String nome;
	private int puntiVita;
	private Deque<Pietra> pietreGiocabili = new ArrayDeque <> ();
	
	
	public TamaGolem (String nome, int V) {
		this.nome = nome;	
		puntiVita = V;
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public int getPuntiVita() {
		return puntiVita;
	}
	
	//Metodo che infligge danni al tamaGolem
	public void perdiVita(int danniInflitti) {
		puntiVita -= danniInflitti ;
	}
	
	//Metodo di verifica della morte del TamaGolem
	public boolean isMorto() {
		return puntiVita <= 0;
	}
	
	public boolean isVivo() {
		return puntiVita > 0;
	}
	
	//Metodo per assegnare le pietre di un TamaGolem
	public void impostaPietre (ArrayList<Pietra> pietrePerTamaGolem) {
		pietreGiocabili.addAll(pietrePerTamaGolem);
	}
	
	//Metodo che permette al TamaGolem di scagliare una pietra
	public Pietra scagliaPietre() {
		Pietra p;
		pietreGiocabili.addLast(p = pietreGiocabili.removeFirst());
		return p;
	}
	
	public Pietra getPietraScagliata() {
		return pietreGiocabili.getLast();
	}
	
	public boolean arePietreCopyOf(ArrayList<Pietra> otherPietre) {
		int i=0;
		for (Pietra p : pietreGiocabili) {
			if (!p.equals(otherPietre.get(i))) return false;
			i++;
		}
		return true;
	}
	
	//Metodo che permette di visualizzare i dati relativi al TamaGolem
	public void stampaTamaGolem() {
		System.out.println("===========================");
		System.out.printf("TamaGolem:     %s\n", getNome());
		System.out.printf("Punti vita:    %d\n", getPuntiVita());
		System.out.println("Pietre del TamaGolem:");
		int i=0, n=pietreGiocabili.size();
		for(Pietra pietra: pietreGiocabili) {
			System.out.printf(i==n-1 ? ">%s< " : "%s ", pietra.getNome());
			i++;
		}
		System.out.println();
		System.out.println("===========================");
	}
	
}
