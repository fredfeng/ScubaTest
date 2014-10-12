package scuba.test.testcase.container.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import scuba.test.testcase.container.dlist.ListItem;
import framework.scuba.helper.AliasHelper;

public class Test {

	public static void run() {
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
		test8();
		test9();
	}
	
	// Customized container.
	public static void test1() {
		ListItem l1 = new ListItem();
		ListItem l2 = new ListItem();

		Object o1 = new Object();
		Object o2 = new Object();

		Object k1 = new Object();
		Object k2 = new Object();
		l1.f = o1;
		l2.f = o2;

		TMHashMap<Object, ListItem> tmhm = new TMHashMap<Object, ListItem>();
		tmhm.put(k1, l1);
		ListItem v1 = tmhm.get(k1).getValue();
		AliasHelper.notAlias(v1, l2);
		AliasHelper.alias(v1, l1);

		tmhm.put(k2, l2);
		ListItem v2 = tmhm.get(k2).getValue();
		AliasHelper.alias(tmhm.get(k1), tmhm.get(k2));
		AliasHelper.notAlias(v1, l2);
		v1 = tmhm.get(k1).getValue();
		AliasHelper.alias(v1, l2);
		AliasHelper.alias(v1, v2);
		AliasHelper.alias(v2, v1);
		AliasHelper.alias(v2.f, v1.f);
		AliasHelper.alias(v2.f, l1.f);

		AliasHelper.notAlias(l1, l2);
	}
	
	// java.util.HashMap
	public static void test2() {
		Object key1 = new Object();
		Object value1 = new Object();
		
		Object key2 = new Object();
		Object value2 = new Object();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put(key1, value1);
		map.put(key2, value2);
		
		Object v1 = map.get(key1);
		Object v2 = map.get(key2);
		AliasHelper.alias(v1, v2);
		
		HashMap<Object, Object> map2 = new HashMap<Object, Object>();
		Object key3 = new Object();
		Object value3 = new Object();
		map2.put(key3, value3);
		Object v3 = map2.get(key3);
		// read from different maps.
		AliasHelper.notAlias(v1, v3);
		AliasHelper.notAlias(v2, v3);
	}
	
	// java.util.LinkedHashMap
	public static void test3() {
		Object key1 = new Object();
		Object value1 = new Object();
		
		Object key2 = new Object();
		Object value2 = new Object();
		LinkedHashMap<Object, Object> map = new LinkedHashMap<Object, Object>();
		map.put(key1, value1);
		map.put(key2, value2);
		
		Object v1 = map.get(key1);
		Object v2 = map.get(key2);
		AliasHelper.alias(v1, v2);
		
		LinkedHashMap<Object, Object> map2 = new LinkedHashMap<Object, Object>();
		Object key3 = new Object();
		Object value3 = new Object();
		map2.put(key3, value3);
		Object v3 = map2.get(key3);
		// read from different maps.
		AliasHelper.notAlias(v1, v3);
		AliasHelper.notAlias(v2, v3);
	}
	
	// java.util.concurrent.ConcurrentHashMap
	public static void test4() {
		Object key1 = new Object();
		Object value1 = new Object();
		
		Object key2 = new Object();
		Object value2 = new Object();
		ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<Object, Object>();
		map.put(key1, value1);
		map.put(key2, value2);
		
		Object v1 = map.get(key1);
		Object v2 = map.get(key2);
		AliasHelper.alias(v1, v2);
		
		ConcurrentHashMap<Object, Object> map2 = new ConcurrentHashMap<Object, Object>();
		Object key3 = new Object();
		Object value3 = new Object();
		map2.put(key3, value3);
		Object v3 = map2.get(key3);
		// read from different maps.
		AliasHelper.notAlias(v1, v3);
		AliasHelper.notAlias(v2, v3);
	}
	
	// java.util.TreeMap
	public static void test5() {
		Object key1 = new Object();
		Object value1 = new Object();
		
		Object key2 = new Object();
		Object value2 = new Object();
		TreeMap<Object, Object> map = new TreeMap<Object, Object>();
		map.put(key1, value1);
		map.put(key2, value2);
		
		Object v1 = map.get(key1);
		Object v2 = map.get(key2);
		AliasHelper.alias(v1, v2);
		
		TreeMap<Object, Object> map2 = new TreeMap<Object, Object>();
		Object key3 = new Object();
		Object value3 = new Object();
		map2.put(key3, value3);
		Object v3 = map2.get(key3);
		// read from different maps.
		AliasHelper.notAlias(v1, v3);
		AliasHelper.notAlias(v2, v3);
	}
	
	// Iterator for map.
	public static void test6() {
		Object key1 = new Object();
		Object value1 = new Object();
		
		Object key2 = new Object();
		Object value2 = new Object();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put(key1, value1);
		map.put(key2, value2);
		
		HashMap<Object, Object> map2 = new HashMap<Object, Object>();
		Object key3 = new Object();
		Object value3 = new Object();
		map2.put(key3, value3);
		
		Iterator<Object> key1It = map.keySet().iterator();
		Iterator<Object> key2It = map2.keySet().iterator();
		AliasHelper.notAlias(key1It, key2It);
	}
	
	// Iterator for map.
	public static void test7() {
		Object key1 = new Object();
		Object value1 = new Object();
		
		Object key2 = new Object();
		Object value2 = new Object();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put(key1, value1);
		map.put(key2, value2);
		
		HashMap<Object, Object> map2 = new HashMap<Object, Object>();
		Object key3 = new Object();
		Object value3 = new Object();
		map2.put(key3, value3);
		
		Iterator<Object> val1It = map.values().iterator();
		Iterator<Object> val2It = map2.values().iterator();
		AliasHelper.notAlias(val1It, val2It);
	}
	
	// Iterator for map.
	public static void test8() {
		Object key1 = new Object();
		Object value1 = new Object();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put(key1, value1);
		
		HashMap<Object, Object> map2 = new HashMap<Object, Object>();
		Object key3 = new Object();
		Object value3 = new Object();
		map2.put(key3, value3);
		Object v3 = map2.get(key3);
		
		Iterator entry1It = map.entrySet().iterator();
		Iterator entry2It = map2.entrySet().iterator();
		AliasHelper.notAlias(entry1It, entry2It);
		Entry v4 = (Entry)entry1It.next();
		Object v5 = entry2It.next();
		AliasHelper.notAlias(v4, v5);
		
		Object v6 = v4.getKey();
		Object v7 = v4.getValue();
		AliasHelper.notAlias(v6, v3);
		AliasHelper.notAlias(v7, value3);
		
		AliasHelper.alias(v6, key1);
		AliasHelper.alias(v7, value1);
	}
	
	// java.util.HashMap remove.
	public static void test9() {
		Object key1 = new Object();
		Object value1 = new Object();
		
		Object key2 = new Object();
		Object value2 = new Object();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put(key1, value1);
		map.put(key2, value2);
		
		// no effect.
		map.remove(key1);
		map.remove(key2);

		Object v1 = map.get(key1);
		Object v2 = map.get(key2);
		AliasHelper.alias(v1, v2);
	}
}
