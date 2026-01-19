package tests;

/*
 * Template for nodes in a general tree.
 * Notes: 
 * 	+ This version of TreeNode is different from what's covered in the lecture.
 * 		Specifically, the list of child nodes is implemented via a chain of singly-linked nodes (see the SLLNode class).
 * 	+ See the TestGeneralTrees class for how the TreeNode and SLLNode classes are supposed to work together.
 */

public class TreeNode<E> {
	private E element; /* data object */
	private TreeNode<E> parent; /* unique parent node */
	private SLLNode<TreeNode<E>> headOfChildList; /* head of list of child nodes */
	private SLLNode<TreeNode<E>> tailOfChildList; /* tail of list of child nodes */

	public TreeNode(E element) {
		this.element = element;
		this.parent = null;
		this.headOfChildList = null;
		this.tailOfChildList = null;
	}

	public E getElement() {
		return this.element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public TreeNode<E> getParent() {
		return this.parent;
	}

	public void setParent(TreeNode<E> parent) {
		this.parent = parent;
	}

	public SLLNode<TreeNode<E>> getChildren() {
		SLLNode<TreeNode<E>> result = null;
		SLLNode<TreeNode<E>> currentResult = null;
		SLLNode<TreeNode<E>> currentChild = headOfChildList;
		while (currentChild != null) {
			SLLNode<TreeNode<E>> n = new SLLNode<>(currentChild.getElement(), null);
			if(result == null) {
				result = n;
				currentResult = result;
			}
			else {
				currentResult.setNext(n);
				currentResult = currentResult.getNext();
			}
			currentChild = currentChild.getNext();
		}
		return result;
	} 

	public void addChild(TreeNode<E> child) {
		SLLNode<TreeNode<E>> n = new SLLNode<>(child, null);
		if(headOfChildList == null) {
			headOfChildList = n;
			tailOfChildList = headOfChildList;
		}
		else {
			tailOfChildList.setNext(n);
			tailOfChildList = tailOfChildList.getNext();
		}
	}
}
