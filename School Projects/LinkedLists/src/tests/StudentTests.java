package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;

public class StudentTests {

	@Test
	public void testReverseArrayList() {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();

		String answer = "";
		basicList.addToEnd("Hello").addToFront("World").addToFront("CMSC132");

		answer += basicList.getReverseArrayList().toString();
		
		assertEquals("[Hello, World, CMSC132]", answer);
	}
	
	@Test
	public void testReverseList() {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();

		String answer = "";
		basicList.addToEnd("Hello").addToFront("World").addToFront("CMSC132");
		
		for (String entry : basicList.getReverseList()) {
			answer += entry + " ";
		}
		
		assertEquals("Hello World CMSC132 ", answer);
	}
	
	@Test
	public void testFirstAndLast() {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();

		String answer = "";
		basicList.addToEnd("Hello").addToFront("World").addToFront("CMSC132");
		
		answer += "First: " + basicList.getFirst();
		answer += " ";
		answer += "Last: " + basicList.getLast();
		answer += " ";
		answer += "Size: " + basicList.getSize();
		
		assertEquals("First: CMSC132 Last: Hello Size: 3", answer);
	}
	
	@Test
	public void testRetrieveElement() {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();

		String answer = "";
		basicList.addToEnd("Hello").addToFront("World").addToFront("CMSC132");
		
		answer += "Retrieve First: " + basicList.retrieveFirstElement();
		answer += " ";
		answer += "Retrieve Last: " + basicList.retrieveLastElement();
		answer += " ";
		answer += "Size: " + basicList.getSize();
		
		assertEquals("Retrieve First: CMSC132 Retrieve Last: Hello Size: 1", answer);
	}
	
	@Test
	public void testRemove() {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();

		String answer = "";
		basicList.addToEnd("Hello").addToFront("World").addToFront("CMSC132");
		
		basicList.remove("World", String.CASE_INSENSITIVE_ORDER);
		
		for (String entry : basicList) {
			answer += entry + " ";
		}
		
		assertEquals("CMSC132 Hello ", answer);
	}
	
	@Test
	public void testSortedLinkedList() {
		SortedLinkedList<String> sortedList = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		String answer = "";
		
		sortedList.add("Yellow").add("Blue").add("Navy").add("Blue").add("Red");
		answer += "Iteration (for sorted list): ";
		for (String entry : sortedList) {
			answer += entry + " ";
		}
		
		sortedList.remove("Red");
		answer += "---- After Removing Red: ";
		for (String entry : sortedList) {
			answer += entry + " ";
		}
		
		System.out.println(answer);
		
		assertEquals("Iteration (for sorted list): Blue Blue Navy Red Yellow ---- After Removing Red: Blue Blue Navy Yellow ", answer);
	}

}
