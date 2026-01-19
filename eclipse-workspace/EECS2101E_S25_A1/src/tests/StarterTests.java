package tests;

import model.RecursiveMethods;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

/*
 * Requirements:
 * 	+ Infer from the JUnit tests about the required class(es) and method(s).
 * 	+ Any classes you create must be placed in the `model` package.
 * 	+ You may add as many helper methods as needed.
 */

public class StarterTests {
	
	/*
	 * Starter tests related to Task 1.
	 * 
	 * (The starter tests are not meant to cover all scenarios:
	 *  you are responsible for writing additional tests to assess your software
	 *  as thoroughly as possible before the submission deadline.)
	 * 
	 * Input: A string `in_string`
	 * Output: A string `out_string`
	 * Problem: 
	 * 	Given `in_string` which contains a single pair of parentheses, compute recursively and 
	 * 	return `out_string` made of only the pair of parentheses and their enclosed characters. 
	 */
	@Test
	public void testTask1_01() {
		String input = "xyz(abc)123";
		String output = "(abc)";
		RecursiveMethods rm = new RecursiveMethods();
		assertEquals(output, rm.task1(input));
	}
	
	@Test
	public void testTask1_02() {
		String input = "x(hello)";
		String output = "(hello)";
		RecursiveMethods rm = new RecursiveMethods();
		assertEquals(output, rm.task1(input));
	}
	
	@Test
	public void testTask1_03() {
		String input = "(xy)1";
		String output = "(xy)";
		RecursiveMethods rm = new RecursiveMethods();
		assertEquals(output, rm.task1(input));
	}
	
	/*
	 * Starter tests related to Task 2.
	 * 
	 * (The starter tests are not meant to cover all scenarios:
	 *  you are responsible for writing additional tests to assess your software
	 *  as thoroughly as possible before the submission deadline.)
	 * 
	 * Inputs: An array `a` of integers and an integer `target`
	 * Output: A boolean `result`
	 * Problem: 
	 * 	Given an array `a` of integers, compute recursively and
	 * 	return a boolean `result` which indicates 
	 * 	whether or not it's possible to choose a group of some of the integers in `a`, 
	 * 	such that this chosen group of integers sums up to the given `target`.   
	 * 
	 * Hint: You should not need to use a loop.  
	 */
	
	@Test
	public void testTask2_01() {
		int[] input = {2, 4, 8};
		RecursiveMethods rm = new RecursiveMethods();
		// True because group consisting of 2 and 8 sum up to the target.
		assertEquals(true, rm.task2(input, 10));
	}
	
	@Test
	public void testTask2_02() {
		int[] input = {2, 4, 8};
		RecursiveMethods rm = new RecursiveMethods();
		// True because group consisting of 2, 4, 8 sum up to the target.
		assertEquals(true, rm.task2(input, 14));
	}
	
	@Test
	public void testTask2_03() {
		int[] input = {2, 4, 8};
		RecursiveMethods rm = new RecursiveMethods();
		// True because group consisting of 2 and 4 sum up to the target.
		assertEquals(true, rm.task2(input, 6));
	}
	
	@Test
	public void testTask2_04() {
		int[] input = {2, 4, 8};
		RecursiveMethods rm = new RecursiveMethods();
		// False because no groups from {2, 4, 8} sum up to the target.
		assertEquals(false, rm.task2(input, 9));
	}
	
	/*
	 * Starter tests related to Task 3.
	 * 
	 * (The starter tests are not meant to cover all scenarios:
	 *  you are responsible for writing additional tests to assess your software
	 *  as thoroughly as possible before the submission deadline.)
	 * 
	 * Inputs: An integer `h` (height of a staircase) and an integer `n` (maximum steps of each climb)
	 * Output: An integer `result`
	 * Problem: 
	 * 	Given `h` and `n`, compute recursively and
	 * 	return an integer `result` which indicates 
	 * 	the number of possible ways for climbing a staircase of height `h`, 
	 * 	while each climb is **up to** `n` steps.    
	 * 
	 * Assumption: n <= h, each climb takes at least 1 step
	 */
	
	@Test
	public void testTask3_01() {
	    RecursiveMethods rm = new RecursiveMethods(); 
	    /*
	     * There is only 1 possible way for climbing a staircase of height 4, 
	     * while each climb takes 1 step.
	     * 1. 1 step, 1 step, 1 step, 1 step 
	     */
	    assertEquals(1, rm.task3(4, 1));
	}
	
	@Test
	public void testTask3_02() {
	    RecursiveMethods rm = new RecursiveMethods(); 
	    /*
	     * There are 5 possible ways for climbing a staircase of height 4, 
	     * while each climb takes either 1 or 2 steps.
	     * 1. 1 step, 1 step, 1 step, 1 step
	     * 2. 1 step, 1 step, 2 steps
	     * 3. 1 step, 2 steps, 1 step
	     * 4. 2 steps, 1 step, 1 step
	     * 5. 2 steps, 2 steps
	     */
	    assertEquals(5, rm.task3(4, 2));
	}
	
	@Test
	public void testTask3_03() {
	    RecursiveMethods rm = new RecursiveMethods(); 
	    /*
	     * There are 5 possible ways for climbing a staircase of height 4, 
	     * while each climb takes either 1, 2, or 3 steps.
	     * 1. 1 step, 1 step, 1 step, 1 step
	     * 2. 1 step, 1 step, 2 steps
	     * 3. 1 step, 2 steps, 1 step
	     * 4. 1 step, 3 steps
	     * 5. 2 steps, 1 step, 1 step
	     * 6. 2 steps, 2 steps
	     * 7. 3 steps, 1 step
	     */
	    assertEquals(7, rm.task3(4, 3));
	}
	
	/*
	 * Starter tests related to Task 4.
	 * 
	 * (The starter tests are not meant to cover all scenarios:
	 *  you are responsible for writing additional tests to assess your software
	 *  as thoroughly as possible before the submission deadline.)
	 * 
	 * Inputs of this task are the same as those of Task 3, 
	 * with the same assumptions: n <= h, each climb takes at least 1 step.
	 * 
	 * Output of this task is a HashSet, where each element is an ArrayList.
	 * Each ArrayList encodes a strategy for climbing the staircase.
	 */
	
	@Test
	public void testTask4_01() {
	    RecursiveMethods rm = new RecursiveMethods(); 
	    /*
	     * There is only 1 possible way for climbing a staircase of height 4, 
	     * while each climb takes 1 step.
	     * 1. 1 step, 1 step, 1 step, 1 step 
	     */
	    HashSet<ArrayList<Integer>> output = rm.task4(4, 1);
	    assertEquals(1, output.size());
	    assertTrue(output.contains(Arrays.asList(1, 1, 1, 1)));
	}
	
	@Test
	public void testTask4_02() {
	    RecursiveMethods rm = new RecursiveMethods(); 
	    /*
	     * There are 5 possible ways for climbing a staircase of height 4, 
	     * while each climb takes either 1 or 2 steps.
	     * 1. 1 step, 1 step, 1 step, 1 step
	     * 2. 1 step, 1 step, 2 steps
	     * 3. 1 step, 2 steps, 1 step
	     * 4. 2 steps, 1 step, 1 step
	     * 5. 2 steps, 2 steps
	     */
	    HashSet<ArrayList<Integer>> output = rm.task4(4, 2);
	    assertEquals(5, output.size());
	    assertTrue(output.contains(Arrays.asList(1, 1, 1, 1)));
	    assertTrue(output.contains(Arrays.asList(1, 1, 2)));
	    assertTrue(output.contains(Arrays.asList(1, 2, 1)));
	    assertTrue(output.contains(Arrays.asList(2, 1, 1)));
	    assertTrue(output.contains(Arrays.asList(2, 2)));
	}
	
	@Test
	public void testTask4_03() {
	    RecursiveMethods rm = new RecursiveMethods(); 
	    /*
	     * There are 5 possible ways for climbing a staircase of height 4, 
	     * while each climb takes either 1, 2, or 3 steps.
	     * 1. 1 step, 1 step, 1 step, 1 step
	     * 2. 1 step, 1 step, 2 steps
	     * 3. 1 step, 2 steps, 1 step
	     * 4. 1 step, 3 steps
	     * 5. 2 steps, 1 step, 1 step
	     * 6. 2 steps, 2 steps
	     * 7. 3 steps, 1 step
	     */
	    HashSet<ArrayList<Integer>> output = rm.task4(4, 3);
	    assertEquals(7, output.size());
	    assertTrue(output.contains(Arrays.asList(1, 1, 1, 1)));
	    assertTrue(output.contains(Arrays.asList(1, 1, 2)));
	    assertTrue(output.contains(Arrays.asList(1, 2, 1)));
	    assertTrue(output.contains(Arrays.asList(1, 3)));
	    assertTrue(output.contains(Arrays.asList(2, 1, 1)));
	    assertTrue(output.contains(Arrays.asList(2, 2)));
	    assertTrue(output.contains(Arrays.asList(3, 1)));
	} 
	
	// Additional Test Cases
	// ----------------------------

	@Test
	public void testTask1_Extra01() {
	    RecursiveMethods rm = new RecursiveMethods();
	    assertEquals("(a)", rm.task1("(a)"));
	}

	@Test
	public void testTask2_Extra01() {
	    RecursiveMethods rm = new RecursiveMethods();
	    int[] input = {-3, 1, 4};
	    assertTrue(rm.task2(input, 1));
	}

	@Test
	public void testTask3_Extra01() {
	    RecursiveMethods rm = new RecursiveMethods();
	    assertEquals(1, rm.task3(0, 3));
	}
}
