package it.unibs.tamagolem;

public class Elemento {
	private String nome;
	public final int id;
	private int presenza;
	

		
	public Elemento(String nome, int id) {
		this.nome = nome;
		this.id = id;;
		presenza = 3;
	}
	
		@Override
	public String toString() {
		return nome;
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
	
	
	

}
