package processor;

import java.text.NumberFormat;
import java.util.Objects;

public class Item implements Comparable<Item> {
	
	private String name;
	private double cost;
	private int quantity = 1;
	
	public Item(String name, double cost) {
		this.name = name;
		this.cost = cost;
	}
	
	public String getName() {
		return name;
	}
	
	public double getCost() {
		return cost;
	}
	
	public double getTotalCost() {
		return quantity * cost;
	}
	
	public void incrementQuantity() {
		quantity++;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	@Override
	public String toString() {
		return "Item's name: " + name + ", Cost per item: " + NumberFormat.getCurrencyInstance().format(cost) + ", Quantity: " + quantity + ", Cost: " + NumberFormat.getCurrencyInstance().format(getTotalCost());
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(Item o) {
		return name.compareTo(o.getName());
	}
	
}
