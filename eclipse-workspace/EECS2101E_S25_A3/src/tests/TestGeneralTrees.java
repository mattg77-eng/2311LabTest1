package tests;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * This test illustrates how the two classes SLLNode and TreeNode may be used together.   
 */

public class TestGeneralTrees {

	@Test
	public void test_general_trees_construction_strings() {
		TreeNode<String> jonathan = new TreeNode<>("Jonathan");
		TreeNode<String> alan = new TreeNode<>("Alan");
		TreeNode<String> mark = new TreeNode<>("Mark");
		TreeNode<String> tom = new TreeNode<>("Tom");

		/* Initially, no child nodes for jonathan */
		assertNull(jonathan.getChildren());

		/* Add the first child node for jonathan */
		jonathan.addChild(alan);
		alan.setParent(jonathan);

		assertNull(jonathan.getParent());
		assertTrue(jonathan == alan.getParent());
		SLLNode<TreeNode<String>> childList = jonathan.getChildren();
		assertTrue(childList.getElement() == alan);
		/*
		 * childList 							returns a SLLNode<TreeNode<String>>
		 * childList.getElement() 				returns a TreeNode<String>
		 * childList.getElement().getElement() 	returns a String
		 */
		assertTrue(childList.getElement().getElement().equals("Alan"));
		assertNull(childList.getNext());

		/* Add the second child node for jonathan */
		jonathan.addChild(mark);
		mark.setParent(jonathan);

		assertNull(jonathan.getParent());
		assertTrue(jonathan == alan.getParent());
		assertTrue(jonathan == mark.getParent());
		childList = jonathan.getChildren();
		assertTrue(childList.getElement() == alan);
		assertTrue(childList.getElement().getElement().equals("Alan"));
		assertTrue(childList.getNext().getElement() == mark);
		/*
		 * childList.getNext() 								returns a SLLNode<TreeNode<String>>
		 * childList.getNext().getElement() 				returns a TreeNode<String>
		 * childList.getNext().getElement().getElement() 	returns a String
		 */
		assertTrue(childList.getNext().getElement().getElement().equals("Mark"));
		assertNull(childList.getNext().getNext());

		/* Add the third child node for jonathan */
		jonathan.addChild(tom);
		tom.setParent(jonathan);

		assertNull(jonathan.getParent());
		assertTrue(jonathan == alan.getParent());
		assertTrue(jonathan == mark.getParent());
		assertTrue(jonathan == tom.getParent());
		childList = jonathan.getChildren();
		assertTrue(childList.getElement() == alan);
		assertTrue(childList.getElement().getElement().equals("Alan"));
		assertTrue(childList.getNext().getElement() == mark);
		assertTrue(childList.getNext().getElement().getElement().equals("Mark"));
		assertTrue(childList.getNext().getNext().getElement() == tom);
		assertTrue(childList.getNext().getNext().getElement().getElement().equals("Tom"));
		assertNull(childList.getNext().getNext().getNext());
	}

	@Test
	public void test_general_trees_construction_integers() {
		TreeNode<Integer> i0 = new TreeNode<>(0);
		TreeNode<Integer> i1 = new TreeNode<>(1);
		TreeNode<Integer> i2 = new TreeNode<>(2);
		TreeNode<Integer> i3 = new TreeNode<>(3);

		/* Initially, no child nodes for i0 */
		assertNull(i0.getChildren());

		/* Add the first child node for i0 */
		i0.addChild(i1);
		i1.setParent(i0);

		assertNull(i0.getParent());
		assertTrue(i0 == i1.getParent());
		SLLNode<TreeNode<Integer>> childList = i0.getChildren();
		assertTrue(childList.getElement() == i1);
		/*
		 * childList 							returns a SLLNode<TreeNode<Integer>>
		 * childList.getElement() 				returns a TreeNode<Integer>
		 * childList.getElement().getElement() 	returns a Integer
		 */
		assertTrue(childList.getElement().getElement().equals(1));
		assertNull(childList.getNext());

		/* Add the second child node for i0 */
		i0.addChild(i2);
		i2.setParent(i0);

		assertNull(i0.getParent());
		assertTrue(i0 == i1.getParent());
		assertTrue(i0 == i2.getParent());
		childList = i0.getChildren();
		assertTrue(childList.getElement() == i1);
		assertTrue(childList.getElement().getElement().equals(1));
		assertTrue(childList.getNext().getElement() == i2);
		/*
		 * childList.getNext() 								returns a SLLNode<TreeNode<Integer>>
		 * childList.getNext().getElement() 				returns a TreeNode<Integer>
		 * childList.getNext().getElement().getElement() 	returns a Integer
		 */
		assertTrue(childList.getNext().getElement().getElement().equals(2));
		assertNull(childList.getNext().getNext());

		/* Add the third child node for i0 */
		i0.addChild(i3);
		i3.setParent(i0);

		assertNull(i0.getParent());
		assertTrue(i0 == i1.getParent());
		assertTrue(i0 == i2.getParent());
		assertTrue(i0 == i3.getParent());
		childList = i0.getChildren();
		assertTrue(childList.getElement() == i1);
		assertTrue(childList.getElement().getElement().equals(1));
		assertTrue(childList.getNext().getElement() == i2);
		assertTrue(childList.getNext().getElement().getElement().equals(2));
		assertTrue(childList.getNext().getNext().getElement() == i3);
		assertTrue(childList.getNext().getNext().getElement().getElement().equals(3));
		assertNull(childList.getNext().getNext().getNext());
	}
}
