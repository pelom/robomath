package br.pjsign.robomath; /**
 * Created by IntelliJ IDEA.
 * User: Milan Vít (Cellane)
 * Date: May 12, 2010
 * Time: 2:16:44 PM
 */

import java.text.MessageFormat;

public class MatrixDeterminant {
	/**
	 * Main method that initializes the matrix and calls method to calculate
	 * its determinant
	 *
	 * @param args command line arguments; unused
	 */
	public static void main (String[] args) {
		double x[][] = {
				{14, 5, 2, 10,},
				{12, -9, 0, 7,},
				{1, 2, 0, -4,},
				{-7, 29, 2, -16},
		};

		double determinant;

		determinant = MatrixOperations.matrixDeterminant (x);
		System.out.println (MessageFormat.format ("Determinant: {0}", Double.toString (determinant)));
	}
}
