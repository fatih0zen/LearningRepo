package listClasses;

import java.util.Comparator;

public class SortedLinkedList<T> extends BasicLinkedList<T> {
	
	private Comparator<T> comparator;
	
	public SortedLinkedList(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}
	
	public SortedLinkedList<T> add(T element) {		
		Node newNode = new Node(element);
		
		if (head == null) {
            head = newNode;
        } else if (comparator.compare(element, head.data) < 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node after = head.next;
            Node before = head;
            while (after != null) {
                if (comparator.compare(element, after.data) < 0) {
                    break;
                }
                before = after;
                after = after.next;
            }
            newNode.next = before.next;
            before.next = newNode;
        }
		
		size++;
		return this;
	}
	
	public SortedLinkedList<T> remove(T targetData) {
		super.remove(targetData, comparator);
		return this;
	}
	
	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	
	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
}