package it.unibs.tamagolem;


import java.util.ArrayList;

import it.unibs.mylib.EstrazioniCasuali;

public enum Elemento {
	Be ("Berillio"),
	B  ("Boro"),
	Ar ("Argo"),
	Sc ("Scandio"),
	V  ("Vanadio"),
	Rb ("Rubidio"),
	Zr ("Zirconio"),
	Tc ("Tecneto"),
	Ru ("Rutenio"),
	Sb ("Antimonio"),
	Xe ("Xeno"),
	La ("Lantanio"),
	Hf ("Afnio"),
	W  ("Wolframio"),
	Re ("Renio"),
	Os ("Osmio"),
	Rn ("Radon"),
	Pa ("Protoattinio"),
	Bk ("Nerkelio"),
	Hs ("Hassio"),
	Mt ("Meitmerio"),
	Nh ("Nihorio"),
	Lv ("Livermorio");
	
	public final String nome, simbolo;
	
	
	private Elemento(String nome) {
		this.nome = nome;
		this.simbolo = toString();
	}
	
	
	public static ArrayList<Elemento> getRandom(int n) {
		return EstrazioniCasuali.estraiObjects(n, values());
	}
		
	public String getNome() {
		return this.nome;
	}
	
	
	

}
