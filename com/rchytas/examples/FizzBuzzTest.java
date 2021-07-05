package com.rchytas.examples;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Write a program that prints the numbers from 1 to 100. But for multiples of 3
 * print “Fizz” instead of the number and for the multiples of 5 print “Buzz“.
 * For numbers which are multiples of both 3 and 5 print “FizzBuzz”.
 * 
 * Also, provide Unit Test for the program.
 * 
 * @author mpandit
 *
 */

public class FizzBuzzTest {

	@Test
	public void testFizzBuzz() {
		// FizzBuzz Unit Test using array
		int k = 5;
		String expected_result[] = { "1", "2", "Fizz", "4", "Buzz" };
		assertArrayEquals(expected_result, fizzBuzzArr(k));
		// FizzBuzz Unit Test using individual numbers
		assertEquals("1", fizzBuzz(1));
		assertEquals("2", fizzBuzz(2));
		assertEquals("Fizz", fizzBuzz(3));
		assertEquals("Fizz", fizzBuzz(6));
		assertEquals("Fizz", fizzBuzz(9));
		assertEquals("Buzz", fizzBuzz(5));
		assertEquals("Buzz", fizzBuzz(10));
		//assertEquals("Buzz", fizzBuzz(15));
		assertEquals("FizzBuzz", fizzBuzz(15));
		int n = 100;
		printMe(n);
	}
	
	/**
	 * Returns array for FizzBuzz Unit Test example
	 * 
	 * @param n
	 * @return
	 */
	public String[] fizzBuzzArr(int n) {
		String result[] = new String[n];
		for (int i = 1; i <= n; i++) {
			String output = "";
			if (i % 3 == 0) {
				output += "Fizz";
			}
			if (i % 5 == 0) {
				output += "Buzz";
			}
			if (i % 3 != 0 && i % 5 != 0) {
				output = String.valueOf(i);
			}
			result[i - 1] = output;
		}
		return result;
	}

	/**
	 * FizzBuzz
	 * 
	 * @param input
	 * @return
	 */
	private String fizzBuzz(int input) {
		String output = "";
		if (input % 3 == 0) {
			output += "Fizz";
		}
		if (input % 5 == 0) {
			output += "Buzz";
		}
		if (input % 3 != 0 && input % 5 != 0) {
			output = String.valueOf(input);
		}
		return output;
	}

	/**
	 * 
	 * @param n
	 */
	private void printMe(int n) {
		for (int i = 1; i <= n; i++) {
			System.out.println(fizzBuzz(i));
		}
	}
}
