package it.unibs.tamagolem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Elemento {
	private String nome;
	private int potenza;
	private int presenza;
	

		
	public Elemento(String nome, int potenza) {
		this.nome = nome;
		this.potenza = potenza;
		presenza = 3;
	}
	
		
	public int getPresenza() {
		return presenza;
	}
	
	
	public void diminuisciPresenza() {
		this.presenza --;
	}
	
	
	public String getNome() {
		return this.nome;
	}
	
	public int getPotenza() {
		return potenza;
	}
	
	
	

}
