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
