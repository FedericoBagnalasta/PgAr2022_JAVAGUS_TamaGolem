package it.unibs.tamagolem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class TamaGolem {
	
	private int puntiVita;
	private String nome;
	public static final int PUNTI_VITA_MAX = 10;
	public static final int NUM_PIETRE = 3;
	
	Deque<Elemento> pietreGiocabili = new ArrayDeque <> ();
	
	public TamaGolem (String nome) {
		this.nome = nome;	
		puntiVita = PUNTI_VITA_MAX;
	}
	
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getPuntiVita() {
		return puntiVita;
	}


	public void setPuntiVita(int puntiVita) {
		this.puntiVita = puntiVita;
	}

	//Metodo che infligge danni al tamaGolem
	public void perdiVita(int danniInflitti) {
		puntiVita = Math.max(0, puntiVita - Math.abs(danniInflitti)) ;
	}
	
	//Metodo di verifica della morte del TamaGolem
	public boolean verificaVita() {
		if (puntiVita == 0) return true;
		else return false;
	}
	
	//Metodo per assegnare le pietre di un TamaGolem
	public void impostaPietre (ArrayList<Elemento> pietreScelte) {
		
		for(int i=0; i < NUM_PIETRE; i++) {
			pietreGiocabili.add(pietreScelte.get(i));
		}
	}

	//Metodo che permette al TamaGolem di scagliare una pietra
	
	public Elemento scagliaPietre() {
		Elemento a;
		a = pietreGiocabili.pollFirst();
		pietreGiocabili.add(a);
		a = pietreGiocabili.getFirst();
		return a;
	}
	
	//Metodo che permette di visualizzare i dati relativi al TamaGolem
	public void stampaTamaGolem() {
		int i =0;
		System.out.println("===========================");
		System.out.printf("TamaGolem:     %s\n", getNome());
		System.out.printf("Punti vita:    %d\n", getPuntiVita());
		System.out.println("Pietre del TamaGolem:");
		for(Elemento pietra: pietreGiocabili) {
			if (i==0) {
				System.out.printf(">%s< ", pietra.getNome());
			}
			else 
				System.out.printf("%s ", pietra.getNome());
			i++;
		}
		System.out.println();
		System.out.println("===========================");
	}
}
