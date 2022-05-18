package it.unibs.tamagolem;

import java.util.ArrayList;
import java.util.Iterator;


public class Giocatore implements Iterable<TamaGolem> {
	
	private final String nome;
	private ArrayList<TamaGolem> tamaGolemEvocati = new ArrayList<>();
	private int numTamaGolemEvocabili;
	
	
	public Giocatore(String nome, int G) {
		this.nome = nome;
		numTamaGolemEvocabili = G;
	}
	
	
	public void aggiungiGolem(TamaGolem golem) {
		tamaGolemEvocati.add(golem);
		numTamaGolemEvocabili-- ;
	}

	public String getNome() {
		return nome;
	}
	
	public boolean canEvocareTamagolem() {
		return numTamaGolemEvocabili > 0;
	}
	
	public TamaGolem getTamagolem() {
		return tamaGolemEvocati.get(tamaGolemEvocati.size()-1);
	}
	
	public boolean isTamaMorto() {
		return getTamagolem().isMorto();
	}
	
	
	@Override
	public Iterator<TamaGolem> iterator() {
		return tamaGolemEvocati.iterator();
	}


}
