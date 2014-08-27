import scuba.test.container.Container;
import scuba.test.container.Item;
import scuba.test.container.ItemSettingVisitor;
import scuba.test.container.Visitor;
import scuba.test.virtualcall.A;
import scuba.test.virtualcall.B;
import scuba.test.virtualcall.Bldg;
import scuba.test.virtualcall.C;
import scuba.test.virtualcall.Cst1;
import scuba.test.virtualcall.Cst2;
import scuba.test.virtualcall.SubCst1;
import scuba.test.virtualcall.SubCst2;
import framework.scuba.helper.AliasHelper;

public class Driver {

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		// Original test case from Isil showing the precision by constraint.
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();

		A a = new A(o1, o2, o3);
		B b = new B(o1, o2, o3);
		C c = new C(o1, o2, o3);

		Object o4 = test11(a);
		Object o5 = test11(b);
		Object o6 = test11(c);

		AliasHelper.notAlias(o4, o5); // false
		AliasHelper.notAlias(o4, o6); // false
		AliasHelper.notAlias(o5, o6); // false

		// Showing array allocated in different obj.
		Bldg bldg = new Bldg();
		AliasHelper.notAlias(bldg.events.elems, bldg.floors.elems);// false

		// test case to show that kcfa is better than kobj from Manu
		Object x = new Object(); // o1
		Object y = new Object(); // o2
		Object z = a.id(x); // z -> o1
		Object w = a.id(y); // w -> o2
		AliasHelper.notAlias(z, w); // false

		// test case to show that kobj is better than kcfa
		A a2 = new A(o1, o2, o3); // a1
		boolean p = true;
		A a3 = p ? a : a2; // a3 -> {a, a2}
		a3.m(); // a.j -> a, a2.j -> a2
		AliasHelper.notAlias(a.j, a2);
		AliasHelper.notAlias(a2.j, a);
		AliasHelper.alias(a.j, a);

		// test container.
		testContainer();
		
		//testing conjunction and disjunction of cst.
		testCst();
	}

	public static Object test11(A a) {
		return a.foo();
	}
	
	public static void testCst() {
		Object o1 = new Object(); //o1
		Object o2 = new Object(); //o2
		SubCst1 sub1 = new SubCst1(o1, o2); //s1
		SubCst1 sub2 = new SubCst2(o1, o2); //s2
		
		Cst1 cst1 = new Cst1(sub1, sub2);
		Cst1 cst2 = new Cst2(sub1, sub2);
		
		Cst1 cst3 = new Cst1(sub2, sub1);
		Cst1 cst4 = new Cst2(sub2, sub1);
		
		Object o3 = cst1.gut();
		Object o4 = cst2.gut();
		Object o5 = cst3.gut();
		Object o6 = cst4.gut();
		AliasHelper.alias(o1, o4);
		AliasHelper.alias(o1, o5);
		AliasHelper.alias(o2, o3);
		AliasHelper.alias(o2, o6);
		AliasHelper.notAlias(o3, o5);
		AliasHelper.notAlias(o4, o6);
		AliasHelper.notAlias(o3, o4);
		AliasHelper.notAlias(o5, o6);
	}

	static void testContainer() {
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
	}

}
