package it.unibs.tamagolem;

import it.unibs.mylib.EstrazioniCasuali;

/**
 * Classe per la creazione dell'equilibrio.
 * 
 * @author JAVAGUS
 */
public class Equilibrio {
	
	private static final int SIGMA=2, MAX_OFFSET=4;
	/** Matrice con le varie potenze tra elementi. */
	private final int[][] potenze;
	/** Il valore massimo (in modulo) presente in <code>potenze</code> . */
	private int maxPotenza;
	private final Elemento[] pietreGiocabili;
	
	
	public Equilibrio(int N, String...elementNames) {
		if (elementNames.length<N) throw new NullPointerException();
		pietreGiocabili = new Elemento[N];
		for (int i=0; i<N; i++) {
			pietreGiocabili[i] = new Elemento(elementNames[i], i);
		}
		potenze = creaEqulibrio(N);
	}
	
	
	/**
	 * Questo metodo verrà poi rimosso, serve solo a noi all'inizio
	 * per avere un'idea di come usare la classe e i suoi metodi.
	 */
	@Deprecated
	public static void main() {
		
		int N = 6;
		String[] elementNames = "H-He-Li-Be-B-C-N-O-F-Ne-Na".split("-");
		
		Equilibrio equilibrio = new Equilibrio(N, elementNames);
		System.out.println(equilibrio);
		int V = equilibrio.getSup();
		System.out.println("V: "+V);
		
		
		Elemento a,b;
		for (int i=0,p; i<5; i++) {
			a = equilibrio.getPietreGiocabili()[EstrazioniCasuali.estraiIntero(N-1)];
			b = EstrazioniCasuali.estraiObject(equilibrio.getPietreGiocabili());
			p = equilibrio.confrontaElementi(a, b);
			System.out.println("%2s + %2s = %2d".formatted(a, b, p));
		}
		
		
		int rowSum=0, colSum=0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				rowSum += equilibrio.potenze[i][j];
				colSum += equilibrio.potenze[j][i];
			}
			if (rowSum!=0 || colSum!=0)
				System.out.println("row %d=%d, column %d=%d".formatted(i,rowSum,i,colSum));
		}
		
	}
	
	
	/**
	 * Genera la matrice dell'Equilibrio.
	 * 
	 * @return la matrice dell'equilibrio
	 */
	private int[][] creaEqulibrio(int N) {
		int[][] equ = new int[N][N];
		
		for (int i=0; i<N-1; i++) {
			int sum=0,j=0;
			while (j<i) sum += equ[i][j++];
			
			for (; j<N-1; j++) {
				if (j==i) continue;
				int r = EstrazioniCasuali.estraiFromRange(SIGMA, -sum, MAX_OFFSET);
				
				equ[i][j] = r;
				equ[j][i] =-r;
				sum += r;
				if (Math.abs(r)>maxPotenza) maxPotenza=Math.abs(r);
			}
			
			equ[i][j] =-sum;
			equ[j][i] = sum;
			if (Math.abs(sum)>maxPotenza) maxPotenza=Math.abs(sum);
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
	public int confrontaElementi(Elemento a, Elemento b) {
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
	/**
	 * Restituisce l'array delle pietre giocabili.
	 * 
	 * @return l'array delle pietre giocabili
	 */
	public Elemento[] getPietreGiocabili() {
		return pietreGiocabili;
	}
	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		String s = "%s%3s";
		sb.append(s.formatted("",""));
		for (int i=0; i<potenze.length; i++)
			sb.append(s.formatted(" ", pietreGiocabili[i]));
		
		
		for (int i=0; i<potenze.length; i++) {
			sb.append("\n");
			sb.append(s.formatted("",pietreGiocabili[i]));
			for (int j=0; j<potenze[0].length; j++) {
				sb.append(s.formatted(" ", potenze[i][j]));
			}
			
		}
		
		return sb.toString();
	}
	
	
	
}
