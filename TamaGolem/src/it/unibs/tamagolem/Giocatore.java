package it.unibs.tamagolem;

import java.util.ArrayList;

public class Giocatore {
	private String nome;
	private ArrayList<TamaGolem> tamaGolemEvocabili;
	private int numTamaGolemEvocabili;
	private ArrayList<Elemento> elementi;
	
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
	public void setNumTamaGolemEvocabili(int numTamaGolemEvocabili) {
		this.numTamaGolemEvocabili = numTamaGolemEvocabili;
	}
	public void aggiornaNumTama() {
		numTamaGolemEvocabili-- ;
	}
	public Giocatore(String nome) {
		this.nome = nome;
	}
	
	public Giocatore(String nome, ArrayList<TamaGolem> tamaGolemEvocabili) {
		this.nome = nome;
		this.tamaGolemEvocabili = tamaGolemEvocabili;
	}
}
