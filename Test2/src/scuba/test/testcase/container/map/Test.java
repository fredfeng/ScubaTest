package scuba.test.testcase.container.map;

import framework.scuba.helper.AliasHelper;
import scuba.test.testcase.container.dlist.ListItem;

public class Test {

	public static void run() {
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
		AliasHelper.alias(v1, l2);
		AliasHelper.alias(v1, v2);
		AliasHelper.alias(v2, v1);
		AliasHelper.alias(v2.f, v1.f);
		AliasHelper.alias(v2.f, l1.f);

		AliasHelper.notAlias(l1, l2);
	}
}
