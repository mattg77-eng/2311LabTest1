package tests;

/*
 * This Node class is provided to you, 
 * 	and you must use it to implement the required class(es) and method(s) in the model package.
 * The StarterTests class in the `tests` package suggests what you need to create in the `model` package.
 * Where the Node class is needed, you must:
 * 	+ Only use the public methods given here.
 *  + Not add any additional attributes or methods in this Node class.
 */

public class Node<E> {
	/*
	 * Do not modify this class. 
	 * When your submission is graded, the same starter version of the Node class will be used,
	 * meaning that if you made any changes to this class, they would be wiped out 
	 * and your submitted classes may just stop compiling.
	 */
	
	private E element;
	private Node<E> next;
	
	/*
	 * Constructor
	 */
	public Node(E e, Node<E> n) {
		element = e;
		next = n;
	}
	
	/*
	 * Accessors
	 */
	public E getElement() {
		return element;
	} 
	
	public Node<E> getNext() {
		return next;
	}
	
	/*
	 * Mutators
	 */
	public void setElement(E e) {
		element = e;
	}
	
	public void setNext(Node<E> n) {
		next = n;
	} 
}
