package com.rchytas.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



public class ArrayExamplesTest {
	
	@Test
	public void testEqualsEmpty() {
		int[] arr1 = new int[] {};
		int[] arr2 = new int[] {};
		assertTrue(ArrayExamples.eq(arr1, arr2));
	}

	
	@Test
	public void testEq() {
		int[] arr1 = new int[] { 1, 2, 3 };
		int[] arr2 = new int[] { 1, 2, 3 };
		assertTrue(ArrayExamples.eq(arr1, arr2));
	}

	
	@Test
	public void testNotEq() {
		int[] arr1 = new int[] { 1, 2, 3 };
		int[] arr2 = new int[] { 1, 3, 3 };
		assertFalse(ArrayExamples.eq(arr1, arr2));
	}

	
	@Test
	public void testEqAlias() {
		int[] arr1 = new int[] { 1, 2, 3 };
		int[] arr2 = arr1;
		assertTrue(ArrayExamples.eq(arr1, arr2));
	}

	
	@Test
	public void testReverse() {
		int[] arr = new int[] { 1, 2, 3 };
		int[] arr2 = ArrayExamples.reverse(arr);
		assertTrue(arr != arr2);
		assertNotNull(arr2);
		assertTrue(arr2.length == 3);
		assertTrue(arr2[2] == 1);
		assertTrue(arr2[1] == 2);
		assertTrue(arr2[0] == 3);
	}

	
	@Test
	public void testSub() {
		int[] arr = { 0, 1, 2, 3, 4 };
		int[] ans = ArrayExamples.sub(arr, 2, 3);
		assertTrue(ArrayExamples.eq(new int[] { 2 }, ans));
	}

	
	@Test
	public void testSubHead() {
		int[] arr = { 0, 1, 2, 3, 4 };
		int[] ans = ArrayExamples.sub(arr, 0, 2);
		assertTrue(ArrayExamples.eq(new int[] { 0, 1 }, ans));
	}

	
	@Test
	public void testSubTail() {
		int[] arr = { 0, 1, 2, 3, 4 };
		int[] ans = ArrayExamples.sub(arr, 2, 5);
		assertTrue(ArrayExamples.eq(new int[] { 2, 3, 4 }, ans));
	}

	
	@Test
	public void testNotPalindrome() {
		int[] arr = new int[] { 1, 2, 3 };
		assertFalse(ArrayExamples.palindrome(arr));
	}

	
	@Test
	public void testPalindromeEven() {
		int[] arr = new int[] { 1, 2, 2, 1 };
		assertTrue(ArrayExamples.palindrome(arr));
	}

	
	@Test
	public void testPalindromeEmpty() {
		int[] arr = new int[] {};
		assertTrue(ArrayExamples.palindrome(arr));
	}

	
	@Test
	public void testPalindromeOdd() {
		int[] arr = new int[] { 1, 2, 3, 2, 1 };
		assertTrue(ArrayExamples.palindrome(arr));
	}

	
	@Test
	public void testConcat1() {
		int[] arr1 = new int[] { 1, 2, 3 };
		int[] arr2 = new int[] { 4, 5, 6 };
		int[] ans = new int[] { 1, 2, 3, 4, 5, 6 };
		assertTrue(ArrayExamples.eq(ans, ArrayExamples.concat(arr1, arr2)));
	}

	
	@Test
	public void testConcat2() {
		int[] arr1 = new int[] { 1, 2, 3 };
		int[] arr2 = new int[] {};
		int[] ans = new int[] { 1, 2, 3 };
		assertTrue(ArrayExamples.eq(ans, ArrayExamples.concat(arr1, arr2)));
	}

	
	@Test
	public void testConcat3() {
		int[] arr1 = new int[] {};
		int[] arr2 = new int[] { 1, 2, 3 };
		int[] ans = new int[] { 1, 2, 3 };
		assertTrue(ArrayExamples.eq(ans, ArrayExamples.concat(arr1, arr2)));
	}

	
	@Test
	public void testInterleave() {
		int[] arr1 = { 1, 3, 5 };
		int[] arr2 = { 2 };
		int[] ans = { 1, 2, 3, 5 };
		assertTrue(ArrayExamples.eq(ans, ArrayExamples.interleave(arr1, arr2)));
	}

	
	@Test
	public void testMax() {
		int[] arr1 = { 1, 3, 4, 4 };
		assertEquals(ArrayExamples.max(arr1), 4);
	}

	
	@Test
	public void testMax1() {
		assertEquals(ArrayExamples.max(new int[] { 5 }), 5);
	}

	
	@Test
	public void testMaxIndex() {
		int[] arr1 = { 1, 3, 4, 4 };
		assertEquals(2, ArrayExamples.maxIndex(arr1));
	}

	
	@Test
	public void testMaxIndex1() {
		assertEquals(0, ArrayExamples.maxIndex(new int[] { 5 }));
	}

	
	@Test
	public void testRect() {
		int[][] arr1 = { { 1, 2 }, { 3, 4 } };
		assertTrue(ArrayExamples.rect(arr1));
	}

	
	@Test
	public void testNotRect() {
		int[][] arr1 = { { 1, 2 }, { 3, 4, 5 } };
		assertFalse(ArrayExamples.rect(arr1));
	}

	
	@Test
	public void testRectEmpty() {
		int[][] arr = {};
		assertTrue(ArrayExamples.rect(arr));
	}

	
	@Test
	public void testRectTriv() {
		int[][] arr1 = { { 1, 2 } };
		assertTrue(ArrayExamples.rect(arr1));
	}

	
	@Test
	public void testAvg1() {
		int[][] arr = { { 1 } };
		assertEquals(ArrayExamples.avg(arr), 1);
	}

	
	@Test
	public void testAvg2() {
		int[][] arr = { { 1, 2 }, { 3 } };
		assertEquals(ArrayExamples.avg(arr), 2);
	}

	
	@Test
	public void testMagicYes() {
		int[][] sq = { { 2, 7, 6 }, { 9, 5, 1 }, { 4, 3, 8 } };
		assertTrue(ArrayExamples.isMagicSquare(sq));
	}

	
	@Test
	public void testMagicAbnormal() {
		int[][] sq = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		assertFalse(ArrayExamples.isMagicSquare(sq));
	}

	
	@Test
	public void testMagicYes1() {
		int[][] sq = { { 1 } };
		assertTrue(ArrayExamples.isMagicSquare(sq));
	}

	
	@Test
	public void testMagicNo() {
		int[][] sq = { { 2, 7, 6 }, { 9, 5, 1 }, { 4, 8, 3 } };
		assertFalse(ArrayExamples.isMagicSquare(sq));
	}
}
