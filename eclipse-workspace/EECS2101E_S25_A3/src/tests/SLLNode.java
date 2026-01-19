package tests;

/*
 * Template for nodes in a singly-linked list.
 */

public class SLLNode<E> {
	private E element;
	private SLLNode<E> next;
	
	public SLLNode(E e, SLLNode<E> n) { 
		element = e; 
		next = n; 
	}
	
	public E getElement() { 
		return element; 
	}
	
	public SLLNode<E> getNext() { 
		return next; 
	}
	
	public void setNext(SLLNode<E> n) { 
		next = n; 
	}
	
	public void setElement(E e) { 
		element = e; 
	}
}