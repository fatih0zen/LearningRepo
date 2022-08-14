package listClasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class BasicLinkedList<T> implements Iterable<T> {
	
	protected class Node {
		protected T data;
		protected Node next;

		protected Node(T data) {
			this.data = data;
			next = null;
		}
	}
	
	protected Node head, tail;
	protected int size;
	
	public BasicLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}
	
	public int getSize() {
		return size;
	}
	
	public BasicLinkedList<T> addToEnd(T data) {
		Node newNode = new Node(data);
		
		if (tail == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = tail.next;
		}
		
		size++;
		return this;
	}
	
	public BasicLinkedList<T> addToFront(T data) {
		Node newNode = new Node(data);
		newNode.next = head;
		head = newNode;
		
		if (tail == null) {
			tail = head;
		}
		
		size++;
		return this;
	}
	
	public T getFirst() {
		if (size == 0) {
			return null;
		} else {
			return head.data;
		}
	}
	
	public T getLast() {
		if (size == 0) {
			return null;
		} else {
			return tail.data;
		}
	}
	
	public T retrieveFirstElement() {
		if (size == 0) {
			return null;
		} else {
			Node current = head;
			
			head = head.next;
			size--;
			
			if (head == null) {
				tail = null;
			}
			
			return current.data;
		}
	}
	
	public T retrieveLastElement() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			Node current = head;
			head = null;
			tail = null;
			size = 0;
			return current.data;
		} else {
			Node current = head;
			for (int i = 0; i < size - 2; i++) {
				current = current.next;
			}
			
			Node last = tail;
			tail = current;
			tail.next = null;
			
			size--;
			return last.data;
		}
	}
	
	public BasicLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		int index = getIndex(targetData, comparator);
		
		if (index < 0 || index >= size) {
			return null;
		} else if (index == 0) {
			retrieveFirstElement();
			return this;
		} else if (index == size - 1) {
			retrieveLastElement();
			return this;
		} else {
			Node newNode = head;
			
			for (int i = 1; i < index; i++) {
				newNode = newNode.next;
			}
			
			Node current = newNode.next;
			newNode.next = current.next;
			
			size--;
			
			return this;
		}
	}
	
	private int getIndex(T targetData, Comparator<T> comparator) {
		int count = 0;
		Node newNode = head;
		
		for (int i = 0; i < size - 2; i++) {
			if (comparator.compare(targetData, newNode.data) == 0) {
				break;
			} else {
				count++;
			}
		}
		
		return count;
	}
	
	public ArrayList<T> getReverseArrayList() {
		ArrayList<T> reversedList = new ArrayList<T>();
		Node current = head;
	
		for (int i = 0; i < size; i++) {
			reversedList.add(0, current.data);
			current = current.next;
		}
			
		return reversedList;
	}
	
	public BasicLinkedList<T> getReverseList() {
		BasicLinkedList<T> reversedList = new BasicLinkedList<T>();
		Node current = head;
	
		for (int i = 0; i < size; i++) {
			reversedList.addToFront(current.data);
			current = current.next;
		}
			
		return reversedList;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node current = head;
			
			@Override
			public boolean hasNext() {
				return (current != null);
			}

			@Override
			public T next() {
				T t = current.data;
				current = current.next;
				return t;
			}
		};
	}
	
}