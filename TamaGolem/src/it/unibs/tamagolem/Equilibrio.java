package it.unibs.tamagolem;

import java.util.Iterator;
import it.unibs.mylib.EstrazioniCasuali;

/**
 * Classe per la creazione dell'equilibrio.
 * 
 * @author JAVAGUS
 */
public class Equilibrio implements Iterable<Pietra> {
	
	private static final int SIGMA=2, MAX_OFFSET=4;
	/** Matrice con le varie potenze tra elementi. */
	private final int[][] potenze;
	/** Il valore massimo (in modulo) presente in <code>potenze</code>. */
	private int maxPotenza;
	private final Pietra[] pietre;


	/**
	 * Costruttore della classe Equilibrio.
	 * 
	 * @param N il numero di elementi da creare
	 * @param S la scorta di pietre per elemento
	 */
	public Equilibrio(int N, int S) {
		potenze = creaEquilibrio(N);
		pietre = Pietra.getPietre(N, S);
	}
	
	
	/**
	 * Genera la matrice dell'Equilibrio.
	 * 
	 * @param N il numero di elementi
	 * @return la matrice dell'equilibrio
	 */
	private int[][] creaEquilibrio(int N) {
		int[][] equ = new int[N][N];
		
		for (int i=0; i<N-1; i++) {
			int sumX=0, absSumX=0, absSumY=0, j=0;
			
			for (; j<i; j++) {
				sumX += equ[i][j];
				absSumY += equ[j][i+1];
			}
			
			absSumY = Math.abs(absSumY);
			
			for (++j; j<N-1; j++) {
				int r, absR;
				absSumX = Math.abs(sumX);
				
				do {
					r = EstrazioniCasuali.estraiFromRange(SIGMA, -sumX, MAX_OFFSET);
					absR = Math.abs(r);
				} while (absR==absSumX || absR==absSumY);
				
				equ[i][j] = r;
				equ[j][i] =-r;
				sumX += r;
				if (absR > maxPotenza) maxPotenza = absR;
			}
			
			equ[i][j] =-sumX;
			equ[j][i] = sumX;
			if (absSumX > maxPotenza) maxPotenza = absSumX;
		}
		
		return equ;
	}
	
	
	/**
	 * Restituisce la potenza con segno tra due elementi.<br>
	 * Un valore positivo indica che <code>a</code> è l'elemento forte,
	 * altrimenti l'elemento forte è <code>b</code>
	 * 
	 * @param a il primo elemento
	 * @param b il secondo elemento
	 * @return la potenza con segno tra i due elementi
	 */
	public int confrontaElementi(Pietra a, Pietra b) {
		return potenze[a.id][b.id];
	}
	
	
	/**
	 * Restituisce l'estremo superiore dell'insieme delle interazioni di potenza.
	 * 
	 * @return l'estremo superiore dell'insieme delle interazioni di potenza
	 */
	public int getSup() {
		return maxPotenza+1;
	}
	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		String s = "%s%3s";
		sb.append(s.formatted("",""));
		for (int i=0; i<potenze.length; i++)
			sb.append(s.formatted(" ", pietre[i].getSimbolo()));
		
		for (int i=0; i<potenze.length; i++) {
			sb.append("\n" + s.formatted("",pietre[i].getSimbolo()));
			for (int j=0; j<potenze[0].length; j++) {
				sb.append(s.formatted(" ", potenze[i][j]));
			}
		}
		
		return sb.toString();
	}
	
	
	
	@Override
	public Iterator<Pietra> iterator() {
		return new Iterator<Pietra>() {
			int i=0;
			public boolean hasNext() {
				return i<pietre.length;
			}
			public Pietra next() {
				return pietre[i++];
			}
		};
	}

}
