package unsortedList;
public class EmptyList<K extends Comparable<K>> implements List<K> {
	
	public EmptyList() {
		/* Nothing to do */
	}
	
	public boolean contains(K data) {
		return false;
	}

	public NonEmptyList<K> insert(K data) {
		return new NonEmptyList<K>(data, this);
	}

	public List<K> remove(K data) {
		return this;
	}

	public K max() throws ListIsEmptyException {
		throw new ListIsEmptyException();
	}
	
	public String toString() {
		return "";
	}
}