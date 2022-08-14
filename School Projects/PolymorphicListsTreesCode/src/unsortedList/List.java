package unsortedList;

public interface List<K extends Comparable<K>> {
	public boolean contains(K data);

	public NonEmptyList<K> insert(K data);

	public List<K> remove(K data);

	public K max() throws ListIsEmptyException;
}
