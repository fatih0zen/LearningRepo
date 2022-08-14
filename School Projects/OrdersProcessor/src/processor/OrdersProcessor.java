package processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class OrdersProcessor {
	
	public static void main(String[] args) {
		HashMap<String, Double> itemsAndCosts = new HashMap<>();
		ArrayList<Order> orders = new ArrayList<>();
		
		Scanner scan = new Scanner(System.in);
		
		String itemsFileName, ordersBaseFileName, resultsFileName;
		boolean isMultipleThread;
		int numberOfOrders;
		
		/// Prompt to user
		System.out.print("Enter item's data file name: ");
		itemsFileName = scan.next();
		System.out.println();
		
		System.out.print("Enter 'y' for multiple threads, any other character otherwise: ");
		String answer = scan.next();
		if (answer.equalsIgnoreCase("y")) {
			isMultipleThread = true;
		} else {
			isMultipleThread = false;
		}
		System.out.println();
		
		System.out.print("Enter number of orders to process: ");
		numberOfOrders = scan.nextInt();
		System.out.println();
		
		System.out.print("Enter order's base filename: ");
		ordersBaseFileName = scan.next();
		System.out.println();
		
		System.out.print("Enter result's filename: ");
		resultsFileName = scan.next();
		System.out.println();
		
		scan.close();
		/// End of prompt
		
		long startTime = System.currentTimeMillis();
		
		getAllItems(itemsFileName, itemsAndCosts);
		
		if (isMultipleThread) {
			ArrayList<Thread> threads = new ArrayList<>();
			Object o = new Object();
			
			for (int i = 0; i < numberOfOrders; i++) {
				String fName = ordersBaseFileName + (i + 1) + ".txt";
				
				threads.add(new Thread(new Runnable() {
					public void run() {
						synchronized(o) {
							getOrder(fName, orders, itemsAndCosts);
						}
					}
				}));
			}
			
			for (Thread t : threads) {
				t.start();
			}
			
			for (Thread t : threads) {
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			for (int i = 0; i < numberOfOrders; i++) {
				getOrder(ordersBaseFileName + (i + 1) + ".txt", orders, itemsAndCosts);
			}
		}
		
		writeToFile(resultsFileName, orders);
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Processing time (msec): " + (endTime - startTime));
		System.out.println("Results can be found at: " + resultsFileName);
	}
	
	private static void getOrder(String itemsFileName, ArrayList<Order> orders, HashMap<String, Double> itemsAndCosts) {
		try {
			File f = new File(itemsFileName);
			Scanner myReader = new Scanner(f);
			Order newOrder = null;
			
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] item = data.split(" ");
				if (item[0].equalsIgnoreCase("ClientId:")) {
					newOrder = new Order(Integer.parseInt(item[1]));
				} else {
					if (newOrder != null) {
						newOrder.addItem(new Item(item[0], itemsAndCosts.get(item[0])));
					}
				}
			}
			
			if (newOrder != null) {
				orders.add(newOrder);
			}
			
			myReader.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private static void getAllItems(String itemsFileName, HashMap<String, Double> itemsAndCosts) {
		try {
			Scanner myReader = new Scanner(new File(itemsFileName));
			
			while (myReader.hasNextLine()) {
				String[] item = myReader.nextLine().split(" ");

				itemsAndCosts.put(item[0], Double.parseDouble(item[1]));
			}
			
			myReader.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private static String output(ArrayList<Order> orders) {
		TreeMap<Item, Integer> allItems = new TreeMap<>();
		Double grandTotal = 0.0;
		String output = "";

		Collections.sort(orders);
		for (Order o : orders) {
			output += o.toString() + "\n";
		}
		
		for (Order o : orders) {
			for (Item i : o.getItems()) {
				if (allItems.containsKey(i)) {
					allItems.put(i, allItems.get(i) + i.getQuantity());
				} else {
					allItems.put(i, i.getQuantity());
				}
				
				grandTotal += i.getTotalCost();
			}
		}
		
		output += "***** Summary of all orders *****\n";
		
		for (Item i : allItems.keySet()) {
			int numberSold = allItems.get(i);
			output += "Summary - Item's name: " + i.getName() + ", Cost per item: " + NumberFormat.getCurrencyInstance().format(i.getCost()) + ", Number sold: " + numberSold + ", Item's Total: " + NumberFormat.getCurrencyInstance().format(numberSold * i.getCost()) + "\n";
		}
		
		output += "Summary Grand Total: " + NumberFormat.getCurrencyInstance().format(grandTotal);
		
		return output;
	}
	
	private static void writeToFile(String filename, ArrayList<Order> orders) {
		try {
			FileWriter fileWriter = new FileWriter(filename, false);
		
			fileWriter.write(output(orders) + "\n");
			
			fileWriter.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
}
