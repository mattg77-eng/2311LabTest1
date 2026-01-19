package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import model.ListUtilities;

/*
 * Study carefully the test methods below. They suggest:
 * 	- the required class(es) and method(s) to be implement in the `model` package
 * 	- how the required class(es) and method(s) should be implemented
 * 
 * Requirements:
 * 	+ Do not create any new class that is not required by the starter tests.
 *   
 * 	+ Any classes you create must reside in the `model` package and be imported properly.
 * 		For example, creating a new class `Foo` in the `model` package should result in:
 * 			import model.Foo;
 * 
 * 	+ For this assignment, you should not need to declare attributes. 
 * 		But if you really want to, all attributes you declare in the model classes must be private.
 * 
 * 	+ If necessary, you may define private helper methods. 
 */

public class StarterTests {
	/* 
	 * Programming Requirements:
	 * 
	 * 	- This assignment focuses on the manipulation of singly-linked nodes.
	 * 		Therefore, you are forbidden to use primitive arrays (e.g., Integer[], int[], String[]) 
	 * 		for declaring attributes or local variables.
	 * 	- In addition, any use of a Java library class or method is also forbidden 
	 * 		(that is, use selections and loops to build your solution from scratch instead):
	 * 	- Here are some examples of forbidden classes/methods: 
	 * 		- Arrays class (e.g., Arrays.copyOf)
	 * 		- System class (e.g., System.arrayCopy)
	 * 		- ArrayList class
	 * 		- String class (e.g., substring).
	 * 	- The use of some library classes does not require an import statement, 
	 * 		but these classes are also forbidden to be used.
	 * 	- Here are the exceptions (library methods which you are allowed to use if needed):
	 * 		- String class (equals, format, length)
	 * 
	 * Violating the above programming requirements will result in a penalty (see the assignment instructions for details). 
	 * 
	 * Tests included in this class serve as documentation on how instances of List Utilities operate.
	 * 
	 * Before attempting this assignment, 
	 * 	it is expected that you already completed background study materials as outlined in the assignment instructions. 
	 * 
	 * Be sure to also read the following sections from your Assignment 1 instructions PDF:
	 * 	- The `Requirements of this Assignment` section (page 3) 
	 * 	- Sections 0 and 1 on the recommended and required studies
	 * 	- Section 2 on the programming tasks. 
	 */ 
	
	@Test
	public void test_getAllPrefixes_01() {
		ListUtilities util = new ListUtilities();
		Node<Integer> input = 
			new Node<>(23, 
			new Node<>(46, 
			new Node<>(19, 
			new Node<>(-9, null))));
		
		/*
		 * Given an input chain of integers, return a chain of strings.
		 * 
		 * Each string in the output chain is a prefix (i.e., first few numbers) of the input chain,
		 * 	whose length is between the specified lower and upper bounds (inclusive at both ends).
		 * 
		 * Each prefix should be formatted as a comma-separated list of integers, surrounded by a pair of square brackets.
		 *  
		 * Hints:
		 * 	+ The length of the output chain is equal to the length of the input chain. 
		 * 	+ Elements in the output chain are ``sorted'' by the lengths of the prefixes 
		 * 		(e.g,. the first element is the prefix of length 1, the second element is the prefix of length 2).
		 * 
		 * Assumptions:
		 *  + The input chain contains at least one node in it.
		 * 	+ For each call of getAllPrefixes(input, m, n):
		 * 		* The input chain is not null and contains at least one node.
		 * 		* m <= number of nodes in the input chain
		 * 		* n <= number of nodes in the input chain
		 * 		* m <= n 
		 */
		
		/*
		 * getAllPrefixes(23 -> 46 -> 19 -> -9, 1, 4) returns a chain of all prefixes whose lengths are between 1 and 4.
		 */
		Node<String> output = util.getAllPrefixes(input, 1, 4);
		
		assertEquals("[23]", output.getElement());
		assertEquals("[23, 46]", output.getNext().getElement());
		assertEquals("[23, 46, 19]", output.getNext().getNext().getElement());
		assertEquals("[23, 46, 19, -9]", output.getNext().getNext().getNext().getElement()); 
		assertNull(output.getNext().getNext().getNext().getNext());
	}
	
	@Test
	public void test_getAllPrefixes_02() {
		ListUtilities util = new ListUtilities();
		Node<Integer> input = 
			new Node<>(23, 
			new Node<>(46, 
			new Node<>(19, 
			new Node<>(-9, null))));
		
		/*
		 * getAllPrefixes(23 -> 46 -> 19 -> -9, 2, 3) returns a chain of all prefixes whose lengths are between 2 and 3.
		 */
		Node<String> output = util.getAllPrefixes(input, 2, 3);
		
		assertEquals("[23, 46]", output.getElement());
		assertEquals("[23, 46, 19]", output.getNext().getElement()); 
		assertNull(output.getNext().getNext());
	}
	
	@Test
	public void test_getAllPrefixes_03() {
		ListUtilities util = new ListUtilities();
		Node<Integer> input = 
			new Node<>(23, 
			new Node<>(46, 
			new Node<>(19, 
			new Node<>(-9, null))));
		
		/*
		 * getAllPrefixes(23 -> 46 -> 19 -> -9, 3, 3) returns a chain of all prefixes whose lengths are between 3 and 3.
		 */
		Node<String> output = util.getAllPrefixes(input, 3, 3);
		
		assertEquals("[23, 46, 19]", output.getElement());
		assertNull(output.getNext()); 
	}
	
	@Test
	public void test_getMergedList_01() {
		ListUtilities util = new ListUtilities();
		Node<Integer> leftChain = 
			new Node<>(1, 
			new Node<>(3, 
			new Node<>(5, 
			new Node<>(7, 
			new Node<>(9, null)))));
		Node<Integer> rightChain = 
			new Node<>(2, 
			new Node<>(3, 
			new Node<>(5, 
			new Node<>(6, 
			new Node<>(8, null)))));
		
		/* 
		 * Merging two non-empty chains, each sorted in a non-descending order, 
		 * 	results in a new chain containing elements from both chains and sorted in a non-descending order.
		 * 
		 * Assumption: 
		 * 	- Each of the input chains to the getMergedChain method, if not null, is sorted in a non-descending order. 
		 */  
		Node<Integer> output = util.getMergedChain(leftChain, rightChain);
		
		assertTrue(1 == output.getElement());
		assertTrue(2 == output.getNext().getElement());
		assertTrue(3 == output.getNext().getNext().getElement());
		assertTrue(3 == output.getNext().getNext().getNext().getElement());
		assertTrue(5 == output.getNext().getNext().getNext().getNext().getElement());
		assertTrue(5 == output.getNext().getNext().getNext().getNext().getNext().getElement());
		assertTrue(6 == output.getNext().getNext().getNext().getNext().getNext().getNext().getElement());
		assertTrue(7 == output.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getElement());
		assertTrue(8 == output.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getElement());
		assertTrue(9 == output.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getElement());
		assertNull(output.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext());
		/*
		 * The input and output chains do not share any node references in common.
		 * i.e., there is no reference aliasing.
		 */
		assertTrue(noAliasing(output, leftChain));
		assertTrue(noAliasing(output, rightChain));
	}
	 
	@Test
	public void test_getMergedList_02() {
		ListUtilities util = new ListUtilities();
		Node<Integer> leftChain = null; /* empty chain of nodes */
		Node<Integer> rightChain = null; /* empty chain of nodes */
		
		/* Merging two empty chains results in another empty chain. */
		assertNull(null, util.getMergedChain(leftChain, rightChain));
	}
	
	@Test
	public void test_getMergedList_03() {
		ListUtilities util = new ListUtilities();
		Node<Integer> leftChain = 
			new Node<>(1, 
			new Node<>(3, 
			new Node<>(5, 
			new Node<>(7, 
			new Node<>(9, null)))));
		Node<Integer> rightChain = null; /* empty chain of nodes */
		
		/* Merging a non-empty chain and an empty chain results in elements drawn from the non-empty chain. */  
		Node<Integer> output = util.getMergedChain(leftChain, rightChain);
		
		assertTrue(1 == output.getElement());
		assertTrue(3 == output.getNext().getElement());
		assertTrue(5 == output.getNext().getNext().getElement());
		assertTrue(7 == output.getNext().getNext().getNext().getElement());
		assertTrue(9 == output.getNext().getNext().getNext().getNext().getElement());
		assertNull(output.getNext().getNext().getNext().getNext().getNext());
		/*
		 * The input and output chains do not share any node references in common.
		 * i.e., there is no reference aliasing.
		 */
		assertTrue(noAliasing(output, leftChain));
	}
	
	@Test
	public void test_getMergedList_04() {
		ListUtilities util = new ListUtilities();
		Node<Integer> leftChain = null; /* empty chain of nodes */
		Node<Integer> rightChain = 
			new Node<>(2, 
			new Node<>(3, 
			new Node<>(5, 
			new Node<>(6, 
			new Node<>(8, null)))));
		
		/* Merging a non-empty chain and an empty chain results in elements drawn from the non-empty chain. */  
		Node<Integer> output = util.getMergedChain(leftChain, rightChain);
		
		assertTrue(2 == output.getElement());
		assertTrue(3 == output.getNext().getElement());
		assertTrue(5 == output.getNext().getNext().getElement());
		assertTrue(6 == output.getNext().getNext().getNext().getElement());
		assertTrue(8 == output.getNext().getNext().getNext().getNext().getElement());
		assertNull(output.getNext().getNext().getNext().getNext().getNext());
		/*
		 * The input and output chains do not share any node references in common.
		 * i.e., there is no reference aliasing.
		 */
		assertTrue(noAliasing(output, rightChain));
	}
	
	/*
	 * Jackie's suggestions for testing further: 
	 * 	As you will also be graded with additional tests, you may want to write more test methods to check cases where, 
	 * 	e.g., 
	 * 		1. One input chain is much longer than the other.
	 * 		2. One input chain contain values larger than all those contained in the other chain.
	 */
	
	@Test
	public void test_getInterleavingArithmeticFibonacciSeq_01a() {
		ListUtilities util = new ListUtilities();
		
		/*
		 * Get a sequence interleaving elements from:
		 * 	- An arithmetic sequence, starting at 5 with a common difference 3, of size 4.
		 * 	- A Fibonacci sequence of size 1.
		 * 	  Recall. A Fibonacci sequence is infinite: <1, 1, 2, 3, 5, 8, 13, ...>  
		 * (The interleaving starts with one element from the arith. seq., then one element from the Fib. seq., and so on.)
		 */
		Node<Integer> output = util.getInterleavedArithmeticFibonacciSequences(5, 3, 4, 1);
		
		assertTrue(5 == output.getElement()); // 1st element from arith. seq.
		assertTrue(1 == output.getNext().getElement()); // only element from Fib. seq.
		assertTrue(8 == output.getNext().getNext().getElement()); // 2nd element from arith. seq.
		assertTrue(11 == output.getNext().getNext().getNext().getElement()); // 3rd element from arith. seq.
		assertTrue(14 == output.getNext().getNext().getNext().getNext().getElement()); // 4th element from arith. seq.
		assertNull(output.getNext().getNext().getNext().getNext().getNext());
	}
	
	@Test
	public void test_getInterleavingArithmeticFibonacciSeq_01b() {
		ListUtilities util = new ListUtilities();
		/*
		 * Get a sequence interleaving elements from:
		 * 	- An arithmetic sequence, starting at 5 with a common difference 3, of size 4.
		 * 	- A Fibonacci sequence of size 2.
		 * 	  Recall. A Fibonacci sequence is infinite: <1, 1, 2, 3, 5, 8, 13, ...>
		 * (The interleaving starts with one element from the arith. seq., then one element from the Fib. seq., and so on.)  
		 */
		Node<Integer> output = util.getInterleavedArithmeticFibonacciSequences(5, 3, 4, 2);
		
		assertTrue(5 == output.getElement()); // 1st element from arith. seq.
		assertTrue(1 == output.getNext().getElement()); // 1st element from Fib. seq.
		assertTrue(8 == output.getNext().getNext().getElement()); // 2nd element from arith. seq.
		assertTrue(1 == output.getNext().getNext().getNext().getElement()); // 2nd element from Fib. seq.
		assertTrue(11 == output.getNext().getNext().getNext().getNext().getElement()); // 3rd element from arith. seq.
		assertTrue(14 == output.getNext().getNext().getNext().getNext().getNext().getElement()); // 4th element from arith. seq.
		assertNull(output.getNext().getNext().getNext().getNext().getNext().getNext());
	}
	
	@Test
	public void test_getInterleavingArithmeticFibonacciSeq_01c() {
		ListUtilities util = new ListUtilities();
		/*
		 * Get a sequence interleaving elements from:
		 * 	- An arithmetic sequence, starting at 5 with a common difference 3, of size 4.
		 * 	- A Fibonacci sequence of size 5.
		 * 	  Recall. A Fibonacci sequence is infinite: <1, 1, 2, 3, 5, 8, 13, ...>
		 * (The interleaving starts with one element from the arith. seq., then one element from the Fib. seq., and so on.)  
		 */
		Node<Integer> output = util.getInterleavedArithmeticFibonacciSequences(5, 3, 4, 5);
		
		assertTrue(5 == output.getElement()); // 1st element from arith. seq.
		assertTrue(1 == output.getNext().getElement()); // 1st element from Fib. seq.
		assertTrue(8 == output.getNext().getNext().getElement()); // 2nd element from arith. seq.
		assertTrue(1 == output.getNext().getNext().getNext().getElement()); // 2nd element from Fib. seq.
		assertTrue(11 == output.getNext().getNext().getNext().getNext().getElement()); // 3rd element from arith. seq.
		assertTrue(2 == output.getNext().getNext().getNext().getNext().getNext().getElement()); // 3rd element from Fib. seq.
		assertTrue(14 == output.getNext().getNext().getNext().getNext().getNext().getNext().getElement()); // 4th element from arith. seq.
		assertTrue(3 == output.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getElement()); // 4th element from Fib. seq.
		assertTrue(5 == output.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getElement()); // 5th element from arith. seq.
		assertNull(output.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext());
	}
	
	@Test
	public void test_getInterleavingArithmeticFibonacciSeq_02() {
		ListUtilities util = new ListUtilities();
		/*
		 * Get a sequence interleaving elements from:
		 * 	- An arithmetic sequence, starting at 5 with a common difference 3, of size 0.
		 * 	- A Fibonacci sequence of size 0.
		 * In this special case, the resulting interleaving is just empty.
		 */
		Node<Integer> output = util.getInterleavedArithmeticFibonacciSequences(5, 3, 0, 0);
		
		assertNull(output);
	}
	
	@Test
	public void test_getInterleavingArithmeticFibonacciSeq_03() {
		ListUtilities util = new ListUtilities();
		/*
		 * Get a sequence interleaving:
		 * 	- An arithmetic sequence, starting at 5 with a common difference 3, of size 4.
		 * 	- A Fibonacci sequence of size 0.
		 * In this special case, the resulting interleaving is just the arithmetic sequence.
		 */
		Node<Integer> output = util.getInterleavedArithmeticFibonacciSequences(5, 3, 4, 0);
		
		assertTrue(5 == output.getElement());
		assertTrue(8 == output.getNext().getElement());
		assertTrue(11 == output.getNext().getNext().getElement());
		assertTrue(14 == output.getNext().getNext().getNext().getElement());
		assertNull(output.getNext().getNext().getNext().getNext());
	}
	
	@Test
	public void test_getInterleavingArithmeticFibonacciSeq_04a() {
		ListUtilities util = new ListUtilities();
		/*
		 * Get a sequence interleaving:
		 * 	- An arithmetic sequence, starting at 5 with a common difference 3, of size 0.
		 * 	- A Fibonacci sequence of size 1.
		 * 	  Recall. A Fibonacci sequence is infinite: <1, 1, 2, 3, 5, 8, 13, ...>
		 * 
		 * In this special case, since the size of the arithmetic seq. is 0, 
		 * 	the resulting sequence is just the Fibonacci seq. of size 1.  
		 */
		Node<Integer> output = util.getInterleavedArithmeticFibonacciSequences(5, 3, 0, 1);
		
		assertTrue(1 == output.getElement());
		assertNull(output.getNext());
	}
	
	@Test
	public void test_getInterleavingArithmeticFibonacciSeq_04b() {
		ListUtilities util = new ListUtilities();
		/*
		 * Get a sequence interleaving:
		 * 	- An arithmetic sequence, starting at 5 with a common difference 3, of size 0.
		 * 	- A Fibonacci sequence of size 2.
		 * 	  Recall. A Fibonacci sequence is infinite: <1, 1, 2, 3, 5, 8, 13, ...>
		 * 
		 * In this special case, since the size of the arithmetic seq. is 0, 
		 * 	the resulting sequence is just the Fibonacci seq. of size 2.
		 */
		Node<Integer> output = util.getInterleavedArithmeticFibonacciSequences(5, 3, 0, 2);
		
		assertTrue(1 == output.getElement());
		assertTrue(1 == output.getNext().getElement());
		assertNull(output.getNext().getNext());
	}
	
	@Test
	public void test_getInterleavingArithmeticFibonacciSeq_04c() {
		ListUtilities util = new ListUtilities();
		/*
		 * Get a sequence interleaving:
		 * 	- An arithmetic sequence, starting at 5 with a common difference 3, of size 0.
		 * 	- A Fibonacci sequence of size 5.
		 * 	  Recall. A Fibonacci sequence is infinite: <1, 1, 2, 3, 5, 8, 13, ...>
		 *   
		 * In this special case, since the size of the arithmetic seq. is 0, 
		 * 	the resulting sequence is just the Fibonacci seq. of size 5.  
		 */
		Node<Integer> output = util.getInterleavedArithmeticFibonacciSequences(5, 3, 0, 5);
		
		assertTrue(1 == output.getElement());
		assertTrue(1 == output.getNext().getElement());
		assertTrue(2 == output.getNext().getNext().getElement());
		assertTrue(3 == output.getNext().getNext().getNext().getElement());
		assertTrue(5 == output.getNext().getNext().getNext().getNext().getElement());
		assertNull(output.getNext().getNext().getNext().getNext().getNext());
	}
	
	/*
	 * Jackie's suggestions for testing further: 
	 * 	As you will also be graded with additional tests, you may want to write more test methods to check cases where, 
	 * 	e.g., 
	 * 		1. The arithmetic sequence is much longer than the Fibonacci sequence.
	 * 		2. The Fibonacci sequence is much longer than the Arithmetic sequence.
	 */
	
	@Test
	public void test_getGroupedStrings_01() {
		ListUtilities util = new ListUtilities();
		
		Node<String> input = 
			new Node<>("vwxyzj", 
			new Node<>("xy", 
			new Node<>("ghic", 
			new Node<>("pqrstu", 
			new Node<>("def", 
			new Node<>("bc", 
			new Node<>("a", 
			new Node<>("", null))))))));
		/*
		 * Calling getGroupedStrings(input, m, n) returns a chain of nodes 
		 * 	which groups all elements from the input chain as follows, from left to right:
		 * 	Group 1: strings whose lengths are less than m
		 *  Group 2: strings whose lengths are greater than or equal to m and less than n
		 *  Group 3: strings whose lengths are greater than or equal to n
		 * 
		 * Requirements:
		 * 	- The input and output chains are equally long.
		 * 	- Each group in the output chain preserves the order in which its elements appear in the input chain.
		 * 
		 * Assumptions:
		 * 	- Assume that m <= n. 
		 * 	- When m = n, it means that group2 is empty. 
		 * 		(say m = n = 3: there is no string whose length is >= 3 and < 3).
		 */
		
		Node<String> output = util.getGroupedStrings(input, 2, 4); 
		
		/* 
		 * Group 1: strings from the input chain whose lengths are 
		 * 	less than 2 (i.e., 0, 1) 
		 */
		assertEquals("a"		, output.getElement());
		assertEquals(""			, output.getNext().getElement());
		/* 
		 * Group 2: strings from the input chain whose lengths are 
		 * 	greater than or equal to 2 and less than 4 (i.e., 2, 3) 
		 */
		assertEquals("xy"		, output.getNext().getNext().getElement());
		assertEquals("def"		, output.getNext().getNext().getNext().getElement());
		assertEquals("bc"		, output.getNext().getNext().getNext().getNext().getElement());
		/* 
		 * Group 3: strings from the input chain whose lengths are 
		 * 	greater than or equal to 4 (i.e., 4, 5, ...) 
		 */
		assertEquals("vwxyzj"	, output.getNext().getNext().getNext().getNext().getNext().getElement());
		assertEquals("ghic"		, output.getNext().getNext().getNext().getNext().getNext().getNext().getElement());
		assertEquals("pqrstu"	, output.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getElement());
		
		assertNull(output.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext());
		
		/*
		 * The input and output chains do not share any node references in common.
		 * i.e., there is no reference aliasing.
		 */
		assertTrue(noAliasing(input, output));
	}
	
	@Test
	public void test_getGroupedStrings_02() {
		ListUtilities util = new ListUtilities();
		
		Node<String> input = null;
		Node<String> output = util.getGroupedStrings(input, 2, 4); 
		
		/*
		 * Given that the input chain contains no strings, so does the output chain. 
		 */
		assertNull(output);
	}
	
	/*
	 * Jackie's suggestions for testing further: 
	 * 	As you will also be graded with additional tests, you may want to write more test methods to check cases where, 
	 * 	e.g., 
	 * 		1. The input chain contains no numbers in some group(s). 
	 * 			e.g., getGroupedStrings(input, m, n) where no strings in `input` has length less than m,
	 * 					meaning that the 1st group will be absent in the output chain.
	 */
	
	/*
	 * A generic helper method used by some JUnit tests methods.
	 * This method makes sure that the two chains of nodes, starting at `n1` and `n2`,
	 * 	do not overlap in their node references,
	 * i.e., The reference of each node in chain 1 (starting at `n1`)
	 * 			is not equal to the reference of any node in chain 2 (starting at `n2`).
	 */
	private <E> boolean noAliasing(Node<E> n1, Node<E> n2) {
		Node<E> current1 = n1;
		Node<E> current2 = n2;
		boolean found = false;
		while(current1 != null && !found) {
			while(current2 != null && !found) {
				found = current1 == current2;
				current2 = current2.getNext();
			}
			current1 = current1.getNext();
			current2 = n2;
		}
		return !found;
	}
}