package scuba.test.testcase.virtualcall;

import scuba.test.testcase.virtualcall.inherit.H1;
import scuba.test.testcase.virtualcall.inherit.H2;
import scuba.test.testcase.virtualcall.inherit.H3;
import scuba.test.testcase.virtualcall.inherit.H4;
import framework.scuba.helper.AliasHelper;

public class Test {

	public static void test() {
		test1();
		test2();
	}

	public static void test1() {
		Object o1 = new Object(); // o1
		Object o2 = new Object(); // o2
		SubCst1 sub1 = new SubCst1(o1, o2); // s1
		SubCst1 sub2 = new SubCst2(o1, o2); // s2

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

	public static void test2() {
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
		
		//test whether we can generate the correct interval constraints.
		Object o7 = new Object();
		Object o8 = new Object();
		Object o9 = new Object();
		H1 h1 = new H1(o7, o8, o9);
		H1 h2 = new H2(o7, o8, o9);
		H1 h3 = new H3(o7, o8, o9);
		H1 h4 = new H4(o7, o8, o9);
		Object o10 = h2.foo();
		//now g and h are alias.
		AliasHelper.alias(o10, o7);
		AliasHelper.alias(o10, o8);
		AliasHelper.notAlias(o10, o9);

		Object o11 = h3.foo();
		AliasHelper.alias(h3.f, h3.h);
		AliasHelper.notAlias(h3.f, h3.g);

		Object o12 = h4.foo();
		AliasHelper.alias(h3.g, h3.h);
		AliasHelper.notAlias(h3.f, h3.h);

		Object o13 = h1.foo();
		AliasHelper.notAlias(o10, o11);
		AliasHelper.notAlias(o11, o12);
		AliasHelper.notAlias(o10, o13);
		AliasHelper.alias(o10, o12);
	}

	public static Object test11(A a) {
		return a.foo();
	}
}
