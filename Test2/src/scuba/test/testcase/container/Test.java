package scuba.test.testcase.container;

import framework.scuba.helper.AliasHelper;

public class Test {

	public static void testContainer() {
		Visitor visitor = new ItemSettingVisitor();

		Container c1 = new Container();
		Item i11 = new Item();
		Item i12 = new Item();
		c1.apply(visitor, i11);
		c1.apply(visitor, i12);
		AliasHelper.alias(c1.item, i11);

		Container c2 = new Container();
		Item i21 = new Item();
		Item i22 = new Item();
		c2.apply(visitor, i21);
		c1.apply(visitor, i22);
		AliasHelper.notAlias(c1.item, c2.item);
		
		//test double linked list from princeton.
		//the goal is to merge access paths in a sound way.
		scuba.test.testcase.container.dlist.Test.run();
		//map
		scuba.test.testcase.container.map.Test.run();
		//stack
		scuba.test.testcase.container.stack.Test.run();
	}

}
