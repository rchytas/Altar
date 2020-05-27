package com.rchytas.utilities;
import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
*	Sample class for demostrating sequence alignment problem solution.
* 	Not a complete or elegant work, but can be used as good starting point. 
*	
*	@author rchytas
*
**/

public class SequenceAlignment {
	// Sequence1
	@Option(name = "-alignment_input_file", required = true, usage = "Alignment input file")
	private static String strInputFile;

	// Sequences to be aligned
	@SuppressWarnings("unused")
	private static String seq1, seq2;

	// Unique letters in A and B
	@SuppressWarnings("unused")
	private static String lettersInA, lettersInB;

	// integer indicating global or local
	@SuppressWarnings("unused")
	private static int intGlobalorLocal;

	// integers for gap open and close penalties
	private static float dx, ex, dy, ey;

	// number of alphabets in A and B
	private static int intLettersinA, intLettersinB;

	// Matrix contsaining values assigned to a match between letters in A and B
	private static float[][] data;

	// Array containing all the values from file
	private ArrayList<String> fileValues;
	
	// These are "constants" which indicate a direction in the backtracking
	// array.
	private static final int DIAGONAL     = 0;
	private static final int UP          = 1;
	private static final int LEFT        = 2;
	
	// Matrices declaration
	private static float[][] M;
	private static float[][] Ix;
	private static float[][] Iy;
	private static float[][] ScoreMatrix;
	private static float[][] TrackMatrix;

	/**
	 * Main entry point for sequence alignment.
	 */
	public static void main(String[] args) {
		SequenceAlignment alignSequences = new SequenceAlignment();
		alignSequences.run(args);
	}

	/**
	 * Run Sequence alignment.
	 */
	public void run(String[] args) {
		try {
			// Create command line parser.
			CmdLineParser parser = new CmdLineParser(this);

			try {
				parser.parseArgument(args);

				// Read the input file, storing values in class variables
				readInputFile();

				// Compute Matrices
				computeMmatrix();

			} catch (CmdLineException exception) {
				System.err.println(exception.getLocalizedMessage());
				System.err.printf("java %1s [options...]\r\n", getClass()
						.getSimpleName());
				parser.printUsage(System.err);
				System.err.println();
			}
		} catch (Exception exception) {
			System.err.println(exception.getLocalizedMessage());
		}
	}

	/**
	 * Reads the input file and stores values in local variables
	 */	
	private void readInputFile() {
		try {
			// Read the file and store values in an array
			BufferedReader br = new BufferedReader(new FileReader(strInputFile));
			String line;
			fileValues = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				fileValues.add(line);
			}

			// Assign the values from file to local variables
			seq1 = fileValues.get(0).toString();
			seq2 = fileValues.get(1).toString();
			intGlobalorLocal = Integer.parseInt(fileValues.get(2).toString());
			char[] penalties = fileValues.get(3).toString().toCharArray();
			dx = Character.getNumericValue(penalties[0]);
			ex = Character.getNumericValue(penalties[2]);
			dy = Character.getNumericValue(penalties[4]);
			ey = Character.getNumericValue(penalties[6]);
			intLettersinA = Integer.parseInt(fileValues.get(4).toString());
			lettersInA = fileValues.get(5).toString();
			intLettersinB = Integer.parseInt(fileValues.get(6).toString());
			lettersInB = fileValues.get(7).toString();

			// Index of input file
			int iIndex = 0;

			// populate values in data matrix
			data = new float[intLettersinA][intLettersinB];
			for (int i = 0; i < intLettersinA; i++) {
				for (int j = 0; j < intLettersinB; j++) {
					char[] matchVal = fileValues.get(8 + iIndex).toString()
							.toCharArray();
					data[i][j] = Character.getNumericValue(matchVal[8]);
					iIndex++;
				}
			}			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Computes M matrix
	 * 
	 */
	private void computeMmatrix() {
		try {
			int r, c, rows, cols, del, sub;
			float score;
			rows = seq1.length()+1;
			cols = seq2.length()+1;
			
			System.out.println(rows);
			System.out.println(cols);
			
			// initialize M matrix
			M = new float [rows][cols];
			Ix= new float [rows][cols];
			Iy= new float [rows][cols];
			
			// initiate first row
			for (c = 0; c < cols; c++){
				M[0][c] = 0;
				Ix[0][c] = 0;
				Iy[0][c] = 0;
			}
			
			// Compute different values in M matrix
			for (r = 1; r < rows; r++){
				// initiate first column
				M[r][0] = 0;
				Ix[r][0] = 0;
				Iy[r][0] = 0;
				for (c = 1; c < cols; c++){
					// get the score of aligning these two chars from data
					score =returnScore(seq1.charAt(r-1), seq2.charAt(c-1));					
					M[r][c] = maximum (M[c-1][r-1]+score,Ix[c-1][r-1]+score,Iy[c-1][r-1]+score);					
				}				
			}
			for (r = 0; r < rows; r++){
				for (c = 0; c < cols; c++){
					System.out.print(M[r][c] + " " );
				}
				System.out.println();
			}
		}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	/**
	 * 
	 * @param ch1
	 * @param ch2
	 * @return
	 */
	private final float returnScore (char ch1,char ch2){
		float score =0;
		score = data[lettersInA.indexOf(ch1)][lettersInB.indexOf(ch2)];		
		return score;
	}
	
	/**
	 * Helper method to compute the the greater of two values.
	 * 
	 */
	private final float maximum (float a, float b)
	{
		return (a >= b) ? a : b;
	}
	
	/**
	 * Helper method to compute the the greater of three values. This method
	 * will be used by global alignment algorithm.
	 * 
	 */
	private final float maximum (float a, float b, float c)
	{
		return (a >= b) ? ((a >= c)? a : c) : ((b >= c)? b : c);
	}
	
	/**
	 * Returns the maximum of 4 float numbers.
	 * 
	 * @return The maximum of a, b, c and d.
	 */
	private final float maximum(float a, float b, float c, float d) {
		if (a > b) {
			if (a > c) {
				return a > d ? a : d;
			} else {
				return c > d ? c : d;
			}
		} else if (b > c) {
			return b > d ? b : d;
		} else {
			return c > d ? c : d;
		}
	}


}
