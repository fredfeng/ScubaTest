package scuba.test.testcase.container.dlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.Vector;

import framework.scuba.helper.AliasHelper;

public class Test {

	public static void run() {
		// TODO Auto-generated method stub
		int N = 4;
		ListItem l1 = new ListItem();
		ListItem l2 = new ListItem();
		ListItem l3 = new ListItem();
		ListItem l4 = new ListItem();

		Object o1 = new Object();
		Object o2 = new Object();
		l1.f = o1;
		l2.f = o1;
		l3.f = o1;
		l4.f = o2;

		// add elements 1, ..., N
		DoublyLinkedList<ListItem> list = new DoublyLinkedList<ListItem>();
		for (int i = 0; i < N; i++)
			list.add(l1);
		list.add(l2);
		list.add(l3);

		ListIterator<ListItem> iterator = list.iterator();
		test1(iterator, o1, l1, l2, l4);
		test2(iterator, l2, l3, l4);
		test3(iterator, l2, l3, l4);
		test4(iterator, l2, l3, l4);
		// test5(iterator, l4);

		ListItem x = iterator.previous();
		AliasHelper.notAlias(x.f, l4.f);
		// eventually we add l4.
		iterator.add(l4);
		// now everything becomes alias.
		ListItem y = iterator.previous();
		AliasHelper.alias(y, l4);
		AliasHelper.alias(y.f, l4.f);
		AliasHelper.notAlias(x.f, l4.f);

		test6();
		test7();
		test8();
		test9();
		test10();
	}

	private static void test1(ListIterator<ListItem> iterator, Object o1,
			ListItem l1, ListItem l2, ListItem l4) {
		// go forwards with next() and set()
		while (iterator.hasNext()) {
			ListItem x = iterator.next();
			x.f = o1;
			AliasHelper.alias(x.f, l1.f);
			AliasHelper.alias(x.f, l2.f);
			AliasHelper.notAlias(x.f, l4.f);
			iterator.set(x);
		}
	}

	private static void test2(ListIterator<ListItem> iterator, ListItem l2,
			ListItem l3, ListItem l4) {
		// go backwards with previous() and set()
		while (iterator.hasPrevious()) {
			ListItem x = iterator.previous();
			AliasHelper.alias(x, l2);
			AliasHelper.alias(x, l3);
			AliasHelper.notAlias(x, l4);
		}
	}

	private static void test3(ListIterator<ListItem> iterator, ListItem l2,
			ListItem l3, ListItem l4) {
		// remove all elements that are multiples of 4 via next() and remove()
		while (iterator.hasNext()) {
			ListItem x = iterator.next();
			if (x != null)
				iterator.remove();
			AliasHelper.alias(x, l2);
			AliasHelper.alias(x, l3);
			AliasHelper.notAlias(x, l4);
		}
	}

	private static void test4(ListIterator<ListItem> iterator, ListItem l2,
			ListItem l3, ListItem l4) {
		// remove all even elements via previous() and remove()
		while (iterator.hasPrevious()) {
			ListItem x = iterator.previous();
			if (x != null)
				iterator.remove();
			AliasHelper.alias(x, l2);
			AliasHelper.alias(x, l3);
			AliasHelper.notAlias(x, l4);
		}
	}

	private static void test5(ListIterator<ListItem> iterator, ListItem l4) {
		// add elements via previous() and add()
		ListItem x = iterator.previous();
		AliasHelper.notAlias(x.f, l4.f);
		// eventually we add l4.
		iterator.add(l4);
		// now everything becomes alias.
		ListItem y = iterator.previous();
		AliasHelper.alias(y, l4);
		AliasHelper.alias(y.f, l4.f);
		AliasHelper.notAlias(x.f, l4.f);
	}

	public static void test6() {
		DoublyLinkedList<ListItem> list = new DoublyLinkedList<ListItem>();
		// Object t1 = new Object();
		ListItem l1 = new ListItem();
		// l1.f = t1;
		list.add(l1);
		ListIterator<ListItem> iterator = list.iterator();
		ListItem l2 = iterator.next();
		// ListItem l3 = iterator.previous();
		// Object t2 = l2.f;
		// Object t3 = l3.f;
	}
	
	// java.util.ArrayList
	public static void test7() {
		List<Object> list = new ArrayList<Object>();
		Object o1 = new Object();
		Object o2 = new Object();
		list.add(o1);
		list.add(o2);
		
		List<Object> list2 = new ArrayList<Object>();
		Object o3 = new Object();
		list2.add(o3);
		
		Object o4 = list.get(0);
		Object o5 = list2.get(0);
		
		AliasHelper.notAlias(o4, o5);
		AliasHelper.alias(o4, o1);
		AliasHelper.alias(o4, o2);
		AliasHelper.alias(o5, o3);
	}

	// java.util.LinkedList
	public static void test8() {
		List<Object> list = new LinkedList<Object>();
		Object o1 = new Object();
		Object o2 = new Object();
		list.add(o1);
		list.add(o2);
		
		List<Object> list2 = new LinkedList<Object>();
		Object o3 = new Object();
		list2.add(o3);
		
		Object o4 = list.get(0);
		Object o5 = list2.get(0);
		
		AliasHelper.notAlias(o4, o5);
		AliasHelper.alias(o4, o1);
		AliasHelper.alias(o4, o2);
		AliasHelper.alias(o5, o3);
	}
	
	// java.util.Vector
	public static void test9() {
		List<Object> list = new Vector<Object>();
		Object o1 = new Object();
		Object o2 = new Object();
		list.add(o1);
		list.add(o2);
		
		List<Object> list2 = new Vector<Object>();
		Object o3 = new Object();
		list2.add(o3);
		
		Object o4 = list.get(0);
		Object o5 = list2.get(0);
		
		AliasHelper.notAlias(o4, o5);
		AliasHelper.alias(o4, o1);
		AliasHelper.alias(o4, o2);
		AliasHelper.alias(o5, o3);
	}
	
	// java.util.Stack
	public static void test10() {
		List<Object> list = new Stack<Object>();
		Object o1 = new Object();
		Object o2 = new Object();
		list.add(o1);
		list.add(o2);
		
		List<Object> list2 = new Stack<Object>();
		Object o3 = new Object();
		list2.add(o3);
		Object o4 = list.get(0);
		Object o5 = list2.get(0);
		
		AliasHelper.notAlias(o4, o5);
		AliasHelper.alias(o4, o1);
		AliasHelper.alias(o4, o2);
		AliasHelper.alias(o5, o3);
	}
	
	// java.util.LinkedList
	public static void test11() {
		List<Object> list = new LinkedList<Object>();
		Object o1 = new Object();
		Object o2 = new Object();
		list.add(o1);
		list.add(o2);
		
		List<Object> list2 = new LinkedList<Object>();
		Object o3 = new Object();
		list2.add(o3);
		
		Iterator<Object> it = list.iterator();
		Iterator<Object> it2 = list2.iterator();
		
		Object o4 = it.next();
		Object o5 = it2.next();
		
		AliasHelper.notAlias(it, it2);
		AliasHelper.notAlias(o4, o5);
		
		AliasHelper.alias(o4, o1);
		AliasHelper.alias(o4, o2);
		AliasHelper.alias(o3, o5);
		
		AliasHelper.notAlias(o1, o5);
		AliasHelper.notAlias(o2, o5);
	}
}
