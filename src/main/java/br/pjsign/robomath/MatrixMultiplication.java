package br.pjsign.robomath;

/**
 * Created by IntelliJ IDEA.
 * User: Milan Vít (Cellane)
 * Date: May 12, 2010
 * Time: 10:32:52 AM
 */

public class MatrixMultiplication {
	/**
	 * Main method that initialises matrices we'll be counting with, calls
	 * method that does the multiplication and prints results
	 *
	 * @param args command line arguments; unused
	 */
	public static void main (String args[]) {
		int x[][] = {
				{1, 2,},
				{4, 5,},
				{7, 8,},
		};

		int y[][] = {
				{9, 8, 7,},
				{6, 5, 4,},
		};

		int z[][];

		z = MatrixOperations.multiplyMatrices (x, y);
		MatrixOperations.printMatrix (z, 3);
	}
}
