/*
 This calss is the basic Stack implementaion.
 It provides pop(), push(), peek() methods
 @auther Yubing 
 */
import java.util.NoSuchElementException;

public class Stack<E> {
	private Element<E> top;
	
	public Stack() {
		top = null;
	}
	
	public Stack<E> push(E value) {
		top = new Element<E>(value,top);
		return this;
	}
	
	public E pop() {
		if(top == null) {
			throw new NoSuchElementException();
		}
		E result = top.value;
		top = top.next;
		return result;
	}
	
	public E peek() {
		if(top == null) {
			throw new NoSuchElementException();
		}
		return top.value;
	}
	
	public String toString() {
		Element<E> recordTop = top;
		String str = "";
		while(recordTop != null) {
			str += recordTop.value + "\n";
			recordTop = recordTop.next; 
		}
		return str;
	}
}

class Element<E> {
	E value;
	Element<E> next;
	
	public Element(E element, Element<E> next){
		this.value = element;
		this.next = next;
	}
}