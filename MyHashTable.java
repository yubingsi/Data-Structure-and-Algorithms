/**
 * This class implements HashtMap class with generic
 * Uses List to solve the collision results
 */
import java.util.*;

public class MyHashTable<K,V> {

	private int size;
	private ArrayList<LinkedList<V>> array;
	
	public MyHashTable() {
		size = 31;
		array = new ArrayList<LinkedList<V>>(size);
		for(int i=0; i<31;i++) {
			array.add(null);
		}
	}

	private int hash(K key) {
		return key.hashCode() % size;
	}
	
	public void put(K key, V value) {
		if(key == null) {
			throw new NullPointerException();
		}
		int hashKey = hash(key);
		LinkedList<V> list = array.get(hashKey);
		if(list == null) {
		        array.remove(hashKey); // must remove here, or the index will be changed 
			array.add(hashKey,new LinkedList<V>());
		}
		array.get(hashKey).add(value);
	}
	
	public LinkedList<V> get(K key) {
		if(key == null) {
			throw new NullPointerException();
		}
		return array.get(hash(key));
	}
}
