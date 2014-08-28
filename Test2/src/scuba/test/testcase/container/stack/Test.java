package scuba.test.testcase.container.stack;

import framework.scuba.helper.AliasHelper;
import scuba.test.testcase.container.dlist.ListItem;

public class Test {

	public static void run() {
		ListItem l1 = new ListItem();
		ListItem l2 = new ListItem();
		
		Object o1 = new Object();
		Object o2 = new Object();
		l1.f = o1;
		l2.f = o1;
		
        ResizingArrayStack<ListItem> s = new ResizingArrayStack<ListItem>();
        s.push(l1);
        
        ListItem v1 = s.pop();
        AliasHelper.alias(v1, l1);
        AliasHelper.notAlias(v1, l2);
        
        s.push(l1);
        s.push(l2);
        
        while (!s.isEmpty()) {
        	ListItem obj = s.iterator().next();
        	AliasHelper.alias(obj, l1);
        	AliasHelper.alias(obj, l2);
        }
        ListItem v2 = s.peek();
        AliasHelper.notAlias(v2.f, o2);
        AliasHelper.alias(v2.f, o1);
		l2.f = o2;
		
		//should be marked as no-op
		s.iterator().remove();

        ListItem l3 = s.peek();
        AliasHelper.alias(l3.f, o2);
	}

}
