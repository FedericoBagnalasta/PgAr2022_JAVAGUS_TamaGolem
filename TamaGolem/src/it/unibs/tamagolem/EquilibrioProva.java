package it.unibs.tamagolem;

import java.util.ArrayList;
import java.util.Random;

public class EquilibrioProva {
	private ArrayList<Elemento> elementi = new ArrayList<>();	
	private String nomi[] = {"berillio", "vanadio", "argo", "scandio", "rubidio", "zirconio", "tecneto", "rutenio", "antimonio", "xeno", "wolframio",
			"osmio", "radon", "meitmerio", "nihonio", "livermorio", "protoattinio", "berkelio", "hassio", "lantanio", "boro", "renio", "afnio"};
	
	
	//metodo che sceglie i nomi casualmente per gli elementi, e poi crea oggetti elementi
	public void setElementi() {
		Random random = new Random();
		int indice = random.nextInt(4);
		for (int i = 0; i < 7; i++) {
			String string = nomi[indice];
			Elemento elemento = new Elemento(string, int potenza);
			elementi.add(elemento);
			
			indice += random.nextInt(2) +1;
		}

}

	//metodo che passa la lista di pietre disponibili da utilizzare nella classe Scontro
	public void getPietre(ArrayList<Elemento> pietre) {
		pietre.addAll(pietre);
	}
	
}
