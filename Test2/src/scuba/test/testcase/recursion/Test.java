package scuba.test.testcase.recursion;

public class Test {

	public static void test() {
		// test1();
		// test2();
		test3();
	}

	private static void test1() {
		// a simplified version of regular expression.
		new Pattern("hi");
	}

	private static void test2() {
		// our analysis generates a lot of noise during recursion.
		TermsHashPerField f = new TermsHashPerField();
		f.add1();
	}

	private static void test3() {
		B t1 = new B();
		t1.foo(new A());
	}

}