package it.unibs.tamagolem;

public class Elemento {
	
	private final String shortName;
	//private final String fullName;
	public final int id;
	
	public Elemento(String name, int id) {
		shortName = name;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return shortName;
	}
	
}
