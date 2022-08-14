package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

import junit.framework.TestCase;
import tree.PlaceKeysValuesInArrayLists;
import tree.PolymorphicBST;

public class StudentTests extends TestCase {
	
	@Test
	public void testRemove() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");
		ptree.put(4, "Four");
		assertEquals(3, ptree.height());
		
		ptree.remove(3);
		assertEquals(3, ptree.size());
		
		ptree.remove(4);
		assertEquals(2, ptree.size());
		
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(task);
//		System.out.println(task.getKeys().toString());
	}
	
	@Test
	public void testSubTree() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(40, "Forty");
		ptree.put(20, "Twenty");
		ptree.put(60, "Sixty");
		ptree.put(10, "Ten");
		ptree.put(30, "Thirty");
		ptree.put(50, "Fifty");
		ptree.put(70, "Seventy");
		ptree.put(71, "Seventyone");
		
		ptree.subMap(30, 70);
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(task);
		System.out.println(task.getKeys().toString());
		assertEquals(task.getKeys().toString(), "[30, 40, 50, 60, 70]");
		assertEquals(task.getValues().toString(), "[Thirty, Forty, Fifty, Sixty, Seventy]");
	}
	
	@Test
	public void testRightRootLeftTraversal() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(40, "Forty");
		ptree.put(20, "Twenty");
		ptree.put(60, "Sixty");
		ptree.put(10, "Ten");
		ptree.put(30, "Thirty");
		ptree.put(50, "Fifty");
		ptree.put(70, "Seventy");
		ptree.put(71, "Seventyone");
		
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.rightRootLeftTraversal(task);
		assertEquals(task.getKeys().toString(), "[50, 60, 70, 71, 40, 10, 20, 30]");
		assertEquals(task.getValues().toString(), "[Fifty, Sixty, Seventy, Seventyone, Forty, Ten, Twenty, Thirty]");
	}
	
	@Test
	public void testKeySet() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		
		ptree.put(40, "Forty");
		ptree.put(20, "Twenty");
		ptree.put(60, "Sixty");
		ptree.put(10, "Ten");
		ptree.put(30, "Thirty");
		ptree.put(50, "Fifty");
		ptree.put(70, "Seventy");
		ptree.put(71, "Seventyone");
		
		String answer = "";
		for (int key : ptree.keySet()) {
			answer += key + " ";
		}

		assertEquals(answer, "50 20 70 71 40 10 60 30 ");
	}
}