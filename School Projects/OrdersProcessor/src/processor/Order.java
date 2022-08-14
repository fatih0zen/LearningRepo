package processor;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Order implements Comparable<Order> {
	
	private int clientId;
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public Order(int clientId) {
		this.clientId = clientId;
	}
	
	public void addItem(Item item) {
		synchronized(this) {
			if (items.contains(item)) {
				items.get(items.indexOf(item)).incrementQuantity();
			} else {
				items.add(item);
			}
		}
	}
	
	public  double getTotalCost() {
		synchronized(this) {
			double totalCost = 0;

			for (Item item : items) {
				totalCost += item.getTotalCost();
			}

			return totalCost;
		}
	}
	
	public ArrayList<Item> getItems() {
		synchronized(this) {
			return items;
		}
	}

	@Override
	public String toString() {
		synchronized(this) {
			String output = "----- Order details for client with Id: " + clientId + " -----";

			Collections.sort(items);
			for (Item item : items) {
				output += "\n" + item.toString();
			}

			output += "\nOrder Total: " + NumberFormat.getCurrencyInstance().format(getTotalCost());

			return output;
		}
	}

	@Override
	public int compareTo(Order o) {
		synchronized(this) {
			if (clientId == o.clientId) {
				return 0;
			} else if (clientId > o.clientId) {
				return 1;
			} else {
				return -1;
			}
		}
	}
	
}
