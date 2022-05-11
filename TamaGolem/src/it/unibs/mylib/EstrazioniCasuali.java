package it.unibs.mylib;

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
	 * Pick a random integer from a range with specified variance and mid-point,
	 * without picking the middle or boundaries values.
	 * 
	 * @param sigma the variance of the range to pick from
	 * @param offset the mid-point of the range to pick from
	 * @param maxOffset the maximum absolute value that <code>offset</code> will be constrained to
	 * @return a random integer within the specified range
	 */
	public static int estraiFromRange(int sigma, int offset, int maxOffset) {
		offset = constrain(offset, -maxOffset, maxOffset);
		int r;
		do r = EstrazioniCasuali.estraiIntero(-sigma,sigma) + offset;
		while (r==0 || r==offset || r==-offset);
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
	 * Pick a random Object from the specified array.
	 * 
	 * @param <T> the type of the element to be picked
	 * @param array the array from which to pick a random element
	 * @return a random Object from the specified array
	 */
	@SafeVarargs
	public static <T> T estraiObject(T...array) {
		int index = estraiIntero(array.length-1);
		return array[index];
	}
	
}
