package scuba.test.testcase.ctxt;

public class Test {

	public static void test() {
		test1();
		test2();
		test3();
	}

	public static void test1() {
		System.out.println("hello");
		test1();
		A t1 = new A();
	}

	// imprecision because of propagating locals in SCC
	public static void test2() {
		C t1 = new C();
		D t2 = t1.f;
	}

	// another source of imprecision
	// just check the return value of create() method
	public static void test3() {
		F t1 = E.create();
	}

}
