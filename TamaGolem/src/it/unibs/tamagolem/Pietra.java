package it.unibs.tamagolem;

import java.util.ArrayList;

public class Pietra {
	
	public final Elemento elemento;
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
	public void diminuisciPresenza() {
		presenza--;
	}
	public void incrementaPresenza() {
		presenza++;
	}
	public boolean isScortaFinita() {
		return presenza==0;
	}
	
	public boolean matchNameOrSimbol(String str) {
		return getSimbolo().equalsIgnoreCase(str)
			|| getNome().equalsIgnoreCase(str);
	}
	
	
	/**
	 * Restituisce una copia della pietra con scorta specificata.
	 * 
	 * @param S la scorta della pietra create
	 * @return una copia della pietra con scorta specificata
	 */
	public Pietra copyWithScorta(int S) {
		return new Pietra(elemento, id, S);
	}
	
	
	/**
	 * Restituisce un array casuale di pietre di lunghezza specificata.
	 * 
	 * @param N il numero di pietre da creare
	 * @param S la scorta per ogni pietra
	 * @return un array casuale di pietre di lunghezza specificata
	 */
	public static Pietra[] getPietre(int N, int S) {
		ArrayList<Elemento> elementi = Elemento.getRandom(N);
		Pietra[] pietre = new Pietra[N];
		for (int i=0; i<N; i++) {
			pietre[i] = new Pietra(elementi.get(i), i, S);
		}
		return pietre;
	}
	
	@Override
	public String toString() {
		return "%s(%s)".formatted(elemento.nome, elemento.simbolo);
	}
	
}
