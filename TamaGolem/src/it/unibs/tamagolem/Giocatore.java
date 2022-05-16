package it.unibs.tamagolem;

import java.util.ArrayList;


public class Giocatore {
	
	private String nome;
	ArrayList<TamaGolem> tamaGolemEvocabili = new ArrayList<>();
	private int numTamaGolemEvocabili = 3;


	public void aggiungiGolem(TamaGolem golem) {
		tamaGolemEvocabili.add(golem);

	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	//NumTamaGolemEvocaboli = G = ⎡(N - 1)(N - 2) / (2 * P)⎤ classe TamaGolem?
	//G = ceil((N - 1)(N - 2) / (2 * P))

	public int getNumTamaGolemEvocabili() {
		return numTamaGolemEvocabili;
	}

	public void aggiornaNumTama() {
		numTamaGolemEvocabili-- ;
	}
	
	public Giocatore(String nome) {
		this.nome = nome;
	}

	public boolean getTamaVivi() {
		for (int i = 0; i < tamaGolemEvocabili.size(); i++) {
			if(tamaGolemEvocabili.get(i).getPuntiVita() > 0)
				return true;
		}
		return false;
	}


}
