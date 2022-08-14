package unsortedList;

public class NonEmptyList<K extends Comparable<K>> implements List<K> {
	private K data;
	private List<K> rest;
	
	public NonEmptyList(K data, List<K> rest) {
		this.data = data;
		this.rest = rest;
	}

	public boolean contains(K data) {
		if (data.compareTo(this.data) == 0) {
			return true;
		}
		
		return rest.contains(data);
	}

	public NonEmptyList<K> insert(K data) {
		rest = rest.insert(data);
		return this;
	}

	public List<K> remove(K data) {
		if (data.compareTo(this.data) == 0) {
			return rest;
		}
		 
		rest = rest.remove(data);
		return this;	
	}
	
	public K max() {
		try {
			K value = rest.max();
			if (data.compareTo(value) >= 0) {
				return data;
			}
			
			return value;
		} catch (ListIsEmptyException e) {
			return data;
		}
	}
	
	public String toString() {
		return data + "|" + rest;
	}
}