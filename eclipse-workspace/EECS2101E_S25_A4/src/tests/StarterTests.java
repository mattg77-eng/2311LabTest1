package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.TreeUtilities;

/*
 * Study carefully the test methods below. They suggest:
 * 	- the required class(es) and method(s) to be implement in the `model` package
 * 	- how the required class(es) and method(s) should be implemented
 * 
 * Requirements:
 * 	+ Do ***not*** create any new class that is not required by the starter tests.
 * 		All such classes will be ***disregarded*** when grading. 
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
	 * 	- This assignment focuses on the manipulation of: 
	 * 		+ linked-node based trees (see the given TreeNode class) 
	 * 		+ singly-linked nodes (see the given SLLNode class)
	 * 
	 * 	  Therefore, you are forbidden to use primitive arrays (e.g., Integer[], int[], String[]) 
	 * 		for declaring attributes or local variables. Use only the TreeNode and SLLNode classes given to you.
	 * 
	 * 	- In addition, any use of a Java library class or method is also forbidden 
	 * 		(that is, use selections and loops to build your solution from scratch instead):
	 * 
	 * 	- Here are some examples of forbidden classes/methods: 
	 * 		- Arrays class (e.g., Arrays.copyOf)
	 * 		- System class (e.g., System.arrayCopy)
	 * 		- ArrayList class
	 * 		- String class (e.g., substring).
	 * 
	 * 	- The use of some library classes does not require an import statement, 
	 * 		but these classes are also forbidden to be used.
	 * 	- Here are the exceptions (library methods which you are allowed to use if needed):
	 * 		- String class (equals, format, length, charAt)
	 * 
	 * Violating the above programming requirements will result in a penalty (see the assignment instructions for details). 
	 * 
	 * Tests included in this class serve as documentation on how instances of Tree Utilities operate.
	 * 
	 * Before attempting this assignment, 
	 * 	it is expected that you already completed background study materials as outlined in the assignment instructions. 
	 * 
	 * Be sure to also read the following sections from your Assignment 1 instructions PDF:
	 * 	- The `Requirements of this Assignment` section (page 3) 
	 * 	- Sections 0 and 1 on the background studies 
	 */ 
	
	/*
	 * Be sure to study how the TreeNode and SLLNode classes are supposed to work together
	 * 	as illustrated in the TestGeneralTrees JUnit class.
	 */
	
	/*
	 * Tests related to getInfixTree
	 */
	
	@Test
	public void test_getInfixTree_00() {
		TreeUtilities u = new TreeUtilities();
		
		Expression operand1 = new Operand(23);
		
		/* Create a postfix expression "23" */
		SLLNode<Expression> expression = new SLLNode<>(operand1, null);
		
		/*
		 * Input of `getInfixTree`: a chain of SLLNode objects representing a postfix expression
		 * Output: a TreeNode object, which is the root of a tree whose in-order-traversal corresponds to an infix expression that is equivalent to the input postfix expression
		 * 
		 * Assumptions:
		 * 	+ Only these operators will appear on inputs: `+` (addition), `-` (subtraction), `*` (multiplication).
		 * 	+ Operand values are integers (negative, zero, positive). 
		 * 	+ The input postfix expression is not null and it is valid: no error handling is needed.
		 * 	+ An inheritance hierarchy exists among the given classes `Expression`, `Operator`, and `Operand` in the `tests` package.
		 * 
		 * Requirements:
		 * 	+ You must use the provided classes SLLNode and TreeNode (without modifying them) in the `tests` package.
		 * 	+ No extra external/internal classes beyond what is required by the StarterTests (i.e., TreeUtilities) are allowed.
		 * 
		 * Background:
		 * 	+ Refer to the lecture materials on how postfix expressions should be calculated.
		 * 	+ If you wish to use a stack or queue in the TreeUtilities, the programming requirements apply:
		 * 		- No primitive arrays
		 * 		- No library classes
		 * 		- No additional inner/external classes
		 * 	  Instead, use helper methods or attributes/variables for implementation.
		 */
		
		/* 
		 * Expected to return a tree storing the infix expression `23` 
		 */
		TreeNode<Expression> root = u.getInfixTree(expression);
		
		/*
		 * Hint: Visualize what tree structure is being asserted by the tests.
		 * See TestGeneralTrees on how to use SLLNode and TreeNode. 
		 */
		assertTrue(root.getElement() == operand1);
		assertNull(root.getParent());
		assertNull(root.getChildren());
	}
	
	@Test
	public void test_getInfixTree_01() {
		TreeUtilities u = new TreeUtilities();
		
		Expression operand1 = new Operand(23);
		Expression operand2 = new Operand(46);
		Expression operator1 = new Operator('+');
		
		/* Create a postfix expression "23 46 +" */
		SLLNode<Expression> expression = 
			new SLLNode<>(operand1, 
			new SLLNode<>(operand2, 
			new SLLNode<>(operator1, null)));
		
		/* 
		 * Expected to return a tree storing the infix expression `23 + 46` 
		 */
		TreeNode<Expression> root = u.getInfixTree(expression);
		
		/*
		 * Hint: Visualize what tree structure is being asserted by the tests.
		 * See TestGeneralTrees on how to use SLLNode and TreeNode. 
		 */
		SLLNode<TreeNode<Expression>> levelOneChild1 = root.getChildren();
		SLLNode<TreeNode<Expression>> levelOneChild2 = levelOneChild1.getNext();
		assertNull(levelOneChild2.getNext());
		
		assertTrue(root.getElement() == operator1);
		assertTrue(levelOneChild1.getElement().getElement() == operand1);
		assertTrue(levelOneChild2.getElement().getElement() == operand2);
		
		assertNull(root.getParent());
		assertTrue(root == levelOneChild1.getElement().getParent());
		assertTrue(root == levelOneChild2.getElement().getParent());
		assertNull(levelOneChild1.getElement().getChildren());
		assertNull(levelOneChild2.getElement().getChildren());
	}
	
	@Test
	public void test_getInfixTree_02() {
		TreeUtilities u = new TreeUtilities();
		
		Expression operand1 = new Operand(23);
		Expression operand2 = new Operand(46);
		Expression operand3 = new Operand(69);
		Expression operator1 = new Operator('+');
		Expression operator2 = new Operator('*');
		
		/* Create a postfix expression "23 46 + 69 *" */
		SLLNode<Expression> expression = 
			new SLLNode<>(operand1, 
			new SLLNode<>(operand2, 
			new SLLNode<>(operator1, 
			new SLLNode<>(operand3, 
			new SLLNode<>(operator2, null)))));
		
		/* 
		 * Expected to return a tree storing the infix expression `(23 + 46) * 69`
		 * Note: The parentheses here are only meant to indicate the order of evaluation: they need not be stored in the output tree.  
		 */
		TreeNode<Expression> root = u.getInfixTree(expression);
		
		/*
		 * Hint: Visualize what tree structure is being asserted by the tests.
		 * See TestGeneralTrees on how to use SLLNode and TreeNode. 
		 */ 
		SLLNode<TreeNode<Expression>> levelOneChild1 = root.getChildren();
		SLLNode<TreeNode<Expression>> levelOneChild2 = levelOneChild1.getNext();
		assertNull(levelOneChild2.getNext());
		SLLNode<TreeNode<Expression>> levelTwoChild1 = levelOneChild1.getElement().getChildren();
		SLLNode<TreeNode<Expression>> levelTwoChild2 = levelTwoChild1.getNext();
		assertNull(levelTwoChild2.getNext());
		
		assertTrue(root.getElement() == operator2);
		assertTrue(levelOneChild1.getElement().getElement() == operator1);
		assertTrue(levelTwoChild1.getElement().getElement() == operand1);
		assertTrue(levelTwoChild2.getElement().getElement() == operand2);
		assertTrue(levelOneChild2.getElement().getElement() == operand3);
		
		assertNull(root.getParent());
		assertTrue(root == levelOneChild1.getElement().getParent());
		assertTrue(root == levelOneChild2.getElement().getParent());
		assertTrue(levelOneChild1.getElement() == levelTwoChild1.getElement().getParent());
		assertTrue(levelOneChild1.getElement() == levelTwoChild2.getElement().getParent());
		assertNull(levelTwoChild1.getElement().getChildren());
		assertNull(levelTwoChild2.getElement().getChildren());
		assertNull(levelOneChild2.getElement().getChildren()); 
	} 
	
	@Test
	public void test_getInfixTree_03() {
		TreeUtilities u = new TreeUtilities();
		
		Expression operand1 = new Operand(23);
		Expression operand2 = new Operand(46);
		Expression operand3 = new Operand(69);
		Expression operator1 = new Operator('+');
		Expression operator2 = new Operator('*');
		
		/* Create a postfix expression "23 46 69 * +" */
		SLLNode<Expression> expression = 
			new SLLNode<>(operand1, 
			new SLLNode<>(operand2, 
			new SLLNode<>(operand3,
			new SLLNode<>(operator2, 
			new SLLNode<>(operator1, null)))));
		
		/* 
		 * Expected to return a tree storing the infix expression `23 + (46 * 69)`
		 * Note: The parentheses here are only meant to indicate the order of evaluation: they need not be stored in the output tree. 
		 */
		TreeNode<Expression> root = u.getInfixTree(expression);
		
		/*
		 * Hint: Visualize what tree structure is being asserted by the tests.
		 * See TestGeneralTrees on how to use SLLNode and TreeNode. 
		 */  
		SLLNode<TreeNode<Expression>> levelOneChild1 = root.getChildren();
		SLLNode<TreeNode<Expression>> levelOneChild2 = levelOneChild1.getNext();
		assertNull(levelOneChild2.getNext());
		SLLNode<TreeNode<Expression>> levelTwoChild1 = levelOneChild2.getElement().getChildren();
		SLLNode<TreeNode<Expression>> levelTwoChild2 = levelTwoChild1.getNext();
		assertNull(levelTwoChild2.getNext());
		
		assertTrue(root.getElement() == operator1);
		assertTrue(levelOneChild1.getElement().getElement() == operand1);
		assertTrue(levelOneChild2.getElement().getElement() == operator2);
		assertTrue(levelTwoChild1.getElement().getElement() == operand2);
		assertTrue(levelTwoChild2.getElement().getElement() == operand3);
		
		assertNull(root.getParent());
		assertTrue(root == levelOneChild1.getElement().getParent());
		assertTrue(root == levelOneChild2.getElement().getParent());
		assertTrue(levelOneChild2.getElement() == levelTwoChild1.getElement().getParent());
		assertTrue(levelOneChild2.getElement() == levelTwoChild2.getElement().getParent());
		assertNull(levelOneChild1.getElement().getChildren());
		assertNull(levelTwoChild1.getElement().getChildren());
		assertNull(levelTwoChild2.getElement().getChildren());
	}
	
	@Test
	public void test_getInfixTree_04() {
		TreeUtilities u = new TreeUtilities();
		
		Expression operand1 = new Operand(23);
		Expression operand2 = new Operand(46);
		Expression operand3 = new Operand(69);
		Expression operand4 = new Operand(92);
		Expression operator1 = new Operator('-');
		Expression operator2 = new Operator('+');
		Expression operator3 = new Operator('*');
		
		/* Create a postfix expression "23 46 - 69 92 * +" */
		SLLNode<Expression> expression = 
			new SLLNode<>(operand1, 
			new SLLNode<>(operand2, 
			new SLLNode<>(operator1,
			new SLLNode<>(operand3, 
			new SLLNode<>(operand4, 
			new SLLNode<>(operator3, 
			new SLLNode<>(operator2, null)))))));
		
		/* 
		 * Expected to return a tree storing the infix expression `(23 - 46) + (69 * 92)`
		 * Note: The parentheses here are only meant to indicate the order of evaluation: they need not be stored in the output tree. 
		 */
		TreeNode<Expression> root = u.getInfixTree(expression);
		
		/*
		 * Hint: Visualize what tree structure is being asserted by the tests.
		 * See TestGeneralTrees on how to use SLLNode and TreeNode. 
		 */  
		SLLNode<TreeNode<Expression>> levelOneChild1 = root.getChildren();
		SLLNode<TreeNode<Expression>> levelOneChild2 = levelOneChild1.getNext();
		assertNull(levelOneChild2.getNext());
		SLLNode<TreeNode<Expression>> levelTwoChild1 = levelOneChild1.getElement().getChildren();
		SLLNode<TreeNode<Expression>> levelTwoChild2 = levelTwoChild1.getNext();
		assertNull(levelTwoChild2.getNext());
		SLLNode<TreeNode<Expression>> levelTwoChild3 = levelOneChild2.getElement().getChildren();
		SLLNode<TreeNode<Expression>> levelTwoChild4 = levelTwoChild3.getNext();
		assertNull(levelTwoChild4.getNext());
		
		assertTrue(root.getElement() == operator2);
		assertTrue(levelOneChild1.getElement().getElement() == operator1);
		assertTrue(levelTwoChild1.getElement().getElement() == operand1);
		assertTrue(levelTwoChild2.getElement().getElement() == operand2);
		assertTrue(levelOneChild2.getElement().getElement() == operator3);
		assertTrue(levelTwoChild3.getElement().getElement() == operand3);
		assertTrue(levelTwoChild4.getElement().getElement() == operand4);
		
		assertNull(root.getParent());
		assertTrue(root == levelOneChild1.getElement().getParent());
		assertTrue(root == levelOneChild2.getElement().getParent());
		assertTrue(levelOneChild1.getElement() == levelTwoChild1.getElement().getParent());
		assertTrue(levelOneChild1.getElement() == levelTwoChild2.getElement().getParent());
		assertTrue(levelOneChild2.getElement() == levelTwoChild3.getElement().getParent());
		assertTrue(levelOneChild2.getElement() == levelTwoChild4.getElement().getParent());
		assertNull(levelTwoChild1.getElement().getChildren());
		assertNull(levelTwoChild2.getElement().getChildren());
		assertNull(levelTwoChild3.getElement().getChildren());
		assertNull(levelTwoChild4.getElement().getChildren());
	}
	
	/*
	 * Tests related to getInfixSequence
	 */
	
	@Test
	public void test_getInfixSequence_00() {
		TreeUtilities u = new TreeUtilities();
		
		Expression operand1 = new Operand(23);
		
		SLLNode<Expression> expression = new SLLNode<>(operand1, null);
		
		/*
		 * Input of `getInfixSequence`: a chain of SLLNode objects representing a postfix expression
		 * Output: a string of an infix expression that is equivalent to the input postfix expression
		 * 
		 * Assumptions:
		 * 	+ Only these operators will appear on inputs: `+` (addition), `-` (subtraction), `*` (multiplication).
		 * 	+ Operand values are integers (negative, zero, positive). 
		 * 	+ The input postfix expression is not null and it is valid: no error handling is needed.
		 * 	+ An inheritance hierarchy exists among the given classes `Expression`, `Operator`, and `Operand` in the `tests` package.
		 * 
		 * Requirements:
		 * 	+ You must use the provided classes SLLNode and TreeNode (without modifying them) in the `tests` package.
		 * 	+ No extra external/internal classes beyond what is required by the StarterTests (i.e., TreeUtilities) are allowed.
		 * 
		 * Background:
		 * 	+ Refer to the lecture materials on how postfix expressions should be calculated.
		 * 	+ If you wish to use a stack or queue in the TreeUtilities, the programming requirements apply:
		 * 		- No primitive arrays
		 * 		- No library classes
		 * 		- No additional inner/external classes
		 * 	  Instead, use helper methods or attributes/variables for implementation.
		 */
		
		/* Expected to return a string storing the infix expression `23` */
		assertEquals("23", u.getInfixSequence(expression));
		/* 
		 * When the expression only involves a single operand, no parentheses are to be included. 
		 */
	}
	
	@Test
	public void test_getInfixSequence_01() {
		TreeUtilities u = new TreeUtilities();
		
		Expression operand1 = new Operand(23);
		Expression operand2 = new Operand(46);
		Expression operator1 = new Operator('+');
		
		/* Create a postfix expression "23 46 +" */
		SLLNode<Expression> expression = 
			new SLLNode<>(operand1, 
			new SLLNode<>(operand2, 
			new SLLNode<>(operator1, null)));
		
		/* Expected to return a string storing the infix expression `23 + 46` */
		assertEquals("(23 + 46)", u.getInfixSequence(expression));
		/* 
		 * When the expression involves at least an operator, an outer-most pair of parentheses is to be included. 
		 */
	}
	
	@Test
	public void test_getInfixSequence_02() {
		TreeUtilities u = new TreeUtilities();
		
		Expression operand1 = new Operand(23);
		Expression operand2 = new Operand(46);
		Expression operand3 = new Operand(69);
		Expression operator1 = new Operator('+');
		Expression operator2 = new Operator('*');
		
		/* Create a postfix expression "23 46 + 69 *" */
		SLLNode<Expression> expression = 
			new SLLNode<>(operand1, 
			new SLLNode<>(operand2, 
			new SLLNode<>(operator1, 
			new SLLNode<>(operand3, 
			new SLLNode<>(operator2, null)))));
		
		/* Expected to return a string storing the infix expression `(23 + 46) * 69`
		 * Note: The parentheses here are meant to indicate the order of evaluation: they need to be stored in the output string. 
		 */
		assertEquals("((23 + 46) * 69)", u.getInfixSequence(expression));
		/* 
		 * When the expression involves at least an operator, an outer-most pair of parentheses is to be included. 
		 */
	} 
	
	@Test
	public void test_getInfixSequence_03() {
		TreeUtilities u = new TreeUtilities();
		
		Expression operand1 = new Operand(23);
		Expression operand2 = new Operand(46);
		Expression operand3 = new Operand(69);
		Expression operator1 = new Operator('+');
		Expression operator2 = new Operator('*');
		
		/* Create a postfix expression "23 46 69 * +" */
		SLLNode<Expression> expression = 
			new SLLNode<>(operand1, 
			new SLLNode<>(operand2, 
			new SLLNode<>(operand3,
			new SLLNode<>(operator2, 
			new SLLNode<>(operator1, null)))));
		
		/* Expected to return a string storing the infix expression `23 + (46 * 69)`
		 * Notes: 
		 * 	+ The parentheses here are meant to indicate the order of evaluation: they need to be stored in the output string.
		 *  + For this task, we disregard operator precedences: always put a pair or parentheses to explicitly indicate the order of evaluation. 
		 */
		assertEquals("(23 + (46 * 69))", u.getInfixSequence(expression));
		/* 
		 * When the expression involves at least an operator, an outer-most pair of parentheses is to be included. 
		 */
	}
	
	@Test
	public void test_getInfixSequence_04() {
		TreeUtilities u = new TreeUtilities();
		
		Expression operand1 = new Operand(23);
		Expression operand2 = new Operand(46);
		Expression operand3 = new Operand(69);
		Expression operand4 = new Operand(92);
		Expression operator1 = new Operator('-');
		Expression operator2 = new Operator('+');
		Expression operator3 = new Operator('*');
		
		/* Create a postfix expression "23 46 - 69 92 * +" */
		SLLNode<Expression> expression = 
			new SLLNode<>(operand1, 
			new SLLNode<>(operand2, 
			new SLLNode<>(operator1,
			new SLLNode<>(operand3, 
			new SLLNode<>(operand4, 
			new SLLNode<>(operator3, 
			new SLLNode<>(operator2, null)))))));
		
		/* Expected to return a string storing the infix expression `(23 - 46) + (69 * 92)`
		 * Notes: 
		 * 	+ The parentheses here are meant to indicate the order of evaluation: they need to be stored in the output string.
		 *  + For this task, we disregard operator precedences: always put a pair or parentheses to explicitly indicate the order of evaluation. 
		 */
		assertEquals("((23 - 46) + (69 * 92))", u.getInfixSequence(expression));
		/* 
		 * When the expression involves at least an operator, an outer-most pair of parentheses is to be included. 
		 */
	}
	
	/*
	 * Jackie's suggestion: 
	 * 	+ For both tasks, you may want to test your implemented method(s) for more sophisticated input postfix expressions.
	 * 		Hint. First think of the output infix expression (e.g., 3 + 4 * 5), 
	 * 				then work out backwards the corresponding input postfix expression (i.e., 3 4 5 * +) 
	 */
}