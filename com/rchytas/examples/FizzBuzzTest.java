package com.rchytas.examples;

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
		assertEquals("1", fizzBuzz(1));
		assertEquals("2", fizzBuzz(2));
		assertEquals("Fizz", fizzBuzz(3));
		assertEquals("Fizz", fizzBuzz(6));
		assertEquals("Fizz", fizzBuzz(9));
		assertEquals("Buzz", fizzBuzz(5));
		assertEquals("Buzz", fizzBuzz(10));
		assertEquals("Buzz", fizzBuzz(15));
		assertEquals("FizzBuzz", fizzBuzz(15));
		int n = 100;
		printMe(n);
	}

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

	private void printMe(int n) {
		for (int i = 1; i <= n; i++) {
			System.out.println(fizzBuzz(i));
		}
	}
}
