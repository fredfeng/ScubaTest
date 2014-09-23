package scuba.test.testcase.tricky;

import framework.scuba.helper.AliasHelper;
import scuba.test.testcase.virtualcall.inherit.H1;

public class Test {

	public static void test() {
		test1();
		test2();
		test3();
	}
	
	private static void test1() {
		//Isil's favorite example.
		H1 x = new H1();
		H1 y = new H1();
		Object a = foo(x, y);//a->o1
		Object b = foo(x, x);//b->o1,o2
		AliasHelper.alias(a, b);
		AliasHelper.notAlias(a, y.f);
		AliasHelper.alias(a, x.f);
	}
	
	private static void test2() {
		//example in the paper.
		
	}
	
	private static void test3() {
		//show the semantic of copy by value.
		Foo foo = new Foo();
		Bar bar = new Bar();//o1
		foo.setX(bar);
		//p2set of bar should only contain o1;
	}
	
	private static Object foo(H1 x, H1 y) {
		//we don't know whether x and y are alias.
		x.f = new Object();//o1
		y.f = new Object();//o2
		Object t = x.f;
		return t;
	}
}