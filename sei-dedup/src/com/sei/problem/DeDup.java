/*
 * Copyright (c) 2016, SEI Investments Company. All rights reserved.
 */
package com.sei.problem;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * This class various methods to remove duplicates from an integer array.
 * 
 * <p>
 * <code>deDupWithSimpleLooping</code> - Loops over an array comparing each
 * element with others to identify and remove duplicate elements.
 * </p>
 * 
 * <p>
 * <code>deDupWithOriginalOrder</code> - Use java collection classes
 * <code>HashSet/LinkedHashSet</code> which don't allow duplicate elements.
 * </p>
 * 
 * <p>
 * <code>deDupWithIntStream</code> - Use java streams <i>introduced in Java 8</i> to
 * remove duplicate elements.
 * </p>
 * 
 * @author Sasi Kumar
 * @since 1.0
 */
public class DeDup {

	/**
	 * Array of random integers (may include duplicates)
	 */
	public int[] randomIntegers = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000, 11, 16, 19, 1,
			18, 4, 9, 3, 20, 17, 8, 15, 6, 2, 5, 10, 14, 12, 13, 7, 8, 9, 1, 2, 15, 12, 18, 10, 14, 20, 17, 16, 3, 6,
			19, 13, 5, 11, 4, 7, 19, 16, 5, 9, 12, 3, 20, 7, 15, 17, 10, 6, 1, 8, 18, 4, 14, 13, 2, 11 };

	/**
	 * Default constructor.
	 */
	public DeDup() {
	}

	/**
	 * Provide different input to test the implementation from JUnit or other
	 * testing frameworks.
	 * 
	 * @param randomIntegers
	 */
	public DeDup(int[] randomIntegers) {
		this.randomIntegers = randomIntegers;
	}

	/**
	 * Remove duplicates in an int array by comparing elements with in.
	 * It uses two for loops to identify duplicate elements.
	 * 
	 * @return unique int array
	 */
	public int[] deDupBySimpleLooping() {
		// Unique array, length assuming no duplicates
		int[] uniqueIntegers = new int[randomIntegers.length];
		
		// Loop through original array
		int uniqueIntCount = 0;
		for (int outerIndex = 0; outerIndex < randomIntegers.length; outerIndex++) {
			boolean isDuplicate = false;
			for (int innerIndex = 0; innerIndex < uniqueIntCount; innerIndex++) {
				if (randomIntegers[outerIndex] == uniqueIntegers[innerIndex]) {
					isDuplicate = true;
					break;
				}
			}
			
			// If no duplicates found, add element to unique array
			if (!isDuplicate) {
				uniqueIntegers[uniqueIntCount++] = randomIntegers[outerIndex];
			}
		}
		
		// Remove empty elements before returning unique array 
		return Arrays.copyOf(uniqueIntegers, uniqueIntCount);
	}

	/**
	 * Remove duplicates using java collection classes <code>HashSet/LinkedHashSet</code>
	 * Use <code>LinkedHashSet</code> in order to preserve the original order of elements.
	 * 
	 * @return unique int array
	 */
	public int[] deDupWithOriginalOrder() {
		// Initialize set to hold unique elements
		Set<Integer> uniqueIntSet = new LinkedHashSet<Integer>();

		// Add array elements to the set
		for (int index = 0; index < randomIntegers.length; index++) {
			uniqueIntSet.add(randomIntegers[index]);
		}
		
		// Unique int array with actual size
		int[] uniqueIntegers = new int[uniqueIntSet.size()];
		
		// Build array with set values
		int index = 0;
		for (Iterator<Integer> uniqueIntIterator = uniqueIntSet.iterator(); uniqueIntIterator.hasNext();) {
			uniqueIntegers[index++] = uniqueIntIterator.next();
		}
		
		return uniqueIntegers;
	}

	/**
	 * Use java <code>IntStream</code> to identify distinct elements in array
	 * @return unique int array
	 */
	public int[] deDupWithIntStream() {
		return IntStream.of(randomIntegers).parallel().distinct().toArray();
	}
	
	
	/**
	 * Test DeDup implementations
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DeDup deDup = new DeDup();
		
		// Print original array
		System.out.println(String.format("%-18s","Original Array: ") +Arrays.toString(deDup.randomIntegers)+ "\n");
		
		// Print unique array - deDupBySimpleLooping
		System.out.println(String.format("%-18s","Simple looping: ") +Arrays.toString(deDup.deDupBySimpleLooping()));
		
		// Print unique array - deDupWithOriginalOrder
		System.out.println(String.format("%-18s","Orignal order: ") +Arrays.toString(deDup.deDupWithOriginalOrder()));
		
		// Print unique array - deDupWithIntStream
		System.out.println(String.format("%-18s","Int stream: ") +Arrays.toString(deDup.deDupWithIntStream()));
	}

}
