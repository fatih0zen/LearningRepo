package sortedList;

public class NonEmptyList<K extends Comparable<K>> implements List<K> {
	private K data;
	private List<K> rest;

	public NonEmptyList(K data, List<K> rest) {
		this.data = data;
		this.rest = rest;
	}

	public boolean contains(K data) {
		int result = data.compareTo(this.data);

		if (result == 0) {
			return true;
		} else if (result > 0) { /* Keep searching as data > this.data */
			return rest.contains(data);
		} else {
			return false;
		}
	}

	public NonEmptyList<K> insert(K data) {
		int result = data.compareTo(this.data);

		if (result == 0) {
			return this;
		} else if (result > 0) {
			rest = rest.insert(data); /* Notice that you need to assign to rest */
			return this;
		} else {
			/* insert here making current list the rest of the new list */
			return new NonEmptyList<K>(data, this);
		}
	}

	public List<K> remove(K data) {
		int result = data.compareTo(this.data);

		if (result == 0) {
			return rest;
		} else if (result > 0) {
			rest = rest.remove(data);
			return this;
		} else {
			/* data not present in the list so no list modification */
			return this;
		}
	}

	public K max() {
		try {
			return rest.max();
		} catch (ListIsEmptyException e) {
			return data;
		}
	}

	public K min() {
		return data;
	}

	public String toString() {
		return data + "|" + rest;
	}
}