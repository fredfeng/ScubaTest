package scuba.test.testcase.container.map;

/**
 * @author ntallapa
 * @author yufeng
 */
public class TMHashMap<K, V> {
	// for simplicity size is taken as 2^4
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private Entry<K, V> table[];

	public TMHashMap() {
		table = new Entry[DEFAULT_INITIAL_CAPACITY];
	}

	/**
	 * User defined simple Map data structure with key and value. This is also
	 * used as linked list in case multiple key-value pairs lead to the same
	 * bucket with same hashcodes and different keys (collisions) using pointer
	 * 'next'.
	 *
	 * @author ntallapa
	 */
	class Entry<K, V> {
		final K key;
		V value;
		Entry<K, V> next;

		Entry(K k, V v) {
			key = k;
			value = v;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public K getKey() {
			return key;
		}
	}

	/**
	 * Returns the entry associated with the specified key in the HashMap.
	 * Returns null if the HashMap contains no mapping for the key.
	 */
	public Entry<K, V> get(K k) {
		int hash = k.hashCode() % DEFAULT_INITIAL_CAPACITY;
		Entry<K, V> e = table[hash];

		// if bucket is found then traverse through the linked list and
		// see if element is present
		while (e != null) {
			if (e.key.equals(k)) {
				return e;
			}
			e = e.next;
		}
		return null;
	}

	/**
	 * Associates the specified value with the specified key in this map. If the
	 * map previously contained a mapping for the key, the old value is
	 * replaced.
	 */
	public void put(K k, V v) {
		int hash = k.hashCode() % DEFAULT_INITIAL_CAPACITY;
		Entry<K, V> e = table[hash];
		if (e != null) {
			// it means we are trying to insert duplicate
			// key-value pair, hence overwrite the current
			// pair with the old pair
			if (e.key.equals(k)) {
				e.value = v;
			} else {
				// traverse to the end of the list and insert new element
				// in the same bucket
				while (e.next != null) {
					e = e.next;
				}
				Entry<K, V> entryInOldBucket = new Entry<K, V>(k, v);
				e.next = entryInOldBucket;
			}
		} else {
			// new element in the map, hence creating new bucket
			Entry<K, V> entryInNewBucket = new Entry<K, V>(k, v);
			table[hash] = entryInNewBucket;
		}
	}
}