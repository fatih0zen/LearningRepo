package tree;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 *  
 */
 public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/* Provide whatever instance variables you need */
	
	private K key;
	private V value;
	private Tree<K, V> left;
	private Tree<K, V> right;
	
	/**
	 * Only constructor we need.
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public V search(K key) {
		int comparison = key.compareTo(this.key);
		
		if (comparison == 0) {
			return value;
		} else if (comparison < 0) {

			return left.search(key);
		} else {
			return right.search(key);
		}
	}
	
	public NonEmptyTree<K, V> insert(K key, V value) {
		int result = key.compareTo(this.key);

		if (result == 0) {
			this.key = key;
			this.value = value;
			return this;
		} else if (result > 0) {
			right = right.insert(key, value);
			return this;
		} else {
			left = left.insert(key, value);
			return this;
		}
	}
	
	public Tree<K, V> delete(K key) {
		int result = key.compareTo(this.key);
		
		if (result < 0) {
			left = left.delete(key);
		} else if (result > 0) {
			right = right.delete(key);
		} else {
			return right;
		}
		
		return this;
	}

	public K max() {
		try {
			return right.max();
		} catch (TreeIsEmptyException e) {
			throw new NoSuchElementException();
		}
	}

	public K min() {
		try {
			return left.min();
		} catch (TreeIsEmptyException e) {
			throw new NoSuchElementException();
		}
	}

	public int size() {
		return 1 + left.size() + right.size();
	}

	public void addKeysToCollection(Collection<K> c) {
		c.add(key);
		left.addKeysToCollection(c);
		right.addKeysToCollection(c);
	}
	
	public Tree<K,V> subTree(K fromKey, K toKey) {
		
		if (this.key.compareTo(toKey) > 0) {
			return left.subTree(fromKey, toKey);
		} else if (this.key.compareTo(fromKey) < 0) {
			return right.subTree(fromKey, toKey);
		} else {
			return new NonEmptyTree<K, V>(this.key, this.value, left.subTree(fromKey, toKey), right.subTree(fromKey, toKey));
		}
		
	}
	
	public int height() {
		return 1 + Math.max(right.height(), left.height());
	}
	
	public void inorderTraversal(TraversalTask<K,V> p) {
		left.inorderTraversal(p);
		p.performTask(key, value);
		right.inorderTraversal(p);
	}
	
	public void rightRootLeftTraversal(TraversalTask<K,V> p) {
		right.inorderTraversal(p);
		p.performTask(key, value);
		left.inorderTraversal(p);
	}	
}