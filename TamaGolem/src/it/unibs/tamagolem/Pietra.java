package it.unibs.tamagolem;

import java.util.ArrayList;

public class Pietra {
	
	private Elemento elemento;
	public final int id;
	private int presenza;
	
	
	public Pietra(Elemento elemento, int id, int presenza) {
		this.elemento = elemento;
		this.id = id;
		this.presenza = presenza;
	}
	
	
	public String getNome() {
		return elemento.nome;
	}
	public String getSimbolo() {
		return elemento.simbolo;
	}
	
	public int getPresenza() {
		return presenza;
	}
	public void setPresenza(int presenza) {
		this.presenza = presenza;
	}
	
	public void diminuisciPresenza() {
		presenza--;
	}
	
	
	/**
	 * Restituisce un array casuale di pietre di lunghezza specificata.
	 * 
	 * @param N il numero di pietre da creare
	 * @param P la scorta per ogni pietra
	 * @return un array casuale di pietre di lunghezza specificata
	 */
	public static Pietra[] getPietre(int N, int P) {
		ArrayList<Elemento> elementi = Elemento.getRandom(N);
		Pietra[] pietre = new Pietra[N];
		for (int i=0; i<N; i++) {
			pietre[i] = new Pietra(elementi.get(i), i, P);
		}
		return pietre;
	}
	
	@Override
	public String toString() {
		return "%s (%s)".formatted(elemento.nome, elemento.simbolo);
	}

	
}
