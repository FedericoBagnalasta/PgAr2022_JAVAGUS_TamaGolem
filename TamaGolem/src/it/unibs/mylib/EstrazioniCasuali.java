package it.unibs.mylib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EstrazioniCasuali {
	
	private static Random rand = new Random();
	
	public static int estraiIntero(int range) {
		return rand.nextInt(range+1);
	}
	public static int estraiIntero(int min, int max) {
		return min + estraiIntero(max-min);
	}
	
	
	/**
	 * Pick a random non-zero integer from a range with specified radius and mid-point.
	 * 
	 * @param sigma the radius of the range to pick from
	 * @param offset the mid-point of the range to pick from
	 * @param maxOffset the maximum absolute value that <code>offset</code> will be constrained to
	 * @return a random integer within the specified range
	 */
	public static int estraiFromRange(int sigma, int offset, int maxOffset) {
		offset = constrain(offset, -maxOffset, maxOffset);
		int r;
		while (0 == (r = offset+EstrazioniCasuali.estraiIntero(-sigma,sigma)));
		return r;
	}
	
	/**
	 * Constrain a value within the specified boundaries.
	 * 
	 * @param x the value to be constrained
	 * @param a the lower bound
	 * @param b the upper bound
	 * @return the constrained value
	 */
	public static int constrain(int x, int a, int b) {
		return x<a ? a : x>b ? b : x;
	}
	
	
	/**
	 * Pick at most n objects from the specified array.
	 * 
	 * @param <T> the type of the object to be picked
	 * @param n the number of elements that should be picked
	 * @param array the array from which to pick the objects
	 * @return an <code>{@literal ArrayList<T>}</code> with the picked objects
	 */
	@SafeVarargs
	public static <T> ArrayList<T> estraiObjects(int n, T...array) {
		ArrayList<T> estratti = new ArrayList<>(),
					daEstrarre = new ArrayList<>();
		
		Collections.addAll(daEstrarre, array);
		if (n>=array.length) return daEstrarre;
		
		while (n-->0) {
			int index = EstrazioniCasuali.estraiIntero(daEstrarre.size()-1);
			estratti.add( daEstrarre.remove(index) );
		}
		
		return estratti;
	}
	
}
