package scuba.test.testcase.container.dlist;

import java.util.ListIterator;

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
//        test5(iterator, l4);
        
		ListItem x = iterator.previous();
    	AliasHelper.notAlias(x.f, l4.f);
		//eventually we add l4.
		iterator.add(l4);
		//now everything becomes alias.
		ListItem y = iterator.previous();
    	AliasHelper.alias(y, l4);
    	AliasHelper.alias(y.f, l4.f);
    	AliasHelper.notAlias(x.f, l4.f);
        
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
            if (x != null) iterator.remove();
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
            if (x != null) iterator.remove();
        	AliasHelper.alias(x, l2);
        	AliasHelper.alias(x, l3);
        	AliasHelper.notAlias(x, l4);
        }
	}
	
	private static void test5(ListIterator<ListItem> iterator, ListItem l4) {
        // add elements via previous() and add()
		ListItem x = iterator.previous();
    	AliasHelper.notAlias(x.f, l4.f);
		//eventually we add l4.
		iterator.add(l4);
		//now everything becomes alias.
		ListItem y = iterator.previous();
    	AliasHelper.alias(y, l4);
    	AliasHelper.alias(y.f, l4.f);
    	AliasHelper.notAlias(x.f, l4.f);
	}

}
