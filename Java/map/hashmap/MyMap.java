package com.me.map.hashmap;

public interface MyMap<K, V> {
	
	public V get(K key);
	
	public V put(K key, V value);
	
	public int size();
	
	public interface Entry<K, V>{
		public K getKey();
		public V getValue();
	}

}
