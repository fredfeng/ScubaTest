package scuba.test.testcase.container.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import framework.scuba.helper.AliasHelper;

public class Test {

	public static void run() {
		test1();
//		test2();
		test3();
		test4();
	}
	
	// java.util.HashSet
	static void test1() {
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();

		Set<Object> set = new HashSet<Object>();
		set.add(o1);
		set.add(o2);
		Iterator<Object> it = set.iterator();
		
		Set<Object> set2 = new HashSet<Object>();
		set2.add(o3);
		Iterator<Object> it2 = set2.iterator();
		AliasHelper.notAlias(it2, it);
		
		Object o4 = it.next();
		Object o5 = it2.next();
		AliasHelper.notAlias(o4, o5);
		AliasHelper.alias(o4, o1);
		AliasHelper.alias(o4, o2);
		AliasHelper.notAlias(o4, o3);
		AliasHelper.alias(o5, o3);
		AliasHelper.notAlias(o5, o1);
		AliasHelper.notAlias(o5, o2);
	}

	// java.util.LinkedHashSet
	static void test2() {
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();

		LinkedHashSet<Object> set = new LinkedHashSet<Object>();
		set.add(o1);
		set.add(o2);
		Iterator<Object> it = set.iterator();
		
		LinkedHashSet<Object> set2 = new LinkedHashSet<Object>();
		set2.add(o3);
		Iterator<Object> it2 = set2.iterator();
		AliasHelper.notAlias(it2, it);
		
		// no effect.
		set.remove(o1);
		
		Object o4 = it.next();
		Object o5 = it2.next();
		AliasHelper.notAlias(o4, o5);
		AliasHelper.alias(o4, o1);
		AliasHelper.alias(o4, o2);
		AliasHelper.notAlias(o4, o3);
		AliasHelper.alias(o5, o3);
		AliasHelper.notAlias(o5, o1);
		AliasHelper.notAlias(o5, o2);
	}
	
	// java.util.TreeSet
	static void test3() {
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();

		TreeSet<Object> set = new TreeSet<Object>();
		set.add(o1);
		set.add(o2);
		Iterator<Object> it = set.iterator();
		
		TreeSet<Object> set2 = new TreeSet<Object>();
		set2.add(o3);
		Iterator<Object> it2 = set2.iterator();
		AliasHelper.notAlias(it2, it);
		
		Object o4 = it.next();
		Object o5 = it2.next();
		AliasHelper.notAlias(o4, o5);
		AliasHelper.alias(o4, o1);
		AliasHelper.alias(o4, o2);
		AliasHelper.notAlias(o4, o3);
		AliasHelper.alias(o5, o3);
		AliasHelper.notAlias(o5, o1);
		AliasHelper.notAlias(o5, o2);
	}
	
	// java.util.TreeSet addAll
	static void test4() {
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();

		TreeSet<Object> set = new TreeSet<Object>();
		set.add(o1);
		set.add(o2);
		Iterator<Object> it = set.iterator();
		
		TreeSet<Object> set2 = new TreeSet<Object>();
		set2.add(o3);
		Iterator<Object> it2 = set2.iterator();
		AliasHelper.notAlias(it2, it);
		set.addAll(set2);
		
		Object o4 = it.next();
		Object o5 = it2.next();
		AliasHelper.alias(o4, o5);
	}
}
